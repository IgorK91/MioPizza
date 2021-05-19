package ru.korelyakov.miopizza.ui.contacts;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SlideshowViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SlideshowViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Мы находимся по адресу: м.Парнас, улица Фёдора Абрамова, дом 8 корпус 1");
    }

    public LiveData<String> getText() {
        return mText;
    }
}