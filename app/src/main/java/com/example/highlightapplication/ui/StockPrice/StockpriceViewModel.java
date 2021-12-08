package com.example.highlightapplication.ui.StockPrice;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class StockpriceViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public StockpriceViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}