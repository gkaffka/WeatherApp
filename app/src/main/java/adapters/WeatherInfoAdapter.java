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
import worldWeatherModels.CurrentCondition;
import worldWeatherModels.Weather;

public class WeatherInfoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<Weather> mWeatherList;
    private final List<CurrentCondition> mCurrentCondition;
    private final Context mContext;
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.txt_date)
        TextView mTxtDate;
        @Bind(R.id.txt_weather_temperatures_c)
        TextView mTxtTempC;
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

    public static class ViewHolderHeader extends RecyclerView.ViewHolder {
        @Bind(R.id.txt_weather_temperatures_c)
        TextView mTxtTempC;
        @Bind(R.id.txt_weather_status)
        TextView mTxtStatus;
        @Bind(R.id.txt_precipitation)
        TextView mTxtPrec;
        @Bind(R.id.txt_humidity)
        TextView mTxtHumi;
        @Bind(R.id.txt_wind)
        TextView mTxtWind;
        @Bind(R.id.txt_hour)
        TextView mTxtHour;

        @Bind(R.id.img_weather)
        ImageView mImgWeather;

        public ViewHolderHeader(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    public WeatherInfoAdapter(List<Weather> mWeatherList, List<CurrentCondition> mCurrentCondition, Context mCtx) {
        this.mWeatherList = mWeatherList;
        this.mCurrentCondition = mCurrentCondition;
        this.mContext = mCtx;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_HEADER) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.header_day_weather, parent, false);
            return new ViewHolderHeader(v);
        } else if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_day_weather, parent, false);
            return new ViewHolder(v);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (mWeatherList.isEmpty()) return;
        if (holder instanceof ViewHolder) {
            loadViews((ViewHolder) holder, position - 1);
        } else if (holder instanceof ViewHolderHeader) {
            loadHeaderViews((ViewHolderHeader) holder);
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position)) return TYPE_HEADER;
        return TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return mWeatherList.size();
    }


    private void loadViews(final ViewHolder holder, final int position) {
        final Weather w = mWeatherList.get(position);
        try {
            String date = w.getDate();
            holder.mTxtDate.setText(getFormattedDate(date));
            initViews(holder, w, 0);
            holder.mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    if (w.getHourly() == null || w.getHourly().isEmpty() || i > w.getHourly().size())
                        return;
                    initViews(holder, w, i);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                }
            });

        } catch (Exception e) {
            Toast.makeText(mContext, R.string.generic_error, Toast.LENGTH_LONG).show();
        }
    }


    private void loadHeaderViews(final ViewHolderHeader holder) {
        try {
            if(mCurrentCondition!=null)
            initHeaderViews(holder);
        } catch (Exception e) {
        }
    }

    private void initViews(ViewHolder holder, Weather w, int i) {
        holder.mTxtStatus.setText(w.getHourly().get(i).getWeatherDesc());
        String tempC = w.getHourly().get(i).getTempC() + mContext.getString(R.string.celsius);
        String tempF = w.getHourly().get(i).getTempF() + mContext.getString(R.string.fahrenheit);
        String temp = tempC + " / " + tempF;
        String preci = mContext.getString(R.string.precipitation) + " " + w.getHourly().get(i).getChanceofrain() + "%";
        String humi = mContext.getString(R.string.humidity) + " " + w.getHourly().get(i).getHumidity() + "%";
        String wind = mContext.getString(R.string.wind) + " " + w.getHourly().get(i).getWindspeedKmph() + " " + mContext.getString(R.string.kmh);
        holder.mTxtTempC.setText(temp);
        holder.mTxtPrec.setText(preci);
        holder.mTxtHumi.setText(humi);
        holder.mTxtWind.setText(wind);
        Picasso.with(mContext).load(w.getHourly().get(i).getWeatherIconUrl()).into(holder.mImgWeather);
    }

    private void initHeaderViews(ViewHolderHeader holder) {
        holder.mTxtStatus.setText(mCurrentCondition.get(0).getWeatherDesc());
        String tempC = mCurrentCondition.get(0).getTempCelsius() + mContext.getString(R.string.celsius);
        String tempF = mCurrentCondition.get(0).getTempFahrenheit() + mContext.getString(R.string.fahrenheit);
        String upadted = mContext.getString(R.string.updated_at) + " " + mCurrentCondition.get(0).getObservationTime();
        String temp = tempC + " / " + tempF;
        String preci = mContext.getString(R.string.precipitation) + " " + mCurrentCondition.get(0).getPrecipMM() + " mm";
        String humi = mContext.getString(R.string.humidity) + " " + mCurrentCondition.get(0).getHumidity() + "%";
        String wind = mContext.getString(R.string.wind) + " " + mCurrentCondition.get(0).getWindspeedKmph() + " " + mContext.getString(R.string.kmh);
        holder.mTxtTempC.setText(temp);
        holder.mTxtPrec.setText(preci);
        holder.mTxtHumi.setText(humi);
        holder.mTxtWind.setText(wind);
        holder.mTxtHour.setText(upadted);
        Picasso.with(mContext).load(mCurrentCondition.get(0).getWeatherIconUrl()).into(holder.mImgWeather);
    }

    private String getFormattedDate(String date) {
        if (date.contains("-") && date.split("-").length == 3)
            return mContext.getString(R.string.day) + "\n" + date.split("-")[2];
        return date;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }
}
