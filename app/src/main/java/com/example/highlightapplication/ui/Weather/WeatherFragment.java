package com.example.highlightapplication.ui.Weather;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.highlightapplication.GlobalCity;
import com.example.highlightapplication.R;
import com.example.highlightapplication.databinding.FragmentWeatherBinding;

import java.util.ArrayList;


public class WeatherFragment extends Fragment implements SearchView.OnQueryTextListener, NetworkingService.NetworkingListener {
    String TAG="WeatherFragment";

    private WeatherViewModel weatherViewModel;
    private FragmentWeatherBinding binding;
    WeatherAdapter adapter;
    ArrayList<GlobalCity> cities = new ArrayList<>();
    NetworkingService networkingService;
    JsonService jsonService;
    RecyclerView recyclerView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        final SearchView search_view = binding.weatherSearchview;

        search_view.setOnQueryTextListener(this);
        search_view.setQueryHint("Search City for Weather");


        View view = inflater.inflate(R.layout.weather_recyclreview, container, false);

        // Replace 'android.R.id.list' with the 'id' of your RecyclerView
        recyclerView = (RecyclerView) view.findViewById(android.R.id.list);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity());
        Log.d("debugMode", "The application stopped after this");
        recyclerView.setLayoutManager(mLayoutManager);

        adapter = new WeatherAdapter(cities);
        recyclerView.setAdapter(adapter);
        return view;

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Log.d("query", query);//

        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.d("query change", newText);
        if (newText.length() >= 3) {
            // seach for city
          // networkingService.fetchCitiesData(newText);
        }
        else {
            cities = new ArrayList<>(0);
           // adapter.notifyDataSetChanged();
        }
        return false;
    }



    @Override

    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void APINetworkListner(String jsonString) {
        Log.d("tag", jsonString);// not parsed yet.
        cities =  jsonService.parseCitiesAPIJson(jsonString);
        Log.e(TAG,"List size="+cities.size());
        adapter.notifyDataSetChanged();
    }
}