package worldWeatherModels;

import java.util.List;

final public class Results {
    private final List<City> results;

    public Results(List<City> results) {
        this.results = results;
    }

    public List<City> getResults() {
        return results;
    }
}
