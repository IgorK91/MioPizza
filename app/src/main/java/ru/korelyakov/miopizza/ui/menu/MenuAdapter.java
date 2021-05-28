package ru.korelyakov.miopizza.ui.menu;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import ru.korelyakov.miopizza.product.ProductType;

public class MenuAdapter extends ArrayAdapter<ProductType> {

    //private static final String[] mContacts = { "Пицца", "Супы", "Кальцоне", "Пасты", "Салаты", "Напитки", "Десерты" };

    private static final ProductType[] mContacts = ProductType.values();

    Context mContext;

    // Конструктор
    public MenuAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId, mContacts);
        // TODO Auto-generated constructor stub
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        TextView label = (TextView) convertView;

        if (convertView == null) {
            convertView = new TextView(mContext);
            label = (TextView) convertView;
        }
        label.setText(mContacts[position].toString());

        label.setGravity(Gravity.CENTER);
        label.setTextSize(30);
        label.setTextColor(Color.parseColor("#FFFFFFFF"));
        return (convertView);
    }

    // возвращает содержимое выделенного элемента списка
    public ProductType getItem(int position) {
        return mContacts[position];
    }

}