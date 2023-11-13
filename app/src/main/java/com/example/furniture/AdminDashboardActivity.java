package com.example.furniture;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
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

public class AdminDashboardActivity extends AppCompatActivity {

    private TextView textViewAdminDashboard, textViewUsers;
    private Spinner spinnerUserOptions;
    private ListView listViewUsers;
    private Button buttonViewDetails, buttonDeleteUser;

    private FirebaseDatabase database;
    private DatabaseReference usersRef;
    private List<String> userList;
    private ArrayAdapter<String> userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        // Initialize Firebase Database
        database = FirebaseDatabase.getInstance();
        usersRef = database.getReference("users"); // Replace "users" with the actual Firebase database reference

        // Initialize UI elements
        textViewAdminDashboard = findViewById(R.id.textViewAdminDashboard);
        textViewUsers = findViewById(R.id.textViewUsers);
        spinnerUserOptions = findViewById(R.id.spinnerUserOptions);
        listViewUsers = findViewById(R.id.listViewUsers);
        buttonViewDetails = findViewById(R.id.buttonViewDetails);
        buttonDeleteUser = findViewById(R.id.buttonDeleteUser);

        // Initialize user list and adapter
        userList = new ArrayList<>();
        userAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userList);
        listViewUsers.setAdapter(userAdapter);

        // Set up spinner with user options
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.user_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUserOptions.setAdapter(adapter);

        // Set item click listener for the user list
        listViewUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // Handle item click (e.g., show user details)
                String selectedUser = userList.get(position);
                Toast.makeText(AdminDashboardActivity.this, "Selected User: " + selectedUser, Toast.LENGTH_SHORT).show();
            }
        });

        // Set click listener for View Details button
        buttonViewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle View Details button click
                // You can implement the logic to view details for the selected user
            }
        });

        // Set click listener for Delete User button
        buttonDeleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle Delete User button click
                // You can implement the logic to delete the selected user
            }
        });

        // Fetch users from Firebase and update the list
        fetchUsersFromFirebase();
    }

    private void fetchUsersFromFirebase() {
        usersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userList.clear();
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    String userName = userSnapshot.child("name").getValue(String.class);
                    userList.add(userName);
                }
                userAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(AdminDashboardActivity.this, "Failed to fetch users: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
