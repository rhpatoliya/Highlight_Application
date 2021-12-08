package com.example.highlightapplication.ui.Weather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.highlightapplication.GlobalCity;
import com.example.highlightapplication.R;

import java.util.ArrayList;
import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {
  /*  public WeatherAdapter(ArrayList<GlobalCity> cityList) {
    }
*/
    interface cityClickListner {
        public void cityClicked(GlobalCity selectedCity);
    }
    private Context mCtx;
    public ArrayList<GlobalCity> cityList;
    cityClickListner listner;
    public WeatherAdapter(ArrayList<GlobalCity> cityList) {
        this.mCtx = mCtx;
        this.cityList = cityList;
       listner = (cityClickListner)mCtx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.weather_recyclreview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GlobalCity t = cityList.get(position);
        holder.cityTextView.setText(t.getCityName() );
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView cityTextView, countryTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            cityTextView = itemView.findViewById(R.id.city);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            GlobalCity city = cityList.get(getAdapterPosition());
            //listner.cityClicked(city);
        }
    }
}
