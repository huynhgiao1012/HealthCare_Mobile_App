package com.example.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

public class ForgotPasswordActivity extends AppCompatActivity {
    private MaterialToolbar toolbar;
    private TextInputLayout IDNumField, newPWField;
    private MaterialButton changePWBtn, cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        toolbar = findViewById(R.id.toolbar);
        IDNumField = findViewById(R.id.IDNumField);
        newPWField = findViewById(R.id.newPasswordField);
        changePWBtn = findViewById(R.id.changePasswordButton);
        cancelBtn = findViewById(R.id.cancelButton);

        toolbar.setNavigationOnClickListener(onBackPressedHandler);

        changePWBtn.setOnClickListener(changePWHandler);
        cancelBtn.setOnClickListener(onBackPressedHandler);

    }

    private View.OnClickListener onBackPressedHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    private View.OnClickListener changePWHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // TODO: Add logics to change PW in DB
        }
    };

    private Bundle getInputValues() {
        // TODO: Can change Bundle into something else that can ba able to save into DB
        Bundle inputValues = new Bundle();
        inputValues.putString("IDNumber", IDNumField.getEditText().getText().toString());

        // TODO: Hash new PW before putting into inputValues
        inputValues.putString("newPWHashed", newPWField.getEditText().getText().toString());

        return inputValues;
    };
}