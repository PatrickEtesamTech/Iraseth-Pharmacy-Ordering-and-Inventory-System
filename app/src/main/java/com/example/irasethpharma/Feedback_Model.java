package com.example.irasethpharma;

public class Feedback_Model
{
    String Feedback,Customername ,Orderid;
    Long Rating;


    public Feedback_Model()
    {

    }


    public Feedback_Model(String Feedback, long Rating , String Customername , String Orderid)
    {

        this.Feedback = Feedback;
        this.Rating = Rating;
        this.Customername = Customername;
        this.Orderid = Orderid;

    }

    public String getOrderid() {
        return Orderid;
    }

    public void setOrderid(String orderid) {
        Orderid = orderid;
    }

    public String getCustomername() {
        return Customername;
    }

    public void setCustomername(String customername) {
        Customername = customername;
    }

    public String getFeedback() {
        return Feedback;
    }

    public void setFeedback(String feedback) {
        Feedback = feedback;
    }

    public long getRating() {
        return Rating;
    }

    public void setRating(long rating) {
        Rating = rating;
    }
}
