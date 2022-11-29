package com.example.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

public class SignUpActivity extends AppCompatActivity {
    private TextInputLayout IDNumInput, nameInput, PWInput, retypePWInput;
    private Button signUpBtn, toSignInPgBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        IDNumInput = findViewById(R.id.IDNumField);
        nameInput = findViewById(R.id.nameField);
        PWInput = findViewById(R.id.passwordField);
        retypePWInput = findViewById(R.id.retypePWField);

        signUpBtn = findViewById(R.id.signUpButton);
        toSignInPgBtn = findViewById(R.id.alreadyHadAccntBtn);

        toSignInPgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toIntent(SignInActivity.class);
            }
        });
    }

    private void toIntent(Class destinationClass) {
        Intent intent = new Intent(getApplicationContext(), destinationClass);
        startActivity(intent);
    }
}