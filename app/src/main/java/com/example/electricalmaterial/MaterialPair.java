package com.example.electricalmaterial;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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
import java.util.UUID;

public class MaterialPair extends AppCompatActivity {

    final  String RE = "Required!!";

    //authorization
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String companyEmail;
    String userId;
    String cmp;

    LinearLayout addMaterialLL1,addMaterialLL2,addMaterialLL3,addMaterialLL4,addMaterialLL5,addMaterialLL6;
    ImageView addIV1,addIV2,addIV3,addIV4,addIV5;

    AutoCompleteTextView material,material1,material2,material3,material4,material5,material6,material7,material8,material9,material10;
    AutoCompleteTextView material11,material12,material13,material14,material15,material16,material17,material18,material19,material20;
    AutoCompleteTextView material21,material22,material23,material24,material25,material26,material27,material28,material29,material30;
    ArrayAdapter<String> adapterMaterial;
    ArrayList<String> spinnerDataListMaterial;
    DatabaseReference databaseReferenceMaterial;
    ValueEventListener listenerMaterial;

    //Unit
    AutoCompleteTextView unit,unit1,unit2,unit3,unit4,unit5,unit6,unit7,unit8,unit9,unit10;
    AutoCompleteTextView unit11,unit12,unit13,unit14,unit15,unit16,unit17,unit18,unit19,unit20;
    AutoCompleteTextView unit21,unit22,unit23,unit24,unit25,unit26,unit27,unit28,unit29,unit30;
    ArrayAdapter<String> adapterUnit;
    ArrayList<String> spinnerDataListUnit;
    DatabaseReference databaseReferenceUnit;
    ValueEventListener listenerUnit;

    TextInputEditText quantity,quantity1,quantity2,quantity3,quantity4,quantity5,quantity6,quantity7,quantity8,quantity9,quantity10;
    TextInputEditText quantity11,quantity12,quantity13,quantity14,quantity15,quantity16,quantity17,quantity18,quantity19,quantity20;
    TextInputEditText quantity21,quantity22,quantity23,quantity24,quantity25,quantity26,quantity27,quantity28,quantity29,quantity30;

    MaterialButton add;
    String id = UUID.randomUUID().toString();
    LinearLayout animation,all;
    MediaPlayer mediaPlayer;
    ProgressBar progressBar;
    MaterialButton goToHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_pair);


        ActionBar bar = getSupportActionBar();
        assert bar != null;
        bar.setTitle("MATERIAL PAIR");

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


        //Auth
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = mAuth.getCurrentUser().getUid();


        addMaterialLL1 = findViewById(R.id.addMaterialLL1);
        addMaterialLL2 = findViewById(R.id.addMaterialLL2);
        addMaterialLL3 = findViewById(R.id.addMaterialLL3);
        addMaterialLL4 = findViewById(R.id.addMaterialLL4);
        addMaterialLL5 = findViewById(R.id.addMaterialLL5);
        addMaterialLL6 = findViewById(R.id.addMaterialLL6);

        addIV1 = findViewById(R.id.addIV1);
        addIV2 = findViewById(R.id.addIV2);
        addIV3 = findViewById(R.id.addIV3);
        addIV4 = findViewById(R.id.addIV4);
        addIV5 = findViewById(R.id.addIV5);


        //Material
        material = findViewById(R.id.materialWork);
        material1 = findViewById(R.id.materialWork1);
        material2 = findViewById(R.id.materialWork2);
        material3 = findViewById(R.id.materialWork3);
        material4 = findViewById(R.id.materialWork4);
        material5 = findViewById(R.id.materialWork5);
        material6 = findViewById(R.id.materialWork6);
        material7 = findViewById(R.id.materialWork7);
        material8 = findViewById(R.id.materialWork8);
        material9 = findViewById(R.id.materialWork9);
        material10 = findViewById(R.id.materialWork10);
        material11 = findViewById(R.id.materialWork11);
        material12 = findViewById(R.id.materialWork12);
        material13 = findViewById(R.id.materialWork13);
        material14 = findViewById(R.id.materialWork14);
        material15 = findViewById(R.id.materialWork15);
        material16 = findViewById(R.id.materialWork16);
        material17 = findViewById(R.id.materialWork17);
        material18 = findViewById(R.id.materialWork18);
        material19 = findViewById(R.id.materialWork19);
        material20 = findViewById(R.id.materialWork20);
        material21 = findViewById(R.id.materialWork21);
        material22 = findViewById(R.id.materialWork22);
        material23 = findViewById(R.id.materialWork23);
        material24 = findViewById(R.id.materialWork24);
        material25 = findViewById(R.id.materialWork25);
        material26 = findViewById(R.id.materialWork26);
        material27 = findViewById(R.id.materialWork27);
        material28 = findViewById(R.id.materialWork28);
        material29 = findViewById(R.id.materialWork29);
        material30 = findViewById(R.id.materialWork30);
        //Quantity
        quantity = findViewById(R.id.quantity);
        quantity1 = findViewById(R.id.quantity1);
        quantity2 = findViewById(R.id.quantity2);
        quantity3 = findViewById(R.id.quantity3);
        quantity4 = findViewById(R.id.quantity4);
        quantity5 = findViewById(R.id.quantity5);
        quantity6 = findViewById(R.id.quantity6);
        quantity7 = findViewById(R.id.quantity7);
        quantity8 = findViewById(R.id.quantity8);
        quantity9 = findViewById(R.id.quantity9);
        quantity10 = findViewById(R.id.quantity10);
        quantity11 = findViewById(R.id.quantity11);
        quantity12 = findViewById(R.id.quantity12);
        quantity13 = findViewById(R.id.quantity13);
        quantity14 = findViewById(R.id.quantity14);
        quantity15 = findViewById(R.id.quantity15);
        quantity16 = findViewById(R.id.quantity16);
        quantity17 = findViewById(R.id.quantity17);
        quantity18 = findViewById(R.id.quantity18);
        quantity19 = findViewById(R.id.quantity19);
        quantity20 = findViewById(R.id.quantity20);
        quantity21 = findViewById(R.id.quantity21);
        quantity22 = findViewById(R.id.quantity22);
        quantity23 = findViewById(R.id.quantity23);
        quantity24 = findViewById(R.id.quantity24);
        quantity25 = findViewById(R.id.quantity25);
        quantity26 = findViewById(R.id.quantity26);
        quantity27 = findViewById(R.id.quantity27);
        quantity28 = findViewById(R.id.quantity28);
        quantity29 = findViewById(R.id.quantity29);
        quantity30 = findViewById(R.id.quantity30);
        //Unit
        unit = findViewById(R.id.unit);
        unit1 = findViewById(R.id.unit1);
        unit2 = findViewById(R.id.unit2);
        unit3 = findViewById(R.id.unit3);
        unit4 = findViewById(R.id.unit4);
        unit5 = findViewById(R.id.unit5);
        unit6 = findViewById(R.id.unit6);
        unit7 = findViewById(R.id.unit7);
        unit8 = findViewById(R.id.unit8);
        unit9 = findViewById(R.id.unit9);
        unit10 = findViewById(R.id.unit10);
        unit11 = findViewById(R.id.unit11);
        unit12 = findViewById(R.id.unit12);
        unit13 = findViewById(R.id.unit13);
        unit14 = findViewById(R.id.unit14);
        unit15 = findViewById(R.id.unit15);
        unit16 = findViewById(R.id.unit16);
        unit17 = findViewById(R.id.unit17);
        unit18 = findViewById(R.id.unit18);
        unit19 = findViewById(R.id.unit19);
        unit20 = findViewById(R.id.unit20);
        unit21 = findViewById(R.id.unit21);
        unit22 = findViewById(R.id.unit22);
        unit23 = findViewById(R.id.unit23);
        unit24 = findViewById(R.id.unit24);
        unit25 = findViewById(R.id.unit25);
        unit26 = findViewById(R.id.unit26);
        unit27 = findViewById(R.id.unit27);
        unit28 = findViewById(R.id.unit28);
        unit29 = findViewById(R.id.unit29);
        unit30 = findViewById(R.id.unit30);

        add = findViewById(R.id.add);

        //Material Add Btn
        addIV1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Materials
                String uMaterial1 = material1.getText().toString().trim();
                String uQuantity1 = quantity1.getText().toString().trim();
                String uUnit1 = unit1.getText().toString().trim();

                String uMaterial2 = material2.getText().toString().trim();
                String uQuantity2 = quantity2.getText().toString().trim();
                String uUnit2 = unit2.getText().toString().trim();

                String uMaterial3 = material3.getText().toString().trim();
                String uQuantity3 = quantity3.getText().toString().trim();
                String uUnit3 = unit3.getText().toString().trim();

                String uMaterial4 = material4.getText().toString().trim();
                String uQuantity4 = quantity4.getText().toString().trim();
                String uUnit4 = unit4.getText().toString().trim();

                String uMaterial5 = material5.getText().toString().trim();
                String uQuantity5 = quantity5.getText().toString().trim();
                String uUnit5 = unit5.getText().toString().trim();


                if (uMaterial1.isEmpty()||uQuantity1.isEmpty()||uUnit1.isEmpty()||uMaterial2.isEmpty()||uQuantity2.isEmpty()||uUnit2.isEmpty()||
                        uMaterial3.isEmpty()||uQuantity3.isEmpty()||uUnit3.isEmpty()||uMaterial4.isEmpty()||uQuantity4.isEmpty()||uUnit4.isEmpty()||
                        uMaterial5.isEmpty()||uQuantity5.isEmpty()||uUnit5.isEmpty()){
                    showMessage();
                }
                else {
                    addIV1.setVisibility(View.GONE);
                    addMaterialLL2.setVisibility(View.VISIBLE);
                }
            }
        });

        addIV2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Materials
                String uMaterial6 = material6.getText().toString().trim();
                String uQuantity6 = quantity6.getText().toString().trim();
                String uUnit6 = unit6.getText().toString().trim();

                String uMaterial7 = material7.getText().toString().trim();
                String uQuantity7 = quantity7.getText().toString().trim();
                String uUnit7 = unit7.getText().toString().trim();

                String uMaterial8 = material8.getText().toString().trim();
                String uQuantity8 = quantity8.getText().toString().trim();
                String uUnit8 = unit8.getText().toString().trim();

                String uMaterial9 = material9.getText().toString().trim();
                String uQuantity9 = quantity9.getText().toString().trim();
                String uUnit9 = unit9.getText().toString().trim();

                String uMaterial10 = material10.getText().toString().trim();
                String uQuantity10 = quantity10.getText().toString().trim();
                String uUnit10 = unit10.getText().toString().trim();


                if (uMaterial6.isEmpty()||uQuantity6.isEmpty()||uUnit6.isEmpty()||uMaterial7.isEmpty()||uQuantity7.isEmpty()||uUnit7.isEmpty()||
                        uMaterial8.isEmpty()||uQuantity8.isEmpty()||uUnit8.isEmpty()||uMaterial9.isEmpty()||uQuantity9.isEmpty()||uUnit9.isEmpty()||
                        uMaterial10.isEmpty()||uQuantity10.isEmpty()||uUnit10.isEmpty()){
                    showMessage();
                }
                else {
                    addIV2.setVisibility(View.GONE);
                    addMaterialLL3.setVisibility(View.VISIBLE);
                }
            }
        });

        addIV3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Materials
                String uMaterial11 = material11.getText().toString().trim();
                String uQuantity11 = quantity11.getText().toString().trim();
                String uUnit11 = unit11.getText().toString().trim();

                String uMaterial12 = material12.getText().toString().trim();
                String uQuantity12 = quantity12.getText().toString().trim();
                String uUnit12 = unit12.getText().toString().trim();

                String uMaterial13 = material13.getText().toString().trim();
                String uQuantity13 = quantity13.getText().toString().trim();
                String uUnit13 = unit13.getText().toString().trim();

                String uMaterial14 = material4.getText().toString().trim();
                String uQuantity14 = quantity14.getText().toString().trim();
                String uUnit14 = unit14.getText().toString().trim();

                String uMaterial15 = material15.getText().toString().trim();
                String uQuantity15 = quantity15.getText().toString().trim();
                String uUnit15 = unit15.getText().toString().trim();


                if (uMaterial11.isEmpty()||uQuantity11.isEmpty()||uUnit11.isEmpty()||uMaterial12.isEmpty()||uQuantity12.isEmpty()||uUnit12.isEmpty()||
                        uMaterial13.isEmpty()||uQuantity13.isEmpty()||uUnit13.isEmpty()||uMaterial14.isEmpty()||uQuantity14.isEmpty()||uUnit14.isEmpty()||
                        uMaterial15.isEmpty()||uQuantity15.isEmpty()||uUnit15.isEmpty()){
                    showMessage();
                }
                else {
                    addIV3.setVisibility(View.GONE);
                    addMaterialLL4.setVisibility(View.VISIBLE);
                }
            }
        });

        addIV4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Materials
                String uMaterial16 = material16.getText().toString().trim();
                String uQuantity16 = quantity16.getText().toString().trim();
                String uUnit16 = unit16.getText().toString().trim();

                String uMaterial17 = material17.getText().toString().trim();
                String uQuantity17 = quantity17.getText().toString().trim();
                String uUnit17 = unit17.getText().toString().trim();

                String uMaterial18 = material18.getText().toString().trim();
                String uQuantity18 = quantity18.getText().toString().trim();
                String uUnit18 = unit18.getText().toString().trim();

                String uMaterial19 = material19.getText().toString().trim();
                String uQuantity19 = quantity19.getText().toString().trim();
                String uUnit19 = unit19.getText().toString().trim();

                String uMaterial20 = material20.getText().toString().trim();
                String uQuantity20 = quantity20.getText().toString().trim();
                String uUnit20 = unit20.getText().toString().trim();

                if (uMaterial16.isEmpty()||uQuantity16.isEmpty()||uUnit16.isEmpty()||uMaterial17.isEmpty()||uQuantity17.isEmpty()||uUnit17.isEmpty()||
                        uMaterial18.isEmpty()||uQuantity18.isEmpty()||uUnit18.isEmpty()||uMaterial19.isEmpty()||uQuantity19.isEmpty()||uUnit19.isEmpty()||
                        uMaterial20.isEmpty()||uQuantity20.isEmpty()||uUnit20.isEmpty()){
                    showMessage();
                }
                else {
                    addIV4.setVisibility(View.GONE);
                    addMaterialLL5.setVisibility(View.VISIBLE);
                }
            }
        });

        addIV5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Materials
                String uMaterial21 = material21.getText().toString().trim();
                String uQuantity21 = quantity21.getText().toString().trim();
                String uUnit21 = unit21.getText().toString().trim();

                String uMaterial22 = material22.getText().toString().trim();
                String uQuantity22 = quantity22.getText().toString().trim();
                String uUnit22 = unit22.getText().toString().trim();

                String uMaterial23 = material23.getText().toString().trim();
                String uQuantity23 = quantity23.getText().toString().trim();
                String uUnit23 = unit23.getText().toString().trim();

                String uMaterial24 = material4.getText().toString().trim();
                String uQuantity24 = quantity24.getText().toString().trim();
                String uUnit24 = unit24.getText().toString().trim();

                String uMaterial25 = material25.getText().toString().trim();
                String uQuantity25 = quantity25.getText().toString().trim();
                String uUnit25 = unit25.getText().toString().trim();



                if (uMaterial21.isEmpty()||uQuantity21.isEmpty()||uUnit21.isEmpty()||uMaterial22.isEmpty()||uQuantity22.isEmpty()||uUnit22.isEmpty()||
                        uMaterial23.isEmpty()||uQuantity23.isEmpty()||uUnit23.isEmpty()||uMaterial24.isEmpty()||uQuantity24.isEmpty()||uUnit24.isEmpty()||
                        uMaterial25.isEmpty()||uQuantity25.isEmpty()||uUnit25.isEmpty()){
                    showMessage();
                }
                else {
                    addIV5.setVisibility(View.GONE);
                    addMaterialLL6.setVisibility(View.VISIBLE);
                }
            }
        });

        animation = findViewById(R.id.animation);
        all = findViewById(R.id.all);
        progressBar = findViewById(R.id.progressBar);
        mediaPlayer = MediaPlayer.create(this,R.raw.sound);

        goToHome = findViewById(R.id.goToHome);
        goToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Admin.class));
                finish();
            }
        });

        DocumentReference documentReference = fStore.collection("Users")
                .document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                companyEmail = value.getString("companyEmail");
                cmp = companyEmail;
                companyEmail = companyEmail.replace("@", "");
                companyEmail = companyEmail.replace(".", "");

                //Material
                databaseReferenceMaterial = FirebaseDatabase.getInstance().getReference(companyEmail+" Material");
                spinnerDataListMaterial = new ArrayList<>();
                adapterMaterial = new ArrayAdapter<String>(MaterialPair.this,R.layout.support_simple_spinner_dropdown_item,spinnerDataListMaterial);
                material.setAdapter(adapterMaterial);
                material1.setAdapter(adapterMaterial);
                material2.setAdapter(adapterMaterial);
                material3.setAdapter(adapterMaterial);
                material4.setAdapter(adapterMaterial);
                material5.setAdapter(adapterMaterial);
                material6.setAdapter(adapterMaterial);
                material6.setAdapter(adapterMaterial);
                material7.setAdapter(adapterMaterial);
                material8.setAdapter(adapterMaterial);
                material9.setAdapter(adapterMaterial);
                material10.setAdapter(adapterMaterial);
                material11.setAdapter(adapterMaterial);
                material12.setAdapter(adapterMaterial);
                material13.setAdapter(adapterMaterial);
                material14.setAdapter(adapterMaterial);
                material15.setAdapter(adapterMaterial);
                material16.setAdapter(adapterMaterial);
                material16.setAdapter(adapterMaterial);
                material17.setAdapter(adapterMaterial);
                material18.setAdapter(adapterMaterial);
                material19.setAdapter(adapterMaterial);
                material20.setAdapter(adapterMaterial);
                material21.setAdapter(adapterMaterial);
                material22.setAdapter(adapterMaterial);
                material23.setAdapter(adapterMaterial);
                material24.setAdapter(adapterMaterial);
                material25.setAdapter(adapterMaterial);
                material26.setAdapter(adapterMaterial);
                material26.setAdapter(adapterMaterial);
                material27.setAdapter(adapterMaterial);
                material28.setAdapter(adapterMaterial);
                material29.setAdapter(adapterMaterial);
                material30.setAdapter(adapterMaterial);
                retrieveDataMaterial();

                //Unit
                databaseReferenceUnit = FirebaseDatabase.getInstance().getReference(companyEmail+" Unit");
                spinnerDataListUnit = new ArrayList<>();
                adapterUnit = new ArrayAdapter<String>(MaterialPair.this,R.layout.support_simple_spinner_dropdown_item,spinnerDataListUnit);
                unit.setAdapter(adapterUnit);
                unit1.setAdapter(adapterUnit);
                unit2.setAdapter(adapterUnit);
                unit3.setAdapter(adapterUnit);
                unit4.setAdapter(adapterUnit);
                unit5.setAdapter(adapterUnit);
                unit6.setAdapter(adapterUnit);
                unit7.setAdapter(adapterUnit);
                unit8.setAdapter(adapterUnit);
                unit9.setAdapter(adapterUnit);
                unit10.setAdapter(adapterUnit);
                unit11.setAdapter(adapterUnit);
                unit12.setAdapter(adapterUnit);
                unit13.setAdapter(adapterUnit);
                unit14.setAdapter(adapterUnit);
                unit15.setAdapter(adapterUnit);
                unit16.setAdapter(adapterUnit);
                unit17.setAdapter(adapterUnit);
                unit18.setAdapter(adapterUnit);
                unit19.setAdapter(adapterUnit);
                unit20.setAdapter(adapterUnit);
                unit21.setAdapter(adapterUnit);
                unit22.setAdapter(adapterUnit);
                unit23.setAdapter(adapterUnit);
                unit24.setAdapter(adapterUnit);
                unit25.setAdapter(adapterUnit);
                unit26.setAdapter(adapterUnit);
                unit27.setAdapter(adapterUnit);
                unit28.setAdapter(adapterUnit);
                unit29.setAdapter(adapterUnit);
                unit30.setAdapter(adapterUnit);
                retrieveDataUnit();


                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Material
                        String uMaterial = material.getText().toString().trim();
                        String uQuantity = quantity.getText().toString().trim();
                        String uUnit = unit.getText().toString().trim();

                        String uMaterial1 = material1.getText().toString().trim();
                        String uQuantity1 = quantity1.getText().toString().trim();
                        String uUnit1 = unit1.getText().toString().trim();

                        String uMaterial2 = material2.getText().toString().trim();
                        String uQuantity2 = quantity2.getText().toString().trim();
                        String uUnit2 = unit2.getText().toString().trim();

                        String uMaterial3 = material3.getText().toString().trim();
                        String uQuantity3 = quantity3.getText().toString().trim();
                        String uUnit3 = unit3.getText().toString().trim();

                        String uMaterial4 = material4.getText().toString().trim();
                        String uQuantity4 = quantity4.getText().toString().trim();
                        String uUnit4 = unit4.getText().toString().trim();

                        String uMaterial5 = material5.getText().toString().trim();
                        String uQuantity5 = quantity5.getText().toString().trim();
                        String uUnit5 = unit5.getText().toString().trim();

                        String uMaterial6 = material6.getText().toString().trim();
                        String uQuantity6 = quantity6.getText().toString().trim();
                        String uUnit6 = unit6.getText().toString().trim();

                        String uMaterial7 = material7.getText().toString().trim();
                        String uQuantity7 = quantity7.getText().toString().trim();
                        String uUnit7 = unit7.getText().toString().trim();

                        String uMaterial8 = material8.getText().toString().trim();
                        String uQuantity8 = quantity8.getText().toString().trim();
                        String uUnit8 = unit8.getText().toString().trim();

                        String uMaterial9 = material9.getText().toString().trim();
                        String uQuantity9 = quantity9.getText().toString().trim();
                        String uUnit9 = unit9.getText().toString().trim();

                        String uMaterial10 = material10.getText().toString().trim();
                        String uQuantity10 = quantity10.getText().toString().trim();
                        String uUnit10 = unit10.getText().toString().trim();

                        String uMaterial11 = material11.getText().toString().trim();
                        String uQuantity11 = quantity11.getText().toString().trim();
                        String uUnit11 = unit11.getText().toString().trim();

                        String uMaterial12 = material12.getText().toString().trim();
                        String uQuantity12 = quantity12.getText().toString().trim();
                        String uUnit12 = unit12.getText().toString().trim();

                        String uMaterial13 = material13.getText().toString().trim();
                        String uQuantity13 = quantity13.getText().toString().trim();
                        String uUnit13 = unit13.getText().toString().trim();

                        String uMaterial14 = material14.getText().toString().trim();
                        String uQuantity14 = quantity14.getText().toString().trim();
                        String uUnit14 = unit14.getText().toString().trim();

                        String uMaterial15 = material15.getText().toString().trim();
                        String uQuantity15 = quantity15.getText().toString().trim();
                        String uUnit15 = unit15.getText().toString().trim();

                        String uMaterial16 = material16.getText().toString().trim();
                        String uQuantity16 = quantity16.getText().toString().trim();
                        String uUnit16 = unit16.getText().toString().trim();

                        String uMaterial17 = material17.getText().toString().trim();
                        String uQuantity17 = quantity17.getText().toString().trim();
                        String uUnit17 = unit17.getText().toString().trim();

                        String uMaterial18 = material18.getText().toString().trim();
                        String uQuantity18 = quantity18.getText().toString().trim();
                        String uUnit18 = unit18.getText().toString().trim();

                        String uMaterial19 = material19.getText().toString().trim();
                        String uQuantity19 = quantity19.getText().toString().trim();
                        String uUnit19 = unit19.getText().toString().trim();

                        String uMaterial20 = material20.getText().toString().trim();
                        String uQuantity20 = quantity20.getText().toString().trim();
                        String uUnit20 = unit20.getText().toString().trim();

                        String uMaterial21 = material21.getText().toString().trim();
                        String uQuantity21 = quantity21.getText().toString().trim();
                        String uUnit21 = unit21.getText().toString().trim();

                        String uMaterial22 = material22.getText().toString().trim();
                        String uQuantity22 = quantity22.getText().toString().trim();
                        String uUnit22 = unit22.getText().toString().trim();

                        String uMaterial23 = material23.getText().toString().trim();
                        String uQuantity23 = quantity23.getText().toString().trim();
                        String uUnit23 = unit23.getText().toString().trim();

                        String uMaterial24 = material24.getText().toString().trim();
                        String uQuantity24 = quantity24.getText().toString().trim();
                        String uUnit24 = unit24.getText().toString().trim();

                        String uMaterial25 = material25.getText().toString().trim();
                        String uQuantity25 = quantity25.getText().toString().trim();
                        String uUnit25 = unit25.getText().toString().trim();

                        String uMaterial26 = material26.getText().toString().trim();
                        String uQuantity26 = quantity26.getText().toString().trim();
                        String uUnit26 = unit26.getText().toString().trim();

                        String uMaterial27 = material27.getText().toString().trim();
                        String uQuantity27 = quantity27.getText().toString().trim();
                        String uUnit27 = unit27.getText().toString().trim();

                        String uMaterial28 = material28.getText().toString().trim();
                        String uQuantity28 = quantity28.getText().toString().trim();
                        String uUnit28 = unit28.getText().toString().trim();

                        String uMaterial29 = material29.getText().toString().trim();
                        String uQuantity29 = quantity29.getText().toString().trim();
                        String uUnit29 = unit19.getText().toString().trim();

                        String uMaterial30 = material30.getText().toString().trim();
                        String uQuantity30 = quantity30.getText().toString().trim();
                        String uUnit30 = unit30.getText().toString().trim();


                        if (uMaterial.isEmpty()){
                            material.setError(RE);
                        }
                        else if (uQuantity.isEmpty()){
                            quantity.setError(RE);
                        }
                        else if (uUnit.isEmpty()){
                            unit.setError(RE);
                        }
                        else if (uMaterial1.isEmpty()){
                            material1.setError(RE);
                        }
                        else if (uQuantity1.isEmpty()){
                            quantity1.setError(RE);
                        }
                        else if (uUnit1.isEmpty()){
                            unit1.setError(RE);
                        }
                        else {
                            addMaterialPair(cmp,
                                    uMaterial, uMaterial1, uMaterial2, uMaterial3, uMaterial4, uMaterial5, uMaterial6, uMaterial7, uMaterial8, uMaterial9, uMaterial10,
                                    uMaterial11, uMaterial12, uMaterial13, uMaterial14, uMaterial15, uMaterial16, uMaterial17, uMaterial18, uMaterial19, uMaterial20,
                                    uMaterial21, uMaterial22, uMaterial23, uMaterial24, uMaterial25, uMaterial26, uMaterial27, uMaterial28, uMaterial29, uMaterial30,

                                    uUnit, uUnit1, uUnit2, uUnit3, uUnit4, uUnit5, uUnit6, uUnit7, uUnit8, uUnit9, uUnit10,
                                    uUnit11, uUnit12, uUnit13, uUnit14, uUnit15, uUnit16, uUnit17, uUnit18, uUnit19, uUnit20,
                                    uUnit21, uUnit22, uUnit23, uUnit24, uUnit25, uUnit26, uUnit27, uUnit28, uUnit29, uUnit30,

                                    uQuantity, uQuantity1, uQuantity2, uQuantity3, uQuantity4, uQuantity5, uQuantity6, uQuantity7, uQuantity8, uQuantity9, uQuantity10,
                                    uQuantity11, uQuantity12, uQuantity13, uQuantity14, uQuantity15, uQuantity16, uQuantity17, uQuantity18, uQuantity19, uQuantity20,
                                    uQuantity21, uQuantity22, uQuantity23, uQuantity24, uQuantity25, uQuantity26, uQuantity27, uQuantity28, uQuantity29, uQuantity30

                            );
                        }

                    }
                });


//                return false;
            }
        });

    }
    private void addMaterialPair(String cmp, String uMaterial, String uMaterial1, String uMaterial2, String uMaterial3, String uMaterial4, String uMaterial5, String uMaterial6, String uMaterial7, String uMaterial8, String uMaterial9, String uMaterial10, String uMaterial11, String uMaterial12, String uMaterial13, String uMaterial14, String uMaterial15, String uMaterial16, String uMaterial17, String uMaterial18, String uMaterial19, String uMaterial20, String uMaterial21, String uMaterial22, String uMaterial23, String uMaterial24, String uMaterial25, String uMaterial26, String uMaterial27, String uMaterial28, String uMaterial29, String uMaterial30, String uUnit, String uUnit1, String uUnit2, String uUnit3, String uUnit4, String uUnit5, String uUnit6, String uUnit7, String uUnit8, String uUnit9, String uUnit10, String uUnit11, String uUnit12, String uUnit13, String uUnit14, String uUnit15, String uUnit16, String uUnit17, String uUnit18, String uUnit19, String uUnit20, String uUnit21, String uUnit22, String uUnit23, String uUnit24, String uUnit25, String uUnit26, String uUnit27, String uUnit28, String uUnit29, String uUnit30, String uQuantity, String uQuantity1, String uQuantity2, String uQuantity3, String uQuantity4, String uQuantity5, String uQuantity6, String uQuantity7, String uQuantity8, String uQuantity9, String uQuantity10, String uQuantity11, String uQuantity12, String uQuantity13, String uQuantity14, String uQuantity15, String uQuantity16, String uQuantity17, String uQuantity18, String uQuantity19, String uQuantity20, String uQuantity21, String uQuantity22, String uQuantity23, String uQuantity24, String uQuantity25, String uQuantity26, String uQuantity27, String uQuantity28, String uQuantity29, String uQuantity30) {

        progressBar.setVisibility(View.VISIBLE);
        add.setVisibility(View.GONE);
        Map<String, Object> doc = new HashMap<>();

      //Material
        doc.put("id", id);
        doc.put("Material",uMaterial);
        doc.put("Material 1",uMaterial1);
        doc.put("Material 2",uMaterial2);
        doc.put("Material 3",uMaterial3);
        doc.put("Material 4",uMaterial4);
        doc.put("Material 5",uMaterial5);
        doc.put("Material 6",uMaterial6);
        doc.put("Material 7",uMaterial7);
        doc.put("Material 8",uMaterial8);
        doc.put("Material 9",uMaterial9);
        doc.put("Material 10",uMaterial10);
        doc.put("Material 11",uMaterial11);
        doc.put("Material 12",uMaterial12);
        doc.put("Material 13",uMaterial13);
        doc.put("Material 14",uMaterial14);
        doc.put("Material 15",uMaterial15);
        doc.put("Material 16",uMaterial16);
        doc.put("Material 17",uMaterial17);
        doc.put("Material 18",uMaterial18);
        doc.put("Material 19",uMaterial19);
        doc.put("Material 20",uMaterial20);
        doc.put("Material 21",uMaterial21);
        doc.put("Material 22",uMaterial22);
        doc.put("Material 23",uMaterial23);
        doc.put("Material 24",uMaterial24);
        doc.put("Material 25",uMaterial25);
        doc.put("Material 26",uMaterial26);
        doc.put("Material 27",uMaterial27);
        doc.put("Material 28",uMaterial28);
        doc.put("Material 29",uMaterial29);
        doc.put("Material 30",uMaterial30);
        //Quantity
        doc.put("Quantity",uQuantity);
        doc.put("Quantity 1",uQuantity1);
        doc.put("Quantity 2",uQuantity2);
        doc.put("Quantity 3",uQuantity3);
        doc.put("Quantity 4",uQuantity4);
        doc.put("Quantity 5",uQuantity5);
        doc.put("Quantity 6",uQuantity6);
        doc.put("Quantity 7",uQuantity7);
        doc.put("Quantity 8",uQuantity8);
        doc.put("Quantity 9",uQuantity9);
        doc.put("Quantity 10",uQuantity10);
        doc.put("Quantity 11",uQuantity11);
        doc.put("Quantity 12",uQuantity12);
        doc.put("Quantity 13",uQuantity13);
        doc.put("Quantity 14",uQuantity14);
        doc.put("Quantity 15",uQuantity15);
        doc.put("Quantity 16",uQuantity16);
        doc.put("Quantity 17",uQuantity17);
        doc.put("Quantity 18",uQuantity18);
        doc.put("Quantity 19",uQuantity19);
        doc.put("Quantity 20",uQuantity20);
        doc.put("Quantity 21",uQuantity21);
        doc.put("Quantity 22",uQuantity22);
        doc.put("Quantity 23",uQuantity23);
        doc.put("Quantity 24",uQuantity24);
        doc.put("Quantity 25",uQuantity25);
        doc.put("Quantity 26",uQuantity26);
        doc.put("Quantity 27",uQuantity27);
        doc.put("Quantity 28",uQuantity28);
        doc.put("Quantity 29",uQuantity29);
        doc.put("Quantity 30",uQuantity30);
        //Unit
        doc.put("Unit",uUnit);
        doc.put("Unit 1",uUnit1);
        doc.put("Unit 2",uUnit2);
        doc.put("Unit 3",uUnit3);
        doc.put("Unit 4",uUnit4);
        doc.put("Unit 5",uUnit5);
        doc.put("Unit 6",uUnit6);
        doc.put("Unit 7",uUnit7);
        doc.put("Unit 8",uUnit8);
        doc.put("Unit 9",uUnit9);
        doc.put("Unit 10",uUnit10);
        doc.put("Unit 11",uUnit11);
        doc.put("Unit 12",uUnit12);
        doc.put("Unit 13",uUnit13);
        doc.put("Unit 14",uUnit14);
        doc.put("Unit 15",uUnit15);
        doc.put("Unit 16",uUnit16);
        doc.put("Unit 17",uUnit17);
        doc.put("Unit 18",uUnit18);
        doc.put("Unit 19",uUnit19);
        doc.put("Unit 20",uUnit20);
        doc.put("Unit 21",uUnit21);
        doc.put("Unit 22",uUnit22);
        doc.put("Unit 23",uUnit23);
        doc.put("Unit 24",uUnit24);
        doc.put("Unit 25",uUnit25);
        doc.put("Unit 26",uUnit26);
        doc.put("Unit 27",uUnit27);
        doc.put("Unit 28",uUnit28);
        doc.put("Unit 29",uUnit29);
        doc.put("Unit 30",uUnit30);

        fStore.collection(cmp+" MaterialPair").document(uMaterial).set(doc).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                //this will be called when data is added Successfully
                animation.setVisibility(View.VISIBLE);
                all.setVisibility(View.GONE);
                mediaPlayer.start();
                Toast.makeText(getApplicationContext(), "Data Added!!", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //this will be called when data is added Failed
                add.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Failed to add data "+e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void showMessage() {
        Toast.makeText(this, "Fill above information first", Toast.LENGTH_SHORT).show();
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