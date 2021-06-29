package ru.korelyakov.miopizza.ui.confirm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.korelyakov.miopizza.R;
import ru.korelyakov.miopizza.product.Product;

public class ConfirmAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<Product> productEntityModel;
    private OnItemClickListener onItemClickListener;
    private Context context;

    public ConfirmAdapter(Context context, List<Product> productEntityModel) {
        this.context = context;
        this.productEntityModel = productEntityModel;
    }

    public void updateItem(int position, Product cartItemsEntityModel) {

    //    if(cartItemsEntityModel.getQuantity() > 0) {
            productEntityModel.set(position, cartItemsEntityModel);
 //           CartHelper.getCart().update(cartItemsEntityModel.getProduct(), cartItemsEntityModel.getQuantity());
   //     Product.getName().
        }
      //  else {
//            CartHelper.getCart().remove(productEntityModel.get(position).getProduct());
//            onItemClickListener.onUpdateList();
//        }
//        notifyDataSetChanged();
  //  }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_confirm, parent, false);
        return new ReceiveViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        ReceiveViewHolder viewHolder = (ReceiveViewHolder) holder;
      //  viewHolder.name.setText(productEntityModel.get(position).getProduct().getName());
      //  viewHolder.description.setText(productEntityModel.get(position).getProduct().getDescription());
     //   viewHolder.price.setText(String.format(context.getString(R.string.dollars_format), productEntityModel.get(position).getProduct().getPrice()));
      //  viewHolder.quantity.setText(String.valueOf(productEntityModel.get(position).getQuantity()));
      //  Picasso.with(context).load(productEntityModel.get(position).getProduct().getImage()).into(viewHolder.image);
    }

    @Override
    public int getItemCount() {
        return productEntityModel.size();
    }

    public class ReceiveViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.description)
        TextView description;
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.quantity)
        TextView quantity;
        @BindView(R.id.plus)
        Button plus;
        @BindView(R.id.minus)
        Button minus;

        public ReceiveViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(view -> {
                onItemClickListener.onItemClick(productEntityModel.get(getAdapterPosition()));
            });
                 minus.setOnClickListener(view -> {
                     onItemClickListener.onItemMinusClicked(getAdapterPosition(), productEntityModel.get(getAdapterPosition()));
                 });
            plus.setOnClickListener(view -> {
                onItemClickListener.onItemPlusClicked(getAdapterPosition(), productEntityModel.get(getAdapterPosition()));
            });
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        int getViewId();

        void onViewCreated(View view);

        void onItemClick(Product cartItemsEntityModel);
        void onItemPlusClicked(int position, Product cartItemsEntityModel);
        void onItemMinusClicked(int position, Product cartItemsEntityModel);
        void onUpdateList();
    }
}
