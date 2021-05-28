package ru.korelyakov.miopizza.ui.allmenu;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ru.korelyakov.miopizza.product.Product;

public class UniversalAdapter extends BaseAdapter {

    private final List<Product> mContacts;

    Context mContext;

    // Конструктор
    public UniversalAdapter(Context context, List<Product> mContacts) {
        super();
        this.mContacts = mContacts;
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
        label.setText(mContacts.get(position).toString());
        label.setGravity(Gravity.CENTER);
        label.setTextSize(30);
        label.setTextColor(Color.parseColor("#FFFFFFFF"));
        return (convertView);
    }

    @Override
    public int getCount() {
        return mContacts.size();
    }

    // возвращает содержимое выделенного элемента списка
    @Override
    public Product getItem(int position) {
        return mContacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
