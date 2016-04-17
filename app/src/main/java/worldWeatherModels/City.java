package worldWeatherModels;

import java.util.List;

final public class City{
    private final List<Value> areaName;
    private final List<Value> country;
    private final List<Value> region;
    private final String latitude;
    private final String longitude;
    private final List<Value> weatherUrl;

    public City(List<Value> areaName, List<Value> country, List<Value> region, String latitude, String longitude, List<Value> weatherUrl) {
        this.areaName = areaName;
        this.country = country;
        this.region = region;
        this.latitude = latitude;
        this.longitude = longitude;
        this.weatherUrl = null;
    }

    public String getAreaName() {
        return areaName.get(0).getValue();
    }

    public String getCountry() {
        return country.get(0).getValue();
    }

    public String getRegion() {
        return region.get(0).getValue();
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getCoordinates() {
        return getLatitude() + " / " + getLongitude();
    }

    public List<Value> getWeatherUrl() {
        return weatherUrl;
    }

    public String getCityAndArea() {
        return getAreaName() + " - " + getRegion();
    }
}
