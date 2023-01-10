package com.example.irasethpharma;

import android.content.Context;
import android.content.Intent;
import android.text.style.TtsSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class HomeProductListAdapter extends RecyclerView.Adapter<HomeProductListAdapter.ProductViewHolder> {

    Context context;
    ArrayList<HomeProductListModel> productList;
    UpdateRecyclerView updateRecyclerView;

    public HomeProductListAdapter(Context context, ArrayList<HomeProductListModel> productList, UpdateRecyclerView updateRecyclerView) {
        this.context = context;
        this.productList = productList;
        this.updateRecyclerView = updateRecyclerView;

    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        ProductViewHolder productViewHolder = new ProductViewHolder(view);
        return productViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.productimage.setImageResource(productList.get(position).getProductImage());
        holder.productname.setText(productList.get(position).getProductName());
        holder.productPrice.setText("₱ " + currencyFormat(String.valueOf(productList.get(position).getProductPrice())));

        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                updateRecyclerView.itemClick(productList.get(holder.getAdapterPosition()));


            }
        });

        /**
         holder.pCardView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
        ProductDetailsFragment productDetailsFragment =  new ProductDetailsFragment();
        AppCompatActivity activity = (AppCompatActivity) view.getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, productDetailsFragment).addToBackStack(null).commit();
        }
        });
         **/
    }

    public static String currencyFormat(String amount)
    {
        DecimalFormat formatter = new DecimalFormat("###,###,##0.00");
        return formatter.format(Double.parseDouble(amount));
    }



    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView productname, productPrice;
        ImageView productimage;
        CardView pCardView;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productname = itemView.findViewById(R.id.listItemTextView);
            productimage = itemView.findViewById(R.id.listItemImageView);
            productPrice = itemView.findViewById(R.id.listItemPrice);
            pCardView = itemView.findViewById(R.id.listItemCardView);
        }
    }


}
