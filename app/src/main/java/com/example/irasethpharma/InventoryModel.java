package com.example.irasethpharma;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

public class InventoryModel
{
    String stock;
    public InventoryModel()
    {

    }

    public InventoryModel(String stock)
    {
        this.stock = stock;

    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }
}
