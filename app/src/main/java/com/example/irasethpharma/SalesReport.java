package com.example.irasethpharma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.sax.Element;
import android.text.Spannable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SalesReport extends AppCompatActivity
{
    RecyclerView salesrc;
    DatabaseReference databaseReference;
    SalesReportAdapter myadapter;
    TextView salesdate,totalsales,t1,t2,t3;
    ImageView image1 ,image2 , image3;
    String quantity;
    String pname;
    String price;
    String imagenew;
    ArrayList<Integer>quantitylist;
    Double total = 0.0;
    int top1 = 0 , top2 = 0, top3 = 0 ;
    ArrayList<SalesModel> list;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_report);

        salesrc = findViewById(R.id.salesrc);
        salesdate = findViewById(R.id.salesDate);
        totalsales = findViewById(R.id.totalsales1);
        image1 = findViewById(R.id.imageView41);
        image2 = findViewById(R.id.imageView42);
        image3 = findViewById(R.id.imageView43);
        t1 = findViewById(R.id.top1);
        t2 = findViewById(R.id.top2);
        t3 = findViewById(R.id.top3);

       databaseReference = FirebaseDatabase.getInstance().getReference().child("Salesreport");

        salesrc.setHasFixedSize(true);

        salesrc.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();

        myadapter = new SalesReportAdapter(this,list);

        salesrc.setAdapter(myadapter);

        getdate();


        quantitylist = new ArrayList<>();



        databaseReference.addListenerForSingleValueEvent(new ValueEventListener()
        {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    SalesModel user = dataSnapshot.getValue(SalesModel.class);

                     pname = dataSnapshot.child("productname1").getValue().toString();
                    price = dataSnapshot.child("productprice1").getValue().toString();
                    quantity = dataSnapshot.child("productquantity1").getValue().toString();
                    imagenew = dataSnapshot.child("productimage1").getValue().toString();


                    Double pretotal = Double.parseDouble(price) * Double.parseDouble(quantity);

                    total = total + pretotal;

                    quantitylist.add(Integer.parseInt(quantity));


                    totalsales.setText(currencyFormat(String.valueOf(total)));


                    list.add(user);



                }

                bubbleSortArrayList(quantitylist);

                top1 = quantitylist.get(0);
                top2 = quantitylist.get(1);
                top3 = quantitylist.get(2);

                myadapter.notifyDataSetChanged();






            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        });






        databaseReference.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    SalesModel user = dataSnapshot.getValue(SalesModel.class);

                   String  pname1 = dataSnapshot.child("productname1").getValue().toString();
                    String   price1 = dataSnapshot.child("productprice1").getValue().toString();
                    String   quantity1 = dataSnapshot.child("productquantity1").getValue().toString();
                    String    imagenew1 = dataSnapshot.child("productimage1").getValue().toString();



                    if(quantity1.equals(String.valueOf(top1)))
                    {
                        t1.setText(pname1);
                        image1.setImageResource(Integer.parseInt(imagenew1));
                    }
                    if(quantity1.equals(String.valueOf(top2)))
                    {
                        t2.setText(pname1);
                        image2.setImageResource(Integer.parseInt(imagenew1));
                    }
                    if(quantity1.equals(String.valueOf(top3)))
                    {
                        t3.setText(pname1);
                        image3.setImageResource(Integer.parseInt(imagenew1));
                    }

                }






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

    public void gotoadmin2 (View view)
    {
        Intent intent = new Intent(SalesReport.this,AdminActivity.class);
        startActivity(intent);
    }



    public void getdate()
    {
        String savecurrenttime , savecurrentdate;
        Calendar date = Calendar.getInstance();
        SimpleDateFormat currentdate = new SimpleDateFormat("MMM dd,yyyy");
        savecurrentdate = currentdate.format(date.getTime());

        SimpleDateFormat currenttime = new SimpleDateFormat("HH:mm:ss");
        savecurrenttime = currenttime.format(date.getTime());

        salesdate.setText(savecurrentdate);

    }

    public void bubbleSortArrayList(List<Integer> list)
    {
        Integer temp;
        boolean sorted = false;

        while (!sorted) {
            sorted = true;
            for (int i = 0; i < list.size()-1; i++) {
                if (list.get(i).compareTo(list.get(i + 1)) < 0)
                {
                    temp = list.get(i);
                    list.set(i, list.get(i + 1));
                    list.set(i + 1, temp);
                    sorted = false;
                }
            }
        }
    }


}