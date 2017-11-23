package dhirajnayak.com.personalizedweather.Model;

import java.util.ArrayList;

/**
 * Created by dhirajnayak on 11/10/17.
 */

public class ForecastDay {
    private String date, date_epoch;
    private Day day;
    private Astro astro;
    private ArrayList<Hour> hour;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate_epoch() {
        return date_epoch;
    }

    public void setDate_epoch(String date_epoch) {
        this.date_epoch = date_epoch;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public Astro getAstro() {
        return astro;
    }

    public void setAstro(Astro astro) {
        this.astro = astro;
    }

    public ArrayList<Hour> getHour() {
        return hour;
    }

    public void setHour(ArrayList<Hour> hour) {
        this.hour = hour;
    }
}
