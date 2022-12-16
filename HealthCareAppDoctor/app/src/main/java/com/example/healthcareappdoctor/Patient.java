package com.example.healthcareappdoctor;

public class Patient {
    private String name, UID;

    public Patient(String name, String UID) {
        this.name = name;
        this.UID = UID;
    }

    public Patient() {
    }

    public String getName() {
        return name;
    }

    public String getUID() {
        return UID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }
}
