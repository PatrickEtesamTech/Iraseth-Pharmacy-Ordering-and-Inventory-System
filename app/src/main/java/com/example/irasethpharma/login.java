package com.example.irasethpharma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import Prevalent.Prevalent;

public class login extends AppCompatActivity {

    DatabaseReference reference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://irasethpharma-ef44e-default-rtdb.firebaseio.com/");
    String parentdbname;
    TextInputLayout email ,password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.logInEmailAddress);
        password = findViewById(R.id.logInPassword);
        Button login = findViewById(R.id.btnLogin);



        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {


                String emaillogin = email.getEditText().getText().toString();
                String passwordlogin = password.getEditText().getText().toString();


                if(!validateusername()||!validatepass())
                {
                    return;
                }



                reference.child("users").addListenerForSingleValueEvent(new ValueEventListener()
                {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot)
                    {
                        if(snapshot.hasChild(emaillogin))
                        {

                            String getpassword =  snapshot.child(emaillogin).child("password").getValue(String.class);

                            if(getpassword.equals(passwordlogin))
                            {
                                Toast.makeText(login.this, "Successfully Logged in", Toast.LENGTH_SHORT).show();




                                UserHelperClass usersdata =  snapshot.child(emaillogin).getValue(UserHelperClass.class);
                                Prevalent.currentonlineuser = usersdata;

                             //  String username = snapshot.child(emaillogin).child("firstname").getValue(String.class);
                             //   String gender = snapshot.child(emaillogin).child("gender").getValue(String.class);
                             //   String lastname = snapshot.child(emaillogin).child("lastname").getValue(String.class);
                             //   String birthdate = snapshot.child(emaillogin).child("birthdate").getValue(String.class);
                             //   String contact = snapshot.child(emaillogin).child("contactnumber").getValue(String.class);
                             //  String emailaddress= snapshot.child(emaillogin).child("emailaddress").getValue(String.class);
                              //  String password = snapshot.child(emaillogin).child("password").getValue(String.class);
                               // Toast.makeText(login.this, username, Toast.LENGTH_SHORT).show();









                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);





                             //   intent.putExtra("gender",gender);
                               // intent.putExtra("birthdate",birthdate);
                               // intent.putExtra("username",username);
                               // intent.putExtra("lastname",lastname);
                              //  intent.putExtra("contact",contact);
                              //  intent.putExtra("emailaddress",emailaddress);
                              //  intent.putExtra("password",password);
                                startActivity(intent);






                            }
                            else
                            {
                                Toast.makeText(login.this, "Wrong Emailaddress or Password", Toast.LENGTH_SHORT).show();
                            }

                        }
                        else
                        {
                            Toast.makeText(login.this, "Wrong Email Address", Toast.LENGTH_SHORT).show();
                            reference.child("Admin").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot)
                                {

                                    if(snapshot.hasChild(emaillogin))
                                    {

                                        String getpassword =  snapshot.child(emaillogin).child("Admin Password").getValue(String.class);

                                        if(getpassword.equals(passwordlogin))
                                        {
                                            Toast.makeText(login.this, "Successfully Logged in as Admin", Toast.LENGTH_SHORT).show();

                                            Intent intent = new Intent(login.this, AdminActivity.class);
                                            startActivity(intent);

                                        }
                                        else
                                        {
                                            Toast.makeText(login.this, "Wrong Admin Password", Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                    else
                                    {
                                        Toast.makeText(login.this, "Wrong Admin User", Toast.LENGTH_SHORT).show();
                                    }



                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError error)
                                {

                                }
                            });

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error)
                    {

                    }
                });



                /*

                reference.child("Admin").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot)
                {

                    if(snapshot.hasChild(emaillogin))
                    {

                        String getpassword =  snapshot.child(emaillogin).child("Admin Password").getValue(String.class);

                        if(getpassword.equals(passwordlogin))
                        {
                            Toast.makeText(login.this, "Successfully Logged in as Admin", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(login.this, AdminActivity.class);
                            startActivity(intent);

                        }
                        else
                        {
                            Toast.makeText(login.this, "Wrong Admin Password", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else
                    {
                        Toast.makeText(login.this, "Wrong Admin User", Toast.LENGTH_SHORT).show();
                    }



                }
                @Override
                public void onCancelled(@NonNull DatabaseError error)
                {

                }
                });


                 */



            }
        });


    }

    private Boolean validateusername()
    {
        String val = email.getEditText().getText().toString();

        if(val.isEmpty())
        {
            email.setError("Field cannot be empty");
            return false;
        }
        else
        {
            email.setError(null);
            return true;
        }
    }
    private Boolean validatepass()
    {
        String val = password.getEditText().getText().toString();

        if(val.isEmpty())
        {
            password.setError("Field cannot be empty");
            return false;
        }
        else
        {
            password.setError(null);
            return true;
        }
    }












    public void gotosignup(View view)
    {
        Intent intent = new Intent(login.this, SignUp.class);
        startActivity(intent);
    }

    public void gotoforgotpassword(View view)
    {
        Intent intent = new Intent(login.this, ForgotPassword.class);
        intent.putExtra("check","login");
        startActivity(intent);
    }
}
