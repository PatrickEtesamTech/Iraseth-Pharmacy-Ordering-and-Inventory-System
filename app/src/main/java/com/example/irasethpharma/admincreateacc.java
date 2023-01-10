package com.example.irasethpharma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import Prevalent.Prevalent;


public class admincreateacc extends AppCompatActivity
{
  TextInputLayout adminuser , adminpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admincreateacc);

        adminuser = findViewById(R.id.firstName);
        adminpassword = findViewById(R.id.middleName);








    }

    public void adminacc(View view)
    {

        final DatabaseReference ordersref = FirebaseDatabase.getInstance().getReference().child("Admin").child("Admin");

        HashMap<String,Object> ordersmap = new HashMap<>();
        ordersmap.put("Admin Password", adminpassword.getEditText().getText().toString());
        ordersmap.put("Admin User", adminuser.getEditText().getText().toString());



        ordersref.updateChildren(ordersmap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if (task.isSuccessful())
                        {

                            Toast.makeText(admincreateacc.this, "Admin Sucessfully Created an Account", Toast.LENGTH_SHORT).show();

                            /**
                             FirebaseDatabase.getInstance().getReference()
                             .child("Cart List")
                             .child("User View")
                             .child(Prevalent.currentonlineuser.getEmailaddress())
                             .removeValue()
                             .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task)
                            {
                            if (task.isSuccessful())
                            {
                            Toast.makeText(DeliveryInformation.this, "Your final order has been placed successfully", Toast.LENGTH_SHORT).show();
                            // Intent intent = new Intent(DeliveryInformation.this, MainActivity.class);
                            // intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TASK|intent.FLAG_ACTIVITY_CLEAR_TASK);
                            // startActivity(intent);
                            //finish()

                            }
                            }
                            });
                             **/
                        }

                    }
                });



    }


}