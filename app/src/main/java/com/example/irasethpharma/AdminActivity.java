package com.example.irasethpharma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);





        notif();














    }




    public void notif()
    {
        DatabaseReference stockproductref = FirebaseDatabase.getInstance().getReference().child("InventoryProducts");

        stockproductref.child("Stocks").addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {

                for (DataSnapshot child: snapshot.getChildren())
                {
                    String stockupdate = child.child("stock2").getValue().toString();
                    String stockname = child.child("productname2").getValue().toString();


                    if(Integer.parseInt(stockupdate) <= 50 && !stockupdate.equals("0") )
                    {

                        AlertDialog.Builder alert = new AlertDialog.Builder(AdminActivity.this);
                        alert.setTitle("Alert!!!");
                        alert.setMessage(stockname + " is Low on stock");
                        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                             dialogInterface.cancel();
                            }
                        });
                        alert.create().show();
                    }
                    else if (stockupdate.equals("0"))
                    {

                        AlertDialog.Builder alert = new AlertDialog.Builder(AdminActivity.this);
                        alert.setTitle("Alert!!!");
                        alert.setMessage(stockname + " is Out of stock");
                        alert.setPositiveButton("Add Stock", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                Intent intent = new Intent(AdminActivity.this, InventoryActivity.class);
                                startActivity(intent);
                            }
                        });
                        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                dialogInterface.cancel();
                            }
                        });
                        alert.create().show();

                    }
                    else
                    {
                       // Toast.makeText(AdminActivity.this, "Welcome Admin!!!", Toast.LENGTH_SHORT).show();
                    }


                }

                }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        });


    }













    public void logoutadmin(View view)
    {

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Log Out");
        alert.setMessage("Are you sure you want to log out?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                Intent intent = new Intent(AdminActivity.this, login.class);
                startActivity(intent);
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                Toast toast = Toast.makeText(AdminActivity.this, "Welcome Back!!!", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER_HORIZONTAL| Gravity.CENTER,0 ,0);
                toast.setDuration(toast.LENGTH_LONG);
                toast.show();
            }
        });
        alert.create().show();




    }


    public void gotosalesreport(View view)
    {

        Intent intent = new Intent(AdminActivity.this, SalesReport.class);
        startActivity(intent);

    }



    public void gotoinventory(View view)
    {

        Intent intent = new Intent(AdminActivity.this, InventoryActivity.class);
        startActivity(intent);

    }

    public void gotocustomerdetails(View view)
    {

        Intent intent = new Intent(AdminActivity.this, Customerdetails.class);
        startActivity(intent);

    }
    public void gotocustomerorderstatus(View view)
    {

        Intent intent = new Intent(AdminActivity.this,  Customeroderstatus.class);
        startActivity(intent);

    }
    public void gotosupplierdetails(View view)
    {

        Intent intent = new Intent(AdminActivity.this,  SupplierDetails.class);
        startActivity(intent);

    }
    public void gotoadminfeedback(View view)
    {
        Intent intent = new Intent(AdminActivity.this,  Admin_Feedback.class);
        startActivity(intent);

    }
}