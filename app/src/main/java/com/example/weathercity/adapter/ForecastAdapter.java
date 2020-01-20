package com.example.weathercity.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weathercity.R;
import com.example.weathercity.model.ModelForecast;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ViewHolder> {

    private List<ModelForecast.List> forecasts = new ArrayList<>();

    @NonNull
    @Override
    public ForecastAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listForecasts = layoutInflater.inflate(R.layout.activity_city_forecast, parent, false);
        return new ViewHolder(listForecasts);
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastAdapter.ViewHolder holder, int position) {
        final ModelForecast.List modelForecast = forecasts.get(position);

        holder.fillView(modelForecast);
    }

    public void addWeatherForecastModel(ModelForecast model) {
        for (ModelForecast.List list : model.getList()) {
            forecasts.add(list);
            notifyItemInserted(forecasts.size() - 1);
        }
    }

    @Override
    public int getItemCount() {
        return forecasts.size() - 1;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView forecastIcon;
        TextView date;
        TextView minTemp;
        TextView maxTemp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.forecastIcon = itemView.findViewById(R.id.icon);
            this.date = itemView.findViewById(R.id.date);
            this.minTemp = itemView.findViewById(R.id.minTemp);
            this.maxTemp = itemView.findViewById(R.id.maxTemp);
        }

        public void fillView(ModelForecast.List modelForecast) {
            Picasso.get().load(new StringBuilder("https://openweathermap.org/img/w/").append(modelForecast.getWeather().get(0).getIcon()).append(".png").toString()).into(forecastIcon);
            date.setText(getDay(modelForecast.getDtTxt()) + ", " + getTime(modelForecast.getDtTxt()));
            minTemp.setText(modelForecast.getMain().getTempMin() + "°C Min");
            maxTemp.setText(modelForecast.getMain().getTempMax() + "°C Max");
        }

        private String getDay(String dateTime) {
            String date = dateTime.split(" ")[0];
            Date date1 = null;
            try {
                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return date1.toString().substring(0, 10);
        }

        private String getTime(String dateTime) {
            String time = dateTime.split(" ")[1];
            return time.substring(0, 5);
        }
    }
}
