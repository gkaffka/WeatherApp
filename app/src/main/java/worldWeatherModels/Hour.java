package worldWeatherModels;

import java.util.List;

final public class Hour {

    final private String time;
    final private String tempC;
    final private String tempF;
    final private String windspeedMiles;
    final private String windspeedKmph;
    final private String winddirDegree;
    final private String winddir16Point;
    final private String weatherCode;
    final private List<Value> weatherIconUrl;
    final private List<Value> weatherDesc;
    final private String precipMM;
    final private String humidity;
    final private String visibility;
    final private String pressure;
    final private String cloudcover;
    final private String HeatIndexC;
    final private String HeatIndexF;
    final private String DewPointC;
    final private String DewPointF;
    final private String WindChillC;
    final private String WindChillF;
    final private String WindGustMiles;
    final private String WindGustKmph;
    final private String FeelsLikeC;
    final private String FeelsLikeF;
    final private String chanceofrain;
    final private String chanceofremdry;
    final private String chanceofwindy;
    final private String chanceofovercast;
    final private String chanceofsunshine;
    final private String chanceoffrost;
    final private String chanceofhightemp;
    final private String chanceoffog;
    final private String chanceofsnow;
    final private String chanceofthunder;

    public Hour(String time, String tempC, String tempF, String windspeedMiles, String windspeedKmph, String winddirDegree, String winddir16Point, String weatherCode, List<Value> weatherIconUrl, List<Value> weatherDesc, String precipMM, String humidity, String visibility, String pressure, String cloudcover, String heatIndexC, String heatIndexF, String dewPointC, String dewPointF, String windChillC, String windChillF, String windGustMiles, String windGustKmph, String feelsLikeC, String feelsLikeF, String chanceofrain, String chanceofremdry, String chanceofwindy, String chanceofovercast, String chanceofsunshine, String chanceoffrost, String chanceofhightemp, String chanceoffog, String chanceofsnow, String chanceofthunder) {
        this.time = time;
        this.tempC = tempC;
        this.tempF = tempF;
        this.windspeedMiles = windspeedMiles;
        this.windspeedKmph = windspeedKmph;
        this.winddirDegree = winddirDegree;
        this.winddir16Point = winddir16Point;
        this.weatherCode = weatherCode;
        this.weatherIconUrl = weatherIconUrl;
        this.weatherDesc = weatherDesc;
        this.precipMM = precipMM;
        this.humidity = humidity;
        this.visibility = visibility;
        this.pressure = pressure;
        this.cloudcover = cloudcover;
        HeatIndexC = heatIndexC;
        HeatIndexF = heatIndexF;
        DewPointC = dewPointC;
        DewPointF = dewPointF;
        WindChillC = windChillC;
        WindChillF = windChillF;
        WindGustMiles = windGustMiles;
        WindGustKmph = windGustKmph;
        FeelsLikeC = feelsLikeC;
        FeelsLikeF = feelsLikeF;
        this.chanceofrain = chanceofrain;
        this.chanceofremdry = chanceofremdry;
        this.chanceofwindy = chanceofwindy;
        this.chanceofovercast = chanceofovercast;
        this.chanceofsunshine = chanceofsunshine;
        this.chanceoffrost = chanceoffrost;
        this.chanceofhightemp = chanceofhightemp;
        this.chanceoffog = chanceoffog;
        this.chanceofsnow = chanceofsnow;
        this.chanceofthunder = chanceofthunder;
    }

    public String getTime() {
        return time;
    }

    public String getTempC() {
        return tempC;
    }

    public String getTempF() {
        return tempF;
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

    public String getWeatherCode() {
        return weatherCode;
    }

    public String getWeatherIconUrl() {
        return weatherIconUrl.get(0).getValue();
    }

    public String getWeatherDesc() {
        return weatherDesc.get(0).getValue();
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

    public String getHeatIndexC() {
        return HeatIndexC;
    }

    public String getHeatIndexF() {
        return HeatIndexF;
    }

    public String getDewPointC() {
        return DewPointC;
    }

    public String getDewPointF() {
        return DewPointF;
    }

    public String getWindChillC() {
        return WindChillC;
    }

    public String getWindChillF() {
        return WindChillF;
    }

    public String getWindGustMiles() {
        return WindGustMiles;
    }

    public String getWindGustKmph() {
        return WindGustKmph;
    }

    public String getFeelsLikeC() {
        return FeelsLikeC;
    }

    public String getFeelsLikeF() {
        return FeelsLikeF;
    }

    public String getChanceofrain() {
        return chanceofrain;
    }

    public String getChanceofremdry() {
        return chanceofremdry;
    }

    public String getChanceofwindy() {
        return chanceofwindy;
    }

    public String getChanceofovercast() {
        return chanceofovercast;
    }

    public String getChanceofsunshine() {
        return chanceofsunshine;
    }

    public String getChanceoffrost() {
        return chanceoffrost;
    }

    public String getChanceofhightemp() {
        return chanceofhightemp;
    }

    public String getChanceoffog() {
        return chanceoffog;
    }

    public String getChanceofsnow() {
        return chanceofsnow;
    }

    public String getChanceofthunder() {
        return chanceofthunder;
    }
}
