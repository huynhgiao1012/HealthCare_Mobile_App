package com.example.healthcareapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.NoSuchAlgorithmException;

public class SignUpActivity extends AppCompatActivity {
    private TextInputLayout IDNumInput, nameInput, PWInput, retypePWInput;
    private Button signUpBtn, toSignInPgBtn;

    private DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        IDNumInput = findViewById(R.id.IDNumField);
        nameInput = findViewById(R.id.nameField);
        PWInput = findViewById(R.id.passwordField);
        retypePWInput = findViewById(R.id.retypePWField);

        db = FirebaseDatabase.getInstance().getReference();

        signUpBtn = findViewById(R.id.signUpButton);
        toSignInPgBtn = findViewById(R.id.alreadyHadAccntBtn);

        signUpBtn.setOnClickListener(signUpHandler);

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

    private View.OnClickListener signUpHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            try {
                Uri builtURI = Uri.parse("http://192.168.1.7:8080/api/account/getAccountByIdCard").buildUpon()
                        .appendQueryParameter("idCard", IDNumInput.getEditText().getText().toString())
                        .appendQueryParameter("name", nameInput.getEditText().getText().toString())
                        .appendQueryParameter("password", PWInput.getEditText().getText().toString())
                        .build();

                URL obj = new URL(builtURI.toString());
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("POST");

                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                String content = "";
                while ((inputLine = in.readLine()) != null) {
                    content += inputLine;
                }
                if (content == "") {
                    Log.d("SignUpAct", "No id card available");
                }
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };
}