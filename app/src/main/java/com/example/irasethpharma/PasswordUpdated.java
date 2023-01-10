package com.example.irasethpharma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PasswordUpdated extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_updated);
    }

    public void gotologin1(View view)
    {

        Intent intent = new Intent(PasswordUpdated.this, login.class);
        startActivity(intent);
    }

}
