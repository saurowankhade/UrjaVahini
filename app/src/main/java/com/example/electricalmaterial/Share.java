package com.example.electricalmaterial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.button.MaterialButton;

public class Share extends AppCompatActivity {

    MaterialButton share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setTitle("Share");

        share = findViewById(R.id.share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    String body = "Download this app\n";
                    String appLink = "Link : "+"https://play.google.com/store/apps/details?="+BuildConfig.APPLICATION_ID+"\n";
                    String combineText = body+"\n"+appLink;
                    intent.putExtra(Intent.EXTRA_TEXT,combineText);
                    startActivity(Intent.createChooser(intent,"Share using"));

                }
                catch (Exception e){
                    Toast.makeText(Share.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}