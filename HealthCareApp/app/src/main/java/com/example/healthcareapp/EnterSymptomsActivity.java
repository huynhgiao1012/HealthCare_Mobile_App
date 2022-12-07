package com.example.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

public class EnterSymptomsActivity extends AppCompatActivity {
    private MaterialToolbar toolbar;
    private TextInputLayout symptomNameField, descriptionField;
    private MaterialButton addSymptomBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_symptoms);

        toolbar = findViewById(R.id.toolbar);
        symptomNameField = findViewById(R.id.symptomNameField);
        descriptionField = findViewById(R.id.descriptionField);
        addSymptomBtn = findViewById(R.id.addSymptomButton);

        toolbar.setNavigationOnClickListener(onBackPressedHandler);
        toolbar.setOnMenuItemClickListener(menuItemClickListener);

        addSymptomBtn.setOnClickListener(addSymptomHandler);
    }

    private View.OnClickListener onBackPressedHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
            overridePendingTransition(R.anim.hold, R.anim.slide_out_right);
        }
    };

    private View.OnClickListener addSymptomHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String symptomName, description;
            symptomName = symptomNameField.getEditText().getText().toString();
            description = descriptionField.getEditText().getText().toString();

            // TODO: Add logics to save symptoms to DB
        }
    };

    private Toolbar.OnMenuItemClickListener menuItemClickListener = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            if (item.getItemId() == R.id.symptomsList) {
                Intent intent = new Intent(getApplicationContext(), SymptomsListActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
            return true;
        }
    };
}