package com.example.electricalmaterial;


import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private TextInputEditText userEmail,userPassword;
    private MaterialButton login_btn;
    private TextView reset_tv;
    private FirebaseAuth mAuth;
    private ProgressBar login_progress;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        userEmail = findViewById(R.id.email_edit_text);
        userPassword = findViewById(R.id.password_edit_text);
        login_btn = findViewById(R.id.login_btn);
        login_progress = findViewById(R.id.login_progress);
        reset_tv = findViewById(R.id.reset_tv);

        ActionBar bar = getSupportActionBar();
        assert bar != null;
        bar.hide();


        mAuth = FirebaseAuth.getInstance();



        reset_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResetPassword();
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = userEmail.getText().toString().trim();
                final String password = userPassword.getText().toString().trim();

                if(email.isEmpty()){
                    userEmail.setError("Email is Required");
                    userEmail.requestFocus();
                }
                else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    userEmail.setError("Invalid email!");
                    userEmail.requestFocus();
                }
                else if(password.isEmpty()){
                    userPassword.setError("Password is Required");
                    userPassword.requestFocus();
                }
                else if(password.length() < 6){
                    userPassword.setError("Password is minimum 6 digit");
                    userPassword.requestFocus();
                }
                else{
                    login_progress.setVisibility(View.VISIBLE);
                    login_btn.setVisibility(View.GONE);
                    signIn(email,password);
                }


            }
        });




    }

    private void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),OuterAnimation.class));
                            finish();
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Login Failed ",Toast.LENGTH_LONG).show();
                            login_progress.setVisibility(View.INVISIBLE);
                            login_btn.setVisibility(View.GONE);

                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        login_progress.setVisibility(View.INVISIBLE);
                        login_btn.setVisibility(View.VISIBLE);
                    }
                });

    }

    private void ResetPassword(){
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(LoginActivity.this);
        View view = LayoutInflater
                .from(this)
                .inflate(R.layout.dialog_edit_text,null);
        TextInputEditText reg_email = view.findViewById(R.id.reset_email_edit_text);
        materialAlertDialogBuilder.setTitle("ResetPassword")
                .setMessage("Reset link will be sent to your Registered Email Address")
                .setView(view)
                .setPositiveButton("Reset", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String regEmail = reg_email.getText().toString().trim();
                        if (regEmail.isEmpty()){
                            reg_email.setError("Email is Required");
                            reg_email.requestFocus();
                        }
                        else {
                            mAuth.sendPasswordResetEmail(regEmail);
                            Toast.makeText(getApplicationContext(), "Kindly Please check tour email", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).show();
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser()!=null){
            if (!FirebaseAuth.getInstance().getCurrentUser().getEmail().isEmpty()){
                startActivity(new Intent(getApplicationContext(),OuterAnimation.class));
                finish();
            }
        }

    }
}