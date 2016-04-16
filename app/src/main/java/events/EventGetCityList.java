package events;

import java.util.List;

import worldWeatherModels.City;

final public class EventGetCityList {
    final private List<City> results;

    public EventGetCityList(List<City> results) {
        this.results = results;
    }

    public List<City> getResults() {
        return results;
    }
}