package com.example.irasethpharma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

import org.w3c.dom.Text;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

import Prevalent.Prevalent;

public class Customeroderstatus extends AppCompatActivity
{

    private RecyclerView orderlist;
    private DatabaseReference ordersref;




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customeroderstatus);


        ordersref = FirebaseDatabase.getInstance().getReference().child("Orders");




        orderlist = findViewById(R.id.orderstatusrc);



        orderlist.setLayoutManager(new LinearLayoutManager(this));


        //orderstatus();




       // displayorderstatus();





    }


    @Override
    protected void onStart()
    {
        super.onStart();


        FirebaseRecyclerOptions<AdminCustomerOrders> options
                = new FirebaseRecyclerOptions.Builder<AdminCustomerOrders>()
                .setQuery(ordersref,AdminCustomerOrders.class)
                .build();


        FirebaseRecyclerAdapter<AdminCustomerOrders,AdminOrdersViewHolder>adapter =
                new FirebaseRecyclerAdapter<AdminCustomerOrders, AdminOrdersViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull AdminOrdersViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull AdminCustomerOrders model)
                    {
                        holder.txtorderid.setText(model.getOrderid());
                        holder.txtdatefordelivery.setText(model.getDatefordelivery());
                        holder.customername1.setText(model.getCustomername());


                        holder.showorderscustomer.setOnClickListener(new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View v)
                            {
                                //model.getEmailaddress - Alternative for getkey uid position
                                String uid = getRef(position).getKey();

                                Intent intent = new Intent(Customeroderstatus.this,AdminUserProducts.class);
                                intent.putExtra("uid",uid);
                                startActivity(intent);
                            }
                        });


                        holder.updatestatusorder.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v)
                            {
                                CharSequence options[] = new CharSequence[]
                                        {
                                                "To Ship",
                                                "To Receive",
                                                "To Rate"
                                        };

                                AlertDialog.Builder builder = new AlertDialog.Builder(Customeroderstatus.this);

                                builder.setTitle("Update Order Status");

                                builder.setItems(options, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which)
                                    {
                                        if(which == 0)
                                        {
                                             DatabaseReference adminordersstatusref = FirebaseDatabase.getInstance().getReference().child("Delivery Information");

                                            adminordersstatusref.child("CustomerDeliveryInformation").child(Prevalent.currentonlineuser.getEmailaddress()).child("Order Status").child("Status").setValue("To Ship");


                                            holder.txtstatus.setText("To Ship");

                                          //  Toast.makeText(Customeroderstatus.this, "On Delivery", Toast.LENGTH_SHORT).show();



                                        }
                                        else if (which == 1)
                                        {

                                             DatabaseReference adminordersstatusref1 = FirebaseDatabase.getInstance().getReference().child("Delivery Information");

                                            adminordersstatusref1.child("CustomerDeliveryInformation").child(Prevalent.currentonlineuser.getEmailaddress()).child("Order Status").child("Status").setValue("To Receive");


                                            holder.txtstatus.setText("To Receive");


                                          //  Toast.makeText(Customeroderstatus.this, "Order Received", Toast.LENGTH_SHORT).show();


                                        }
                                        else if (which == 2)
                                        {
                                             DatabaseReference adminordersstatusref2 = FirebaseDatabase.getInstance().getReference().child("Delivery Information");

                                            adminordersstatusref2.child("CustomerDeliveryInformation").child(Prevalent.currentonlineuser.getEmailaddress()).child("Order Status").child("Status").setValue("To Rate");

                                         //   Toast.makeText(Customeroderstatus.this, "To rate", Toast.LENGTH_SHORT).show();
                                            String uid = getRef(position).getKey();
                                            RemoveOrder(uid);
                                        }

                                        else
                                        {
                                            finish();
                                        }


                                    }



                                    private void RemoveOrder(String uid)
                                    {
                                        ordersref.child(uid).removeValue();

                                        DatabaseReference cartlistref = FirebaseDatabase.getInstance().getReference().child("Cart List");

                                        cartlistref.child("Admin View").child(Prevalent.currentonlineuser.getEmailaddress()).removeValue();

                                    }



                                });

                                builder.show();


                            }
                        });













                    }

                    @NonNull
                    @Override
                    public AdminOrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
                    {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_order_list,parent,false);

                        return new AdminOrdersViewHolder(view);
                    }
                };

        orderlist.setAdapter(adapter);
        adapter.startListening();


    }

    public static class AdminOrdersViewHolder extends RecyclerView.ViewHolder
    {

        public TextView txtbaranggay , txtaddress , txthouseno,txtcompanyname,txtdate,txtdatefordelivery,txtstatus ,txttime,txttotalprice,txtstreet,txtorderid;

        public TextView showorderscustomer,updatestatusorder,customername1;

        public AdminOrdersViewHolder(@NonNull View itemView)
        {
            super(itemView);





            txtorderid = itemView.findViewById(R.id.tvOrderID);
          //  txtcompanyname= itemView.findViewById(R.id.textView53);
           // txthouseno = itemView.findViewById(R.id.textView51);
          //  txtstreet = itemView.findViewById(R.id.textView51);
           // txtbaranggay = itemView.findViewById(R.id.textView51);
           // txtaddress = itemView.findViewById(R.id.textView51);
            txtdatefordelivery = itemView.findViewById(R.id.tvDeliveryDate);
            showorderscustomer = itemView.findViewById(R.id.tvViewProduct);
            updatestatusorder = itemView.findViewById(R.id.tvUpdateStatus);
            customername1 = itemView.findViewById(R.id.tvOrderCostumerName);
            txtstatus = itemView.findViewById(R.id.tvDeliveryStatus);





        }
    }


    /*


    public void displayorderstatus()
    {

        DatabaseReference adminordersstatusref = FirebaseDatabase.getInstance().getReference().child("Delivery Information");


        adminordersstatusref.child("CustomerDeliveryInformation").child(Prevalent.currentonlineuser.getEmailaddress()).child("Order Status").addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                Map<String,Object> map = (Map<String, Object>) snapshot.getValue();



                String orderstatus = (String) map.get("Status");


                txtstatus.setText(orderstatus);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {


            }
        });

    }

    /*

    public void orderstatus()
    {
        final DatabaseReference deliveryinformationref = FirebaseDatabase.getInstance().getReference().child("Delivery Information");


        deliveryinformationref.child("CustomerDeliveryInformation").child(Prevalent.currentonlineuser.getEmailaddress()).child("Order Status").addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                Map<String,Object> map = (Map<String, Object>) snapshot.getValue();



                String status = (String) map.get("Status");

                TextView txtstatus = findViewById(R.id.tvDeliveryStatus);
                txtstatus.setText(status);

                // ordersmap.put("Status", "Order Placed");





            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {


            }
        });

    }

     */



    public void gotomainadmin(View view)
    {
        Intent intent = new Intent(Customeroderstatus.this,AdminActivity.class);
        startActivity(intent);
    }


}