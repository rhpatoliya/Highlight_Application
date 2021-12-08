package com.example.highlightapplication.ui.Weather;

import com.example.highlightapplication.GlobalCity;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class JsonService {
    public ArrayList<GlobalCity> parseCitiesAPIJson(String jsonCities) {
        ArrayList<GlobalCity> allCitiesFromAPI = new ArrayList<>(0);
        try {//
            JSONArray jsonArray = new JSONArray(jsonCities);
            for (int i = 0 ; i< jsonArray.length(); i++){
//                String cityName = jsonArray.getString(i);
//                City newCity = new City(jsonArray.getString(i));
                allCitiesFromAPI.add(new GlobalCity(jsonArray.getString(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return allCitiesFromAPI;
    }
}