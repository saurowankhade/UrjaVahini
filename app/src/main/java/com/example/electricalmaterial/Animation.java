package com.example.electricalmaterial;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import java.util.Timer;
import java.util.TimerTask;

public class Animation extends AppCompatActivity {

    Timer time;

    ImageView background,logo;

    TextView app_title,sub_title;



    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_animation);

        ActionBar bar = getSupportActionBar();
        assert bar != null;
        bar.hide();

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        background = findViewById(R.id.background);
        logo = findViewById(R.id.logo);

        android.view.animation.Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation_acivity);
        background.setAnimation(animation);

        android.view.animation.Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.imageanimationacivity);
        logo.setAnimation(animation1);


        app_title = findViewById(R.id.app_name);
        sub_title = findViewById(R.id.sub_title);

        android.view.animation.Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bouncing);
        app_title.setAnimation(animation2);
        sub_title.setAnimation(animation2);


        app_title.setTranslationX(300);
        app_title.setAlpha(0);
        app_title.animate().translationX(0).alpha(1).setDuration(3000).setStartDelay(300).start();



        time = new Timer();
        time.schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        },3000);
    }
}