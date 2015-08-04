package com.epam.prihodko.finaltask.domain;

public class Order implements java.io.Serializable{
    private int id=0;
    private int couchette;
    private int room_number;
    private String date_in;
    private String date_out;

    public Order(){}


    public void setId(int id) {
        this.id = id;
    }
    public void setCouchette(int couchette) {
        this.couchette = couchette;
    }
    public void setRoom_number(int room_number) {
        this.room_number = room_number;
    }
    public void setDate_in(String date_in) {
        this.date_in = date_in;
    }
    public void setDate_out(String date_out) {
        this.date_out = date_out;
    }



    public int getId() {
        return id;
    }
    public int getCouchette() {
        return couchette;
    }
    public int getRoom_number() {
        return room_number;
    }
    public String getDate_in() {
        return date_in;
    }
    public String getDate_out() {
        return date_out;
    }
}
