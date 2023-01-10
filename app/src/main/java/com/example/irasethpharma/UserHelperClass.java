package com.example.irasethpharma;

public class UserHelperClass
{

    String firstname,lastname,contactnumber,password,emailaddress,gender,birthdate,gmailaddress;

    public UserHelperClass()
    {

    }

    public UserHelperClass(String firstname, String lastname, String contactnumber, String password, String emailaddress,String gender,String birthdate ,String gmailaddress) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.birthdate = birthdate;
        this.contactnumber = contactnumber;
        this.password = password;
        this.emailaddress = emailaddress;
        this.gmailaddress = gmailaddress;
    }

    public String getGmailaddress() {
        return gmailaddress;
    }

    public void setGmailaddress(String gmailaddress) {
        this.gmailaddress = gmailaddress;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }


    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getContactnumber() {
        return contactnumber;
    }

    public void setContactnumber(String contactnumber) {
        this.contactnumber = contactnumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }
}
