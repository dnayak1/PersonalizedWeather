package dhirajnayak.com.personalizedweather;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import dhirajnayak.com.personalizedweather.Model.Hour;

/**
 * Created by dhirajnayak on 11/22/17.
 */

public class HourlyForecastAdapter extends RecyclerView.Adapter<HourlyForecastAdapter.HourlyForecastViewHolder> {
    ArrayList<Hour> hourlyForecast=new ArrayList<>();
    Context mContext;
    private IHourlyForecastListener hourlyForecastListener;

    public HourlyForecastAdapter(Context mContext, ArrayList<Hour> hourlyForecast, IHourlyForecastListener hourlyForecastListener) {
        this.hourlyForecast = hourlyForecast;
        this.mContext = mContext;
        this.hourlyForecastListener = hourlyForecastListener;

    }

    @Override
    public HourlyForecastAdapter.HourlyForecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(mContext);
        View view= layoutInflater.inflate(R.layout.hourly_forecast_layout,parent,false);
        HourlyForecastAdapter.HourlyForecastViewHolder hourlyForecastViewHolder=new HourlyForecastAdapter.HourlyForecastViewHolder(view);
        return hourlyForecastViewHolder;
    }

    @Override
    public void onBindViewHolder(HourlyForecastViewHolder holder, int position) {
        String ampm="";
        final Hour forecastHour=hourlyForecast.get(position);
        int time = Integer.parseInt(forecastHour.getTime().substring(11,13));
        int temp_c = (int)Double.parseDouble(forecastHour.getTemp_c());

        if(time>12){
            time = time-12;
            ampm = "PM" ;
        }else if(time == 0){
            time = 12;
            ampm = "AM";
        }else if (time == 12){
            ampm = "PM";
        }
        else{
            ampm = "AM";
        }
        holder.textViewHourlyHour.setText(time+ampm);

        //holder.textViewHourlyTemp.setText("abc");

        Picasso.with(mContext).load("http:"+forecastHour.getCondition().getIcon()).into(holder.imageViewHourlyIcon);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hourlyForecastListener.hourlyDetails(forecastHour);
            }
        });
    }

    @Override
    public int getItemCount() {
        return hourlyForecast.size();
    }

    public static class HourlyForecastViewHolder extends RecyclerView.ViewHolder{

        ImageView imageViewHourlyIcon;
        TextView textViewHourlyTemp;
        TextView textViewHourlyHour;

        public HourlyForecastViewHolder(View itemView) {
            super(itemView);
            textViewHourlyTemp = (TextView) itemView.findViewById(R.id.textViewHourlyTemps);
            imageViewHourlyIcon = (ImageView) itemView.findViewById(R.id.imageViewHourlyIcon);
            textViewHourlyHour = (TextView) itemView.findViewById(R.id.textViewHourlyHour);
        }

    }

    interface IHourlyForecastListener
    {
        void hourlyDetails(Hour hour);
    }
}
