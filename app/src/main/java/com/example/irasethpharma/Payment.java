package com.example.irasethpharma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.Map;

import Prevalent.Prevalent;

public class Payment extends AppCompatActivity
{
        TextView payment;
        DatabaseReference userinformationref;
        TextInputEditText num , name;
        String maintotalprice;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);


        payment = findViewById(R.id.paymentAmount);
        num = findViewById(R.id.num);
        name = findViewById(R.id.name);






        gettotalamount();







    }

    public void gettotalamount()
    {
        userinformationref = FirebaseDatabase.getInstance().getReference().child("Orders");

        userinformationref.child(Prevalent.currentonlineuser.getEmailaddress()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                Map<String,Object> map = (Map<String, Object>) snapshot.getValue();



                String companyname1 = (String) map.get("Companyname");
                 maintotalprice = (String)map.get("Totalprice");
                //String addresss = (String)map.get("City");
                String deliverydate1 = (String)map.get("Datefordelivery");
                // String baranggay = (String)map.get("Baranggay");
                // String houseno= (String)map.get("Houseno");
                // String Street= (String)map.get("Street");

                // company.setText(companyname);
                payment.setText(currencyFormat(maintotalprice));
                // date.setText(deliverydate);



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



    public void gotoreceipt(View view)
    {

        if( !validatenum()|| !validatename() )
        {
            return;
        }

        Intent intent = new Intent(Payment.this,ReceiptActivity.class);
        startActivity(intent);

    }




    private Boolean validatename()
    {
        String val = name.getText().toString();

        if(val.isEmpty())
        {
            name.setError("Field cannot be empty");
            return false;
        }
        else
        {
            name.setError(null);
            return true;
        }
    }

    private Boolean validatenum()
    {
        String val = num.getText().toString();

        if(val.isEmpty())
        {
            num.setError("Field cannot be empty");
            return false;
        }
        else
        {
            num.setError(null);
            return true;
        }
    }




}