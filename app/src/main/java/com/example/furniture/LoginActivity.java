package com.example.furniture;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    private EditText emailInput, passwordInput;
    private Button loginButton;

    private Button googleAuth;

    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private GoogleSignInClient mGoogleSignInClient;
    ImageView imageView;

    int RC_SIGN_IN = 20;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Find views by ID
        emailInput = findViewById(R.id.email_input_field);
        passwordInput = findViewById(R.id.password_input_field);
        loginButton = findViewById(R.id.login_button);

        // Set onClickListener for the Login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Call the loginUser method when the button is clicked
                loginUser();
            }
        });

        //for googleAuth
        database = FirebaseDatabase.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("984732632242-hhhpepfrr8u3hrlb91u3vai39uhhodup.apps.googleusercontent.com")
                .requestEmail().build();

        mGoogleSignInClient = GoogleSignIn.getClient(this,gso);

        imageView = findViewById(R.id.googleLogin);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                googleSignIn();

            }
        });

    }

    private void googleSignIn() {
        Intent intent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(intent,RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try{
                GoogleSignInAccount account = task.getResult(ApiException.class);
                fireBaseAuth(account.getIdToken());
            }
            catch(Exception e){
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void fireBaseAuth(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user = mAuth.getCurrentUser();

                            HashMap<String, Object> map = new HashMap<>();
                            map.put("ID",user.getUid());
                            map.put("Name",user.getDisplayName());
                            map.put("Profile",user.getPhotoUrl().toString());

                            database.getReference().child("users").child(user.getUid()).setValue(map);

                            Intent intent = new Intent(LoginActivity.this, ProductMainActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(LoginActivity.this, "Something went Wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void loginUser() {
        // Get user input
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();

        // Check if any field is empty
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Log in user with Firebase Authentication
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Login successful
                            Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();

                            // Redirect to ProductMainActivity
                            Intent intent = new Intent(LoginActivity.this, ProductMainActivity.class);
                            startActivity(intent);
                            finish(); // This prevents the user from coming back to the login activity by pressing the back button
                        } else {
                            // If login fails, display a message to the user.
                            Toast.makeText(LoginActivity.this, "Login failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
