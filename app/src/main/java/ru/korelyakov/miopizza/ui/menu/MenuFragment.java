package ru.korelyakov.miopizza.ui.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ru.korelyakov.miopizza.R;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;

public class MenuFragment extends Fragment implements
        AdapterView.OnItemSelectedListener{
    TextView test;
    private MenuAdapter mAdapter;


    public MenuFragment(){
        super(R.layout.fragment_menu);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        final GridView g = (GridView) view.findViewById(R.id.gridView1);
        mAdapter = new MenuAdapter(getContext(),
                android.R.layout.simple_list_item_1);
        g.setAdapter(mAdapter);



        g.setOnItemSelectedListener(this);
        g.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                // TODO Auto-generated method stub

                    Intent intent = new Intent(getContext(), MenuActivity.class);
                    intent.putExtra("key", mAdapter.getItem(position));
                    startActivity(intent);

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position,
                               long id) {
     //   mSelectText.setText("Выбранный элемент: " + mAdapter.getItem(position));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
      //  mSelectText.setText("Выбранный элемент: ничего");
    }
}