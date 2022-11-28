package com.example.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.google.android.material.textfield.TextInputLayout;

public class SignInActivity extends AppCompatActivity {
    private TextInputLayout usernameInput, passwordInput;
    private CheckBox rememberMeCB;
    private Button signInBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        usernameInput = findViewById(R.id.usernameField);
        passwordInput = findViewById(R.id.passwordField);
        rememberMeCB = findViewById(R.id.rememberMeCheckBox);
        signInBtn = findViewById(R.id.signInButton);

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SignInAct", getInputValue(usernameInput));
                Log.d("SignInAct", getInputValue(passwordInput));
            }
        });
    }

    private String getInputValue(TextInputLayout input) {
        return input.getEditText().getText().toString();
    }
}