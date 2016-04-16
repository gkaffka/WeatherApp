package apiInterfaces;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import worldWeatherModels.SearchCity;
import worldWeatherModels.SearchWeather;

public interface WorldWeatherApi {

    String BASE_API_URL = "https://api.worldweatheronline.com/";

    @GET(BASE_API_URL + "premium/v1/search.ashx")
    Call<SearchCity> getCitiesFromInput(@Query("q") String q,
                                        @Query("format") String format,
                                        @Query("key") String key,
                                        @Query("popular") boolean popular);

    @GET(BASE_API_URL + "premium/v1/weather.ashx")
    Call<SearchWeather> getWeatherFromCoordinates(@Query("q") String q,
                                                  @Query("format") String format,
                                                  @Query("key") String key);
}
