package com.example.furniture;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.furniture.model.CartItem; // Import your CartItem model class if you have one
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartActivity extends AppCompatActivity {

    private List<CartItem> cartItems;
    private ShoppingCartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_manage);

        // Initialize the cart items (you may replace this with your actual data)
        cartItems = generateSampleCartItems();

        // Set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.cartItemsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ShoppingCartAdapter(cartItems);
        recyclerView.setAdapter(adapter);

        // Update the subtotal text view with the total number of items
        updateSubtotal();

        // Set up the "Proceed to Checkout" button click listener
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button proceedToCheckoutButton = findViewById(R.id.proceedToCheckoutButton);
        proceedToCheckoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Implement the logic to proceed to checkout
            }
        });
    }

    // Method to update the subtotal text view with the total number of items
    private void updateSubtotal() {
        int totalItems = calculateTotalItems();
        TextView subtotalTextView = findViewById(R.id.subtotalTextView);
        subtotalTextView.setText("Total No. Items = " + totalItems);
    }

    // Method to calculate the total number of items in the cart
    private int calculateTotalItems() {
        int totalItems = 0;
        for (CartItem item : cartItems) {
            totalItems += item.getQuantity();
        }
        return totalItems;
    }

    // Method to generate sample cart items (replace with your actual data)
    private List<CartItem> generateSampleCartItems() {
        List<CartItem> sampleCartItems = new ArrayList<>();
        sampleCartItems.add(new CartItem("Item 1", 2, 20.0));
        sampleCartItems.add(new CartItem("Item 2", 1, 15.0));
        // Add more items as needed
        return sampleCartItems;
    }
}
