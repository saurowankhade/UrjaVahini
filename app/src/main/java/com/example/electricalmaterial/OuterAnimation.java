package com.example.electricalmaterial;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class OuterAnimation extends AppCompatActivity {
    FirebaseFirestore fStore;
    String userId;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outer_animation);
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = mAuth.getCurrentUser().getUid();
        ActionBar bar = getSupportActionBar();
        assert bar != null;
        bar.hide();

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        DocumentReference documentReference = fStore.collection("Users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                assert value != null;
                String profile = value.getString("profile");
                String [] pro = {"ADMIN","SUPERVISOR","TEAM LEADER"};

                if (profile == null){
                    Toast.makeText(OuterAnimation.this, "Your account is blocked by your company please contact to your company !", Toast.LENGTH_LONG).show();

                    mAuth.getCurrentUser().delete().addOnCompleteListener( new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(OuterAnimation.this, "Your account is deleted!", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                    }).addOnFailureListener( new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(OuterAnimation.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else if (profile.equals(pro[0])){
                    startActivity(new Intent(getApplicationContext(),Admin.class));
//                            addStanderData(value.getString("companyEmail"));
                }
                else if (profile.equals(pro[1])){
                    startActivity(new Intent(getApplicationContext(),Supervisor.class));
//                            addStanderData(value.getString("companyEmail"));
                }
                else if (profile.equals(pro[2])){
                    startActivity(new Intent(getApplicationContext(),TeamLearder.class));
//                            addStanderData(value.getString("companyEmail"));
                }
                else {
                    FirebaseAuth.getInstance().signOut();
                    Toast.makeText(OuterAnimation.this, "Your account is blocked by your company please contact to your company !", Toast.LENGTH_SHORT).show();
                }
                finish();

//                return false;
            }
        });

    }




}