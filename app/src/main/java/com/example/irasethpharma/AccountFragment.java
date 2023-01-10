package com.example.irasethpharma;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

import Prevalent.Prevalent;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountFragment extends Fragment {

    Activity context;
    TextView accname, accmiddle, acclastname, acccontact, accemail, profileinfo,
            accpassword, acctitle,changePass, passSave ,tvSec,tvgender,tvdate,setsecurityquestion;
    Button btn, editBtn, logoutBtn;
    ImageView profileimage;
    ImageView fNameEdit, mNameEdit, lNameEdit, contactEdit, emailEdit, passEdit, visibilityPass, offVisisibilityPass, saveFname;
    EditText name, gender, lastname, contact, email, password,bdate;
    ConstraintLayout constraintLayout;
    DatabaseReference  usernameref;
    CardView cardView;
    boolean clicked=false;
    ImageView logout;
    String oldpassword;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AccountFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AccountFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AccountFragment newInstance(String param1, String param2) {
        AccountFragment fragment = new AccountFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    DatabaseReference reference;
    EditText displayusername;
    EditText displaygender;
    EditText displaybday;
    EditText displaylastname;
    EditText displaycontact;
    EditText displayemail;
    EditText displaygmail;
   // EditText displaypassword;
    String username,gender1,lastname1,contact1,email1,password1,bday1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        context = getActivity();
        View v = inflater.inflate(R.layout.fragment_account, container, false);


        logout = v.findViewById(R.id.btnLogOut);




        displayusername = v.findViewById(R.id.userName);
        displaygender = v.findViewById(R.id.userGender);
        displaylastname = v.findViewById(R.id.userLastName);
        displaycontact = v.findViewById(R.id.userContact);
        displayemail = v.findViewById(R.id.editUserName);
        displaybday = v.findViewById(R.id.userBdate);
        displaygmail = v.findViewById(R.id.userEmail);

        Intent intent = getActivity().getIntent();


         username = intent.getStringExtra("username");
         gender1 = intent.getStringExtra("gender");
         lastname1 = intent.getStringExtra("lastname");
         contact1 = intent.getStringExtra("contact");
         email1 = intent.getStringExtra("emailaddress");
        password1 = intent.getStringExtra("password");
        bday1 = intent.getStringExtra("birthdate");




      //  displayusername.setText(username);
//        displaygender.setText(gender1);
       // displaybday.setText(bday1);
      //  displaylastname.setText(lastname1);
      //  displaycontact.setText(contact1);
      //  displayemail.setText(email1);







        name = v.findViewById(R.id.userName);
       // middlename = v.findViewById(R.id.userMiddle);
        lastname = v.findViewById(R.id.userLastName);
        contact = v.findViewById(R.id.userContact);
        email = v.findViewById(R.id.userEmail);
        tvSec = v.findViewById(R.id.tvSetSecurityQuestion);
        tvgender = v.findViewById(R.id.tvGender);
        tvdate = v.findViewById(R.id.tvBdate);
        changePass = v.findViewById(R.id.tvChangePass);
       // password = v.findViewById(R.id.userPassword);
        accname = v.findViewById(R.id.accName);
      // accmiddle = v.findViewById(R.id.accMiddleName);
        acclastname = v.findViewById(R.id.accLastName);
        acccontact = v.findViewById(R.id.accContact);
        accemail = v.findViewById(R.id.accEmail);
       // accpassword = v.findViewById(R.id.accPassword);
        acctitle = v.findViewById(R.id.accountTitle);
        btn = (Button) v.findViewById(R.id.signUpOrSignIn);
        constraintLayout = v.findViewById(R.id.accConstraint);
        profileinfo = v.findViewById(R.id.Info);
        profileimage = v.findViewById(R.id.profImageView);
        cardView =  v.findViewById(R.id.materialCardView);





        fNameEdit = v.findViewById(R.id.editFirstName);
        setsecurityquestion = v.findViewById(R.id.tvSetSecurityQuestion);



        try
        {
            profileinformation();
        }
        catch(Exception error1)
        {
            Toast.makeText(context, "Please login to your account first", Toast.LENGTH_SHORT).show();
            Intent intent1 = new Intent(getActivity(),login.class);
            startActivity(intent1);
            throw error1;
        }


/*


        if(name.getText().toString().equals(""))
        {
            displaygender.setVisibility(View.INVISIBLE);
            displaybday.setVisibility(View.INVISIBLE);
            tvSec.setVisibility(View.INVISIBLE);
            tvgender.setVisibility(View.INVISIBLE);
            tvdate.setVisibility(View.INVISIBLE);
            acctitle.setVisibility(View.INVISIBLE);
            name.setVisibility(View.GONE);
            //  middlename.setVisibility(View.GONE);
            lastname.setVisibility(View.GONE);
            contact.setVisibility(View.GONE);
            email.setVisibility(View.GONE);
            logout.setVisibility(View.GONE);
            //  password.setVisibility(View.GONE);
            accname.setVisibility(View.GONE);
            //  accmiddle.setVisibility(View.GONE);
            acclastname.setVisibility(View.GONE);
            profileimage.setVisibility(View.GONE);
            acccontact.setVisibility(View.GONE);
            accemail.setVisibility(View.GONE);
            // accpassword.setVisibility(View.GONE);
            profileinfo.setVisibility(View.INVISIBLE);
            cardView.setVisibility(View.INVISIBLE);
            changePass.setVisibility(View.INVISIBLE);
            //  passSave.setVisibility(View.INVISIBLE);
            fNameEdit.setVisibility(View.INVISIBLE);
            // visibilityPass.setVisibility(View.INVISIBLE);
        }
        else
        {
            btn.setVisibility(View.INVISIBLE);
        }



 */

        //emailEdit = v.findViewById(R.id.editEmail);
       // passEdit = v.findViewById(R.id.editPassword);
     //  visibilityPass = v.findViewById(R.id.passVisibility);
      // offVisisibilityPass = v.findViewById(R.id.passVisibilityOff);
      //  saveFname = v.findViewById(R.id.fnameSave);
       // changePass = v.findViewById(R.id.tvChangePass);
       // passSave = v.findViewById(R.id.tvPassSave);

/*

        if(name.getText().toString().equals("") )
        {
            displaygender.setVisibility(View.INVISIBLE);
            displaybday.setVisibility(View.INVISIBLE);
            tvSec.setVisibility(View.INVISIBLE);
            tvgender.setVisibility(View.INVISIBLE);
            tvdate.setVisibility(View.INVISIBLE);
            acctitle.setVisibility(View.INVISIBLE);
            name.setVisibility(View.GONE);
         //  middlename.setVisibility(View.GONE);
            lastname.setVisibility(View.GONE);
            contact.setVisibility(View.GONE);
            email.setVisibility(View.GONE);
            logout.setVisibility(View.GONE);
         //  password.setVisibility(View.GONE);
            accname.setVisibility(View.GONE);
        //  accmiddle.setVisibility(View.GONE);
            acclastname.setVisibility(View.GONE);
            profileimage.setVisibility(View.GONE);
            acccontact.setVisibility(View.GONE);
            accemail.setVisibility(View.GONE);
        // accpassword.setVisibility(View.GONE);
            profileinfo.setVisibility(View.INVISIBLE);
            cardView.setVisibility(View.INVISIBLE);
            changePass.setVisibility(View.INVISIBLE);
          //  passSave.setVisibility(View.INVISIBLE);
            fNameEdit.setVisibility(View.INVISIBLE);
          // visibilityPass.setVisibility(View.INVISIBLE);


        }else
        {
            btn.setVisibility(View.INVISIBLE);

        }

 */

/*
        visibilityPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                visibilityPass.setVisibility(View.INVISIBLE);
                offVisisibilityPass.setVisibility(View.VISIBLE);
            }
        });



 */
 /*

        offVisisibilityPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
                offVisisibilityPass.setVisibility(View.INVISIBLE);
                visibilityPass.setVisibility(View.VISIBLE);
            }
        });


        

        fNameEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                name.setEnabled(true);
              //  middlename.setEnabled(true);
                lastname.setEnabled(true);
                contact.setEnabled(true);
                fNameEdit.setVisibility(View.INVISIBLE);
                saveFname.setVisibility(View.VISIBLE);
            }
        });

  */


        /*

        saveFname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                do
                {
                    Toast.makeText(context, "Data has been updated", Toast.LENGTH_LONG).show();
                }
                while (isfirstnamechange()  || islastnamechange()|| iscontactchange() || ismiddlenamechange() );



                name.setEnabled(false);
                middlename.setEnabled(false);
                lastname.setEnabled(false);
                contact.setEnabled(false);
                saveFname.setVisibility(View.INVISIBLE);
                fNameEdit.setVisibility(View.VISIBLE);
            }
        });





        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePass.setVisibility(View.INVISIBLE);
                passSave.setVisibility(View.VISIBLE);
                accpassword.setText("Enter New Password :");
                password.setEnabled(true);
                password.setText("");
            }
        });


         */

        fNameEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity().getApplication(), EditProfile.class);
                 intent.putExtra("email",email1);
                intent.putExtra("firstname",username);
                intent.putExtra("lastname", lastname1);
                intent.putExtra("gender", gender1);
                intent.putExtra("bday", bday1);
                intent.putExtra("contact", contact1);
                startActivity(intent);
            }
        });



        changePass.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
               // oldpassword = password.getText().toString();
                Intent intent = new Intent(getActivity().getApplication(), ChangePassword.class);
               // intent.putExtra("emailuser",email1);
                //intent.putExtra("keyOldPass", oldpassword);
                startActivity(intent);
            }
        });


        setsecurityquestion.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity().getApplication(), SetUpSecurityQuestion.class);
                intent.putExtra("check","settings");
                startActivity(intent);
            }
        });



 /*
        passSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(ispasswordchange())
                {
                    Toast.makeText(context, "Data has been updated", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(context, "Data is the same cannot be updated", Toast.LENGTH_SHORT).show();
                }


                passSave.setVisibility(View.INVISIBLE);
                changePass.setVisibility(View.VISIBLE);
                accpassword.setText("Password");
                password.setEnabled(false);
            }
        });


  */
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setTitle("Log Out");
                alert.setMessage("Are you sure you want to log out?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        Intent intent = new Intent(context, login.class);
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        Toast toast = Toast.makeText(context, "Welcome Back!!!", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER_HORIZONTAL| Gravity.CENTER,0 ,0);
                        toast.setDuration(toast.LENGTH_LONG);
                        toast.show();
                    }
                });
                alert.create().show();

               // Intent intent = new Intent(context, login.class);
               // startActivity(intent);
            }
        });



        return v;



    }


    public void profileinformation()
    {
        usernameref = FirebaseDatabase.getInstance().getReference().child("users");

        usernameref.child(Prevalent.currentonlineuser.getEmailaddress()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                Map<String,Object> map = (Map<String, Object>) snapshot.getValue();



                String fname = (String) map.get("firstname");
                String lname = (String) map.get("lastname");
                String bday = (String) map.get("birthdate");
                String gender = (String) map.get("gender");
                String contact1 = (String) map.get("contactnumber");
                String email1 = (String) map.get("emailaddress");
                String gmail1 = (String) map.get("gmailaddress");


                displayusername.setText(fname);
                displaygender.setText(gender);
                displaybday.setText(bday);
                displaylastname.setText(lname);
                displaycontact.setText(contact1);
                displayemail.setText(email1);
                displaygmail.setText(gmail1);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {


            }
        });

    }







/*
    private boolean iscontactchange()
    {
        if(!contact1.equals(displaycontact.getText().toString()))
        {
            reference.child(email1).child("contactnumber").setValue(displaycontact.getText().toString());
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
        if(!lastname1.equals(displaylastname.getText().toString()))
        {
            reference.child(email1).child("lastname").setValue(displaylastname.getText().toString());
            lastname1 = displaylastname.getText().toString();
            return true;
        }
        else
        {
            return false;
        }
    }


    private boolean ismiddlenamechange()
    {
        if(!middlename1.equals(displaymiddlename.getText().toString()))
        {
            reference.child(email1).child("middlename").setValue(displaymiddlename.getText().toString());
            middlename1 = displaymiddlename.getText().toString();
            return true;
        }
        else
        {
            return false;
        }
    }



    private boolean isfirstnamechange()
    {
        if(!username.equals(displayusername.getText().toString()))
        {
         reference.child(email1).child("firstname").setValue(displayusername.getText().toString());
         username = displayusername.getText().toString();
         return true;
        }
        else
        {
            return false;
        }
    }
    /*
    private boolean ispasswordchange()
    {
        if(!password1.equals(displaypassword.getText().toString()))
        {
            reference.child(email1).child("password").setValue(displaypassword.getText().toString());
            password1 = displaypassword.getText().toString();
            return true;
        }
        else
        {
            return false;
        }
    }


     */






    public void onStart(){
        super.onStart();
        Button btn = (Button) context.findViewById(R.id.signUpOrSignIn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                    Intent intent = new Intent(context, SignUp.class);
                    startActivity(intent);
            }
        });
    }
}