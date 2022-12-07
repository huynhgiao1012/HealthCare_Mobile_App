package com.example.healthcareapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class MessageActivity extends AppCompatActivity {
    private MessageAdapter adapter;
    private ArrayList<Message> msgList;
    private RecyclerView recyclerView;
    private MaterialToolbar toolbar;
    private DoctorInfoFragment doctorInfoFragment;
    private TextInputLayout chatTextInput;
    private MaterialButton sendBtn;

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
        String doctorName = intent.getStringExtra("doctorName");

        toolbar.setTitle(doctorName);
        toolbar.setNavigationOnClickListener(navOnClickHandler);
        toolbar.setOnMenuItemClickListener(menuItemClickListener);

        doctorInfoFragment = new DoctorInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putString("doctorName", doctorName);
        doctorInfoFragment.setArguments(bundle);

        chatTextInput = findViewById(R.id.chatInputLayout);
        sendBtn = findViewById(R.id.sendButton);

        sendBtn.setOnClickListener(sendMsgHandler);
    }

    private View.OnClickListener navOnClickHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
            overridePendingTransition(R.anim.hold, R.anim.slide_out_right);
        }
    };

    private Toolbar.OnMenuItemClickListener menuItemClickListener = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.doctorInfo:
                    doctorInfoFragment.show(getSupportFragmentManager(), doctorInfoFragment.getTag());
                    break;
                case R.id.doctorCall:
                    break;
                default:
                    return false;
            }
            return false;
        }
    };

    // TODO: Add logics relates to BE
    private View.OnClickListener sendMsgHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // TODO: Add logics to handle sending messages
            String msg = chatTextInput.getEditText().getText().toString();
        }
    };
}