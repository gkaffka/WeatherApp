package activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import adapters.WeatherInfoAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;
import events.EventError;
import events.EventGetWeatherInfo;
import services.GetWeatherInfo;
import util.Constants;
import weatherapp.com.kaffka.weatherapp.R;
import worldWeatherModels.Weather;

public class WeatherInfo extends AppCompatActivity {

    private List<Weather> mWeatherList;
    private WeatherInfoAdapter mAdapter;
    private String latitude, longitude, location_name;
    private ProgressDialog mDialog;

    @Bind(R.id.recycler_view)
    RecyclerView mRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_info);
        ButterKnife.bind(this);
        initRecyclerView();
        initArguments();
        callService();
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void addCity(EventGetWeatherInfo ev) {
        mDialog.dismiss();
        if (ev.getWeatherData().getWeather() == null) {
            Toast.makeText(WeatherInfo.this, R.string.generic_error, Toast.LENGTH_LONG).show();
            return;
        }
        mWeatherList.addAll(ev.getWeatherData().getWeather());
        mAdapter.notifyDataSetChanged();
        EventBus.getDefault().removeStickyEvent(ev);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onError(EventError ev) {
        mDialog.dismiss();
        Toast.makeText(this, R.string.generic_error, Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    private void initRecyclerView() {
        LinearLayoutManager mLayoutManager;
        mLayoutManager = new LinearLayoutManager(this);
        mRecycler.setLayoutManager(mLayoutManager);
        mWeatherList = new ArrayList<>();
        mAdapter = new WeatherInfoAdapter(mWeatherList, this);
        mRecycler.setAdapter(mAdapter);
    }

    private void initArguments() {
        latitude = getIntent().getStringExtra(Constants.intents.LATITUDE.name());
        longitude = getIntent().getStringExtra(Constants.intents.LONGITUDE.name());
        location_name = getIntent().getStringExtra(Constants.intents.LOCATION_NAME.name());
    }

    private void callService() {
        if (latitude == null || longitude == null || location_name == null) {
            Toast.makeText(this, R.string.generic_error, Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        initProgressDialog();
        new GetWeatherInfo().getWeatherInfo(latitude, longitude);
    }

    private void initProgressDialog() {
        mDialog = new ProgressDialog(this);
        mDialog.setMessage(getString(R.string.loading_message_weather));
        mDialog.show();
    }

    private void initToolbar() {
        getSupportActionBar().setTitle(getString(R.string.app_name) + " - " + location_name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}
