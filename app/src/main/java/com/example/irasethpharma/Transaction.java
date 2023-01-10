package com.example.irasethpharma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import Prevalent.Prevalent;

public class Transaction extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference userinformationref , usernameref;
    TextView name , company , date , address , total , total2;
    //OrderListAdapter orderListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
       // ordeRrecyclerView = findViewById(R.id.orderRecyclerView);

        name = findViewById(R.id.textView72);
       // company = findViewById(R.id.textView2);
       // date = findViewById(R.id.textView19);
        address = findViewById(R.id.useraddresstransaction);
        total = findViewById(R.id.textView75);
        total2= findViewById(R.id.textView78);


        recyclerView =findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


        userinformation();
        username();

       // RecyclerView.LayoutManager lt = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //ordeRrecyclerView.setLayoutManager(lt);
       // orderListAdapter =  new OrderListAdapter();
        //ordeRrecyclerView.setAdapter(orderListAdapter);
    }







    @Override
    protected void onStart()
    {
        super.onStart();

        final DatabaseReference cartlistref = FirebaseDatabase.getInstance().getReference().child("Cart List");

        FirebaseRecyclerOptions<Cartmodelfirebase> options = new FirebaseRecyclerOptions
                .Builder<Cartmodelfirebase>().setQuery(cartlistref.child("User View")
                .child(Prevalent.currentonlineuser.getEmailaddress()).child("Products"),Cartmodelfirebase.class).build();


        FirebaseRecyclerAdapter<Cartmodelfirebase,Cartviewholder> adapter = new FirebaseRecyclerAdapter<Cartmodelfirebase, Cartviewholder>(options)
        {


            @Override
            protected void onBindViewHolder(@NonNull Cartviewholder holder, int position, @NonNull Cartmodelfirebase model)
            {

                holder.txtproductname1.setText(model.getProductname());
                holder.txtproductprice2.setText(currencyFormat(model.getProductprice()));
                holder.txtproductquantity3.setText(model.getProductquantity());
                holder.productimage1.setImageResource(Integer.parseInt(model.getProductimage().toString()));
                // Picasso.get().load(model.getProductimage()).into(holder.productimage);

            }

            @NonNull
            @Override
            public Cartviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
            {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_list,parent,false);
                Cartviewholder cartviewholder = new Cartviewholder(view);

                return cartviewholder;
            }

        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();





    }


    public void userinformation ()
    {

        userinformationref = FirebaseDatabase.getInstance().getReference().child("Orders");

        userinformationref.child(Prevalent.currentonlineuser.getEmailaddress()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                Map<String,Object> map = (Map<String, Object>) snapshot.getValue();



              //  String companyname = (String) map.get("Company name");
                String maintotalprice = (String)map.get("Totalprice");
                String addresss = (String)map.get("City");
               // String deliverydate = (String)map.get("Date for Delivery");
                String baranggay = (String)map.get("Baranggay");
                String houseno= (String)map.get("Houseno");
                String Street= (String)map.get("Street");

               // company.setText(companyname);
                total.setText(currencyFormat(String.valueOf(maintotalprice)));
                total2.setText(currencyFormat(String.valueOf(maintotalprice)));
                address.setText(houseno+" "+Street+" Street "+baranggay+" "+addresss);
               // date.setText(deliverydate);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {


            }
        });



    }

    public void username()
    {
        usernameref = FirebaseDatabase.getInstance().getReference().child("users");

        usernameref.child(Prevalent.currentonlineuser.getEmailaddress()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                Map<String,Object> map = (Map<String, Object>) snapshot.getValue();



                String fname = (String) map.get("firstname");
                String lname = (String) map.get("lastname");

                name.setText(fname+" "+lname);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {


            }
        });

    }




    public static String currencyFormat(String amount)
    {
        DecimalFormat formatter = new DecimalFormat("###,###,##0.00");
        return formatter.format(Double.parseDouble(amount));
    }


    public void gotoreceipt (View view)
    {
        startActivity(new Intent(Transaction.this,Payment.class));
    }
}