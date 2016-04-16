package worldWeatherModels;

final public class Astronomy {

    final private String sunrise;
    final private String sunset;
    final private String moonrise;
    final private String moonset;

    public Astronomy(String sunrise, String sunset, String moonrise, String moonset) {
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.moonrise = moonrise;
        this.moonset = moonset;
    }

    public String getSunrise() {
        return sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public String getMoonrise() {
        return moonrise;
    }

    public String getMoonset() {
        return moonset;
    }
}
