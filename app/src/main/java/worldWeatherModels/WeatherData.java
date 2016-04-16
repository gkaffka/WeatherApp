package worldWeatherModels;

import com.google.gson.annotations.SerializedName;

import java.util.List;

final public class WeatherData {
    @SerializedName("current_condition")
    private final List<CurrentCondition> currentCondition;
    private final List<Weather> weather;

    public WeatherData(List<CurrentCondition> currentCondition, List<Weather> weather) {
        this.currentCondition = currentCondition;
        this.weather = weather;
    }

    public CurrentCondition getCurrentCondition() {
        return currentCondition.get(0);
    }

    public List<Weather> getWeather() {
        return weather;
    }
}
