package services;

import java.util.concurrent.TimeUnit;

import apiInterfaces.WorldWeatherApi;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseRetrofitService {
    private final Retrofit mRestAdapter;
    public final WorldWeatherApi mServiceInterface;

    public BaseRetrofitService(Retrofit mRestAdapter, WorldWeatherApi mServiceInterface) {
        this.mRestAdapter = mRestAdapter;
        this.mServiceInterface = mServiceInterface;

        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.connectTimeout(15, TimeUnit.SECONDS);
        okHttpClient.readTimeout(15, TimeUnit.SECONDS);
        okHttpClient.writeTimeout(15, TimeUnit.SECONDS);
        okHttpClient.retryOnConnectionFailure(true);

        OkHttpClient client = okHttpClient.build();
        mRestAdapter = new Retrofit.Builder()
                .baseUrl(WorldWeatherApi.BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client).build();

        mServiceInterface = mRestAdapter.create(WorldWeatherApi.class);
    }
}
