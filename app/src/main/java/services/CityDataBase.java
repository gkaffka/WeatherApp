package services;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import worldWeatherModels.City;
import worldWeatherModels.RealmCity;
import worldWeatherModels.Value;

public class CityDataBase {

    private Context mContext;
    private Realm mRealm;


    public CityDataBase(Context mContext) {
        this.mContext = mContext;
        initRealm();
    }

    public void saveCityToDb(final City city) {
        RealmCity rCity = new RealmCity();
        rCity.setCountry(city.getCountry());
        rCity.setLatitude(city.getLatitude());
        rCity.setLongitude(city.getLongitude());
        rCity.setCountry(city.getCountry());
        rCity.setRegion(city.getRegion());
        rCity.setAreaName(city.getAreaName());
        mRealm.beginTransaction();
        mRealm.copyToRealm(rCity);
        mRealm.commitTransaction();
    }


    public List<City> getCityFromDb() {
        List<City> cityList = new ArrayList<>();
        RealmResults<RealmCity> realmCities = mRealm.allObjects(RealmCity.class);
        for (RealmCity r : realmCities) {
            cityList.add(new City(getListValue(r.getAreaName()),
                    getListValue(r.getCountry()),
                    getListValue(r.getRegion()),
                    r.getLatitude(),
                    r.getLongitude(),
                    null));
        }
        return cityList;
    }

    public void deleteCityFromDb(City city) {
        RealmCity rcity = mRealm.where(RealmCity.class)
                .equalTo("latitude", city.getLatitude())
                .equalTo("longitude", city.getLongitude()).findFirst();
        mRealm.beginTransaction();
        rcity.removeFromRealm();
        mRealm.commitTransaction();
    }

    private void initRealm() {
        mRealm = Realm.getDefaultInstance();
    }

    private List<Value> getListValue(String value) {
        List<Value> v = new ArrayList<>();
        v.add(new Value(value));
        return v;
    }
}
