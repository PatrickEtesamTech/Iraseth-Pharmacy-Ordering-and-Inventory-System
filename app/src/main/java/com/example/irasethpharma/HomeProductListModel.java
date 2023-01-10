package com.example.irasethpharma;
import android.graphics.drawable.Drawable;

public class HomeProductListModel {
    String productName;
    int productImage;
    double productPrice;
    String productBrand, productModel, productDetails ;


    public HomeProductListModel(String productName, int productImage, double productPrice, String productBrand, String productModel, String productDetails) {
        this.productName = productName;
        this.productImage = productImage;
        this.productPrice = productPrice;
        this.productBrand = productBrand;
        this.productModel = productModel;
        this.productDetails = productDetails;

    }

    public HomeProductListModel()
    {

    }



    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getProductModel() {
        return productModel;
    }

    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }

    public String getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(String productDetails) {
        this.productDetails = productDetails;
    }
}