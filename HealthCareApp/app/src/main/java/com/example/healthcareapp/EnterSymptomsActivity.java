package com.example.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.healthcareapp.utilities.Constants;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.internal.TextWatcherAdapter;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class EnterSymptomsActivity extends AppCompatActivity {
    private MaterialToolbar toolbar;
    private TextInputLayout symptomNameField, descriptionField;
    private MaterialButton addSymptomBtn;

    private List<Symptom> listSymptom;

    private FirebaseAuth mAuth;
    private String IDNum, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_symptoms);

        mAuth = FirebaseAuth.getInstance();
        email = mAuth.getCurrentUser().getEmail();
        IDNum = email.substring(0, email.length() - 18);

        toolbar = findViewById(R.id.toolbar);
        symptomNameField = findViewById(R.id.symptomNameField);
        descriptionField = findViewById(R.id.descriptionField);
        addSymptomBtn = findViewById(R.id.addSymptomButton);

        toolbar.setNavigationOnClickListener(onBackPressedHandler);
        toolbar.setOnMenuItemClickListener(menuItemClickListener);

        addSymptomBtn.setOnClickListener(addSymptomHandler);

        symptomNameField.getEditText().addTextChangedListener(clearError);
        descriptionField.getEditText().addTextChangedListener(clearError);
    }

    private View.OnClickListener onBackPressedHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
            overridePendingTransition(R.anim.hold, R.anim.slide_out_right);
        }
    };

    private TextWatcher clearError = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            symptomNameField.setError(null);
            descriptionField.setError(null);
        }
    };

    private View.OnClickListener addSymptomHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            addSymptomBtn.setEnabled(false);
            addSymptomBtn.setText("Adding...");

            String symptomName, description;
            symptomName = symptomNameField.getEditText().getText().toString();
            description = descriptionField.getEditText().getText().toString();

            if (!symptomName.isEmpty() && !description.isEmpty()) {
                Handler handler = new Handler();
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {

                        addSymptom(symptomName, description);

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                symptomNameField.getEditText().setText("");
                                descriptionField.getEditText().setText("");
                                addSymptomBtn.setEnabled(true);
                                addSymptomBtn.setText("Add Symptom");
                            }
                        });
                    }
                });
                thread.start();
            }
            else if (symptomName.isEmpty()) {
                symptomNameField.setError("Field cannot be empty");
                addSymptomBtn.setEnabled(true);
                addSymptomBtn.setText("Add Symptom");
            }
            else if (description.isEmpty()) {
                descriptionField.setError("Field cannot be empty");
                addSymptomBtn.setEnabled(true);
                addSymptomBtn.setText("Add Symptom");
            }
        }
    };

    private void addSymptom(String symptomName, String description) {
        try {
            Uri builtURI = Uri.parse("http://" + Constants.IP_ADDRESS + ":8080/api/symptomPatient/saveSymptomPatient").buildUpon()
                    .appendQueryParameter("symptomName", symptomName)
                    .appendQueryParameter("patientIdCard", IDNum)
                    .appendQueryParameter("symptomDescription", description)
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
                Log.d("SaveSymptomAct", "No id card available");
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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