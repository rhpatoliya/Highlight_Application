package com.example.highlightapplication.ui.Weather;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.highlightapplication.MainActivity;
import com.example.highlightapplication.R;

import java.awt.font.TextAttribute;

public class WeatherDetails implements NetworkingService.NetworkingListener{

    TextView city;
    NetworkingService networkingService;
    JsonService jsonService;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_weather, container, false);

        String  cityName = (String) MainActivity.myBundle.get("SelectedCity");


        return view;

    }

    @Override
    public void APINetworkListner(String jsonString) {

    }
}
