package com.example.irasethpharma;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SupplierDetails extends AppCompatActivity {

    ArrayList<SupplierModel> supplierItem;
    RecyclerView supRecycler;
    SupplierAdapter supplierAdapter;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_details);






        //email = findViewById(R.id.imageView36);





        supplierItem = new ArrayList<>();
        supRecycler = findViewById(R.id.supplierRecyclerView);
        for (int i = 0; i < SupplierData.company.length; i++)
        {
            supplierItem.add(new SupplierModel(SupplierData.number[i], SupplierData.company[i], SupplierData.contact[i], SupplierData.email[i], SupplierData.address[i]));
        }

        RecyclerView.LayoutManager layoutManager =  new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        supRecycler.setLayoutManager(layoutManager);
        supplierAdapter =  new SupplierAdapter(this, supplierItem);
        supRecycler.setAdapter(supplierAdapter);


    }


    public void gotoadmin(View view)
    {
        Intent intent = new Intent(SupplierDetails.this,AdminActivity.class);
        startActivity(intent);
    }


}