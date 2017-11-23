package dhirajnayak.com.personalizedweather;

import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import dhirajnayak.com.personalizedweather.Interface.IRetrofit;
import dhirajnayak.com.personalizedweather.Model.Current;
import dhirajnayak.com.personalizedweather.Model.Hour;
import dhirajnayak.com.personalizedweather.Model.Weather;
import dhirajnayak.com.personalizedweather.databinding.ActivityCurrentWeatherBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static dhirajnayak.com.personalizedweather.Constant.API_KEY;
import static dhirajnayak.com.personalizedweather.Constant.BASE_URL;
import static dhirajnayak.com.personalizedweather.Constant.FORECAST_DAYS;

public class CurrentWeatherActivity extends AppCompatActivity implements HourlyForecastAdapter.IHourlyForecastListener {
    ActivityCurrentWeatherBinding binding;
    HourlyForecastAdapter hourlyForecastAdapter;
    LinearLayoutManager layoutManager;
    RecyclerView recyclerViewHourly;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_weather);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_current_weather);
        //recyclerViewHourly = (RecyclerView) findViewById(R.id.recyclerViewHourly);
        String selectedCity = getIntent().getStringExtra("selectedCity");
        if(selectedCity != null || !selectedCity.isEmpty()){
            getWeather(selectedCity);
        }
    }

    public Weather getWeather(String city)
    {
        final Weather weather=new Weather();
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IRetrofit service=retrofit.create(IRetrofit.class);
        Call<Weather> call=service.getWeatherDetails(API_KEY,city,FORECAST_DAYS);
        call.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                weather.setCurrent(response.body().getCurrent());
                weather.setForecast(response.body().getForecast());
                weather.setLocation(response.body().getLocation());
                setData(weather);
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {

            }
        });
        return weather;
    }

    public void setData(Weather weather){
        if(weather.getCurrent().getIs_day().equals("1")){
            binding.linearLayoutBackground.setBackgroundResource(R.drawable.background_image);
        }else{
            binding.linearLayoutBackground.setBackgroundResource(R.drawable.night);
        }
        int currTempCelsius = (int)Double.parseDouble(weather.getCurrent().getTemp_c());
        int currTempFahrenheit = (int)Double.parseDouble(weather.getCurrent().getTemp_f());
        int maxCelsius = (int)Double.parseDouble(weather.getForecast().getForecastday().get(0).getDay().getMaxtemp_c());
        int maxFahrenheit = (int)Double.parseDouble(weather.getForecast().getForecastday().get(0).getDay().getMaxtemp_f());
        int minCelsius = (int)Double.parseDouble(weather.getForecast().getForecastday().get(0).getDay().getMintemp_c());
        int minFahrenheit = (int)Double.parseDouble(weather.getForecast().getForecastday().get(0).getDay().getMintemp_f());
        int feelsLikeCelsius=(int)Double.parseDouble(weather.getCurrent().getFeelslike_c());
        int feelsLikeFahrenheit=(int)Double.parseDouble(weather.getCurrent().getFeelslike_f());
        binding.textViewCurrentLastUpdated.setText("Last updated: "+weather.getCurrent().getLast_updated());
        binding.textViewCurrMaxMin.setText("Max "+maxCelsius + " Min "+minCelsius);
        binding.textViewCurrTemp.setText(String.valueOf(currTempCelsius));
        binding.textViewCurrFeelsLike.setText(String.valueOf(feelsLikeCelsius));
        Picasso.with(CurrentWeatherActivity.this).load("https:"+weather.getCurrent().getCondition().getIcon()).into(binding.imageViewCurrentIcon);
        binding.textViewCurrentDesc.setText(weather.getCurrent().getCondition().getText());
        setHourlyData(prepareHourlyData(weather));

    }

    public void setHourlyData(ArrayList<Hour> hourlyData){
        hourlyForecastAdapter = new HourlyForecastAdapter(CurrentWeatherActivity.this,hourlyData,CurrentWeatherActivity.this);
        layoutManager = new LinearLayoutManager(CurrentWeatherActivity.this,LinearLayoutManager.HORIZONTAL,false);
        binding.recyclerHourly.setAdapter(hourlyForecastAdapter);
        binding.recyclerHourly.setLayoutManager(layoutManager);
        hourlyForecastAdapter.notifyDataSetChanged();

    }

    @Override
    public void hourlyDetails(Hour hour) {

    }

    public ArrayList<Hour> prepareHourlyData(Weather weather){
        ArrayList<Hour> sameDayHours = weather.getForecast().getForecastday().get(0).getHour();
        ArrayList<Hour> nextDayHours = weather.getForecast().getForecastday().get(1).getHour();
        ArrayList<Hour> requiredHours = new ArrayList<>();
        int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        for(int i = currentHour+1 ;i<sameDayHours.size();i++){
            requiredHours.add(sameDayHours.get(i));
        }
        for(int i = 0;i<=currentHour;i++){
            requiredHours.add(nextDayHours.get(i));
        }
        Log.d("demo",String.valueOf(currentHour));
        return requiredHours;

    }
}
