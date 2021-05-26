package ru.korelyakov.miopizza.ui.exit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import ru.korelyakov.miopizza.MainActivity;
import ru.korelyakov.miopizza.MainActivity5;
import ru.korelyakov.miopizza.R;
import ru.korelyakov.miopizza.ui.contacts.SlideshowViewModel;

public class ExitFragment extends Fragment {


    public ExitFragment(){
        super(R.layout.fragment_exit);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Intent intent = new Intent(getContext(), MainActivity5.class);
        intent.putExtra("clearData", true);
        startActivity(intent);
    }
}
