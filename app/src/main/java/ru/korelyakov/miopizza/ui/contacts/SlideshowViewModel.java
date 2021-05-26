package ru.korelyakov.miopizza.ui.contacts;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SlideshowViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<String> mText2;

    public SlideshowViewModel() {
        mText = new MutableLiveData<>();
        mText2 = new MutableLiveData<>();
        mText.setValue("Мы находимся по адресу: м.Парнас, улица Фёдора Абрамова, дом 8, корпус 1");
        mText2.setValue("Наш телефон: 960-21-23");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<String> getText2() {
        return mText2;
    }

}