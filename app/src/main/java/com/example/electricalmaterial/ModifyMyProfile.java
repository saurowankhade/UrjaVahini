package com.example.electricalmaterial;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ModifyMyProfile extends AppCompatActivity {

    ProgressDialog pd;

    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String companyEmail;
    String userId;
    String cmp;


    TextInputEditText fullNameET,currentAddressET,permanentAddressET,educationET,phoneNoET,phoneNoVerificationET;


    LinearLayout verifyPhone,allData;

    private String verificationId;

    MaterialButton check,save;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_my_profile);
        ActionBar bar = getSupportActionBar();
        assert bar != null;
        bar.setTitle("MODIFY PROFILE");

        pd = ProgressDialog.show(this,"Sending...","Please Wait",false,false);
        pd.dismiss();

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = mAuth.getCurrentUser().getUid();

        fullNameET = findViewById(R.id.fullNameET);
        currentAddressET = findViewById(R.id.currentAddressET);
        permanentAddressET = findViewById(R.id.permanentAddressET);
        educationET = findViewById(R.id.educationET);
        phoneNoET = findViewById(R.id.phoneNoET);
        phoneNoVerificationET = findViewById(R.id.phoneNoVerificationET);
        verifyPhone = findViewById(R.id.verifyPhone);
        allData = findViewById(R.id.allData);
        check = findViewById(R.id.check);
        save = findViewById(R.id.save);






        DocumentReference documentReference = fStore.collection("Users")
                .document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                companyEmail = value.getString("companyEmail");
                cmp = companyEmail;
                companyEmail = companyEmail.replace("@", "");
                companyEmail = companyEmail.replace(".", "");


                fullNameET.setText(value.getString("fullName"));
                currentAddressET.setText(value.getString("currentAddress"));
                permanentAddressET.setText(value.getString("permanentAddress"));
                educationET.setText(value.getString("education"));
                phoneNoET.setText(value.getString("mobileNumber"));

                phoneNoET.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        if (s.equals(value.getString("mobileNumber"))){
                            verifyPhone.setVisibility(View.GONE);
                            check.setText("SAVE");
                        }
                        else {
                            verifyPhone.setVisibility(View.VISIBLE);
                            check.setText("SEND");
                        }
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (s.equals(value.getString("mobileNumber"))){
                            verifyPhone.setVisibility(View.GONE);
                            check.setText("SAVE");
                        }
                        else {
                            verifyPhone.setVisibility(View.VISIBLE);
                            check.setText("SEND");
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if (s.toString().trim().equals(value.getString("mobileNumber"))){
                            verifyPhone.setVisibility(View.GONE);
                            check.setText("SAVE");
                        }
                        else {
                            verifyPhone.setVisibility(View.VISIBLE);
                            check.setText("SEND");
                        }
                    }
                });

                check.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String fullName = fullNameET.getText().toString().trim();
                        String currentAddress = currentAddressET.getText().toString().trim();
                        String permanentAddress = permanentAddressET.getText().toString().trim();
                        String education = educationET.getText().toString().trim();
                        String phoneNo = phoneNoET.getText().toString().trim();

                        String id = userId;
                        String profile = value.getString("profile");
                        String companyEmail = value.getString("companyEmail");
                        String companyName = value.getString("companyName");
                        String email = value.getString("email");

                        if (fullName.isEmpty()||currentAddress.isEmpty()||permanentAddress.isEmpty()||education.isEmpty()||phoneNo.isEmpty()){
                            Toast.makeText(ModifyMyProfile.this, "Fill All information!", Toast.LENGTH_SHORT).show();
                        }
                        else if (phoneNo.equals(value.getString("mobileNumber"))){

                            addData(fullName,currentAddress,permanentAddress,education,phoneNo,id,profile,companyEmail,companyName,email);
                        }
                         else {
                            sendVerificationCode(phoneNo);
                        }

                    }
                });

                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String code = phoneNoVerificationET.getText().toString().trim();

                        String fullName = fullNameET.getText().toString().trim();
                        String currentAddress = currentAddressET.getText().toString().trim();
                        String permanentAddress = permanentAddressET.getText().toString().trim();
                        String education = educationET.getText().toString().trim();
                        String phoneNo = phoneNoET.getText().toString().trim();

                        String id = userId;
                        String profile = value.getString("profile");
                        String companyEmail = value.getString("companyEmail");
                        String companyName = value.getString("companyName");
                        String email = value.getString("email");

                        if(code.isEmpty()){
                            phoneNoVerificationET.setError("Required!!");
                        }
                        if (code.length()<6){
                            phoneNoVerificationET.setError("Invalid!!");
                        }
                        else {
                            verifyCode(code);
                            addData(fullName,currentAddress,permanentAddress,education,phoneNo,id,profile,companyEmail,companyName,email);
                        }
                    }
                });


            }
        });
    }

    private void addData(String fullName, String currentAddress, String permanentAddress, String education, String phoneNo, String id, String profile, String companyEmail, String companyName, String email) {


        Map<String, Object> doc = new HashMap<>();

        doc.put("Id", id);
        doc.put("fullName",fullName);
        doc.put("email",email);
        doc.put("currentAddress",currentAddress);
        doc.put("permanentAddress",permanentAddress);
        doc.put("companyName",companyName);
        doc.put("companyEmail",companyEmail);
        doc.put("education",education);
        doc.put("mobileNumber",phoneNo);
        doc.put("profile",profile);


        fStore.collection("Users").document(id).set(doc).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                //this will be called when data is added Successfully
                Toast.makeText(getApplicationContext(), "Update!!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //this will be called when data is added Failed
                Toast.makeText(getApplicationContext(), "Failed to add data : "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }


    private void signInWithCredential() {
        Toast.makeText(this, "Verification Done", Toast.LENGTH_SHORT).show();
    }


    private void sendVerificationCode(String number) {
        // this method is used for getting
        // OTP on user phone number.
        pd.show();
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(number)		 // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)				 // Activity (for callback binding)
                        .setCallbacks(mCallBack)		 // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }


    // callback method is called on Phone auth provider.
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks

            mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationId = s;
            pd.dismiss();
            save.setEnabled(true);
            Toast.makeText(getApplicationContext(), "Message is send", Toast.LENGTH_SHORT).show();
        }
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            // below line is used for getting OTP code
            // which is sent in phone auth credentials.
            final String code = phoneAuthCredential.getSmsCode();

            // checking if the code
            // is null or not.
            if (code != null) {
                phoneNoVerificationET.setText(code);
                verifyCode(code);
            }
        }

        // this method is called when firebase doesn't
        // sends our OTP code due to any error or issue.
        @Override
        public void onVerificationFailed(FirebaseException e) {
            // displaying error message with firebase exception.
            pd.dismiss();
            Toast.makeText(ModifyMyProfile.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    };

    @SuppressLint("SetTextI18n")
    private void verifyCode(String code) {
        // below line is used for getting getting
        // credentials from our verification id and code.
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);


       signInWithCredential();

        // after getting credential we are
        // calling sign in method.

    }

}