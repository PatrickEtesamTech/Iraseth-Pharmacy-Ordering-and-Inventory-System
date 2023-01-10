package com.example.irasethpharma;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class InventoryviewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{

    public TextView txtproductname2, txtproductprice2,txtstock2 ;
    public ImageView txtproductimage2,inventadd,deleteproduct;
    private ItemClickListner itemClickListener;


    public InventoryviewHolder(@NonNull View itemView)
    {
        super(itemView);

        txtproductname2 = itemView.findViewById(R.id.inventoryPName);
        txtproductprice2 = itemView.findViewById(R.id.inventoryPPrice);
        txtstock2 = itemView.findViewById(R.id.inventStock);
        txtproductimage2 = itemView.findViewById(R.id.imgInventory);
        inventadd = itemView.findViewById(R.id.inventAdd);
        deleteproduct = itemView.findViewById(R.id.delete1);
       // instock = itemView.findViewById(R.id.imgInStock);
        //outstock = itemView.findViewById(R.id.imgOutStock);







    }


    @Override
    public void onClick(View v)
    { itemClickListener.onClick(v,getAdapterPosition(),false);}

    public void setItemClickListener(ItemClickListner itemClickListener)
    {
        this.itemClickListener = itemClickListener;
    }
}
