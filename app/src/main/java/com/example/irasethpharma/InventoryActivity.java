package com.example.irasethpharma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import Prevalent.Prevalent;

public class InventoryActivity extends AppCompatActivity
{


    ImageView inventBackBtn ;
    RecyclerView recyclerViewstock;
    FirebaseRecyclerAdapter adapter;
    String inventorydata;
    String stockdata;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);





        recyclerViewstock = findViewById(R.id.inventRecycler2);
        recyclerViewstock.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewstock.setHasFixedSize(true);
        inventBackBtn = findViewById(R.id.inventoryBack);
      //  instock = findViewById(R.id.imgInStock);
      //  outstock = findViewById(R.id.imgOutStock);



       // categorynamesdata();

/*
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle("Add Product");

        EditText stockdatanew = new EditText(context);
        stockdatanew.setHint("Add Stock");
        builder.setView(stockdatanew);



        builder.setPositiveButton("Enter Stock", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {

                // holder.stock.setText(stockdatanew.getText().toString());


                //inventorydata = holder.inventName.getText().toString();



                DatabaseReference inventoryref = FirebaseDatabase.getInstance().getReference().child("Inventory");

                HashMap<String,Object> inventorymap = new HashMap<>();
                inventorymap.put("stock",stockdatanew.getText().toString());
                inventorymap.put("inventorydata",holder.inventName.getText().toString());

                inventoryref.child("pushid").push()
                        .updateChildren(inventorymap)
                        .addOnCompleteListener(new OnCompleteListener<Void>()
                        {
                            @Override
                            public void onComplete(@NonNull Task<Void> task)
                            {
                                if(task.isSuccessful())
                                {


                                    Toast.makeText(context, "Inventory Added Successfully!!", Toast.LENGTH_SHORT).show();


                                }
                            }
                        });
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.cancel();
            }
        });
        builder.show();


 */






        inventBackBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {


                  Intent intent = new Intent(InventoryActivity.this,AdminActivity.class);
                 startActivity(intent);



            }
        });






    }

    @Override
    protected void onStart()
    {
        super.onStart();


        final DatabaseReference stockproductref = FirebaseDatabase.getInstance().getReference().child("InventoryProducts");



        FirebaseRecyclerOptions<InventoryProductsModel> options = new FirebaseRecyclerOptions
                .Builder<InventoryProductsModel>().setQuery(stockproductref.child("Stocks"),InventoryProductsModel.class).build();



        FirebaseRecyclerAdapter<InventoryProductsModel,InventoryviewHolder>adapter = new FirebaseRecyclerAdapter<InventoryProductsModel, InventoryviewHolder>(options)
        {

            @Override
            protected void onBindViewHolder(@NonNull InventoryviewHolder holder, int position, @NonNull InventoryProductsModel model)
            {
                holder.txtproductname2.setText(model.getProductname2());
                holder.txtproductprice2.setText(currencyFormat(model.getProductprice2()));
                holder.txtproductimage2.setImageResource(Integer.parseInt(model.getProductimage2().toString()));
                holder.txtstock2.setText(model.getStock2());


                holder.inventadd.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {


                        // holder.stock.setText(stockdatanew.getText().toString());


                        //inventorydata = holder.inventName.getText().toString();

                        DatabaseReference inventoryref = FirebaseDatabase.getInstance().getReference().child("Inventory");

                        HashMap<String,Object> inventorymap = new HashMap<>();
                        inventorymap.put("stock",holder.txtstock2.getText().toString());
                        inventorymap.put("inventorydata",holder.txtproductname2.getText().toString());

                        inventoryref.child("pushid").push()
                                .updateChildren(inventorymap)
                                .addOnCompleteListener(new OnCompleteListener<Void>()
                                {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task)
                                    {
                                        if(task.isSuccessful())
                                        {

                                            Toast.makeText(InventoryActivity.this, "Inventory Added Successfully!!", Toast.LENGTH_SHORT).show();


                                            DatabaseReference productref = FirebaseDatabase.getInstance().getReference().child("Inventory");


                                            productref.child("pushid").addListenerForSingleValueEvent(new ValueEventListener()
                                            {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot snapshot)
                                                {


                                                    for (DataSnapshot child: snapshot.getChildren())
                                                    {
                                                        inventorydata = child.child("inventorydata").getValue().toString();
                                                        stockdata = child.child("stock").getValue().toString();




                                                        DatabaseReference stockproductref = FirebaseDatabase.getInstance().getReference().child("InventoryProducts");

                                                        stockproductref.child("Stocks").addListenerForSingleValueEvent(new ValueEventListener()
                                                        {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot snapshot)
                                                            {

                                                                if(snapshot.hasChild(inventorydata))
                                                                {
                                                                    stockproductref.child("Stocks").child(inventorydata).child("stock2").setValue(stockdata);

                                                                }


                                                            }

                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError error)
                                                            {

                                                            }
                                                        });





                                                    }

                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError error)
                                                {

                                                }
                                            });





                                        }
                                    }
                                });


                    }
                });


                holder.deleteproduct.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {


                        DatabaseReference inventoryref = FirebaseDatabase.getInstance().getReference().child("Inventory");

                        HashMap<String,Object> inventorymap = new HashMap<>();

                        inventoryref.child("pushid").push()
                                .updateChildren(inventorymap)
                                .addOnCompleteListener(new OnCompleteListener<Void>()
                                {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task)
                                    {
                                        if(task.isSuccessful())
                                        {

                                          //  Toast.makeText(InventoryActivity.this, "Inventory Added Successfully!!", Toast.LENGTH_SHORT).show();


                                            DatabaseReference productref = FirebaseDatabase.getInstance().getReference().child("Inventory");


                                            productref.child("pushid").addListenerForSingleValueEvent(new ValueEventListener()
                                            {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot snapshot)
                                                {


                                                    for (DataSnapshot child: snapshot.getChildren())
                                                    {
                                                        inventorydata = child.child("inventorydata").getValue().toString();
                                                        stockdata = child.child("stock").getValue().toString();




                                                        DatabaseReference stockproductref = FirebaseDatabase.getInstance().getReference().child("InventoryProducts");

                                                        stockproductref.child("Stocks").addListenerForSingleValueEvent(new ValueEventListener()
                                                        {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot snapshot)
                                                            {

                                                                if(snapshot.hasChild(inventorydata))
                                                                {
                                                                    stockproductref.child("Stocks").child(inventorydata).removeValue();

                                                                }


                                                            }

                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError error)
                                                            {

                                                            }
                                                        });





                                                    }

                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError error)
                                                {

                                                }
                                            });





                                        }
                                    }
                                });






                    }
                });













            }


            @NonNull
            @Override
            public InventoryviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
            {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.inventory_list,parent,false);
                InventoryviewHolder inventoryviewHolder = new InventoryviewHolder(view);

                return inventoryviewHolder;
            }

        };

        recyclerViewstock.setAdapter(adapter);
        adapter.startListening();












    }


    /*
    public void categorynamesdata()
    {
        final DatabaseReference cartlistref = FirebaseDatabase.getInstance().getReference().child("Inventory");

        HomeCategory hc = new HomeCategory();


        final HashMap<String,Object> cartmap = new HashMap<>();

        cartmap.put("productname", Arrays.asList(hc.categoryNames));


        cartlistref.updateChildren(cartmap)
                .addOnCompleteListener(new OnCompleteListener<Void>()
                {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(InventoryActivity.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                        }
                    }
                });






    }

     */
    public static String currencyFormat(String amount)
    {
        DecimalFormat formatter = new DecimalFormat("###,###,##0.00");
        return formatter.format(Double.parseDouble(amount));
    }

    public void addproductnewnew(View view)
    {
        Intent intent = new Intent(InventoryActivity.this,AdminInventoryAddProducts.class);
        startActivity(intent);
    }



    public void gotoadminlast(View view)
    {
        Intent intent = new Intent(InventoryActivity.this,AdminActivity.class);
        startActivity(intent);
    }

}