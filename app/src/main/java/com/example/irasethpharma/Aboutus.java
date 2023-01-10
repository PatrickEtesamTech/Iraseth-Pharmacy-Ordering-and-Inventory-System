package com.example.irasethpharma;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Adapter;

public class Aboutus extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);


        ViewPager viewP = findViewById(R.id.viewPager);
        AboutusAdapter adapter = new AboutusAdapter(this);
        viewP.setAdapter(adapter);
    }
}