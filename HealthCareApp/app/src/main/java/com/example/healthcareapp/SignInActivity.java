package com.example.healthcareapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity {
    private TextInputLayout usernameInput, passwordInput;
    private CheckBox rememberMeCB;
    private Button signInBtn, forgotPWBtn, toSignUpBtn;
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
        toSignUpBtn = findViewById(R.id.toSignUpPageBtn);

        signInBtn.setOnClickListener(signInHandler);
        forgotPWBtn.setOnClickListener(forgotPWHandler);
        toSignUpBtn.setOnClickListener(toSignUpHandler);

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
            signInUser();
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

    private View.OnClickListener toSignUpHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
        }
    };

    private void signInUser() {
        String IDNum = usernameInput.getEditText().getText().toString();
        String password = passwordInput.getEditText().getText().toString();

        if (IDNum.isEmpty()) {
            usernameInput.setError(Utilities.setEmptyErrorText("ID number"));
            usernameInput.requestFocus();
        }
        else if (password.isEmpty()) {
            passwordInput.setError(Utilities.setEmptyErrorText("Password"));
            passwordInput.requestFocus();
        }
        else {
            mAuth.signInWithEmailAndPassword(IDNum + "@healthcareapp.com", password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        signInBtn.setEnabled(false);
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }
                    else {
                        Log.d("SignInActivity", task.getException().getMessage());
                    }
                }
            });
        }
    }
}