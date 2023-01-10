package com.example.irasethpharma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

import Prevalent.Prevalent;

public class ForgotPassword extends AppCompatActivity
{

    private String check = "";
    private TextView pagetitle,pagetitle1;
    private Button verifybutton;
    private TextInputLayout question1,question2,email;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);


        check = getIntent().getStringExtra("check");


        pagetitle = findViewById(R.id.textView95);
        pagetitle1 = findViewById(R.id.textView97);
        question1 = findViewById(R.id.textInputLayout4);
        question2 = findViewById(R.id.textInputLayout5);
        email = findViewById(R.id.textInputLayout3);
        verifybutton = findViewById(R.id.btnVerifiy);






    }

    @Override
    protected void onStart()
    {
        super.onStart();


        verifybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                verifyuser();

            }
        });


    }

    public void verifyuser()
    {
        String emailuser = email.getEditText().getText().toString();
        String answer1 = question1.getEditText().getText().toString().toLowerCase();
        String answer2 = question2.getEditText().getText().toString().toLowerCase();


        if(!emailuser.equals("") && !answer1.equals("")&&!answer2.equals(""))
        {
            final DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users").child(emailuser);

            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot)
                {
                    if(snapshot.exists())
                    {


                        if(snapshot.hasChild("Security Questions"))
                        {
                            String ans1 = snapshot.child("Security Questions").child("answer1").getValue().toString();
                            String ans2 = snapshot.child("Security Questions").child("answer2").getValue().toString();

                            if(!ans1.equals(answer1))
                            {
                                Toast.makeText(ForgotPassword.this, "Your first answer is wrong,please try again.", Toast.LENGTH_SHORT).show();
                            }
                            else if(!ans2.equals(answer2))
                            {
                                Toast.makeText(ForgotPassword.this, "Your second answer is wrong,please try again.", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                AlertDialog.Builder builder = new AlertDialog.Builder(ForgotPassword.this);

                                builder.setTitle("New Password");

                                final EditText newpassword = new EditText(ForgotPassword.this);
                                newpassword.setHint("Enter your new password here...");
                                builder.setView(newpassword);

                                builder.setPositiveButton("Reset Password", new DialogInterface.OnClickListener()
                                {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which)
                                    {
                                        if(!newpassword.getText().toString().equals(""))
                                        {
                                            ref.child("password").setValue(newpassword.getText().toString())
                                                    .addOnCompleteListener(new OnCompleteListener<Void>()
                                                    {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task)
                                                        {
                                                            if(task.isSuccessful())
                                                            {
                                                                Toast.makeText(ForgotPassword.this, "Password changed successfully!!!", Toast.LENGTH_SHORT).show();
                                                                  Intent intent = new Intent(ForgotPassword.this,PasswordUpdated.class);
                                                                   startActivity(intent);
                                                            }
                                                        }
                                                    });
                                        }
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
                            }
                        }

                        else
                        {
                            Toast.makeText(ForgotPassword.this, "You have not set your Security Questions,please go to account settings and click set security questions.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(ForgotPassword.this, "This EmailAddress do not exist", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error)
                {

                }
            });

        }
        else
        {
            Toast.makeText(this, "Please provide the necessesary details.", Toast.LENGTH_SHORT).show();
        }


       



    }

}