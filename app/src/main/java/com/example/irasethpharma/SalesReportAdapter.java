package com.example.irasethpharma;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SalesReportAdapter extends RecyclerView.Adapter<SalesReportAdapter.Myviewholder>
{

    Context context;
    Double totalprice = 0.0;

    ArrayList<SalesModel>list;

    public SalesReportAdapter(Context context,ArrayList<SalesModel>list)
    {
        this.context = context;
        this.list = list;
    }



    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(context).inflate(R.layout.sales_item_list,parent,false);

        return new Myviewholder(v);



    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder holder, int position)
    {

        SalesModel user = list.get(position);

        holder.txtpname.setText(user.getProductname1());
        holder.txtquantity.setText(user.getProductquantity1());

       // Double productprice = ((Double.parseDouble(user.getProductprice1()))) * Double.parseDouble(user.getProductquantity1());


       // totalprice = totalprice + productprice;



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  static class Myviewholder extends  RecyclerView.ViewHolder
    {
        TextView txtpname , txtquantity ;


        public Myviewholder(@NonNull View itemView)
        {
            super(itemView);

            txtpname = itemView.findViewById(R.id.saleProdName);
            txtquantity = itemView.findViewById(R.id.saleQuantity);
           // totalsales = itemView.findViewById(R.id.totalsales1);

        }
    }


}

