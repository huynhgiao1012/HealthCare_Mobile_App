package com.example.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class IncomingCallActivity extends AppCompatActivity {
    private TextView doctorNameTextView;
    private String doctorName;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incoming_call);

        intent = getIntent();
        doctorName = intent.getStringExtra("doctorName");

        doctorNameTextView = findViewById(R.id.doctorName);
        doctorNameTextView.setText(doctorName);
    }
}