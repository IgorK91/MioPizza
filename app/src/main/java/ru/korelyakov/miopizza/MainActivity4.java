package ru.korelyakov.miopizza;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ru.korelyakov.miopizza.product.OrderTools;
import ru.korelyakov.miopizza.product.Product;
import ru.korelyakov.miopizza.product.ProductType;
import ru.korelyakov.miopizza.ui.allmenu.ImageAdapter;
import ru.korelyakov.miopizza.ui.allmenu.UniversalAdapter;

public class MainActivity4 extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {
   // private UniversalAdapter mAdapter;
   TextView name, structure, countMain4, coast, textCartItemCount;
   Integer count = 1;
   Button button1,button2;
   Integer itogOne, itogTwo;
   public  static Integer countPosition = 0;
   int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

     //   CharSequence zz = textCartItemCount.getText();
     //   countPosition = Integer.valueOf(zz.toString());

        Bundle arguments = getIntent().getExtras();
        position = arguments.getInt("id");

         //   Intent intent = getIntent();
        //    int position = intent.getExtras().getInt("id");

        Product product = OrderTools.AllProducts.get(position);
        name = findViewById(R.id.textView2);
        // подставим имя из продукта
        name.setText(product.getName());
        structure = findViewById(R.id.textView3);
        // подставим состав из продукта
        structure.setText(product.getContent());

        countMain4 = findViewById(R.id.countMain4);
        countMain4.setText(Integer.toString(count));
        coast = findViewById(R.id.coast);
        coast.setText("0");

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button1.setBackgroundColor(Color.RED);
        button2.setBackgroundColor(Color.GREEN);
        coast.setText("500");

        }

    public void Normal (View view) {
        count = 1;
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button1.setBackgroundColor(Color.RED);
        button2.setBackgroundColor(Color.GREEN);
        countMain4.setText(Integer.toString(count));
        coast.setText("500");
    }

    public void Big (View view) {
        count = 1;
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button2.setBackgroundColor(Color.RED);
        button1.setBackgroundColor(Color.GREEN);
        countMain4.setText(Integer.toString(count));
        coast.setText("790");
    }

    public void Put (View view) {
        countPosition++;
        OrderTools.Corzina.addToCart(MainActivity2.mAdapter.getItem(position));
        CharSequence cc = coast.getText();
        CharSequence ss = countMain4.getText();
        OrderTools.Corzina.addToCart2(Integer.valueOf(cc.toString()));
        OrderTools.Corzina.addToCart3(Integer.valueOf(ss.toString()));
        OrderTools.Corzina.addToCart6(countPosition);
        textCartItemCount.setText(String.valueOf(countPosition));
        onBackPressed();
    }

    public void Plus (View view) {
        countMain4 = findViewById(R.id.countMain4);
        count++;
        countMain4.setText(Integer.toString(count));
        CharSequence zz = coast.getText();
        itogOne = Integer.valueOf(zz.toString());
        if (itogOne % 790 == 1) {
            itogTwo = itogOne + 790;
        }
        else
        {
            itogTwo = itogOne + 500;
        }
        coast.setText(Integer.toString(itogTwo));
    }

    public void Minus (View view) {
        countMain4 = findViewById(R.id.countMain4);
        count--;
        countMain4.setText(Integer.toString(count));
        CharSequence zz = coast.getText();
        itogOne = Integer.valueOf(zz.toString());
        if (itogOne % 790 == 1) {
            itogTwo = itogOne - 790;
        }
        else
        {
            itogTwo = itogOne - 500;
        }
        coast.setText(Integer.toString(itogTwo));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        final MenuItem menuItem = menu.findItem(R.id.cart);
        View actionView = MenuItemCompat.getActionView(menuItem);
        textCartItemCount = actionView.findViewById(R.id.cart_badge);
        try{
            textCartItemCount.setText(OrderTools.Corzina.getItems6().toString());
        }
        catch (Exception e){

        }
        setupBadge();
        actionView.setOnClickListener(v -> onOptionsItemSelected(menuItem));
        return true;
    }

    private void setupBadge() {

    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cart:
                Intent intent = new Intent(this, MainActivity3.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onBackPressed() {
            finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

