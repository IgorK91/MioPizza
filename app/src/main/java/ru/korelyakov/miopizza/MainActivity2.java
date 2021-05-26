package ru.korelyakov.miopizza;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.content.Intent;
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

import ru.korelyakov.miopizza.product.OrderTools;
import ru.korelyakov.miopizza.product.Product;
import ru.korelyakov.miopizza.product.ProductType;
import ru.korelyakov.miopizza.ui.allmenu.UniversalAdapter;

import static ru.korelyakov.miopizza.product.OrderTools.AllProducts;

public class MainActivity2 extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {

    private TextView mSelectText, textCartItemCount1;
    public static UniversalAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


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

        /*mAdapter = new UniversalAdapter(getApplicationContext(),
                android.R.layout.simple_list_item_1, array);*/
        mAdapter = new UniversalAdapter(getApplicationContext(), products);
        g.setAdapter(mAdapter);
        g.setOnItemSelectedListener(this);
        g.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
              //  OrderTools.Corzina.addToCart(mAdapter.getItem(position));
                if(productType.equals(ProductType.Pizza)){
                    Intent i = new Intent(getApplicationContext(),
                            MainActivity4.class);
                    i.putExtra("id",position);
                    startActivity(i);
                }
                else if(productType.equals(ProductType.Soup)) {
                    Intent i = new Intent(getApplicationContext(),
                            MainActivity4.class);
                    i.putExtra("id",position+33);
                    startActivity(i);
                }
                else if(productType.equals(ProductType.Calzone)) {
                    Intent i = new Intent(getApplicationContext(),
                            MainActivity4.class);
                    i.putExtra("id",position+14);
                    startActivity(i);
                }
                else if(productType.equals(ProductType.Salad)) {
                    Intent i = new Intent(getApplicationContext(),
                            MainActivity4.class);
                    i.putExtra("id",position+30);
                    startActivity(i);
                }
                else if(productType.equals(ProductType.Dessert)) {
                    Intent i = new Intent(getApplicationContext(),
                            MainActivity4.class);
                    i.putExtra("id",position+18);
                    startActivity(i);
                }
                else if(productType.equals(ProductType.Drink)) {
                    Intent i = new Intent(getApplicationContext(),
                            MainActivity4.class);
                    i.putExtra("id",position+22);
                    startActivity(i);
                }
                else if(productType.equals(ProductType.Pasta)) {
                    Intent i = new Intent(getApplicationContext(),
                            MainActivity4.class);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        final MenuItem menuItem = menu.findItem(R.id.cart);
        View actionView = MenuItemCompat.getActionView(menuItem);
        textCartItemCount1 = actionView.findViewById(R.id.cart_badge);
        try{
            textCartItemCount1.setText(OrderTools.Corzina.getItems6().toString());
        }
        catch (Exception e){

        }
        setupBadge();
        actionView.setOnClickListener(v -> onOptionsItemSelected(menuItem));
        return true;
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

}
//gg