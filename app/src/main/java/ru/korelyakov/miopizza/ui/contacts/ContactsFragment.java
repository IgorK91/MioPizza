package ru.korelyakov.miopizza.ui.contacts;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import ru.korelyakov.miopizza.R;

public class ContactsFragment extends Fragment {

    TextView textView, textView2;
    private ContactsViewModel contactsViewModel;

    public ContactsFragment(){
        super(R.layout.fragment_contacts);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        contactsViewModel =
                new ViewModelProvider(this).get(ContactsViewModel.class);

        textView = view.findViewById(R.id.text_slideshow);
        textView2 = view.findViewById(R.id.text_slideshow2);

        contactsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        contactsViewModel.getText2().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView2.setText(s);
            }
        });
    }
}
//g