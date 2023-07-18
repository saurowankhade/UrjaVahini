package com.example.electricalmaterial;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.button.MaterialButton;

public class RateThisApp extends AppCompatActivity {

    MaterialButton rate_this_app;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_this_app);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setTitle("Rate This app");
        rate_this_app = findViewById(R.id.rate_this_app);
        rate_this_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Uri uri = Uri.parse("market://details?id="+getPackageName());
                    Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }catch (ActivityNotFoundException e){
                    Uri uri = Uri.parse("http://play.google.com/store/apps/details?id="+getPackageName());
                    Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
        });
    }
}