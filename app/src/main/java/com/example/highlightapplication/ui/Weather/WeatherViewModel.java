package com.example.highlightapplication.ui.Weather;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.highlightapplication.GlobalCity;

import java.util.ArrayList;

public class WeatherViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<ArrayList<GlobalCity>> weatherAdapterModel;

    public WeatherViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");

    }

    public LiveData<String> getText() {
        return mText;
    }

    public void WeatherAdapterModel() {
        weatherAdapterModel = new MutableLiveData<>();



    }
    public LiveData<ArrayList<GlobalCity>> getAdapter() {
        return weatherAdapterModel;
    }
}