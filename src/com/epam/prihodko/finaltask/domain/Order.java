package com.epam.prihodko.finaltask.domain;

public class Order implements java.io.Serializable{
    private int id=0;
    private int couchette;
    private int roomNumber;
    private String date_in;
    private String date_out;
    private String apartmentClass;
    private int personId;

    public Order(){}
    public Order( String apartmentClass,int roomNumber,int couchette, String date_in, String date_out) {
        this.couchette = couchette;
        this.date_in = date_in;
        this.date_out = date_out;
        this.apartmentClass = apartmentClass;
        this.roomNumber = roomNumber;
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
    @Override
    public String toString(){
        return " "+this.getApartmentClass()+" | "+this.getRoomNumber()+" | "+
                this.getCouchette()+ " | "+this.getDate_in()+" | "+ this.getDate_out()+" ";
    }
}
