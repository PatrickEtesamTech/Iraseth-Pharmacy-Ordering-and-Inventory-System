package com.example.irasethpharma;

public class SupplierModel {
    int num;
    String cName;
    String cContact;
    String cMail;
    String cAddress;


    public SupplierModel(int num, String cName, String cContact, String cMail, String cAddress) {
        this.num = num;
        this.cName = cName;
        this.cContact = cContact;
        this.cMail = cMail;
        this.cAddress = cAddress;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcContact() {
        return cContact;
    }

    public void setcContact(String cContact) {
        this.cContact = cContact;
    }

    public String getcMail() {
        return cMail;
    }

    public void setcMail(String cMail) {
        this.cMail = cMail;
    }

    public String getcAddress() {
        return cAddress;
    }

    public void setcAddress(String cAddress) {
        this.cAddress = cAddress;
    }
}