package com.example.irasethpharma;

public class AdminCustomerOrders
{
    private String Baranggay , City , Companyname, Date , Datefordelivery , Houseno , Orderid , Status , Street , Time , Totalprice , emailaddress ,Customername ;

    public AdminCustomerOrders()
    {

    }



    public AdminCustomerOrders(String baranggay, String city, String companyname, String date, String datefordelivery, String houseno, String orderid, String status, String street, String time, String totalprice , String Emailaddress,String customername) {
        Baranggay = baranggay;
        Customername = customername;
        City = city;
        Companyname = companyname;
        Date = date;
        Datefordelivery = datefordelivery;
        Houseno = houseno;
        Orderid = orderid;
        Status = status;
        Street = street;
        Time = time;
        Totalprice = totalprice;
        emailaddress = Emailaddress;
    }


    public String getCustomername() {
        return Customername;
    }

    public void setCustomername(String customername) {
        Customername = customername;
    }

    public String getEmailaddress()
    {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress)
    {
        this.emailaddress = emailaddress;
    }

    public String getBaranggay() {
        return Baranggay;
    }

    public void setBaranggay(String baranggay) {
        Baranggay = baranggay;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getCompanyname() {
        return Companyname;
    }

    public void setCompanyname(String companyname) {
        Companyname = companyname;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getDatefordelivery() {
        return Datefordelivery;
    }

    public void setDatefordelivery(String datefordelivery) {
        Datefordelivery = datefordelivery;
    }

    public String getHouseno() {
        return Houseno;
    }

    public void setHouseno(String houseno) {
        Houseno = houseno;
    }

    public String getOrderid() {
        return Orderid;
    }

    public void setOrderid(String orderid) {
        Orderid = orderid;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getTotalprice() {
        return Totalprice;
    }

    public void setTotalprice(String totalprice) {
        Totalprice = totalprice;
    }
}
