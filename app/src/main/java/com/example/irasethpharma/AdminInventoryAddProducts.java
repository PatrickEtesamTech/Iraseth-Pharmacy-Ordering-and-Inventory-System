package com.example.irasethpharma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class AdminInventoryAddProducts extends AppCompatActivity
{


    Button add;
    EditText pname,pbrand,pmodel,pprice,pstock,pdetail,pstock1;
    ImageView pimage;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_inventory_add_products);

        pname = findViewById(R.id.tvProdDetailsName1);
        pprice = findViewById(R.id.tvpdPrice1);
        pstock = findViewById(R.id.stocks1);
        pimage = findViewById(R.id.pdImageView1);
        pimage.setTag(R.drawable.categ_hospitalfurniture);


        Integer resource = (Integer)pimage.getTag();

        add = findViewById(R.id.darwin);


        add.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {


                DatabaseReference stockproductref = FirebaseDatabase.getInstance().getReference().child("InventoryProducts");



                stockproductref.child("Stocks").addListenerForSingleValueEvent(new ValueEventListener()
                {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot)
                    {

                        for (DataSnapshot child: snapshot.getChildren())
                        {

                            String productnamethis = child.child("productname2").getValue().toString();



                            if(!productnamethis.equals(pname.getText().toString()))
                            {
                                HashMap<String,Object> cartmap = new HashMap<>();
                                cartmap.put("productname2",pname.getText().toString());
                                cartmap.put("stock2",pstock.getText().toString());
                                cartmap.put("productprice2",pprice.getText().toString());
                                cartmap.put("productimage2", Integer.parseInt(String.valueOf(resource)));


                                stockproductref.child("Stocks").child(pname.getText().toString())
                                        .updateChildren(cartmap)
                                        .addOnCompleteListener(new OnCompleteListener<Void>()
                                        {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task)
                                            {
                                                if(task.isSuccessful())
                                                {
                                                      Toast.makeText(AdminInventoryAddProducts.this, "Successfully added a new Product", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });



                            }




                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error)
                    {


                    }
                });

            }
        });







    }

    public void gotoinven(View view)
    {
        Intent intent = new Intent(AdminInventoryAddProducts.this,InventoryActivity.class);
        startActivity(intent);
    }


}