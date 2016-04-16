package worldWeatherModels;

final public class SearchWeather {
    private final WeatherData data;

    public SearchWeather(WeatherData data) {
        this.data = data;
    }

    public WeatherData getData() {
        return data;
    }
}
