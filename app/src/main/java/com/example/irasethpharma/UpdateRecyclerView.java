package com.example.irasethpharma;

import java.util.ArrayList;

public interface UpdateRecyclerView {
    void callback(ArrayList<HomeProductListModel> productList);
    void itemClick(HomeProductListModel dataModel);
}