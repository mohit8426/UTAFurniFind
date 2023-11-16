package com.example.furniture;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button loginButton;
    private Button adminLoginButton;
    private Button createAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize buttons
        loginButton = findViewById(R.id.LoginOption);
        adminLoginButton = findViewById(R.id.adminLoginOption);
        createAccountButton = findViewById(R.id.createAccountOption);

        // Set click listeners
        loginButton.setOnClickListener(this);
        adminLoginButton.setOnClickListener(this);
        createAccountButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;

        if (view.getId() == R.id.LoginOption) {
            intent = new Intent(this, LoginActivity.class);
        } else if (view.getId() == R.id.adminLoginOption) {
            intent = new Intent(this, AdminLoginActivity.class);
        } else if (view.getId() == R.id.createAccountOption) {
            intent = new Intent(this, RegisterActivity.class);
        }

        if (intent != null) {
            startActivity(intent);
        }
    }
}
