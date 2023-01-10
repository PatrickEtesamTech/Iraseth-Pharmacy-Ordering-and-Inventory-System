package com.example.irasethpharma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
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

public class DeliveryInformation extends AppCompatActivity {

    DatePickerDialog datePickerDialog;
    ImageView imgCal, back;
    EditText pickDate,edithouse,editbaranggay,editcity,editcompany,editstreet;
    String totalamount = "";
    RecyclerView recyclerView;
    String orderplaced = "Order Placed";
    String fname,lname;
    Button proceed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_information);
        initDate();
        imgCal = findViewById(R.id.btnCalendar);
        pickDate = findViewById(R.id.editDate);
        editbaranggay = findViewById(R.id.editBarangay);
        editstreet = findViewById(R.id.editStreet);
        editcompany = findViewById(R.id.editCompany);
        edithouse = findViewById(R.id.editHouseNo);
        editcity = findViewById(R.id.editCity);
        back = findViewById(R.id.backBtn);




        totalamount = getIntent().getStringExtra("totalprice");

      //  Toast.makeText(this, "totalprice is "+totalamount, Toast.LENGTH_SHORT).show();

        pickDate.setText(getTodaysDate());

        imgCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        proceed = findViewById(R.id.btnProceed);
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                check();
                Intent intent = new Intent(DeliveryInformation.this, Transaction.class);
                startActivity(intent);
            }
        });

    }

    private void check()
    {
        if(TextUtils.isEmpty(pickDate.getText().toString()))
        {
            Toast.makeText(this, "Please Provide the date of you delivery ", Toast.LENGTH_SHORT).show();
            edithouse.requestFocus();
        }
        else if (TextUtils.isEmpty(edithouse.getText().toString()))
        {
            Toast.makeText(this, "Please Provide the necessary Details ", Toast.LENGTH_SHORT).show();
            edithouse.setError("Field cannot be empty");
            edithouse.requestFocus();
        }
        else if (TextUtils.isEmpty(editcity.getText().toString()))
        {
            Toast.makeText(this, "Please Provide the necessary Details ", Toast.LENGTH_SHORT).show();
            editcity.setError("Field cannot be empty");
            editcity.requestFocus();
        }
        else if (TextUtils.isEmpty(editstreet.getText().toString()))
        {
            Toast.makeText(this, "Please Provide the necessary Details ", Toast.LENGTH_SHORT).show();
            editstreet.setError("Field cannot be empty");
            editstreet.requestFocus();
        }
        else if (TextUtils.isEmpty(editcompany.getText().toString()))
        {
            Toast.makeText(this, "Please Provide the necessary Details ", Toast.LENGTH_SHORT).show();
            editcompany.setError("Field cannot be empty");
            editcompany.requestFocus();
        }
        else if (TextUtils.isEmpty(editbaranggay.getText().toString()))
        {
            Toast.makeText(this, "Please Provide the necessary Details ", Toast.LENGTH_SHORT).show();
            editbaranggay.setError("Field cannot be empty");
            editbaranggay.requestFocus();
        }
        else
        {
            confirmorder();
            deliveryinformation();
            username();
        }

    }



    private void confirmorder()
    {




        final String savecurrenttime , savecurrentdate;
        Calendar date = Calendar.getInstance();
        SimpleDateFormat currentdate = new SimpleDateFormat("MMM dd,yyyy");
        savecurrentdate = currentdate.format(date.getTime());

        SimpleDateFormat currenttime = new SimpleDateFormat("HH:mm:ss");
        savecurrenttime = currenttime.format(date.getTime());


        final DatabaseReference ordersref = FirebaseDatabase.getInstance().getReference().child("Orders").child(Prevalent.currentonlineuser.getEmailaddress());





        HashMap<String,Object>ordersmap = new HashMap<>();
        ordersmap.put("Date", savecurrentdate);
        ordersmap.put("Time", savecurrenttime);
        ordersmap.put("Totalprice",totalamount);
        ordersmap.put("Datefordelivery",pickDate.getText().toString());
        ordersmap.put("Houseno",edithouse.getText().toString());
        ordersmap.put("Street", editstreet.getText().toString());
        ordersmap.put("Baranggay", editbaranggay.getText().toString());
        ordersmap.put("City", editcity.getText().toString());
        ordersmap.put("Companyname", editcompany.getText().toString());


       // ordersmap.put("Status", "Order Placed");





        ordersref.updateChildren(ordersmap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task)
            {
                if (task.isSuccessful())
                {

                  //  Toast.makeText(DeliveryInformation.this, "Your final order has been placed successfully", Toast.LENGTH_SHORT).show();

                    /**
                    FirebaseDatabase.getInstance().getReference()
                            .child("Cart List")
                            .child("User View")
                            .child(Prevalent.currentonlineuser.getEmailaddress())
                            .removeValue()
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task)
                                {
                                    if (task.isSuccessful())
                                    {
                                        Toast.makeText(DeliveryInformation.this, "Your final order has been placed successfully", Toast.LENGTH_SHORT).show();
                                       // Intent intent = new Intent(DeliveryInformation.this, MainActivity.class);
                                       // intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TASK|intent.FLAG_ACTIVITY_CLEAR_TASK);
                                       // startActivity(intent);
                                        //finish()

                                    }
                                }
                            });
                     **/
                }

            }
        });


    }


    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDate() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day, month, year);
                pickDate.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
    }
    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month) {
        if(month == 1)
            return "Jan";
        if(month == 2)
            return "Feb";
        if(month == 3)
            return "Mar";
        if(month == 4)
            return "Apr";
        if(month == 5)
            return "May";
        if(month == 6)
            return "Jun";
        if(month == 7)
            return "Jul";
        if(month == 8)
            return "Aug";
        if(month == 9)
            return "Sep";
        if(month == 10)
            return "Oct";
        if(month == 11)
            return "Nov";
        if(month == 12)
            return "Dec";

        return "Jan";
    }


    public void deliveryinformation()
    {




        final DatabaseReference deliveryinformationref = FirebaseDatabase.getInstance().getReference().child("Delivery Information");



        final HashMap<String,Object> cartmap = new HashMap<>();

        cartmap.put("Status", orderplaced);
        cartmap.put("Datefordelivery",pickDate.getText().toString());




        deliveryinformationref.child("CustomerDeliveryInformation").child(Prevalent.currentonlineuser.getEmailaddress()).child("Order Status")
                .updateChildren(cartmap)
                .addOnCompleteListener(new OnCompleteListener<Void>()
                {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if(task.isSuccessful())
                        {

                      //  Toast.makeText(DeliveryInformation.this, "Delivery Information Saved", Toast.LENGTH_SHORT).show();


                        }
                    }
                });



    }


    public void username()
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




                final DatabaseReference ordersref = FirebaseDatabase.getInstance().getReference().child("Orders").child(Prevalent.currentonlineuser.getEmailaddress());





                HashMap<String,Object>ordersmap = new HashMap<>();
                ordersmap.put("Customername",fname+" "+lname);


               // ordersmap.put("Status",orderplaced);



                // ordersmap.put("Status", "Order Placed");





                ordersref.updateChildren(ordersmap)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task)
                            {
                                if (task.isSuccessful())
                                {

                                 //   Toast.makeText(DeliveryInformation.this, "", Toast.LENGTH_SHORT).show();


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






}