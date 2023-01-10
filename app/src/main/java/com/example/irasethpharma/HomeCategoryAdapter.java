package com.example.irasethpharma;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeCategoryAdapter extends RecyclerView.Adapter<HomeCategoryAdapter.CategViewHolder>{
    Context context;
    ArrayList<HomeCategoryModel> homeCategoryModels;
    UpdateRecyclerView updateRecyclerView;
    DatabaseReference databaseReference;
    Activity activity;
    boolean check = true;
    int indx;
    public HomeCategoryAdapter(Context context, ArrayList<HomeCategoryModel> homeCategoryModels, Activity activity, UpdateRecyclerView updateRecyclerView) {
        this.context = context;
        this.homeCategoryModels = homeCategoryModels;
        this.activity = activity;
        this.updateRecyclerView = updateRecyclerView;
    }

    @NonNull
    @Override
    public HomeCategoryAdapter.CategViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_category_items, parent, false);
        CategViewHolder categViewHolder = new CategViewHolder(view);
        return categViewHolder;
    }




    @Override
    public void onBindViewHolder(@NonNull HomeCategoryAdapter.CategViewHolder holder, int position)
    {


        ArrayList<String> list;

        DatabaseReference databaseReference;


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


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        });
















            holder.categImageView.setImageResource(homeCategoryModels.get(position).getCategImage());
        holder.categNameTextView.setText(homeCategoryModels.get(position).getCategName());
        if(check) {
            ArrayList<HomeProductListModel> productList = new ArrayList<>();
            for(int a = 0; a < HomeProductList.productNames.length; a++){
                productList.add(new HomeProductListModel(HomeProductList.productNames[a], HomeProductList.productImages[a], HomeProductList.productPrice[a], HomeProductList.productBrand[a], HomeProductList.getProductModel[a], HomeProductList.getProductDetails[a]));
            }
            updateRecyclerView.callback(productList);
            check = false;
        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                indx = holder.getAdapterPosition();
               // Toast.makeText(context, "Category "+indx, Toast.LENGTH_SHORT).show();
                if (indx == 0)
                {
                    // Toast.makeText(context.getApplicationContext(), String.valueOf(list), Toast.LENGTH_SHORT).show();



                    ArrayList<HomeProductListModel> productList = new ArrayList<HomeProductListModel>();
                    for (int a = 0; a < HomeProductList.productNames.length; a++) {
                        productList.add(new HomeProductListModel(HomeProductList.productNames[a], HomeProductList.productImages[a], HomeProductList.productPrice[a], HomeProductList.productBrand[a], HomeProductList.getProductModel[a], HomeProductList.getProductDetails[a]));

                    }
                    updateRecyclerView.callback(productList);
                }else if (indx == 1) {
                    int a = 0;
                    ArrayList<HomeProductListModel> productList = new ArrayList<>();
                    while (a < HomeProductList.radiologyItemsName.length){
                        productList.add(new HomeProductListModel(HomeProductList.radiologyItemsName[a], HomeProductList.radiologyItemsImage[a], HomeProductList.radiologyProductPrice[a], HomeProductList.radiologyBrand[a], HomeProductList.radiologyModel[a], HomeProductList.radiologyDetails[a]));

                        a++;
                    }
                    updateRecyclerView.callback(productList);
                }
                else if (indx == 2)
                {

                    int i = 0;
                    ArrayList<HomeProductListModel> productList = new ArrayList<>();
                    while (i < HomeProductList.furnitureItemsName.length){
                        productList.add(new HomeProductListModel(HomeProductList.furnitureItemsName[i], HomeProductList.furnitureItemsImage[i], HomeProductList.furnitureProductPrice[i], HomeProductList.furnitureProductBrand[i], HomeProductList.furnitureProductModel[i], HomeProductList.furnitureProductDetails[i]));
                        i++;
                    }
                    updateRecyclerView.callback(productList);
                }
                else if (indx == 3)
                {
                    String [] nurstock = new String[]{"1","2","3","4","5","6","7","8"};
                    ArrayList<HomeProductListModel> productList = new ArrayList<>();
                    for (int a = 0; a < HomeProductList.nurseryItemsName.length; a++) {
                        productList.add(new HomeProductListModel(HomeProductList.nurseryItemsName[a], HomeProductList.nurseryItemsImage[a], HomeProductList.nurseryProductPrice[a], HomeProductList.nurseryProductBrand[a], HomeProductList.nurseryProductModel[a], HomeProductList.nurseryProductDetails[a]));
                    }
                    updateRecyclerView.callback(productList);
                }else if (indx == 4) {
                    ArrayList<HomeProductListModel> productList = new ArrayList<>();
                    for (int a = 0; a < HomeProductList.laboratoryItemsName.length; a++) {
                        productList.add(new HomeProductListModel(HomeProductList.laboratoryItemsName[a], HomeProductList.laboratoryItemsImage[a], HomeProductList.laboratoryProductPrice[a], HomeProductList.laboratoryProductBrand[a], HomeProductList.laboratoryProductModel[a], HomeProductList.laboratoryProductDetails[a]));
                    }
                    updateRecyclerView.callback(productList);
                }
                else if (indx == 5)
                {
                    ArrayList<HomeProductListModel> productList = new ArrayList<>();
                    for (int a = 0; a < HomeProductList.consumablesItemsName.length; a++) {
                        productList.add(new HomeProductListModel(HomeProductList.consumablesItemsName[a], HomeProductList.consumablesItemsImage[a], HomeProductList.consumablesProductPrice[a], HomeProductList.consumablesProductBrand[a], HomeProductList.consumablesProductModel[a], HomeProductList.consumablesProductDetails[a]));
                    }
                    updateRecyclerView.callback(productList);
                }
            }

        });


    }

    @Override
    public int getItemCount() {
        return homeCategoryModels.size();
    }

    public static class CategViewHolder extends RecyclerView.ViewHolder {
        TextView categNameTextView;
        ImageView categImageView;
        CardView cardView;
        public CategViewHolder(@NonNull View itemView) {
            super(itemView);
            categNameTextView = itemView.findViewById(R.id.homeCategoryTextView);
            categImageView = itemView.findViewById(R.id.homeCategoryImageView);
            cardView = itemView.findViewById(R.id.categCardView);
        }
    }

    /**

     UpdateRecyclerView updateRecyclerView;
     Activity activity;
     boolean check = true;
     boolean select = true;


     public HomeCategoryAdapter(Context context, ArrayList arrayListProduct, ArrayList arrayListProductName, Activity activity, UpdateRecyclerView updateRecyclerView) {
     this.context = context;
     this.arrayListProduct = arrayListProduct;
     this.arrayListProductName = arrayListProductName;
     this.activity = activity;
     this.updateRecyclerView = updateRecyclerView;
     }

     @NonNull
     @Override
     public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
     View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_category_items, parent, false);
     CategViewHolder viewHolderClass = new CategViewHolder(view);
     return CategViewHolder;
     }

     @Override
     public void onBindViewHolder(@NonNull CategViewHolder holder, int position) {
     CategViewHolder viewHolderClass = (CategViewHolder) holder;
     viewHolderClass.productImage.setImageResource(HomeCategory.categoryImages[position]);
     viewHolderClass.productName.setText(HomeCategory.categoryNames[position]);
     viewHolderClass.itemView.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View view) {
     Toast.makeText(context, viewHolderClass.productName.getText().toString(), Toast.LENGTH_SHORT).show();
     }
     });
     }


     @Override
     public int getItemCount() {
     return arrayListProductName.size();
     }

     public class CategViewHolder extends RecyclerView.ViewHolder {
     TextView categNameTextView;
     ImageView categImageView;
     public CategViewHolder(@NonNull View itemView) {
     super(itemView);
     categNameTextView = itemView.findViewById(R.id.homeCategoryTextView);
     categImageView = itemView.findViewById(R.id.homeCategoryImageView);

     }


     }
     **/

}