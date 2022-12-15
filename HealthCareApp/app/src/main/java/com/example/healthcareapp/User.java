package com.example.healthcareapp;

public class User {
    private String IDNum, hashedPW, name;
    public User(String IDNum, String hashedPW, String name) {
        this.IDNum = IDNum;
        this.hashedPW = hashedPW;
        this.name = name;
    }

    public String getIDNum() {
        return IDNum;
    }

    public String getName() {
        return name;
    }
}
