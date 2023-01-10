package com.example.irasethpharma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EmailActivity extends AppCompatActivity {

    EditText mailTo, mailSubject, mailMessage;
    Button sendBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        initMail();

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMail();
            }
        });
    }

    public void initMail(){
        mailTo = findViewById(R.id.sendTo);
        mailSubject = findViewById(R.id.emailSubject);
        mailMessage = findViewById(R.id.emailMessage);
        sendBtn = findViewById(R.id.sendEmailBtn);

    }

    public void sendMail(){
        String receipientList = mailTo.getText().toString();
        String[] reciepients = receipientList.split(",");

        String subject = mailSubject.getText().toString();
        String message = mailMessage.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, reciepients);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent,"Choose an email client"));


    }

    public void gotosupplier(View view)
    {
        Intent intent = new Intent(EmailActivity.this,SupplierDetails.class);
        startActivity(intent);
    }
}