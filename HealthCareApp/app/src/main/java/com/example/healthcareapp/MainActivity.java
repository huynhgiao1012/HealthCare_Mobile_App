package com.example.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button toSignInPg, toSignUpPg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toSignInPg = findViewById(R.id.toSignInPageBtn);
        toSignUpPg = findViewById(R.id.toSignUpPageBtn);

        toSignInPg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toIntent(SignInActivity.class);
            }
        });

        toSignUpPg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toIntent(SignUpActivity.class);
            }
        });
    }

    private void toIntent(Class activityClass) {
        Intent intent = new Intent(getApplicationContext(), activityClass);
        startActivity(intent);
    }
}