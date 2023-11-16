package com.example.furniture;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.furniture.R;

public class ProductMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_main);

        // Set up your views and handle button clicks here
        TextView homeText = findViewById(R.id.homeText);

        // Buttons for different categories
        Button btnAllFurniture = findViewById(R.id.btnAllFurniture);
        Button btnTable = findViewById(R.id.btnTable);
        Button btnSofa = findViewById(R.id.btnSofa);
        Button btnChair = findViewById(R.id.btnChair);

        // "See All" buttons for different categories
        Button btnSeeAll = findViewById(R.id.btnSeeAll);
        Button btnSeeAllNewArrival = findViewById(R.id.btnSeeAllNewArrival);

        // Example: Handle "See All" button click for the Table category
        btnSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayAllItems("All Furniture");
            }
        });

        // Example: Handle "See All" button click for the New Arrival category
        btnSeeAllNewArrival.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayAllItems("New Arrival");
            }
        });

        // Example: Handle click for the "All Furniture" category button
        btnAllFurniture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayAllItems("All Furniture");
            }
        });

        // Example: Handle click for the "Table" category button
        btnTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayAllItems("Table");
            }
        });

        // Example: Handle click for the "Sofa" category button
        btnSofa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayAllItems("Sofa");
            }
        });

        // Example: Handle click for the "Chair" category button
        btnChair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayAllItems("Chair");
            }
        });

        // Example: Handle card click for the first card
        FrameLayout card1Container = findViewById(R.id.card1Container);
        card1Container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToItemDetails("Table", 1);
            }
        });

        // Example: Handle card click for the second card
        FrameLayout card2Container = findViewById(R.id.card2Container);
        card2Container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToItemDetails("Table", 2);
            }
        });

        // You can add similar click listeners for other cards as needed
    }

    // Example method to display all items for a specific category
    private void displayAllItems(String category) {
        // Replace this with your logic to load and display all items for the selected category
        // You might want to use a RecyclerView or another appropriate view for displaying the items
    }

    // Example method to navigate to item details
    private void navigateToItemDetails(String category, int itemId) {
        // Replace this with your logic to navigate to the details view of the selected item
        // You might want to pass the category and itemId as extras to the details activity
    }
}
