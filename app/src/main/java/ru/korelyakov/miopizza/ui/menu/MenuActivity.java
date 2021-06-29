package ru.korelyakov.miopizza.ui.menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ru.korelyakov.miopizza.ui.confirm.CartFragment;
import ru.korelyakov.miopizza.ui.order.OrderActivity;
import ru.korelyakov.miopizza.ui.product.ProductActivity;
import ru.korelyakov.miopizza.R;
import ru.korelyakov.miopizza.product.OrderTools;
import ru.korelyakov.miopizza.product.Product;
import ru.korelyakov.miopizza.product.ProductType;
import ru.korelyakov.miopizza.ui.allmenu.MenuAdapter;

import static ru.korelyakov.miopizza.product.OrderTools.AllProducts;

public class MenuActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {

    private TextView mSelectText, textCartItemCount1;
    public static MenuAdapter mAdapter;
    private int currentApiVersion;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

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
        ProductType productType = (ProductType)arguments.get("key");

        mSelectText =  findViewById(R.id.info2);
        final GridView g =  findViewById(R.id.gridView2);

        List<Product> products = new ArrayList<>();

        switch (productType) {
            case Pizza:
                products = AllProducts.stream().filter(pr -> pr.getProductType() == ProductType.Pizza).collect(Collectors.toList());
                break;
            case Soup:
                products = AllProducts.stream().filter(pr -> pr.getProductType() == ProductType.Soup).collect(Collectors.toList());
                break;
            case Calzone:
                products = AllProducts.stream().filter(pr -> pr.getProductType() == ProductType.Calzone).collect(Collectors.toList());
                break;
            case Salad:
                products = AllProducts.stream().filter(pr -> pr.getProductType() == ProductType.Salad).collect(Collectors.toList());
                break;
            case Dessert:
                products = AllProducts.stream().filter(pr -> pr.getProductType() == ProductType.Dessert).collect(Collectors.toList());
                break;
            case Drink:
                products = AllProducts.stream().filter(pr -> pr.getProductType() == ProductType.Drink).collect(Collectors.toList());
                break;
            case Pasta:
                products = AllProducts.stream().filter(pr -> pr.getProductType() == ProductType.Pasta).collect(Collectors.toList());
                break;
            default:
                break;
        }

        /*mAdapter = new MenuAdapter(getApplicationContext(),
                android.R.layout.simple_list_item_1, array);*/
        mAdapter = new MenuAdapter(getApplicationContext(), products);
        g.setAdapter(mAdapter);
        g.setOnItemSelectedListener(this);
        g.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
              //  OrderTools.Cart.addToCart(mAdapter.getItem(position));
                if(productType.equals(ProductType.Pizza)){
                    Intent i = new Intent(getApplicationContext(),
                            ProductActivity.class);
                    i.putExtra("id",position);
                    startActivity(i);
                }
                else if(productType.equals(ProductType.Soup)) {
                    Intent i = new Intent(getApplicationContext(),
                            ProductActivity.class);
                    i.putExtra("id",position+33);
                    startActivity(i);
                }
                else if(productType.equals(ProductType.Calzone)) {
                    Intent i = new Intent(getApplicationContext(),
                            ProductActivity.class);
                    i.putExtra("id",position+14);
                    startActivity(i);
                }
                else if(productType.equals(ProductType.Salad)) {
                    Intent i = new Intent(getApplicationContext(),
                            ProductActivity.class);
                    i.putExtra("id",position+30);
                    startActivity(i);
                }
                else if(productType.equals(ProductType.Dessert)) {
                    Intent i = new Intent(getApplicationContext(),
                            ProductActivity.class);
                    i.putExtra("id",position+18);
                    startActivity(i);
                }
                else if(productType.equals(ProductType.Drink)) {
                    Intent i = new Intent(getApplicationContext(),
                            ProductActivity.class);
                    i.putExtra("id",position+22);
                    startActivity(i);
                }
                else if(productType.equals(ProductType.Pasta)) {
                    Intent i = new Intent(getApplicationContext(),
                            ProductActivity.class);
                    i.putExtra("id",position+26);
                    startActivity(i);
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position,
                               long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
      //  mSelectText.setText("Выбранный элемент: ничего");
    }


    private void setupBadge() {
    }

    @Override
    public void onResume() {
        super.onResume();
        if (textCartItemCount1 != null)
            textCartItemCount1.setText(String.format("%s", OrderTools.Cart.getCountPosition()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        final MenuItem menuItem = menu.findItem(R.id.cart);
        View actionView = MenuItemCompat.getActionView(menuItem);
        textCartItemCount1 = actionView.findViewById(R.id.cart_badge);
        textCartItemCount1.setText(String.format("%s", OrderTools.Cart.getCountPosition()));
        setupBadge();
        actionView.setOnClickListener(v -> onOptionsItemSelected(menuItem));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cart:
                //  Intent intent = new Intent(this, OrderActivity.class);
                Intent intent = new Intent(this, CartFragment.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
//g