package com.epam.prihodko.finaltask.domain;

public class Person implements java.io.Serializable{
    private int id=0;
    private String name;
    private String sname;
    private String email;
    private String phone;
    private int accountId=0;

    public Person() {}
    public Person(String name, String surname, String email, String phone){
        this.name = name;
        this.sname = surname;
        this.email = email;
        this.phone = phone;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSurname(String surname) {
        this.sname = surname;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setAccountId(int accountId){ this.accountId = accountId;}



    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return sname;
    }
    public String getEmail() {
        return email;
    }
    public String getPhone() {
        return phone;
    }
    public int getAccountId() {
        return accountId;
    }

    //toSting, equals... some other methods

}
