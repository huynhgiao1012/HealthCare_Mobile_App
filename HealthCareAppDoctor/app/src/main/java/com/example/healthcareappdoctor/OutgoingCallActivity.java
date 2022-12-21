package com.example.healthcareappdoctor;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.healthcareappdoctor.network.APIClient;
import com.example.healthcareappdoctor.network.APIService;
import com.example.healthcareappdoctor.utilities.Constants;
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
    private String inviterToken = null, receiverToken = null;
    private String patientName, inviterName = null;
    private Intent intent;

    private TextView patientNameTextView;
    private MaterialButton cancelCallBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outgoing_call);

        intent = getIntent();
        Bundle patientInfo = intent.getBundleExtra("patientInfo");
        receiverToken = patientInfo.getString("token");
        patientName = patientInfo.getString("name");

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

        Handler handler = new Handler();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String inviterUID = FirebaseAuth.getInstance().getUid();
                databaseReference = FirebaseDatabase.getInstance().getReference("Doctors").child(inviterUID);
                databaseReference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (task.isSuccessful()) {
                            inviterName = String.valueOf(task.getResult().child("name").getValue());
                            initMeeting(receiverToken, inviterName);
                        }
                        else {
                            Log.d("OutgoingAct", "failed");
                        }
                    }
                });

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        if (inviterName != null) {
//                            Log.d("OutgoingAct", inviterName);
//                        }
//                        else {
//                            Log.d("OutgoingAct", "null inviterName");
//                        }
                    }
                });
            }
        });
        thread.start();

        patientNameTextView = findViewById(R.id.patientName);
        patientNameTextView.setText(patientName);

        cancelCallBtn = findViewById(R.id.cancelCallButton);
        cancelCallBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initMeeting(String receiverToken, String inviterName) {
        try {
            JSONArray tokens = new JSONArray();
            tokens.put(receiverToken);

            JSONObject body = new JSONObject();
            JSONObject data = new JSONObject();

            data.put(Constants.REMOTE_MSG_TYPE, Constants.REMOTE_MSG_INVITATION);
            data.put(Constants.REMOTE_MSG_INVITER_TOKEN, inviterToken);
            data.put("doctorName", inviterName);

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
                    Toast.makeText(getApplicationContext(), "Calling " + patientName, Toast.LENGTH_SHORT).show();
                } else {
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

    private void test(String string) {
        Log.d("OutgoingAct", string);
    }
}