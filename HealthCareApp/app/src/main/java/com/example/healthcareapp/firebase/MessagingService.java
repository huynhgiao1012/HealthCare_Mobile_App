package com.example.healthcareapp.firebase;

import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.healthcareapp.IncomingCallActivity;
import com.example.healthcareapp.utilities.Constants;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MessagingService extends FirebaseMessagingService {
    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);

        Log.d("MsgService", message.getData().toString());

        String type = message.getData().get(Constants.REMOTE_MSG_TYPE);

        if (type != null) {
            if (type.equals(Constants.REMOTE_MSG_INVITATION)) {
                Intent intent = new Intent(getApplicationContext(), IncomingCallActivity.class);
                intent.putExtra("doctorName", message.getData().get("doctorName"));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        }
    }
}
