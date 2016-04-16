package activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import adapters.CityListAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import events.EventAddCity;
import weatherapp.com.kaffka.weatherapp.R;
import worldWeatherModels.City;

public class MainActivity extends AppCompatActivity {

    private List<City> mCityList;
    private CityListAdapter mAdapter;

    @Bind(R.id.recycler_view)
    RecyclerView mRecycler;
    @Bind(R.id.txt_fab_explainer)
    TextView mTxtFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initRecyclerView();
    }

    @OnClick(R.id.fab)
    public void fabClick() {
        startActivity(new Intent(this, CitySearch.class));
    }


    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void addCity(EventAddCity ev) {
        if (ev.getCity() == null) {
            Toast.makeText(MainActivity.this, R.string.generic_error, Toast.LENGTH_LONG).show();
            return;
        }
        mTxtFab.setVisibility(View.GONE);
        mCityList.add(ev.getCity());
        mAdapter.notifyDataSetChanged();
        EventBus.getDefault().removeStickyEvent(ev);
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
        mCityList = new ArrayList<>();
        mAdapter = new CityListAdapter(mCityList, this);
        mRecycler.setAdapter(mAdapter);
    }

    public TextView getFabExplainer() {
        return mTxtFab;
    }
}
