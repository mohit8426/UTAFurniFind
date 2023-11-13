package com.example.furniture;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class AdminLoginActivity extends AppCompatActivity {

    private EditText emailInput, passwordInput;
    private Button loginButton;

    private FirebaseAuth mAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Find views by ID
        emailInput = findViewById(R.id.admin_email_input_field);
        passwordInput = findViewById(R.id.admin_password_input_field);
        loginButton = findViewById(R.id.admin_login);

        // Set onClickListener for the Login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Call the loginUser method when the button is clicked
                loginUser();
            }
        });
    }

    private void loginUser() {
        // Get user input
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();

        // Check if any field is empty
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(AdminLoginActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Implement your admin login logic here, such as checking if the email and password are valid for admin access.
        // You may use Firebase Authentication or any other authentication mechanism.

        // For demonstration purposes, showing a toast message indicating successful login
        Toast.makeText(AdminLoginActivity.this, "Admin Login Successful", Toast.LENGTH_SHORT).show();

        // Redirect to AdminDashboardActivity
        Intent intent = new Intent(AdminLoginActivity.this, AdminDashboardActivity.class);
        startActivity(intent);
        finish(); // This prevents the user from coming back to the login activity by pressing the back button
    }
}
