package com.example.healthcareappdoctor.utilities;

import java.util.HashMap;

public class Constants {
    public static final String REMOTE_MSG_AUTHORIZATION = "Authorization";
    public static final String REMOTE_MSG_CONTENT_TYPE = "Content-Type";

    public static final String REMOTE_MSG_TYPE = "type";
    public static final String REMOTE_MSG_INVITATION = "invitation";
    public static final String REMOTE_MSG_MEETING_TYPE = "meetingType";
    public static final String REMOTE_MSG_INVITER_TOKEN = "inviterToken";
    public static final String REMOTE_MSG_DATA = "data";
    public static final String REMOTE_MSG_REGISTRATION_IDS = "registration_ids";

    public static HashMap<String, String> getRemoteMessageHeaders() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put(
                REMOTE_MSG_AUTHORIZATION,
                "key=AAAAyuvNakE:APA91bECmgRb9ZO1YZn0SBbMVTMVborr7mT6vJAzLPCqBxjLbyggnTKddZbJKqBmbf1vJ-_UeatjbyFnQgEWk0wMIf1UWBv0tQGK4cBHZhtgVhcBInwtQhkNnCy_Ml3__R54LDe3AMDQ"
        );

        headers.put(REMOTE_MSG_CONTENT_TYPE, "application/json");
        return headers;
    }
}
