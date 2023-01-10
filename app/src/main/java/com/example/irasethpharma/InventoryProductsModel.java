package com.example.irasethpharma;

public class InventoryProductsModel
{
    private String productname2, productprice2 ,stock2 ;
    private Long productimage2;


    public InventoryProductsModel()
    {

    }

    public InventoryProductsModel(String productname2, String productprice2, String stock2, Long productimage2) {
        this.productname2 = productname2;
        this.productprice2 = productprice2;
        this.stock2 = stock2;
        this.productimage2 = productimage2;
    }

    public String getProductname2() {
        return productname2;
    }

    public void setProductname2(String productname2) {
        this.productname2 = productname2;
    }

    public String getProductprice2() {
        return productprice2;
    }

    public void setProductprice2(String productprice2) {
        this.productprice2 = productprice2;
    }

    public String getStock2() {
        return stock2;
    }

    public void setStock2(String stock2) {
        this.stock2 = stock2;
    }

    public Long getProductimage2() {
        return productimage2;
    }

    public void setProductimage2(Long productimage2) {
        this.productimage2 = productimage2;
    }
}
