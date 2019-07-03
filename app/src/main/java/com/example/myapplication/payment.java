package com.example.myapplication;

public class payment {
    private String cardNumber;
    private  String experid;

    public payment(String cardNumber, String experid) {
        this.cardNumber = cardNumber;
        this.experid = experid;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExperid() {
        return experid;
    }

    public void setExperid(String experid) {
        this.experid = experid;
    }
}
