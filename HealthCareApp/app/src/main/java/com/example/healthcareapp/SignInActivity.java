package com.example.healthcareapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity {
    private TextInputLayout usernameInput, passwordInput;
    private CheckBox rememberMeCB;
    private Button signInBtn, forgotPWBtn;
    private MaterialToolbar toolbar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mAuth = FirebaseAuth.getInstance();

        usernameInput = findViewById(R.id.IDNumField);
        passwordInput = findViewById(R.id.passwordField);
        rememberMeCB = findViewById(R.id.rememberMeCheckBox);
        signInBtn = findViewById(R.id.signInButton);
        forgotPWBtn = findViewById(R.id.forgotPWButton);
        toolbar = findViewById(R.id.toolbar);

        signInBtn.setOnClickListener(signInHandler);
        forgotPWBtn.setOnClickListener(forgotPWHandler);

        toolbar.setNavigationOnClickListener(onBackPressedHandler);
    }

    private String getInputValue(TextInputLayout input) {
        return input.getEditText().getText().toString();
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
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
            Intent intent = new Intent(getApplicationContext(), ForgotPasswordActivity.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener onBackPressedHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };
}