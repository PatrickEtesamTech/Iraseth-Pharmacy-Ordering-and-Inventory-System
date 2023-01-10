package com.example.irasethpharma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.irasethpharma.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class FinalVerification extends AppCompatActivity
{
    String verificationcodebysystem;
    Button verifybutton;
    EditText verifynum,verifynum2;
    private String verificationId;
  //  FirebaseAuth mAuth;
    FirebaseUser user;
    FinalVerification binding;
    String password;
    String pass ;
    String email;
    private FirebaseAuth mAuth;
    String email1 ,pass1;




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_verification);


        verifybutton = findViewById(R.id.verifSubmitBtn);



        verifynum = findViewById(R.id.editNum1);
        verifynum2 = findViewById(R.id.editNum2);









        pass = getIntent().getStringExtra("pass");
        email = getIntent().getStringExtra("email");



          verifynum.setText(email);
         verifynum2.setText(pass);


















        //  mAuth = FirebaseAuth.getInstance();


       // String phoneno = getIntent().getStringExtra("phone");


       // sendverificationuser(phoneno);


       // mAuth = FirebaseAuth.getInstance();



        /*


        binding.verifybutton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                email1 = binding.verifynum.getText().toString();
                pass1 = binding.verifynum2.getText().toString();

                FirebaseAuth.getInstance()
                        .signInWithEmailAndPassword(email1,pass1)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>()
                        {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task)
                            {
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                if(user.isEmailVerified())
                                {
                                    Toast.makeText(FinalVerification.this, "Successfully Verified", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                     user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>()
                                     {
                                         @Override
                                         public void onSuccess(Void unused)
                                         {
                                             Toast.makeText(FinalVerification.this, "Kindly verify your email", Toast.LENGTH_SHORT).show();
                                         }
                                     }).addOnFailureListener(new OnFailureListener()
                                     {
                                         @Override
                                         public void onFailure(@NonNull Exception e)
                                         {
                                             Toast.makeText(FinalVerification.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                         }
                                     });
                                }
                            }
                        });

            }
        });




         */


    }









/*

    private void signInWithCredential(PhoneAuthCredential credential) {
        // inside this method we are checking if
        // the code entered is correct or not.
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // if the code is correct and the task is successful
                            // we are sending our user to new activity.
                            Intent i = new Intent(FinalVerification.this, AccountVerified.class);
                            startActivity(i);
                            finish();
                        } else {
                            // if the code is not correct then we are
                            // displaying an error message to the user.
                            Toast.makeText(FinalVerification.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }



    private void sendverificationuser(String number) {
        // this method is used for getting
        // OTP on user phone number.
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+92" + number,            // Phone number to verify
                60, // Timeout and unit
                TimeUnit.SECONDS,                 // Activity (for callback binding)
                this  ,
                mCallBack);// OnVerificationStateChangedCallbacks

    }


    // callback method is called on Phone auth provider.
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks

            // initializing our callbacks for on
            // verification callback method.
            mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        // below method is used when
        // OTP is sent from Firebase
        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            // when we receive the OTP it
            // contains a unique id which
            // we are storing in our string
            // which we have already created.
            verificationId = s;
        }

        // this method is called when user
        // receive OTP from Firebase.
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            // below line is used for getting OTP code
            // which is sent in phone auth credentials.
            final String code = phoneAuthCredential.getSmsCode();

            // checking if the code
            // is null or not.
            if (code != null) {
                // if the code is not null then
                // we are setting that code to
                // our OTP edittext field.
                verifynum.setText(code);

                // after setting this code
                // to OTP edittext field we
                // are calling our verifycode method.
                verifyCode(code);
            }
        }

        // this method is called when firebase doesn't
        // sends our OTP code due to any error or issue.
        @Override
        public void onVerificationFailed(FirebaseException e) {
            // displaying error message with firebase exception.
            Toast.makeText(FinalVerification.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    };


    private void verifyCode(String code) {
        // below line is used for getting
        // credentials from our verification id and code.
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);

        // after getting credential we are
        // calling sign in method.
        signInWithCredential(credential);
    }




 */






    /*



    private void sendverificationuser(String phoneno)
    {

                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+63" + phoneno,            // Phone number to verify
                          60, // Timeout and unit
                        TimeUnit.SECONDS,                 // Activity (for callback binding)
                         this ,
                        mCallbacks);// OnVerificationStateChangedCallbacks

    }



    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken)
        {
            super.onCodeSent(s, forceResendingToken);

            verificationcodebysystem = s;
        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential)
        {
            String code = phoneAuthCredential.getSmsCode();
            if(code!=null)
            {
                verifynum.setText(code);
                verifycode(code);
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e)
        {

            Toast.makeText(FinalVerification.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    };




    private void verifycode(String code)
    {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationcodebysystem,code);

        signinuserbycredential(credential);
    }

    private void signinuserbycredential(PhoneAuthCredential credential)
    {


       mAuth.signInWithCredential(credential)
                .addOnCompleteListener(FinalVerification.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {

                        if(task.isSuccessful())
                        {
                            Toast.makeText(FinalVerification.this, "Verification Complete", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(FinalVerification.this, AccountVerified.class);
                            startActivity(intent);

                        }
                        else
                        {
                            Toast.makeText(FinalVerification.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });




    }









 */





    public void gotoaccountverified (View view)
    {



        String email1=verifynum.getText().toString();
        String password1=verifynum2.getText().toString();

        // Sign in success, update UI with the signed-in user's information
        FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email1,password1)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        if(user.isEmailVerified())
                        {
                            Toast.makeText(FinalVerification.this, "Successfully Verified", Toast.LENGTH_SHORT).show();
                            verifynum.setText("");
                            verifynum2.setText("");
                             Intent intent = new Intent(FinalVerification.this,AccountVerified.class);
                              startActivity(intent);
                        }
                        else
                        {
                            user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>()
                            {
                                @Override
                                public void onSuccess(Void unused)
                                {
                                    Toast.makeText(FinalVerification.this, "Please Check your Email for Verification", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener()
                            {
                                @Override
                                public void onFailure(@NonNull Exception e)
                                {
                                   // Toast.makeText(FinalVerification.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    Toast.makeText(getApplicationContext(),"Please Check your Email for Verification",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });

    }


}