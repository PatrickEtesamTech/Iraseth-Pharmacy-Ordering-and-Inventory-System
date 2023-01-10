package com.example.irasethpharma;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Prevalent.Prevalent;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Orderstatus#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Orderstatus extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Orderstatus() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Orderstatus.
     */
    // TODO: Rename and change types and number of parameters
    public static Orderstatus newInstance(String param1, String param2) {
        Orderstatus fragment = new Orderstatus();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    private RecyclerView orderlist;

    Activity context;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_orderstatus, container, false);





        orderlist = view.findViewById(R.id.orderstatusrc1);

        orderlist.setLayoutManager(new LinearLayoutManager(getContext()));
        orderlist.setHasFixedSize(true);





        try
        {
            onStart();
        }
        catch(Exception error1)
        {
            Intent intent1 = new Intent(getActivity(),login.class);
            startActivity(intent1);
            throw error1;
        }













        // Inflate the layout for this fragment
        return view;
    }


    @Override
   public void onStart()
    {
        super.onStart();

        final DatabaseReference adminordersstatusref = FirebaseDatabase.getInstance().getReference().child("Delivery Information");



        FirebaseRecyclerOptions<AdminCustomerOrders> options
                = new FirebaseRecyclerOptions.Builder<AdminCustomerOrders>()
                .setQuery(adminordersstatusref.child("CustomerDeliveryInformation").child(Prevalent.currentonlineuser.getEmailaddress()),AdminCustomerOrders.class)
                .build();



        FirebaseRecyclerAdapter<AdminCustomerOrders,AdminOrdersViewHolder>adapter =
                new FirebaseRecyclerAdapter<AdminCustomerOrders,AdminOrdersViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull AdminOrdersViewHolder holder,int position, @NonNull AdminCustomerOrders model)
                    {

                        holder.txtdatefordelivery.setText(model.getDatefordelivery());
                        holder.txtorderid.setText(model.getOrderid());


                        if(model.getStatus() == "To Ship")
                        {
                            holder.txtstatus.setTextColor(Color.GRAY);
                            holder.txttoship.setText(model.getStatus());
                            holder.txttoship.setTextColor(Color.BLUE);
                            holder.txtdatefordelivery.setText(model.getDatefordelivery());
                            holder.txtorderid.setText(model.getOrderid());
                        }
                        else if(model.getStatus()  == "To Receive")
                        {
                            holder.txtstatus.setTextColor(Color.GRAY);
                            holder.txttoreceive.setText(model.getStatus());
                            holder.txttoreceive.setTextColor(Color.BLUE);
                            holder.txtdatefordelivery.setText(model.getDatefordelivery());
                            holder.txtorderid.setText(model.getOrderid());
                        }
                        else if (model.getStatus() == "To Rate")
                        {
                            holder.txtstatus.setTextColor(Color.GRAY);
                            holder.txttorate.setText(model.getStatus());
                            holder.txttorate.setTextColor(Color.BLUE);
                            holder.txtdatefordelivery.setText(model.getDatefordelivery());
                            holder.txtorderid.setText(model.getOrderid());

                            holder.toratebtn.setOnClickListener(new View.OnClickListener()
                            {
                                @Override
                                public void onClick(View v)
                                {

                                    adminordersstatusref.child("CustomerDeliveryInformation").child(Prevalent.currentonlineuser.getEmailaddress()).removeValue()
                                            .addOnCompleteListener(new OnCompleteListener<Void>()
                                            {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task)
                                                {
                                                    if(task.isSuccessful())
                                                    {
                                                        //Toast.makeText(getContext(), "Item Removed Successfully", Toast.LENGTH_SHORT).show();
                                                        Intent intent = new Intent(getActivity().getApplication(),Feedback.class);
                                                        startActivity(intent);

                                                    }
                                                }
                                            });


                                }
                            });


                        }
                        else
                        {
                           // Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                        }




                    }




                    @NonNull
                    @Override
                    public AdminOrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
                    {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_status_list,parent,false);

                        return new AdminOrdersViewHolder(view);
                    }
                };

        orderlist.setAdapter(adapter);
        adapter.startListening();










    }

    public static class AdminOrdersViewHolder extends RecyclerView.ViewHolder
    {

        public TextView txtbaranggay , txtaddress , txthouseno,txtcompanyname,txtdate,txtdatefordelivery,txtstatus ,txttime,txttotalprice,txtstreet,txtorderid,txttoship,txttoreceive,txttorate , toratebtn;

        public Button showorderscustomer ;

        public AdminOrdersViewHolder(@NonNull View itemView)
        {
            super(itemView);



            toratebtn = itemView.findViewById(R.id.torate);

            txtorderid = itemView.findViewById(R.id.textView33);
            txtstatus = itemView.findViewById(R.id.textView37);
            txttorate = itemView.findViewById(R.id.torate);
            txttoreceive = itemView.findViewById(R.id.toreceive);
            txttoship = itemView.findViewById(R.id.toship);

           // txtcompanyname= itemView.findViewById(R.id.textView53);
            // txthouseno = itemView.findViewById(R.id.textView51);
            //  txtstreet = itemView.findViewById(R.id.textView51);
            // txtbaranggay = itemView.findViewById(R.id.textView51);
           // txtaddress = itemView.findViewById(R.id.textView51);
            txtdatefordelivery = itemView.findViewById(R.id.textView35);
            //showorderscustomer = itemView.findViewById(R.id.adminuserviewproducts);
        }
    }



}