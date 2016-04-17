package activities;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import adapters.CityListAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;
import events.EventError;
import events.EventGetCityList;
import services.GetCityList;
import weatherapp.com.kaffka.weatherapp.R;
import worldWeatherModels.City;

public class CitySearch extends AppCompatActivity {

    private List<City> mCityList;
    private CityListAdapter mAdapter;
    private ProgressDialog mDialog;

    @Bind(R.id.recycler_view)
    RecyclerView mRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_city);
        ButterKnife.bind(this);
        initToolbar();
        initRecyclerView();
        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            initProgressDialog();
            String query = intent.getStringExtra(SearchManager.QUERY);
            new GetCityList().getCityList(query);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconified(false);
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void loadList(EventGetCityList ev) {
        mDialog.dismiss();
        if (ev.getResults().isEmpty()) {
            Toast.makeText(CitySearch.this, R.string.no_city_found, Toast.LENGTH_LONG).show();
            return;
        }

        mCityList.clear();
        mCityList.addAll(ev.getResults());
        mAdapter.notifyDataSetChanged();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onError(EventError ev) {
        mDialog.dismiss();
        Toast.makeText(this, R.string.generic_error, Toast.LENGTH_LONG).show();
    }

    private void initToolbar() {
        try {
            getSupportActionBar().setTitle("");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }catch (NullPointerException e){
            Toast.makeText(this,R.string.generic_error,Toast.LENGTH_LONG).show();
        }
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

    private void initProgressDialog() {
        mDialog = new ProgressDialog(this);
        mDialog.setMessage(getString(R.string.loading_message));
        mDialog.show();
    }

}