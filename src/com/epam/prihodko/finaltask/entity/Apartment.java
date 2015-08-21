package com.epam.prihodko.finaltask.entity;


public class Apartment implements java.io.Serializable{
    private int id;
    private int price;
    private int couchette;
    private int roomNumber;
    private String status;
    private int classId;

    public Apartment() {}

    public Apartment(int id, int price, int couchette, int roomNumber) {
        this.id = id;
        this.price = price;
        this.couchette = couchette;
        this.roomNumber = roomNumber;
    }
    public Apartment(int couchette, int roomNumber, String status) {
        this.status = status;
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
    public String getStatus() {
        return status;
    }
    public int getClassId() {
        return classId;
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
    public void setStatus(String status) {
        this.status = status;
    }
    public void setClassId(int classId) {
        this.classId = classId;
    }

    @Override
    public String toString(){

        return  "<td>"+this.getId()+"</td>"+
                "<td>"+this.getPrice()+"</td>"+
                "<td>"+this.getRoomNumber()+"</td>"+
                "<td>"+this.getCouchette()+"</td>"+
                "<td>"+this.getStatus()+"</td>"+
                "<td>"+this.getClassId()+"</td>";
    }
}
