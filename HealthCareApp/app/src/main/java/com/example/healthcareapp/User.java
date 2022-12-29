package com.example.healthcareapp;

public class User {
    private String IDNum, hashedPW, name, token, phone;

    public User(String IDNum, String hashedPW, String name, String phone) {
        this.IDNum = IDNum;
        this.hashedPW = hashedPW;
        this.name = name;
        this.phone = phone;
    }

    public String getIDNum() {
        return IDNum;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }
}
