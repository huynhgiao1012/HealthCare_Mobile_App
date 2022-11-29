package com.example.healthcareapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class SignInActivity extends AppCompatActivity {
    private TextInputLayout usernameInput, passwordInput;
    private CheckBox rememberMeCB;
    private Button signInBtn, forgotPWBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        usernameInput = findViewById(R.id.IDNumField);
        passwordInput = findViewById(R.id.passwordField);
        rememberMeCB = findViewById(R.id.rememberMeCheckBox);
        signInBtn = findViewById(R.id.signInButton);
        forgotPWBtn = findViewById(R.id.forgotPWButton);

        signInBtn.setOnClickListener(signInHandler);
        forgotPWBtn.setOnClickListener(forgotPWHandler);
    }

    private String getInputValue(TextInputLayout input) {
        return input.getEditText().getText().toString();
    }

    private View.OnClickListener signInHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // TODO: Replace this with logics that handle the authentication and submission to DB
            Log.d("SignInAct", getInputValue(usernameInput));
            Log.d("SignInAct", getInputValue(passwordInput));
        }
    };

    private View.OnClickListener forgotPWHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // TODO: Replace this with logics that handle "Forgot Password" case
            Toast.makeText(getApplicationContext(), "Forgot Password Clicked", Toast.LENGTH_SHORT).show();
        }
    };
}