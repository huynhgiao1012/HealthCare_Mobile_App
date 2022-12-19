package com.example.healthcareappdoctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class IncomingCallActivity extends AppCompatActivity {
    private TextView patientNameTextView;
    private String patientName;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incoming_call);

        intent = getIntent();
        patientName = intent.getStringExtra("patientName");

        patientNameTextView = findViewById(R.id.patientName);
        patientNameTextView.setText(patientName);
    }
}