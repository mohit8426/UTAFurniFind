package com.example.furniture;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProductMainActivity extends AppCompatActivity {

    private LinearLayout allFurnitureContainer, tableContainer, sofaContainer, chairContainer;
    private TextView homeText;
    private ListView listViewProducts;

    private FirebaseDatabase database;
    private DatabaseReference productsRef;

    private List<String> productList;
    private ArrayAdapter<String> productAdapter;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_main);

        // Initialize Firebase Database
        database = FirebaseDatabase.getInstance();
        productsRef = database.getReference("products"); // Replace "products" with the actual Firebase database reference

        // Initialize UI elements
        homeText = findViewById(R.id.homeText);
        allFurnitureContainer = findViewById(R.id.allFurnitureContainer);
        tableContainer = findViewById(R.id.tableContainer);
        sofaContainer = findViewById(R.id.sofaContainer);
        chairContainer = findViewById(R.id.chairContainer);
        listViewProducts = findViewById(R.id.listViewProducts);

        // Initialize product list and adapter
        productList = new ArrayList<>();
        productAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, productList);
        listViewProducts.setAdapter(productAdapter);

        // Set click listeners for category containers
        allFurnitureContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchProductsByCategory("All Furniture");
            }
        });

        tableContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchProductsByCategory("Table");
            }
        });

        sofaContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchProductsByCategory("Sofa");
            }
        });

        chairContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchProductsByCategory("Chair");
            }
        });

        // Set item click listener for the product list
        listViewProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String selectedProduct = productList.get(position);
                Toast.makeText(ProductMainActivity.this, "Selected Product: " + selectedProduct, Toast.LENGTH_SHORT).show();
                // Implement logic to navigate to the detailed product view or perform other actions
            }
        });

        // Fetch all products initially
        fetchProductsByCategory("All Furniture");
    }

    private void fetchProductsByCategory(final String category) {
        productsRef.child(category).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                productList.clear();
                for (DataSnapshot productSnapshot : dataSnapshot.getChildren()) {
                    String productName = productSnapshot.child("name").getValue(String.class);
                    productList.add(productName);
                }
                productAdapter.notifyDataSetChanged();
                homeText.setText(category); // Update the category title
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ProductMainActivity.this, "Failed to fetch products: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
