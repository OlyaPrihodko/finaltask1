package com.epam.prihodko.finaltask.domain;

public class Account implements java.io.Serializable{
    private int id = 0;
    private String login;
    private String password;
    private String role;

    public Account() {}
    public Account(String login,String password) {
        this.login=login;
        this.password = password;
    }
    public Account(String login,String password, String role) {
        this.login=login;
        this.password = password;
        this.role = role;
    }

    public void setId(int id){ this.id=id; }
    public void setLogin(String login){ this.login=login; }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }
    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }
    public String getRole() {
        return role;
    }


}
