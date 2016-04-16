package events;

import worldWeatherModels.WeatherData;

final public class EventGetWeatherInfo {
    private final WeatherData weatherData;

    public EventGetWeatherInfo(WeatherData weatherData) {
        this.weatherData = weatherData;
    }

    public WeatherData getWeatherData() {
        return weatherData;
    }
}
