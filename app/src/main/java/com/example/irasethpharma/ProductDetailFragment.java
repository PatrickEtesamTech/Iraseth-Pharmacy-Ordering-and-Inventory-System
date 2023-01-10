package com.example.irasethpharma;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

import Prevalent.Prevalent;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductDetailFragment extends Fragment {
    TextView pdname, pdPrice, quantitiy, pdBrand, pdModel, pdDetails ,stocks;
    ImageView pdImage, add, minus;
    Button addtocart;
    FirebaseDatabase rootNode;
    DatabaseReference dataref;
    String downloadimageurl;

    Uri imageuri;
    int arrayposition;
    int quantities = 1;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private static final String ARG_PARAM4 = "param4";
    private static final String ARG_PARAM5 = "param5";
    private static final String ARG_PARAM6 = "param6";



    // TODO: Rename and change types of parameters
    private String mParam1;
    private int mParam2;
    private double mParam3;
    private String mParam4;
    private String mParam5;
    private String mParam6;

    public ProductDetailFragment()
    {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment ProductDetailFragment.
     */
    // TODO: Rename and change types and number of parameters

    public static ProductDetailFragment newInstance(String param1, int param2, double param3,String param4, String param5, String param6)
    {
        ProductDetailFragment fragment = new ProductDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putInt(ARG_PARAM2, param2);
        args.putDouble(ARG_PARAM3, param3);
        args.putString(ARG_PARAM4, param4);
        args.putString(ARG_PARAM5, param5);
        args.putString(ARG_PARAM6, param6);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getInt(ARG_PARAM2);
            mParam3 = getArguments().getDouble(ARG_PARAM3);
            mParam4 = getArguments().getString(ARG_PARAM4);
            mParam5 = getArguments().getString(ARG_PARAM5);
            mParam6 = getArguments().getString(ARG_PARAM6);

        }
    }
    String productid = "";
  //  String productid1 = "";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_detail, container, false);
        pdname = view.findViewById(R.id.tvProdDetailsName);
        pdImage = view.findViewById(R.id.pdImageView);
        pdPrice = view.findViewById(R.id.tvpdPrice);
        stocks = view.findViewById(R.id.stocks);
        add = view.findViewById(R.id.plusBtn);
        minus = view.findViewById(R.id.minusBtn);
        quantitiy = view.findViewById(R.id.tvQuantity);
        addtocart = view.findViewById(R.id.addToCartBtn);
        pdBrand = view.findViewById(R.id.tvPBrand);
        pdModel = view.findViewById(R.id.tvPModel);
        pdDetails = view.findViewById(R.id.tvPDetails);


       // getstock();
       // stockdata();
       // stockdata();
       // livestockdata();

        pdname.setText(mParam1);
        pdImage.setImageResource(mParam2);
        pdPrice.setText(currencyFormat(String.valueOf(mParam3)));
        pdBrand.setText(mParam4);
        pdModel.setText(mParam5);
        pdDetails.setText(mParam6);
       // getstock();
       // getproductdetails(productid);
      //  updatestockdata();


        getstock();
       // updatestockdata();
       // stockdata();








        add.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                quantities++;
                quantitiy.setText(String.valueOf(quantities));
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(quantities > 1) {
                    quantities--;
                    quantitiy.setText(String.valueOf(quantities));
                }else{
                    Toast.makeText(getContext(), "Minimum Value is 1", Toast.LENGTH_SHORT).show();
                }
            }
        });




            addtocart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {


                    Intent intent = getActivity().getIntent();

                    productid = intent.getStringExtra("pid");
                    //  productid1 = intent.getStringExtra("pid1");



                        salesreport();
                        addtocartlist();
                        stockproducts();
                        stockquantity();



                       // stockquantity();
                    // getstock();
                    // stockdata();




                    Toast.makeText(getContext(), "Successfully Added to Cart", Toast.LENGTH_SHORT).show();
                    // intent = new Intent(getActivity().getApplication(),MainActivity.class);
                    // startActivity(intent);

                }
            });







        return view;
    }







    public void getproductdetails(String productid)
    {
        DatabaseReference productref = FirebaseDatabase.getInstance().getReference().child("Inventory");

        productref.child(productid).addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                if(snapshot.exists())
                {
                    InventoryModel inventoryModel = snapshot.getValue(InventoryModel.class);

                   // pdname.setText(productListModel.getProductName());
                  //  pdPrice.setText(String.valueOf(productListModel.getProductPrice()));
                    stocks.setText(String.valueOf(inventoryModel.getStock()));



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        });


    }



    public void livestockdata()
    {
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference friendsRef = rootRef.child("Inventory");
        ValueEventListener eventListener = new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                List<String> friends = new ArrayList<>();
                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    String friend = ds.getKey();
                    friends.add(friend);


                    Toast.makeText(getContext(), friends.toString(), Toast.LENGTH_SHORT).show();
                }
                Log.d("TAG", friends.toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        friendsRef.addListenerForSingleValueEvent(eventListener);
    }








    public void stockdata()
    {
        DatabaseReference databaseReference;

        ArrayList<String> list;
        databaseReference = FirebaseDatabase.getInstance().getReference("Inventory");

        list = new ArrayList<>();

        databaseReference.child("pushid").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                for (DataSnapshot child: snapshot.getChildren())
                {

                    String stockdata = child.child("stock").getValue().toString();


                    list.add(stockdata);


                }

                Toast.makeText(getContext(), String.valueOf(list), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        });
    }




    public void getstock()
    {



      //  InventoryProductAdapter inventoryProductAdapter = new InventoryProductAdapter();
        DatabaseReference productref = FirebaseDatabase.getInstance().getReference().child("Inventory");


        productref.child("pushid").addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {


                for (DataSnapshot child: snapshot.getChildren())
                {
                    String inventorydata = child.child("inventorydata").getValue().toString();
                    String stockdata = child.child("stock").getValue().toString();
                    if(inventorydata.equals(pdname.getText().toString()) && !stockdata.equals(stocks.getText().toString()))
                    {

                       stocks.setText(stockdata);
                    }
                    if(stockdata.equals("0") && stocks.getText().toString().equals("0"))
                    {
                        addtocart.setEnabled(false);
                    }
                    else
                    {
                        addtocart.setEnabled(true);
                    }


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        });

    }





    public void stockquantity()
    {

        DatabaseReference stockproductref = FirebaseDatabase.getInstance().getReference().child("InventoryProducts");


        int total = Integer.parseInt(stocks.getText().toString()) - Integer.parseInt(quantitiy.getText().toString())  ;

        HashMap<String,Object> cartmap = new HashMap<>();
        cartmap.put("stock2",String.valueOf(total));



        stockproductref.child("Stocks").child(productid)
                .updateChildren(cartmap)
                .addOnCompleteListener(new OnCompleteListener<Void>()
                {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if(task.isSuccessful())
                        {
                            stocks.setText(String.valueOf(total));
                            //  Toast.makeText(getContext(), "Successfully added to inventory report", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }











    private void salesreport()
    {

        DatabaseReference salesref = FirebaseDatabase.getInstance().getReference().child("Salesreport");


         HashMap<String,Object> cartmap = new HashMap<>();
        cartmap.put("productname1",pdname.getText().toString());
        cartmap.put("productprice1",String.valueOf(mParam3));
        cartmap.put("productquantity1",quantitiy.getText().toString());
        cartmap.put("productimage1", mParam2);


        salesref.child(productid)
                .updateChildren(cartmap)
                .addOnCompleteListener(new OnCompleteListener<Void>()
                {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if(task.isSuccessful())
                        {
                           // Toast.makeText(getContext(), "Successfully added to sales report", Toast.LENGTH_SHORT).show();

                        }
                    }
                });


    }






    private void stockproducts()
    {
         DatabaseReference stockproductref = FirebaseDatabase.getInstance().getReference().child("InventoryProducts");


         HashMap<String,Object> cartmap = new HashMap<>();
        cartmap.put("productname2",pdname.getText().toString());
        cartmap.put("stock2",stocks.getText().toString());
        cartmap.put("productprice2",String.valueOf(mParam3));
        cartmap.put("productimage2", mParam2);


        stockproductref.child("Stocks").child(productid)
                .updateChildren(cartmap)
                .addOnCompleteListener(new OnCompleteListener<Void>()
                {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if(task.isSuccessful())
                        {
                          //  Toast.makeText(getContext(), "Successfully added to inventory report", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }






    private void addtocartlist()
    {


        String savecurrenttime , savecurrentdate;
        Calendar date = Calendar.getInstance();
        SimpleDateFormat currentdate = new SimpleDateFormat("MMM dd,yyyy");
        savecurrentdate = currentdate.format(date.getTime());

        SimpleDateFormat currenttime = new SimpleDateFormat("HH:mm:ss");
        savecurrenttime = currenttime.format(date.getTime());




        DatabaseReference cartlistref = FirebaseDatabase.getInstance().getReference().child("Cart List");


        HashMap<String,Object> cartmap = new HashMap<>();
       cartmap.put("productname",pdname.getText().toString());
       cartmap.put("productprice",String.valueOf(mParam3));
       cartmap.put("productquantity",quantitiy.getText().toString());
        cartmap.put("productbrand",pdBrand.getText().toString());
        cartmap.put("productmodel",pdModel.getText().toString());
        cartmap.put("productdetail",pdDetails.getText().toString());
       cartmap.put("date", savecurrentdate);
       cartmap.put("time", savecurrenttime);
       cartmap.put("productimage", mParam2);




       cartlistref.child("User View").child(Prevalent.currentonlineuser.getEmailaddress()).child("Products").child(productid)
               .updateChildren(cartmap)
               .addOnCompleteListener(new OnCompleteListener<Void>()
               {
                   @Override
                   public void onComplete(@NonNull Task<Void> task)
                   {
                       if(task.isSuccessful())
                       {
                           cartlistref.child("Admin View").child(Prevalent.currentonlineuser.getEmailaddress())
                                   .child("Products").child(productid)
                                   .updateChildren(cartmap)
                                   .addOnCompleteListener(new OnCompleteListener<Void>()
                                   {
                                       @Override
                                       public void onComplete(@NonNull Task<Void> task)
                                       {
                                           if(task.isSuccessful())
                                           {
                                              // Toast.makeText(getContext(), "Added to Cart", Toast.LENGTH_SHORT).show();
                                           }
                                       }
                                   });
                       }
                   }
               });




        /**
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Cartlist");

        String productname = pdname.getText().toString();
        String productprice = pdPrice.getText().toString();
        String productquantity = quantitiy.getText().toString();

        Toast.makeText(getContext(), "Successfully Added to Cart", Toast.LENGTH_SHORT).show();

        ProductHelperClass helperClass = new ProductHelperClass(productname,productprice,productquantity);

        reference.setValue(helperClass);
         **/



    }


    public static String currencyFormat(String amount)
    {
        DecimalFormat formatter = new DecimalFormat("###,###,##0.00");
        return formatter.format(Double.parseDouble(amount));
    }
}