package com.example.furniture;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.furniture.R;

public class PersonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile); // Replace 'your_layout' with the actual layout file name

        // Find the "Log Out" TextView by its ID
        TextView logOutTextView = findViewById(R.id.textViewLogOut);

        // Set an OnClickListener to handle the click event
        logOutTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call the logout method when the "Log Out" TextView is clicked
                logout();
            }
        });
    }

    // Method to handle the logout logic
    private void logout() {
        // Add your logout logic here, such as clearing user session, navigating to the login screen, etc.
        // For example, you can finish the current activity and start a new LoginActivity
        finish(); // Close the current activity

        // Start LoginActivity or any other appropriate activity
         Intent intent = new Intent(PersonActivity.this, MainActivity.class);
         startActivity(intent);
    }
}
