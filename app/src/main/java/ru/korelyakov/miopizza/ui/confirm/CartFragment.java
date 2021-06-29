package ru.korelyakov.miopizza.ui.confirm;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import androidx.appcompat.app.AppCompatActivity;
import ru.korelyakov.miopizza.R;
import ru.korelyakov.miopizza.ui.confirm.ConfirmAdapter;
import ru.korelyakov.miopizza.product.Product;

public class CartFragment extends AppCompatActivity implements ConfirmAdapter.OnItemClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private ConfirmAdapter productRecyclerAdapter;

    @Override
    public int getViewId() {
        return R.layout.activity_confirm;
    }

    @Override
    public void onViewCreated(View view) {
        onUpdateList();
    }

  //  @Override
  //  public void onItemClick(CartItemsEntityModel cartItemsEntityModel) {
        // open details of product
  //  }

  //  @Override
  //  public void onItemPlusClicked(int position, CartItemsEntityModel cartItemsEntityModel) {
  //      int quantity = cartItemsEntityModel.getQuantity();
  //      CartItemsEntityModel cartModel = new CartItemsEntityModel();
   //     cartModel.setProduct(cartItemsEntityModel.getProduct());
  //      quantity++;
  //      cartModel.setQuantity(quantity);
 //       productRecyclerAdapter.updateItem(position, cartModel);
 //   }

 //   @Override
 //   public void onItemMinusClicked(int position, CartItemsEntityModel cartItemsEntityModel) {
 //       int quantity = cartItemsEntityModel.getQuantity();
 //       CartItemsEntityModel cartModel = new CartItemsEntityModel();
 //       cartModel.setProduct(cartItemsEntityModel.getProduct());
 //       quantity--;
 //       cartModel.setQuantity(quantity);
 //       productRecyclerAdapter.updateItem(position, cartModel);
//    }

    @Override
    public void onItemClick(Product cartItemsEntityModel) {

    }

    @Override
    public void onItemPlusClicked(int position, Product cartItemsEntityModel) {
        int quantity = cartItemsEntityModel.getNormalCoast();
       // Product cartModel = new Product();
      //  cartModel.setName(cartItemsEntityModel.getName());
        quantity++;
     //   cartModel.setNormalCoast(quantity);
     //   productRecyclerAdapter.updateItem(position, cartModel);
    }

    @Override
    public void onItemMinusClicked(int position, Product cartItemsEntityModel) {
        int quantity = cartItemsEntityModel.getNormalCoast();
       // Product cartModel = new Product();
     //   cartModel.setName(cartItemsEntityModel.getName());
        quantity--;
    //    cartModel.setNormalCoast(quantity);
     //   productRecyclerAdapter.updateItem(position, cartModel);
    }

    @Override
    public void onUpdateList() {
     //   productRecyclerAdapter = new ConfirmAdapter(context, CartHelper.getCartItems());
        productRecyclerAdapter.setOnItemClickListener(this);
     //   recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(productRecyclerAdapter);
    }

    @OnClick(R.id.buyButton)
    public void onBuyClick() {
      //  Toast.makeText(context, String.format(getString(R.string.cart_success_message), CartHelper.getCart().getTotalQuantity(), CartHelper.getCart().getTotalPrice()), Toast.LENGTH_LONG).show();
      //  CartHelper.getCart().clear();
      //  getActivity().finish();
    }
}
