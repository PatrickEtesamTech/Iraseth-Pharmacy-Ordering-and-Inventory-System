package com.example.irasethpharma;

public class ProductHelperClass
{

    String productname , productprice , productquantity , productid;

    public ProductHelperClass()
    {

    }

    public ProductHelperClass(String productname, String productprice, String productquantity ,String productid) {
        this.productname = productname;
        this.productid = productid;
        this.productprice = productprice;
        this.productquantity = productquantity;

    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productname = productid;
    }


    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }


    public String getProductprice() {
        return productprice;
    }

    public void setProductprice(String productprice) {
        this.productprice = productprice;
    }


    public String getProductquantity() {
        return productquantity;
    }

    public void setProductquantity(String productquantity) {this.productquantity = productquantity;}



}


