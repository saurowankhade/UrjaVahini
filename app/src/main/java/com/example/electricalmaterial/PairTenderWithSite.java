package com.example.electricalmaterial;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.gms.tasks.OnSuccessListener;
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

public class PairTenderWithSite extends AppCompatActivity {

    //authorization
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String companyEmail;
    String userId;

    MaterialButton savePair;



    AutoCompleteTextView tenderPair,statePair,districtPair,talukaPair,centerPair;

    //Tender
    ArrayAdapter<String> adapterTender;
    ArrayList<String> spinnerDataListTender;
    DatabaseReference databaseReferenceTender;
    ValueEventListener listenerTender;

    //Center
    ArrayAdapter<String> adapterCenter;
    ArrayList<String> spinnerDataListCenter;
    DatabaseReference databaseReferenceCenter;
    ValueEventListener listenerCenter;

    //State
    ArrayAdapter<String> adapterState;
    ArrayList<String> spinnerDataListState;
    DatabaseReference databaseReferenceState;
    ValueEventListener listenerState;


    //District
    ArrayAdapter<String> adapterDistrict;
    ArrayList<String> spinnerDataListDistrict;
    DatabaseReference databaseReferenceDistrict;
    ValueEventListener listenerDistrict;


    //Taluka
    ArrayAdapter<String> adapterTaluka;
    ArrayList<String> spinnerDataListTaluka;
    DatabaseReference databaseReferenceTaluka;
    ValueEventListener listenerTaluka;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pair_tender_with_site);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        savePair = findViewById(R.id.savePair);
        talukaPair = findViewById(R.id.talukaPair);
        tenderPair = findViewById(R.id.tenderPair);
        statePair = findViewById(R.id.statePair);
        districtPair = findViewById(R.id.districtPair);
        centerPair = findViewById(R.id.centerPair);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = mAuth.getCurrentUser().getUid();

        ActionBar bar = getSupportActionBar();
        assert bar != null;
        bar.setTitle("PAIR TENDER WITH SITE");

        DocumentReference documentReference = fStore.collection("Users")
                .document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                companyEmail = value.getString("companyEmail");
                String cmp = companyEmail;
                companyEmail = companyEmail.replace("@", "");
                companyEmail = companyEmail.replace(".", "");


                //State
                databaseReferenceState = FirebaseDatabase.getInstance().getReference(companyEmail+" State");
                spinnerDataListState = new ArrayList<>();
                adapterState = new ArrayAdapter<String>(PairTenderWithSite.this,R.layout.support_simple_spinner_dropdown_item,spinnerDataListState);
                statePair.setAdapter(adapterState);
                retrieveDataState();

                //District
                databaseReferenceDistrict = FirebaseDatabase.getInstance().getReference(companyEmail+" District");
                spinnerDataListDistrict = new ArrayList<>();
                adapterDistrict = new ArrayAdapter<String>(PairTenderWithSite.this,R.layout.support_simple_spinner_dropdown_item,spinnerDataListDistrict);
                districtPair.setAdapter(adapterDistrict);
                retrieveDataDistrict();


                //Taluka
                databaseReferenceTaluka = FirebaseDatabase.getInstance().getReference(companyEmail+" Taluka");
                spinnerDataListTaluka = new ArrayList<>();
                adapterTaluka = new ArrayAdapter<String>(PairTenderWithSite.this,R.layout.support_simple_spinner_dropdown_item,spinnerDataListTaluka);
                talukaPair.setAdapter(adapterTaluka);
                retrieveDataTaluka();

                //Tender
                databaseReferenceTender = FirebaseDatabase.getInstance().getReference(companyEmail+" Tender");
                spinnerDataListTender = new ArrayList<>();
                adapterTender = new ArrayAdapter<String>(PairTenderWithSite.this,R.layout.support_simple_spinner_dropdown_item,spinnerDataListTender);
                tenderPair.setAdapter(adapterTender);
                retrieveDataTender();

                //Center
                databaseReferenceCenter = FirebaseDatabase.getInstance().getReference(companyEmail+" Center");
                spinnerDataListCenter = new ArrayList<>();
                adapterCenter = new ArrayAdapter<String>(PairTenderWithSite.this,R.layout.support_simple_spinner_dropdown_item,spinnerDataListCenter);
                centerPair.setAdapter(adapterCenter);
                retrieveDataCenter();


                savePair.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String tender = tenderPair.getText().toString().trim();
                        String state = statePair.getText().toString().trim();
                        String district = districtPair.getText().toString().trim();
                        String taluka = talukaPair.getText().toString().trim();
                        String center = centerPair.getText().toString().trim();
                        if (tender.isEmpty()) {
                            tenderPair.setError("Required!!");
                            tenderPair.requestFocus();
                        } else if (state.isEmpty()) {
                            statePair.setError("Required!!");
                            statePair.requestFocus();
                        } else if (district.isEmpty()) {
                            districtPair.setError("Required!!");
                            districtPair.requestFocus();
                        } else if (taluka.isEmpty()) {
                            talukaPair.setError("Required!!");
                            talukaPair.requestFocus();
                        } else if (center.isEmpty()) {
                            centerPair.setError("Required!!");
                            centerPair.requestFocus();
                        } else {
                            makePair(cmp, tender, state, district, taluka, center);
                        }
                    }
                });

            }
        });

    }
    private void makePair(String cmp, String tender, String state, String district, String taluka,String center) {
        DocumentReference documentReference = fStore.collection(cmp+" Pair").document(tender);
        Map<String,Object> doc = new HashMap<>();
        doc.put("Tender",tender);
        doc.put("State",state);
        doc.put("District",district);
        doc.put("Taluka",taluka);
        doc.put("Center",center);

        documentReference.set(doc).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(PairTenderWithSite.this, "Added!!", Toast.LENGTH_SHORT).show();
                tenderPair.setText("");
                statePair.setText("");
                districtPair.setText("");
                talukaPair.setText("");
                centerPair.setText("");

            }
        });
    }


    public void retrieveDataCenter(){
        listenerCenter = databaseReferenceCenter.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot item : snapshot.getChildren()){
                    spinnerDataListCenter.add(item.getValue().toString());
                }
                adapterCenter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void retrieveDataState(){
        listenerState = databaseReferenceState.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot item : snapshot.getChildren()){
                    spinnerDataListState.add(item.getValue().toString());
                }
                adapterState.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void retrieveDataDistrict(){
        listenerDistrict = databaseReferenceDistrict.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot item : snapshot.getChildren()){
                    spinnerDataListDistrict.add(item.getValue().toString());
                }
                adapterDistrict.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void retrieveDataTaluka(){
        listenerTaluka = databaseReferenceTaluka.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot item : snapshot.getChildren()){
                    spinnerDataListTaluka.add(item.getValue().toString());
                }
                adapterTaluka.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void retrieveDataTender(){
        listenerTender = databaseReferenceTender.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot item : snapshot.getChildren()){
                    spinnerDataListTender.add(item.getValue().toString());
                }
                adapterTender.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}