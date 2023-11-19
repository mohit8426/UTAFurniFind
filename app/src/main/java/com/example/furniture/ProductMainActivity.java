package com.example.furniture;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.furniture.ShoppingCartActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import com.squareup.picasso.Picasso;

import java.util.Objects;

public class ProductMainActivity extends AppCompatActivity {

    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_main);

        firestore = FirebaseFirestore.getInstance();

        // Set up your views and handle button clicks here
        TextView homeText = findViewById(R.id.homeText);

        // Buttons for different categories
        Button btnAllFurniture = findViewById(R.id.btnAllFurniture);
        Button btnTable = findViewById(R.id.btnTable);
        Button btnSofa = findViewById(R.id.btnSofa);
        Button btnChair = findViewById(R.id.btnChair);

        // "See All" buttons for different categories

        // Assuming you have assigned an id to your ImageView in the XML layout file
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView imageView = findViewById(R.id.yourImageViewId);

        // Set an OnClickListener to the ImageView
        imageView.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             // Define the intent to start the PersonActivity
                                             Intent intent = new Intent(ProductMainActivity.this, PersonActivity.class);

                                             // Start the PersonActivity
                                             startActivity(intent);
                                         }
                                     });
        // Example: Handle "See All" button click for the Table category
//        btnSeeAll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                displayAllItems("All Furniture");
//            }
//        });

        // Example: Handle "See All" button click for the New Arrival category


        // Example: Handle click for the "All Furniture" category button
        btnAllFurniture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchAndDisplayData("table");
                fetchAndDisplayData1("sofa");
                fetchAndDisplayData2("chair");
            }
        });

        // Example: Handle click for the "Table" category button
        btnTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchAndDisplayData("table");
            }
//            @Override
//            public void onClick(View view) {
//                displayProduct("table_name","table_desc","table_img");
//            }

        });

        // Example: Handle click for the "Sofa" category button
        btnSofa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchAndDisplayData1("sofa");
            }
        });

        // Example: Handle click for the "Chair" category button
        btnChair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchAndDisplayData2("chair");
            }
        });

        // Example: Handle card click for the first card
        FrameLayout card1Container = findViewById(R.id.card1Container);
        card1Container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToCart("Table", 1);
            }
        });

        // Example: Handle card click for the second card
        FrameLayout card2Container = findViewById(R.id.card2Container);
        card2Container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToCart("Table", 2);
            }
        });

        // You can add similar click listeners for other cards as needed

        // Example: Handle click for the shopping cart icon
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView cartImageView = findViewById(R.id.cartImageView);
        cartImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openShoppingCart();
            }
        });
    }

    private void displayAllItems1(String newArrival) {
    }

    // Example method to display all items for a specific category
    private void displayAllItems(String collectionName) {
        // Replace this with your logic to load and display all items for the selected category
        // You might want to use a RecyclerView or another appropriate view for displaying the items
        firestore.collection(collectionName)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                // Here, you can access the data for each document
                                // For example, assuming you have a field named "name"
                                String tableName = document.getString("table_name");
                                String tableDesc = document.getString("table_desc");
                                String chairName = document.getString("chair_name");
                                String chairDesc = document.getString("chair_desc");
                                String itemName = document.getString("sofa_name");
                                String itemDesc = document.getString("sofa_desc");
                                // Add your logic to display or process the retrieved data
                                Log.d("Firestore", "Sofa Name: " + itemName);
                                Log.d("Firestore", "Sofa Description: " + itemDesc);
                                Log.d("Firestore", "Table Name: " + tableName);
                                Log.d("Firestore", "Table Description: " + tableDesc);
                                Log.d("Firestore", "chair Name: " + chairName);
                                Log.d("Firestore", "chair Description: " + chairDesc);

                            }

                        } else {
                            Log.e("Firestore", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    // Example method to add an item to the shopping cart
    private void addToCart(String category, int itemId) {
        // Replace this with your logic to add the selected item to the shopping cart
        // You might want to manage the shopping cart state using a database or a data structure
    }

    // Example method to open the shopping cart activity
    private void openShoppingCart() {
        // Replace this with your logic to open the shopping cart activity
        Intent intent = new Intent(ProductMainActivity.this, ShoppingCartActivity.class);
        startActivity(intent);
    }

    private void fetchAndDisplayData(String collectionName) {
        firestore.collection(collectionName)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                // Here, you can access the data for each document
                                // For example, assuming you have a field named "name"
                                String itemName = document.getString("table_name");
                                String itemDesc = document.getString("table_desc");
                                String imageUrl = document.getString("table_img");
                                // Add your logic to display or process the retrieved data
                                Log.d("Firestore", "Item Name: " + itemName);
                                Log.d("Firestore", "Item Description: " + itemDesc);
                                Log.d("Firestore", "Image Url: " + imageUrl);



                                displayProduct(itemName, itemDesc, imageUrl);
                            }

                        } else {
                            Log.e("Firestore", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }


    private void fetchAndDisplayData1(String collectionName) {
        firestore.collection(collectionName)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                // Here, you can access the data for each document
                                // For example, assuming you have a field named "name"
                                String itemName = document.getString("sofa_name");
                                String itemDesc = document.getString("sofa_desc");
                                String imageUrl = document.getString("sofa_img");

                                // Add your logic to display or process the retrieved data
                                Log.d("Firestore", "Item Name: " + itemName);
                                Log.d("Firestore", "Item Description: " + itemDesc);
                                Log.d("Firestore", "Image Url: " + imageUrl);

                                    displayProduct(itemName, itemDesc, imageUrl);


                            }


                        } else {
                            Log.e("Firestore", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }


    private void fetchAndDisplayData2(String collectionName) {
        firestore.collection(collectionName)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                // Here, you can access the data for each document
                                // For example, assuming you have a field named "name"
                                String itemName = document.getString("chair_name");
                                String itemDesc = document.getString("chair_desc");
                                String imageUrl = document.getString("chair_img");
                                // Add your logic to display or process the retrieved data
                                Log.d("Firestore", "Item Name: " + itemName);
                                Log.d("Firestore", "Item Description: " + itemDesc);
                                Log.d("Firestore", "Image Url: " + imageUrl);
                                displayProduct(itemName, itemDesc, imageUrl);


                            }

                        } else {
                            Log.e("Firestore", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }
    private void clearProductShowcase() {
        // Clear previous data in the product showcase
        // You need to implement this based on your layout
        // For example, if you are using TextViews and ImageView, reset their values
     //   TextView productNameTextView = findViewById(R.id.productNameTextView);
        TextView productDescTextView = findViewById(R.id.productDescTextView);
        ImageView productImageView = findViewById(R.id.productImageView);

        //    productNameTextView.setText("");
        productDescTextView.setText("");
        productImageView.setImageDrawable(null); // Clear the image
    }

    private void displayProduct(String itemName, String itemDesc, String imageUrl) {
        // Display the fetched data in the product showcase
        // You need to implement this based on your layout and views




        Log.d("Firestore", "Outside If " );

        if (Objects.equals(itemName, "table1")) {
            Log.d("Firestore", "Inside If ");

            TextView productDescTextView = findViewById(R.id.productDescTextView);
            productDescTextView.setText(itemDesc);

            ImageView productImageView = findViewById(R.id.productImageView);

            int imageResource = getResources().getIdentifier("table1", "drawable", getPackageName());
            Drawable drawable = ContextCompat.getDrawable(this, imageResource);

            if (drawable != null) {
                productImageView.setImageDrawable(drawable);
                productImageView.setAdjustViewBounds(true);
                productImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }
        }

        if(Objects.equals(itemName, "table2")){
            Log.d("Firestore", "Inside If " );

            TextView productDescTextView = findViewById(R.id.productDescTextView1);
            productDescTextView.setText(itemDesc);
            ImageView productImageView = findViewById(R.id.productImageView1);

            int imageResource = getResources().getIdentifier("table2", "drawable", getPackageName());
            Drawable drawable = ContextCompat.getDrawable(this, imageResource);

            if (drawable != null) {
                productImageView.setImageDrawable(drawable);
                productImageView.setAdjustViewBounds(true);
                productImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }
        }
        if(Objects.equals(itemName, "table3")){
            Log.d("Firestore", "Inside If " );

            TextView productDescTextView = findViewById(R.id.productDescTextView2);
            productDescTextView.setText(itemDesc);
            ImageView productImageView = findViewById(R.id.productImageView2);

            int imageResource = getResources().getIdentifier("table3", "drawable", getPackageName());
            Drawable drawable = ContextCompat.getDrawable(this, imageResource);

            if (drawable != null) {
                productImageView.setImageDrawable(drawable);
                productImageView.setAdjustViewBounds(true);
                productImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }
        }
        if(Objects.equals(itemName, "table4")){
            Log.d("Firestore", "Inside If " );

            TextView productDescTextView = findViewById(R.id.productDescTextView3);
            productDescTextView.setText(itemDesc);
            ImageView productImageView = findViewById(R.id.productImageView3);

            int imageResource = getResources().getIdentifier("table4", "drawable", getPackageName());
            Drawable drawable = ContextCompat.getDrawable(this, imageResource);

            if (drawable != null) {
                productImageView.setImageDrawable(drawable);
                productImageView.setAdjustViewBounds(true);
                productImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }
        }
        if(Objects.equals(itemName, "table5")){
            Log.d("Firestore", "Inside If " );

            TextView productDescTextView = findViewById(R.id.productDescTextView4);
            productDescTextView.setText(itemDesc);
            ImageView productImageView = findViewById(R.id.productImageView4);

            int imageResource = getResources().getIdentifier("table5", "drawable", getPackageName());
            Drawable drawable = ContextCompat.getDrawable(this, imageResource);

            if (drawable != null) {
                productImageView.setImageDrawable(drawable);
                productImageView.setAdjustViewBounds(true);
                productImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }
        }
        if(Objects.equals(itemName, "table6")){
            Log.d("Firestore", "Inside If " );

            TextView productDescTextView = findViewById(R.id.productDescTextView5);
            productDescTextView.setText(itemDesc);
            ImageView productImageView = findViewById(R.id.productImageView5);

            int imageResource = getResources().getIdentifier("table6", "drawable", getPackageName());
            Drawable drawable = ContextCompat.getDrawable(this, imageResource);

            if (drawable != null) {
                productImageView.setImageDrawable(drawable);
                productImageView.setAdjustViewBounds(true);
                productImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }
        }
        if(Objects.equals(itemName, "sofa1")){
            Log.d("Firestore", "Inside If " );

            TextView productDescTextView = findViewById(R.id.productDescTextView);
            productDescTextView.setText(itemDesc);
            ImageView productImageView = findViewById(R.id.productImageView);

            int imageResource = getResources().getIdentifier("sofa1", "drawable", getPackageName());
            Drawable drawable = ContextCompat.getDrawable(this, imageResource);

            if (drawable != null) {
                productImageView.setImageDrawable(drawable);
                productImageView.setAdjustViewBounds(true);
                productImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }
        }


        if(Objects.equals(itemName, "sofa2")){
            Log.d("Firestore", "Inside If " );

            TextView productDescTextView = findViewById(R.id.productDescTextView1);
            productDescTextView.setText(itemDesc);
            ImageView productImageView = findViewById(R.id.productImageView1);

            int imageResource = getResources().getIdentifier("sofa2", "drawable", getPackageName());
            Drawable drawable = ContextCompat.getDrawable(this, imageResource);

            if (drawable != null) {
                productImageView.setImageDrawable(drawable);
                productImageView.setAdjustViewBounds(true);
                productImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }
        }
        if(Objects.equals(itemName, "sofa3")){
            Log.d("Firestore", "Inside If " );

            TextView productDescTextView = findViewById(R.id.productDescTextView2);
            productDescTextView.setText(itemDesc);
            ImageView productImageView = findViewById(R.id.productImageView2);

            int imageResource = getResources().getIdentifier("sofa3", "drawable", getPackageName());
            Drawable drawable = ContextCompat.getDrawable(this, imageResource);

            if (drawable != null) {
                productImageView.setImageDrawable(drawable);
                productImageView.setAdjustViewBounds(true);
                productImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }
        }
        if(Objects.equals(itemName, "sofa4")){
            Log.d("Firestore", "Inside If " );

            TextView productDescTextView = findViewById(R.id.productDescTextView3);
            productDescTextView.setText(itemDesc);
            ImageView productImageView = findViewById(R.id.productImageView3);

            int imageResource = getResources().getIdentifier("sofa4", "drawable", getPackageName());
            Drawable drawable = ContextCompat.getDrawable(this, imageResource);

            if (drawable != null) {
                productImageView.setImageDrawable(drawable);
                productImageView.setAdjustViewBounds(true);
                productImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }
        }
        if(Objects.equals(itemName, "sofa5")){
            Log.d("Firestore", "Inside If " );

            TextView productDescTextView = findViewById(R.id.productDescTextView4);
            productDescTextView.setText(itemDesc);
            ImageView productImageView = findViewById(R.id.productImageView4);

            int imageResource = getResources().getIdentifier("sofa5", "drawable", getPackageName());
            Drawable drawable = ContextCompat.getDrawable(this, imageResource);

            if (drawable != null) {
                productImageView.setImageDrawable(drawable);
                productImageView.setAdjustViewBounds(true);
                productImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }
        }
        if(Objects.equals(itemName, "sofa6")){
            Log.d("Firestore", "Inside If " );

            TextView productDescTextView = findViewById(R.id.productDescTextView5);
            productDescTextView.setText(itemDesc);
            ImageView productImageView = findViewById(R.id.productImageView5);

            int imageResource = getResources().getIdentifier("sofa6", "drawable", getPackageName());
            Drawable drawable = ContextCompat.getDrawable(this, imageResource);

            if (drawable != null) {
                productImageView.setImageDrawable(drawable);
                productImageView.setAdjustViewBounds(true);
                productImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }
        }
        if(Objects.equals(itemName, "chair1")){
            Log.d("Firestore", "Inside If " );

            TextView productDescTextView = findViewById(R.id.productDescTextView);
            productDescTextView.setText(itemDesc);
            ImageView productImageView = findViewById(R.id.productImageView);

            int imageResource = getResources().getIdentifier("chair1", "drawable", getPackageName());
            Drawable drawable = ContextCompat.getDrawable(this, imageResource);

            if (drawable != null) {
                productImageView.setImageDrawable(drawable);
                productImageView.setAdjustViewBounds(true);
                productImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }
        }
        if(Objects.equals(itemName, "chair2")){
            Log.d("Firestore", "Inside If " );

            TextView productDescTextView = findViewById(R.id.productDescTextView1);
            productDescTextView.setText(itemDesc);
            ImageView productImageView = findViewById(R.id.productImageView1);

            int imageResource = getResources().getIdentifier("chair2", "drawable", getPackageName());
            Drawable drawable = ContextCompat.getDrawable(this, imageResource);

            if (drawable != null) {
                productImageView.setImageDrawable(drawable);
                productImageView.setAdjustViewBounds(true);
                productImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }
        }
        if(Objects.equals(itemName, "chair3")){
            Log.d("Firestore", "Inside If " );

            TextView productDescTextView = findViewById(R.id.productDescTextView2);
            productDescTextView.setText(itemDesc);

            ImageView productImageView = findViewById(R.id.productImageView2);

            int imageResource = getResources().getIdentifier("chair3", "drawable", getPackageName());
            Drawable drawable = ContextCompat.getDrawable(this, imageResource);

            if (drawable != null) {
                productImageView.setImageDrawable(drawable);
                productImageView.setAdjustViewBounds(true);
                productImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }
        }
        if(Objects.equals(itemName, "chair4")){
            Log.d("Firestore", "Inside If " );

            TextView productDescTextView = findViewById(R.id.productDescTextView3);
            productDescTextView.setText(itemDesc);
            ImageView productImageView = findViewById(R.id.productImageView3);

            int imageResource = getResources().getIdentifier("chair4", "drawable", getPackageName());
            Drawable drawable = ContextCompat.getDrawable(this, imageResource);

            if (drawable != null) {
                productImageView.setImageDrawable(drawable);
                productImageView.setAdjustViewBounds(true);
                productImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }
        }
        if(Objects.equals(itemName, "chair_5")){
            Log.d("Firestore", "Inside If " );

            TextView productDescTextView = findViewById(R.id.productDescTextView4);
            productDescTextView.setText(itemDesc);
            ImageView productImageView = findViewById(R.id.productImageView4);

            int imageResource = getResources().getIdentifier("chair5", "drawable", getPackageName());
            Drawable drawable = ContextCompat.getDrawable(this, imageResource);

            if (drawable != null) {
                productImageView.setImageDrawable(drawable);
                productImageView.setAdjustViewBounds(true);
                productImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }
        }
        if(Objects.equals(itemName, "chair_6")){
            Log.d("Firestore", "Inside If " );

            TextView productDescTextView = findViewById(R.id.productDescTextView5);
            productDescTextView.setText(itemDesc);
            ImageView productImageView = findViewById(R.id.productImageView5);

            int imageResource = getResources().getIdentifier("chair6", "drawable", getPackageName());
            Drawable drawable = ContextCompat.getDrawable(this, imageResource);

            if (drawable != null) {
                productImageView.setImageDrawable(drawable);
                productImageView.setAdjustViewBounds(true);
                productImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }
        }



    }
}



