package com.example.irasethpharma;

import android.content.ClipData;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class Cartviewholder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView txtproductname, txtproductprice,txtproductquantity;
    public TextView add;
    public TextView txtuserproductname , txtuserproductprice  , txtuserproductquantity;
    public TextView txtproductname1, txtproductprice2,txtproductquantity3;
    public TextView txtproductnameinvoice, txtproductpriceinvoice,txtproductquantityinvoice;
    public ImageView productimage ,deletebutton ,productimage1 ,  txtuserproductimage;
    private ItemClickListner itemClickListener;





    public Cartviewholder(@NonNull View itemView)
    {
        super(itemView);

        txtuserproductname = itemView.findViewById(R.id.userproductname);
        txtuserproductprice = itemView.findViewById(R.id.userproductprice);
        txtuserproductquantity = itemView.findViewById(R.id.userproductquantity);
        txtuserproductimage = itemView.findViewById(R.id.userproductimage);

        txtproductname = itemView.findViewById(R.id.cartItemName);
        txtproductprice = itemView.findViewById(R.id.cartItemPrice);
        txtproductquantity = itemView.findViewById(R.id.tvCartQuantity);
        txtproductname1 = itemView.findViewById(R.id.checkoutItemName);
        txtproductprice2 = itemView.findViewById(R.id.checkoutPrice);
        txtproductquantity3 = itemView.findViewById(R.id.checkoutQuantity);
        productimage1 = itemView.findViewById(R.id.checkoutItemImage);
        productimage = itemView.findViewById(R.id.cartItemImage);
        txtproductpriceinvoice = itemView.findViewById(R.id.invoiceamount);
        txtproductnameinvoice = itemView.findViewById(R.id.invoicename);
        txtproductquantityinvoice = itemView.findViewById(R.id.invoiceqty);
        deletebutton = itemView.findViewById(R.id.deletebutton);
       // productimage2  = itemView.findViewById(R.id.cartItemImagess);
        add = itemView.findViewById(R.id.cartEdit);







    }


    @Override
    public void onClick(View v)
    {
        itemClickListener.onClick(v,getAdapterPosition(),false);
    }

    public void setItemClickListener(ItemClickListner itemClickListener)
    {
        this.itemClickListener = itemClickListener;
    }
}
