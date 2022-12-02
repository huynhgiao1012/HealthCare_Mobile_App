package com.example.healthcareapp;

import android.content.Context;

public class Doctor {
    private String name, doctorID;
    private final int pfp;

    public Doctor(String name, String doctorID, int pfp) {
        this.name = name;
        this.pfp = pfp;
        this.doctorID = doctorID;
    }

    // TODO: This constructor only serves as a test. Remove this in the future
    public Doctor(String name) {
        this.name = name;
        this.doctorID = "testID";
        this.pfp = R.mipmap.ic_launcher_round;
    }

    public int getPfp() {
        return pfp;
    }

    public String getName() {
        return name;
    }

    public String getDoctorID() {
        return doctorID;
    }
}
