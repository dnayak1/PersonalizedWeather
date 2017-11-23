package dhirajnayak.com.personalizedweather.Interface;

import java.util.List;

import dhirajnayak.com.personalizedweather.Model.City;
import dhirajnayak.com.personalizedweather.Model.Weather;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by dhirajnayak on 11/7/17.
 */

public interface IRetrofit {
    @GET("search.json")
    Call<List<City>> getLocationData(@Query("key") String apiKey,
                                     @Query("q") String searchText);
    @GET("forecast.json")
    Call<Weather> getWeatherDetails(@Query("key") String apiKey,
                                    @Query("q") String searchCity,
                                    @Query("days") int numOfDays);
}
