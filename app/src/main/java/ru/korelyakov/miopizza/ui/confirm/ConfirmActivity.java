package ru.korelyakov.miopizza.ui.confirm;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import androidx.appcompat.app.AppCompatActivity;
import ru.korelyakov.miopizza.R;
import ru.korelyakov.miopizza.product.Cart;
import ru.korelyakov.miopizza.product.OrderTools;
import ru.korelyakov.miopizza.ui.confirm.ConfirmAdapter;
import ru.korelyakov.miopizza.product.Product;

public class ConfirmActivity extends AppCompatActivity implements ConfirmAdapter.OnItemClickListener {

    RecyclerView recyclerView;

    private ConfirmAdapter productRecyclerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        recyclerView = findViewById(R.id.recyclerView);
    }

    @Override
    public void onResume() {
        super.onResume();
        onUpdateList();
    }

    @Override
    public void onItemClick(int position) {
        Product product = OrderTools.Cart.getItem(position);
        // todo возможно нужно открыть активити с инфо о товаре?
    }

    @Override
    public void onItemPlusClicked(int position) {
        Product product = OrderTools.Cart.getItem(position);
        product.setCount(product.getCount() + 1);
        onUpdateList();
    }

    @Override
    public void onItemMinusClicked(int position) {
        Product product = OrderTools.Cart.getItem(position);
        product.setCount(product.getCount() - 1);
        onUpdateList();
    }

    /**
     * Обновление списка при изменениях
     */
    public void onUpdateList() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        productRecyclerAdapter = new ConfirmAdapter(this, OrderTools.Cart.getItems());
        productRecyclerAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(productRecyclerAdapter);
    }

    public void onBuyClick(View view) {
        Toast.makeText(this, String.format("%s. Количество товаров: %s. Сумма: %s", getString(R.string.cart_success_message), OrderTools.Cart.getTotalCount(), OrderTools.Cart.getTotalPrice()), Toast.LENGTH_LONG).show();
        //OrderTools.Cart.cleanCart();

        // тут видимо нужно открыть финальное активити с адресом доставки?

        //finish();
    }
}
