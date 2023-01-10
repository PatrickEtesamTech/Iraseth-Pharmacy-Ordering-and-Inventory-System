package com.example.irasethpharma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class SignUp extends AppCompatActivity {

    TextView haveAcc;
    Button regacc;
    EditText regfirstname,regsurname,reggender,regemail,regcontact,regpassword,regbday,reggmail;
    FirebaseDatabase rootNode;
    DatabaseReference reference ;
    ImageView calendar;
    DatePickerDialog datePickerDialog;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);



        calendar = findViewById(R.id.btnCalendar1);
        regfirstname = findViewById(R.id.firstName1);
        regsurname = findViewById(R.id.lastName1);
        reggender = findViewById(R.id.Gender);
        regbday = findViewById(R.id.bdate);
        regemail = findViewById(R.id.emailAddress1);
        regpassword = findViewById(R.id.password1);
        regcontact = findViewById(R.id.number1);
        regacc = findViewById(R.id.CreateAccount);
        reggmail = findViewById(R.id.gmail1);


        initDate1();
        regbday.setText(getTodaysDate1());

        calendar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                datePickerDialog.show();
            }
        });



        regacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                if(!validatename() || !validatesurname() || !validategender() || !validategmail() || !validatepass()|| !validateusername() || !validatenumber())
                {
                    return;
                }




                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("users");




                String firstname = regfirstname.getText().toString();
                String lastname = regsurname.getText().toString();
                String email = regemail.getText().toString();
                String number =  regcontact.getText().toString();
                String password = regpassword.getText().toString();
                String gender = reggender.getText().toString();
                String bday = regbday.getText().toString();
                String gmail = reggmail.getText().toString();



                UserHelperClass helperClass = new UserHelperClass(firstname,lastname,number,password,email,gender,bday,gmail);
                // reference.setValue(helperClass);
              reference.child(email).setValue(helperClass);






                mAuth = FirebaseAuth.getInstance();

                mAuth.createUserWithEmailAndPassword(gmail, password)
                        .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>()
                        {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful())
                                {

                                    regfirstname.setText("");
                                    regsurname.setText("");
                                    regemail.setText("");
                                    regcontact.setText("");
                                    regbday.setText("");
                                    reggender.setText("");
                                    regpassword.setText("");
                                    reggmail.setText("");

                                    Toast.makeText(SignUp.this, "Successfully Created Account", Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(getApplicationContext(), FinalVerification.class);
                                    // intent.putExtra("phone",number);
                                    intent.putExtra("pass",password);
                                    intent.putExtra("email",gmail);
                                    startActivity(intent);
                                   // Toast.makeText(getApplicationContext(),"Email/Password Created",Toast.LENGTH_SHORT).show();

                                }
                                else
                                {


                                    Toast.makeText(getApplicationContext(),"Email/Password Already Exist",Toast.LENGTH_SHORT).show();

                                    // If sign in fails, display a message to the user.

                                }


                            }
                        });












            }
        });



        haveAcc = findViewById(R.id.alreadyHave);
        allreadyHaveAccOnClick();

    }

    private String getTodaysDate1() {
        Calendar cal = Calendar.getInstance();
        int year1 = cal.get(Calendar.YEAR);
        int month1 = cal.get(Calendar.MONTH);
        month1 = month1 + 1;
        int day1 = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString1(day1, month1, year1);
    }

    private void initDate1() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date1 = makeDateString1(day, month, year);
                regbday.setText(date1);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
    }
    private String makeDateString1(int day1, int month1, int year1) {
        return getMonthFormat1(month1) + " " + day1 + " " + year1;
    }

    private String getMonthFormat1(int month1) {
        if(month1 == 1)
            return "Jan";
        if(month1 == 2)
            return "Feb";
        if(month1 == 3)
            return "Mar";
        if(month1 == 4)
            return "Apr";
        if(month1 == 5)
            return "May";
        if(month1 == 6)
            return "Jun";
        if(month1 == 7)
            return "Jul";
        if(month1 == 8)
            return "Aug";
        if(month1 == 9)
            return "Sep";
        if(month1 == 10)
            return "Oct";
        if(month1 == 11)
            return "Nov";
        if(month1 == 12)
            return "Dec";

        return "Jan";
    }


    private Boolean validatename()
    {
        String val = regfirstname.getText().toString();

        if(val.isEmpty())
        {
            regfirstname.setError("Field cannot be empty");
            return false;
        }
        else
        {
            regfirstname.setError(null);
            return true;
        }
    }

    private Boolean validatesurname()
    {
        String val = regsurname.getText().toString();

        if(val.isEmpty())
        {
            regsurname.setError("Field cannot be empty");
            return false;
        }
        else
        {
            regsurname.setError(null);
            return true;
        }
    }

    private Boolean validateusername()
    {
        String val = regemail.getText().toString();

        if(val.isEmpty())
        {
            regemail.setError("Field cannot be empty");
            return false;
        }
        else if (val.length()>=15)
        {
            regemail.setError("Username too long");
            return false;
        }
        else
        {
            regemail.setError(null);
            return true;
        }
    }

    private Boolean validatenumber()
    {
        String val = regcontact.getText().toString();

        if(val.isEmpty())
        {
            regcontact.setError("Field cannot be empty");
            return false;
        }
        else if (val.length()>11)
        {
            regcontact.setError("Please enter 11 Digits");
            return false;
        }
        else
        {
            regcontact.setError(null);
            return true;
        }
    }

    private Boolean validategender()
    {
        String val = reggender.getText().toString();

        if(val.isEmpty())
        {
            reggender.setError("Field cannot be empty");
            return false;
        }
        else
        {
            reggender.setError(null);
            return true;
        }
    }

    private Boolean validategmail()
    {
        String val = reggmail.getText().toString();

        if(val.isEmpty())
        {
            reggmail.setError("Field cannot be empty");
            return false;
        }
        else
        {
            reggmail.setError(null);
            return true;
        }
    }

    private Boolean validatepass()
    {
        String val = regpassword.getText().toString();

        if(val.isEmpty())
        {
            regpassword.setError("Field cannot be empty");
            return false;
        }
        else if (val.length()<6)
        {
            regpassword.setError("Please enter more then 6 characters");
            return false;
        }
        else
        {
            regpassword.setError(null);
            return true;
        }
    }













    private void allreadyHaveAccOnClick()
    {
        haveAcc.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
             //  Intent intent = new Intent(SignUp.this, admincreateacc.class);
                Intent intent = new Intent(SignUp.this, login.class);
                startActivity(intent);
            }
        });
    }
}