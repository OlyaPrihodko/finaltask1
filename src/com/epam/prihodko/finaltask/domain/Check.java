package com.epam.prihodko.finaltask.domain;

public class Check implements java.io.Serializable{
    private int id=0;
    private int price;
    private int apatrmentId;
    private int orderId;

    public Check(){}

    public Check(int id, int price) {
        this.id = id;
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public void setApatrmentId(int apatrmentId) {
        this.apatrmentId = apatrmentId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getId() {
        return id;
    }
    public int getPrice() {
        return price;
    }
    public int getOrderId() {
        return orderId;
    }
    public int getApatrmentId() {
        return apatrmentId;
    }

    @Override
    public String toString(){
        return  "<td>"+this.getId()+"</td>"+
                "<td>"+this.getPrice()+"</td>"+
                "<td>"+this.getApatrmentId()+"</td>"+
                "<td>"+this.getOrderId()+"</td>";
    }
}
