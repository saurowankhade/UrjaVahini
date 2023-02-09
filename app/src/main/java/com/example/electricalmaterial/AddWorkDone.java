package com.example.electricalmaterial;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.UUID;

public class AddWorkDone extends AppCompatActivity {

    String r = "Required!!";

    //authorization
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String companyEmail;
    String userId;
    String cmp;


    //LinerLayout
    LinearLayout homeLL;
    LinearLayout middleLL;
    LinearLayout lastOneLL;
    LinearLayout lastLL;
    LinearLayout animation;

    //date
    TextView mDateFormate;
    DatePickerDialog.OnDateSetListener onDateSetListener;
    String date;

    //team name
    AutoCompleteTextView teamName;
    ArrayAdapter<String> adapter;
    ArrayList<String> spinnerDataList;
    DatabaseReference databaseReference;
    ValueEventListener listener;

    //Line
    AutoCompleteTextView line;
    ArrayAdapter<String> adapterLine;
    ArrayList<String> spinnerDataListLine;
    DatabaseReference databaseReferenceLine;
    ValueEventListener listenerLine;

    //State
    AutoCompleteTextView state;
    ArrayAdapter<String> adapterState;
    ArrayList<String> spinnerDataListState;
    DatabaseReference databaseReferenceState;
    ValueEventListener listenerState;


    //District
    AutoCompleteTextView district;
    ArrayAdapter<String> adapterDistrict;
    ArrayList<String> spinnerDataListDistrict;
    DatabaseReference databaseReferenceDistrict;
    ValueEventListener listenerDistrict;


    //Taluka
    AutoCompleteTextView taluka;
    ArrayAdapter<String> adapterTaluka;
    ArrayList<String> spinnerDataListTaluka;
    DatabaseReference databaseReferenceTaluka;
    ValueEventListener listenerTaluka;

    //Tender
    AutoCompleteTextView tender;
    ArrayAdapter<String> adapterTender;
    ArrayList<String> spinnerDataListTender;
    DatabaseReference databaseReferenceTender;
    ValueEventListener listenerTender;

    //Center
    AutoCompleteTextView center;
    ArrayAdapter<String> adapterCenter;
    ArrayList<String> spinnerDataListCenter;
    DatabaseReference databaseReferenceCenter;
    ValueEventListener listenerCenter;

    //Village
    AutoCompleteTextView village;
    ArrayAdapter<String> adapterVillage;
    ArrayList<String> spinnerDataListVillage;
    DatabaseReference databaseReferenceVillage;
    ValueEventListener listenerVillage;

    //Material
    AutoCompleteTextView material1,material2,material3,material4,material5,material6,material7,material8,material9,material10;
    AutoCompleteTextView material11,material12,material13,material14,material15,material16,material17,material18,material19,material20;
    AutoCompleteTextView material21,material22,material23,material24,material25,material26,material27,material28,material29,material30;
    AutoCompleteTextView material31,material32,material33,material34,material35,material36,material37,material38,material39,material40;
    ArrayAdapter<String> adapterMaterial;
    ArrayList<String> spinnerDataListMaterial;
    DatabaseReference databaseReferenceMaterial;
    ValueEventListener listenerMaterial;


    //Button Home
    MaterialButton homeNextBtn;
    MaterialButton homeBackBtn;
    MaterialButton middleNextBtn;
    MaterialButton middleBackBtn;
    MaterialButton nextLastTwo;
    MaterialButton doneBtn;
    MaterialButton lastBackBtn;
    MaterialButton doneAll;

    //TextInputEditText
    TextInputEditText site;



    TextInputEditText quantity1,quantity2,quantity3,quantity4,quantity5,quantity6,quantity7,quantity8,quantity9,quantity10;
    TextInputEditText quantity11,quantity12,quantity13,quantity14,quantity15,quantity16,quantity17,quantity18,quantity19,quantity20;
    TextInputEditText quantity21,quantity22,quantity23,quantity24,quantity25,quantity26,quantity27,quantity28,quantity29,quantity30;
    TextInputEditText quantity31,quantity32,quantity33,quantity34,quantity35,quantity36,quantity37,quantity38,quantity39,quantity40;

    TextInputEditText consumerName,note;

    LinearLayout addDataLL;
    ProgressBar progressBar;



    LinearLayout addMaterialLL1,addMaterialLL2,addMaterialLL3,addMaterialLL4,addMaterialLL5,addMaterialLL6,addMaterialLL7,addMaterialLL8;
    ImageView addIV1,addIV2,addIV3,addIV4,addIV5,addIV6,addIV7;

    String dateS,lineS,tenderS,consumerNameS,siteNameS,teamNameS;
    String districtS,talukaS,stateS,noteS;

    String material1S,quantity1S;
    String material2S,quantity2S;
    String material3S,quantity3S;
    String material4S,quantity4S;
    String material5S,quantity5S;
    String material6S,quantity6S;
    String material7S,quantity7S;
    String material8S,quantity8S;
    String material9S,quantity9S;
    String material10S,quantity10S;
    String material11S,quantity11S;
    String material12S,quantity12S;
    String material13S,quantity13S;
    String material14S,quantity14S;
    String material15S,quantity15S;
    String material16S,quantity16S;
    String material17S,quantity17S;
    String material18S,quantity18S;
    String material19S,quantity19S;
    String material20S,quantity20S;
    String material21S,quantity21S;
    String material22S,quantity22S;
    String material23S,quantity23S;
    String material24S,quantity24S;
    String material25S,quantity25S;
    String material26S,quantity26S;
    String material27S,quantity27S;
    String material28S,quantity28S;
    String material29S,quantity29S;
    String material30S,quantity30S;



    String material31S,quantity31S;
    String material32S,quantity32S;
    String material33S,quantity33S;
    String material34S,quantity34S;
    String material35S,quantity35S;
    String material36S,quantity36S;
    String material37S,quantity37S;
    String material38S,quantity38S;
    String material39S,quantity39S;
    String material40S,quantity40S;


    String centerSet,villageSet;

    Timer time;
//    ProgressDialog pd;

    MediaPlayer mediaPlayer;

    String id;


    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_work_done);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        id  = UUID.randomUUID().toString();

        //Auth
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = mAuth.getCurrentUser().getUid();

        mediaPlayer = MediaPlayer.create(this,R.raw.sound);

        //LinerLayout
        homeLL = findViewById(R.id.homeLL);
        middleLL = findViewById(R.id.middleLL);
        lastOneLL = findViewById(R.id.lastOneLL);
        lastLL = findViewById(R.id.lastLL);
        animation = findViewById(R.id.animation);

        addMaterialLL1 = findViewById(R.id.addMaterialLL1);
        addMaterialLL2 = findViewById(R.id.addMaterialLL2);
        addMaterialLL3 = findViewById(R.id.addMaterialLL3);
        addMaterialLL4 = findViewById(R.id.addMaterialLL4);
        addMaterialLL5 = findViewById(R.id.addMaterialLL5);
        addMaterialLL6 = findViewById(R.id.addMaterialLL6);
        addMaterialLL7 = findViewById(R.id.addMaterialLL7);
        addMaterialLL8 = findViewById(R.id.addMaterialLL8);

        addIV1 = findViewById(R.id.addIV1);
        addIV2 = findViewById(R.id.addIV2);
        addIV3 = findViewById(R.id.addIV3);
        addIV4 = findViewById(R.id.addIV4);
        addIV5 = findViewById(R.id.addIV5);
        addIV6 = findViewById(R.id.addIV6);
        addIV7 = findViewById(R.id.addIV7);

        //Date
        mDateFormate = findViewById(R.id.date);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        mDateFormate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        AddWorkDone.this, R.style.DatePicker1,
                        onDateSetListener,year,month,day);

                //Theme_Material_Light_Dialog_MinWidth

                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.rgb(230,223,223)));

                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });
        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                if (month<=9){ //
                    date  = year+"/"+"0"+month+"/"+dayOfMonth;
                }
                else if (dayOfMonth<=9){
                    date  = year+"/"+month+"/"+"0"+dayOfMonth;
                }
                else {
                    date  = year+"/"+month+"/"+dayOfMonth;
                }
                if (month<=9 && dayOfMonth<=9){
                    date  = year+"/"+"0"+month+"/"+"0"+dayOfMonth;
                }
                mDateFormate.setText(date);
            }
        };

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
        mDateFormate.setText(df.format(c));

        addDataLL = findViewById(R.id.addData);
        progressBar = findViewById(R.id.progressBar);

        //Team Name
        consumerName = findViewById(R.id.consumer);

        //team name
        teamName = findViewById(R.id.teamName);
        //Line
        line = findViewById(R.id.line);
        //Tender
        tender = findViewById(R.id.tender);
        //State
        state = findViewById(R.id.state);
        //District
        district = findViewById(R.id.district);
        //Taluka
        taluka = findViewById(R.id.taluka);


        //Center
        center = findViewById(R.id.centerACTV);
        //Village
        village = findViewById(R.id.villageACTV);
        //Material
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


        material31 = findViewById(R.id.materialWork31);
        material32 = findViewById(R.id.materialWork32);
        material33 = findViewById(R.id.materialWork33);
        material34 = findViewById(R.id.materialWork34);
        material35 = findViewById(R.id.materialWork35);
        material36 = findViewById(R.id.materialWork36);
        material37 = findViewById(R.id.materialWork37);
        material38 = findViewById(R.id.materialWork38);
        material39 = findViewById(R.id.materialWork39);
        material40 = findViewById(R.id.materialWork40);


        //Quantity
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

        quantity31 = findViewById(R.id.quantity31);
        quantity32 = findViewById(R.id.quantity32);
        quantity33 = findViewById(R.id.quantity33);
        quantity34 = findViewById(R.id.quantity34);
        quantity35 = findViewById(R.id.quantity35);
        quantity36 = findViewById(R.id.quantity36);
        quantity37 = findViewById(R.id.quantity37);
        quantity38 = findViewById(R.id.quantity38);
        quantity39 = findViewById(R.id.quantity39);
        quantity40 = findViewById(R.id.quantity40);


        //Button
        homeNextBtn = findViewById(R.id.nextHome);
        homeBackBtn = findViewById(R.id.backHome);
        middleNextBtn = findViewById(R.id.nextLast);
        middleBackBtn = findViewById(R.id.backMiddle);
        nextLastTwo = findViewById(R.id.nextLastTwo);
        doneBtn = findViewById(R.id.done);
        lastBackBtn = findViewById(R.id.backLastOne);
        doneAll = findViewById(R.id.doneAll);

        //TextInputEditText
        consumerName = findViewById(R.id.consumer);
        site = findViewById(R.id.siteName);

        note = findViewById(R.id.note);




        Bundle bundle = getIntent().getExtras();

        if (bundle!=null){
            id = bundle.getString("Id");
            dateS = bundle.getString("Date");
            lineS = bundle.getString("Line");
            tenderS = bundle.getString("Tender");
            consumerNameS = bundle.getString("Consumer Name");
            siteNameS = bundle.getString("Site");
            teamNameS = bundle.getString("Team Name");

            stateS = bundle.getString("State");
            districtS = bundle.getString("District");
            talukaS = bundle.getString("Taluka");
            noteS = bundle.getString("Note");

            //material
            material1S = bundle.getString("Material1");
            quantity1S = bundle.getString("Quantity1");

            material2S = bundle.getString("Material2");
            quantity2S = bundle.getString("Quantity2");

            material3S = bundle.getString("Material3");
            quantity3S = bundle.getString("Quantity3");

            material4S = bundle.getString("Material4");
            quantity4S = bundle.getString("Quantity4");

            material5S = bundle.getString("Material5");
            quantity5S = bundle.getString("Quantity5");

            material6S = bundle.getString("Material6");
            quantity6S = bundle.getString("Quantity6");

            material7S = bundle.getString("Material7");
            quantity7S = bundle.getString("Quantity7");

            material8S = bundle.getString("Material8");
            quantity8S = bundle.getString("Quantity8");

            material9S = bundle.getString("Material9");
            quantity9S = bundle.getString("Quantity9");

            material10S = bundle.getString("Material10");
            quantity10S = bundle.getString("Quantity10");

            material11S = bundle.getString("Material11");
            quantity11S = bundle.getString("Quantity11");

            material12S = bundle.getString("Material12");
            quantity12S = bundle.getString("Quantity12");

            material13S = bundle.getString("Material13");
            quantity13S = bundle.getString("Quantity13");

            material14S = bundle.getString("Material14");
            quantity14S = bundle.getString("Quantity14");

            material15S = bundle.getString("Material15");
            quantity15S = bundle.getString("Quantity15");

            material16S = bundle.getString("Material16");
            quantity16S = bundle.getString("Quantity16");

            material17S = bundle.getString("Material17");
            quantity17S = bundle.getString("Quantity17");

            material18S = bundle.getString("Material18");
            quantity18S = bundle.getString("Quantity18");

            material19S = bundle.getString("Material19");
            quantity19S = bundle.getString("Quantity19");

            material20S = bundle.getString("Material20");
            quantity20S = bundle.getString("Quantity20");

            material21S = bundle.getString("Material21");
            quantity21S = bundle.getString("Quantity21");

            material22S = bundle.getString("Material22");
            quantity22S = bundle.getString("Quantity22");

            material23S = bundle.getString("Material23");
            quantity23S = bundle.getString("Quantity23");

            material24S = bundle.getString("Material24");
            quantity24S = bundle.getString("Quantity24");

            material25S = bundle.getString("Material25");
            quantity25S = bundle.getString("Quantity25");

            material26S = bundle.getString("Material26");
            quantity26S = bundle.getString("Quantity26");

            material27S = bundle.getString("Material27");
            quantity27S = bundle.getString("Quantity27");

            material28S = bundle.getString("Material28");
            quantity28S = bundle.getString("Quantity28");

            material29S = bundle.getString("Material29");
            quantity29S = bundle.getString("Quantity29");

            material30S = bundle.getString("Material30");
            quantity30S = bundle.getString("Quantity30");





            material31S = bundle.getString("Material31");
            quantity31S = bundle.getString("Quantity31");

            material32S = bundle.getString("Material32");
            quantity32S = bundle.getString("Quantity32");

            material33S = bundle.getString("Material33");
            quantity33S = bundle.getString("Quantity33");

            material34S = bundle.getString("Material34");
            quantity34S = bundle.getString("Quantity34");

            material35S = bundle.getString("Material35");
            quantity35S = bundle.getString("Quantity35");

            material36S = bundle.getString("Material36");
            quantity36S = bundle.getString("Quantity36");

            material37S = bundle.getString("Material37");
            quantity37S = bundle.getString("Quantity37");

            material38S = bundle.getString("Material38");
            quantity38S = bundle.getString("Quantity38");

            material39S = bundle.getString("Material39");
            quantity39S = bundle.getString("Quantity39");

            material40S = bundle.getString("Material40");
            quantity40S = bundle.getString("Quantity40");








            centerSet = bundle.getString("Center");
            villageSet = bundle.getString("Village");


            mDateFormate.setText(dateS);

            line.setText(lineS);
            tender.setText(tenderS);
            consumerName.setText(consumerNameS);
            site.setText(siteNameS);

            state.setText(stateS);
            district.setText(districtS);
            taluka.setText(talukaS);
            teamName.setText(teamNameS);

            note.setText(noteS);

            center.setText(centerSet);
            village.setText(villageSet);

            material1.setText(material1S);
            material2.setText(material2S);
            material3.setText(material3S);
            material4.setText(material4S);
            material5.setText(material5S);
            material6.setText(material6S);
            material7.setText(material7S);
            material8.setText(material8S);
            material9.setText(material9S);
            material10.setText(material10S);
            material11.setText(material11S);
            material12.setText(material12S);
            material13.setText(material13S);
            material14.setText(material14S);
            material15.setText(material15S);
            material16.setText(material16S);
            material17.setText(material17S);
            material18.setText(material18S);
            material19.setText(material19S);
            material20.setText(material20S);
            material21.setText(material21S);
            material22.setText(material22S);
            material23.setText(material23S);
            material24.setText(material24S);
            material25.setText(material25S);
            material26.setText(material26S);
            material27.setText(material27S);
            material28.setText(material28S);
            material29.setText(material29S);
            material30.setText(material30S);
            quantity1.setText(quantity1S);
            quantity2.setText(quantity2S);
            quantity3.setText(quantity3S);
            quantity4.setText(quantity4S);
            quantity5.setText(quantity5S);
            quantity6.setText(quantity6S);
            quantity7.setText(quantity7S);
            quantity8.setText(quantity8S);
            quantity9.setText(quantity9S);
            quantity10.setText(quantity10S);
            quantity11.setText(quantity11S);
            quantity12.setText(quantity12S);
            quantity13.setText(quantity13S);
            quantity14.setText(quantity14S);
            quantity15.setText(quantity15S);
            quantity16.setText(quantity16S);
            quantity17.setText(quantity17S);
            quantity18.setText(quantity18S);
            quantity19.setText(quantity19S);
            quantity20.setText(quantity20S);
            quantity21.setText(quantity21S);
            quantity22.setText(quantity22S);
            quantity23.setText(quantity23S);
            quantity24.setText(quantity24S);
            quantity25.setText(quantity25S);
            quantity26.setText(quantity26S);
            quantity27.setText(quantity27S);
            quantity28.setText(quantity28S);
            quantity29.setText(quantity29S);
            quantity30.setText(quantity30S);

        }



        DocumentReference documentReference = fStore.collection("Users")
                .document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                companyEmail = value.getString("companyEmail");
                cmp = companyEmail;
                companyEmail = companyEmail.replace("@", "");
                companyEmail = companyEmail.replace(".", "");
                ActionBar bar = getSupportActionBar();
                assert bar != null;
                bar.setTitle("ADD WORK DONE");


                String profile = value.getString("profile");


                doneAll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (profile) {
                            case "ADMIN":
                                startActivity(new Intent(getApplicationContext(), Admin.class));
                                break;
                            case "SUPERVISOR":
                                startActivity(new Intent(getApplicationContext(), Supervisor.class));
                                break;
                            case "TEAM LEADER":
                                startActivity(new Intent(getApplicationContext(), TeamLearder.class));
                                break;
                        }

                        finish();
                    }
                });


                //State
                databaseReferenceState = FirebaseDatabase.getInstance().getReference(companyEmail+" State");
                spinnerDataListState = new ArrayList<>();
                adapterState = new ArrayAdapter<String>(AddWorkDone.this,R.layout.support_simple_spinner_dropdown_item,spinnerDataListState);
                state.setAdapter(adapterState);
                retrieveDataState();
                //District
                databaseReferenceDistrict = FirebaseDatabase.getInstance().getReference(companyEmail+" District");
                spinnerDataListDistrict = new ArrayList<>();
                adapterDistrict = new ArrayAdapter<String>(AddWorkDone.this,R.layout.support_simple_spinner_dropdown_item,spinnerDataListDistrict);
                district.setAdapter(adapterDistrict);
                retrieveDataDistrict();


                //teamName
                databaseReference = FirebaseDatabase.getInstance().getReference(companyEmail+" teamName");
                spinnerDataList = new ArrayList<>();
                adapter = new ArrayAdapter<String>(AddWorkDone.this,R.layout.support_simple_spinner_dropdown_item,spinnerDataList);
                teamName.setAdapter(adapter);
                retrieveData();



                //Taluka
                databaseReferenceTaluka = FirebaseDatabase.getInstance().getReference(companyEmail+" Taluka");
                spinnerDataListTaluka = new ArrayList<>();
                adapterTaluka = new ArrayAdapter<String>(AddWorkDone.this,R.layout.support_simple_spinner_dropdown_item,spinnerDataListTaluka);
                taluka.setAdapter(adapterTaluka);
                retrieveDataTaluka();


                //Line
                databaseReferenceLine = FirebaseDatabase.getInstance().getReference(companyEmail+" Line");
                spinnerDataListLine = new ArrayList<>();
                adapterLine = new ArrayAdapter<String>(AddWorkDone.this,R.layout.support_simple_spinner_dropdown_item,spinnerDataListLine);
                line.setAdapter(adapterLine);
                retrieveDataLine();

                //Tender
                databaseReferenceTender = FirebaseDatabase.getInstance().getReference(companyEmail+" Tender");
                spinnerDataListTender = new ArrayList<>();
                adapterTender = new ArrayAdapter<String>(AddWorkDone.this,R.layout.support_simple_spinner_dropdown_item,spinnerDataListTender);
                tender.setAdapter(adapterTender);
                retrieveDataTender();



                //Center
                databaseReferenceCenter = FirebaseDatabase.getInstance().getReference(companyEmail+" Center");
                spinnerDataListCenter = new ArrayList<>();
                adapterCenter = new ArrayAdapter<String>(AddWorkDone.this,R.layout.support_simple_spinner_dropdown_item,spinnerDataListCenter);
                center.setAdapter(adapterCenter);
                retrieveDataCenter();

                //Village
                databaseReferenceVillage = FirebaseDatabase.getInstance().getReference(companyEmail+" Village");
                spinnerDataListVillage = new ArrayList<>();
                adapterVillage = new ArrayAdapter<String>(AddWorkDone.this,R.layout.support_simple_spinner_dropdown_item,spinnerDataListVillage);
                village.setAdapter(adapterVillage);
                retrieveDataVillage();

                //Material
                databaseReferenceMaterial = FirebaseDatabase.getInstance().getReference(companyEmail+" WorkDone");
                spinnerDataListMaterial = new ArrayList<>();
                adapterMaterial = new ArrayAdapter<String>(AddWorkDone.this,R.layout.support_simple_spinner_dropdown_item,spinnerDataListMaterial);
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

                material31.setAdapter(adapterMaterial);
                material32.setAdapter(adapterMaterial);
                material33.setAdapter(adapterMaterial);
                material34.setAdapter(adapterMaterial);
                material35.setAdapter(adapterMaterial);
                material36.setAdapter(adapterMaterial);
                material36.setAdapter(adapterMaterial);
                material37.setAdapter(adapterMaterial);
                material38.setAdapter(adapterMaterial);
                material39.setAdapter(adapterMaterial);
                material40.setAdapter(adapterMaterial);
                retrieveDataMaterial();



                //update



                //Material Add Btn
                addIV1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Materials
                        String uMaterial1 = material1.getText().toString().trim();
                        String uQuantity1 = quantity1.getText().toString().trim();

                        String uMaterial2 = material2.getText().toString().trim();
                        String uQuantity2 = quantity2.getText().toString().trim();

                        String uMaterial3 = material3.getText().toString().trim();
                        String uQuantity3 = quantity3.getText().toString().trim();

                        String uMaterial4 = material4.getText().toString().trim();
                        String uQuantity4 = quantity4.getText().toString().trim();

                        String uMaterial5 = material5.getText().toString().trim();
                        String uQuantity5 = quantity5.getText().toString().trim();


                        if (uMaterial1.isEmpty()||uQuantity1.isEmpty()||uMaterial2.isEmpty()||uQuantity2.isEmpty()||
                                uMaterial3.isEmpty()||uQuantity3.isEmpty()||uMaterial4.isEmpty()||uQuantity4.isEmpty()||
                                uMaterial5.isEmpty()||uQuantity5.isEmpty()){
                            showMessage("Fill above information first");
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

                        String uMaterial7 = material7.getText().toString().trim();
                        String uQuantity7 = quantity7.getText().toString().trim();

                        String uMaterial8 = material8.getText().toString().trim();
                        String uQuantity8 = quantity8.getText().toString().trim();

                        String uMaterial9 = material9.getText().toString().trim();
                        String uQuantity9 = quantity9.getText().toString().trim();

                        String uMaterial10 = material10.getText().toString().trim();
                        String uQuantity10 = quantity10.getText().toString().trim();


                        if (uMaterial6.isEmpty()||uQuantity6.isEmpty()||uMaterial7.isEmpty()||uQuantity7.isEmpty()||
                                uMaterial8.isEmpty()||uQuantity8.isEmpty()||uMaterial9.isEmpty()||uQuantity9.isEmpty()||
                                uMaterial10.isEmpty()||uQuantity10.isEmpty()){
                            showMessage("Fill above information first");
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

                        String uMaterial12 = material12.getText().toString().trim();
                        String uQuantity12 = quantity12.getText().toString().trim();

                        String uMaterial13 = material13.getText().toString().trim();
                        String uQuantity13 = quantity13.getText().toString().trim();

                        String uMaterial14 = material4.getText().toString().trim();
                        String uQuantity14 = quantity14.getText().toString().trim();

                        String uMaterial15 = material15.getText().toString().trim();
                        String uQuantity15 = quantity15.getText().toString().trim();


                        if (uMaterial11.isEmpty()||uQuantity11.isEmpty()||uMaterial12.isEmpty()||uQuantity12.isEmpty()||
                                uMaterial13.isEmpty()||uQuantity13.isEmpty()||uMaterial14.isEmpty()||uQuantity14.isEmpty()||
                                uMaterial15.isEmpty()||uQuantity15.isEmpty()){
                            showMessage("Fill above information first");
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

                        String uMaterial17 = material17.getText().toString().trim();
                        String uQuantity17 = quantity17.getText().toString().trim();

                        String uMaterial18 = material18.getText().toString().trim();
                        String uQuantity18 = quantity18.getText().toString().trim();

                        String uMaterial19 = material19.getText().toString().trim();
                        String uQuantity19 = quantity19.getText().toString().trim();

                        String uMaterial20 = material20.getText().toString().trim();
                        String uQuantity20 = quantity20.getText().toString().trim();

                        if (uMaterial16.isEmpty()||uQuantity16.isEmpty()||uMaterial17.isEmpty()||uQuantity17.isEmpty()||
                                uMaterial18.isEmpty()||uQuantity18.isEmpty()||uMaterial19.isEmpty()||uQuantity19.isEmpty()||
                                uMaterial20.isEmpty()||uQuantity20.isEmpty()){
                            showMessage("Fill above information first");
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

                        String uMaterial22 = material22.getText().toString().trim();
                        String uQuantity22 = quantity22.getText().toString().trim();

                        String uMaterial23 = material23.getText().toString().trim();
                        String uQuantity23 = quantity23.getText().toString().trim();

                        String uMaterial24 = material4.getText().toString().trim();
                        String uQuantity24 = quantity24.getText().toString().trim();

                        String uMaterial25 = material25.getText().toString().trim();
                        String uQuantity25 = quantity25.getText().toString().trim();



                        if (uMaterial21.isEmpty()||uQuantity21.isEmpty()||uMaterial22.isEmpty()||uQuantity22.isEmpty()||
                                uMaterial23.isEmpty()||uQuantity23.isEmpty()||uMaterial24.isEmpty()||uQuantity24.isEmpty()||
                                uMaterial25.isEmpty()||uQuantity25.isEmpty()){
                            showMessage("Fill above information first");
                        }
                        else {
                            addIV5.setVisibility(View.GONE);
                            addMaterialLL6.setVisibility(View.VISIBLE);
                        }
                    }
                });

                addIV6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Materials
                        String uMaterial26 = material26.getText().toString().trim();
                        String uQuantity26 = quantity26.getText().toString().trim();

                        String uMaterial27 = material27.getText().toString().trim();
                        String uQuantity27 = quantity27.getText().toString().trim();

                        String uMaterial28 = material28.getText().toString().trim();
                        String uQuantity28 = quantity28.getText().toString().trim();

                        String uMaterial29 = material29.getText().toString().trim();
                        String uQuantity29 = quantity29.getText().toString().trim();

                        String uMaterial20 = material20.getText().toString().trim();
                        String uQuantity20 = quantity20.getText().toString().trim();

                        if (uMaterial26.isEmpty()||uQuantity26.isEmpty()||uMaterial27.isEmpty()||uQuantity27.isEmpty()||
                                uMaterial28.isEmpty()||uQuantity28.isEmpty()||uMaterial29.isEmpty()||uQuantity29.isEmpty()||
                                uMaterial20.isEmpty()||uQuantity20.isEmpty()){
                            showMessage("Fill above information first");
                        }
                        else {
                            addIV6.setVisibility(View.GONE);
                            addMaterialLL7.setVisibility(View.VISIBLE);
                        }
                    }
                });

                addIV7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Materials
                        String uMaterial31 = material31.getText().toString().trim();
                        String uQuantity31 = quantity31.getText().toString().trim();

                        String uMaterial32 = material32.getText().toString().trim();
                        String uQuantity32 = quantity32.getText().toString().trim();

                        String uMaterial33 = material33.getText().toString().trim();
                        String uQuantity33 = quantity33.getText().toString().trim();

                        String uMaterial34 = material34.getText().toString().trim();
                        String uQuantity34 = quantity34.getText().toString().trim();

                        String uMaterial35 = material35.getText().toString().trim();
                        String uQuantity35 = quantity35.getText().toString().trim();



                        if (uMaterial31.isEmpty()||uQuantity31.isEmpty()||uMaterial32.isEmpty()||uQuantity32.isEmpty()||
                                uMaterial33.isEmpty()||uQuantity33.isEmpty()||uMaterial34.isEmpty()||uQuantity34.isEmpty()||
                                uMaterial35.isEmpty()||uQuantity35.isEmpty()){
                            showMessage("Fill above information first");
                        }
                        else {
                            addIV7.setVisibility(View.GONE);
                            addMaterialLL8.setVisibility(View.VISIBLE);
                        }
                    }
                });


                //HomeBtn
                homeNextBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String consumer = consumerName.getText().toString().trim();
                        String team = teamName.getText().toString().trim();
                        String lineS = line.getText().toString().trim();
                        String tenderS = tender.getText().toString().trim();
                        String dateS = mDateFormate.getText().toString().trim();


                        if (dateS.isEmpty()){
                            mDateFormate.setError(r);
                            mDateFormate.requestFocus();
                        }
                        else if (consumer.isEmpty()){
                            consumerName.setError(r);
                            consumerName.requestFocus();
                        }
                        else if (team.isEmpty()){
                            teamName.setError(r);
                            teamName.requestFocus();
                        }
                        else if (lineS.isEmpty()){
                            line.setError(r);
                            line.requestFocus();
                        }
                        else if (tenderS.isEmpty()){
                            tender.setError(r);
                            tender.requestFocus();
                        }
                        else {
                            homeLL.setVisibility(View.GONE);
                            middleLL.setVisibility(View.VISIBLE);
                            pair(cmp,tenderS);
                        }

                    }
                });

                //Home Back
                homeBackBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        homeLL.setVisibility(View.VISIBLE);
                        middleLL.setVisibility(View.GONE);
                    }
                });

                //Middle Next
                middleNextBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String siteName = site.getText().toString().trim();
                        String stateS = state.getText().toString().trim();
                        String dis = district.getText().toString().trim();
                        String talukaS = taluka.getText().toString().trim();
                        String centerS = center.getText().toString().trim();
                        String villageS = village.getText().toString().trim();
                        if (stateS.isEmpty()){
                            state.setError(r);
                            state.requestFocus();
                        }
                        else if (dis.isEmpty()){
                            district.setError(r);
                            district.requestFocus();
                        }
                        else if (talukaS.isEmpty()){
                            taluka.setError(r);
                            taluka.requestFocus();
                        }
                        else if (centerS.isEmpty()){
                            center.setError(r);
                            center.requestFocus();
                        }
                        else if (villageS.isEmpty()){
                            village.setError(r);
                            village.requestFocus();
                        }

                        else if (siteName.isEmpty()){
                            site.setError(r);
                            site.requestFocus();
                        }
                        else {
                            middleLL.setVisibility(View.GONE);
                            lastOneLL.setVisibility(View.VISIBLE);
                        }
                    }
                });

                //Middle Back
                middleBackBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        middleLL.setVisibility(View.VISIBLE);
                        lastOneLL.setVisibility(View.GONE);
                    }
                });

                //NextLastTwo
                nextLastTwo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        lastOneLL.setVisibility(View.GONE);
                        lastLL.setVisibility(View.VISIBLE);
                    }
                });

                lastBackBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        lastOneLL.setVisibility(View.VISIBLE);
                        lastLL.setVisibility(View.GONE);
                    }
                });

                doneBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Home
                        String uDate = mDateFormate.getText().toString().trim();
                        String uLine = line.getText().toString().trim();
                        String uTender = tender.getText().toString().trim();
                        String uTeamName = teamName.getText().toString().trim();

                        //Site
                        String uConsumerName = consumerName.getText().toString().trim();
                        String uTaluka = (taluka.getText().toString().trim());
                        String uDistrict = (district.getText().toString().trim());
                        String uState = (state.getText().toString().trim());
                        String uSite = (site.getText().toString().trim());

                        //Material
                        String uMaterial1 = material1.getText().toString().trim();
                        String uQuantity1 = quantity1.getText().toString().trim();

                        String uMaterial2 = material2.getText().toString().trim();
                        String uQuantity2 = quantity2.getText().toString().trim();

                        String uMaterial3 = material3.getText().toString().trim();
                        String uQuantity3 = quantity3.getText().toString().trim();

                        String uMaterial4 = material4.getText().toString().trim();
                        String uQuantity4 = quantity4.getText().toString().trim();

                        String uMaterial5 = material5.getText().toString().trim();
                        String uQuantity5 = quantity5.getText().toString().trim();

                        String uMaterial6 = material6.getText().toString().trim();
                        String uQuantity6 = quantity6.getText().toString().trim();

                        String uMaterial7 = material7.getText().toString().trim();
                        String uQuantity7 = quantity7.getText().toString().trim();

                        String uMaterial8 = material8.getText().toString().trim();
                        String uQuantity8 = quantity8.getText().toString().trim();

                        String uMaterial9 = material9.getText().toString().trim();
                        String uQuantity9 = quantity9.getText().toString().trim();

                        String uMaterial10 = material10.getText().toString().trim();
                        String uQuantity10 = quantity10.getText().toString().trim();

                        String uMaterial11 = material11.getText().toString().trim();
                        String uQuantity11 = quantity11.getText().toString().trim();

                        String uMaterial12 = material12.getText().toString().trim();
                        String uQuantity12 = quantity12.getText().toString().trim();

                        String uMaterial13 = material13.getText().toString().trim();
                        String uQuantity13 = quantity13.getText().toString().trim();

                        String uMaterial14 = material14.getText().toString().trim();
                        String uQuantity14 = quantity14.getText().toString().trim();

                        String uMaterial15 = material15.getText().toString().trim();
                        String uQuantity15 = quantity15.getText().toString().trim();

                        String uMaterial16 = material16.getText().toString().trim();
                        String uQuantity16 = quantity16.getText().toString().trim();

                        String uMaterial17 = material17.getText().toString().trim();
                        String uQuantity17 = quantity17.getText().toString().trim();

                        String uMaterial18 = material18.getText().toString().trim();
                        String uQuantity18 = quantity18.getText().toString().trim();

                        String uMaterial19 = material19.getText().toString().trim();
                        String uQuantity19 = quantity19.getText().toString().trim();

                        String uMaterial20 = material20.getText().toString().trim();
                        String uQuantity20 = quantity20.getText().toString().trim();

                        String uMaterial21 = material21.getText().toString().trim();
                        String uQuantity21 = quantity21.getText().toString().trim();

                        String uMaterial22 = material22.getText().toString().trim();
                        String uQuantity22 = quantity22.getText().toString().trim();

                        String uMaterial23 = material23.getText().toString().trim();
                        String uQuantity23 = quantity23.getText().toString().trim();

                        String uMaterial24 = material24.getText().toString().trim();
                        String uQuantity24 = quantity24.getText().toString().trim();

                        String uMaterial25 = material25.getText().toString().trim();
                        String uQuantity25 = quantity25.getText().toString().trim();

                        String uMaterial26 = material26.getText().toString().trim();
                        String uQuantity26 = quantity26.getText().toString().trim();

                        String uMaterial27 = material27.getText().toString().trim();
                        String uQuantity27 = quantity27.getText().toString().trim();

                        String uMaterial28 = material28.getText().toString().trim();
                        String uQuantity28 = quantity28.getText().toString().trim();

                        String uMaterial29 = material29.getText().toString().trim();
                        String uQuantity29 = quantity29.getText().toString().trim();

                        String uMaterial30 = material30.getText().toString().trim();
                        String uQuantity30 = quantity30.getText().toString().trim();




                        String uMaterial31 = material31.getText().toString().trim();
                        String uQuantity31 = quantity31.getText().toString().trim();

                        String uMaterial32 = material32.getText().toString().trim();
                        String uQuantity32 = quantity32.getText().toString().trim();

                        String uMaterial33 = material33.getText().toString().trim();
                        String uQuantity33 = quantity33.getText().toString().trim();

                        String uMaterial34 = material34.getText().toString().trim();
                        String uQuantity34 = quantity34.getText().toString().trim();

                        String uMaterial35 = material35.getText().toString().trim();
                        String uQuantity35 = quantity35.getText().toString().trim();

                        String uMaterial36 = material36.getText().toString().trim();
                        String uQuantity36 = quantity36.getText().toString().trim();

                        String uMaterial37 = material37.getText().toString().trim();
                        String uQuantity37 = quantity37.getText().toString().trim();

                        String uMaterial38 = material38.getText().toString().trim();
                        String uQuantity38 = quantity38.getText().toString().trim();

                        String uMaterial39 = material39.getText().toString().trim();
                        String uQuantity39 = quantity39.getText().toString().trim();

                        String uMaterial40 = material40.getText().toString().trim();
                        String uQuantity40 = quantity40.getText().toString().trim();

                        String centerS = center.getText().toString().trim();
                        String villageS = village.getText().toString().trim();
                        String noteS = note.getText().toString().trim();

                        addWorkDone(cmp,uDate,uLine,uTender,uConsumerName,uSite,uDistrict,uTaluka,uState
                                ,uMaterial1,uQuantity1,uMaterial2,uQuantity2,uMaterial3,uQuantity3,uMaterial4,uQuantity4,uMaterial5,uQuantity5,
                                uMaterial6,uQuantity6,uMaterial7,uQuantity7,uMaterial8,uQuantity8,uMaterial9,uQuantity9,uMaterial10,uQuantity10
                               , uMaterial11 ,uQuantity11,uMaterial12,uQuantity12,uMaterial13,uQuantity13,uMaterial14,uQuantity14,uMaterial15,uQuantity15,
                                uMaterial16,uQuantity16,uMaterial17,uQuantity17,uMaterial18,uQuantity18,uMaterial19,uQuantity19,uMaterial20,uQuantity20
                                ,uMaterial21,uQuantity21,uMaterial22,uQuantity22,uMaterial23,uQuantity23,uMaterial24,uQuantity24,uMaterial25,uQuantity25,
                                uMaterial26,uQuantity26,uMaterial27,uQuantity27,uMaterial28,uQuantity28,uMaterial29,uQuantity29,uMaterial30,uQuantity30
                                ,uMaterial31,uQuantity31,uMaterial32,uQuantity32,uMaterial33,uQuantity33,uMaterial34,uQuantity34,uMaterial35,uQuantity35,
                                uMaterial36,uQuantity36,uMaterial37,uQuantity37,uMaterial38,uQuantity38,uMaterial39,uQuantity39,uMaterial40,uQuantity40,
                                centerS,villageS,noteS,uTeamName);



                    }
                });

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        doneAll.setEnabled(true);
                    }
                },1500);



            }
        });
        
        
    }

    private void addWorkDone(String cmp, String uDate, String uLine, String uTender, String uConsumerName, String uSite, String uDistrict, String uTaluka, String uState, String uMaterial1, String uQuantity1, String uMaterial2, String uQuantity2, String uMaterial3, String uQuantity3, String uMaterial4, String uQuantity4, String uMaterial5, String uQuantity5, String uMaterial6, String uQuantity6, String uMaterial7, String uQuantity7, String uMaterial8, String uQuantity8, String uMaterial9, String uQuantity9, String uMaterial10, String uQuantity10, String uMaterial11, String uQuantity11, String uMaterial12, String uQuantity12, String uMaterial13, String uQuantity13, String uMaterial14, String uQuantity14, String uMaterial15, String uQuantity15, String uMaterial16, String uQuantity16, String uMaterial17, String uQuantity17, String uMaterial18, String uQuantity18, String uMaterial19, String uQuantity19, String uMaterial20, String uQuantity20, String uMaterial21, String uQuantity21, String uMaterial22, String uQuantity22, String uMaterial23, String uQuantity23, String uMaterial24, String uQuantity24, String uMaterial25, String uQuantity25, String uMaterial26, String uQuantity26, String uMaterial27, String uQuantity27, String uMaterial28, String uQuantity28, String uMaterial29, String uQuantity29, String uMaterial30, String uQuantity30, String uMaterial31, String uQuantity31, String uMaterial32, String uQuantity32, String uMaterial33, String uQuantity33, String uMaterial34, String uQuantity34, String uMaterial35, String uQuantity35, String uMaterial36, String uQuantity36, String uMaterial37, String uQuantity37, String uMaterial38, String uQuantity38, String uMaterial39, String uQuantity39, String uMaterial40, String uQuantity40, String centerS, String villageS, String noteS,String uTeamName) {

        addDataLL.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        Map<String, Object> doc = new HashMap<>();

        doc.put("id", id);
        doc.put("Date",uDate);
        doc.put("SearchDate",uDate.toLowerCase());
        doc.put("Consumer Name",uConsumerName);
        doc.put("Line",uLine);
        doc.put("Tender",uTender);
        doc.put("SearchTeamName",uTeamName);
        doc.put("Site Name",uSite);
        doc.put("District Name",uDistrict);
        doc.put("Taluka Name",uTaluka);
        doc.put("State Name",uState);
        //Material
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

        doc.put("Material 31",uMaterial31);
        doc.put("Material 32",uMaterial32);
        doc.put("Material 33",uMaterial33);
        doc.put("Material 34",uMaterial34);
        doc.put("Material 35",uMaterial35);
        doc.put("Material 36",uMaterial36);
        doc.put("Material 37",uMaterial37);
        doc.put("Material 38",uMaterial38);
        doc.put("Material 39",uMaterial39);
        doc.put("Material 40",uMaterial40);

        //Quantity
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

        doc.put("Quantity 31",uQuantity31);
        doc.put("Quantity 32",uQuantity32);
        doc.put("Quantity 33",uQuantity33);
        doc.put("Quantity 34",uQuantity34);
        doc.put("Quantity 35",uQuantity35);
        doc.put("Quantity 36",uQuantity36);
        doc.put("Quantity 37",uQuantity37);
        doc.put("Quantity 38",uQuantity38);
        doc.put("Quantity 39",uQuantity39);
        doc.put("Quantity 40",uQuantity40);


        doc.put("Center",centerS);
        doc.put("SearchCenter",centerS.toLowerCase());
        doc.put("Village",villageS);
        doc.put("Note",noteS);
        doc.put("SearchVillage",villageS.toLowerCase());

        fStore.collection(cmp+" AddWorkDone").document(id).set(doc).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                //this will be called when data is added Successfully
                Toast.makeText(getApplicationContext(), "Data Added!!", Toast.LENGTH_SHORT).show();
                addDataLL.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
                animation.setVisibility(View.VISIBLE);
                lastLL.setVisibility(View.GONE);
                mediaPlayer.start();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //this will be called when data is added Failed
                Toast.makeText(getApplicationContext(), "Failed to add data "+e.getMessage(), Toast.LENGTH_SHORT).show();
                addDataLL.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }
        });


    }

    public void retrieveDataLine(){
        listenerLine = databaseReferenceLine.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot item : snapshot.getChildren()){
                    spinnerDataListLine.add(item.getValue().toString());
                }
                adapterLine.notifyDataSetChanged();
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
    public void retrieveDataVillage(){
        listenerVillage = databaseReferenceVillage.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot item : snapshot.getChildren()){
                    spinnerDataListVillage.add(item.getValue().toString());
                }
                adapterVillage.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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
    public void retrieveData(){
        listener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot item : snapshot.getChildren()){
                    spinnerDataList.add(item.getValue().toString());
                }
                adapter.notifyDataSetChanged();
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
    private void showMessage(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }

    private void pair(String cmp,String tender){
        DocumentReference documentReference = fStore.collection(cmp+" Pair")
                .document(tender);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                assert value != null;
                state.setText(value.getString("State"));
                district.setText(value.getString("District"));
                taluka.setText(value.getString("Taluka"));
                center.setText(value.getString("Center"));
            }
        });
    }
    
    
    
}