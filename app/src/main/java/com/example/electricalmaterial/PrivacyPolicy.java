package com.example.electricalmaterial;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class PrivacyPolicy extends AppCompatActivity {

    TextView email;
    TextView firebase,googlePlayStore,firebaseChase;
    final String EMAIL_ID = "mailto:urjavahini2809@gmail.com";
    final String GOOGLE_PLAY_STORE = "https://policies.google.com/privacy";
    final String FIREBASE_ANALYTICS = "https://firebase.google.com/policies/analytics";
    final String FIREBASE_CRASHLYTICS = "https://firebase.google.com/support/privacy/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);
        setTitle("Privacy-Policy");

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        email = findViewById(R.id.email);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl(EMAIL_ID);
            }
        });


        firebase = findViewById(R.id.firebase);
        firebaseChase = findViewById(R.id.firebaseCash);
        googlePlayStore = findViewById(R.id.googlePlay);

        firebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl(FIREBASE_ANALYTICS);
            }
        });
        firebaseChase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl(FIREBASE_CRASHLYTICS);
            }
        });
        googlePlayStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl(GOOGLE_PLAY_STORE);
            }
        });



    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}