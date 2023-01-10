package com.example.irasethpharma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
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

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import Prevalent.Prevalent;

public class ReceiptActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    public static  final int STORAGE_CODE = 1000;
    String orderid1;
    TextView orderid,invoice,username,deliverydate,totalprice,companyname,totalprice1;
    DatabaseReference userinformationref , usernameref;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);


        deliverydate = findViewById(R.id.textView28);
        totalprice = findViewById(R.id.textView92);
        companyname = findViewById(R.id.textView30);
        totalprice1 = findViewById(R.id.textView87);


        invoice = findViewById(R.id.textView26);
        username = findViewById(R.id.textView32);
        orderid = findViewById(R.id.textView34);
        recyclerView =findViewById(R.id.recyclerView3);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


        Random random = new Random();

        int val = random.nextInt(1000000000);


        orderid.setText(Integer.toString(val));
        invoice.setText(Integer.toString(val));


        username();
        userinformation();
        deliveryinformation();
        orderid();
       // orderstatus();

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

                holder.txtproductnameinvoice.setText(model.getProductname());
                holder.txtproductpriceinvoice.setText(model.getProductprice());
                holder.txtproductquantityinvoice.setText(model.getProductquantity());
              //  holder.productimage2.setImageResource(Integer.parseInt(model.getProductimage().toString()));
                // Picasso.get().load(model.getProductimage()).into(holder.productimage);

            }

            @NonNull
            @Override
            public Cartviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
            {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.invoice_list,parent,false);
                Cartviewholder cartviewholder = new Cartviewholder(view);

                return cartviewholder;
            }

        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();





    }





    public void orderid()
    {



        final DatabaseReference feedbackref = FirebaseDatabase.getInstance().getReference().child("Feedbacks").child(Prevalent.currentonlineuser.getEmailaddress());





        HashMap<String,Object> feedback = new HashMap<>();
        feedback.put("Orderid",orderid.getText().toString());





        feedbackref.updateChildren(feedback)
                .addOnCompleteListener(new OnCompleteListener<Void>()
                {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(ReceiptActivity.this, "", Toast.LENGTH_SHORT).show();

                        }

                    }
                });







    }











    public void userinformation ()
    {

        userinformationref = FirebaseDatabase.getInstance().getReference().child("Orders");

        userinformationref.child(Prevalent.currentonlineuser.getEmailaddress()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                Map<String,Object> map = (Map<String, Object>) snapshot.getValue();



                  String companyname1 = (String) map.get("Companyname");
                String maintotalprice = (String)map.get("Totalprice");
                //String addresss = (String)map.get("City");
                 String deliverydate1 = (String)map.get("Datefordelivery");
               // String baranggay = (String)map.get("Baranggay");
               // String houseno= (String)map.get("Houseno");
               // String Street= (String)map.get("Street");

                // company.setText(companyname);
                totalprice.setText(currencyFormat(maintotalprice));
                totalprice1.setText(currencyFormat(maintotalprice));
                deliverydate.setText(deliverydate1);
                companyname.setText(companyname1);
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

        usernameref.child(Prevalent.currentonlineuser.getEmailaddress()).addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                Map<String,Object> map = (Map<String, Object>) snapshot.getValue();



                String fname = (String) map.get("firstname");
                String lname = (String) map.get("lastname");

                username.setText(fname+" "+lname);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {


            }
        });

    }







    public void gotohomepage (View view)
    {
        done();
       // Intent intent =new Intent(ReceiptActivity.this,MainActivity.class);
       // startActivity(intent);
    }


    public void deliveryinformation()
    {
        final DatabaseReference deliveryinformationref = FirebaseDatabase.getInstance().getReference().child("Delivery Information");


        final HashMap<String,Object> cartmap = new HashMap<>();

        cartmap.put("Orderid",orderid.getText().toString());




        deliveryinformationref.child("CustomerDeliveryInformation").child(Prevalent.currentonlineuser.getEmailaddress()).child("Order Status")
                .updateChildren(cartmap)
                .addOnCompleteListener(new OnCompleteListener<Void>()
                {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if(task.isSuccessful())
                        {

                          //  Toast.makeText(ReceiptActivity.this, "Order ID Saved", Toast.LENGTH_SHORT).show();

                        }
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





                final DatabaseReference ordersref = FirebaseDatabase.getInstance().getReference().child("Orders").child(Prevalent.currentonlineuser.getEmailaddress());





                HashMap<String,Object>ordersmap = new HashMap<>();
                ordersmap.put("Status",status);



                // ordersmap.put("Status", "Order Placed");





                ordersref.updateChildren(ordersmap)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task)
                            {
                                if (task.isSuccessful())
                                {

                                    Toast.makeText(ReceiptActivity.this, "", Toast.LENGTH_SHORT).show();


                                }

                            }
                        });







            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {


            }
        });


    }

 */









    private void done()
    {

        final DatabaseReference ordersref = FirebaseDatabase.getInstance().getReference().child("Orders").child(Prevalent.currentonlineuser.getEmailaddress());

        HashMap<String,Object>ordersmap = new HashMap<>();
        ordersmap.put("Orderid",orderid.getText().toString());



        ordersref.updateChildren(ordersmap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if (task.isSuccessful())
                        {

                          //  Toast.makeText(DeliveryInformation.this, "Your final order has been placed successfully", Toast.LENGTH_SHORT).show();

                             FirebaseDatabase.getInstance().getReference()
                             .child("Cart List")
                             .child("User View")
                             .child(Prevalent.currentonlineuser.getEmailaddress())
                             .removeValue()
                             .addOnCompleteListener(new OnCompleteListener<Void>()
                             {
                            @Override
                            public void onComplete(@NonNull Task<Void> task)
                            {
                            if (task.isSuccessful())
                            {

                          //  Toast.makeText(ReceiptActivity.this, "Your final order has been placed successfully", Toast.LENGTH_SHORT).show();

                          //  FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                           // fragmentTransaction.replace(R.id.receiptid,new HomeFragment()).commit();


                            Intent intent = new Intent(ReceiptActivity.this, MainActivity.class);
                            intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TASK|intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            finish();

                            }
                            }
                            });

                        }

                    }
                });

    }


    public void printreceipt(View view)
    {
        PdfDocument mypdf = new PdfDocument();

        Paint paint = new Paint();
        PdfDocument.PageInfo mypageinfo = new PdfDocument.PageInfo.Builder(250,350,1).create();

        PdfDocument.Page mypage = mypdf.startPage(mypageinfo);
        Canvas canvas = mypage.getCanvas();

        paint.setTextSize(15.5f);
        paint.setColor(Color.rgb(0,50,250));

        canvas.drawText("Iraseth Receipt",20,20,paint);

        paint.setTextSize(8.5f);

        canvas.drawText("Welcome",20,40,paint);
        canvas.drawText("Welcome now",20,55,paint);


        canvas.drawText("Customer Name: " + "jheehe",20,80,paint);

        canvas.drawText("dasd",20,105,paint);


        canvas.drawText("dasdasdasd",20,135,paint);
        canvas.drawText("dasdasasdasdasddasd",20,135,paint);



        paint.setTextAlign(Paint.Align.RIGHT);


        canvas.drawText(String.valueOf(500),230,135,paint);

        paint.setTextAlign(Paint.Align.LEFT);

        canvas.drawText("dasd",20,175,paint);
        canvas.drawText("daASDsd",120,175,paint);
        paint.setTextAlign(Paint.Align.RIGHT);

        paint.setTextSize(10f);


        canvas.drawText("dasdasda",20,225,paint);

        paint.setTextAlign(Paint.Align.RIGHT);

        canvas.drawText("dasdasdaasas",230,225,paint);
        paint.setTextAlign(Paint.Align.LEFT);

        paint.setTextSize(8.5f);


        canvas.drawText("dasdasdasassssasas",20,290,paint);
        paint.setTextAlign(Paint.Align.CENTER);

        canvas.drawText("ty",canvas.getWidth()/2,320,paint);

        mypdf.finishPage(mypage);

        File file = new File(this.getExternalFilesDir("/"),"IrasethReceipt.pdf");


        try
        {
            mypdf.writeTo(new FileOutputStream(file));
        }
        catch (Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


        mypdf.close();



    }









    public static String currencyFormat(String amount)
    {
        DecimalFormat formatter = new DecimalFormat("###,###,##0.00");
        return formatter.format(Double.parseDouble(amount));
    }

}