package com.example.highlightapplication.ui.Weather;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.highlightapplication.GlobalCity;
import com.example.highlightapplication.MainActivity;
import com.example.highlightapplication.R;

import java.util.ArrayList;


public class WeatherFragment extends Fragment implements SearchView.OnQueryTextListener,SearchView.OnCloseListener,
        NetworkingService.NetworkingListener , WeatherAdapter.CityclickListner {
    String TAG="WeatherFragment";

    Context appContext;
    WeatherAdapter adapter;
    ArrayList<GlobalCity> cities = new ArrayList<>();
    NetworkingService networkingService;
    JsonService jsonService;
    RecyclerView recyclerView;
    SearchView search_view;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        // Replace 'android.R.id.list' with the 'id' of your RecyclerView
        appContext=getActivity();
        search_view = view.findViewById(R.id.weather_searchview);
        search_view.setOnQueryTextListener(this);
        search_view.setQueryHint("Search City for Weather");


        networkingService = new NetworkingService(this);
        jsonService=new JsonService();

        adapter = new WeatherAdapter(appContext,cities,this);
        recyclerView = view.findViewById(R.id.recyclerview);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
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
           networkingService.fetchCitiesData(newText);
        }
        else {
            cities = new ArrayList<>(0);
            adapter.notifyDataSetChanged();
        }
        return false;
    }



    @Override
    public void APINetworkListner(String jsonString) {
        Log.d(TAG, jsonString + "= Data");// not parsed yet.
        cities =  jsonService.parseCitiesAPIJson(jsonString);
        adapter.cityList = cities;
        adapter.notifyDataSetChanged();
    }

    @Override
    public void cityClicked(GlobalCity selectedCity) {

        MainActivity.myBundle.putString("SelectedCity", String.valueOf(selectedCity.getCityName()));

        //Click methid implement here
    }

    @Override
    public boolean onClose() {
        cities = new ArrayList<>(0);
        adapter.cityList=cities;
        adapter.notifyDataSetChanged();
        return true;
    }
}