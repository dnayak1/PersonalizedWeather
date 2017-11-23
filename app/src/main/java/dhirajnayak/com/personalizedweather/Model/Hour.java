package dhirajnayak.com.personalizedweather.Model;

/**
 * Created by dhirajnayak on 11/10/17.
 */

public class Hour {
    private String time_epoch,time,temp_c,temp_f,is_day,wind_mph,wind_kph,wind_degree,wind_dir,pressure_mb,pressure_in,precip_mm,
            precip_in,humidity,cloud,feelslike_c,feelslike_f,windchill_c,windchill_f,heatindex_c,heatindex_f,dewpoint_c,dewpoint_f,
            will_it_rain,chance_of_rain,will_it_snow,chance_of_snow,vis_km,vis_miles;
    private Condition condition;

    public String getTime_epoch() {
        return time_epoch;
    }

    public void setTime_epoch(String time_epoch) {
        this.time_epoch = time_epoch;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTemp_c() {
        return temp_c;
    }

    public void setTemp_c(String temp_c) {
        this.temp_c = temp_c;
    }

    public String getTemp_f() {
        return temp_f;
    }

    public void setTemp_f(String temp_f) {
        this.temp_f = temp_f;
    }

    public String getIs_day() {
        return is_day;
    }

    public void setIs_day(String is_day) {
        this.is_day = is_day;
    }

    public String getWind_mph() {
        return wind_mph;
    }

    public void setWind_mph(String wind_mph) {
        this.wind_mph = wind_mph;
    }

    public String getWind_kph() {
        return wind_kph;
    }

    public void setWind_kph(String wind_kph) {
        this.wind_kph = wind_kph;
    }

    public String getWind_degree() {
        return wind_degree;
    }

    public void setWind_degree(String wind_degree) {
        this.wind_degree = wind_degree;
    }

    public String getWind_dir() {
        return wind_dir;
    }

    public void setWind_dir(String wind_dir) {
        this.wind_dir = wind_dir;
    }

    public String getPressure_mb() {
        return pressure_mb;
    }

    public void setPressure_mb(String pressure_mb) {
        this.pressure_mb = pressure_mb;
    }

    public String getPressure_in() {
        return pressure_in;
    }

    public void setPressure_in(String pressure_in) {
        this.pressure_in = pressure_in;
    }

    public String getPrecip_mm() {
        return precip_mm;
    }

    public void setPrecip_mm(String precip_mm) {
        this.precip_mm = precip_mm;
    }

    public String getPrecip_in() {
        return precip_in;
    }

    public void setPrecip_in(String precip_in) {
        this.precip_in = precip_in;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getCloud() {
        return cloud;
    }

    public void setCloud(String cloud) {
        this.cloud = cloud;
    }

    public String getFeelslike_c() {
        return feelslike_c;
    }

    public void setFeelslike_c(String feelslike_c) {
        this.feelslike_c = feelslike_c;
    }

    public String getFeelslike_f() {
        return feelslike_f;
    }

    public void setFeelslike_f(String feelslike_f) {
        this.feelslike_f = feelslike_f;
    }

    public String getWindchill_c() {
        return windchill_c;
    }

    public void setWindchill_c(String windchill_c) {
        this.windchill_c = windchill_c;
    }

    public String getWindchill_f() {
        return windchill_f;
    }

    public void setWindchill_f(String windchill_f) {
        this.windchill_f = windchill_f;
    }

    public String getHeatindex_c() {
        return heatindex_c;
    }

    public void setHeatindex_c(String heatindex_c) {
        this.heatindex_c = heatindex_c;
    }

    public String getHeatindex_f() {
        return heatindex_f;
    }

    public void setHeatindex_f(String heatindex_f) {
        this.heatindex_f = heatindex_f;
    }

    public String getDewpoint_c() {
        return dewpoint_c;
    }

    public void setDewpoint_c(String dewpoint_c) {
        this.dewpoint_c = dewpoint_c;
    }

    public String getDewpoint_f() {
        return dewpoint_f;
    }

    public void setDewpoint_f(String dewpoint_f) {
        this.dewpoint_f = dewpoint_f;
    }

    public String getWill_it_rain() {
        return will_it_rain;
    }

    public void setWill_it_rain(String will_it_rain) {
        this.will_it_rain = will_it_rain;
    }

    public String getChance_of_rain() {
        return chance_of_rain;
    }

    public void setChance_of_rain(String chance_of_rain) {
        this.chance_of_rain = chance_of_rain;
    }

    public String getWill_it_snow() {
        return will_it_snow;
    }

    public void setWill_it_snow(String will_it_snow) {
        this.will_it_snow = will_it_snow;
    }

    public String getChance_of_snow() {
        return chance_of_snow;
    }

    public void setChance_of_snow(String chance_of_snow) {
        this.chance_of_snow = chance_of_snow;
    }

    public String getVis_km() {
        return vis_km;
    }

    public void setVis_km(String vis_km) {
        this.vis_km = vis_km;
    }

    public String getVis_miles() {
        return vis_miles;
    }

    public void setVis_miles(String vis_miles) {
        this.vis_miles = vis_miles;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }
}
