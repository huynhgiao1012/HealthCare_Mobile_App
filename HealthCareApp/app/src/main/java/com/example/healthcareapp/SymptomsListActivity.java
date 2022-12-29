package com.example.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthcareapp.utilities.Constants;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class SymptomsListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<Symptom> symptomsList;
    private SymptomAdapter adapter;
    private MaterialToolbar toolbar;
    private TextView loadingTextView;

    private FirebaseAuth mAuth;
    private String IDNum, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptoms_list);

        mAuth = FirebaseAuth.getInstance();

        recyclerView = findViewById(R.id.symptomsListRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        symptomsList = new ArrayList<>();
        adapter = new SymptomAdapter(getApplicationContext(), symptomsList);
        recyclerView.setAdapter(adapter);

        loadingTextView = findViewById(R.id.loadingTextView);

        if (symptomsList.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            loadingTextView.setVisibility(View.VISIBLE);
        }

        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(onBackPressedHandler);

        email = mAuth.getCurrentUser().getEmail();
        IDNum = email.substring(0, email.length() - 18);

        Handler handler = new Handler();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    populateData();
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }


                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                        recyclerView.setVisibility(View.VISIBLE);
                        loadingTextView.setVisibility(View.GONE);
                    }
                });
            }
        });
        thread.start();
    }

    private void populateData() throws JSONException, IOException {
        Uri builtURI = Uri.parse("http://" + Constants.IP_ADDRESS + ":8080/api/symptomPatient/getSymptomFromIdCard").buildUpon()
                .appendQueryParameter("idCard", IDNum)
                .build();

        URL obj = new URL(builtURI.toString());
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        String content = "";
        while ((inputLine = in.readLine()) != null) {
            content += inputLine;
        }

        JSONArray symptomList = new JSONArray(content);
        Log.d("SympList", symptomList.toString());

        for (int i = 0; i < symptomList.length(); i++) {
            JSONObject symptom = symptomList.getJSONObject(i);

            symptomsList.add(new Symptom(
                    symptom.getString("name"),
                    symptom.getString("description")));
        }
        in.close();
    }


    private View.OnClickListener onBackPressedHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        }
    };
}