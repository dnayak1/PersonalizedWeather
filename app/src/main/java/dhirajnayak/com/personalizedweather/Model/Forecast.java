package dhirajnayak.com.personalizedweather.Model;

import java.util.ArrayList;

/**
 * Created by dhirajnayak on 11/10/17.
 */

public class Forecast {
    private ArrayList<ForecastDay> forecastday;

    public ArrayList<ForecastDay> getForecastday() {
        return forecastday;
    }

    public void setForecastday(ArrayList<ForecastDay> forecastday) {
        this.forecastday = forecastday;
    }
}
