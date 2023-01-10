package com.example.irasethpharma;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;


public class HomeFragment extends Fragment implements UpdateRecyclerView
{

    RecyclerView categRecyclerView, productRecyclerView;
    ArrayList<HomeCategoryModel> categoryItem;
    HomeCategoryAdapter homeCategoryAdapter;
    HomeProductListAdapter homeProductListAdapter;
    ImageView buttonlogin;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        categoryItem = new ArrayList<>();
        categRecyclerView = view.findViewById(R.id.homeCategoryRecyclerView);
        for (int i = 0; i < HomeCategory.categoryNames.length; i++)
        {
            categoryItem.add(new HomeCategoryModel(HomeCategory.categoryNames[i],HomeCategory.categoryImages[i]));
        }

        RecyclerView.LayoutManager layoutManager =  new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        categRecyclerView.setLayoutManager(layoutManager);
        homeCategoryAdapter =  new HomeCategoryAdapter(getContext(), categoryItem, getActivity(), this);
        categRecyclerView.setAdapter(homeCategoryAdapter);


        productRecyclerView = view.findViewById(R.id.ProductListRecyclerView);



        buttonlogin = view.findViewById(R.id.imageView8);

        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(),Aboutus.class);
                startActivity(intent);
            }
        });






        /**
         HomeCategoryAdapter homeCategoryAdapter = new HomeCategoryAdapter(categoryItem);
         LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
         categRecyclerView.setLayoutManager(linearLayoutManager);
         categRecyclerView.setAdapter(homeCategoryAdapter);

         **/
        /**
         productRecyclerView = view.findViewById(R.id.ProductListRecyclerView);
         productImage = new ArrayList();
         productName = new ArrayList();


         HomeProductListAdapter homeProductListAdapter = new HomeProductListAdapter(getContext(), productImage, productName);
         GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), GridLayoutManager.DEFAULT_SPAN_COUNT);
         productRecyclerView.setLayoutManager(gridLayoutManager);
         productRecyclerView.setAdapter(homeProductListAdapter);
         **/

        return view;
    }


    @Override
    public void callback(ArrayList<HomeProductListModel> productList) {
        RecyclerView.LayoutManager layoutManager1 =  new GridLayoutManager(getContext(), 2);
        productRecyclerView.setLayoutManager(layoutManager1);
        homeProductListAdapter = new HomeProductListAdapter(getContext(),productList, this);
        productRecyclerView.setAdapter(homeProductListAdapter);
    }




    @Override
    public void itemClick(HomeProductListModel dataModel)
    {
        Fragment fragment = ProductDetailFragment.newInstance(dataModel.getProductName(), dataModel.getProductImage(), dataModel.getProductPrice(), dataModel.getProductBrand(), dataModel.getProductModel(), dataModel.getProductDetails());
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        Intent intent = getActivity().getIntent();
        intent.putExtra("pid",dataModel.productName);
        transaction.replace(R.id.frameLayout, fragment);
        transaction.commit();
    }

    @Override
    public void onStart()
    {
        super.onStart();
    }
}