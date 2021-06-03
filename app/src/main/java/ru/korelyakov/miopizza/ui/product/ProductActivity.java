package ru.korelyakov.miopizza.ui.product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import ru.korelyakov.miopizza.R;
import ru.korelyakov.miopizza.product.OrderTools;
import ru.korelyakov.miopizza.product.Product;
import ru.korelyakov.miopizza.ui.order.OrderActivity;

public class ProductActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {
   // private MenuAdapter mAdapter;
   ImageView imageView;
   TextView name, structure, countMain4, coast, textCartItemCount;
   Integer count = 1;
   Button button1,button2;
   Integer itogOne, itogTwo;
   public  static Integer countPosition = 0;
   int position;
    Product product;
    private int currentApiVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        currentApiVersion = android.os.Build.VERSION.SDK_INT;
        final int flags =  View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION  | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        if (currentApiVersion >= Build.VERSION_CODES.KITKAT) {
            getWindow().getDecorView().setSystemUiVisibility(flags);
            final View decorView = getWindow().getDecorView();
            decorView
                    .setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
                        @Override
                        public void onSystemUiVisibilityChange(int visibility) {
                            if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                                decorView.setSystemUiVisibility(flags);
                            }
                        }
                    });
        }


        Bundle arguments = getIntent().getExtras();
        position = arguments.getInt("id");

        product = OrderTools.AllProducts.get(position);
        name = findViewById(R.id.textView2);
        // подставим имя из продукта
        name.setText(product.getName());
        structure = findViewById(R.id.textView3);
        // подставим состав из продукта
        structure.setText(product.getContent());

        imageView = findViewById(R.id.full_image_view);
        // подставим картинку из продукта
        imageView.setImageResource(product.getPicture());

        countMain4 = findViewById(R.id.countMain4);
        countMain4.setText(Integer.toString(count));
        coast = findViewById(R.id.coast);
        coast.setText("0");

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button1.setBackgroundColor(Color.RED);
        button2.setBackgroundColor(Color.WHITE);

        coast.setText(product.getNormalCoast().toString());

        }

    public void Normal (View view) {
        count = 1;
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button1.setBackgroundColor(Color.RED);
        button2.setBackgroundColor(Color.WHITE);
        countMain4.setText(Integer.toString(count));
        coast.setText(product.getNormalCoast().toString());
    }

    public void Big (View view) {
        count = 1;
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button2.setBackgroundColor(Color.RED);
        button1.setBackgroundColor(Color.WHITE);
        countMain4.setText(Integer.toString(count));
        coast.setText(product.getBigCoast().toString());
    }

    public void Put (View view) {
        countPosition++;
       // OrderTools.Cart.addToCart(MenuActivity.mAdapter.getItem(position));
        CharSequence zz = name.getText();
        OrderTools.Cart.addToCart7(zz.toString());
        CharSequence cc = coast.getText();
        CharSequence ss = countMain4.getText();
        OrderTools.Cart.addToCart2(Integer.valueOf(cc.toString()));
        OrderTools.Cart.addToCart3(Integer.valueOf(ss.toString()));
        OrderTools.Cart.addToCart6(countPosition);
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
            textCartItemCount.setText(OrderTools.Cart.getItems6().toString());
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
                Intent intent = new Intent(this, OrderActivity.class);
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

//gg