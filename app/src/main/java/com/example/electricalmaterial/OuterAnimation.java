package com.example.electricalmaterial;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
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
import java.util.UUID;

public class OuterAnimation extends AppCompatActivity {
    FirebaseFirestore fStore;
    String userId;
    FirebaseAuth mAuth;


    String materialData;
    ArrayAdapter<String> adapterMaterial;
    ArrayList<String> spinnerMaterial;
    DatabaseReference databaseReferenceMaterial;
    ValueEventListener listenerMaterial;


    String [] materialListDefault = {
            "Dist. Transformer 63 KVA  110.43 kv", "RSJ 116x100  9 m long", "MS Channel 100x50x6 mm (TOP AND BASE CHANNEL)", "M.S.Channel 75x40x6 mm", "MS angle 50x50x6 mm", "M.S.Flat 50 x 6 mm", "11 kV Pin Insulators with G.I. Pins", "Strain Hardware for Dog0.1 or Equ.AAAC.", "L.T.Dist.Boxes 100 KVA with KITKAT", "L.As. 11 KV (Gapless type)  with disconector", "H.T.Stay Set", "G.I.Stay Wire 74mm(8 SWG)", "Earthing Sets H.T", "11KV H.G.Fuses", "11KV A.B. Switch", "Danger Board in yard.", "G.I.Wire 8 SWG 6 SWG", "G.I.Barbed Wire `A' type. ", "Concreting ration 1:4:8", "PaintingHardware & support with numbering of pole", "Sundries", "DTC Metring with Box  single core L.T. XLPE cable (70 sq.mm.  120 mtr length) and other allied material.", "Dist. Transformer 100 KVA  110.43 kv", "RSJ 116x100  9 m long", "MS Channel 100x50x6 mm", "M.S.Channel 75x40x6 mm", "MS angle 50x50x6 mm", "M.S.Flat 50 x 6 mm", "11 kV Pin Insulators with G.I. Pins", "Strain Hardware for Dog0.1 or Equ.AAAC.", "L.T.Dist.Boxes 100 KVA with KITKAT", "L.As. 11 KV (Gapless type)  with disconector", "H.T.Stay Set", "G.I.Stay Wire 74mm(8 SWG)", "Earthing Sets H.T", "11KV H.G.Fuses", "11KV A.B. Switch", "Danger Board in yard.", "G.I.Wire 8 SWG 6 SWG", "G.I.Barbed Wire `A' type. ", "Concreting ration 1:4:8", "PaintingHardware & support with numbering of pole", "Sundries", "DTC Metring with Box  single core L.T. XLPE cable (70 sq.mm.  120 mtr length) and other allied material.", "PSC Pole 9 Mtr (200 KG)", "11 kv guarding channel MS 75x40", "M.S.Channel 75x40x6 mm", "MS angle 50x50x6 mm", "11 KV  V cross arm with clamp", "11 KV Top fitting with clamp", "M.S. Flats(50 X 10mm)", "11 kV Pin Insulators with G.I. Pins", "Disc Insulator 11 KV 7000 KG.", "Strain Hardware for 55 Sq.mm AAAC", "AAAC 55 mm2", "Sleve Joints", "H.T.Stay Set", "Stay Wire 78", "Earthing Sets H.T", "G.I.Barbed Wire `A' type. ", "Danger Board in yard.", "Concreting ration 1:4:8", "G.I.Wire 8 SWG 6 SWG", "Black Bituminus Paint", "Red Oxide Paint for 2 coats", "Aluminium Paint for 1 coat", "G.I.Nut Bolts", "Sundries", "Wedge connectors", "RABIT To RABIT", "RSJ 116x100  11 m long", "M.S.Channel 75x40x6 mm", "MS angle 50x50x6 mm", "Strain Hardware for Dog0.1 or Equ.AAAC.", "Disc Insulator 11 KV 7000 KG.", "H.T.Stay Set", "Stay Wire 78", "Earthing Sets H.T", "G.I.Wire 8 SWG 6 SWG", "G.I.Barbed Wire `A' type. ", "Danger Board in yard.", "Black Bituminus Paint", "Red Oxide Paint for 2 coats", "Aluminium Paint for 1 coat", "Concreting ration 1:4:8", "Sundries", "PSC Pole 8 Mtr (140 KG)", "AAC Gnat", "M.S.Flat 50 x 6 mm", "L.T.Shackle Insulator", "Aluminium Bobbins for neutral", "L.T.Shackle hardware", "Jointing Sleeves for AAC", "PG Clamp", "Earthing Sets L.T.", "L.T. Stay sets", "G.I.Stay Wire 73.15mm(10SWG)", "Stay Insulators", "Binding Wire", "Binding Tape", "G.I.Wire 8 SWG 6 SWG", "Concreting ration 1:4:8", "L.T.Spacers", "Sundries such as NB  Washers  Welding etc.", "GNAT To GNAT", "RSJ 125x70  9 m long", "AAC Ant", "AAC Gnat", "M.S.Flat 50 x 6 mm", "L.T.Shackle Insulator", "Aluminium Bobbins for neutral", "L.T.Shackle hardware", "Jointing Sleeves for AAC", "PG Clamp", "Earthing Sets L.T.", "L.T. Stay sets", "G.I.Stay Wire 73.15mm(10SWG)", "Stay Insulators", "Binding Wire", "Binding Tape", "G.I.Wire 8 SWG 6 SWG", "Concreting ration 1:4:8", "L.T.Spacers", "Sundries such as NB  Washers  Welding etc.", "Wedge connectors", "Ant To Ant", "GNAT To GNAT"
    };

    MaterialButton btnContinue;


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

        btnContinue = findViewById(R.id.btnContinue);

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

            }
        });

    }

    private void addStanderData(String cmpEmail) {

        String companyEmail = cmpEmail;
        cmpEmail = cmpEmail.replace("@","");
        cmpEmail = cmpEmail.replace(".","");

        databaseReferenceMaterial = FirebaseDatabase.getInstance().getReference(cmpEmail+" Material");
        spinnerMaterial = new ArrayList<>();
        adapterMaterial = new ArrayAdapter<String>(OuterAnimation.this,R.layout.support_simple_spinner_dropdown_item,spinnerMaterial);

        for (int i=0;i<materialListDefault.length;i++){
            materialListDefault[i] = materialListDefault[i].replace("@"," ");
            materialListDefault[i] = materialListDefault[i].replace("."," ");
            materialListDefault[i] = materialListDefault[i].replace("/"," ");
            materialListDefault[i] = materialListDefault[i].replace("{"," ");
            materialListDefault[i] = materialListDefault[i].replace("}"," ");
            materialListDefault[i] = materialListDefault[i].replace("["," ");
            materialListDefault[i] = materialListDefault[i].replace("]"," ");
            materialListDefault[i] = materialListDefault[i].replace("!"," ");
            materialListDefault[i] = materialListDefault[i].replace("#"," ");
            materialListDefault[i] = materialListDefault[i].replace("$"," ");
            materialListDefault[i] = materialListDefault[i].replace("%"," ");
            materialListDefault[i] = materialListDefault[i].replace("^"," ");
            materialListDefault[i] = materialListDefault[i].replace("&"," ");
            materialListDefault[i] = materialListDefault[i].replace("*"," ");
            materialListDefault[i] = materialListDefault[i].replace("("," ");
            materialListDefault[i] = materialListDefault[i].replace(")"," ");

            uploadMaterial(materialListDefault[i], companyEmail);

            databaseReferenceMaterial.child(materialListDefault[i]).setValue(materialListDefault[i])
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            spinnerMaterial.clear();
                            retrieveDataMaterial();
                            adapterMaterial.notifyDataSetChanged();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

        }

    }

    public void retrieveDataMaterial(){
        listenerMaterial = databaseReferenceMaterial.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot item : snapshot.getChildren()){
                    spinnerMaterial.add(item.getValue().toString());
                }
                adapterMaterial.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



    private void uploadMaterial(String materialData, String cmp) {
        String id = UUID.randomUUID().toString();
        Map<String, Object> doc = new HashMap<>();

        doc.put("id", id);
        doc.put("Material",materialData);

        fStore.collection(cmp+" Material").document(id).set(doc).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                //this will be called when data is added Successfully
//                Toast.makeText(getApplicationContext(), "Data Added !!", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //this will be called when data is added Failed
                Toast.makeText(getApplicationContext(), "Failed to add data "+e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

}