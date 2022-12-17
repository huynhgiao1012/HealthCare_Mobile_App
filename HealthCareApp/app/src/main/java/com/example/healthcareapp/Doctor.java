package com.example.healthcareapp;

public class Doctor {
    private String UID, name;

    public Doctor(String name, String UID) {
        this.name = name;
        this.UID = UID;
    }

    public Doctor() {

    }

    public String getName() {
        return name;
    }

    public String getUID() {
        return UID;
    }
}
