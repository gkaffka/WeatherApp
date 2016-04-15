package services;

import apiInterfaces.WorldWeatherApi;
import retrofit2.Retrofit;

public class GetCityList extends BaseRetrofitService {
    final String API_KEY = "183b43b8af2e4fee863110236161504";

    public GetCityList(Retrofit mRestAdapter, WorldWeatherApi mServiceInterface) {
        super(mRestAdapter, mServiceInterface);
    }
}
