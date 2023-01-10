package com.example.irasethpharma;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SupplierAdapter extends RecyclerView.Adapter<SupplierAdapter.SupplierViewHolder> {
    Context context;
    ArrayList<SupplierModel> supplierModels;

    public SupplierAdapter(Context context, ArrayList<SupplierModel> supplierModels) {
        this.context = context;
        this.supplierModels = supplierModels;
    }


    @NonNull
    @Override
    public SupplierViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.supplier_list, parent, false);
        SupplierAdapter.SupplierViewHolder supplierViewHolder = new SupplierAdapter.SupplierViewHolder(view);
        return supplierViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SupplierViewHolder holder, int position) {
        holder.cnum.setText(String.valueOf(supplierModels.get(position).getNum()));
        holder.comName.setText(supplierModels.get(position).getcName());
        holder.comContact.setText(supplierModels.get(position).getcContact());
        holder.comMail.setText(supplierModels.get(position).getcMail());
        holder.comAddress.setText(supplierModels.get(position).getcAddress());

        holder.email.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(v.getContext(), EmailActivity.class);
                v.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return supplierModels.size();
    }

    public class SupplierViewHolder extends RecyclerView.ViewHolder {
        TextView comName, comContact, comMail, comAddress, cnum;
        ImageView email;
        public SupplierViewHolder(@NonNull View itemView) {
            super(itemView);
            cnum = itemView.findViewById(R.id.supNum);
            comName = itemView.findViewById(R.id.companyName);
            comContact = itemView.findViewById(R.id.companyContact);
            comMail = itemView.findViewById(R.id.companyEmail);
            comAddress = itemView.findViewById(R.id.companyAddress);
            email = itemView.findViewById(R.id.imageView36);

        }
    }
}