package com.example.electricalmaterial;

import static com.example.electricalmaterial.R.drawable.back_background;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class ViewAllProfileWithAllDetails extends AppCompatActivity {


    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String companyEmail;
    String userId;
    String cmp;
    StorageReference storageReference;





    String id;
    TextView fullName,mobileNo,email,education,currentAddress,permanentAddress,companyEmailTV,companyName,profile;
    ImageView userImage;
    ProgressBar progressBar;

    ImageView profileImage;

    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_profile_with_all_details);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = mAuth.getCurrentUser().getUid();

        getSupportActionBar().hide();



        fullName = findViewById(R.id.name);
        mobileNo = findViewById(R.id.mobileNo);
        email = findViewById(R.id.email);
        education = findViewById(R.id.education);
        currentAddress = findViewById(R.id.currentAddress);
        permanentAddress = findViewById(R.id.permanentAddress);
        companyEmailTV = findViewById(R.id.companyEmail);
        companyName = findViewById(R.id.companyName);
        profile = findViewById(R.id.profile);
        userImage = findViewById(R.id.userImage);
        progressBar = findViewById(R.id.progressBar);

        storageReference = FirebaseStorage.getInstance().getReference();

        DocumentReference documentReference = fStore.collection("Users")
                .document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                companyEmail = value.getString("companyEmail");
                cmp = companyEmail;
                companyEmail = companyEmail.replace("@", "");
                companyEmail = companyEmail.replace(".", "");

                Bundle bundle = getIntent().getExtras();

                if (bundle!=null){
                    fullName.setText(bundle.getString("fullName"));
                    currentAddress.setText(bundle.getString("currentAddress"));
                    permanentAddress.setText(bundle.getString("permanentAddress"));
                    mobileNo.setText(bundle.getString("mobileNumber"));
                    email.setText(bundle.getString("email"));
                    profile.setText(bundle.getString("profile"));
                    companyName.setText(bundle.getString("companyName"));
                    companyEmailTV.setText(bundle.getString("companyEmail"));
                    education.setText(bundle.getString("education"));
                    id = bundle.getString("Id");
                }

                progressBar.setVisibility(View.VISIBLE);

                dialog = new Dialog(ViewAllProfileWithAllDetails.this);
                dialog.setContentView(R.layout.image_display);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
                dialog.getWindow().setBackgroundDrawable(getDrawable(back_background));
                profileImage = dialog.findViewById(R.id.profileImage);


                StorageReference profileRef = storageReference.child("users/"+id+"/profile.jpg");
                profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(userImage);
                        Picasso.get().load(uri).into(profileImage);
                        progressBar.setVisibility(View.GONE);
                        userImage.setVisibility(View.VISIBLE);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Uri uri = Uri.parse("android.resource://com.example.electricalmaterial/drawable/userphoto");
                        Picasso.get().load(uri).into(userImage);
                        Picasso.get().load(uri).into(profileImage);
                        progressBar.setVisibility(View.GONE);
                        userImage.setVisibility(View.VISIBLE);
                    }
                });

                userImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.show();
                    }
                });


//                return false;
            }

        });




    }
}