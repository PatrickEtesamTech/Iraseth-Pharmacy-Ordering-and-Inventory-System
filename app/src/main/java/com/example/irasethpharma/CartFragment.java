package com.example.irasethpharma;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.common.internal.StringResourceValueReader;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseError;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import Prevalent.Prevalent;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
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

    int quantities;
    Activity context;
    Button checkout;
    String productq;
    TextView totaltxtview;
    ImageView deletebutton1,add,minus;
    Double totalprice = 0.0;
    RecyclerView recyclerView;
    FirebaseRecyclerAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);



        checkout = view.findViewById(R.id.checkoutBtn);

        totaltxtview = view.findViewById(R.id.totalprice);


        recyclerView = view.findViewById(R.id.carRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);




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









        return view;
    }









    @Override
    public void onStart()
    {
        super.onStart();


        final DatabaseReference cartlistref = FirebaseDatabase.getInstance().getReference().child("Cart List");

        FirebaseRecyclerOptions<Cartmodelfirebase> options = new FirebaseRecyclerOptions
                .Builder<Cartmodelfirebase>().setQuery(cartlistref.child("User View")
                .child(Prevalent.currentonlineuser.getEmailaddress()).child("Products"),Cartmodelfirebase.class).build();

        FirebaseRecyclerAdapter<Cartmodelfirebase,Cartviewholder>adapter = new FirebaseRecyclerAdapter<Cartmodelfirebase, Cartviewholder>(options)
        {

            @SuppressLint("ResourceType")
            @Override
            protected void onBindViewHolder(@NonNull Cartviewholder holder, int position, @NonNull Cartmodelfirebase model)
            {



                holder.txtproductname.setText(model.getProductname());
                holder.txtproductprice.setText(currencyFormat(model.getProductprice()));
                holder.txtproductquantity.setText(model.getProductquantity());
                holder.productimage.setImageResource(Integer.parseInt(model.getProductimage().toString()));
               // Picasso.get().load(model.getProductimage()).into(holder.productimage);

                Double productprice = ((Double.parseDouble(model.getProductprice()))) * Double.parseDouble(model.getProductquantity());

                totalprice = totalprice + productprice;

                totaltxtview.setText(currencyFormat(String.valueOf(totalprice)));

                checkout.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity().getApplication(),DeliveryInformation.class);
                        intent.putExtra("totalprice",String.valueOf(totalprice));
                        startActivity(intent);

                    }
                });



                holder.deletebutton.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        cartlistref.child("User View").child(Prevalent.currentonlineuser.getEmailaddress()).child("Products").child(model.getProductname()).removeValue()
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task)
                                    {
                                        if(task.isSuccessful())
                                        {
                                            Toast.makeText(getContext(), "Item Removed Successfully", Toast.LENGTH_SHORT).show();



                                            DatabaseReference stockproductref = FirebaseDatabase.getInstance().getReference().child("InventoryProducts");



                                            stockproductref.child("Stocks").addListenerForSingleValueEvent(new ValueEventListener()
                                            {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot snapshot)
                                                {

                                                    for (DataSnapshot child: snapshot.getChildren())
                                                    {

                                                        String stockdata = child.child("stock2").getValue().toString();
                                                        int total =  Integer.parseInt(stockdata) + Integer.parseInt(holder.txtproductquantity.getText().toString());
                                                        if(snapshot.hasChild(holder.txtproductname.getText().toString()))
                                                        {
                                                            stockproductref.child("Stocks").child(holder.txtproductname.getText().toString()).child("stock2").setValue(String.valueOf(total));
                                                        }


                                                    }

                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError error)
                                                {


                                                }
                                            });




                                        }
                                    }
                                });
                    }
                });


                holder.add.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {


                        ProductDetailFragment pdf = new ProductDetailFragment();


                        Intent intent = getActivity().getIntent();
                        intent.putExtra("pid",model.getProductname());


                        Fragment fragment = ProductDetailFragment.newInstance(model.getProductname(),Integer.parseInt(model.getProductimage().toString()),Double.parseDouble(model.getProductprice()), model.getProductbrand(),model.getProductmodel(),model.getProductdetail());

                       // Fragment fragment = new ProductDetailFragment();
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frameLayout, fragment);
                        transaction.commit();








                           // quantities = Integer.parseInt(holder.txtproductquantity.getText().toString());
                          //  quantities++;

                       // holder.txtproductquantity.setText(String.valueOf(quantities));


                        /**
                        ProductDetailFragment pdf = new ProductDetailFragment();

                        HashMap<String, Object> hashMap = new HashMap<>();

                        hashMap.put("productquantity",String.valueOf(quantities));

                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Cart List");

                        reference.child("User View").child(Prevalent.currentonlineuser.getEmailaddress())
                                .child(pdf.productid).updateChildren(hashMap)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        //update success

                                        Toast.makeText(getContext(), "Successfully Updated", Toast.LENGTH_SHORT).show();

                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                        Toast.makeText(getContext(), "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });

                         **/


                        //holder.txtproductquantity.setText(String.valueOf(quantities));
                    }
                });




            }

            @NonNull
            @Override
            public Cartviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
            {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_list,parent,false);
                Cartviewholder cartviewholder = new Cartviewholder(view);

                return cartviewholder;
            }

        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();



    }
    public static String currencyFormat(String amount)
    {
        DecimalFormat formatter = new DecimalFormat("###,###,##0.00");
        return formatter.format(Double.parseDouble(amount));
    }

    }

