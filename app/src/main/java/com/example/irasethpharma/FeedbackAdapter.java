package com.example.irasethpharma;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.Myviewholder>
{


    Context context;

    ArrayList<Feedback_Model>list;

    public FeedbackAdapter(Context context,ArrayList<Feedback_Model>list)
    {
        this.context = context;
        this.list = list;
    }



    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(context).inflate(R.layout.admin_feedback_list,parent,false);

        return new Myviewholder(v);



    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder holder, int position)
    {

        Feedback_Model user = list.get(position);

        holder.txtfeedback.setText(user.getFeedback());
        holder.rtrating.setRating(Integer.parseInt(String.valueOf(user.getRating())));
        holder.txtcustomername.setText(user.getCustomername());
        holder.txtorderid.setText(user.getOrderid());




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  static class Myviewholder extends  RecyclerView.ViewHolder
    {
        EditText txtfeedback;
        RatingBar rtrating;
        TextView txtcustomername , txtorderid;


        public Myviewholder(@NonNull View itemView)
        {
            super(itemView);

           txtfeedback = itemView.findViewById(R.id.editTextTextMultiLine2);
            rtrating = itemView.findViewById(R.id.adminrating);

            txtcustomername = itemView.findViewById(R.id.adminFbName);
            txtorderid = itemView.findViewById(R.id.adminFbOrderID);
        }
    }

}
