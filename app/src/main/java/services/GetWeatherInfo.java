package services;

import org.greenrobot.eventbus.EventBus;

import events.EventError;
import events.EventGetWeatherInfo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import worldWeatherModels.SearchWeather;
import worldWeatherModels.WeatherData;

public class GetWeatherInfo extends BaseRetrofitService {

    public void getWeatherInfo(String latitude, String longitude) {
        String query = latitude + "," + longitude;
        String json = "json";
        String API_KEY = "183b43b8af2e4fee863110236161504";
        getApiInterface().getWeatherFromCoordinates(query, json, API_KEY).enqueue(new Callback<SearchWeather>() {
            @Override
            public void onResponse(Call<SearchWeather> call, Response<SearchWeather> response) {
                if (response.isSuccessful()) {
                    WeatherData wd = null;
                    if (response.body().getData() != null)
                        wd = response.body().getData();
                    EventBus.getDefault().post(new EventGetWeatherInfo(wd));
                } else
                    sendErrorEvent(response);
            }

            @Override
            public void onFailure(Call<SearchWeather> call, Throwable t) {
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
