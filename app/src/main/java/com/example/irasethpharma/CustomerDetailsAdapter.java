package com.example.irasethpharma;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomerDetailsAdapter extends RecyclerView.Adapter<CustomerDetailsAdapter.Myviewholder>
{

    Context context;

    ArrayList<UserHelperClass>list;

    public CustomerDetailsAdapter(Context context,ArrayList<UserHelperClass>list)
    {
        this.context = context;
        this.list = list;
    }



    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(context).inflate(R.layout.customer_list,parent,false);

        return new Myviewholder(v);



    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder holder, int position)
    {

        UserHelperClass user = list.get(position);

        holder.txtfname.setText(user.getFirstname());
       // holder.txtmname.setText(user.getMiddlename());
        holder.txtlname.setText(user.getLastname());
        holder.txtcontact.setText(user.getContactnumber());
        holder.txtemail.setText(user.getEmailaddress());
        holder.txtpassword.setText(user.getPassword());
        holder.txtgender.setText(user.getGender());
        holder.txtemailaddress.setText(user.getGmailaddress());




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  static class Myviewholder extends  RecyclerView.ViewHolder
    {
        TextView txtfname , txtmname , txtlname , txtcontact , txtemail , txtpassword ,txtemailaddress ,txtgender;


        public Myviewholder(@NonNull View itemView)
        {
            super(itemView);

            txtfname = itemView.findViewById(R.id.textView42);
            txtlname = itemView.findViewById(R.id.textView60);
            txtcontact = itemView.findViewById(R.id.textView62);
            txtemail = itemView.findViewById(R.id.textView64);
            txtpassword = itemView.findViewById(R.id.textView66);
            txtemailaddress = itemView.findViewById(R.id.tveditaccusername);
            txtgender = itemView.findViewById(R.id.tveditaccgender);

        }
    }


}
