package com.example.irasethpharma;

public class Cartmodelfirebase
{
    private String productname, productprice,productquantity , productbrand , productdetail , productmodel;
    private Long productimage;

    public Cartmodelfirebase()
    {

    }

    public Cartmodelfirebase(String productname, String productprice, String productquantity , Long productimage ,String productbrand ,String productmodel , String productdetail)
    {
        this.productmodel = productmodel;
        this.productdetail = productdetail;
        this.productbrand = productbrand;
        this.productimage = productimage;
        this.productname = productname;
        this.productprice = productprice;
        this.productquantity = productquantity;
    }

    public String getProductbrand() {
        return productbrand;
    }

    public void setProductbrand(String productbrand) {
        this.productbrand = productbrand;
    }

    public String getProductdetail() {
        return productdetail;
    }

    public void setProductdetail(String productdetail) {
        this.productdetail = productdetail;
    }

    public String getProductmodel() {
        return productmodel;
    }

    public void setProductmodel(String productmodel) {
        this.productmodel = productmodel;
    }

    public Long getProductimage() {
        return productimage;
    }

    public void setProductimage(Long productimage) {
        this.productimage = productimage;
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

    public void setProductquantity(String productquantity) {
        this.productquantity = productquantity;
    }
}
