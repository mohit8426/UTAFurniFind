package com.example.furniture;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.furniture.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AdminDashboardActivity extends AppCompatActivity {

    private Spinner spinnerUserOptions;
    private ListView listViewUsers;
    private ArrayAdapter<String> usersAdapter;

    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        // Initialize Firebase Firestore
        firestore = FirebaseFirestore.getInstance();

        // Initialize UI elements
        spinnerUserOptions = findViewById(R.id.spinnerUserOptions);
        listViewUsers = findViewById(R.id.listViewUsers);

        // Set up the adapter for the users list
        List<String> usersList = new ArrayList<>();
        usersAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, usersList);
        listViewUsers.setAdapter(usersAdapter);

        // Fetch users from Firestore
        fetchUsersFromFirestore();

        // You can add more functionality related to the spinner and other UI elements as needed
    }


    private void fetchUsersFromFirestore() {
        // Reference to the "users" collection
        CollectionReference usersCollection = firestore.collection("users");

        // Fetch all documents in the "users" collection
        usersCollection.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<String> usersList = new ArrayList<>();

                    // Loop through the documents and add user names to the list
                    for (DocumentSnapshot document : task.getResult()) {
                        Log.e("document", document.toString());
                        String userName = document.getString("name").toLowerCase(Locale.ROOT);
                        usersList.add(userName);
                        Log.e("FirestoreError", "Error Users: ", task.getException());


                    }

                    // Update the UI with the fetched users
                    updateUsersList(usersList);
                } else {
                    Log.e("FirestoreError", "Error getting documents: ", task.getException());
                    Toast.makeText(AdminDashboardActivity.this, "Error fetching users", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void updateUsersList(List<String> usersList) {
        // Clear the existing list and add the new users
        usersAdapter.clear();
        usersAdapter.addAll(usersList);
        usersAdapter.notifyDataSetChanged();
    }


}
