package com.example.irasethpharma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Prevalent.Prevalent;

public class Customerdetails extends AppCompatActivity
{

    RecyclerView customerdetailsrc;
    DatabaseReference databaseReference;
    CustomerDetailsAdapter myadapter;
    ArrayList<UserHelperClass>list;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customerdetails);


        customerdetailsrc = findViewById(R.id.customerdetailsrc);
        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        customerdetailsrc.setHasFixedSize(true);

        customerdetailsrc.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();

        myadapter = new CustomerDetailsAdapter(this,list);

        customerdetailsrc.setAdapter(myadapter);


        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    UserHelperClass user = dataSnapshot.getValue(UserHelperClass.class);

                    list.add(user);
                }

                myadapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        });






    }

    public void gotoadmin1(View view)
    {
        Intent intent = new Intent(Customerdetails.this,AdminActivity.class);
        startActivity(intent);
    }


}