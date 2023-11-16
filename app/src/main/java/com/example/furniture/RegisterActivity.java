

package com.example.furniture;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity; // Add this import

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private EditText nameInput, emailInput, passwordInput;
    private Button signUpButton;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Find views by ID
        nameInput = findViewById(R.id.name_input_field);
        emailInput = findViewById(R.id.email_input_field);
        passwordInput = findViewById(R.id.password_input_field);
        signUpButton = findViewById(R.id.sign_up_button);

        // Set onClickListener for the Sign Up button
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Call the registerUser method when the button is clicked
                registerUser();
            }
        });
    }

    private void registerUser() {
        // Get user input
        String name = nameInput.getText().toString().trim();
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();

        // Check if any field is empty
        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Register user with Firebase Authentication
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Registration successful
                            Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                            // You can add additional logic here, such as navigating to another activity
                        } else {
                            // If registration fails, display a message to the user.
                            Toast.makeText(RegisterActivity.this, "Registration failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
