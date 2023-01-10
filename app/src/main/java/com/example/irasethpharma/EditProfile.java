package com.example.irasethpharma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class EditProfile extends AppCompatActivity

{
    DatabaseReference  usernameref;
    DatabaseReference reference;
    EditText displayusername;
    EditText displaygender;
    EditText displaybday;
    EditText displaylastname;
    EditText displaycontact;
    ImageView savebutton,exitbutton;

    String contact1 ,fname , lname , bday , gender ;

    // EditText displaypassword;
  //  String username,gender1,lastname1,contact1,email1,bday1;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);





        Bundle extras = getIntent().getExtras();
       //  username = extras.getString("firstname");
       // lastname1 = extras.getString("lastname");
       // gender1 = extras.getString("gender");
       // bday1 = extras.getString("bday");
      //  contact1 = extras.getString("contact");
      //  email1 = extras.getString("email");


        savebutton = findViewById(R.id.imgEditProfSave);
        exitbutton = findViewById(R.id.imgEditProfCancel);

       displayusername = findViewById(R.id.editFname);
        displaylastname = findViewById(R.id.editLname);
        displaygender= findViewById(R.id.editGender);
        displaybday = findViewById(R.id.editBDay);
        displaycontact = findViewById(R.id.editContact);



        reference = FirebaseDatabase.getInstance().getReference("users");
        profileinformation1();
        // displayusername.setText(username);
       //  displaylastname.setText(lastname1);
      //  displaygender.setText(gender1);
       // displaybday.setText(bday1);
       // displaycontact.setText(contact1);



        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                do
                {
                    Toast.makeText(EditProfile.this, "Data has been updated", Toast.LENGTH_SHORT).show();
                }
                while (isfirstnamechange()  || islastnamechange()|| iscontactchange() || isbdaychange() || isgenderchange() );


            }
        });

        exitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                Intent intent = new Intent(EditProfile.this, MainActivity.class);
                startActivity(intent);



            }
        });

    }


    private boolean iscontactchange()
    {
        if(!contact1.equals(displaycontact.getText().toString()))
        {
            reference.child(Prevalent.currentonlineuser.getEmailaddress()).child("contactnumber").setValue(displaycontact.getText().toString());
            contact1 = displaycontact.getText().toString();
            return true;
        }
        else
        {
            return false;
        }
    }

    private boolean islastnamechange()
    {
        if(!lname.equals(displaylastname.getText().toString()))
        {
            reference.child(Prevalent.currentonlineuser.getEmailaddress()).child("lastname").setValue(displaylastname.getText().toString());
            lname = displaylastname.getText().toString();
            return true;
        }
        else
        {
            return false;
        }
    }


    private boolean isfirstnamechange()
    {
        if(!fname.equals(displayusername.getText().toString()))
        {
         reference.child(Prevalent.currentonlineuser.getEmailaddress()).child("firstname").setValue(displayusername.getText().toString());
         fname = displayusername.getText().toString();
         return true;
        }
        else
        {
            return false;
        }
    }

    private boolean isbdaychange()
    {
        if(!bday.equals(displaybday.getText().toString()))
        {
            reference.child(Prevalent.currentonlineuser.getEmailaddress()).child("birthdate").setValue(displaybday.getText().toString());
            bday = displaybday.getText().toString();
            return true;
        }
        else
        {
            return false;
        }
    }

    private boolean isgenderchange()
    {
        if(!gender.equals(displaygender.getText().toString()))
        {
            reference.child(Prevalent.currentonlineuser.getEmailaddress()).child("gender").setValue(displaygender.getText().toString());
            gender = displaygender.getText().toString();
            return true;
        }
        else
        {
            return false;
        }
    }

    public void profileinformation1()
    {
        usernameref = FirebaseDatabase.getInstance().getReference().child("users");

        usernameref.child(Prevalent.currentonlineuser.getEmailaddress()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                Map<String,Object> map = (Map<String, Object>) snapshot.getValue();




                 fname = (String) map.get("firstname");
                 lname = (String) map.get("lastname");
                bday = (String) map.get("birthdate");
                gender = (String) map.get("gender");
                contact1 = (String) map.get("contactnumber");
                //  String email1 = (String) map.get("emailaddress");


                displayusername.setText(fname);
                displaylastname.setText(lname);
                displaygender.setText(gender);
                displaybday.setText(bday);
                displaycontact.setText(contact1);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {


            }
        });

    }



}