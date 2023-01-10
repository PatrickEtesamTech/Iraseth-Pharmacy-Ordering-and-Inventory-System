package com.example.irasethpharma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AccountVerified extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_verified);
    }

    public void gotologin1(View view)
    {
        Intent intent = new Intent(AccountVerified.this, login.class);
        startActivity(intent);
    }
}