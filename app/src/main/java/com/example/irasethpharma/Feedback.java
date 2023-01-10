package com.example.irasethpharma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import Prevalent.Prevalent;

public class Feedback extends AppCompatActivity
{
    Button submitbtn;
    EditText comment;
    RatingBar userrating;
    DatabaseReference  usernameref;
    String fname,lname,orderid1;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);



        comment = findViewById(R.id.editTextTextMultiLine);
        userrating = findViewById(R.id.rating);
        submitbtn = findViewById(R.id.subFeed);


        submitbtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
               // Toast.makeText(Feedback.this, "Thank you for giving us a Feedback!", Toast.LENGTH_SHORT).show();
                customername();
                check();

            }
        });
    }


    private void check()
    {
        if(TextUtils.isEmpty(comment.getText().toString()))
        {
            Toast.makeText(this, "Please type your comment", Toast.LENGTH_SHORT).show();
        }
        else
        {
            submit();
        }

    }



    public void customername()
    {

        DatabaseReference usernameref;

        usernameref = FirebaseDatabase.getInstance().getReference().child("users");

        usernameref.child(Prevalent.currentonlineuser.getEmailaddress()).addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                Map<String,Object> map = (Map<String, Object>) snapshot.getValue();



                fname = (String) map.get("firstname");
                lname = (String) map.get("lastname");




                DatabaseReference ordersref = FirebaseDatabase.getInstance().getReference().child("Feedbacks").child(Prevalent.currentonlineuser.getEmailaddress());



                HashMap<String,Object>ordersmap = new HashMap<>();
                ordersmap.put("Customername",fname+" "+lname);


                ordersref.updateChildren(ordersmap)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task)
                            {
                                if (task.isSuccessful())
                                {

                                    Toast.makeText(Feedback.this, "", Toast.LENGTH_SHORT).show();


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



    private void submit()
    {


        String savecurrenttime , savecurrentdate;
        Calendar date = Calendar.getInstance();
        SimpleDateFormat currentdate = new SimpleDateFormat("MMM dd,yyyy");
        savecurrentdate = currentdate.format(date.getTime());

        SimpleDateFormat currenttime = new SimpleDateFormat("HH:mm:ss");
        savecurrenttime = currenttime.format(date.getTime());


         DatabaseReference feedbackref = FirebaseDatabase.getInstance().getReference().child("Feedbacks").child(Prevalent.currentonlineuser.getEmailaddress());





        HashMap<String,Object> feedback = new HashMap<>();
        feedback.put("Date", savecurrentdate);
        feedback.put("Time", savecurrenttime);
        feedback.put("Customername", fname+lname);
        feedback.put("Feedback",comment.getText().toString());
        feedback.put("Rating",userrating.getRating());



        // ordersmap.put("Status", "Order Placed");





        feedbackref.updateChildren(feedback)
                .addOnCompleteListener(new OnCompleteListener<Void>()
                {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if (task.isSuccessful())
                        {

                            Intent intent = new Intent(Feedback.this, MainActivity.class);
                            startActivity(intent);
                            Toast.makeText(Feedback.this, "Thank you for giving us a Feedback!", Toast.LENGTH_SHORT).show();

                        }

                    }
                });


    }


    public void gotomain(View view)
    {
        Intent intent = new Intent(Feedback.this,MainActivity.class);
        startActivity(intent);
    }


}