package com.example.electricalmaterial;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hbb20.CountryCodePicker;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    ProgressDialog pd;

    MaterialButton create,login;
    LinearLayout loginLL;
    LinearLayout name_details;
    LinearLayout address_details;
    LinearLayout other_details;
    LinearLayout company_details;
    LinearLayout regEmail_details;
    LinearLayout regPhone_details;
    LinearLayout verifyPhone_datails;
    LinearLayout verifyEmail_datails;
    LinearLayout captureCode_Details;
    TextView text;

    TextInputEditText firstName,lastName,currentAddress,permanentAddress,currentPin,permanentPin,edu,companyEmail
                        ,companyName,email,password,phoneNo,verifyPhone,verifyEmail,captureCodeVerify;
    MaterialButton nameNextBtn,addressNextBtn,otherNextBtn,companyNextBtn,regEmailNextBtn,regPhoneNextBtn,verifyPhoneNextBtn,verifyEmailNextBtn,captureCodeVerifyBtn;
    ImageButton captureCodeRefresh;
    CountryCodePicker c;

    //String values
    String fName,lName,cAddress,pAddress,cPin,pPin,education,cmpName,cmpEmail,userEmail,userPassword,mobileNo;
           String  verifyMobileNo,verifyMail;
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String generatedPassword,generatedPassword1;
    ProgressBar progressBar;
    String captureCode1,captureCode2;
    TextView num1,num2;
    TextView add;
    private String verificationId;

    LinearLayout code_verify,code_send;

    MaterialButton sendBtn,verifyBtn;

    TextInputEditText vCompanyEmail;

    Spinner work_profile;
    String workProfile;

    TextInputEditText passwordAll,verifyCode;
    String passAll,verify_code;


    MaterialButton addressBackBtn,otherBackBtn,companyBackBtn,regPhoneBackBtn,regBackNextBtn;





    @SuppressLint({"SetTextI18n", "DefaultLocale", "SourceLockedOrientationActivity"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


        pd = ProgressDialog.show(this,"Sending...","Please Wait",false,false);
        pd.dismiss();

        text = findViewById(R.id.text);

        ActionBar bar = getSupportActionBar();
        assert bar != null;
        bar.hide();

        //FireBase
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        //TextView
        //Random number
        Random random = new Random();
        generatedPassword = String.format("%04d", random.nextInt(10000));
        generatedPassword1 = String.format("%04d", random.nextInt(10000));
        create = findViewById(R.id.create_new);
        login = findViewById(R.id.login_tv);
        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        add = findViewById(R.id.add);
        add.setText("+");
        //LinerLayout
        loginLL = findViewById(R.id.login_ll);
        name_details = findViewById(R.id.name_details);
        address_details = findViewById(R.id.address_details);
        other_details = findViewById(R.id.other_details);
        company_details = findViewById(R.id.company_details);
        regEmail_details = findViewById(R.id.regEmail_details);
        regPhone_details = findViewById(R.id.regPhoneDetails);
        verifyPhone_datails = findViewById(R.id.verifyPhoneDetails);
        verifyEmail_datails = findViewById(R.id.verifyEmailDetails);
        captureCode_Details = findViewById(R.id.captureCode_Details);
        code_send = findViewById(R.id.code_send);
        code_verify = findViewById(R.id.code_verify);


        //TextInputEditText
        firstName = findViewById(R.id.firstName_edit_text);
        lastName = findViewById(R.id.lastName_edit_text);
        currentAddress = findViewById(R.id.current_edit_text);
        currentPin = findViewById(R.id.currentPin_edit_text);
        permanentAddress = findViewById(R.id.permanent_edit_text);
        permanentPin = findViewById(R.id.permanentPin_edit_text);
        edu = findViewById(R.id.education);
        companyName = findViewById(R.id.companyName);
        companyEmail = findViewById(R.id.companyEmail);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        phoneNo = findViewById(R.id.phoneNo);
        c = findViewById(R.id.countryCode_picker);
        verifyPhone = findViewById(R.id.verifyPhoneNo);
        verifyEmail = findViewById(R.id.verifyEmail);
        captureCodeVerify = findViewById(R.id.captureCodeVerify);
        vCompanyEmail = findViewById(R.id.vCompanyEmail);
        vCompanyEmail.setEnabled(false);
        verifyCode = findViewById(R.id.verifyCode);
        passwordAll = findViewById(R.id.passwordAll);
        //Button
        nameNextBtn = findViewById(R.id.nameNextBtn);
        addressNextBtn = findViewById(R.id.addressNextBtn);
        otherNextBtn = findViewById(R.id.otherNextBtn);
        companyNextBtn = findViewById(R.id.companyNextBtn);
        regEmailNextBtn = findViewById(R.id.regEmailNextBtn);
        regPhoneNextBtn = findViewById(R.id.regPhoneNextBtn);
        verifyPhoneNextBtn = findViewById(R.id.verifyPhoneNextBtn);
        verifyEmailNextBtn = findViewById(R.id.verifyEmailNextBtn);
        captureCodeRefresh = findViewById(R.id.captureCodeRefresh);
        captureCodeVerifyBtn = findViewById(R.id.captureCodeVerifyBtn);
        work_profile = findViewById(R.id.work_profile);

        sendBtn = findViewById(R.id.sendBtn);
        verifyBtn = findViewById(R.id.verifyBtn);

        progressBar = findViewById(R.id.progressBar);
        captureCode1 = getRandomString1(2);
        captureCode2 = getRandomString2(1);
        num1.setText(captureCode1);
        num2.setText(captureCode2);



        //Back Button
        addressBackBtn = findViewById(R.id.addressBackBtn);
        otherBackBtn = findViewById(R.id.otherBackBtn);
        regBackNextBtn = findViewById(R.id.regBackNextBtn);
        companyBackBtn = findViewById(R.id.companyBackBtn);
        regPhoneBackBtn = findViewById(R.id.regPhoneBackBtn);




        text.setVisibility(View.GONE);
        //setOnclick
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                finish();
            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginLL.setVisibility(View.GONE);
                name_details.setVisibility(View.VISIBLE);
                text.setVisibility(View.VISIBLE);
            }
        });


        //Create New account
        nameNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fName = firstName.getText().toString().trim();
                lName = lastName.getText().toString().trim();
                if (fName.isEmpty()){
                    firstName.setError("Required");
                    firstName.requestFocus();
                }
                else if (lName.isEmpty()){
                    lastName.setError("Required");
                    lastName.requestFocus();
                }
                else{
                    name_details.setVisibility(View.GONE);
                    address_details.setVisibility(View.VISIBLE);
                }
            }
        });
        addressNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cAddress = currentAddress.getText().toString().trim();
                pAddress  = permanentAddress.getText().toString().trim();
                cPin = currentPin.getText().toString().trim();
                pPin = permanentPin.getText().toString().trim();
                if (cAddress.isEmpty()){
                    currentAddress.setError("Required");
                    currentAddress.requestFocus();
                }
                else if (pAddress.isEmpty()){
                    permanentAddress.setError("Required");
                    permanentAddress.requestFocus();
                }
                else if (cPin.isEmpty()){
                    currentPin.setError("Required");
                    currentPin.requestFocus();
                }
                else if (cPin.length()<6){
                    currentPin.setError("Invalid");
                    currentPin.requestFocus();
                }
                else if (pPin.isEmpty()){
                    permanentPin.setError("Required");
                    permanentPin.requestFocus();
                }
                else if (pPin.length()<6){
                    permanentPin.setError("Invalid");
                    permanentPin.requestFocus();
                }
                else{
                    address_details.setVisibility(View.GONE);
                    other_details.setVisibility(View.VISIBLE);
                }
            }
        });
        otherNextBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                education = edu.getText().toString().trim();
                if (education.isEmpty()){
                    edu.setError("Required");
                    edu.requestFocus();
                }

                else {
                        other_details.setVisibility(View.GONE);
                        regEmail_details.setVisibility(View.VISIBLE);
                }
            }
        });

        regEmailNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userEmail = email.getText().toString().trim();
                userPassword = password.getText().toString().trim();
                if (userEmail.isEmpty()){
                    email.setError("Required");
                    email.requestFocus();
                }
                else  if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
                    email.setError("Invalid");
                    email.requestFocus();
                }
                else if (userEmail.equals(cmpEmail)){
                    email.setError("Company email and user email should not be same!");
                    email.requestFocus();
                }
                else if (userPassword.isEmpty()){
                    password.setError("Required");
                    password.requestFocus();
                }
                else if (userPassword.length()<6){
                    password.setError("Password minimum 6 digit");
                    password.requestFocus();
                }
                else {
                    regEmail_details.setVisibility(View.GONE);
                    verifyEmail_datails.setVisibility(View.VISIBLE);
                    sendMail();
                }
            }
        });
        verifyEmailNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyMail = verifyEmail.getText().toString().trim();
                if (verifyMail.isEmpty()){
                    verifyEmail.setError("Required");
                    verifyEmail.requestFocus();
                }
                else if (verifyMail.equals(generatedPassword)){
                    regPhone_details.setVisibility(View.VISIBLE);
                    verifyEmail_datails.setVisibility(View.GONE);
                }
                else {
                    verifyEmail.setError("Invalid");
                    verifyEmail.requestFocus();
                }
            }
        });
        regPhoneNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String country = c.getDefaultCountryCode();
                mobileNo = "+"+country+phoneNo.getText().toString().trim();
                if (mobileNo.isEmpty()){
                    phoneNo.setError("Required");
                    phoneNo.requestFocus();
                }
                else if (mobileNo.length()<13){
                    phoneNo.setError("Invalid Number");
                    phoneNo.requestFocus();
                }
                else {
                    regPhone_details.setVisibility(View.GONE);
                    verifyPhone_datails.setVisibility(View.VISIBLE);
                    sendVerificationCode(mobileNo);
                }
            }
        });

        verifyPhoneNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyMobileNo = verifyPhone.getText().toString().trim();
                if (verifyMobileNo.isEmpty()){
                    verifyPhone.setError("Required");
                    verifyPhone.requestFocus();
                }
                else if (verifyMobileNo.length()<6){
                    verifyPhone.setError("Invalid");
                    verifyPhone.requestFocus();
                }
                else {
                    verifyCode(verifyMobileNo);

                }
            }
        });

        companyNextBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cmpName = companyName.getText().toString().trim();
                cmpEmail = companyEmail.getText().toString().trim();
                if(cmpName.isEmpty()){
                    companyName.setError("Required");
                    companyName.requestFocus();
                }
                else if (cmpEmail.isEmpty()){
                    companyEmail.setError("Required");
                    companyEmail.requestFocus();
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(cmpEmail).matches()){
                    companyEmail.setError("Invalid");
                    companyEmail.requestFocus();
                }
                else {
                    company_details.setVisibility(View.GONE);
                    vCompanyEmail.setText(cmpEmail);
                    code_send.setVisibility(View.VISIBLE);

                }
            }
        });

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workProfile = work_profile.getSelectedItem().toString().trim();
                passAll = passwordAll.getText().toString().trim();
                if (workProfile.equals("Select work profile")){
                    showMessage();
                }
                else if (passAll.isEmpty()){
                    passwordAll.setError("Required!!");
                    passwordAll.requestFocus();
                }
                else {
                    code_verify.setVisibility(View.VISIBLE);
                    code_send.setVisibility(View.GONE);
                    sendEmail();

                }
            }
        });
        verifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verify_code = verifyCode.getText().toString().trim();
                if (verify_code.isEmpty()){
                    verifyCode.setError("Required!!");
                    verifyCode.requestFocus();
                }
                else if (verify_code.equals(generatedPassword1)){
                    code_verify.setVisibility(View.GONE);
                    captureCode_Details.setVisibility(View.VISIBLE);

                }
                else {
                    verifyCode.setError("Invalid code");
                    verifyCode.requestFocus();
                }
            }
        });

        captureCodeRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                captureCode1 = getRandomString1(2);
                captureCode2 = getRandomString2(1);
                num1.setText(captureCode1);
                num2.setText(captureCode2);

            }
        });
        captureCodeVerifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String verify = captureCodeVerify.getText().toString().trim();
                int a = Integer.parseInt(captureCode1);
                int b = Integer.parseInt(captureCode2);
                int c = a+b;
                cmpEmail = companyEmail.getText().toString().trim();
                String code = String.valueOf(c);
                if (verify.isEmpty()){
                    captureCodeVerify.setError("Required");
                    captureCodeVerify.requestFocus();
                }
                else if (verify.equals(code)){
                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                    captureCode_Details.setVisibility(View.GONE);
                    RegisterUserAccount(fName,lName,cAddress,pAddress,cPin,pPin,education,cmpName,cmpEmail,userEmail,userPassword,mobileNo);

                }
                else {
                    captureCodeVerify.setError("Invalid");
                    captureCodeVerify.requestFocus();
                }
            }
        });


        //Back click
        addressBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                address_details.setVisibility(View.GONE);
                name_details.setVisibility(View.VISIBLE);

            }
        });

        otherBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                address_details.setVisibility(View.VISIBLE);
                other_details.setVisibility(View.GONE);
            }
        });

        regBackNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             other_details.setVisibility(View.VISIBLE);
             regEmail_details.setVisibility(View.GONE);
            }
        });

        regPhoneBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regEmail_details.setVisibility(View.VISIBLE);
                regPhone_details.setVisibility(View.GONE);
            }
        });

        companyBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regPhone_details.setVisibility(View.VISIBLE);
                company_details.setVisibility(View.GONE);
            }
        });


    }

    private void sendEmail() {

        String name = fName+" "+lName;

        String subject = workProfile+" verify User";
        String message = "Company Name : "+cmpName+"\n"+"Name : "+name+"\n"+"Profile : "+workProfile
                +"\n"+"Current Address : "+cAddress+"\n"+"Permanent Address : "+pAddress
                +"\n"+"Education : "+education+"\n"
                +"Mobile number : "+mobileNo+"\n\n"+"If above information is right then enter this varification code otherwise don`t share this code"
                +"\n"+"Varification Code: "+generatedPassword1+"\n\n"+"THANKS YOU SO MUCH!!";

        JavaMailAPI javaMailAPI = new JavaMailAPI(this, cmpEmail, subject, message);
        javaMailAPI.execute();
    }

    private void showMessage() {
        Toast.makeText(getApplicationContext(), "Select  your work profile", Toast.LENGTH_SHORT).show();
    }

    private void RegisterUserAccount(String fName, String lName, String cAddress, String pAddress, String cPin, String pPin,
                                     String education, String cmpName, String cmpEmail, String userEmail,
                                     String userPassword, String mobileNo) {

        pd.setTitle("Creating new account");
        pd.show();
        String profile = workProfile;
        String name = fName.substring(0, 1).toUpperCase() + fName.substring(1);
        String surname = lName.substring(0, 1).toUpperCase() + lName.substring(1);

        String fullName = name+" "+surname;
        String currentAddress = cAddress+" "+cPin;
        String permanentAddress = pAddress+" "+pPin;
        mAuth.createUserWithEmailAndPassword(userEmail,userPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Register Successfully", Toast.LENGTH_SHORT).show();
                            String userId = mAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("Users")
                            .document(userId);
                            Map<String,Object> user = new HashMap<>();

                            user.put("Id",userId);
                            user.put("fullName",fullName);
                            user.put("email",userEmail);
                            user.put("currentAddress",currentAddress);
                            user.put("permanentAddress",permanentAddress);
                            user.put("companyName",cmpName);
                            user.put("companyEmail",cmpEmail);
                            user.put("education",education);
                            user.put("mobileNumber",mobileNo);
                            user.put("profile",profile);

                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d(TAG,"successes");
                                    codeVerify(cmpEmail,passAll,fullName);
                                    pd.dismiss();
                                                                    }
                            });


                        }
                    }
                }).addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }






    private void codeVerify(String cmpEmail, String passAll,String fullName) {

        pd.show();
        pd.setTitle("Creating new account");
        DocumentReference documentReference = fStore.collection(cmpEmail+" ProfilePass")
                .document(fullName.toLowerCase());
        Map<String,Object> user = new HashMap<>();
        user.put("Password",passAll);
        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d(TAG,"successes");
                pd.dismiss();
                startActivity(new Intent(getApplicationContext(),OuterAnimation.class));
                finish();
            }
        });




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
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationId = s;
            pd.dismiss();
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
                verifyPhone.setText(code);
                verifyCode(code);
            }
        }

        // this method is called when firebase doesn't
        // sends our OTP code due to any error or issue.
        @Override
        public void onVerificationFailed(FirebaseException e) {
            // displaying error message with firebase exception.
            pd.dismiss();
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    };

    @SuppressLint("SetTextI18n")
    private void verifyCode(String code) {
        // below line is used for getting getting
        // credentials from our verification id and code.
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithPhoneAuthCredential(credential);

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            verifyPhone_datails.setVisibility(View.GONE);
                            company_details.setVisibility(View.VISIBLE);
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }

    private void sendMail() {

        String message = "Your Varification code is "+generatedPassword+"\nDon`t share this code"+"\n\n"+"THANKS YOU";

        JavaMailAPI javaMailAPI = new JavaMailAPI(this, userEmail, "Verify your email", message);
        javaMailAPI.execute();


    }

    private static String getRandomString1(int a){
        final String ch = "1234567890";
        StringBuilder result = new StringBuilder();
        while (a>0){
            Random random = new Random();
            result.append(ch.charAt(random.nextInt(ch.length())));
            a--;
        }
        return result.toString().trim();
    }

    private static String getRandomString2(int a){
        final String ch = "1234567890";
        StringBuilder result = new StringBuilder();
        while (a>0){
            Random random = new Random();
            result.append(ch.charAt(random.nextInt(ch.length())));
            a--;
        }
        return result.toString().trim();
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