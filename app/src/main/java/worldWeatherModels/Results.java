package worldWeatherModels;

import java.util.List;

final public class Results {
    private final List<City> result;

    public Results(List<City> results) {
        this.result = results;
    }

    public List<City> getResults() {
        return result;
    }
}
