package com.example.meteoprueba;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ForecastAdapter extends
        RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder> {
    private List<ForecastItem> forecastItems;

    public ForecastAdapter(List<ForecastItem> forecastItems) {
        this.forecastItems = forecastItems;
    }

    @NonNull
    @Override
    public ForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_forecast, parent, false);
        return new ForecastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastViewHolder holder, int position) {
        ForecastItem forecastItem = forecastItems.get(position);
        holder.dateTextView.setText(forecastItem.getDate());
        holder.weatherTextView.setText(forecastItem.getWeather());
    }

    @Override
    public int getItemCount() {
        return forecastItems.size();
    }

    public static class ForecastViewHolder extends RecyclerView.ViewHolder {
        TextView dateTextView;
        TextView weatherTextView;

        public ForecastViewHolder(@NonNull View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.txt_fecha);
            weatherTextView = itemView.findViewById(R.id.item_weather);
        }
    }
}

