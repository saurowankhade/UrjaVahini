package com.example.electricalmaterial;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class KgToNo extends AppCompatActivity {

    //authorization
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String companyEmail;
    String userId;
    String cmp;

    //Material
    AutoCompleteTextView material;
    ArrayAdapter<String> adapterMaterial;
    ArrayList<String> spinnerDataListMaterial;
    DatabaseReference databaseReferenceMaterial;
    ValueEventListener listenerMaterial;

    //Number
    EditText numberEt;

    //Button
    MaterialButton add;

    LinearLayout ll;
    LinearLayout animation;

    //Sound
    MediaPlayer mediaPlayer;

    //Unit
    AutoCompleteTextView unit;
    ArrayAdapter<String> adapterUnit;
    ArrayList<String> spinnerDataListUnit;
    DatabaseReference databaseReferenceUnit;
    ValueEventListener listenerUnit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kg_to_no);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


        //Auth
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = mAuth.getCurrentUser().getUid();


        material = findViewById(R.id.material);
        numberEt = findViewById(R.id.number);
        add = findViewById(R.id.add);
        unit = findViewById(R.id.unit);

        mediaPlayer = MediaPlayer.create(this,R.raw.sound);


        ActionBar bar = getSupportActionBar();
        assert bar != null;
        bar.setTitle("CONVERTER");


        //LL
        ll = findViewById(R.id.ll);
        animation = findViewById(R.id.animation);

        DocumentReference documentReference = fStore.collection("Users")
                .document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                companyEmail = value.getString("companyEmail");
                cmp = companyEmail;
                companyEmail = companyEmail.replace("@", "");
                companyEmail = companyEmail.replace(".", "");

                databaseReferenceUnit = FirebaseDatabase.getInstance().getReference(companyEmail+" Unit");
                spinnerDataListUnit = new ArrayList<>();
                adapterUnit = new ArrayAdapter<String>(KgToNo.this,R.layout.support_simple_spinner_dropdown_item,spinnerDataListUnit);
                unit.setAdapter(adapterUnit);
                retrieveDataUnit();

                databaseReferenceMaterial = FirebaseDatabase.getInstance().getReference(companyEmail + " Material");
                spinnerDataListMaterial = new ArrayList<>();
                adapterMaterial = new ArrayAdapter<String>(KgToNo.this, R.layout.support_simple_spinner_dropdown_item, spinnerDataListMaterial);
                material.setAdapter(adapterMaterial);
                retrieveDataMaterial();
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String materialS = material.getText().toString().trim();
                        String num = numberEt.getText().toString().trim();
                        String unitS = unit.getText().toString().trim();
                        if (materialS.isEmpty()){
                            material.setError("Required!!");
                            material.requestFocus();
                        }
                        else if (num.isEmpty()){
                            numberEt.setError("Required!!");
                            numberEt.requestFocus();
                        }
                        else {
                            addData(cmp,materialS,num,unitS);
                        }
                    }
                });

//                return false;
            }
        });
    }

    private void addData(String cmp, String materialS, String num,String unit) {

        String id = materialS;
        Map<String, Object> doc = new HashMap<>();

        doc.put("id", id);
        doc.put("Material",materialS);
        doc.put("Number",num);
        doc.put("Unit",unit);

        fStore.collection(cmp+" KgToNo").document(id).set(doc).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(getApplicationContext(), "Added", Toast.LENGTH_SHORT).show();
                animation.setVisibility(View.VISIBLE);
                ll.setVisibility(View.GONE);
                mediaPlayer.start();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //this will be called when data is added Failed
                Toast.makeText(getApplicationContext(), "Failed to add data "+e.getMessage(), Toast.LENGTH_SHORT).show();
                animation.setVisibility(View.GONE);
                ll.setVisibility(View.VISIBLE);

            }
        });

    }

    public void retrieveDataMaterial(){
        listenerMaterial = databaseReferenceMaterial.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot item : snapshot.getChildren()){
                    spinnerDataListMaterial.add(item.getValue().toString());
                }
                adapterMaterial.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void retrieveDataUnit(){
        listenerUnit = databaseReferenceUnit.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot item : snapshot.getChildren()){
                    spinnerDataListUnit.add(item.getValue().toString());
                }
                adapterUnit.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}