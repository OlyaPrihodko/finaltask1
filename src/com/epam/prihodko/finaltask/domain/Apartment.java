package com.epam.prihodko.finaltask.domain;

public class Apartment implements java.io.Serializable{
    private int id=0;
    private int price;
    private int couchette;
    private int roomNumber;

    public Apartment() {}

    public Apartment(int id, int price, int couchette, int roomNumber) {
        this.id = id;
        this.price = price;
        this.couchette = couchette;
        this.roomNumber = roomNumber;
    }

    public int getId() {
        return id;
    }
    public int getPrice() {
        return price;
    }
    public int getCouchette() {
        return couchette;
    }
    public int getRoomNumber() {
        return roomNumber;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public void setCouchette(int couchette) {
        this.couchette = couchette;
    }
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
}
