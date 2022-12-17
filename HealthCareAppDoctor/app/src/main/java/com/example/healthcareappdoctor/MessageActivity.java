package com.example.healthcareappdoctor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MessageActivity extends AppCompatActivity {
    private MaterialToolbar toolbar;
    private Intent intent;
    private MaterialButton sendBtn;
    private TextInputLayout msgInput;
    private String patientName, patientUID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        intent = getIntent();
        Bundle patientInfo = intent.getBundleExtra("patientInfo");
        patientName = patientInfo.getString("name");
        patientUID = patientInfo.getString("UID");

        toolbar = findViewById(R.id.chatTopAppBar);
        toolbar.setTitle(patientName);
        toolbar.setNavigationOnClickListener(navClickHandler);
        toolbar.setOnMenuItemClickListener(menuItemClickHandler);

        sendBtn = findViewById(R.id.sendButton);
        sendBtn.setOnClickListener(sendMsgHandler);

        msgInput = findViewById(R.id.chatInputLayout);
    }

    private View.OnClickListener navClickHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    private Toolbar.OnMenuItemClickListener menuItemClickHandler = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.patientCall:
                    // TODO: Add Jitsi call logics
                    break;

                case R.id.patientInfo:
                    // TODO: Add intent to patient info
                    break;

                default:
                    return false;
            }
            return false;
        }
    };

    private View.OnClickListener sendMsgHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String msg = msgInput.getEditText().getText().toString();
            if (!msg.isEmpty()) {
                sendMsg(msg);
            }
            msgInput.getEditText().setText("");
        }
    };

    private void sendMsg(String message) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        FirebaseUser currentDoctor = FirebaseAuth.getInstance().getCurrentUser();

        String receiver = patientUID;
        String sender = currentDoctor.getUid();

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("receiver", receiver);
        hashMap.put("sender", sender);
        hashMap.put("message", message);

        databaseReference.child("Chats").push().setValue(hashMap);
    }
}