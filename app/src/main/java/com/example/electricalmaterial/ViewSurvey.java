package com.example.electricalmaterial;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class ViewSurvey extends AppCompatActivity {

    //Authorization
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String companyEmail;
    String userId;
    String cmp;

    TextView date,surveyorName,line,tender,linemanName,consumerName,siteName,center,village,state,taluka,district,note;
    TextView material1,unit1,quantity1;
    TextView material2,unit2,quantity2;
    TextView material3,unit3,quantity3;
    TextView material4,unit4,quantity4;
    TextView material5,unit5,quantity5;
    TextView material6,unit6,quantity6;
    TextView material7,unit7,quantity7;
    TextView material8,unit8,quantity8;
    TextView material9,unit9,quantity9;
    TextView material10,unit10,quantity10;
    TextView material11,unit11,quantity11;
    TextView material12,unit12,quantity12;
    TextView material13,unit13,quantity13;
    TextView material14,unit14,quantity14;
    TextView material15,unit15,quantity15;
    TextView material16,unit16,quantity16;
    TextView material17,unit17,quantity17;
    TextView material18,unit18,quantity18;
    TextView material19,unit19,quantity19;
    TextView material20,unit20,quantity20;
    TextView material21,unit21,quantity21;
    TextView material22,unit22,quantity22;
    TextView material23,unit23,quantity23;
    TextView material24,unit24,quantity24;
    TextView material25,unit25,quantity25;
    TextView material26,unit26,quantity26;
    TextView material27,unit27,quantity27;
    TextView material28,unit28,quantity28;
    TextView material29,unit29,quantity29;
    TextView material30,unit30,quantity30;

    String id,dateS,surveyorNameS,lineS,tenderS,linemanNameS,consumerNameS,siteNameS,centerS,villageS,stateS,talukaS,districtS,noteS;

    String material1S,unit1S,quantity1S;
    String material2S,unit2S,quantity2S;
    String material3S,unit3S,quantity3S;
    String material4S,unit4S,quantity4S;
    String material5S,unit5S,quantity5S;
    String material6S,unit6S,quantity6S;
    String material7S,unit7S,quantity7S;
    String material8S,unit8S,quantity8S;
    String material9S,unit9S,quantity9S;
    String material10S,unit10S,quantity10S;
    String material11S,unit11S,quantity11S;
    String material12S,unit12S,quantity12S;
    String material13S,unit13S,quantity13S;
    String material14S,unit14S,quantity14S;
    String material15S,unit15S,quantity15S;
    String material16S,unit16S,quantity16S;
    String material17S,unit17S,quantity17S;
    String material18S,unit18S,quantity18S;
    String material19S,unit19S,quantity19S;
    String material20S,unit20S,quantity20S;
    String material21S,unit21S,quantity21S;
    String material22S,unit22S,quantity22S;
    String material23S,unit23S,quantity23S;
    String material24S,unit24S,quantity24S;
    String material25S,unit25S,quantity25S;
    String material26S,unit26S,quantity26S;
    String material27S,unit27S,quantity27S;
    String material28S,unit28S,quantity28S;
    String material29S,unit29S,quantity29S;
    String material30S,unit30S,quantity30S;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_survey);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


        //Auth
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = mAuth.getCurrentUser().getUid();


        date = findViewById(R.id.dateTV);
        surveyorName = findViewById(R.id.surveyorNameTV);
        line = findViewById(R.id.lineTV);
        tender = findViewById(R.id.tenderTV);
        note = findViewById(R.id.noteTV);
        linemanName = findViewById(R.id.linemanNameTV);
        consumerName = findViewById(R.id.consumerNameTV);
        siteName = findViewById(R.id.siteTV);
        state = findViewById(R.id.stateTV);
        district = findViewById(R.id.districtTV);
        taluka = findViewById(R.id.talukaTV);
        center = findViewById(R.id.centerTV);
        village = findViewById(R.id.villageTV);

        //Material
        material1 = findViewById(R.id.material1);
        unit1 = findViewById(R.id.unit1);
        quantity1 = findViewById(R.id.quantity1);

        material2 = findViewById(R.id.material2);
        unit2 = findViewById(R.id.unit2);
        quantity2 = findViewById(R.id.quantity2);

        material3 = findViewById(R.id.material3);
        unit3 = findViewById(R.id.unit3);
        quantity3 = findViewById(R.id.quantity3);

        material4 = findViewById(R.id.material4);
        unit4 = findViewById(R.id.unit4);
        quantity4 = findViewById(R.id.quantity4);

        material5 = findViewById(R.id.material5);
        unit5 = findViewById(R.id.unit5);
        quantity5 = findViewById(R.id.quantity5);

        material6 = findViewById(R.id.material6);
        unit6 = findViewById(R.id.unit6);
        quantity6 = findViewById(R.id.quantity6);

        material7 = findViewById(R.id.material7);
        unit7 = findViewById(R.id.unit7);
        quantity7 = findViewById(R.id.quantity7);

        material8 = findViewById(R.id.material8);
        unit8 = findViewById(R.id.unit8);
        quantity8 = findViewById(R.id.quantity8);

        material9 = findViewById(R.id.material9);
        unit9 = findViewById(R.id.unit9);
        quantity9 = findViewById(R.id.quantity9);

        material10 = findViewById(R.id.material10);
        unit10 = findViewById(R.id.unit10);
        quantity10 = findViewById(R.id.quantity10);


        material11 = findViewById(R.id.material11);
        unit11 = findViewById(R.id.unit11);
        quantity11 = findViewById(R.id.quantity11t);

        material12 = findViewById(R.id.material12);
        unit12 = findViewById(R.id.unit12);
        quantity12 = findViewById(R.id.quantity12);

        material13 = findViewById(R.id.material13);
        unit13 = findViewById(R.id.unit13);
        quantity13 = findViewById(R.id.quantity13);

        material14 = findViewById(R.id.material14);
        unit14 = findViewById(R.id.unit14);
        quantity14 = findViewById(R.id.quantity14);

        material15 = findViewById(R.id.material15);
        unit15 = findViewById(R.id.unit15);
        quantity15 = findViewById(R.id.quantity15);

        material16 = findViewById(R.id.material16);
        unit16 = findViewById(R.id.unit16);
        quantity16 = findViewById(R.id.quantity16);

        material17 = findViewById(R.id.material17);
        unit17 = findViewById(R.id.unit17);
        quantity17 = findViewById(R.id.quantity17);

        material18 = findViewById(R.id.material18);
        unit18 = findViewById(R.id.unit18);
        quantity18 = findViewById(R.id.quantity18);

        material19 = findViewById(R.id.material19);
        unit19 = findViewById(R.id.unit19);
        quantity19 = findViewById(R.id.quantity19);

        material20 = findViewById(R.id.material20);
        unit20 = findViewById(R.id.unit20);
        quantity20 = findViewById(R.id.quantity20);

        material21 = findViewById(R.id.material21);
        unit21 = findViewById(R.id.unit21);
        quantity21 = findViewById(R.id.quantity21);

        material22 = findViewById(R.id.material22);
        unit22 = findViewById(R.id.unit22);
        quantity22 = findViewById(R.id.quantity22);

        material23 = findViewById(R.id.material23);
        unit23 = findViewById(R.id.unit23);
        quantity23 = findViewById(R.id.quantity23);

        material24 = findViewById(R.id.material24);
        unit24 = findViewById(R.id.unit24);
        quantity24 = findViewById(R.id.quantity24);

        material25 = findViewById(R.id.material25);
        unit25 = findViewById(R.id.unit25);
        quantity25 = findViewById(R.id.quantity25);

        material26 = findViewById(R.id.material26);
        unit26 = findViewById(R.id.unit26);
        quantity26 = findViewById(R.id.quantity26);

        material27 = findViewById(R.id.material27);
        unit27 = findViewById(R.id.unit27);
        quantity27 = findViewById(R.id.quantity27);

        material28 = findViewById(R.id.material28);
        unit28 = findViewById(R.id.unit28);
        quantity28 = findViewById(R.id.quantity28);

        material29 = findViewById(R.id.material29);
        unit29 = findViewById(R.id.unit29);
        quantity29 = findViewById(R.id.quantity29);

        material30 = findViewById(R.id.material30);
        unit30 = findViewById(R.id.unit30);
        quantity30 = findViewById(R.id.quantity30);

        ActionBar bar = getSupportActionBar();
        assert bar != null;
        bar.hide();

        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            id = bundle.getString("Id");
            dateS = bundle.getString("Date");
            surveyorNameS = bundle.getString("Surveyor Name");
            lineS = bundle.getString("Line");
            tenderS = bundle.getString("Tender");
            consumerNameS = bundle.getString("Consumer Name");
            siteNameS = bundle.getString("Site");
            centerS = bundle.getString("Center");
            villageS = bundle.getString("Village");

            linemanNameS = bundle.getString("lineman");
            talukaS = bundle.getString("Taluka");
            districtS = bundle.getString("District");
            stateS = bundle.getString("State");
            noteS = bundle.getString("Note");



            //Material
            material1S = bundle.getString("Material1");
            unit1S = bundle.getString("Unit1");
            quantity1S = bundle.getString("Quantity1");

            material2S = bundle.getString("Material2");
            unit2S = bundle.getString("Unit2");
            quantity2S = bundle.getString("Quantity2");

            material3S = bundle.getString("Material3");
            unit3S = bundle.getString("Unit3");
            quantity3S = bundle.getString("Quantity3");

            material4S = bundle.getString("Material4");
            unit4S = bundle.getString("Unit4");
            quantity4S = bundle.getString("Quantity4");

            material5S = bundle.getString("Material5");
            unit5S = bundle.getString("Unit5");
            quantity5S = bundle.getString("Quantity5");

            material6S = bundle.getString("Material6");
            unit6S = bundle.getString("Unit6");
            quantity6S = bundle.getString("Quantity6");

            material7S = bundle.getString("Material7");
            unit7S = bundle.getString("Unit7");
            quantity7S = bundle.getString("Quantity7");

            material8S = bundle.getString("Material8");
            unit8S = bundle.getString("Unit8");
            quantity8S = bundle.getString("Quantity8");

            material9S = bundle.getString("Material9");
            unit9S = bundle.getString("Unit9");
            quantity9S = bundle.getString("Quantity9");

            material10S = bundle.getString("Material10");
            unit10S = bundle.getString("Unit10");
            quantity10S = bundle.getString("Quantity10");

            material11S = bundle.getString("Material11");
            unit11S = bundle.getString("Unit11");
            quantity11S = bundle.getString("Quantity11");

            material12S = bundle.getString("Material12");
            unit12S = bundle.getString("Unit12");
            quantity12S = bundle.getString("Quantity12");

            material13S = bundle.getString("Material13");
            unit13S = bundle.getString("Unit13");
            quantity13S = bundle.getString("Quantity13");

            material14S = bundle.getString("Material14");
            unit14S = bundle.getString("Unit14");
            quantity14S = bundle.getString("Quantity14");

            material15S = bundle.getString("Material15");
            unit15S = bundle.getString("Unit15");
            quantity15S = bundle.getString("Quantity15");

            material16S = bundle.getString("Material16");
            unit16S = bundle.getString("Unit16");
            quantity16S = bundle.getString("Quantity16");

            material17S = bundle.getString("Material17");
            unit17S = bundle.getString("Unit17");
            quantity17S = bundle.getString("Quantity17");

            material18S = bundle.getString("Material18");
            unit18S = bundle.getString("Unit18");
            quantity18S = bundle.getString("Quantity18");

            material19S = bundle.getString("Material19");
            unit19S = bundle.getString("Unit19");
            quantity19S = bundle.getString("Quantity19");

            material20S = bundle.getString("Material20");
            unit20S = bundle.getString("Unit20");
            quantity20S = bundle.getString("Quantity20");

            material21S = bundle.getString("Material21");
            unit21S = bundle.getString("Unit21");
            quantity21S = bundle.getString("Quantity21");

            material22S = bundle.getString("Material22");
            unit22S = bundle.getString("Unit22");
            quantity22S = bundle.getString("Quantity22");

            material23S = bundle.getString("Material23");
            unit23S = bundle.getString("Unit23");
            quantity23S = bundle.getString("Quantity23");

            material24S = bundle.getString("Material24");
            unit24S = bundle.getString("Unit24");
            quantity24S = bundle.getString("Quantity24");

            material25S = bundle.getString("Material25");
            unit25S = bundle.getString("Unit25");
            quantity25S = bundle.getString("Quantity25");

            material26S = bundle.getString("Material26");
            unit26S = bundle.getString("Unit26");
            quantity26S = bundle.getString("Quantity26");

            material27S = bundle.getString("Material27");
            unit27S = bundle.getString("Unit27");
            quantity27S = bundle.getString("Quantity27");

            material28S = bundle.getString("Material28");
            unit28S = bundle.getString("Unit28");
            quantity28S = bundle.getString("Quantity28");

            material29S = bundle.getString("Material29");
            unit29S = bundle.getString("Unit29");
            quantity29S = bundle.getString("Quantity29");

            material30S = bundle.getString("Material30");
            unit30S = bundle.getString("Unit30");
            quantity30S = bundle.getString("Quantity30");






        }

        date.setText(dateS);
        surveyorName.setText(surveyorNameS);
        line.setText(lineS);
        tender.setText(tenderS);
        linemanName.setText(linemanNameS);
        state.setText(stateS);
        consumerName.setText(consumerNameS);
        siteName.setText(siteNameS);
        district.setText(districtS);
        center.setText(centerS);
        village.setText(villageS);
        taluka.setText(talukaS);
        note.setText(noteS);



        //Material

        material1.setText(material1S);
        unit1.setText(unit1S);
        quantity1.setText(quantity1S);

        material2.setText(material2S);
        unit2.setText(unit2S);
        quantity2.setText(quantity2S);

        material3.setText(material3S);
        unit3.setText(unit3S);
        quantity3.setText(quantity3S);

        material4.setText(material4S);
        unit4.setText(unit4S);
        quantity4.setText(quantity4S);

        material5.setText(material5S);
        unit5.setText(unit5S);
        quantity5.setText(quantity5S);

        material6.setText(material6S);
        unit6.setText(unit6S);
        quantity6.setText(quantity6S);

        material7.setText(material7S);
        unit7.setText(unit7S);
        quantity7.setText(quantity7S);

        material8.setText(material8S);
        unit8.setText(unit8S);
        quantity8.setText(quantity8S);

        material9.setText(material9S);
        unit9.setText(unit9S);
        quantity9.setText(quantity9S);

        material10.setText(material10S);
        unit10.setText(unit10S);
        quantity10.setText(quantity10S);

        material11.setText(material11S);
        unit11.setText(unit11S);
        quantity11.setText(quantity11S);

        material12.setText(material12S);
        unit12.setText(unit12S);
        quantity12.setText(quantity12S);

        material13.setText(material13S);
        unit13.setText(unit13S);
        quantity13.setText(quantity13S);

        material14.setText(material14S);
        unit14.setText(unit14S);
        quantity14.setText(quantity14S);

        material15.setText(material15S);
        unit15.setText(unit15S);
        quantity15.setText(quantity15S);

        material16.setText(material16S);
        unit16.setText(unit16S);
        quantity16.setText(quantity16S);

        material17.setText(material17S);
        unit17.setText(unit17S);
        quantity17.setText(quantity17S);

        material18.setText(material18S);
        unit18.setText(unit18S);
        quantity18.setText(quantity18S);

        material19.setText(material19S);
        unit19.setText(unit19S);
        quantity19.setText(quantity19S);

        material20.setText(material20S);
        unit20.setText(unit20S);
        quantity20.setText(quantity20S);

        material21.setText(material21S);
        unit21.setText(unit21S);
        quantity21.setText(quantity21S);

        material22.setText(material22S);
        unit22.setText(unit22S);
        quantity22.setText(quantity22S);

        material23.setText(material23S);
        unit23.setText(unit23S);
        quantity23.setText(quantity23S);

        material24.setText(material24S);
        unit24.setText(unit24S);
        quantity24.setText(quantity24S);

        material25.setText(material25S);
        unit25.setText(unit25S);
        quantity25.setText(quantity25S);

        material26.setText(material26S);
        unit26.setText(unit26S);
        quantity26.setText(quantity26S);

        material27.setText(material27S);
        unit27.setText(unit27S);
        quantity27.setText(quantity27S);

        material28.setText(material28S);
        unit28.setText(unit28S);
        quantity28.setText(quantity28S);

        material29.setText(material29S);
        unit29.setText(unit29S);
        quantity29.setText(quantity29S);

        material30.setText(material30S);
        unit30.setText(unit30S);
        quantity30.setText(quantity30S);



        String material [] = {
                material1S,material2S,material3S,material4S,material5S,material6S,material7S,material8S,material9S,material10S,
                material11S,material12S,material13S,material14S,material15S,material16S,material17S,material18S,material19S,material20S,
                material21S,material22S,material23S,material24S,material25S,material26S,material27S,material28S,material29S,material30S
        };





        String unit [] = {
                unit1S,unit2S,unit3S,unit4S,unit5S,unit6S,unit7S,unit8S,unit9S,unit10S,
                unit11S,unit12S,unit13S,unit14S,unit15S,unit16S,unit17S,unit18S,unit19S,unit20S,
                unit21S,unit22S,unit23S,unit24S,unit25S,unit26S,unit27S,unit28S,unit29S,unit30S
        };


        DocumentReference documentReference = fStore.collection("Users")
                .document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                companyEmail = value.getString("companyEmail");
                cmp = companyEmail;
                companyEmail = companyEmail.replace("@", "");
                companyEmail = companyEmail.replace(".", "");
                for (int i=0;i<material.length;i++){
                    if (!material[i].isEmpty()) {
                        dis(cmp, material[i], i,unit[i]);
                    }
                }


            }

        });

    }

    public void dis(String cmp,String material,int i,String unit){
        DocumentReference a = fStore.collection(cmp+" MaterialPair")
                .document(material);
        a.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                String DMaterial = value.getString("Material");
                String DUnit = value.getString("Unit");

                if (material.equals(DMaterial) && unit.equals(DUnit) ){

                    if (i==0){
                        material1.setTextColor(Color.parseColor("#0000FF"));
                        unit1.setTextColor(Color.parseColor("#0000FF"));
                        quantity1.setTextColor(Color.parseColor("#0000FF"));
                        material1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String material = material1.getText().toString().trim();
                                String unit = unit1.getText().toString().trim();
                                String quantity = quantity1.getText().toString().trim();
                                show(material,unit,quantity);
                            }
                        });
                    }
                    if (i==1){
                        material2.setTextColor(Color.parseColor("#0000FF"));
                        unit2.setTextColor(Color.parseColor("#0000FF"));
                        quantity2.setTextColor(Color.parseColor("#0000FF"));
                        material2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String material = material2.getText().toString().trim();
                                String unit = unit2.getText().toString().trim();
                                String quantity = quantity2.getText().toString().trim();
                                show(material,unit,quantity);
                            }
                        });
                    }
                    if (i==2){
                        material3.setTextColor(Color.parseColor("#0000FF"));
                        unit3.setTextColor(Color.parseColor("#0000FF"));
                        quantity3.setTextColor(Color.parseColor("#0000FF"));
                        material3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String material = material3.getText().toString().trim();
                                String unit = unit3.getText().toString().trim();
                                String quantity = quantity3.getText().toString().trim();
                                show(material,unit,quantity);
                            }
                        });
                    }
                    if (i==3){
                        material4.setTextColor(Color.parseColor("#0000FF"));
                        unit4.setTextColor(Color.parseColor("#0000FF"));
                        quantity4.setTextColor(Color.parseColor("#0000FF"));
                        material4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String material = material4.getText().toString().trim();
                                String unit = unit4.getText().toString().trim();
                                String quantity = quantity4.getText().toString().trim();
                                show(material,unit,quantity);
                            }
                        });
                    }
                    if (i==4){
                        material5.setTextColor(Color.parseColor("#0000FF"));
                        unit5.setTextColor(Color.parseColor("#0000FF"));
                        quantity5.setTextColor(Color.parseColor("#0000FF"));
                        material5.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String material = material5.getText().toString().trim();
                                String unit = unit5.getText().toString().trim();
                                String quantity = quantity5.getText().toString().trim();
                                show(material,unit,quantity);
                            }
                        });
                    }
                    if (i==5){
                        material6.setTextColor(Color.parseColor("#0000FF"));
                        unit6.setTextColor(Color.parseColor("#0000FF"));
                        quantity6.setTextColor(Color.parseColor("#0000FF"));
                        material6.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String material = material6.getText().toString().trim();
                                String unit = unit6.getText().toString().trim();
                                String quantity = quantity6.getText().toString().trim();
                                show(material,unit,quantity);
                            }
                        });
                    }
                    if (i==6){
                        material7.setTextColor(Color.parseColor("#0000FF"));
                        unit7.setTextColor(Color.parseColor("#0000FF"));
                        quantity7.setTextColor(Color.parseColor("#0000FF"));
                        material7.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String material = material7.getText().toString().trim();
                                String unit = unit7.getText().toString().trim();
                                String quantity = quantity7.getText().toString().trim();
                                show(material,unit,quantity);
                            }
                        });
                    }
                    if (i==7){
                        material8.setTextColor(Color.parseColor("#0000FF"));
                        unit8.setTextColor(Color.parseColor("#0000FF"));
                        quantity8.setTextColor(Color.parseColor("#0000FF"));
                        material8.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String material = material8.getText().toString().trim();
                                String unit = unit8.getText().toString().trim();
                                String quantity = quantity8.getText().toString().trim();
                                show(material,unit,quantity);
                            }
                        });
                    }
                    if (i==8){
                        material9.setTextColor(Color.parseColor("#0000FF"));
                        unit9.setTextColor(Color.parseColor("#0000FF"));
                        quantity9.setTextColor(Color.parseColor("#0000FF"));
                        material9.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String material = material9.getText().toString().trim();
                                String unit = unit9.getText().toString().trim();
                                String quantity = quantity9.getText().toString().trim();
                                show(material,unit,quantity);
                            }
                        });
                    }
                    if (i==9){
                        material10.setTextColor(Color.parseColor("#0000FF"));
                        unit10.setTextColor(Color.parseColor("#0000FF"));
                        quantity10.setTextColor(Color.parseColor("#0000FF"));
                        material10.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String material = material10.getText().toString().trim();
                                String unit = unit10.getText().toString().trim();
                                String quantity = quantity10.getText().toString().trim();
                                show(material,unit,quantity);
                            }
                        });
                    }
                    if (i==10){
                        material11.setTextColor(Color.parseColor("#0000FF"));
                        unit11.setTextColor(Color.parseColor("#0000FF"));
                        quantity11.setTextColor(Color.parseColor("#0000FF"));
                        material11.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String material = material11.getText().toString().trim();
                                String unit = unit11.getText().toString().trim();
                                String quantity = quantity11.getText().toString().trim();
                                show(material,unit,quantity);
                            }
                        });
                    }
                    if (i==11){
                        material12.setTextColor(Color.parseColor("#0000FF"));
                        unit12.setTextColor(Color.parseColor("#0000FF"));
                        quantity12.setTextColor(Color.parseColor("#0000FF"));
                        material12.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String material = material12.getText().toString().trim();
                                String unit = unit12.getText().toString().trim();
                                String quantity = quantity12.getText().toString().trim();
                                show(material,unit,quantity);
                            }
                        });
                    }
                    if (i==12){
                        material13.setTextColor(Color.parseColor("#0000FF"));
                        unit13.setTextColor(Color.parseColor("#0000FF"));
                        quantity13.setTextColor(Color.parseColor("#0000FF"));
                        material13.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String material = material13.getText().toString().trim();
                                String unit = unit13.getText().toString().trim();
                                String quantity = quantity13.getText().toString().trim();
                                show(material,unit,quantity);
                            }
                        });
                    }
                    if (i==13){
                        material14.setTextColor(Color.parseColor("#0000FF"));
                        unit14.setTextColor(Color.parseColor("#0000FF"));
                        quantity14.setTextColor(Color.parseColor("#0000FF"));
                        material14.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String material = material14.getText().toString().trim();
                                String unit = unit14.getText().toString().trim();
                                String quantity = quantity14.getText().toString().trim();
                                show(material,unit,quantity);
                            }
                        });
                    }
                    if (i==14){
                        material15.setTextColor(Color.parseColor("#0000FF"));
                        unit15.setTextColor(Color.parseColor("#0000FF"));
                        quantity15.setTextColor(Color.parseColor("#0000FF"));
                        material15.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String material = material15.getText().toString().trim();
                                String unit = unit15.getText().toString().trim();
                                String quantity = quantity15.getText().toString().trim();
                                show(material,unit,quantity);
                            }
                        });
                    }
                    if (i==15){
                        material16.setTextColor(Color.parseColor("#0000FF"));
                        unit16.setTextColor(Color.parseColor("#0000FF"));
                        quantity16.setTextColor(Color.parseColor("#0000FF"));
                        material16.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String material = material16.getText().toString().trim();
                                String unit = unit16.getText().toString().trim();
                                String quantity = quantity16.getText().toString().trim();
                                show(material,unit,quantity);
                            }
                        });
                    }
                    if (i==16){
                        material17.setTextColor(Color.parseColor("#0000FF"));
                        unit17.setTextColor(Color.parseColor("#0000FF"));
                        quantity17.setTextColor(Color.parseColor("#0000FF"));
                        material17.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String material = material17.getText().toString().trim();
                                String unit = unit17.getText().toString().trim();
                                String quantity = quantity17.getText().toString().trim();
                                show(material,unit,quantity);
                            }
                        });
                    }
                    if (i==17){
                        material18.setTextColor(Color.parseColor("#0000FF"));
                        unit18.setTextColor(Color.parseColor("#0000FF"));
                        quantity18.setTextColor(Color.parseColor("#0000FF"));
                        material18.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String material = material18.getText().toString().trim();
                                String unit = unit18.getText().toString().trim();
                                String quantity = quantity18.getText().toString().trim();
                                show(material,unit,quantity);
                            }
                        });
                    }
                    if (i==18){
                        material19.setTextColor(Color.parseColor("#0000FF"));
                        unit19.setTextColor(Color.parseColor("#0000FF"));
                        quantity19.setTextColor(Color.parseColor("#0000FF"));
                        material19.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String material = material19.getText().toString().trim();
                                String unit = unit19.getText().toString().trim();
                                String quantity = quantity19.getText().toString().trim();
                                show(material,unit,quantity);
                            }
                        });
                    }
                    if (i==19){
                        material20.setTextColor(Color.parseColor("#0000FF"));
                        unit20.setTextColor(Color.parseColor("#0000FF"));
                        quantity20.setTextColor(Color.parseColor("#0000FF"));
                        material20.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String material = material20.getText().toString().trim();
                                String unit = unit20.getText().toString().trim();
                                String quantity = quantity20.getText().toString().trim();
                                show(material,unit,quantity);
                            }
                        });
                    }
                    if (i==20){
                        material21.setTextColor(Color.parseColor("#0000FF"));
                        unit21.setTextColor(Color.parseColor("#0000FF"));
                        quantity21.setTextColor(Color.parseColor("#0000FF"));
                        material21.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String material = material21.getText().toString().trim();
                                String unit = unit21.getText().toString().trim();
                                String quantity = quantity21.getText().toString().trim();
                                show(material,unit,quantity);
                            }
                        });
                    }
                    if (i==21){
                        material22.setTextColor(Color.parseColor("#0000FF"));
                        unit22.setTextColor(Color.parseColor("#0000FF"));
                        quantity22.setTextColor(Color.parseColor("#0000FF"));
                        material22.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String material = material22.getText().toString().trim();
                                String unit = unit22.getText().toString().trim();
                                String quantity = quantity22.getText().toString().trim();
                                show(material,unit,quantity);
                            }
                        });
                    }
                    if (i==22){
                        material23.setTextColor(Color.parseColor("#0000FF"));
                        unit23.setTextColor(Color.parseColor("#0000FF"));
                        quantity23.setTextColor(Color.parseColor("#0000FF"));
                        material23.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String material = material23.getText().toString().trim();
                                String unit = unit23.getText().toString().trim();
                                String quantity = quantity23.getText().toString().trim();
                                show(material,unit,quantity);
                            }
                        });
                    }
                    if (i==23){
                        material24.setTextColor(Color.parseColor("#0000FF"));
                        unit24.setTextColor(Color.parseColor("#0000FF"));
                        quantity24.setTextColor(Color.parseColor("#0000FF"));
                        material24.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String material = material24.getText().toString().trim();
                                String unit = unit24.getText().toString().trim();
                                String quantity = quantity24.getText().toString().trim();
                                show(material,unit,quantity);
                            }
                        });
                    }
                    if (i==24){
                        material25.setTextColor(Color.parseColor("#0000FF"));
                        unit25.setTextColor(Color.parseColor("#0000FF"));
                        quantity25.setTextColor(Color.parseColor("#0000FF"));
                        material25.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String material = material25.getText().toString().trim();
                                String unit = unit25.getText().toString().trim();
                                String quantity = quantity25.getText().toString().trim();
                                show(material,unit,quantity);
                            }
                        });
                    }
                    if (i==25){
                        material26.setTextColor(Color.parseColor("#0000FF"));
                        unit26.setTextColor(Color.parseColor("#0000FF"));
                        quantity26.setTextColor(Color.parseColor("#0000FF"));
                        material26.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String material = material26.getText().toString().trim();
                                String unit = unit26.getText().toString().trim();
                                String quantity = quantity26.getText().toString().trim();
                                show(material,unit,quantity);
                            }
                        });
                    }
                    if (i==26){
                        material27.setTextColor(Color.parseColor("#0000FF"));
                        unit27.setTextColor(Color.parseColor("#0000FF"));
                        quantity27.setTextColor(Color.parseColor("#0000FF"));
                        material27.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String material = material27.getText().toString().trim();
                                String unit = unit27.getText().toString().trim();
                                String quantity = quantity27.getText().toString().trim();
                                show(material,unit,quantity);
                            }
                        });
                    }
                    if (i==27){
                        material28.setTextColor(Color.parseColor("#0000FF"));
                        unit28.setTextColor(Color.parseColor("#0000FF"));
                        quantity28.setTextColor(Color.parseColor("#0000FF"));
                        material28.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String material = material28.getText().toString().trim();
                                String unit = unit28.getText().toString().trim();
                                String quantity = quantity28.getText().toString().trim();
                                show(material,unit,quantity);
                            }
                        });
                    }
                    if (i==28){
                        material29.setTextColor(Color.parseColor("#0000FF"));
                        unit29.setTextColor(Color.parseColor("#0000FF"));
                        quantity29.setTextColor(Color.parseColor("#0000FF"));
                        material29.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String material = material29.getText().toString().trim();
                                String unit = unit29.getText().toString().trim();
                                String quantity = quantity29.getText().toString().trim();
                                show(material,unit,quantity);
                            }
                        });
                    }
                    if (i==29){
                        material30.setTextColor(Color.parseColor("#0000FF"));
                        unit30.setTextColor(Color.parseColor("#0000FF"));
                        quantity30.setTextColor(Color.parseColor("#0000FF"));
                        material30.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String material = material30.getText().toString().trim();
                                String unit = unit30.getText().toString().trim();
                                String quantity = quantity30.getText().toString().trim();
                                show(material,unit,quantity);
                            }
                        });
                    }


                }
            }
        });
    }

    private void show(String material,String unit, String quantity) {
        Intent intent = new Intent(getApplicationContext(),ViewSurveyMaterialList.class);
        intent.putExtra("Material",material);
        intent.putExtra("Unit",unit);
        intent.putExtra("Quantity",quantity);
        startActivity(intent);
    }
}