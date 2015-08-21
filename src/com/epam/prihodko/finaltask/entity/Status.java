package com.epam.prihodko.finaltask.entity;

public class Status implements java.io.Serializable{
    private int id=0;
    private String status;

    public Status() {
    }
    public Status(int id, String status) {
        this.id = id;
        this.status = status;
    }

    public int getId() {
        return id;
    }
    public String getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
