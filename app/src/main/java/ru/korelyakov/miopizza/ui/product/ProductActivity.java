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
import ru.korelyakov.miopizza.product.ProductType;
import ru.korelyakov.miopizza.ui.order.OrderActivity;

public class ProductActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {
   // private MenuAdapter mAdapter;
   ImageView imageView;
   TextView name, structure, countMain4, coast, textCartItemCount;
   private int count = 1;
   Button buttonNormal, buttonBig;
   private int itog;
   private int position;
   private Product product;
   private int currentApiVersion;
   // для понимания большой товар или маленький
   private boolean isBig = false;

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
        countMain4.setText(String.format("%s", count));
        coast = findViewById(R.id.coast);
        // подставим цену из продукта
        coast.setText(String.format("%s", product.getNormalCoast()));

        buttonNormal = findViewById(R.id.buttonNormal);
        buttonBig = findViewById(R.id.buttonBig);
        buttonNormal.setBackgroundColor(Color.RED);
        buttonBig.setBackgroundColor(Color.WHITE);
        // если не пицца, нельзя менять размер
        if (product.getProductType() != ProductType.Pizza) {
            buttonNormal.setVisibility(View.GONE);
            buttonBig.setVisibility(View.GONE);
        }
    }

    public void Normal (View view) {
        count = 1;
        buttonNormal.setBackgroundColor(Color.RED);
        buttonBig.setBackgroundColor(Color.WHITE);
        countMain4.setText(String.format("%s", count));
        coast.setText(String.format("%s", product.getNormalCoast()));
        isBig = false;
    }

    public void Big (View view) {
        count = 1;
        buttonBig.setBackgroundColor(Color.RED);
        buttonNormal.setBackgroundColor(Color.WHITE);
        countMain4.setText(String.format("%s", count));
        coast.setText(String.format("%s", product.getBigCoast()));
        isBig = true;
    }

    public void Put (View view) {
       // OrderTools.Cart.addToCart(MenuActivity.mAdapter.getItem(position));
        OrderTools.Cart.addToCartNameList(name.getText().toString());
        OrderTools.Cart.addToCoastList(Integer.parseInt(coast.getText().toString()));
        OrderTools.Cart.addToCountList(Integer.parseInt(countMain4.getText().toString()));
        OrderTools.Cart.addPositions(count);
        textCartItemCount.setText(String.format("%s", OrderTools.Cart.getCountPosition()));
        onBackPressed();
    }

    public void Plus (View view) {
        count++;
        countMain4.setText(String.format("%s", count));
        itog = Integer.parseInt(coast.getText().toString());
        itog += isBig ? product.getBigCoast() : product.getNormalCoast();
        coast.setText(String.format("%s", itog));
    }

    public void Minus (View view) {
        if (count > 1) {
            count--;
            countMain4.setText(String.format("%s", count));
            itog = Integer.parseInt(coast.getText().toString());
            itog -= isBig ? product.getBigCoast() : product.getNormalCoast();
            coast.setText(String.format("%s", itog));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        final MenuItem menuItem = menu.findItem(R.id.cart);
        View actionView = MenuItemCompat.getActionView(menuItem);
        textCartItemCount = actionView.findViewById(R.id.cart_badge);
        textCartItemCount.setText(String.format("%s", OrderTools.Cart.getCountPosition()));
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