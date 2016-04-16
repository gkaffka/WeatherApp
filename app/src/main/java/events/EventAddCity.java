package events;

import worldWeatherModels.City;

final public class EventAddCity {
    private final City city;

    public EventAddCity(City city) {
        this.city = city;
    }

    public City getCity() {
        return city;
    }
}
