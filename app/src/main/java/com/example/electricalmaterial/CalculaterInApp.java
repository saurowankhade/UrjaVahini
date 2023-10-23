package com.example.electricalmaterial;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

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

public class CalculaterInApp extends AppCompatActivity {
    //Authorization
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String companyEmail;
    String userId;
    String cmp;

    AutoCompleteTextView material;
    ArrayAdapter<String> adapterMaterial;
    ArrayList<String> spinnerDataListMaterial;
    DatabaseReference databaseReferenceMaterial;
    ValueEventListener listenerMaterial;
    AutoCompleteTextView unit;
    ArrayAdapter<String> adapterUnit;
    ArrayList<String> spinnerDataListUnit;
    DatabaseReference databaseReferenceUnit;
    ValueEventListener listenerUnit;
    TextInputEditText quantity;

    MaterialButton calculate;


    TextView material1,material2,material3,material4,material5,material6,material7,material8,material9,material10;
    TextView material11,material12,material13,material14,material15,material16,material17,material18,material19,material20;
    TextView material21,material22,material23,material24,material25,material26,material27,material28,material29,material30;

    TextView unit1,unit2,unit3,unit4,unit5,unit6,unit7,unit8,unit9,unit10;
    TextView unit11,unit12,unit13,unit14,unit15,unit16,unit17,unit18,unit19,unit20;
    TextView unit21,unit22,unit23,unit24,unit25,unit26,unit27,unit28,unit29,unit30;

    TextView quantity1,quantity2,quantity3,quantity4,quantity5,quantity6,quantity7,quantity8,quantity9,quantity10;
    TextView quantity11,quantity12,quantity13,quantity14,quantity15,quantity16,quantity17,quantity18,quantity19,quantity20;
    TextView quantity21,quantity22,quantity23,quantity24,quantity25,quantity26,quantity27,quantity28,quantity29,quantity30;

    float fQuantity;
    float fQuantity1,fQuantity2,fQuantity3,fQuantity4,fQuantity5,fQuantity6,fQuantity7,fQuantity8,fQuantity9,fQuantity10;
    float fQuantity11,fQuantity12,fQuantity13,fQuantity14,fQuantity15,fQuantity16,fQuantity17,fQuantity18,fQuantity19,fQuantity20;
    float fQuantity21,fQuantity22,fQuantity23,fQuantity24,fQuantity25,fQuantity26,fQuantity27,fQuantity28,fQuantity29,fQuantity30;

    LinearLayout materialList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculater_in_app);
        //Auth
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = mAuth.getCurrentUser().getUid();

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setTitle("Calculator");

        //Material
        material = findViewById(R.id.materialWork);
        material1 = findViewById(R.id.material1);
        material2 = findViewById(R.id.material2);
        material3 = findViewById(R.id.material3);
        material4 = findViewById(R.id.material4);
        material5 = findViewById(R.id.material5);
        material6 = findViewById(R.id.material6);
        material7 = findViewById(R.id.material7);
        material8 = findViewById(R.id.material8);
        material9 = findViewById(R.id.material9);
        material10 = findViewById(R.id.material10);
        material11 = findViewById(R.id.material11);
        material12 = findViewById(R.id.material12);
        material13 = findViewById(R.id.material13);
        material14 = findViewById(R.id.material14);
        material15 = findViewById(R.id.material15);
        material16 = findViewById(R.id.material16);
        material17 = findViewById(R.id.material17);
        material18 = findViewById(R.id.material18);
        material19 = findViewById(R.id.material19);
        material20 = findViewById(R.id.material20);
        material21 = findViewById(R.id.material21);
        material22 = findViewById(R.id.material22);
        material23 = findViewById(R.id.material23);
        material24 = findViewById(R.id.material24);
        material25 = findViewById(R.id.material25);
        material26 = findViewById(R.id.material26);
        material27 = findViewById(R.id.material27);
        material28 = findViewById(R.id.material28);
        material29 = findViewById(R.id.material29);
        material30 = findViewById(R.id.material30);
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

        calculate = findViewById(R.id.calculate);


        materialList = findViewById(R.id.materialList);


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
                adapterMaterial = new ArrayAdapter<String>(CalculaterInApp.this,R.layout.support_simple_spinner_dropdown_item,spinnerDataListMaterial);
                material.setAdapter(adapterMaterial);
                retrieveDataMaterial();

                //Unit
                databaseReferenceUnit = FirebaseDatabase.getInstance().getReference(companyEmail+" Unit");
                spinnerDataListUnit = new ArrayList<>();
                adapterUnit = new ArrayAdapter<String>(CalculaterInApp.this,R.layout.support_simple_spinner_dropdown_item,spinnerDataListUnit);
                unit.setAdapter(adapterUnit);
                retrieveDataUnit();

                calculate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String uMaterial = material.getText().toString().trim();
                        String uUnit = unit.getText().toString().trim();
                        String uQuantity = quantity.getText().toString().trim();
                        if (uMaterial.isEmpty()){
                            material.setError("Required!!");
                            material.requestFocus();
                        }
                        else  if (uUnit.isEmpty()){
                            unit.setError("Required!!");
                            unit.requestFocus();
                        }
                        else  if (uQuantity.isEmpty()){
                            quantity.setError("Required!!");
                            quantity.requestFocus();
                        }
                        else {
                            materialList.setVisibility(View.VISIBLE);
                            click(cmp, uMaterial, uUnit, uQuantity);
                        }
                    }
                });
//                return false;
            }
        });

    }
    private void click(String cmp,String uMaterial,String uUnit,String uQuantity) {

        DocumentReference a = fStore.collection(cmp+" MaterialPair")
                .document(uMaterial);
        a.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                String DMaterial = value.getString("Material");
                String DUnit = value.getString("Unit");
                String DQuantity = value.getString("Quantity");

                fQuantity = Float.parseFloat(uQuantity);

                String sMaterial1 = value.getString("Material 1");
                String sMaterial2 = value.getString("Material 2");
                String sMaterial3 = value.getString("Material 3");
                String sMaterial4 = value.getString("Material 4");
                String sMaterial5 = value.getString("Material 5");
                String sMaterial6 = value.getString("Material 6");
                String sMaterial7 = value.getString("Material 7");
                String sMaterial8 = value.getString("Material 8");
                String sMaterial9 = value.getString("Material 9");
                String sMaterial10 = value.getString("Material 10");
                String sMaterial11 = value.getString("Material 11");
                String sMaterial12 = value.getString("Material 12");
                String sMaterial13 = value.getString("Material 13");
                String sMaterial14 = value.getString("Material 14");
                String sMaterial15 = value.getString("Material 15");
                String sMaterial16 = value.getString("Material 16");
                String sMaterial17 = value.getString("Material 17");
                String sMaterial18 = value.getString("Material 18");
                String sMaterial19 = value.getString("Material 19");
                String sMaterial20 = value.getString("Material 20");
                String sMaterial21 = value.getString("Material 21");
                String sMaterial22 = value.getString("Material 22");
                String sMaterial23 = value.getString("Material 23");
                String sMaterial24 = value.getString("Material 24");
                String sMaterial25 = value.getString("Material 25");
                String sMaterial26 = value.getString("Material 26");
                String sMaterial27 = value.getString("Material 27");
                String sMaterial28 = value.getString("Material 28");
                String sMaterial29 = value.getString("Material 29");
                String sMaterial30 = value.getString("Material 30");

                String sUnit1 = value.getString("Unit 1");
                String sUnit2 = value.getString("Unit 2");
                String sUnit3 = value.getString("Unit 3");
                String sUnit4 = value.getString("Unit 4");
                String sUnit5 = value.getString("Unit 5");
                String sUnit6 = value.getString("Unit 6");
                String sUnit7 = value.getString("Unit 7");
                String sUnit8 = value.getString("Unit 8");
                String sUnit9 = value.getString("Unit 9");
                String sUnit10 = value.getString("Unit 10");
                String sUnit11 = value.getString("Unit 11");
                String sUnit12 = value.getString("Unit 12");
                String sUnit13 = value.getString("Unit 13");
                String sUnit14 = value.getString("Unit 14");
                String sUnit15 = value.getString("Unit 15");
                String sUnit16 = value.getString("Unit 16");
                String sUnit17 = value.getString("Unit 17");
                String sUnit18 = value.getString("Unit 18");
                String sUnit19 = value.getString("Unit 19");
                String sUnit20 = value.getString("Unit 20");
                String sUnit21 = value.getString("Unit 21");
                String sUnit22 = value.getString("Unit 22");
                String sUnit23 = value.getString("Unit 23");
                String sUnit24 = value.getString("Unit 24");
                String sUnit25 = value.getString("Unit 25");
                String sUnit26 = value.getString("Unit 26");
                String sUnit27 = value.getString("Unit 27");
                String sUnit28 = value.getString("Unit 28");
                String sUnit29 = value.getString("Unit 29");
                String sUnit30 = value.getString("Unit 30");

                String sQuantity1 = value.getString("Quantity 1");
                String sQuantity2 = value.getString("Quantity 2");
                String sQuantity3 = value.getString("Quantity 3");
                String sQuantity4 = value.getString("Quantity 4");
                String sQuantity5 = value.getString("Quantity 5");
                String sQuantity6 = value.getString("Quantity 6");
                String sQuantity7 = value.getString("Quantity 7");
                String sQuantity8 = value.getString("Quantity 8");
                String sQuantity9 = value.getString("Quantity 9");
                String sQuantity10 = value.getString("Quantity 10");
                String sQuantity11 = value.getString("Quantity 11");
                String sQuantity12 = value.getString("Quantity 12");
                String sQuantity13 = value.getString("Quantity 13");
                String sQuantity14 = value.getString("Quantity 14");
                String sQuantity15 = value.getString("Quantity 15");
                String sQuantity16 = value.getString("Quantity 16");
                String sQuantity17 = value.getString("Quantity 17");
                String sQuantity18 = value.getString("Quantity 18");
                String sQuantity19 = value.getString("Quantity 19");
                String sQuantity20 = value.getString("Quantity 20");
                String sQuantity21 = value.getString("Quantity 21");
                String sQuantity22 = value.getString("Quantity 22");
                String sQuantity23 = value.getString("Quantity 23");
                String sQuantity24 = value.getString("Quantity 24");
                String sQuantity25 = value.getString("Quantity 25");
                String sQuantity26 = value.getString("Quantity 26");
                String sQuantity27 = value.getString("Quantity 27");
                String sQuantity28 = value.getString("Quantity 28");
                String sQuantity29 = value.getString("Quantity 29");
                String sQuantity30 = value.getString("Quantity 30");



                if (uMaterial.equals(DMaterial) && uUnit.equals(DUnit)){
                    material1.setText(sMaterial1);
                    material2.setText(sMaterial2);
                    material3.setText(sMaterial3);
                    material4.setText(sMaterial4);
                    material5.setText(sMaterial5);
                    material6.setText(sMaterial6);
                    material7.setText(sMaterial7);
                    material8.setText(sMaterial8);
                    material9.setText(sMaterial9);
                    material10.setText(sMaterial10);
                    material11.setText(sMaterial11);
                    material12.setText(sMaterial12);
                    material13.setText(sMaterial13);
                    material14.setText(sMaterial14);
                    material15.setText(sMaterial15);
                    material16.setText(sMaterial16);
                    material17.setText(sMaterial17);
                    material18.setText(sMaterial18);
                    material19.setText(sMaterial19);
                    material20.setText(sMaterial20);
                    material21.setText(sMaterial21);
                    material22.setText(sMaterial22);
                    material23.setText(sMaterial23);
                    material24.setText(sMaterial24);
                    material25.setText(sMaterial25);
                    material26.setText(sMaterial26);
                    material27.setText(sMaterial27);
                    material28.setText(sMaterial28);
                    material29.setText(sMaterial29);
                    material30.setText(sMaterial30);

                    unit1.setText(sUnit1);
                    unit2.setText(sUnit2);
                    unit3.setText(sUnit3);
                    unit4.setText(sUnit4);
                    unit5.setText(sUnit5);
                    unit6.setText(sUnit6);
                    unit7.setText(sUnit7);
                    unit8.setText(sUnit8);
                    unit9.setText(sUnit9);
                    unit10.setText(sUnit10);
                    unit11.setText(sUnit11);
                    unit12.setText(sUnit12);
                    unit13.setText(sUnit13);
                    unit14.setText(sUnit14);
                    unit15.setText(sUnit15);
                    unit16.setText(sUnit16);
                    unit17.setText(sUnit17);
                    unit18.setText(sUnit18);
                    unit19.setText(sUnit19);
                    unit20.setText(sUnit20);
                    unit21.setText(sUnit21);
                    unit22.setText(sUnit22);
                    unit23.setText(sUnit23);
                    unit24.setText(sUnit24);
                    unit25.setText(sUnit25);
                    unit26.setText(sUnit26);
                    unit27.setText(sUnit27);
                    unit28.setText(sUnit28);
                    unit29.setText(sUnit29);
                    unit30.setText(sUnit30);


                    assert sQuantity1 != null;
                    if (!sQuantity1.isEmpty()){
                        fQuantity1 = Float.parseFloat(sQuantity1);
                        float qu = fQuantity1*fQuantity;
                        quantity1.setText(String.valueOf(qu));
                    }
                    else {
                        quantity1.setText("");
                    }

                    assert sQuantity2 != null;
                    if (!sQuantity2.isEmpty()){
                        fQuantity2 = Float.parseFloat(sQuantity2);
                        float qu = fQuantity2*fQuantity;
                        quantity2.setText(String.valueOf(qu));
                    }
                    else {
                        quantity2.setText("");
                    }

                    assert sQuantity3 != null;
                    if (!sQuantity3.isEmpty()){
                        fQuantity3 = Float.parseFloat(sQuantity3);
                        float qu = fQuantity3*fQuantity;
                        quantity3.setText(String.valueOf(qu));
                    }
                    else {
                        quantity3.setText("");
                    }

                    assert sQuantity4 != null;
                    if (!sQuantity4.isEmpty()){
                        fQuantity4 = Float.parseFloat(sQuantity4);
                        float qu = fQuantity4*fQuantity;
                        quantity4.setText(String.valueOf(qu));
                    }
                    else {
                        quantity4.setText("");
                    }

                    assert sQuantity5 != null;
                    if (!sQuantity5.isEmpty()){
                        fQuantity5 = Float.parseFloat(sQuantity5);
                        float qu = fQuantity5*fQuantity;
                        quantity5.setText(String.valueOf(qu));
                    }
                    else {
                        quantity5.setText("");
                    }

                    assert sQuantity6 != null;
                    if (!sQuantity6.isEmpty()){
                        fQuantity6 = Float.parseFloat(sQuantity6);
                        float qu = fQuantity6*fQuantity;
                        quantity6.setText(String.valueOf(qu));
                    }
                    else {
                        quantity6.setText("");
                    }

                    assert sQuantity7 != null;
                    if (!sQuantity7.isEmpty()){
                        fQuantity7 = Float.parseFloat(sQuantity7);
                        float qu = fQuantity7*fQuantity;
                        quantity7.setText(String.valueOf(qu));
                    }
                    else {
                        quantity7.setText("");
                    }

                    assert sQuantity8 != null;
                    if (!sQuantity8.isEmpty()){
                        fQuantity8 = Float.parseFloat(sQuantity8);
                        float qu = fQuantity8*fQuantity;
                        quantity8.setText(String.valueOf(qu));
                    }
                    else {
                        quantity8.setText("");
                    }

                    assert sQuantity9 != null;
                    if (!sQuantity9.isEmpty()){
                        fQuantity9 = Float.parseFloat(sQuantity9);
                        float qu = fQuantity9*fQuantity;
                        quantity9.setText(String.valueOf(qu));
                    }
                    else {
                        quantity9.setText("");
                    }

                    assert sQuantity10 != null;
                    if (!sQuantity10.isEmpty()){
                        fQuantity10 = Float.parseFloat(sQuantity10);
                        float qu = fQuantity10*fQuantity;
                        quantity10.setText(String.valueOf(qu));
                    }
                    else {
                        quantity10.setText("");
                    }

                    assert sQuantity11 != null;
                    if (!sQuantity11.isEmpty()){
                        fQuantity11 = Float.parseFloat(sQuantity11);
                        float qu = fQuantity11*fQuantity;
                        quantity11.setText(String.valueOf(qu));
                    }
                    else {
                        quantity11.setText("");
                    }



                    assert sQuantity12 != null;
                    if (!sQuantity12.isEmpty()){
                        fQuantity12 = Float.parseFloat(sQuantity12);
                        float qu = fQuantity12*fQuantity;
                        quantity12.setText(String.valueOf(qu));
                    }
                    else {
                        quantity12.setText("");
                    }

                    assert sQuantity13 != null;
                    if (!sQuantity13.isEmpty()){
                        fQuantity13 = Float.parseFloat(sQuantity13);
                        float qu = fQuantity13*fQuantity;
                        quantity13.setText(String.valueOf(qu));
                    }
                    else {
                        quantity13.setText("");
                    }

                    assert sQuantity14 != null;
                    if (!sQuantity14.isEmpty()){
                        fQuantity14 = Float.parseFloat(sQuantity14);
                        float qu = fQuantity14*fQuantity;
                        quantity14.setText(String.valueOf(qu));
                    }
                    else {
                        quantity14.setText("");
                    }

                    assert sQuantity15 != null;
                    if (!sQuantity15.isEmpty()){
                        fQuantity15 = Float.parseFloat(sQuantity15);
                        float qu = fQuantity15*fQuantity;
                        quantity15.setText(String.valueOf(qu));
                    }
                    else {
                        quantity15.setText("");
                    }

                    assert sQuantity16 != null;
                    if (!sQuantity16.isEmpty()){
                        fQuantity16 = Float.parseFloat(sQuantity16);
                        float qu = fQuantity16*fQuantity;
                        quantity16.setText(String.valueOf(qu));
                    }
                    else {
                        quantity16.setText("");
                    }

                    assert sQuantity17 != null;
                    if (!sQuantity17.isEmpty()){
                        fQuantity17 = Float.parseFloat(sQuantity17);
                        float qu = fQuantity17*fQuantity;
                        quantity17.setText(String.valueOf(qu));
                    }
                    else {
                        quantity17.setText("");
                    }

                    assert sQuantity18 != null;
                    if (!sQuantity18.isEmpty()){
                        fQuantity18 = Float.parseFloat(sQuantity18);
                        float qu = fQuantity18*fQuantity;
                        quantity18.setText(String.valueOf(qu));
                    }
                    else {
                        quantity18.setText("");
                    }

                    assert sQuantity19 != null;
                    if (!sQuantity19.isEmpty()){
                        fQuantity19 = Float.parseFloat(sQuantity19);
                        float qu = fQuantity19*fQuantity;
                        quantity19.setText(String.valueOf(qu));
                    }
                    else {
                        quantity19.setText("");
                    }

                    assert sQuantity20 != null;
                    if (!sQuantity20.isEmpty()){
                        fQuantity20 = Float.parseFloat(sQuantity20);
                        float qu = fQuantity20*fQuantity;
                        quantity20.setText(String.valueOf(qu));
                    }
                    else {
                        quantity20.setText("");
                    }

                    assert sQuantity21 != null;
                    if (!sQuantity21.isEmpty()){
                        fQuantity21 = Float.parseFloat(sQuantity21);
                        float qu = fQuantity21*fQuantity;
                        quantity21.setText(String.valueOf(qu));
                    }
                    else {
                        quantity21.setText("");
                    }

                    assert sQuantity22 != null;
                    if (!sQuantity22.isEmpty()){
                        fQuantity22 = Float.parseFloat(sQuantity22);
                        float qu = fQuantity22*fQuantity;
                        quantity22.setText(String.valueOf(qu));
                    }
                    else {
                        quantity22.setText("");
                    }

                    assert sQuantity23 != null;
                    if (!sQuantity23.isEmpty()){
                        fQuantity23 = Float.parseFloat(sQuantity23);
                        float qu = fQuantity23*fQuantity;
                        quantity23.setText(String.valueOf(qu));
                    }
                    else {
                        quantity23.setText("");
                    }

                    assert sQuantity24 != null;
                    if (!sQuantity24.isEmpty()){
                        fQuantity24 = Float.parseFloat(sQuantity24);
                        float qu = fQuantity24*fQuantity;
                        quantity24.setText(String.valueOf(qu));
                    }
                    else {
                        quantity24.setText("");
                    }

                    assert sQuantity25 != null;
                    if (!sQuantity25.isEmpty()){
                        fQuantity25 = Float.parseFloat(sQuantity25);
                        float qu = fQuantity25*fQuantity;
                        quantity25.setText(String.valueOf(qu));
                    }
                    else {
                        quantity25.setText("");
                    }

                    assert sQuantity26 != null;
                    if (!sQuantity26.isEmpty()){
                        fQuantity26 = Float.parseFloat(sQuantity26);
                        float qu = fQuantity26*fQuantity;
                        quantity26.setText(String.valueOf(qu));
                    }
                    else {
                        quantity26.setText("");
                    }

                    assert sQuantity27 != null;
                    if (!sQuantity27.isEmpty()){
                        fQuantity27 = Float.parseFloat(sQuantity27);
                        float qu = fQuantity27*fQuantity;
                        quantity27.setText(String.valueOf(qu));
                    }
                    else {
                        quantity27.setText("");
                    }

                    assert sQuantity28 != null;
                    if (!sQuantity28.isEmpty()){
                        fQuantity28 = Float.parseFloat(sQuantity28);
                        float qu = fQuantity28*fQuantity;
                        quantity28.setText(String.valueOf(qu));
                    }
                    else {
                        quantity28.setText("");
                    }

                    assert sQuantity29 != null;
                    if (!sQuantity29.isEmpty()){
                        fQuantity29 = Float.parseFloat(sQuantity29);
                        float qu = fQuantity29*fQuantity;
                        quantity29.setText(String.valueOf(qu));
                    }
                    else {
                        quantity29.setText("");
                    }

                    assert sQuantity30 != null;
                    if (!sQuantity30.isEmpty()){
                        fQuantity30 = Float.parseFloat(sQuantity30);
                        float qu = fQuantity30*fQuantity;
                        quantity30.setText(String.valueOf(qu));
                    }
                    else {
                        quantity30.setText("");
                    }

                }
                else {
                    Toast.makeText(CalculaterInApp.this, "Please select correct material or unit !!", Toast.LENGTH_SHORT).show();
                    materialList.setVisibility(View.GONE);
                    material1.setText("");
                    material2.setText("");
                    material3.setText("");
                    material4.setText("");
                    material5.setText("");
                    material6.setText("");
                    material7.setText("");
                    material8.setText("");
                    material9.setText("");
                    material10.setText("");
                    material11.setText("");
                    material12.setText("");
                    material13.setText("");
                    material14.setText("");
                    material15.setText("");
                    material16.setText("");
                    material18.setText("");
                    material19.setText("");
                    material20.setText("");
                    material21.setText("");
                    material22.setText("");
                    material23.setText("");
                    material24.setText("");
                    material25.setText("");
                    material26.setText("");
                    material27.setText("");
                    material28.setText("");
                    material29.setText("");
                    material30.setText("");

                    unit1.setText("");
                    unit2.setText("");
                    unit3.setText("");
                    unit4.setText("");
                    unit5.setText("");
                    unit6.setText("");
                    unit7.setText("");
                    unit8.setText("");
                    unit9.setText("");
                    unit10.setText("");
                    unit11.setText("");
                    unit12.setText("");
                    unit13.setText("");
                    unit14.setText("");
                    unit15.setText("");
                    unit16.setText("");
                    unit18.setText("");
                    unit19.setText("");
                    unit20.setText("");
                    unit21.setText("");
                    unit22.setText("");
                    unit23.setText("");
                    unit24.setText("");
                    unit25.setText("");
                    unit26.setText("");
                    unit27.setText("");
                    unit28.setText("");
                    unit29.setText("");
                    unit30.setText("");

                    quantity1.setText("");
                    quantity2.setText("");
                    quantity3.setText("");
                    quantity4.setText("");
                    quantity5.setText("");
                    quantity6.setText("");
                    quantity7.setText("");
                    quantity8.setText("");
                    quantity9.setText("");
                    quantity10.setText("");
                    quantity11.setText("");
                    quantity12.setText("");
                    quantity13.setText("");
                    quantity14.setText("");
                    quantity15.setText("");
                    quantity16.setText("");
                    quantity18.setText("");
                    quantity19.setText("");
                    quantity20.setText("");
                    quantity21.setText("");
                    quantity22.setText("");
                    quantity23.setText("");
                    quantity24.setText("");
                    quantity25.setText("");
                    quantity26.setText("");
                    quantity27.setText("");
                    quantity28.setText("");
                    quantity29.setText("");
                    quantity30.setText("");
                }
//                return false;
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