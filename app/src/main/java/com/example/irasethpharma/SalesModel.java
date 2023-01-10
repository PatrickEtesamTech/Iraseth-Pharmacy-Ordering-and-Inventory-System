package com.example.irasethpharma;

public class SalesModel
{

    String productprice1,productquantity1,productname1;
    long productimage1;


    public SalesModel()
    {

    }

    public SalesModel(long productimage1, String productprice1, String productquantity1, String productname1)
    {
        this.productimage1 = productimage1;
        this.productprice1 = productprice1;
        this.productquantity1 = productquantity1;
        this.productname1 = productname1;
    }

    public long getProductimage1() {
        return productimage1;
    }

    public void setProductimage1(long productimage1) {
        this.productimage1 = productimage1;
    }

    public String getProductprice1() {
        return productprice1;
    }

    public void setProductprice1(String productprice1) {
        this.productprice1 = productprice1;
    }

    public String getProductquantity1() {
        return productquantity1;
    }

    public void setProductquantity1(String productquantity1) {
        this.productquantity1 = productquantity1;
    }

    public String getProductname1() {
        return productname1;
    }

    public void setProductname1(String productname1) {
        this.productname1 = productname1;
    }
}
