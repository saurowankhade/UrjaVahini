package com.example.electricalmaterial;

import static com.example.electricalmaterial.R.drawable.back_background;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;

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
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {
    ProgressDialog pd;

    TextView user_email;

    TextView cmpName,userEdu,userCurrAdd,userPerAdd;

    TextView uName,userPhone,uCEmail;

    FirebaseAuth mAuth;

    FirebaseFirestore fStore;

    String userId;

    String companyEmail;

    ImageView setImage;

    StorageReference storageReference;

    CardView changePin;



    //Custom  Dialog

    Dialog dialog;

    ImageView profileImage;

    CardView modifyMyProfile;


    @SuppressLint({"SourceLockedOrientationActivity", "UseCompatLoadingForDrawables"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        pd = ProgressDialog.show(this,"Loading...","Please Wait",false,false);
        pd.dismiss();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);



        user_email = findViewById(R.id.user_email);

        cmpName = findViewById(R.id.companyName);
        uName = findViewById(R.id.name);
        userPhone = findViewById(R.id.mobileNo);
        userEdu = findViewById(R.id.edu);
        userCurrAdd = findViewById(R.id.currAdd);
        userPerAdd = findViewById(R.id.perAdd);
        uCEmail = findViewById(R.id.cEmail);

        ActionBar bar = getSupportActionBar();
        assert bar != null;
        bar.setTitle("PROFILE");

        setImage = findViewById(R.id.setImage);



        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = mAuth.getCurrentUser().getUid();
        DocumentReference documentReference = fStore.collection("Users")
                .document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                assert value != null;
                cmpName.setText(value.getString("companyName"));
                uName.setText(value.getString("fullName"));
                userPerAdd.setText(value.getString("permanentAddress"));
                userEdu.setText(value.getString("education"));
                userPhone.setText(value.getString("mobileNumber"));
                userCurrAdd.setText(value.getString("currentAddress"));
                companyEmail = value.getString("companyEmail");
                uCEmail.setText(companyEmail);


            }
        });

        user_email.setText(mAuth.getCurrentUser().getEmail());

        storageReference = FirebaseStorage.getInstance().getReference();

        pd.show();

        setImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                //noinspection deprecation

                startActivityForResult(openGalleryIntent,1000);
            }
        });

        changePin = findViewById(R.id.changePin);
        changePin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ChangePin.class));
            }
        });



        dialog = new Dialog(this);
        dialog.setContentView(R.layout.image_display);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setBackgroundDrawable(getDrawable(back_background));
        profileImage = dialog.findViewById(R.id.profileImage);


        setImage.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                dialog.show();
                return true;
            }
        });


        StorageReference profileRef = storageReference.child("users/"+mAuth.getCurrentUser().getUid()+"/profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                pd.dismiss();
                Picasso.get().load(uri).into(setImage);
                Picasso.get().load(uri).into(profileImage);
                pd.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
            }
        });


        modifyMyProfile = findViewById(R.id.modifyProfile);
        modifyMyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ModifyMyProfile.class));
                finish();
            }
        });

        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                dialog.dismiss();
            }
        });



    }



    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() == null) {
            startActivity(new Intent(ProfileActivity.this,LoginActivity.class));
            finish();

        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000){
            if (resultCode == Activity.RESULT_OK){
                Uri imageUri = data.getData();
                // profileImage.setImageURI(imageUri);
                pd.show();
                uploadImageToFirebase(imageUri);
            }

        }
    }

    private void uploadImageToFirebase(Uri imageUri) {

        // upload image to firestore
        StorageReference fileRef = storageReference.child("users/"+mAuth.getCurrentUser().getUid()+"/profile.jpg");
        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        pd.dismiss();
                        Picasso.get().load(uri).into(setImage);
                        pd.dismiss();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }
        });
    }
}