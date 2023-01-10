package com.example.irasethpharma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Locale;

import Prevalent.Prevalent;

public class SetUpSecurityQuestion extends AppCompatActivity
{
    private Button done;
    private EditText question1,question2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up_security_question);

        done = findViewById(R.id.btnDone);
        question1 = findViewById(R.id.editText);
        question2 = findViewById(R.id.editText2);



    }
    @Override
    protected void onStart()
    {
        super.onStart();

        displaysecanswers();

        done.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                setanswers();

            }
        });


    }
     public void setanswers()
     {
         String answer1 = question1.getText().toString().toLowerCase();
         String answer2 = question2.getText().toString().toLowerCase();

         if(answer1.equals("")  && answer2.equals(""))
         {
             Toast.makeText(SetUpSecurityQuestion.this, "Please Answer the Questions", Toast.LENGTH_SHORT).show();
         }
         else
         {
             DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users").child(Prevalent.currentonlineuser.getEmailaddress());

             HashMap<String , Object> usersdatamap = new HashMap<>();
             usersdatamap.put("answer1",answer1);
             usersdatamap.put("answer2",answer2);


             ref.child("Security Questions").updateChildren(usersdatamap).addOnCompleteListener(new OnCompleteListener<Void>() {
                 @Override
                 public void onComplete(@NonNull Task<Void> task)
                 {
                     if(task.isSuccessful())
                     {
                         Toast.makeText(SetUpSecurityQuestion.this, "Security Questions Added Successfully!!!", Toast.LENGTH_SHORT).show();
                       //  Intent intent = new Intent(SetUpSecurityQuestion.this, MainActivity.class);
                      //   startActivity(intent);
                     }

                 }
             });

         }
     }


     public void displaysecanswers()
     {
         DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users").child(Prevalent.currentonlineuser.getEmailaddress());


         ref.child("Security Questions").addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot snapshot)
             {

                 if(snapshot.exists())
                 {
                     String ans1 = snapshot.child("answer1").getValue().toString();
                     String ans2 = snapshot.child("answer2").getValue().toString();

                     question1.setText(ans1);
                     question2.setText(ans2);
                 }
             }

             @Override
             public void onCancelled(@NonNull DatabaseError error)
             {

             }
         });

     }





}