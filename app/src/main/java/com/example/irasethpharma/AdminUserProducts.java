package com.example.irasethpharma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminUserProducts extends AppCompatActivity
{

    private RecyclerView userproductlist;
    RecyclerView.LayoutManager layoutManager;
    private DatabaseReference userproductsrefs;

    private String userid = "";




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_products);



        userid = getIntent().getStringExtra("uid");


        userproductlist = findViewById(R.id.carRecyclerViewAdminUserProducts);
        userproductlist.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        userproductlist.setLayoutManager(layoutManager);


        userproductsrefs = FirebaseDatabase.getInstance().getReference().child("Cart List").child("Admin View").child(userid).child("Products");






    }

    @Override
    protected void onStart()
    {
        super.onStart();


        FirebaseRecyclerOptions<Cartmodelfirebase> options = new FirebaseRecyclerOptions.Builder<Cartmodelfirebase>()
                .setQuery(userproductsrefs,Cartmodelfirebase.class)
                .build();



        FirebaseRecyclerAdapter<Cartmodelfirebase,Cartviewholder> adapter = new FirebaseRecyclerAdapter<Cartmodelfirebase, Cartviewholder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull Cartviewholder holder, int position, @NonNull Cartmodelfirebase model)
            {
                holder.txtuserproductname.setText(model.getProductname());
                holder.txtuserproductprice.setText(model.getProductprice());
                holder.txtuserproductquantity.setText(model.getProductquantity());
                holder.txtuserproductimage.setImageResource(Integer.parseInt(model.getProductimage().toString()));
            }

            @NonNull
            @Override
            public Cartviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
            {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.userproduct_list,parent,false);
                Cartviewholder cartviewholder = new Cartviewholder(view);

                return cartviewholder;
            }
        };


        userproductlist.setAdapter(adapter);
        adapter.startListening();


    }
}