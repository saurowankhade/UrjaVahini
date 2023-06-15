package com.example.electricalmaterial;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

public class AddActualMaterial extends AppCompatActivity {

    String r = "Required!";

    //authorization
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String companyEmail;
    String userId;

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

    //Driver
    AutoCompleteTextView driver;
    ArrayAdapter<String> adapterDriver;
    ArrayList<String> spinnerDataListDriver;
    DatabaseReference databaseReferenceDriver;
    ValueEventListener listenerDriver;

    //Vehical
    AutoCompleteTextView vehical;
    ArrayAdapter<String> adapterVehical;
    ArrayList<String> spinnerDataListVehical;
    DatabaseReference databaseReferenceVehical;
    ValueEventListener listenerVehical;

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
    ArrayAdapter<String> adapterMaterial;
    ArrayList<String> spinnerDataListMaterial;
    DatabaseReference databaseReferenceMaterial;
    ValueEventListener listenerMaterial;

    //Receiver
    AutoCompleteTextView receiver;
    ArrayAdapter<String> adapterReceiver;
    ArrayList<String> spinnerDataListReceiver;
    DatabaseReference databaseReferenceReceiver;
    ValueEventListener listenerReceiver;

    //Button Home
    MaterialButton homeNextBtn;
    MaterialButton homeBackBtn;
    MaterialButton middleNextBtn;
    MaterialButton nextLastTwo;
    MaterialButton doneBtn;
    MaterialButton lastBackBtn;
    MaterialButton doneAll;

    //TextInputEditText
    TextInputEditText consumer,site;
    TextInputEditText passReceiver;

    String cmp;


    //Unit
    AutoCompleteTextView unit1,unit2,unit3,unit4,unit5,unit6,unit7,unit8,unit9,unit10;
    AutoCompleteTextView unit11,unit12,unit13,unit14,unit15,unit16,unit17,unit18,unit19,unit20;
    AutoCompleteTextView unit21,unit22,unit23,unit24,unit25,unit26,unit27,unit28,unit29,unit30;
    ArrayAdapter<String> adapterUnit;
    ArrayList<String> spinnerDataListUnit;
    DatabaseReference databaseReferenceUnit;
    ValueEventListener listenerUnit;

    TextInputEditText quantity1,quantity2,quantity3,quantity4,quantity5,quantity6,quantity7,quantity8,quantity9,quantity10;
    TextInputEditText quantity11,quantity12,quantity13,quantity14,quantity15,quantity16,quantity17,quantity18,quantity19,quantity20;
    TextInputEditText quantity21,quantity22,quantity23,quantity24,quantity25,quantity26,quantity27,quantity28,quantity29,quantity30;


    LinearLayout addDataLL;
    ProgressBar progressBar;

    LinearLayout addMaterialLL1,addMaterialLL2,addMaterialLL3,addMaterialLL4,addMaterialLL5,addMaterialLL6;
    ImageView addIV1,addIV2,addIV3,addIV4,addIV5;


    
    
    ProgressDialog pd;

    MediaPlayer mediaPlayer;


    String uMaterial1,uUnit1,uQuantity1;
    String uMaterial2,uUnit2,uQuantity2;
    String uMaterial3,uUnit3,uQuantity3;
    String uMaterial4,uUnit4,uQuantity4;
    String uMaterial5,uUnit5,uQuantity5;
    String uMaterial6,uUnit6,uQuantity6;
    String uMaterial7,uUnit7,uQuantity7;
    String uMaterial8,uUnit8,uQuantity8;
    String uMaterial9,uUnit9,uQuantity9;
    String uMaterial10,uUnit10,uQuantity10;
    String uMaterial11,uUnit11,uQuantity11;
    String uMaterial12,uUnit12,uQuantity12;
    String uMaterial13,uUnit13,uQuantity13;
    String uMaterial14,uUnit14,uQuantity14;
    String uMaterial15,uUnit15,uQuantity15;
    String uMaterial16,uUnit16,uQuantity16;
    String uMaterial17,uUnit17,uQuantity17;
    String uMaterial18,uUnit18,uQuantity18;
    String uMaterial19,uUnit19,uQuantity19;
    String uMaterial20,uUnit20,uQuantity20;
    String uMaterial21,uUnit21,uQuantity21;
    String uMaterial22,uUnit22,uQuantity22;
    String uMaterial23,uUnit23,uQuantity23;
    String uMaterial24,uUnit24,uQuantity24;
    String uMaterial25,uUnit25,uQuantity25;
    String uMaterial26,uUnit26,uQuantity26;
    String uMaterial27,uUnit27,uQuantity27;
    String uMaterial28,uUnit28,uQuantity28;
    String uMaterial29,uUnit29,uQuantity29;
    String uMaterial30,uUnit30,uQuantity30;


    String idS;
    String dateS;
    String teamNameS;
    String lineS;
    String tenderS;
    String driverNameS;
    String vehicalNameS;
    String consumerNameS;
    String siteNameS;
    String villageS;
    String centerS;



    // creating new id for database
    String id = UUID.randomUUID().toString();


    //View all data
    List<UsedAddAcutualMaterialModel> modelList = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    UsedAddActucalMaterialCustomAdapter usedAddActucalMaterialCustomAdapter;




    //Add Actual Material
    List<AddTotalMaterialTakenModel> modelListTMT = new ArrayList<>();
    RecyclerView recyclerViewTMT;
    RecyclerView.LayoutManager layoutManagerTMT;
    AddActualTotalMaterialTakenAdapter adapterStockTMT;



    // Balance Material
    List<BalanceMaterialModel> modelListBalance = new ArrayList<>();
    RecyclerView recyclerViewBalance;
    RecyclerView.LayoutManager layoutManagerBalance;
    BalanceMaterialCustomAdapterInAddActucalMater adapterStockBalance;

    int a1 = 0;
    int a2 = 0;
    int a3 = 0;
    int a4 = 0;
    int a5 = 0;
    int a6 = 0;
    int a7 = 0;
    int a8 = 0;
    int a9 = 0;
    int a10 = 0;
    int a11 = 0;
    int a12 = 0;
    int a13 = 0;
    int a14 = 0;
    int a15 = 0;
    int a16 = 0;
    int a17 = 0;
    int a18 = 0;
    int a19 = 0;
    int a20 = 0;
    int a21 = 0;
    int a22 = 0;
    int a23 = 0;
    int a24 = 0;
    int a25 = 0;
    int a26 = 0;
    int a27 = 0;
    int a28 = 0;
    int a29 = 0;
    int a30 = 0;


    @SuppressLint({"MissingInflatedId", "SourceLockedOrientationActivity"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_actual_material);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //Auth
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = mAuth.getCurrentUser().getUid();

        pd = ProgressDialog.show(this,"Loading...","Please Wait",false,false);
        
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

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

        addIV1 = findViewById(R.id.addIV1);
        addIV2 = findViewById(R.id.addIV2);
        addIV3 = findViewById(R.id.addIV3);
        addIV4 = findViewById(R.id.addIV4);
        addIV5 = findViewById(R.id.addIV5);

      
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
                        AddActualMaterial.this, R.style.DatePicker1,
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



        //  View Used data
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        modelList.clear();

        

        addDataLL = findViewById(R.id.addData);
        progressBar = findViewById(R.id.progressBar);

        //Team Name
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
        //Driver
        driver = findViewById(R.id.driver);
        //Vehical
        vehical = findViewById(R.id.vehical);
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


        //Unit
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

        //Receiver
        receiver = findViewById(R.id.receiver);

        //Button
        homeNextBtn = findViewById(R.id.nextHome);
        homeBackBtn = findViewById(R.id.backHome);
        middleNextBtn = findViewById(R.id.nextLast);

        nextLastTwo = findViewById(R.id.nextLastTwo);
        doneBtn = findViewById(R.id.done);
        lastBackBtn = findViewById(R.id.backLastOne);
        doneAll = findViewById(R.id.doneAll);


        //TextInputEditText
        consumer = findViewById(R.id.consumer);
        site = findViewById(R.id.siteName);
        passReceiver = findViewById(R.id.pass);


        recyclerViewTMT = findViewById(R.id.recyclerATMT);
        recyclerViewTMT.setHasFixedSize(true);
        layoutManagerTMT = new LinearLayoutManager(AddActualMaterial.this);
        recyclerViewTMT.setLayoutManager(layoutManagerTMT);
        modelListTMT.clear();


        // Balance Material
        recyclerViewBalance = findViewById(R.id.recyclerBalance);
        recyclerViewBalance.setHasFixedSize(true);
        layoutManagerBalance = new LinearLayoutManager(AddActualMaterial.this);
        recyclerViewBalance.setLayoutManager(layoutManagerBalance);
        modelListBalance.clear();



        DocumentReference documentReference = fStore.collection("Users")
                .document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                companyEmail = value.getString("companyEmail");
                cmp = companyEmail;
                companyEmail = companyEmail.replace("@","");
                companyEmail = companyEmail.replace(".","");



                Bundle bundle = getIntent().getExtras();

                if (bundle!=null){
                    id = bundle.getString("Id");
                    dateS = bundle.getString("Date");
                    teamNameS = bundle.getString("TeamName");
                    lineS = bundle.getString("Line");
                    tenderS = bundle.getString("Tender");
                    driverNameS = bundle.getString("Driver Name");
                    vehicalNameS = bundle.getString("Vehical Name");
                    consumerNameS = bundle.getString("Consumer Name");
                    siteNameS = bundle.getString("Site");
                    centerS = bundle.getString("Center");
                    villageS = bundle.getString("Village");


                    // String Text
                    mDateFormate.setText(dateS);
                    teamName.setText(teamNameS);
                    line.setText(lineS);
                    tender.setText(tenderS);
                    driver.setText(driverNameS);
                    vehical.setText(vehicalNameS);
                    consumer.setText(consumerNameS);
                    site.setText(siteNameS);
                    center.setText(centerS);
                    village.setText(villageS);
                }


                showData(cmp);
                showBalanceData(cmp);


                ActionBar bar = getSupportActionBar();
                assert bar != null;
                bar.hide();


                //State
                databaseReferenceState = FirebaseDatabase.getInstance().getReference(companyEmail+" State");
                spinnerDataListState = new ArrayList<>();
                adapterState = new ArrayAdapter<String>(AddActualMaterial.this,R.layout.support_simple_spinner_dropdown_item,spinnerDataListState);
                state.setAdapter(adapterState);
                retrieveDataState();
                //District
                databaseReferenceDistrict = FirebaseDatabase.getInstance().getReference(companyEmail+" District");
                spinnerDataListDistrict = new ArrayList<>();
                adapterDistrict = new ArrayAdapter<String>(AddActualMaterial.this,R.layout.support_simple_spinner_dropdown_item,spinnerDataListDistrict);
                district.setAdapter(adapterDistrict);
                retrieveDataDistrict();


                //Taluka
                databaseReferenceTaluka = FirebaseDatabase.getInstance().getReference(companyEmail+" Taluka");
                spinnerDataListTaluka = new ArrayList<>();
                adapterTaluka = new ArrayAdapter<String>(AddActualMaterial.this,R.layout.support_simple_spinner_dropdown_item,spinnerDataListTaluka);
                taluka.setAdapter(adapterTaluka);
                retrieveDataTaluka();

                //Team Name
                databaseReference = FirebaseDatabase.getInstance().getReference(companyEmail+" teamName");
                spinnerDataList = new ArrayList<>();
                adapter = new ArrayAdapter<String>(AddActualMaterial.this,R.layout.support_simple_spinner_dropdown_item,spinnerDataList);
                teamName.setAdapter(adapter);
                retrieveData();

                //Line
                databaseReferenceLine = FirebaseDatabase.getInstance().getReference(companyEmail+" Line");
                spinnerDataListLine = new ArrayList<>();
                adapterLine = new ArrayAdapter<String>(AddActualMaterial.this,R.layout.support_simple_spinner_dropdown_item,spinnerDataListLine);
                line.setAdapter(adapterLine);
                retrieveDataLine();

                //Tender
                databaseReferenceTender = FirebaseDatabase.getInstance().getReference(companyEmail+" Tender");
                spinnerDataListTender = new ArrayList<>();
                adapterTender = new ArrayAdapter<String>(AddActualMaterial.this,R.layout.support_simple_spinner_dropdown_item,spinnerDataListTender);
                tender.setAdapter(adapterTender);
                retrieveDataTender();

                //Driver
                databaseReferenceDriver = FirebaseDatabase.getInstance().getReference(companyEmail+" Driver");
                spinnerDataListDriver = new ArrayList<>();
                adapterDriver = new ArrayAdapter<String>(AddActualMaterial.this,R.layout.support_simple_spinner_dropdown_item,spinnerDataListDriver);
                driver.setAdapter(adapterDriver);
                retrieveDataDriver();

                //Vehical
                databaseReferenceVehical = FirebaseDatabase.getInstance().getReference(companyEmail+" Vehical");
                spinnerDataListVehical = new ArrayList<>();
                adapterVehical = new ArrayAdapter<String>(AddActualMaterial.this,R.layout.support_simple_spinner_dropdown_item,spinnerDataListVehical);
                vehical.setAdapter(adapterVehical);
                retrieveDataVehical();

                //Center
                databaseReferenceCenter = FirebaseDatabase.getInstance().getReference(companyEmail+" Center");
                spinnerDataListCenter = new ArrayList<>();
                adapterCenter = new ArrayAdapter<String>(AddActualMaterial.this,R.layout.support_simple_spinner_dropdown_item,spinnerDataListCenter);
                center.setAdapter(adapterCenter);
                retrieveDataCenter();

                //Village
                databaseReferenceVillage = FirebaseDatabase.getInstance().getReference(companyEmail+" Village");
                spinnerDataListVillage = new ArrayList<>();
                adapterVillage = new ArrayAdapter<String>(AddActualMaterial.this,R.layout.support_simple_spinner_dropdown_item,spinnerDataListVillage);
                village.setAdapter(adapterVillage);
                retrieveDataVillage();


                //Material
                databaseReferenceMaterial = FirebaseDatabase.getInstance().getReference(companyEmail + " Material");
                spinnerDataListMaterial = new ArrayList<>();
                adapterMaterial = new ArrayAdapter<String>(AddActualMaterial.this, R.layout.support_simple_spinner_dropdown_item, spinnerDataListMaterial);
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
                databaseReferenceUnit = FirebaseDatabase.getInstance().getReference(companyEmail + " Unit");
                spinnerDataListUnit = new ArrayList<>();
                adapterUnit = new ArrayAdapter<String>(AddActualMaterial.this, R.layout.support_simple_spinner_dropdown_item, spinnerDataListUnit);
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












                //Receiver
                databaseReferenceReceiver = FirebaseDatabase.getInstance().getReference(companyEmail+" Receiver");
                spinnerDataListReceiver = new ArrayList<>();
                adapterReceiver = new ArrayAdapter<String>(AddActualMaterial.this,R.layout.support_simple_spinner_dropdown_item,spinnerDataListReceiver);
                receiver.setAdapter(adapterReceiver);
                retrieveDataReceiver();

                pd.dismiss();
                


                //State
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
                            showMessage("Fill above information first");
                        }
                        else {
                            addIV5.setVisibility(View.GONE);
                            addMaterialLL6.setVisibility(View.VISIBLE);
                        }
                    }
                });

                //HomeBtn
                homeNextBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String team = teamName.getText().toString().trim();
                        String lineS = line.getText().toString().trim();
                        String tenderS = tender.getText().toString().trim();
                        String driverS = driver.getText().toString().trim();
                        String vehicalS = vehical.getText().toString().trim();
                        String dateS = mDateFormate.getText().toString().trim();

                        if (dateS.isEmpty()){
                            mDateFormate.setError(r);
                            mDateFormate.requestFocus();
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
                        else if (driverS.isEmpty()){
                            driver.setError(r);
                            driver.requestFocus();
                        }
                        else if (vehicalS.isEmpty()){
                            vehical.setError(r);
                            vehical.requestFocus();
                        }
                        else {
                            homeLL.setVisibility(View.GONE);
                            middleLL.setVisibility(View.VISIBLE);
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
                        String consumerName = consumer.getText().toString().trim();
                        String siteName = site.getText().toString().trim();
                        String centerS = center.getText().toString().trim();
                        String villageS = village.getText().toString().trim();


                        showTotalMaterialTaken(cmp,consumerName);



                        if (consumerName.isEmpty()){
                            consumer.setError(r);
                            consumer.requestFocus();
                        }
                        else if (siteName.isEmpty()){
                            site.setError(r);
                            site.requestFocus();
                        }
                        else if (centerS.isEmpty()){
                            center.setError(r);
                            center.requestFocus();
                        }
                        else if (villageS.isEmpty()){
                            village.setError(r);
                            village.requestFocus();
                        }
                        else {
                            middleLL.setVisibility(View.GONE);
                            lastOneLL.setVisibility(View.VISIBLE);
                        }
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
                        String pass = passReceiver.getText().toString().trim();

                        //Home
                        String uDate = mDateFormate.getText().toString().trim();
                        String uTeamName = teamName.getText().toString().trim();
                        String uLine = line.getText().toString().trim();
                        String uTender = tender.getText().toString().trim();
                        String uDriverName = driver.getText().toString().trim();
                        String uVehicalName = vehical.getText().toString().trim();

                        //Site
                        String uConsumerName = consumer.getText().toString().trim();




                        String uSite = (site.getText().toString().trim());

                        //Material
                        uMaterial1 = material1.getText().toString().trim();
                        uQuantity1 = quantity1.getText().toString().trim();
                        uUnit1 = unit1.getText().toString().trim();

                        uMaterial2 = material2.getText().toString().trim();
                        uQuantity2 = quantity2.getText().toString().trim();
                        uUnit2 = unit2.getText().toString().trim();

                        uMaterial3 = material3.getText().toString().trim();
                        uQuantity3 = quantity3.getText().toString().trim();
                        uUnit3 = unit3.getText().toString().trim();

                        uMaterial4 = material4.getText().toString().trim();
                        uQuantity4 = quantity4.getText().toString().trim();
                        uUnit4 = unit4.getText().toString().trim();

                        uMaterial5 = material5.getText().toString().trim();
                        uQuantity5 = quantity5.getText().toString().trim();
                        uUnit5 = unit5.getText().toString().trim();

                        uMaterial6 = material6.getText().toString().trim();
                        uQuantity6 = quantity6.getText().toString().trim();
                        uUnit6 = unit6.getText().toString().trim();

                        uMaterial7 = material7.getText().toString().trim();
                        uQuantity7 = quantity7.getText().toString().trim();
                        uUnit7 = unit7.getText().toString().trim();

                        uMaterial8 = material8.getText().toString().trim();
                        uQuantity8 = quantity8.getText().toString().trim();
                        uUnit8 = unit8.getText().toString().trim();

                        uMaterial9 = material9.getText().toString().trim();
                        uQuantity9 = quantity9.getText().toString().trim();
                        uUnit9 = unit9.getText().toString().trim();

                        uMaterial10 = material10.getText().toString().trim();
                        uQuantity10 = quantity10.getText().toString().trim();
                        uUnit10 = unit10.getText().toString().trim();

                        uMaterial11 = material11.getText().toString().trim();
                        uQuantity11 = quantity11.getText().toString().trim();
                        uUnit11 = unit11.getText().toString().trim();

                        uMaterial12 = material12.getText().toString().trim();
                        uQuantity12 = quantity12.getText().toString().trim();
                        uUnit12 = unit12.getText().toString().trim();

                        uMaterial13 = material13.getText().toString().trim();
                        uQuantity13 = quantity13.getText().toString().trim();
                        uUnit13 = unit13.getText().toString().trim();

                        uMaterial14 = material14.getText().toString().trim();
                        uQuantity14 = quantity14.getText().toString().trim();
                        uUnit14 = unit14.getText().toString().trim();

                        uMaterial15 = material15.getText().toString().trim();
                        uQuantity15 = quantity15.getText().toString().trim();
                        uUnit15 = unit15.getText().toString().trim();

                        uMaterial16 = material16.getText().toString().trim();
                        uQuantity16 = quantity16.getText().toString().trim();
                        uUnit16 = unit16.getText().toString().trim();

                        uMaterial17 = material17.getText().toString().trim();
                        uQuantity17 = quantity17.getText().toString().trim();
                        uUnit17 = unit17.getText().toString().trim();

                        uMaterial18 = material18.getText().toString().trim();
                        uQuantity18 = quantity18.getText().toString().trim();
                        uUnit18 = unit18.getText().toString().trim();

                        uMaterial19 = material19.getText().toString().trim();
                        uQuantity19 = quantity19.getText().toString().trim();
                        uUnit19 = unit19.getText().toString().trim();

                        uMaterial20 = material20.getText().toString().trim();
                        uQuantity20 = quantity20.getText().toString().trim();
                        uUnit20 = unit20.getText().toString().trim();


                        uMaterial21 = material21.getText().toString().trim();
                        uQuantity21 = quantity21.getText().toString().trim();
                        uUnit21 = unit21.getText().toString().trim();

                        uMaterial22 = material22.getText().toString().trim();
                        uQuantity22 = quantity22.getText().toString().trim();
                        uUnit22 = unit22.getText().toString().trim();

                        uMaterial23 = material23.getText().toString().trim();
                        uQuantity23 = quantity23.getText().toString().trim();
                        uUnit23 = unit23.getText().toString().trim();

                        uMaterial24 = material24.getText().toString().trim();
                        uQuantity24 = quantity24.getText().toString().trim();
                        uUnit24 = unit24.getText().toString().trim();

                        uMaterial25 = material25.getText().toString().trim();
                        uQuantity25 = quantity25.getText().toString().trim();
                        uUnit25 = unit25.getText().toString().trim();

                        uMaterial26 = material26.getText().toString().trim();
                        uQuantity26 = quantity26.getText().toString().trim();
                        uUnit26 = unit26.getText().toString().trim();

                        uMaterial27 = material27.getText().toString().trim();
                        uQuantity27 = quantity27.getText().toString().trim();
                        uUnit27 = unit27.getText().toString().trim();

                        uMaterial28 = material28.getText().toString().trim();
                        uQuantity28 = quantity28.getText().toString().trim();
                        uUnit28 = unit28.getText().toString().trim();

                        uMaterial29 = material29.getText().toString().trim();
                        uQuantity29 = quantity29.getText().toString().trim();
                        uUnit29 = unit19.getText().toString().trim();

                        uMaterial30 = material30.getText().toString().trim();
                        uQuantity30 = quantity30.getText().toString().trim();
                        uUnit30 = unit30.getText().toString().trim();

                        String centerS = center.getText().toString().trim();
                        String villageS = village.getText().toString().trim();


                        //Receiver
                        String materialReceiver = receiver.getText().toString().trim();

                        if (materialReceiver.isEmpty()){
                            receiver.setError(r);
                            receiver.requestFocus();
                        }
                        else if (pass.isEmpty()){
                            passReceiver.setError(r);
                            passReceiver.requestFocus();
                        }
                        else {
                            verifyPassReceiver(materialReceiver,pass,cmp,uDate,uTeamName,uLine,uTender,uDriverName,uVehicalName,uConsumerName,uSite,uMaterial1,uQuantity1,uMaterial2,uQuantity2,uMaterial3,uQuantity3,uMaterial4,uQuantity4,uMaterial5,uQuantity5,
                                    uMaterial6,uQuantity6,uMaterial7,uQuantity7,uMaterial8,uQuantity8,uMaterial9,uQuantity9,uMaterial10,uQuantity10
                                    ,uQuantity11,uMaterial12,uQuantity12,uMaterial13,uQuantity13,uMaterial14,uQuantity14,uMaterial15,uQuantity15,
                                    uMaterial16,uQuantity16,uMaterial17,uQuantity17,uMaterial18,uQuantity18,uMaterial19,uQuantity19,uMaterial20,uQuantity20
                                    ,uUnit1,uUnit2,uUnit3,uUnit4,uUnit5,uUnit6,uUnit7,uUnit8,uUnit9,uUnit10
                                    ,uUnit11,uUnit12,uUnit13,uUnit14,uUnit15,uUnit16,uUnit17,uUnit18,uUnit19,uUnit20,uMaterial11
                                    ,uMaterial21,uQuantity21,uMaterial22,uQuantity22,uMaterial23,uQuantity23,uMaterial24,uQuantity24,uMaterial25,uQuantity25,
                                    uMaterial26,uQuantity26,uMaterial27,uQuantity27,uMaterial28,uQuantity28,uMaterial29,uQuantity29,uMaterial30,uQuantity30
                                    ,uUnit21,uUnit22,uUnit23,uUnit24,uUnit25,uUnit26,uUnit27,uUnit28,uUnit29,uUnit30,
                                    centerS,villageS
                            );

                        }
                    }
                });

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        doneAll.setEnabled(true);
                    }
                },1500);
                doneAll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(),Admin.class));
                        finish();
                    }
                });


            }
        });

    }

    private void showBalanceData(String cmp) {
        fStore.collection(cmp+" BalanceMaterialOnSite").orderBy("Date", Query.Direction.DESCENDING).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        modelListBalance.clear();
                        for (DocumentSnapshot doc : queryDocumentSnapshots){
                            BalanceMaterialModel model = new BalanceMaterialModel(
                                    doc.getString("id"),
                                    doc.getString("Date"),
                                    doc.getString("Team Name"),
                                    doc.getString("Line"),
                                    doc.getString("Tender"),
                                    doc.getString("Driver Name"),
                                    doc.getString("Vehical Name"),
                                    doc.getString("Consumer Name"),
                                    doc.getString("Site Name"),
                                    doc.getString("Material Receiver Name"),
                                    doc.getString("Center"),
                                    doc.getString("Village")
                            );
                            modelListBalance.add(model);
                        }
                        adapterStockBalance = new BalanceMaterialCustomAdapterInAddActucalMater(AddActualMaterial.this,modelListBalance);
                        recyclerView.setAdapter(adapterStockBalance);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(getApplicationContext(), "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void showTotalMaterialTaken(String cmp, String consumerName) {
        fStore.collection(cmp+" "+consumerName).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                modelListTMT.clear();
                for (DocumentSnapshot doc : task.getResult()){
                    AddTotalMaterialTakenModel model = new AddTotalMaterialTakenModel(
                            doc.getString("Id"),
                            doc.getString("Material"),
                            doc.getString("Unit"),
                            doc.getString("Quantity")
                    );
                    modelListTMT.add(model);
                }
                adapterStockTMT = new AddActualTotalMaterialTakenAdapter(AddActualMaterial.this,modelListTMT);
                recyclerViewTMT.setAdapter(adapterStockTMT);
            }}).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AddActualMaterial.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void showData(String cmp) {

        fStore.collection(cmp+" AddData").orderBy("Date", Query.Direction.DESCENDING).get()

                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        modelList.clear();
                        pd.dismiss();
                        for (DocumentSnapshot doc :queryDocumentSnapshots){
                            UsedAddAcutualMaterialModel model = new UsedAddAcutualMaterialModel(
                                    doc.getString("id"),
                                    doc.getString("Date"),
                                    doc.getString("Team Name"),
                                    doc.getString("Line"),
                                    doc.getString("Tender"),
                                    doc.getString("Driver Name"),
                                    doc.getString("Vehical Name"),
                                    doc.getString("Consumer Name"),
                                    doc.getString("Site Name"),
                                    doc.getString("Material 1"),
                                    doc.getString("Unit 1"),
                                    doc.getString("Quantity 1"),
                                    doc.getString("Material 2"),
                                    doc.getString("Unit 2"),
                                    doc.getString("Quantity 2"),
                                    doc.getString("Material 3"),
                                    doc.getString("Unit 3"),
                                    doc.getString("Quantity 3"),
                                    doc.getString("Material 4"),
                                    doc.getString("Unit 4"),
                                    doc.getString("Quantity 4"),
                                    doc.getString("Material 5"),
                                    doc.getString("Unit 5"),
                                    doc.getString("Quantity 5"),
                                    doc.getString("Material 6"),
                                    doc.getString("Unit 6"),
                                    doc.getString("Quantity 6"),
                                    doc.getString("Material 7"),
                                    doc.getString("Unit 7"),
                                    doc.getString("Quantity 7"),
                                    doc.getString("Material 8"),
                                    doc.getString("Unit 8"),
                                    doc.getString("Quantity 8"),
                                    doc.getString("Material 9"),
                                    doc.getString("Unit 9"),
                                    doc.getString("Quantity 9"),
                                    doc.getString("Material 10"),
                                    doc.getString("Unit 10"),
                                    doc.getString("Quantity 10"),
                                    doc.getString("Material 11"),
                                    doc.getString("Unit 11"),
                                    doc.getString("Quantity 11"),
                                    doc.getString("Material 12"),
                                    doc.getString("Unit 12"),
                                    doc.getString("Quantity 12"),
                                    doc.getString("Material 13"),
                                    doc.getString("Unit 13"),
                                    doc.getString("Quantity 13"),
                                    doc.getString("Material 14"),
                                    doc.getString("Unit 14"),
                                    doc.getString("Quantity 14"),
                                    doc.getString("Material 15"),
                                    doc.getString("Unit 15"),
                                    doc.getString("Quantity 15"),
                                    doc.getString("Material 16"),
                                    doc.getString("Unit 16"),
                                    doc.getString("Quantity 16"),
                                    doc.getString("Material 17"),
                                    doc.getString("Unit 17"),
                                    doc.getString("Quantity 17"),
                                    doc.getString("Material 18"),
                                    doc.getString("Unit 18"),
                                    doc.getString("Quantity 18"),
                                    doc.getString("Material 19"),
                                    doc.getString("Unit 19"),
                                    doc.getString("Quantity 19"),
                                    doc.getString("Material 20"),
                                    doc.getString("Unit 20"),
                                    doc.getString("Quantity 20"),
                                    doc.getString("Material 21"),
                                    doc.getString("Unit 21"),
                                    doc.getString("Quantity 21"),
                                    doc.getString("Material 22"),
                                    doc.getString("Unit 22"),
                                    doc.getString("Quantity 22"),
                                    doc.getString("Material 23"),
                                    doc.getString("Unit 23"),
                                    doc.getString("Quantity 23"),
                                    doc.getString("Material 24"),
                                    doc.getString("Unit 24"),
                                    doc.getString("Quantity 24"),
                                    doc.getString("Material 25"),
                                    doc.getString("Unit 25"),
                                    doc.getString("Quantity 25"),
                                    doc.getString("Material 26"),
                                    doc.getString("Unit 26"),
                                    doc.getString("Quantity 26"),
                                    doc.getString("Material 27"),
                                    doc.getString("Unit 27"),
                                    doc.getString("Quantity 27"),
                                    doc.getString("Material 28"),
                                    doc.getString("Unit 28"),
                                    doc.getString("Quantity 28"),
                                    doc.getString("Material 29"),
                                    doc.getString("Unit 29"),
                                    doc.getString("Quantity 29"),
                                    doc.getString("Material 30"),
                                    doc.getString("Unit 30"),
                                    doc.getString("Quantity 30"),
                                    doc.getString("Material Receiver Name"),
                                    doc.getString("Center"),
                                    doc.getString("Village")
                            );
                            modelList.add(model);
                        }
                        usedAddActucalMaterialCustomAdapter = new UsedAddActucalMaterialCustomAdapter(AddActualMaterial.this,modelList);
                        recyclerView.setAdapter(usedAddActucalMaterialCustomAdapter);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(getApplicationContext(), "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void verifyPassReceiver(String materialReceiver, String pass, String cmp, String uDate, String uTeamName, String uLine, String uTender, String uDriverName, String uVehicalName, String uConsumerName, String uSite, String uMaterial1, String uQuantity1, String uMaterial2, String uQuantity2, String uMaterial3, String uQuantity3, String uMaterial4, String uQuantity4, String uMaterial5, String uQuantity5, String uMaterial6, String uQuantity6, String uMaterial7, String uQuantity7, String uMaterial8, String uQuantity8, String uMaterial9, String uQuantity9, String uMaterial10, String uQuantity10, String uQuantity11, String uMaterial12, String uQuantity12, String uMaterial13, String uQuantity13, String uMaterial14, String uQuantity14, String uMaterial15, String uQuantity15, String uMaterial16, String uQuantity16, String uMaterial17, String uQuantity17, String uMaterial18, String uQuantity18, String uMaterial19, String uQuantity19, String uMaterial20, String uQuantity20, String uUnit1, String uUnit2, String uUnit3, String uUnit4, String uUnit5, String uUnit6, String uUnit7, String uUnit8, String uUnit9, String uUnit10, String uUnit11, String uUnit12, String uUnit13, String uUnit14, String uUnit15, String uUnit16, String uUnit17, String uUnit18, String uUnit19, String uUnit20, String uMaterial11, String uMaterial21, String uQuantity21, String uMaterial22, String uQuantity22, String uMaterial23, String uQuantity23, String uMaterial24, String uQuantity24, String uMaterial25, String uQuantity25, String uMaterial26, String uQuantity26, String uMaterial27, String uQuantity27, String uMaterial28, String uQuantity28, String uMaterial29, String uQuantity29, String uMaterial30, String uQuantity30, String uUnit21, String uUnit22, String uUnit23, String uUnit24, String uUnit25, String uUnit26, String uUnit27, String uUnit28, String uUnit29, String uUnit30, String centerS, String villageS) {
        DocumentReference a = fStore.collection(cmp+" ProfilePass")
                .document(materialReceiver.toLowerCase());
        a.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                String passReceiverS = value.getString("Password");

                if (pass.equals(passReceiverS)){
                    Toast.makeText(getApplicationContext(), "Password Match!!", Toast.LENGTH_SHORT).show();



                    uploadData(cmp, uDate, uTeamName, uLine, uTender, uDriverName, uVehicalName, uConsumerName, uSite, uMaterial1, uQuantity1, uMaterial2, uQuantity2, uMaterial3, uQuantity3, uMaterial4, uQuantity4, uMaterial5, uQuantity5,
                            uMaterial6, uQuantity6, uMaterial7, uQuantity7, uMaterial8, uQuantity8, uMaterial9, uQuantity9, uMaterial10, uQuantity10
                            , uQuantity11, uMaterial12, uQuantity12, uMaterial13, uQuantity13, uMaterial14, uQuantity14, uMaterial15, uQuantity15,
                            uMaterial16, uQuantity16, uMaterial17, uQuantity17, uMaterial18, uQuantity18, uMaterial19, uQuantity19, uMaterial20, uQuantity20
                            , uUnit1, uUnit2, uUnit3, uUnit4, uUnit5, uUnit6, uUnit7, uUnit8, uUnit9, uUnit10
                            , uUnit11, uUnit12, uUnit13, uUnit14, uUnit15, uUnit16, uUnit17, uUnit18, uUnit19, uUnit20, uMaterial11
                            , uMaterial21, uQuantity21, uMaterial22, uQuantity22, uMaterial23, uQuantity23, uMaterial24, uQuantity24, uMaterial25, uQuantity25,
                            uMaterial26, uQuantity26, uMaterial27, uQuantity27, uMaterial28, uQuantity28, uMaterial29, uQuantity29, uMaterial30, uQuantity30
                            , uUnit21, uUnit22, uUnit23, uUnit24, uUnit25, uUnit26, uUnit27, uUnit28, uUnit29, uUnit30, materialReceiver, centerS, villageS);

                }
                else {
                    passReceiver.setError("Password does not Match!!");
                    passReceiver.requestFocus();
                    Toast.makeText(getApplicationContext(), "Password does not Match!!", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    private void uploadData(String cmp, String uDate, String uTeamName, String uLine, String uTender, String uDriverName, String uVehicalName, String uConsumerName, String uSite, String uMaterial1, String uQuantity1, String uMaterial2, String uQuantity2, String uMaterial3, String uQuantity3, String uMaterial4, String uQuantity4, String uMaterial5, String uQuantity5, String uMaterial6, String uQuantity6, String uMaterial7, String uQuantity7, String uMaterial8, String uQuantity8, String uMaterial9, String uQuantity9, String uMaterial10, String uQuantity10, String uQuantity11, String uMaterial12, String uQuantity12, String uMaterial13, String uQuantity13, String uMaterial14, String uQuantity14, String uMaterial15, String uQuantity15, String uMaterial16, String uQuantity16, String uMaterial17, String uQuantity17, String uMaterial18, String uQuantity18, String uMaterial19, String uQuantity19, String uMaterial20, String uQuantity20, String uUnit1, String uUnit2, String uUnit3, String uUnit4, String uUnit5, String uUnit6, String uUnit7, String uUnit8, String uUnit9, String uUnit10, String uUnit11, String uUnit12, String uUnit13, String uUnit14, String uUnit15, String uUnit16, String uUnit17, String uUnit18, String uUnit19, String uUnit20, String uMaterial11, String uMaterial21, String uQuantity21, String uMaterial22, String uQuantity22, String uMaterial23, String uQuantity23, String uMaterial24, String uQuantity24, String uMaterial25, String uQuantity25, String uMaterial26, String uQuantity26, String uMaterial27, String uQuantity27, String uMaterial28, String uQuantity28, String uMaterial29, String uQuantity29, String uMaterial30, String uQuantity30, String uUnit21, String uUnit22, String uUnit23, String uUnit24, String uUnit25, String uUnit26, String uUnit27, String uUnit28, String uUnit29, String uUnit30, String materialReceiver,String centerS,String villageS) {
        addDataLL.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);


        Map<String, Object> doc = new HashMap<>();

        doc.put("id", id);
        doc.put("Date",uDate);
        doc.put("SearchDate",uDate.toLowerCase());
        doc.put("Team Name",uTeamName);
        doc.put("SearchTeamName",uTeamName.toLowerCase());
        doc.put("Line",uLine);
        doc.put("Tender",uTender);
        doc.put("SearchTender",uTender.toLowerCase());
        doc.put("Driver Name",uDriverName);
        doc.put("Vehical Name",uVehicalName);
        doc.put("Consumer Name",uConsumerName);
        doc.put("Site Name",uSite);
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
        //Unit
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

        doc.put("Material Receiver Name",materialReceiver);
        doc.put("Center",centerS);
        doc.put("Village",villageS);
        doc.put("SearchCenter",centerS.toLowerCase());
        doc.put("SearchVillage",villageS.toLowerCase());



        fStore.collection(cmp+" AddActualData").document(id).set(doc).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                //this will be called when data is added Successfully

                Toast.makeText(getApplicationContext(), "Data Added !!", Toast.LENGTH_SHORT).show();
                addDataLL.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
                animation.setVisibility(View.VISIBLE);
                lastLL.setVisibility(View.GONE);
                mediaPlayer.start();

                blanceMaterial(uConsumerName,cmp, uDate, uTeamName, uLine, uTender, uDriverName, uVehicalName, uConsumerName, uSite, uMaterial1, uQuantity1, uMaterial2, uQuantity2, uMaterial3, uQuantity3, uMaterial4, uQuantity4, uMaterial5, uQuantity5,
                        uMaterial6, uQuantity6, uMaterial7, uQuantity7, uMaterial8, uQuantity8, uMaterial9, uQuantity9, uMaterial10, uQuantity10
                        , uQuantity11, uMaterial12, uQuantity12, uMaterial13, uQuantity13, uMaterial14, uQuantity14, uMaterial15, uQuantity15,
                        uMaterial16, uQuantity16, uMaterial17, uQuantity17, uMaterial18, uQuantity18, uMaterial19, uQuantity19, uMaterial20, uQuantity20
                        , uUnit1, uUnit2, uUnit3, uUnit4, uUnit5, uUnit6, uUnit7, uUnit8, uUnit9, uUnit10
                        , uUnit11, uUnit12, uUnit13, uUnit14, uUnit15, uUnit16, uUnit17, uUnit18, uUnit19, uUnit20, uMaterial11
                        , uMaterial21, uQuantity21, uMaterial22, uQuantity22, uMaterial23, uQuantity23, uMaterial24, uQuantity24, uMaterial25, uQuantity25,
                        uMaterial26, uQuantity26, uMaterial27, uQuantity27, uMaterial28, uQuantity28, uMaterial29, uQuantity29, uMaterial30, uQuantity30
                        , uUnit21, uUnit22, uUnit23, uUnit24, uUnit25, uUnit26, uUnit27, uUnit28, uUnit29, uUnit30, materialReceiver, centerS, villageS);



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

    private void showMessage(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }

    public void retrieveDataReceiver(){
        listenerReceiver = databaseReferenceReceiver.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot item : snapshot.getChildren()){
                    spinnerDataListReceiver.add(item.getValue().toString());
                }
                adapterReceiver.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void blanceMaterial(String consumerName,String cmp, String uDate, String uTeamName, String uLine, String uTender, String uDriverName, String uVehicalName, String uConsumerName, String uSite, String uMaterial1, String uQuantity1, String uMaterial2, String uQuantity2, String uMaterial3, String uQuantity3, String uMaterial4, String uQuantity4, String uMaterial5, String uQuantity5, String uMaterial6, String uQuantity6, String uMaterial7, String uQuantity7, String uMaterial8, String uQuantity8, String uMaterial9, String uQuantity9, String uMaterial10, String uQuantity10, String uQuantity11, String uMaterial12, String uQuantity12, String uMaterial13, String uQuantity13, String uMaterial14, String uQuantity14, String uMaterial15, String uQuantity15, String uMaterial16, String uQuantity16, String uMaterial17, String uQuantity17, String uMaterial18, String uQuantity18, String uMaterial19, String uQuantity19, String uMaterial20, String uQuantity20, String uUnit1, String uUnit2, String uUnit3, String uUnit4, String uUnit5, String uUnit6, String uUnit7, String uUnit8, String uUnit9, String uUnit10, String uUnit11, String uUnit12, String uUnit13, String uUnit14, String uUnit15, String uUnit16, String uUnit17, String uUnit18, String uUnit19, String uUnit20, String uMaterial11, String uMaterial21, String uQuantity21, String uMaterial22, String uQuantity22, String uMaterial23, String uQuantity23, String uMaterial24, String uQuantity24, String uMaterial25, String uQuantity25, String uMaterial26, String uQuantity26, String uMaterial27, String uQuantity27, String uMaterial28, String uQuantity28, String uMaterial29, String uQuantity29, String uMaterial30, String uQuantity30, String uUnit21, String uUnit22, String uUnit23, String uUnit24, String uUnit25, String uUnit26, String uUnit27, String uUnit28, String uUnit29, String uUnit30, String materialReceiver, String centerS, String villageS) {
        addDataLL.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        for (int i=0;i<modelListBalance.size();i++){
            if (modelListBalance.get(i).getConsumerName().equalsIgnoreCase(uConsumerName)){
                id = modelListBalance.get(i).getId();
            }
        }


        if (modelListTMT.size()!=0){
            for (int i=0;i<modelListTMT.size();i++){
                if (modelListTMT.get(i).getMaterial().equalsIgnoreCase(uMaterial1)){
                    float q = Float.parseFloat(modelListTMT.get(i).getQuantity()) - Float.parseFloat(uQuantity1);
                    addTotalMaterialTakenSpecific(modelListTMT.get(i).getMaterial(),cmp,consumerName,String.valueOf(q),uUnit1);
                    a1=1;

                }
                else if (modelListTMT.get(i).getMaterial().equalsIgnoreCase(uMaterial2)){
                    float q = Float.parseFloat(modelListTMT.get(i).getQuantity()) - Float.parseFloat(uQuantity2);
                    addTotalMaterialTakenSpecific(modelListTMT.get(i).getMaterial(),cmp,consumerName,String.valueOf(q),uUnit2);
                    a2=1;

                }
                else if (modelListTMT.get(i).getMaterial().equalsIgnoreCase(uMaterial3)){
                    float q = Float.parseFloat(modelListTMT.get(i).getQuantity()) - Float.parseFloat(uQuantity3);
                    addTotalMaterialTakenSpecific(modelListTMT.get(i).getMaterial(),cmp,consumerName,String.valueOf(q),uUnit3);
                     a3=1;
                }else if (modelListTMT.get(i).getMaterial().equalsIgnoreCase(uMaterial4)){
                    float q = Float.parseFloat(modelListTMT.get(i).getQuantity()) - Float.parseFloat(uQuantity4);
                    addTotalMaterialTakenSpecific(modelListTMT.get(i).getMaterial(),cmp,consumerName,String.valueOf(q),uUnit4);
                     a4=1;
                }else if (modelListTMT.get(i).getMaterial().equalsIgnoreCase(uMaterial5)){
                    float q = Float.parseFloat(modelListTMT.get(i).getQuantity()) - Float.parseFloat(uQuantity5);
                    addTotalMaterialTakenSpecific(modelListTMT.get(i).getMaterial(),cmp,consumerName,String.valueOf(q),uUnit5);
                     a5=1;
                }else if (modelListTMT.get(i).getMaterial().equalsIgnoreCase(uMaterial6)){
                    float q = Float.parseFloat(modelListTMT.get(i).getQuantity()) - Float.parseFloat(uQuantity6);
                    addTotalMaterialTakenSpecific(modelListTMT.get(i).getMaterial(),cmp,consumerName,String.valueOf(q),uUnit6);
                     a6=1;
                }else if (modelListTMT.get(i).getMaterial().equalsIgnoreCase(uMaterial7)){
                    float q = Float.parseFloat(modelListTMT.get(i).getQuantity()) - Float.parseFloat(uQuantity7);
                    addTotalMaterialTakenSpecific(modelListTMT.get(i).getMaterial(),cmp,consumerName,String.valueOf(q),uUnit7);
                     a7=1;
                }else if (modelListTMT.get(i).getMaterial().equalsIgnoreCase(uMaterial8)){
                    float q = Float.parseFloat(modelListTMT.get(i).getQuantity()) - Float.parseFloat(uQuantity8);
                    addTotalMaterialTakenSpecific(modelListTMT.get(i).getMaterial(),cmp,consumerName,String.valueOf(q),uUnit8);
                     a8=1;
                }else if (modelListTMT.get(i).getMaterial().equalsIgnoreCase(uMaterial9)){
                    float q = Float.parseFloat(modelListTMT.get(i).getQuantity()) - Float.parseFloat(uQuantity9);
                    addTotalMaterialTakenSpecific(modelListTMT.get(i).getMaterial(),cmp,consumerName,String.valueOf(q),uUnit9);
                     a9=1;
                }else if (modelListTMT.get(i).getMaterial().equalsIgnoreCase(uMaterial10)){
                    float q = Float.parseFloat(modelListTMT.get(i).getQuantity()) - Float.parseFloat(uQuantity10);
                    addTotalMaterialTakenSpecific(modelListTMT.get(i).getMaterial(),cmp,consumerName,String.valueOf(q),uUnit10);
                     a10=1;
                }else if (modelListTMT.get(i).getMaterial().equalsIgnoreCase(uMaterial11)){
                    float q = Float.parseFloat(modelListTMT.get(i).getQuantity()) - Float.parseFloat(uQuantity11);
                    addTotalMaterialTakenSpecific(modelListTMT.get(i).getMaterial(),cmp,consumerName,String.valueOf(q),uUnit11);
                     a11=1;
                }else if (modelListTMT.get(i).getMaterial().equalsIgnoreCase(uMaterial12)){
                    float q = Float.parseFloat(modelListTMT.get(i).getQuantity()) - Float.parseFloat(uQuantity12);
                    addTotalMaterialTakenSpecific(modelListTMT.get(i).getMaterial(),cmp,consumerName,String.valueOf(q),uUnit12);
                     a12=1;
                }else if (modelListTMT.get(i).getMaterial().equalsIgnoreCase(uMaterial13)){
                    float q = Float.parseFloat(modelListTMT.get(i).getQuantity()) - Float.parseFloat(uQuantity13);
                    addTotalMaterialTakenSpecific(modelListTMT.get(i).getMaterial(),cmp,consumerName,String.valueOf(q),uUnit13);
                     a13=1;
                }else if (modelListTMT.get(i).getMaterial().equalsIgnoreCase(uMaterial14)){
                    float q = Float.parseFloat(modelListTMT.get(i).getQuantity()) - Float.parseFloat(uQuantity14);
                    addTotalMaterialTakenSpecific(modelListTMT.get(i).getMaterial(),cmp,consumerName,String.valueOf(q),uUnit14);
                     a14=1;
                }else if (modelListTMT.get(i).getMaterial().equalsIgnoreCase(uMaterial15)){
                    float q = Float.parseFloat(modelListTMT.get(i).getQuantity()) - Float.parseFloat(uQuantity15);
                    addTotalMaterialTakenSpecific(modelListTMT.get(i).getMaterial(),cmp,consumerName,String.valueOf(q),uUnit15);
                     a15=1;
                }else if (modelListTMT.get(i).getMaterial().equalsIgnoreCase(uMaterial16)){
                    float q = Float.parseFloat(modelListTMT.get(i).getQuantity()) - Float.parseFloat(uQuantity16);
                    addTotalMaterialTakenSpecific(modelListTMT.get(i).getMaterial(),cmp,consumerName,String.valueOf(q),uUnit16);
                     a16=1;
                }else if (modelListTMT.get(i).getMaterial().equalsIgnoreCase(uMaterial17)){
                    float q = Float.parseFloat(modelListTMT.get(i).getQuantity()) - Float.parseFloat(uQuantity17);
                    addTotalMaterialTakenSpecific(modelListTMT.get(i).getMaterial(),cmp,consumerName,String.valueOf(q),uUnit17);
                     a17=1;
                }else if (modelListTMT.get(i).getMaterial().equalsIgnoreCase(uMaterial18)){
                    float q = Float.parseFloat(modelListTMT.get(i).getQuantity()) - Float.parseFloat(uQuantity18);
                    addTotalMaterialTakenSpecific(modelListTMT.get(i).getMaterial(),cmp,consumerName,String.valueOf(q),uUnit18);
                     a18=1;
                }else if (modelListTMT.get(i).getMaterial().equalsIgnoreCase(uMaterial19)){
                    float q = Float.parseFloat(modelListTMT.get(i).getQuantity()) - Float.parseFloat(uQuantity19);
                    addTotalMaterialTakenSpecific(modelListTMT.get(i).getMaterial(),cmp,consumerName,String.valueOf(q),uUnit19);
                     a19=1;
                }else if (modelListTMT.get(i).getMaterial().equalsIgnoreCase(uMaterial20)){
                    float q = Float.parseFloat(modelListTMT.get(i).getQuantity()) - Float.parseFloat(uQuantity20);
                    addTotalMaterialTakenSpecific(modelListTMT.get(i).getMaterial(),cmp,consumerName,String.valueOf(q),uUnit20);
                     a20=1;
                }else if (modelListTMT.get(i).getMaterial().equalsIgnoreCase(uMaterial21)){
                    float q = Float.parseFloat(modelListTMT.get(i).getQuantity()) - Float.parseFloat(uQuantity21);
                    addTotalMaterialTakenSpecific(modelListTMT.get(i).getMaterial(),cmp,consumerName,String.valueOf(q),uUnit21);
                     a21=1;
                }else if (modelListTMT.get(i).getMaterial().equalsIgnoreCase(uMaterial22)){
                    float q = Float.parseFloat(modelListTMT.get(i).getQuantity()) - Float.parseFloat(uQuantity22);
                    addTotalMaterialTakenSpecific(modelListTMT.get(i).getMaterial(),cmp,consumerName,String.valueOf(q),uUnit22);
                     a22=1;
                }else if (modelListTMT.get(i).getMaterial().equalsIgnoreCase(uMaterial23)){
                    float q = Float.parseFloat(modelListTMT.get(i).getQuantity()) - Float.parseFloat(uQuantity23);
                    addTotalMaterialTakenSpecific(modelListTMT.get(i).getMaterial(),cmp,consumerName,String.valueOf(q),uUnit23);
                     a23=1;
                }else if (modelListTMT.get(i).getMaterial().equalsIgnoreCase(uMaterial24)){
                    float q = Float.parseFloat(modelListTMT.get(i).getQuantity()) - Float.parseFloat(uQuantity24);
                    addTotalMaterialTakenSpecific(modelListTMT.get(i).getMaterial(),cmp,consumerName,String.valueOf(q),uUnit24);
                     a24=1;
                }else if (modelListTMT.get(i).getMaterial().equalsIgnoreCase(uMaterial25)){
                    float q = Float.parseFloat(modelListTMT.get(i).getQuantity()) - Float.parseFloat(uQuantity25);
                    addTotalMaterialTakenSpecific(modelListTMT.get(i).getMaterial(),cmp,consumerName,String.valueOf(q),uUnit25);
                     a25=1;
                }else if (modelListTMT.get(i).getMaterial().equalsIgnoreCase(uMaterial26)){
                    float q = Float.parseFloat(modelListTMT.get(i).getQuantity()) - Float.parseFloat(uQuantity26);
                    addTotalMaterialTakenSpecific(modelListTMT.get(i).getMaterial(),cmp,consumerName,String.valueOf(q),uUnit26);
                     a26=1;
                }else if (modelListTMT.get(i).getMaterial().equalsIgnoreCase(uMaterial27)){
                    float q = Float.parseFloat(modelListTMT.get(i).getQuantity()) - Float.parseFloat(uQuantity27);
                    addTotalMaterialTakenSpecific(modelListTMT.get(i).getMaterial(),cmp,consumerName,String.valueOf(q),uUnit27);
                     a27=1;
                }else if (modelListTMT.get(i).getMaterial().equalsIgnoreCase(uMaterial28)){
                    float q = Float.parseFloat(modelListTMT.get(i).getQuantity()) - Float.parseFloat(uQuantity28);
                    addTotalMaterialTakenSpecific(modelListTMT.get(i).getMaterial(),cmp,consumerName,String.valueOf(q),uUnit28);
                     a28=1;
                }else if (modelListTMT.get(i).getMaterial().equalsIgnoreCase(uMaterial29)){
                    float q = Float.parseFloat(modelListTMT.get(i).getQuantity()) - Float.parseFloat(uQuantity29);
                    addTotalMaterialTakenSpecific(modelListTMT.get(i).getMaterial(),cmp,consumerName,String.valueOf(q),uUnit29);
                     a29=1;
                }else if (modelListTMT.get(i).getMaterial().equalsIgnoreCase(uMaterial30)){
                    float q = Float.parseFloat(modelListTMT.get(i).getQuantity()) - Float.parseFloat(uQuantity30);
                    addTotalMaterialTakenSpecific(modelListTMT.get(i).getMaterial(),cmp,consumerName,String.valueOf(q),uUnit30);
                     a30=1;
                }
                else{
                    addTotalMaterialTakenSpecific(modelListTMT.get(i).getMaterial(),cmp,consumerName,modelListTMT.get(i).getQuantity(),modelListTMT.get(i).getUnit());

                }

            }



            if (a1==0 && !uMaterial1.equals("")){
                addTotalMaterialTakenSpecific(uMaterial1,cmp,consumerName,uQuantity1,uUnit1);
            }

            if (a2==0 && !uMaterial2.equals("")){
                addTotalMaterialTakenSpecific(uMaterial2,cmp,consumerName,uQuantity2,uUnit2);
            }
            if (a3==0 && !uMaterial3.equals("")){
                addTotalMaterialTakenSpecific(uMaterial3,cmp,consumerName,uQuantity3,uUnit3);
            }

            if (a4==0 && !uMaterial4.equals("")){
                addTotalMaterialTakenSpecific(uMaterial4,cmp,consumerName,uQuantity4,uUnit4);
            }

            if (a5==0 && !uMaterial5.equals("")){
                addTotalMaterialTakenSpecific(uMaterial5,cmp,consumerName,uQuantity5,uUnit5);
            }

            if (a6==0 && !uMaterial6.equals("")){
                addTotalMaterialTakenSpecific(uMaterial6,cmp,consumerName,uQuantity6,uUnit6);
            }

            if (a7==0 && !uMaterial7.equals("")){
                addTotalMaterialTakenSpecific(uMaterial7,cmp,consumerName,uQuantity7,uUnit7);
            }

            if (a8==0 && !uMaterial8.equals("")){
                addTotalMaterialTakenSpecific(uMaterial8,cmp,consumerName,uQuantity8,uUnit8);
            }

            if (a9==0 && !uMaterial9.equals("")){
                addTotalMaterialTakenSpecific(uMaterial9,cmp,consumerName,uQuantity9,uUnit9);
            }

            if (a10==0 && !uMaterial10.equals("")){
                addTotalMaterialTakenSpecific(uMaterial10,cmp,consumerName,uQuantity10,uUnit10);
            }

            if (a11==0 && !uMaterial11.equals("")){
                addTotalMaterialTakenSpecific(uMaterial11,cmp,consumerName,uQuantity11,uUnit11);
            }

            if (a12==0 && !uMaterial12.equals("")){
                addTotalMaterialTakenSpecific(uMaterial12,cmp,consumerName,uQuantity12,uUnit12);
            }

            if (a13==0 && !uMaterial13.equals("")){
                addTotalMaterialTakenSpecific(uMaterial13,cmp,consumerName,uQuantity13,uUnit13);
            }

            if (a14==0 && !uMaterial14.equals("")){
                addTotalMaterialTakenSpecific(uMaterial14,cmp,consumerName,uQuantity14,uUnit14);
            }

            if (a15==0 && !uMaterial15.equals("")){
                addTotalMaterialTakenSpecific(uMaterial15,cmp,consumerName,uQuantity15,uUnit15);
            }

            if (a16==0 && !uMaterial16.equals("")){
                addTotalMaterialTakenSpecific(uMaterial16,cmp,consumerName,uQuantity16,uUnit16);
            }

            if (a17==0 && !uMaterial17.equals("")){
                addTotalMaterialTakenSpecific(uMaterial17,cmp,consumerName,uQuantity17,uUnit17);
            }

            if (a18==0 && !uMaterial18.equals("")){
                addTotalMaterialTakenSpecific(uMaterial18,cmp,consumerName,uQuantity18,uUnit18);
            }

            if (a19==0 && !uMaterial19.equals("")){
                addTotalMaterialTakenSpecific(uMaterial19,cmp,consumerName,uQuantity19,uUnit19);
            }

            if (a20==0 && !uMaterial20.equals("")){
                addTotalMaterialTakenSpecific(uMaterial20,cmp,consumerName,uQuantity20,uUnit20);
            }

            if (a21==0 && !uMaterial21.equals("")){
                addTotalMaterialTakenSpecific(uMaterial21,cmp,consumerName,uQuantity21,uUnit21);
            }

            if (a22==0 && !uMaterial22.equals("")){
                addTotalMaterialTakenSpecific(uMaterial22,cmp,consumerName,uQuantity22,uUnit22);
            }

            if (a23==0 && !uMaterial23.equals("")){
                addTotalMaterialTakenSpecific(uMaterial23,cmp,consumerName,uQuantity23,uUnit23);
            }

            if (a24==0 && !uMaterial24.equals("")){
                addTotalMaterialTakenSpecific(uMaterial24,cmp,consumerName,uQuantity24,uUnit24);
            }

            if (a25==0 && !uMaterial25.equals("")){
                addTotalMaterialTakenSpecific(uMaterial25,cmp,consumerName,uQuantity25,uUnit25);
            }

            if (a26==0 && !uMaterial26.equals("")){
                addTotalMaterialTakenSpecific(uMaterial26,cmp,consumerName,uQuantity26,uUnit26);
            }

            if (a27==0 && !uMaterial27.equals("")){
                addTotalMaterialTakenSpecific(uMaterial27,cmp,consumerName,uQuantity27,uUnit27);
            }

            if (a28==0 && !uMaterial28.equals("")){
                addTotalMaterialTakenSpecific(uMaterial28,cmp,consumerName,uQuantity28,uUnit28);
            }

            if (a29==0 && !uMaterial29.equals("")){
                addTotalMaterialTakenSpecific(uMaterial29,cmp,consumerName,uQuantity29,uUnit29);
            }

            if (a30==0 && !uMaterial30.equals("")){
                addTotalMaterialTakenSpecific(uMaterial30,cmp,consumerName,uQuantity30,uUnit30);
            }



        }

        Map<String, Object> doc = new HashMap<>();

        doc.put("id", id);
        doc.put("Date",uDate);
        doc.put("SearchDate",uDate.toLowerCase());
        doc.put("Team Name",uTeamName);
        doc.put("SearchTeamName",uTeamName.toLowerCase());
        doc.put("Line",uLine);
        doc.put("Tender",uTender);
        doc.put("SearchTender",uTender.toLowerCase());
        doc.put("Driver Name",uDriverName);
        doc.put("Vehical Name",uVehicalName);
        doc.put("Consumer Name",uConsumerName);
        doc.put("Site Name",uSite);

        doc.put("Material Receiver Name",materialReceiver);
        doc.put("Center",centerS);
        doc.put("Village",villageS);
        doc.put("SearchCenter",centerS.toLowerCase());
        doc.put("SearchVillage",villageS.toLowerCase());

        fStore.collection(cmp+" BalanceMaterialOnSite").document(id).set(doc).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                //this will be called when data is added Successfully

                Toast.makeText(getApplicationContext(), "Data Added !!", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //this will be called when data is added Failed
                Toast.makeText(getApplicationContext(), "Failed to add data "+e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });





    }

    private void addTotalMaterialTakenSpecific(String material, String cmp, String consumerName, String quantity,String unit) {

        Map<String, Object> doc = new HashMap<>();
        doc.put("Id",material);
        doc.put("Material",material);
        doc.put("Unit",unit);
        doc.put("Quantity",quantity);

        fStore.collection(cmp+" "+consumerName+" AAM").document(material).set(doc).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                //this will be called when data is added Successfully
                Toast.makeText(getApplicationContext(), "Total Material Added", Toast.LENGTH_SHORT).show();
                animation.setVisibility(View.VISIBLE);
                mediaPlayer.start();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //this will be called when data is added Failed
                Toast.makeText(getApplicationContext(), "Failed to add data "+e.getMessage(), Toast.LENGTH_SHORT).show();
                animation.setVisibility(View.GONE);
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
    public void retrieveDataDriver(){
        listenerDriver = databaseReferenceDriver.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot item : snapshot.getChildren()){
                    spinnerDataListDriver.add(item.getValue().toString());
                }
                adapterDriver.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void retrieveDataVehical(){
        listenerVehical = databaseReferenceVehical.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot item : snapshot.getChildren()){
                    spinnerDataListVehical.add(item.getValue().toString());
                }
                adapterVehical.notifyDataSetChanged();
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

}