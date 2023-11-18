package com.example.furniture;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.furniture.model.CartItem;
import java.util.List;

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder> {

    private List<CartItem> cartItems;

    public ShoppingCartAdapter(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_cart_manage, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartItem cartItem = cartItems.get(position);

        // Bind data to the views
        holder.itemNameTextView.setText(cartItem.getItemName());
        holder.quantityTextView.setText("Quantity: " + cartItem.getQuantity());
        holder.priceTextView.setText("Price: $" + cartItem.getPrice());
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemNameTextView;
        TextView quantityTextView;
        TextView priceTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
         //   itemNameTextView = itemView.findViewById(R.id.itemNameTextView);
          //  quantityTextView = itemView.findViewById(R.id.quantityTextView);
        //    priceTextView = itemView.findViewById(R.id.priceTextView);
        }
    }
}
