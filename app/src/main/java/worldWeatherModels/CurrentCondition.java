package worldWeatherModels;

import com.google.gson.annotations.SerializedName;

import java.util.List;

final public class CurrentCondition {

    @SerializedName("observation_time")
    final private String observationTime;
    @SerializedName("temp_C")
    final private String tempCelsius;
    @SerializedName("temp_F")
    final private String tempFahrenheit;
    final private String weatherCode;
    final private List<Value> weatherIconUrl;
    final private List<Value> weatherDesc;

    final private String windspeedMiles;
    final private String windspeedKmph;
    final private String winddirDegree;
    final private String winddir16Point;
    final private String precipMM;
    final private String humidity;
    final private String visibility;
    final private String pressure;
    final private String cloudcover;
    final private String FeelsLikeC;
    final private String FeelsLikeF;

    public CurrentCondition(String observationTime, String tempCelsius, String tempFahrenheit, String weatherCode, List<Value> weatherIconUrl, List<Value> weatherDesc, String windspeedMiles, String windspeedKmph, String winddirDegree, String winddir16Point, String precipMM, String humidity, String visibility, String pressure, String cloudcover, String feelsLikeC, String feelsLikeF) {
        this.observationTime = observationTime;
        this.tempCelsius = tempCelsius;
        this.tempFahrenheit = tempFahrenheit;
        this.weatherCode = weatherCode;
        this.weatherIconUrl = weatherIconUrl;
        this.weatherDesc = weatherDesc;
        this.windspeedMiles = windspeedMiles;
        this.windspeedKmph = windspeedKmph;
        this.winddirDegree = winddirDegree;
        this.winddir16Point = winddir16Point;
        this.precipMM = precipMM;
        this.humidity = humidity;
        this.visibility = visibility;
        this.pressure = pressure;
        this.cloudcover = cloudcover;
        FeelsLikeC = feelsLikeC;
        FeelsLikeF = feelsLikeF;
    }

    public String getObservationTime() {
        return observationTime;
    }

    public String getTempCelsius() {
        return tempCelsius;
    }

    public String getTempFahrenheit() {
        return tempFahrenheit;
    }

    public String getWeatherCode() {
        return weatherCode;
    }

    public String getWeatherIconUrl() {
        return weatherIconUrl.get(0).getValue();
    }

    public String getWeatherDesc() {
        return weatherDesc.get(0).getValue();
    }

    public String getWindspeedMiles() {
        return windspeedMiles;
    }

    public String getWindspeedKmph() {
        return windspeedKmph;
    }

    public String getWinddirDegree() {
        return winddirDegree;
    }

    public String getWinddir16Point() {
        return winddir16Point;
    }

    public String getPrecipMM() {
        return precipMM;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getVisibility() {
        return visibility;
    }

    public String getPressure() {
        return pressure;
    }

    public String getCloudcover() {
        return cloudcover;
    }

    public String getFeelsLikeC() {
        return FeelsLikeC;
    }

    public String getFeelsLikeF() {
        return FeelsLikeF;
    }
}
