package ru.korelyakov.miopizza.ui.exit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ru.korelyakov.miopizza.ui.login.LoginActivity;
import ru.korelyakov.miopizza.R;

public class ExitFragment extends Fragment {


    public ExitFragment(){
        super(R.layout.fragment_exit);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Intent intent = new Intent(getContext(), LoginActivity.class);
        intent.putExtra("clearData", true);
        startActivity(intent);
    }
}
