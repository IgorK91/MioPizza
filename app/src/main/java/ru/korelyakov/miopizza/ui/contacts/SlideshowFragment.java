package ru.korelyakov.miopizza.ui.contacts;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieSyncManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import ru.korelyakov.miopizza.R;

public class SlideshowFragment extends Fragment {

    TextView textView;
    private SlideshowViewModel slideshowViewModel;

    public SlideshowFragment(){
        super(R.layout.fragment_contacts);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        textView = view.findViewById(R.id.text_slideshow);

        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
    }
}