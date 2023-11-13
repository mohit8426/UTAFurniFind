package com.example.furniture;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final int LOGIN_OPTION_ID = R.id.LoginOption;
    private static final int ADMIN_LOGIN_OPTION_ID = R.id.adminLoginOption;
    private static final int CREATE_ACCOUNT_OPTION_ID = R.id.createAccountOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onButtonClick(View view) {
        Intent intent = null;

        if (view.getId() == LOGIN_OPTION_ID) {
            intent = new Intent(this, LoginActivity.class);
        } else if (view.getId() == ADMIN_LOGIN_OPTION_ID) {
            intent = new Intent(this, AdminLoginActivity.class);
        } else if (view.getId() == CREATE_ACCOUNT_OPTION_ID) {
            intent = new Intent(this, RegisterActivity.class);
        }

        if (intent != null) {
            startActivity(intent);
        }
    }
}
