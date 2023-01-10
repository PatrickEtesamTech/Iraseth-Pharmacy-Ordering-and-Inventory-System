package com.example.irasethpharma;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

public class HomeCategoryModel{
    String categName;
    int categImage;

    public HomeCategoryModel(String categName, int categImage) {
        this.categName = categName;
        this.categImage = categImage;
    }


    public String getCategName() {
        return categName;
    }

    public void setCategName(String categName) {
        this.categName = categName;
    }

    public int getCategImage() {
        return categImage;
    }

    public void setCategImage(int categImage) {
        this.categImage = categImage;
    }
}
