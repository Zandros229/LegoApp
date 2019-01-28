package com.example.ernest.legoapp;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;

public class AppFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onNewToken(String token) {
        Log.i("AppFMS", "Token: " + token);
    }
}
