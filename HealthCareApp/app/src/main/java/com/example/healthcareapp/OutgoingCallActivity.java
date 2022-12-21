package com.example.healthcareapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthcareapp.network.APIClient;
import com.example.healthcareapp.network.APIService;
import com.example.healthcareapp.utilities.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OutgoingCallActivity extends AppCompatActivity {
    private DatabaseReference databaseReference;
    private String inviterToken = null;
    private String receiverToken = null;
    private String doctorName, inviterName;
    private Intent intent;
    private TextView doctorNameTextView;

    private MaterialButton cancelCallBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outgoing_call);

        Handler handler = new Handler();

        FirebaseMessaging.getInstance().getToken().addOnSuccessListener(new OnSuccessListener<String>() {
            @Override
            public void onSuccess(String token) {
                inviterToken = token;
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("FCM", e.getStackTrace().toString());
            }
        });

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String myUID = FirebaseAuth.getInstance().getUid();
                databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(myUID).child("name");
                databaseReference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (task.isSuccessful()) {
                            inviterName = task.getResult().toString();
                        }
                        else {

                        }
                    }
                });
            }
        });

        thread.start();

        intent = getIntent();
        Bundle doctorInfo = intent.getBundleExtra("doctorInfo");

        receiverToken = doctorInfo.getString("token");
        initMeeting(receiverToken);

        doctorNameTextView = findViewById(R.id.doctorName);
        doctorName = doctorInfo.getString("name");
        doctorNameTextView.setText(doctorName);

        cancelCallBtn = findViewById(R.id.cancelCallButton);
        cancelCallBtn.setOnClickListener(declineCallHandler);
    }

    private View.OnClickListener declineCallHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onBackPressed();
        }
    };

    private void declineCall(String receiverToken) {
        try {
            JSONArray tokens = new JSONArray();
            tokens.put(receiverToken);

            JSONObject body = new JSONObject();
            JSONObject data = new JSONObject();

            data.put(Constants.REMOTE_MSG_TYPE, Constants.REMOTE_MSG_DECLINE);
            data.put(Constants.REMOTE_MSG_INVITER_TOKEN, inviterToken);

            body.put(Constants.REMOTE_MSG_DATA, data);
            body.put(Constants.REMOTE_MSG_REGISTRATION_IDS, tokens);

            sendRemoteMessage(body.toString(), Constants.REMOTE_MSG_DECLINE);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void initMeeting(String receiverToken) {
        try {
            JSONArray tokens = new JSONArray();
            tokens.put(receiverToken);

            JSONObject body = new JSONObject();
            JSONObject data = new JSONObject();

            data.put(Constants.REMOTE_MSG_TYPE, Constants.REMOTE_MSG_INVITATION);
            data.put(Constants.REMOTE_MSG_INVITER_TOKEN, inviterToken);
            data.put("name", inviterName);

            body.put(Constants.REMOTE_MSG_DATA, data);
            body.put(Constants.REMOTE_MSG_REGISTRATION_IDS, tokens);

            sendRemoteMessage(body.toString(), Constants.REMOTE_MSG_INVITATION);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void sendRemoteMessage(String remoteMessageBody, String type) {
        APIClient.getClient().create(APIService.class).sendRemoteMessage(
                Constants.getRemoteMessageHeaders(), remoteMessageBody
        ).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Calling " + doctorName, Toast.LENGTH_SHORT).show();
                }
                else {
                    Log.d("OutgoingCall", response.message());
                    finish();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("OutgoingCall", t.getMessage());
                finish();
            }
        });
    }
}