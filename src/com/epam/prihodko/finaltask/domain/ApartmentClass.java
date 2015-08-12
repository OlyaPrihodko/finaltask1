package com.epam.prihodko.finaltask.domain;

public class ApartmentClass implements java.io.Serializable{
    private int id=0;
    private String type;

    public ApartmentClass() {
    }
    public ApartmentClass(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }
    public String getType() {
        return type;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setType(String type) {
        this.type = type;
    }
}
