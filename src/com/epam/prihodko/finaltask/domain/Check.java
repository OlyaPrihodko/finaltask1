package com.epam.prihodko.finaltask.domain;

public class Check implements java.io.Serializable{
    private int id=0;
    private int price;
    public Check(){}


    public void setId(int id) {
        this.id = id;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }
    public int getPrice() {
        return price;
    }

}
