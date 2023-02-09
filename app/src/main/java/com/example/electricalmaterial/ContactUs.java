package com.example.electricalmaterial;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class ContactUs extends AppCompatActivity {
    //authorization
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String userEmail;
    String userId;

    final String required = "Required!!";


    TextInputEditText userEmailAdd,userMessage;
    MaterialButton sendMail;
    ProgressBar progressBar;


    ImageView email,whatsapp,phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        setTitle("Contact Us");

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        //Auth
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = mAuth.getCurrentUser().getUid();


        userEmailAdd = findViewById(R.id.userEmail);
        userMessage = findViewById(R.id.message);
        sendMail = findViewById(R.id.sendBtn);
        userEmailAdd.setText(userEmail);
        progressBar = findViewById(R.id.progressBar);

        email = findViewById(R.id.email);
        whatsapp = findViewById(R.id.whatsapp);
        phone = findViewById(R.id.phoneNo);



        DocumentReference documentReference = fStore.collection("Users")
                .document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                userEmail = value.getString("email");
                String fullName = value.getString("fullName");

                userEmailAdd.setText("urjavahini2809@gmail.com");

                sendMail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String message = userMessage.getText().toString().trim();
                        if (message.isEmpty()){
                            userMessage.setError(required);
                            userMessage.requestFocus();
                        }
                        else {
                            userMessage.setText("");
                          sendMail(userEmail,message);
                        }
                    }
                });

                email.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gotoUrl("mailto:urjavahini2809@gmail.com");
                    }
                });

                whatsapp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gotoUrl("http://wa.me/+919322108251?text=Hello+My+self+"+fullName+"+");
                    }
                });

                phone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gotoUrl("tel:+919322108251");
                    }
                });

            }});

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.shakeanimation);
        phone.setAnimation(animation);


        Animation translate = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.translate);
        whatsapp.setAnimation(translate);
        email.setAnimation(translate);

    }

    private void sendMail(String userEmail, String message) {
        String subject = "Report form UrjaVahini";
        String finalMessage = userEmail+"\n"+message;
        JavaMailAPI javaMailAPI = new JavaMailAPI(this, "urjavahini2809@gmail.com", subject, finalMessage);
        javaMailAPI.execute();


    }
    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }

}