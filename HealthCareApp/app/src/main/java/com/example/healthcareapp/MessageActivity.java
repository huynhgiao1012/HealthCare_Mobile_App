package com.example.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

public class MessageActivity extends AppCompatActivity {
    private MessageAdapter adapter;
    private ArrayList<Message> msgList;
    private RecyclerView recyclerView;
    private MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        recyclerView = findViewById(R.id.chatRV);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        toolbar = findViewById(R.id.chatTopAppBar);
        Intent intent =  getIntent();
        toolbar.setTitle(intent.getStringExtra("doctorName"));
        toolbar.setNavigationOnClickListener(navOnClickHandler);
    }

    private View.OnClickListener navOnClickHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
            overridePendingTransition(R.anim.hold, R.anim.slide_out_right);
        }
    };

    // TODO: Add logics relates to BE
}