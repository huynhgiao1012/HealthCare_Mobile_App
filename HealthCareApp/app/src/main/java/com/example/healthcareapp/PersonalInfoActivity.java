package com.example.healthcareapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class PersonalInfoActivity extends AppCompatActivity {
    private TextInputEditText userName, userPhone, userNewPW;
    private ImageView userPfp;
    private MaterialButton updateInfoBtn;
    private MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        userName = findViewById(R.id.userName);
        userPhone = findViewById(R.id.userPhone);
        userNewPW = findViewById(R.id.userNewPassword);
        userPfp = findViewById(R.id.userPfp);
        updateInfoBtn = findViewById(R.id.updateInfoButton);
        toolbar = findViewById(R.id.toolbar);

        updateInfoBtn.setOnClickListener(updateInfoHandler);

        toolbar.setNavigationOnClickListener(toolbarOnBackPressedHandler);
    }

    private View.OnClickListener updateInfoHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // TODO: Add logics to handle checking if info is new and saving to DB
        }
    };

    private View.OnClickListener toolbarOnBackPressedHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
            overridePendingTransition(R.anim.hold, R.anim.slide_out_right);
        }
    };
}