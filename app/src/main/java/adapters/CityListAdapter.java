package adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import activities.MainActivity;
import activities.WeatherInfo;
import butterknife.Bind;
import butterknife.ButterKnife;
import events.EventAddCity;
import services.CityDataBase;
import util.Constants;
import weatherapp.com.kaffka.weatherapp.R;
import worldWeatherModels.City;

public class CityListAdapter extends RecyclerView.Adapter<CityListAdapter.ViewHolder> {

    private final List<City> mCityList;
    private final Context mContext;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.txt_city_name_and_area)
        TextView mCityName;
        @Bind(R.id.txt_country_name)
        TextView mCountryName;
        @Bind(R.id.txt_coordinates)
        TextView mCoordinates;
        @Bind(R.id.card)
        CardView mItemCard;
        @Bind(R.id.img_delete)
        ImageView mImgDelete;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    public CityListAdapter(List<City> mCityList, Context mCtx) {
        this.mCityList = mCityList;
        this.mContext = mCtx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_city, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (mCityList.isEmpty()) return;
        loadViews(holder, position);
    }

    @Override
    public int getItemCount() {
        return mCityList.size();
    }


    private void loadViews(ViewHolder holder, final int position) {
        final City m = mCityList.get(position);
        try {
            if (showRemoveOption()) holder.mImgDelete.setVisibility(View.VISIBLE);
            holder.mCityName.setText(m.getCityAndArea());
            holder.mCountryName.setText(m.getCountry());
            holder.mCoordinates.setText(m.getCoordinates());
            holder.mItemCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!showRemoveOption()) {
                        EventBus.getDefault().postSticky(new EventAddCity(m));
                        ((AppCompatActivity) mContext).finish();
                    } else {
                        mContext.startActivity(getWeatherInfoIntent(m.getLatitude(), m.getLongitude(), m.getCityAndArea()));
                    }
                }
            });
            holder.mImgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    removeItem(position);
                }
            });

        } catch (NullPointerException e) {
            Toast.makeText(mContext, R.string.generic_error, Toast.LENGTH_LONG).show();
        }
    }

    private boolean showRemoveOption() {
        return mContext instanceof MainActivity;
    }

    private void removeItem(int position) {
        new CityDataBase(mContext).deleteCityFromDb(mCityList.get(position));
        mCityList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mCityList.size());
        showExplainer();
    }

    private void showExplainer() {
        if (showRemoveOption()) {
            if (mCityList.isEmpty())
                ((MainActivity) mContext).getFabExplainer().setVisibility(View.VISIBLE);
            else
                ((MainActivity) mContext).getFabExplainer().setVisibility(View.GONE);
        }
    }

    private Intent getWeatherInfoIntent(String latitude, String longitude, String location) {
        Intent i = new Intent(mContext, WeatherInfo.class);
        i.putExtra(Constants.intents.LATITUDE.name(), latitude);
        i.putExtra(Constants.intents.LONGITUDE.name(), longitude);
        i.putExtra(Constants.intents.LOCATION_NAME.name(), location);
        return i;
    }
}
