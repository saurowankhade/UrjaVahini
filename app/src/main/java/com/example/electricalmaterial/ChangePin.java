package com.example.electricalmaterial;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ChangePin extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String userId;
    String companyEmail;

    String generatedPassword;

    LinearLayout oldPinLL;
    TextInputEditText oldPin;
    MaterialButton oldPinBtn;
    TextView forgotPin;

    LinearLayout verifyEmailLL;
    TextInputEditText mailTV,verifyEmailTV;
    MaterialButton verifyEmailBtn;

    LinearLayout newPinLL;
    TextInputEditText setPin1,setPin2;
    MaterialButton save;

    LinearLayout animation;
    MaterialButton done;

    @SuppressLint({"DefaultLocale", "SourceLockedOrientationActivity"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pin);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        oldPinLL = findViewById(R.id.oldPinLL);
        oldPin = findViewById(R.id.oldPin);
        oldPinBtn = findViewById(R.id.oldPinBtn);
        forgotPin = findViewById(R.id.forgotPin);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        ActionBar bar = getSupportActionBar();
        assert bar != null;
        bar.setTitle("CHANGE PIN");

        verifyEmailLL = findViewById(R.id.verifyEmailLL);
        verifyEmailTV = findViewById(R.id.verifyEmailTV);
        mailTV = findViewById(R.id.mail);
        verifyEmailBtn = findViewById(R.id.verifyEmailBtn);


        newPinLL = findViewById(R.id.newPinLL);
        setPin1 = findViewById(R.id.setPin1);
        setPin2 = findViewById(R.id.setPin2);
        save = findViewById(R.id.save);

        animation = findViewById(R.id.animation);
        done = findViewById(R.id.done);


        Random random = new Random();
        generatedPassword = String.format("%04d", random.nextInt(10000));


        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = mAuth.getCurrentUser().getUid();
        DocumentReference documentReference = fStore.collection("Users")
                .document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                assert value != null;
                companyEmail = value.getString("companyEmail");
                String mail = value.getString("email");
                String name = value.getString("fullName");

                oldPinBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String old = oldPin.getText().toString().trim();
                        verifyOldPin(companyEmail,old,name);
                    }
                });
                forgotPin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        oldPinLL.setVisibility(View.GONE);
                        verifyEmailLL.setVisibility(View.VISIBLE);
                        sendMail(mail);
                        mailTV.setText(mail);

                    }
                });
                verifyEmailBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String code = verifyEmailTV.getText().toString().trim();
                        if (code.isEmpty()){
                            verifyEmailTV.setError("Required!!");
                            verifyEmailTV.requestFocus();
                        }
                        if (code.equals(generatedPassword)){
                            newPinLL.setVisibility(View.VISIBLE);
                            verifyEmailLL.setVisibility(View.GONE);
                            Toast.makeText(ChangePin.this, "Match!", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            verifyEmailTV.setError("Wrong!!");
                            verifyEmailTV.requestFocus();
                        }
                    }
                });

                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String s1 = setPin1.getText().toString().trim();
                        String s2 = setPin2.getText().toString().trim();
                        if (s1.isEmpty()){
                            setPin1.setError("Required!!");
                            setPin1.requestFocus();
                        }
                        else if (s2.isEmpty()){
                            setPin2.setError("Required!!");
                            setPin2.requestFocus();
                        }
                        else if (!s1.equals(s2)){
                            setPin2.setError("Does not match!!");
                            setPin2.requestFocus();
                        }
                        else {
                            addNewPin(companyEmail,s1,name);
                        }
                    }
                });

                done.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                        finish();
                    }
                });


            }
        });




    }

    private void addNewPin(String companyEmail, String s1, String name) {

        DocumentReference documentReference = fStore.collection(companyEmail+" ProfilePass")
                .document(name.toLowerCase());
        Map<String,Object> user = new HashMap<>();
        user.put("Password",s1);
        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

                newPinLL.setVisibility(View.GONE);
                animation.setVisibility(View.VISIBLE);
                Toast.makeText(ChangePin.this, "Save", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void verifyOldPin(String companyEmail, String old,String name) {

        DocumentReference a = fStore.collection(companyEmail+" ProfilePass")
                .document(name.toLowerCase());
        a.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                String passReceiverS = value.getString("Password");

                if (passReceiverS.equals(old)){
                    newPinLL.setVisibility(View.VISIBLE);
                    oldPinLL.setVisibility(View.GONE);
                }
                else {
                    Toast.makeText(ChangePin.this, "Not Match!", Toast.LENGTH_SHORT).show();
                }

            }

        });

    }

    private void sendMail(String mail) {

        String message = "Your Varification code is "+generatedPassword+"\nDon`t share this code"+"\n\n"+"THANKS YOU";

        JavaMailAPI javaMailAPI = new JavaMailAPI(this, mail, "Verify your email", message);
        javaMailAPI.execute();


    }




}