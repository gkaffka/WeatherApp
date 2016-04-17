package services;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import events.EventError;
import events.EventGetCityList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import worldWeatherModels.City;
import worldWeatherModels.SearchCity;

public class GetCityList extends BaseRetrofitService {

    public void getCityList(String query) {
        String API_KEY = "183b43b8af2e4fee863110236161504";
        String json = "json";
        getApiInterface().getCitiesFromInput(query, json, API_KEY, true).enqueue(new Callback<SearchCity>() {
            @Override
            public void onResponse(Call<SearchCity> call, Response<SearchCity> response) {
                if (response.isSuccessful()) {
                    List<City> cityList = new ArrayList<>();
                    if (response.body().getSearchApi() != null)
                        cityList = response.body().getSearchApi().getResults();
                    EventBus.getDefault().post(new EventGetCityList(cityList));
                } else
                    sendErrorEvent(response);
            }

            @Override
            public void onFailure(Call<SearchCity> call, Throwable t) {
                sendErrorEvent(null);
            }
        });
    }

    private String getErrorFromResponse(Response response) {
        try {
            return response.errorBody().string();
        } catch (Exception e) {
            return null;
        }
    }

    private void sendErrorEvent(Response response) {
        EventBus.getDefault().post(new EventError(getErrorFromResponse(response)));
    }

}
