package com.epam.prihodko.finaltask.domain;

public class Order implements java.io.Serializable{
    private int id=0;
    private String apartmentClass;
    private int couchette;
    private int roomNumber;
    private String date_in;
    private String date_out;
    private String status;
    private int personId;
    private int checkId;

    public Order(){}


    public Order( String apartmentClass,int roomNumber,int couchette, String date_in, String date_out, String status) {

        this.couchette = couchette;
        this.date_in = date_in;
        this.date_out = date_out;
        this.apartmentClass = apartmentClass;
        this.roomNumber = roomNumber;
        this.status = status;
    }

    public Order(int id, String apartmentClass,int roomNumber,int couchette, String date_in, String date_out, String status) {
        this.id=id;
        this.couchette = couchette;
        this.date_in = date_in;
        this.date_out = date_out;
        this.apartmentClass = apartmentClass;
        this.roomNumber = roomNumber;
        this.status = status;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setCouchette(int couchette) {
        this.couchette = couchette;
    }
    public void setRoomNumber(int room_number) {
        this.roomNumber = room_number;
    }
    public void setDate_in(String date_in) {
        this.date_in = date_in;
    }
    public void setDate_out(String date_out) {
        this.date_out = date_out;
    }
    public void setApartmentClass(String apartmentClass) {
        this.apartmentClass = apartmentClass;
    }
    public void setPersonId(int personId) {
        this.personId = personId;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setCheckId(int checkId) {
        this.checkId = checkId;
    }

    public int getId() {
        return id;
    }
    public int getCouchette() {
        return couchette;
    }
    public int getRoomNumber() {
        return roomNumber;
    }
    public String getDate_in() {
        return date_in;
    }
    public String getDate_out() {
        return date_out;
    }
    public String getApartmentClass() {
        return apartmentClass;
    }
    public int getPersonId() {
        return personId;
    }
    public String getStatus() {
        return status;
    }
    public int getCheckId() {
        return checkId;
    }

    @Override
    public String toString(){
        return  "<td>"+this.getId()+"</td>"+
                "<td>"+this.getApartmentClass()+"</td>"+
                "<td>"+this.getRoomNumber()+"</td>"+
                "<td>"+this.getCouchette()+"</td>"+
                "<td>"+this.getDate_in()+"</td>"+
                "<td>"+this.getDate_out()+"</td>"+
                "<td>"+this.getStatus()+"</td>";
    }
}
