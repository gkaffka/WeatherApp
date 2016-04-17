package worldWeatherModels;

import io.realm.RealmObject;
import io.realm.annotations.Required;

public class RealmCity extends RealmObject {
    @Required
    private String latitude;
    @Required
    private String longitude;
    private String areaName;
    private String region;
    private String country;

    public RealmCity() {
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getCountry() {
        return country;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAreaName() {
        return areaName;
    }

    public String getRegion() {
        return region;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
