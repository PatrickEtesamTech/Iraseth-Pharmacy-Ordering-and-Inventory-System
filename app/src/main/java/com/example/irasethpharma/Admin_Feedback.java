package com.example.irasethpharma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Admin_Feedback extends AppCompatActivity
{

    RecyclerView feedbackdetails;
    DatabaseReference databaseReference;
    FeedbackAdapter myadapter;
    ArrayList<Feedback_Model> list;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_feedback);




        feedbackdetails = findViewById(R.id.fbRecycler);
        databaseReference = FirebaseDatabase.getInstance().getReference("Feedbacks");

        feedbackdetails.setHasFixedSize(true);

        feedbackdetails.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();

        myadapter = new FeedbackAdapter(this,list);

        feedbackdetails.setAdapter(myadapter);


        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    Feedback_Model user = dataSnapshot.getValue(Feedback_Model.class);

                    list.add(user);
                }

                myadapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        });






    }
    public void gotoadminmain(View view)
    {
        Intent intent = new Intent(Admin_Feedback.this,AdminActivity.class);
        startActivity(intent);
    }
}