package com.example.irasethpharma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

import Prevalent.Prevalent;

public class ChangePassword extends AppCompatActivity {

    EditText oldpass, newpass, repass;
    Button save, cancel;
    String email1,password1;
    DatabaseReference reference;
    DatabaseReference  usernameref;

    ImageView bck, oldvisible, oldVisibleOff, newVisible, newVisibleOff, reVisible, reVisisbleOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        oldpass = findViewById(R.id.userOldPassword);
        newpass = findViewById(R.id.userNewPassword);
        repass = findViewById(R.id.userReEnterPassword);
        save = findViewById(R.id.btnChangePass);
        cancel = findViewById(R.id.btnCancel);
        bck = findViewById(R.id.imgCPBack);
        oldvisible = findViewById(R.id.oldOn);
        oldVisibleOff = findViewById(R.id.oldOff);
        newVisible = findViewById(R.id.newOn);
        newVisibleOff = findViewById(R.id.newOff);
        reVisible = findViewById(R.id.reOn);
        reVisisbleOff = findViewById(R.id.reOff);


        reference = FirebaseDatabase.getInstance().getReference("users");




      //  Bundle extras = getIntent().getExtras();
//         password1 = extras.getString("keyOldPass");
       //  email1 = extras.getString("emailuser");


        //reference = FirebaseDatabase.getInstance().getReference("users");
       // oldpass.setText(password1);
       // newpass.setText(password1);




        save.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {


                if(newpass.getText().toString().equals(repass.getText().toString()))
                {
                    reference.child(Prevalent.currentonlineuser.getEmailaddress()).child("password").setValue(repass.getText().toString());


                    Toast.makeText(ChangePassword.this, "Data has been updated", Toast.LENGTH_LONG).show();
                    Toast.makeText(ChangePassword.this, "New Password Accepted", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ChangePassword.this, login.class);
                    startActivity(intent);

                }
                else
                {
                    Toast.makeText(ChangePassword.this, "Password is not match", Toast.LENGTH_SHORT).show();
                }




            }
        });






        oldvisible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oldpass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                oldvisible.setVisibility(View.INVISIBLE);
                oldVisibleOff.setVisibility(View.VISIBLE);
            }
        });

        oldVisibleOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oldpass.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
                oldvisible.setVisibility(View.VISIBLE);
                oldVisibleOff.setVisibility(View.INVISIBLE);
            }
        });

        newVisible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newpass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                newVisible.setVisibility(View.INVISIBLE);
                newVisibleOff.setVisibility(View.VISIBLE);
            }
        });

        newVisibleOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newpass.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
                newVisible.setVisibility(View.VISIBLE);
                newVisibleOff.setVisibility(View.INVISIBLE);
            }
        });

        reVisible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                repass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                reVisible.setVisibility(View.INVISIBLE);
                reVisisbleOff.setVisibility(View.VISIBLE);
            }
        });

        reVisisbleOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                repass.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
                reVisible.setVisibility(View.VISIBLE);
                reVisisbleOff.setVisibility(View.INVISIBLE);
            }
        });

        bck.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChangePassword.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }



    }



