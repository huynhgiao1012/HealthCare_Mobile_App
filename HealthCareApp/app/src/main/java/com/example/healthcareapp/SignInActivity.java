package com.example.healthcareapp;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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
            try {
                Uri builtURI = Uri.parse("http://192.168.1.7:8080/api/account/getAccountByIdCard").buildUpon()
                        .appendQueryParameter("idCard", getInputValue(usernameInput))
                        .appendQueryParameter("password", getInputValue(passwordInput))
                        .build();

                URL obj = new URL(builtURI.toString());
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("PUT");

                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                String content = "";
                while ((inputLine = in.readLine()) != null) {
                    content += inputLine;
                }
                if (content == "") {
                    Log.d("SignInAct", "Wrong pass or no id card available");
                }
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    private View.OnClickListener forgotPWHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                Uri builtURI = Uri.parse("http://192.168.1.7:8080/api/account/forgotPassword").buildUpon()
                        .appendQueryParameter("idCard", getInputValue(usernameInput))
                        .appendQueryParameter("password", getInputValue(passwordInput))
                        .build();

                URL obj = new URL(builtURI.toString());
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("PUT");

                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                String content = "";
                while ((inputLine = in.readLine()) != null) {
                    content += inputLine;
                }
                if (content == "") {
                    Log.d("SignInAct", "No id card available");
                }
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };
}