package adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import weatherapp.com.kaffka.weatherapp.R;
import worldWeatherModels.Weather;

public class WeatherInfoAdapter extends RecyclerView.Adapter<WeatherInfoAdapter.ViewHolder> {

    private final List<Weather> mWeatherList;
    private final Context mContext;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.txt_date)
        TextView mTxtDate;
        @Bind(R.id.txt_weather_temperatures_c)
        TextView mTxtTempC;
        @Bind(R.id.txt_weather_temperatures_f)
        TextView mTxtTempF;
        @Bind(R.id.txt_weather_status)
        TextView mTxtStatus;
        @Bind(R.id.txt_precipitation)
        TextView mTxtPrec;
        @Bind(R.id.txt_humidity)
        TextView mTxtHumi;
        @Bind(R.id.txt_wind)
        TextView mTxtWind;

        @Bind(R.id.img_weather)
        ImageView mImgWeather;

        @Bind(R.id.seekBar)
        SeekBar mSeekBar;


        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    public WeatherInfoAdapter(List<Weather> mWeatherList, Context mCtx) {
        this.mWeatherList = mWeatherList;
        this.mContext = mCtx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_day_weather, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (mWeatherList.isEmpty()) return;
        loadViews(holder, position);
    }

    @Override
    public int getItemCount() {
        return mWeatherList.size();
    }


    private void loadViews(final ViewHolder holder, final int position) {
        final Weather w = mWeatherList.get(position);
        try {
            holder.mTxtDate.setText(w.getDate());

            holder.mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    if (w.getHourly() == null || w.getHourly().isEmpty() || i > w.getHourly().size()) return;
                    holder.mTxtStatus.setText(w.getHourly().get(i).getWeatherDesc());
                    holder.mTxtTempC.setText(w.getHourly().get(i).getTempC() + mContext.getString(R.string.celsius));
                    holder.mTxtTempF.setText(w.getHourly().get(i).getTempF() + mContext.getString(R.string.fahrenheit));
                    holder.mTxtPrec.setText(mContext.getString(R.string.precipitation) + w.getHourly().get(i).getChanceofrain());
                    holder.mTxtHumi.setText(mContext.getString(R.string.humidity) + w.getHourly().get(i).getHumidity());
                    holder.mTxtWind.setText(mContext.getString(R.string.wind) + w.getHourly().get(i).getWindspeedKmph() + mContext.getString(R.string.fahrenheit));
                    Picasso.with(mContext).load(w.getHourly().get(i).getWeatherIconUrl()).into(holder.mImgWeather);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
            holder.mTxtStatus.setText(w.getHourly().get(0).getWeatherDesc());
            holder.mTxtTempC.setText(w.getHourly().get(0).getTempC());
            holder.mTxtTempF.setText(w.getHourly().get(0).getTempF());
            Picasso.with(mContext).load(w.getHourly().get(0).getWeatherIconUrl()).into(holder.mImgWeather);

        } catch (NullPointerException e) {
            Toast.makeText(mContext, R.string.generic_error, Toast.LENGTH_LONG).show();
        }
    }
}
