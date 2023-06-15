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

public class AddMaterial extends AppCompatActivity {

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
    MaterialButton middleBackBtn;
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

    String idS,dateS,teamNameS,lineS,tenderS,driverNameS,vehicalNameS,consumerNameS,siteNameS, mReceiver;

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
    String centerSet,villageSet;



    MediaPlayer mediaPlayer;

    String id = UUID.randomUUID().toString();


    //Stock Material
    List<AddStockModel> modelList = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    AdminStockAdapter adapterStock;


    //Kg to no
    List<KgToNoModel> modelListKN = new ArrayList<>();
    RecyclerView recyclerViewKN;
    RecyclerView.LayoutManager layoutManagerKN;
    AdminKgToNoAdapter adapterStockKN;


    //Total Material Taken
    List<AddTotalMaterialTakenModel> modelListTMT = new ArrayList<>();
    RecyclerView recyclerViewTMT;
    RecyclerView.LayoutManager layoutManagerTMT;
    AddTotalMaterialTakenAdapter adapterStockTMT;


    //Add Data
    List<AdminModel> modelListAddData = new ArrayList<>();
    RecyclerView recycleAddData;
    RecyclerView.LayoutManager layoutManagerAddData;
    ShowDataAdminCustomAdapter adapterStockAddData;

//    SwipeRefreshLayout refreshLayout;


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

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_material);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

//        pd = ProgressDialog.show(this,"Loading...","Please Wait",false,false);

//        time = new Timer();
//        time.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                pd.dismiss();
//            }
//        },3000);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        mediaPlayer = MediaPlayer.create(this,R.raw.sound);

        //Auth
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = mAuth.getCurrentUser().getUid();

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
                        AddMaterial.this, R.style.DatePicker1,
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
        middleBackBtn = findViewById(R.id.backMiddle);
        nextLastTwo = findViewById(R.id.nextLastTwo);
        doneBtn = findViewById(R.id.done);
        lastBackBtn = findViewById(R.id.backLastOne);
        doneAll = findViewById(R.id.doneAll);

        //TextInputEditText
        consumer = findViewById(R.id.consumer);
        site = findViewById(R.id.siteName);
        passReceiver = findViewById(R.id.pass);


            DocumentReference documentReference = fStore.collection("Users")
            .document(userId);
            documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
        @Override
        public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {


                companyEmail = value.getString("companyEmail");
                cmp = companyEmail;
                companyEmail = companyEmail.replace("@", "");
                companyEmail = companyEmail.replace(".", "");

            //Stock Material
            recyclerView = findViewById(R.id.recyclerAM);
            recyclerView.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(AddMaterial.this);
            recyclerView.setLayoutManager(layoutManager);
            modelList.clear();
            showData(cmp);


            //Stock Material
            recyclerViewTMT = findViewById(R.id.recyclerATMT);
            recyclerViewTMT.setHasFixedSize(true);
            layoutManagerTMT = new LinearLayoutManager(AddMaterial.this);
            recyclerViewTMT.setLayoutManager(layoutManagerTMT);
            modelListTMT.clear();


            //Add Data
            recycleAddData = findViewById(R.id.recycleAddData);
            recycleAddData.setHasFixedSize(true);
            layoutManagerAddData = new LinearLayoutManager(AddMaterial.this);
            recycleAddData.setLayoutManager(layoutManagerAddData);
            modelListAddData.clear();
            showAddData(cmp);


            //Stock Material
            recyclerViewKN = findViewById(R.id.recyclerKN);
            recyclerViewKN.setHasFixedSize(true);
            layoutManagerKN = new LinearLayoutManager(AddMaterial.this);
            recyclerViewKN.setLayoutManager(layoutManagerKN);
            modelListKN.clear();
            showDataKN(cmp);

            ActionBar bar = getSupportActionBar();
            assert bar != null;
            bar.setTitle("MATERIAL DELIVERY");

//                Finding Error
//                refreshLayout = findViewById(R.id.refresh);
//                refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//                    @Override
//                    public void onRefresh() {
//                        refreshLayout.setRefreshing(false);
//                        retrieveData();
//                        teamName.setText("Non");
//                    }
//                });

            Bundle bundle = getIntent().getExtras();

            if (bundle != null) {
                idS = bundle.getString("Id");
                dateS = bundle.getString("Date");
                teamNameS = bundle.getString("TeamName");
                lineS = bundle.getString("Line");
                tenderS = bundle.getString("Tender");
                driverNameS = bundle.getString("Driver Name");
                vehicalNameS = bundle.getString("Vehical Name");
                consumerNameS = bundle.getString("Consumer Name");
                siteNameS = bundle.getString("Site");

                //material
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

                centerSet = bundle.getString("Center");
                villageSet = bundle.getString("Village");

                mReceiver = bundle.getString("Material Receiver Name");

                receiver.setText(mReceiver);

                mDateFormate.setText(dateS);
                teamName.setText(teamNameS);
                line.setText(lineS);
                tender.setText(tenderS);
                driver.setText(driverNameS);
                vehical.setText(vehicalNameS);
                consumer.setText(consumerNameS);
                site.setText(siteNameS);

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
                unit1.setText(unit1S);
                unit2.setText(unit2S);
                unit3.setText(unit3S);
                unit4.setText(unit4S);
                unit5.setText(unit5S);
                unit6.setText(unit6S);
                unit7.setText(unit7S);
                unit8.setText(unit8S);
                unit9.setText(unit9S);
                unit10.setText(unit10S);
                unit11.setText(unit11S);
                unit12.setText(unit12S);
                unit13.setText(unit13S);
                unit14.setText(unit14S);
                unit15.setText(unit15S);
                unit16.setText(unit16S);
                unit17.setText(unit17S);
                unit18.setText(unit18S);
                unit19.setText(unit19S);
                unit20.setText(unit20S);
                unit21.setText(unit21S);
                unit22.setText(unit22S);
                unit23.setText(unit23S);
                unit24.setText(unit24S);
                unit25.setText(unit25S);
                unit26.setText(unit26S);
                unit27.setText(unit27S);
                unit28.setText(unit28S);
                unit29.setText(unit29S);
                unit30.setText(unit30S);

            }
            //State
            databaseReferenceState = FirebaseDatabase.getInstance().getReference(companyEmail + " State");
            spinnerDataListState = new ArrayList<>();
            adapterState = new ArrayAdapter<String>(AddMaterial.this, R.layout.support_simple_spinner_dropdown_item, spinnerDataListState);
            state.setAdapter(adapterState);
            retrieveDataState();
            //District
            databaseReferenceDistrict = FirebaseDatabase.getInstance().getReference(companyEmail + " District");
            spinnerDataListDistrict = new ArrayList<>();
            adapterDistrict = new ArrayAdapter<String>(AddMaterial.this, R.layout.support_simple_spinner_dropdown_item, spinnerDataListDistrict);
            district.setAdapter(adapterDistrict);
            retrieveDataDistrict();


            //Taluka
            databaseReferenceTaluka = FirebaseDatabase.getInstance().getReference(companyEmail + " Taluka");
            spinnerDataListTaluka = new ArrayList<>();
            adapterTaluka = new ArrayAdapter<String>(AddMaterial.this, R.layout.support_simple_spinner_dropdown_item, spinnerDataListTaluka);
            taluka.setAdapter(adapterTaluka);
            retrieveDataTaluka();

            //Team Name
            databaseReference = FirebaseDatabase.getInstance().getReference(companyEmail + " teamName");
            spinnerDataList = new ArrayList<>();
            adapter = new ArrayAdapter<String>(AddMaterial.this, R.layout.support_simple_spinner_dropdown_item, spinnerDataList);
            teamName.setAdapter(adapter);
            retrieveData();

            //Line
            databaseReferenceLine = FirebaseDatabase.getInstance().getReference(companyEmail + " Line");
            spinnerDataListLine = new ArrayList<>();
            adapterLine = new ArrayAdapter<String>(AddMaterial.this, R.layout.support_simple_spinner_dropdown_item, spinnerDataListLine);
            line.setAdapter(adapterLine);
            retrieveDataLine();

            //Tender
            databaseReferenceTender = FirebaseDatabase.getInstance().getReference(companyEmail + " Tender");
            spinnerDataListTender = new ArrayList<>();
            adapterTender = new ArrayAdapter<String>(AddMaterial.this, R.layout.support_simple_spinner_dropdown_item, spinnerDataListTender);
            tender.setAdapter(adapterTender);
            retrieveDataTender();

            //Driver
            databaseReferenceDriver = FirebaseDatabase.getInstance().getReference(companyEmail + " Driver");
            spinnerDataListDriver = new ArrayList<>();
            adapterDriver = new ArrayAdapter<String>(AddMaterial.this, R.layout.support_simple_spinner_dropdown_item, spinnerDataListDriver);
            driver.setAdapter(adapterDriver);
            retrieveDataDriver();

            //Vehical
            databaseReferenceVehical = FirebaseDatabase.getInstance().getReference(companyEmail + " Vehical");
            spinnerDataListVehical = new ArrayList<>();
            adapterVehical = new ArrayAdapter<String>(AddMaterial.this, R.layout.support_simple_spinner_dropdown_item, spinnerDataListVehical);
            vehical.setAdapter(adapterVehical);
            retrieveDataVehical();

            //Center
            databaseReferenceCenter = FirebaseDatabase.getInstance().getReference(companyEmail + " Center");
            spinnerDataListCenter = new ArrayList<>();
            adapterCenter = new ArrayAdapter<String>(AddMaterial.this, R.layout.support_simple_spinner_dropdown_item, spinnerDataListCenter);
            center.setAdapter(adapterCenter);
            retrieveDataCenter();

            //Village
            databaseReferenceVillage = FirebaseDatabase.getInstance().getReference(companyEmail + " Village");
            spinnerDataListVillage = new ArrayList<>();
            adapterVillage = new ArrayAdapter<String>(AddMaterial.this, R.layout.support_simple_spinner_dropdown_item, spinnerDataListVillage);
            village.setAdapter(adapterVillage);
            retrieveDataVillage();

            //Material
            databaseReferenceMaterial = FirebaseDatabase.getInstance().getReference(companyEmail + " Material");
            spinnerDataListMaterial = new ArrayList<>();
            adapterMaterial = new ArrayAdapter<String>(AddMaterial.this, R.layout.support_simple_spinner_dropdown_item, spinnerDataListMaterial);
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
            adapterUnit = new ArrayAdapter<String>(AddMaterial.this, R.layout.support_simple_spinner_dropdown_item, spinnerDataListUnit);
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
            databaseReferenceReceiver = FirebaseDatabase.getInstance().getReference(companyEmail + " Receiver");
            spinnerDataListReceiver = new ArrayList<>();
            adapterReceiver = new ArrayAdapter<String>(AddMaterial.this, R.layout.support_simple_spinner_dropdown_item, spinnerDataListReceiver);
            receiver.setAdapter(adapterReceiver);
            retrieveDataReceiver();


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


                    if (uMaterial1.isEmpty() || uQuantity1.isEmpty() || uUnit1.isEmpty() || uMaterial2.isEmpty() || uQuantity2.isEmpty() || uUnit2.isEmpty() ||
                            uMaterial3.isEmpty() || uQuantity3.isEmpty() || uUnit3.isEmpty() || uMaterial4.isEmpty() || uQuantity4.isEmpty() || uUnit4.isEmpty() ||
                            uMaterial5.isEmpty() || uQuantity5.isEmpty() || uUnit5.isEmpty()) {
                        showMessage("Fill above information first");
                    } else {
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


                    if (uMaterial6.isEmpty() || uQuantity6.isEmpty() || uUnit6.isEmpty() || uMaterial7.isEmpty() || uQuantity7.isEmpty() || uUnit7.isEmpty() ||
                            uMaterial8.isEmpty() || uQuantity8.isEmpty() || uUnit8.isEmpty() || uMaterial9.isEmpty() || uQuantity9.isEmpty() || uUnit9.isEmpty() ||
                            uMaterial10.isEmpty() || uQuantity10.isEmpty() || uUnit10.isEmpty()) {
                        showMessage("Fill above information first");
                    } else {
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


                    if (uMaterial11.isEmpty() || uQuantity11.isEmpty() || uUnit11.isEmpty() || uMaterial12.isEmpty() || uQuantity12.isEmpty() || uUnit12.isEmpty() ||
                            uMaterial13.isEmpty() || uQuantity13.isEmpty() || uUnit13.isEmpty() || uMaterial14.isEmpty() || uQuantity14.isEmpty() || uUnit14.isEmpty() ||
                            uMaterial15.isEmpty() || uQuantity15.isEmpty() || uUnit15.isEmpty()) {
                        showMessage("Fill above information first");
                    } else {
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

                    if (uMaterial16.isEmpty() || uQuantity16.isEmpty() || uUnit16.isEmpty() || uMaterial17.isEmpty() || uQuantity17.isEmpty() || uUnit17.isEmpty() ||
                            uMaterial18.isEmpty() || uQuantity18.isEmpty() || uUnit18.isEmpty() || uMaterial19.isEmpty() || uQuantity19.isEmpty() || uUnit19.isEmpty() ||
                            uMaterial20.isEmpty() || uQuantity20.isEmpty() || uUnit20.isEmpty()) {
                        showMessage("Fill above information first");
                    } else {
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


                    if (uMaterial21.isEmpty() || uQuantity21.isEmpty() || uUnit21.isEmpty() || uMaterial22.isEmpty() || uQuantity22.isEmpty() || uUnit22.isEmpty() ||
                            uMaterial23.isEmpty() || uQuantity23.isEmpty() || uUnit23.isEmpty() || uMaterial24.isEmpty() || uQuantity24.isEmpty() || uUnit24.isEmpty() ||
                            uMaterial25.isEmpty() || uQuantity25.isEmpty() || uUnit25.isEmpty()) {
                        showMessage("Fill above information first");
                    } else {
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

                    if (dateS.isEmpty()) {
                        mDateFormate.setError(r);
                        mDateFormate.requestFocus();
                    } else if (team.isEmpty()) {
                        teamName.setError(r);
                        teamName.requestFocus();
                    } else if (lineS.isEmpty()) {
                        line.setError(r);
                        line.requestFocus();
                    } else if (tenderS.isEmpty()) {
                        tender.setError(r);
                        tender.requestFocus();
                    } else if (driverS.isEmpty()) {
                        driver.setError(r);
                        driver.requestFocus();
                    } else if (vehicalS.isEmpty()) {
                        vehical.setError(r);
                        vehical.requestFocus();
                    } else {
                        homeLL.setVisibility(View.GONE);
                        middleLL.setVisibility(View.VISIBLE);
                        pair(cmp, tenderS);
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
                    String stateS = state.getText().toString().trim();
                    String dis = district.getText().toString().trim();
                    String talukaS = taluka.getText().toString().trim();
                    String centerS = center.getText().toString().trim();
                    String villageS = village.getText().toString().trim();

                    if (consumerName.isEmpty()) {
                        consumer.setError(r);
                        consumer.requestFocus();
                    } else if (stateS.isEmpty()) {
                        state.setError(r);
                        state.requestFocus();
                    } else if (dis.isEmpty()) {
                        district.setError(r);
                        district.requestFocus();
                    } else if (talukaS.isEmpty()) {
                        taluka.setError(r);
                        taluka.requestFocus();
                    } else if (centerS.isEmpty()) {
                        center.setError(r);
                        center.requestFocus();
                    } else if (villageS.isEmpty()) {
                        village.setError(r);
                        village.requestFocus();
                    } else if (siteName.isEmpty()) {
                        site.setError(r);
                        site.requestFocus();
                    } else {
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
                    String uSite = (site.getText().toString().trim()) + "  " + (taluka.getText().toString().trim()) + "  " + (district.getText().toString().trim()) + "  " + (state.getText().toString().trim());


                    showTotalMaterialTaken(cmp,uConsumerName);


                    //Material
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


                    //Receiver
                    String materialReceiver = receiver.getText().toString().trim();

                    String centerS = center.getText().toString().trim();
                    String villageS = village.getText().toString().trim();


                    if (materialReceiver.isEmpty()) {
                        receiver.setError(r);
                        receiver.requestFocus();
                    } else if (pass.isEmpty()) {
                        passReceiver.setError(r);
                        passReceiver.requestFocus();
                    } else if (bundle != null) {

                    } else {
                        verifyPassReceiver(materialReceiver, pass, cmp, uDate, uTeamName, uLine, uTender, uDriverName, uVehicalName, uConsumerName, uSite
                                , uMaterial1, uQuantity1, uMaterial2, uQuantity2, uMaterial3, uQuantity3, uMaterial4, uQuantity4, uMaterial5, uQuantity5,
                                uMaterial6, uQuantity6, uMaterial7, uQuantity7, uMaterial8, uQuantity8, uMaterial9, uQuantity9, uMaterial10, uQuantity10
                                , uQuantity11, uMaterial12, uQuantity12, uMaterial13, uQuantity13, uMaterial14, uQuantity14, uMaterial15, uQuantity15,
                                uMaterial16, uQuantity16, uMaterial17, uQuantity17, uMaterial18, uQuantity18, uMaterial19, uQuantity19, uMaterial20, uQuantity20
                                , uUnit1, uUnit2, uUnit3, uUnit4, uUnit5, uUnit6, uUnit7, uUnit8, uUnit9, uUnit10
                                , uUnit11, uUnit12, uUnit13, uUnit14, uUnit15, uUnit16, uUnit17, uUnit18, uUnit19, uUnit20, uMaterial11
                                , uMaterial21, uQuantity21, uMaterial22, uQuantity22, uMaterial23, uQuantity23, uMaterial24, uQuantity24, uMaterial25, uQuantity25,
                                uMaterial26, uQuantity26, uMaterial27, uQuantity27, uMaterial28, uQuantity28, uMaterial29, uQuantity29, uMaterial30, uQuantity30
                                , uUnit21, uUnit22, uUnit23, uUnit24, uUnit25, uUnit26, uUnit27, uUnit28, uUnit29, uUnit30,
                                centerS, villageS);

                    }
                }
            });

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    doneAll.setEnabled(true);
                }
            }, 1500);
            doneAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), Admin.class));
                    finish();
                }
            });

            if (bundle != null) {


                //Receiver
                String materialReceiver = receiver.getText().toString().trim();
                //NextLastTwo
                nextLastTwo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Home
                        String uDate = mDateFormate.getText().toString().trim();
                        String uTeamName = teamName.getText().toString().trim();
                        String uLine = line.getText().toString().trim();
                        String uTender = tender.getText().toString().trim();
                        String uDriverName = driver.getText().toString().trim();
                        String uVehicalName = vehical.getText().toString().trim();

                        //Site
                        String uConsumerName = consumer.getText().toString().trim();
                        String uSite = (site.getText().toString().trim()) + "  " + (taluka.getText().toString().trim()) + "  " + (district.getText().toString().trim()) + "  " + (state.getText().toString().trim());

                        //Material
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
                        String uUnit29 = unit29.getText().toString().trim();

                        String uMaterial30 = material30.getText().toString().trim();
                        String uQuantity30 = quantity30.getText().toString().trim();
                        String uUnit30 = unit30.getText().toString().trim();


                        String centerS = center.getText().toString().trim();
                        String villageS = village.getText().toString().trim();
                        lastOneLL.setVisibility(View.GONE);
                        lastLL.setVisibility(View.GONE);
                        update(cmp, uDate, uTeamName, uLine, uTender, uDriverName, uVehicalName, uConsumerName, uSite, uMaterial1, uQuantity1, uMaterial2, uQuantity2,
                                uMaterial3, uQuantity3, uMaterial4, uQuantity4, uMaterial5,
                                uQuantity5, uMaterial6, uQuantity6, uMaterial7, uQuantity7, uMaterial8, uQuantity8, uMaterial9, uQuantity9, uMaterial10, uQuantity10,
                                uQuantity11, uMaterial12, uQuantity12, uMaterial13, uQuantity13, uMaterial14, uQuantity14, uMaterial15, uQuantity15, uMaterial16, uQuantity16,
                                uMaterial17, uQuantity17, uMaterial18, uQuantity18, uMaterial19, uQuantity19, uMaterial20, uQuantity20, uUnit1, uUnit2, uUnit3, uUnit4, uUnit5, uUnit6, uUnit7, uUnit8, uUnit9,
                                uUnit10, uUnit11, uUnit12, uUnit13, uUnit14, uUnit15, uUnit16, uUnit17, uUnit18, uUnit19, uUnit20, uMaterial11, uMaterial21, uQuantity21, uMaterial22, uQuantity22,
                                uMaterial23, uQuantity23, uMaterial24, uQuantity24, uMaterial25, uQuantity25, uMaterial26, uQuantity26, uMaterial27, uQuantity27, uMaterial28,
                                uQuantity28, uMaterial29, uQuantity29, uMaterial30, uQuantity30, uUnit21, uUnit22, uUnit23, uUnit24, uUnit25, uUnit26, uUnit27, uUnit28, uUnit29,
                                uUnit30, materialReceiver, centerS, villageS);
                    }
                });

            }

        }
    });

    }

    private void showAddData(String cmp) {
        fStore.collection(cmp + " RemainingMaterial").orderBy("Date", Query.Direction.DESCENDING).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        modelList.clear();
                        for (DocumentSnapshot doc : queryDocumentSnapshots) {
                            AdminModel model = new AdminModel(
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

                            modelListAddData.add(model);
                        }
                        adapterStockAddData = new ShowDataAdminCustomAdapter(AddMaterial.this, modelListAddData);
                        recycleAddData.setAdapter(adapterStockAddData);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
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
                adapterStockTMT = new AddTotalMaterialTakenAdapter(AddMaterial.this,modelListTMT);
                recyclerViewTMT.setAdapter(adapterStockTMT);
            }}).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AddMaterial.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void showData(String cmp) {
        fStore.collection(cmp+" AddStock").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                modelList.clear();
                for (DocumentSnapshot doc : task.getResult()){
                    AddStockModel model = new AddStockModel(
                            doc.getString("Id"),
                            doc.getString("Material"),
                            doc.getString("Unit"),
                            doc.getString("Quantity")
                    );
                    modelList.add(model);
                }
                adapterStock = new AdminStockAdapter(AddMaterial.this,modelList);
                recyclerView.setAdapter(adapterStock);
            }}).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AddMaterial.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showDataKN(String cmp) {
        fStore.collection(cmp+" KgToNo").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                modelListKN.clear();
                for (DocumentSnapshot doc : task.getResult()){
                    KgToNoModel model = new KgToNoModel(
                            doc.getString("Id"),
                            doc.getString("Material"),
                            doc.getString("Number"),
                            doc.getString("Unit")
                    );
                    modelListKN.add(model);
                }
                adapterStockKN = new AdminKgToNoAdapter(AddMaterial.this,modelListKN);
                recyclerViewKN.setAdapter(adapterStockKN);
            }}).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AddMaterial.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
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

                    Bundle bundle = getIntent().getExtras();
                    if (bundle!=null){
                        update(cmp,uDate,uTeamName,uLine,uTender,uDriverName,uVehicalName,uConsumerName,uSite,uMaterial1,uQuantity1,uMaterial2,uQuantity2,uMaterial3,uQuantity3,uMaterial4,uQuantity4,uMaterial5,uQuantity5,
                                uMaterial6,uQuantity6,uMaterial7,uQuantity7,uMaterial8,uQuantity8,uMaterial9,uQuantity9,uMaterial10,uQuantity10
                                ,uQuantity11,uMaterial12,uQuantity12,uMaterial13,uQuantity13,uMaterial14,uQuantity14,uMaterial15,uQuantity15,
                                uMaterial16,uQuantity16,uMaterial17,uQuantity17,uMaterial18,uQuantity18,uMaterial19,uQuantity19,uMaterial20,uQuantity20
                                ,uUnit1,uUnit2,uUnit3,uUnit4,uUnit5,uUnit6,uUnit7,uUnit8,uUnit9,uUnit10
                                ,uUnit11,uUnit12,uUnit13,uUnit14,uUnit15,uUnit16,uUnit17,uUnit18,uUnit19,uUnit20,uMaterial11
                                ,uMaterial21,uQuantity21,uMaterial22,uQuantity22,uMaterial23,uQuantity23,uMaterial24,uQuantity24,uMaterial25,uQuantity25,
                                uMaterial26,uQuantity26,uMaterial27,uQuantity27,uMaterial28,uQuantity28,uMaterial29,uQuantity29,uMaterial30,uQuantity30
                                ,uUnit21,uUnit22,uUnit23,uUnit24,uUnit25,uUnit26,uUnit27,uUnit28,uUnit29,uUnit30,materialReceiver,centerS,villageS);

                    }
                    else {

                        uploadData(cmp, uDate, uTeamName, uLine, uTender, uDriverName, uVehicalName, uConsumerName, uSite, uMaterial1, uQuantity1, uMaterial2, uQuantity2, uMaterial3, uQuantity3, uMaterial4, uQuantity4, uMaterial5, uQuantity5,
                                uMaterial6, uQuantity6, uMaterial7, uQuantity7, uMaterial8, uQuantity8, uMaterial9, uQuantity9, uMaterial10, uQuantity10
                                , uQuantity11, uMaterial12, uQuantity12, uMaterial13, uQuantity13, uMaterial14, uQuantity14, uMaterial15, uQuantity15,
                                uMaterial16, uQuantity16, uMaterial17, uQuantity17, uMaterial18, uQuantity18, uMaterial19, uQuantity19, uMaterial20, uQuantity20
                                , uUnit1, uUnit2, uUnit3, uUnit4, uUnit5, uUnit6, uUnit7, uUnit8, uUnit9, uUnit10
                                , uUnit11, uUnit12, uUnit13, uUnit14, uUnit15, uUnit16, uUnit17, uUnit18, uUnit19, uUnit20, uMaterial11
                                , uMaterial21, uQuantity21, uMaterial22, uQuantity22, uMaterial23, uQuantity23, uMaterial24, uQuantity24, uMaterial25, uQuantity25,
                                uMaterial26, uQuantity26, uMaterial27, uQuantity27, uMaterial28, uQuantity28, uMaterial29, uQuantity29, uMaterial30, uQuantity30
                                , uUnit21, uUnit22, uUnit23, uUnit24, uUnit25, uUnit26, uUnit27, uUnit28, uUnit29, uUnit30, materialReceiver,centerS,villageS);
                    }

                }
                else {
                    passReceiver.setError("Password does not Match!!");
                    passReceiver.requestFocus();
                    Toast.makeText(getApplicationContext(), "Password does not Match!!", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
    private void uploadData(String cmp, String uDate, String uTeamName, String uLine, String uTender, String uDriverName, String uVehicalName, String uConsumerName, String uSite, String uMaterial1, String uQuantity1, String uMaterial2, String uQuantity2, String uMaterial3, String uQuantity3, String uMaterial4, String uQuantity4, String uMaterial5, String uQuantity5, String uMaterial6, String uQuantity6, String uMaterial7, String uQuantity7, String uMaterial8, String uQuantity8, String uMaterial9, String uQuantity9, String uMaterial10, String uQuantity10, String uQuantity11, String uMaterial12, String uQuantity12, String uMaterial13, String uQuantity13, String uMaterial14, String uQuantity14, String uMaterial15, String uQuantity15, String uMaterial16, String uQuantity16, String uMaterial17, String uQuantity17, String uMaterial18, String uQuantity18, String uMaterial19, String uQuantity19, String uMaterial20, String uQuantity20, String uUnit1, String uUnit2, String uUnit3, String uUnit4, String uUnit5, String uUnit6, String uUnit7, String uUnit8, String uUnit9, String uUnit10, String uUnit11, String uUnit12, String uUnit13, String uUnit14, String uUnit15, String uUnit16, String uUnit17, String uUnit18, String uUnit19, String uUnit20, String uMaterial11, String uMaterial21, String uQuantity21, String uMaterial22, String uQuantity22, String uMaterial23, String uQuantity23, String uMaterial24, String uQuantity24, String uMaterial25, String uQuantity25, String uMaterial26, String uQuantity26, String uMaterial27, String uQuantity27, String uMaterial28, String uQuantity28, String uMaterial29, String uQuantity29, String uMaterial30, String uQuantity30, String uUnit21, String uUnit22, String uUnit23, String uUnit24, String uUnit25, String uUnit26, String uUnit27, String uUnit28, String uUnit29, String uUnit30, String materialReceiver, String centerS, String villageS) {
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
        doc.put("SearchCenter",centerS.toLowerCase());
        doc.put("Village",villageS);
        doc.put("SearchVillage",villageS.toLowerCase());

        fStore.collection(cmp+" AddData").document(id).set(doc).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                //this will be called when data is added Successfully
                Toast.makeText(getApplicationContext(), "Data Added!!", Toast.LENGTH_SHORT).show();
                addDataLL.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
                animation.setVisibility(View.VISIBLE);
                lastLL.setVisibility(View.GONE);
                mediaPlayer.start();

                usedData(id,cmp,uDate,uTeamName,uLine,uTender,uDriverName,uVehicalName,uConsumerName,uSite,uMaterial1,uQuantity1,uMaterial2,uQuantity2,uMaterial3,uQuantity3,uMaterial4,uQuantity4,uMaterial5,uQuantity5,
                        uMaterial6,uQuantity6,uMaterial7,uQuantity7,uMaterial8,uQuantity8,uMaterial9,uQuantity9,uMaterial10,uQuantity10
                        ,uQuantity11,uMaterial12,uQuantity12,uMaterial13,uQuantity13,uMaterial14,uQuantity14,uMaterial15,uQuantity15,
                        uMaterial16,uQuantity16,uMaterial17,uQuantity17,uMaterial18,uQuantity18,uMaterial19,uQuantity19,uMaterial20,uQuantity20
                        ,uUnit1,uUnit2,uUnit3,uUnit4,uUnit5,uUnit6,uUnit7,uUnit8,uUnit9,uUnit10
                        ,uUnit11,uUnit12,uUnit13,uUnit14,uUnit15,uUnit16,uUnit17,uUnit18,uUnit19,uUnit20,uMaterial11
                        ,uMaterial21,uQuantity21,uMaterial22,uQuantity22,uMaterial23,uQuantity23,uMaterial24,uQuantity24,uMaterial25,uQuantity25,
                        uMaterial26,uQuantity26,uMaterial27,uQuantity27,uMaterial28,uQuantity28,uMaterial29,uQuantity29,uMaterial30,uQuantity30
                        ,uUnit21,uUnit22,uUnit23,uUnit24,uUnit25,uUnit26,uUnit27,uUnit28,uUnit29,uUnit30,materialReceiver,centerS,villageS);

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
    private void update(String cmp, String uDate, String uTeamName, String uLine, String uTender, String uDriverName, String uVehicalName, String uConsumerName, String uSite, String uMaterial1, String uQuantity1, String uMaterial2, String uQuantity2, String uMaterial3, String uQuantity3, String uMaterial4, String uQuantity4, String uMaterial5, String uQuantity5, String uMaterial6, String uQuantity6, String uMaterial7, String uQuantity7, String uMaterial8, String uQuantity8, String uMaterial9, String uQuantity9, String uMaterial10, String uQuantity10, String uQuantity11, String uMaterial12, String uQuantity12, String uMaterial13, String uQuantity13, String uMaterial14, String uQuantity14, String uMaterial15, String uQuantity15, String uMaterial16, String uQuantity16, String uMaterial17, String uQuantity17, String uMaterial18, String uQuantity18, String uMaterial19, String uQuantity19, String uMaterial20, String uQuantity20, String uUnit1, String uUnit2, String uUnit3, String uUnit4, String uUnit5, String uUnit6, String uUnit7, String uUnit8, String uUnit9, String uUnit10, String uUnit11, String uUnit12, String uUnit13, String uUnit14, String uUnit15, String uUnit16, String uUnit17, String uUnit18, String uUnit19, String uUnit20, String uMaterial11, String uMaterial21, String uQuantity21, String uMaterial22, String uQuantity22, String uMaterial23, String uQuantity23, String uMaterial24, String uQuantity24, String uMaterial25, String uQuantity25, String uMaterial26, String uQuantity26, String uMaterial27, String uQuantity27, String uMaterial28, String uQuantity28, String uMaterial29, String uQuantity29, String uMaterial30, String uQuantity30, String uUnit21, String uUnit22, String uUnit23, String uUnit24, String uUnit25, String uUnit26, String uUnit27, String uUnit28, String uUnit29, String uUnit30, String materialReceiver, String centerS, String villageS) {
        addDataLL.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        Map<String, Object> doc = new HashMap<>();

        doc.put("id", idS);
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
        doc.put("SearchCenter",centerS.toLowerCase());
        doc.put("Village",villageS);
        doc.put("SearchVillage",villageS.toLowerCase());
        fStore.collection(cmp+" AddData").document(idS).set(doc).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                //this will be called when data is added Successfully
                Toast.makeText(getApplicationContext(), "Update!!", Toast.LENGTH_SHORT).show();
                mediaPlayer.start();
                addDataLL.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
                animation.setVisibility(View.VISIBLE);
                lastLL.setVisibility(View.GONE);
                usedData(idS,cmp,uDate,uTeamName,uLine,uTender,uDriverName,uVehicalName,uConsumerName,uSite,uMaterial1,uQuantity1,uMaterial2,uQuantity2,uMaterial3,uQuantity3,uMaterial4,uQuantity4,uMaterial5,uQuantity5,
                        uMaterial6,uQuantity6,uMaterial7,uQuantity7,uMaterial8,uQuantity8,uMaterial9,uQuantity9,uMaterial10,uQuantity10
                        ,uQuantity11,uMaterial12,uQuantity12,uMaterial13,uQuantity13,uMaterial14,uQuantity14,uMaterial15,uQuantity15,
                        uMaterial16,uQuantity16,uMaterial17,uQuantity17,uMaterial18,uQuantity18,uMaterial19,uQuantity19,uMaterial20,uQuantity20
                        ,uUnit1,uUnit2,uUnit3,uUnit4,uUnit5,uUnit6,uUnit7,uUnit8,uUnit9,uUnit10
                        ,uUnit11,uUnit12,uUnit13,uUnit14,uUnit15,uUnit16,uUnit17,uUnit18,uUnit19,uUnit20,uMaterial11
                        ,uMaterial21,uQuantity21,uMaterial22,uQuantity22,uMaterial23,uQuantity23,uMaterial24,uQuantity24,uMaterial25,uQuantity25,
                        uMaterial26,uQuantity26,uMaterial27,uQuantity27,uMaterial28,uQuantity28,uMaterial29,uQuantity29,uMaterial30,uQuantity30
                        ,uUnit21,uUnit22,uUnit23,uUnit24,uUnit25,uUnit26,uUnit27,uUnit28,uUnit29,uUnit30,materialReceiver,centerS,villageS);

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
    private void usedData(String id,String cmp, String uDate, String uTeamName, String uLine, String uTender, String uDriverName, String uVehicalName, String uConsumerName, String uSite, String uMaterial1, String uQuantity1, String uMaterial2, String uQuantity2, String uMaterial3, String uQuantity3, String uMaterial4, String uQuantity4, String uMaterial5, String uQuantity5, String uMaterial6, String uQuantity6, String uMaterial7, String uQuantity7, String uMaterial8, String uQuantity8, String uMaterial9, String uQuantity9, String uMaterial10, String uQuantity10, String uQuantity11, String uMaterial12, String uQuantity12, String uMaterial13, String uQuantity13, String uMaterial14, String uQuantity14, String uMaterial15, String uQuantity15, String uMaterial16, String uQuantity16, String uMaterial17, String uQuantity17, String uMaterial18, String uQuantity18, String uMaterial19, String uQuantity19, String uMaterial20, String uQuantity20, String uUnit1, String uUnit2, String uUnit3, String uUnit4, String uUnit5, String uUnit6, String uUnit7, String uUnit8, String uUnit9, String uUnit10, String uUnit11, String uUnit12, String uUnit13, String uUnit14, String uUnit15, String uUnit16, String uUnit17, String uUnit18, String uUnit19, String uUnit20, String uMaterial11, String uMaterial21, String uQuantity21, String uMaterial22, String uQuantity22, String uMaterial23, String uQuantity23, String uMaterial24, String uQuantity24, String uMaterial25, String uQuantity25, String uMaterial26, String uQuantity26, String uMaterial27, String uQuantity27, String uMaterial28, String uQuantity28, String uMaterial29, String uQuantity29, String uMaterial30, String uQuantity30, String uUnit21, String uUnit22, String uUnit23, String uUnit24, String uUnit25, String uUnit26, String uUnit27, String uUnit28, String uUnit29, String uUnit30, String materialReceiver, String centerS, String villageS) {

        Map<String, Object> doc = new HashMap<>();

        for (int i=0;i<modelListAddData.size();i++){
            if (modelListAddData.get(i).getConsumerName().equalsIgnoreCase(uConsumerName)){
                id = modelListAddData.get(i).getId();
                if (!modelListAddData.get(i).getTeamName().equalsIgnoreCase(uTeamName)){
                    uTeamName = uTeamName + "," + modelListAddData.get(i).getTeamName();
                }
                if (!modelListAddData.get(i).getDate().equalsIgnoreCase(uDate)){
                    uDate = uDate + "," + modelListAddData.get(i).getDate();
                }
            }
        }


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
        doc.put("SearchCenter",centerS.toLowerCase());
        doc.put("Village",villageS);
        doc.put("SearchVillage",villageS.toLowerCase());

        fStore.collection(cmp+" RemainingMaterial").document(id).set(doc).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                //this will be called when data is added Successfully
                Toast.makeText(getApplicationContext(), "Added!!", Toast.LENGTH_SHORT).show();
                addDataLL.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
                animation.setVisibility(View.VISIBLE);
                lastLL.setVisibility(View.GONE);
                mediaPlayer.start();
                stock(cmp,uMaterial1,uQuantity1,uMaterial2,uQuantity2,uMaterial3,uQuantity3,uMaterial4,uQuantity4,uMaterial5,uQuantity5,
                        uMaterial6,uQuantity6,uMaterial7,uQuantity7,uMaterial8,uQuantity8,uMaterial9,uQuantity9,uMaterial10,uQuantity10
                        ,uQuantity11,uMaterial12,uQuantity12,uMaterial13,uQuantity13,uMaterial14,uQuantity14,uMaterial15,uQuantity15,
                        uMaterial16,uQuantity16,uMaterial17,uQuantity17,uMaterial18,uQuantity18,uMaterial19,uQuantity19,uMaterial20,uQuantity20
                        ,uUnit1,uUnit2,uUnit3,uUnit4,uUnit5,uUnit6,uUnit7,uUnit8,uUnit9,uUnit10
                        ,uUnit11,uUnit12,uUnit13,uUnit14,uUnit15,uUnit16,uUnit17,uUnit18,uUnit19,uUnit20,uMaterial11
                        ,uMaterial21,uQuantity21,uMaterial22,uQuantity22,uMaterial23,uQuantity23,uMaterial24,uQuantity24,uMaterial25,uQuantity25,
                        uMaterial26,uQuantity26,uMaterial27,uQuantity27,uMaterial28,uQuantity28,uMaterial29,uQuantity29,uMaterial30,uQuantity30
                        ,uUnit21,uUnit22,uUnit23,uUnit24,uUnit25,uUnit26,uUnit27,uUnit28,uUnit29,uUnit30);

                addTotalMaterialTaken(cmp,uConsumerName,uMaterial1,uQuantity1,uMaterial2,uQuantity2,uMaterial3,uQuantity3,uMaterial4,uQuantity4,uMaterial5,uQuantity5,
                        uMaterial6,uQuantity6,uMaterial7,uQuantity7,uMaterial8,uQuantity8,uMaterial9,uQuantity9,uMaterial10,uQuantity10
                        ,uQuantity11,uMaterial12,uQuantity12,uMaterial13,uQuantity13,uMaterial14,uQuantity14,uMaterial15,uQuantity15,
                        uMaterial16,uQuantity16,uMaterial17,uQuantity17,uMaterial18,uQuantity18,uMaterial19,uQuantity19,uMaterial20,uQuantity20
                        ,uUnit1,uUnit2,uUnit3,uUnit4,uUnit5,uUnit6,uUnit7,uUnit8,uUnit9,uUnit10
                        ,uUnit11,uUnit12,uUnit13,uUnit14,uUnit15,uUnit16,uUnit17,uUnit18,uUnit19,uUnit20,uMaterial11
                        ,uMaterial21,uQuantity21,uMaterial22,uQuantity22,uMaterial23,uQuantity23,uMaterial24,uQuantity24,uMaterial25,uQuantity25,
                        uMaterial26,uQuantity26,uMaterial27,uQuantity27,uMaterial28,uQuantity28,uMaterial29,uQuantity29,uMaterial30,uQuantity30
                        ,uUnit21,uUnit22,uUnit23,uUnit24,uUnit25,uUnit26,uUnit27,uUnit28,uUnit29,uUnit30);
                
                
                
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

    private void addTotalMaterialTaken(String cmp,String consumerName, String uMaterial1, String uQuantity1, String uMaterial2, String uQuantity2, String uMaterial3, String uQuantity3, String uMaterial4, String uQuantity4, String uMaterial5, String uQuantity5, String uMaterial6, String uQuantity6, String uMaterial7, String uQuantity7, String uMaterial8, String uQuantity8, String uMaterial9, String uQuantity9, String uMaterial10, String uQuantity10, String uQuantity11, String uMaterial12, String uQuantity12, String uMaterial13, String uQuantity13, String uMaterial14, String uQuantity14, String uMaterial15, String uQuantity15, String uMaterial16, String uQuantity16, String uMaterial17, String uQuantity17, String uMaterial18, String uQuantity18, String uMaterial19, String uQuantity19, String uMaterial20, String uQuantity20, String uUnit1, String uUnit2, String uUnit3, String uUnit4, String uUnit5, String uUnit6, String uUnit7, String uUnit8, String uUnit9, String uUnit10, String uUnit11, String uUnit12, String uUnit13, String uUnit14, String uUnit15, String uUnit16, String uUnit17, String uUnit18, String uUnit19, String uUnit20, String uMaterial11, String uMaterial21, String uQuantity21, String uMaterial22, String uQuantity22, String uMaterial23, String uQuantity23, String uMaterial24, String uQuantity24, String uMaterial25, String uQuantity25, String uMaterial26, String uQuantity26, String uMaterial27, String uQuantity27, String uMaterial28, String uQuantity28, String uMaterial29, String uQuantity29, String uMaterial30, String uQuantity30, String uUnit21, String uUnit22, String uUnit23, String uUnit24, String uUnit25, String uUnit26, String uUnit27, String uUnit28, String uUnit29, String uUnit30) {


        if (modelListTMT.size()!=0){
            for (int i=0;i<modelListTMT.size();i++){

                if (!uMaterial1.equals("")){
                    if (modelListTMT.get(i).getMaterial().equalsIgnoreCase(uMaterial1)){
                        float q = Float.parseFloat(modelListTMT.get(i).getQuantity()) + Float.parseFloat(uQuantity1);
                        addTotalMaterialTakenSpecific(uMaterial1,cmp,consumerName,String.valueOf(q),uUnit1);
                        a1=1;
                    }
                }

                if (!uMaterial2.equals("")){
                    if (modelListTMT.get(i).getMaterial().equalsIgnoreCase(uMaterial2)){
                        float q = Float.parseFloat(modelListTMT.get(i).getQuantity()) + Float.parseFloat(uQuantity2);
                        addTotalMaterialTakenSpecific(uMaterial2,cmp,consumerName,String.valueOf(q),uUnit2);
                        a2=1;
                    }
                }

                if (!uMaterial3.equals("")){
                    if (modelListTMT.get(i).getMaterial().equalsIgnoreCase(uMaterial3)){
                        float q = Float.parseFloat(modelListTMT.get(i).getQuantity()) + Float.parseFloat(uQuantity3);
                        addTotalMaterialTakenSpecific(uMaterial3,cmp,consumerName,String.valueOf(q),uUnit3);
                        a3=1;
                    }

                }

                if (!uMaterial4.equals("")){
                    if (modelListTMT.get(i).getMaterial().equalsIgnoreCase(uMaterial4)){
                        float q = Float.parseFloat(modelListTMT.get(i).getQuantity()) + Float.parseFloat(uQuantity4);
                        addTotalMaterialTakenSpecific(uMaterial4,cmp,consumerName,String.valueOf(q),uUnit4);
                        a4=1;
                    }

                }

                if (!uMaterial5.equals("")){
                    if (modelListTMT.get(i).getMaterial().equalsIgnoreCase(uMaterial5)){
                        float q = Float.parseFloat(modelListTMT.get(i).getQuantity()) + Float.parseFloat(uQuantity5);
                        addTotalMaterialTakenSpecific(uMaterial5,cmp,consumerName,String.valueOf(q),uUnit5);
                        a5=1;
                    }

                }

                if (!uMaterial6.equals("")){
                    if (modelListTMT.get(i).getMaterial().equalsIgnoreCase(uMaterial6)){
                        float q = Float.parseFloat(modelListTMT.get(i).getQuantity()) + Float.parseFloat(uQuantity6);
                        addTotalMaterialTakenSpecific(uMaterial6,cmp,consumerName,String.valueOf(q),uUnit6);
                        a6=1;
                    }

                }

                if (!uMaterial7.equals("")){
                    if (modelListTMT.get(i).getMaterial().equalsIgnoreCase(uMaterial7)){
                        float q = Float.parseFloat(modelListTMT.get(i).getQuantity()) + Float.parseFloat(uQuantity7);
                        addTotalMaterialTakenSpecific(uMaterial7,cmp,consumerName,String.valueOf(q),uUnit7);
                        a7=1;
                    }

                }

                if (!uMaterial8.equals("")){
                    if (modelListTMT.get(i).getMaterial().equalsIgnoreCase(uMaterial8)){
                        float q = Float.parseFloat(modelListTMT.get(i).getQuantity()) + Float.parseFloat(uQuantity8);
                        addTotalMaterialTakenSpecific(uMaterial8,cmp,consumerName,String.valueOf(q),uUnit8);
                        a8=1;
                    }

                }

                if (!uMaterial9.equals("")){
                    if (modelListTMT.get(i).getMaterial().equalsIgnoreCase(uMaterial9)){
                        float q = Float.parseFloat(modelListTMT.get(i).getQuantity()) + Float.parseFloat(uQuantity9);
                        addTotalMaterialTakenSpecific(uMaterial9,cmp,consumerName,String.valueOf(q),uUnit9);
                        a9=1;
                    }

                }

                if (!uMaterial10.equals("")){
                    if (modelListTMT.get(i).getMaterial().equalsIgnoreCase(uMaterial10)){
                        float q = Float.parseFloat(modelListTMT.get(i).getQuantity()) + Float.parseFloat(uQuantity10);
                        addTotalMaterialTakenSpecific(uMaterial10,cmp,consumerName,String.valueOf(q),uUnit10);
                        a10=1;
                    }

                }

                if (!uMaterial11.equals("")){
                    if (modelListTMT.get(i).getMaterial().equalsIgnoreCase(uMaterial11)){
                        float q = Float.parseFloat(modelListTMT.get(i).getQuantity()) + Float.parseFloat(uQuantity11);
                        addTotalMaterialTakenSpecific(uMaterial11,cmp,consumerName,String.valueOf(q),uUnit11);
                        a11=1;
                    }

                }

                if (!uMaterial12.equals("")){
                    if (modelListTMT.get(i).getMaterial().equalsIgnoreCase(uMaterial12)){
                        float q = Float.parseFloat(modelListTMT.get(i).getQuantity()) + Float.parseFloat(uQuantity12);
                        addTotalMaterialTakenSpecific(uMaterial12,cmp,consumerName,String.valueOf(q),uUnit12);
                        a12=1;
                    }

                }

                if (!uMaterial13.equals("")){
                    if (modelListTMT.get(i).getMaterial().equalsIgnoreCase(uMaterial13)){
                        float q = Float.parseFloat(modelListTMT.get(i).getQuantity()) + Float.parseFloat(uQuantity13);
                        addTotalMaterialTakenSpecific(uMaterial13,cmp,consumerName,String.valueOf(q),uUnit13);
                        a13=1;
                    }

                }

                if (!uMaterial14.equals("")){
                    if (modelListTMT.get(i).getMaterial().equalsIgnoreCase(uMaterial14)){
                        float q = Float.parseFloat(modelListTMT.get(i).getQuantity()) + Float.parseFloat(uQuantity14);
                        addTotalMaterialTakenSpecific(uMaterial14,cmp,consumerName,String.valueOf(q),uUnit14);
                        a14=1;
                    }

                }

                if (!uMaterial15.equals("")){
                    if (modelListTMT.get(i).getMaterial().equalsIgnoreCase(uMaterial15)){
                        float q = Float.parseFloat(modelListTMT.get(i).getQuantity()) + Float.parseFloat(uQuantity15);
                        addTotalMaterialTakenSpecific(uMaterial15,cmp,consumerName,String.valueOf(q),uUnit15);
                        a15=1;
                    }

                }

                if (!uMaterial16.equals("")){
                    if (modelListTMT.get(i).getMaterial().equalsIgnoreCase(uMaterial16)){
                        float q = Float.parseFloat(modelListTMT.get(i).getQuantity()) + Float.parseFloat(uQuantity16);
                        addTotalMaterialTakenSpecific(uMaterial16,cmp,consumerName,String.valueOf(q),uUnit16);
                        a16=1;
                    }

                }

                if (!uMaterial17.equals("")){
                    if (modelListTMT.get(i).getMaterial().equalsIgnoreCase(uMaterial17)){
                        float q = Float.parseFloat(modelListTMT.get(i).getQuantity()) + Float.parseFloat(uQuantity17);
                        addTotalMaterialTakenSpecific(uMaterial17,cmp,consumerName,String.valueOf(q),uUnit17);
                        a17=1;
                    }

                }

                if (!uMaterial18.equals("")){
                    if (modelListTMT.get(i).getMaterial().equalsIgnoreCase(uMaterial18)){
                        float q = Float.parseFloat(modelListTMT.get(i).getQuantity()) + Float.parseFloat(uQuantity18);
                        addTotalMaterialTakenSpecific(uMaterial18,cmp,consumerName,String.valueOf(q),uUnit18);
                        a18=1;
                    }

                }

                if (!uMaterial19.equals("")){
                    if (modelListTMT.get(i).getMaterial().equalsIgnoreCase(uMaterial19)){
                        float q = Float.parseFloat(modelListTMT.get(i).getQuantity()) + Float.parseFloat(uQuantity19);
                        addTotalMaterialTakenSpecific(uMaterial19,cmp,consumerName,String.valueOf(q),uUnit19);
                        a19=1;
                    }

                }

                if (!uMaterial20.equals("")){
                    if (modelListTMT.get(i).getMaterial().equalsIgnoreCase(uMaterial20)){
                        float q = Float.parseFloat(modelListTMT.get(i).getQuantity()) + Float.parseFloat(uQuantity20);
                        addTotalMaterialTakenSpecific(uMaterial20,cmp,consumerName,String.valueOf(q),uUnit20);
                        a20=1;
                    }

                }

                if (!uMaterial21.equals("")){
                    if (modelListTMT.get(i).getMaterial().equalsIgnoreCase(uMaterial21)){
                        float q = Float.parseFloat(modelListTMT.get(i).getQuantity()) + Float.parseFloat(uQuantity21);
                        addTotalMaterialTakenSpecific(uMaterial21,cmp,consumerName,String.valueOf(q),uUnit21);
                        a21=1;
                    }

                }

                if (!uMaterial22.equals("")){
                    if (modelListTMT.get(i).getMaterial().equalsIgnoreCase(uMaterial22)){
                        float q = Float.parseFloat(modelListTMT.get(i).getQuantity()) + Float.parseFloat(uQuantity22);
                        addTotalMaterialTakenSpecific(uMaterial22,cmp,consumerName,String.valueOf(q),uUnit22);
                        a22=1;
                    }

                }

                if (!uMaterial23.equals("")){
                    if (modelListTMT.get(i).getMaterial().equalsIgnoreCase(uMaterial23)){
                        float q = Float.parseFloat(modelListTMT.get(i).getQuantity()) + Float.parseFloat(uQuantity23);
                        addTotalMaterialTakenSpecific(uMaterial23,cmp,consumerName,String.valueOf(q),uUnit23);
                        a23=1;
                    }

                }

                if (!uMaterial24.equals("")){
                    if (modelListTMT.get(i).getMaterial().equalsIgnoreCase(uMaterial24)){
                        float q = Float.parseFloat(modelListTMT.get(i).getQuantity()) + Float.parseFloat(uQuantity24);
                        addTotalMaterialTakenSpecific(uMaterial24,cmp,consumerName,String.valueOf(q),uUnit24);
                        a24=1;
                    }

                }

                if (!uMaterial25.equals("")){
                    if (modelListTMT.get(i).getMaterial().equalsIgnoreCase(uMaterial25)){
                        float q = Float.parseFloat(modelListTMT.get(i).getQuantity()) + Float.parseFloat(uQuantity25);
                        addTotalMaterialTakenSpecific(uMaterial25,cmp,consumerName,String.valueOf(q),uUnit25);
                        a25=1;
                    }

                }

                if (!uMaterial26.equals("")){
                    if (modelListTMT.get(i).getMaterial().equalsIgnoreCase(uMaterial26)){
                        float q = Float.parseFloat(modelListTMT.get(i).getQuantity()) + Float.parseFloat(uQuantity26);
                        addTotalMaterialTakenSpecific(uMaterial26,cmp,consumerName,String.valueOf(q),uUnit26);
                        a26=1;
                    }

                }

                if (!uMaterial27.equals("")){
                    if (modelListTMT.get(i).getMaterial().equalsIgnoreCase(uMaterial27)){
                        float q = Float.parseFloat(modelListTMT.get(i).getQuantity()) + Float.parseFloat(uQuantity27);
                        addTotalMaterialTakenSpecific(uMaterial27,cmp,consumerName,String.valueOf(q),uUnit27);
                        a27=1;
                    }

                }

                if (!uMaterial28.equals("")){
                    if (modelListTMT.get(i).getMaterial().equalsIgnoreCase(uMaterial28)){
                        float q = Float.parseFloat(modelListTMT.get(i).getQuantity()) + Float.parseFloat(uQuantity28);
                        addTotalMaterialTakenSpecific(uMaterial28,cmp,consumerName,String.valueOf(q),uUnit28);
                        a28=1;
                    }

                }

                if (!uMaterial29.equals("")){
                    if (modelListTMT.get(i).getMaterial().equalsIgnoreCase(uMaterial29)){
                        float q = Float.parseFloat(modelListTMT.get(i).getQuantity()) + Float.parseFloat(uQuantity29);
                        addTotalMaterialTakenSpecific(uMaterial29,cmp,consumerName,String.valueOf(q),uUnit29);
                        a29=1;
                    }

                }

                if (!uMaterial30.equals("")){
                    if (modelListTMT.get(i).getMaterial().equalsIgnoreCase(uMaterial30)){
                        float q = Float.parseFloat(modelListTMT.get(i).getQuantity()) + Float.parseFloat(uQuantity30);
                        addTotalMaterialTakenSpecific(uMaterial30,cmp,consumerName,String.valueOf(q),uUnit30);
                        a30=1;
                    }

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
        else{

            if (!uMaterial1.equals("")){
                addTotalMaterialTakenSpecific(uMaterial1,cmp,consumerName,uQuantity1,uUnit1);
            }
            if (!uMaterial2.equals("")){
                addTotalMaterialTakenSpecific(uMaterial2,cmp,consumerName,uQuantity2,uUnit2);
            }
            if (!uMaterial3.equals("")){
                addTotalMaterialTakenSpecific(uMaterial3,cmp,consumerName,uQuantity3,uUnit3);
            }
            if (!uMaterial4.equals("")){
                addTotalMaterialTakenSpecific(uMaterial4,cmp,consumerName,uQuantity4,uUnit4);
            }
            if (!uMaterial5.equals("")){
                addTotalMaterialTakenSpecific(uMaterial5,cmp,consumerName,uQuantity5,uUnit5);
            }
            if (!uMaterial6.equals("")){
                addTotalMaterialTakenSpecific(uMaterial6,cmp,consumerName,uQuantity6,uUnit6);
            }
            if (!uMaterial7.equals("")){
                addTotalMaterialTakenSpecific(uMaterial7,cmp,consumerName,uQuantity7,uUnit7);
            }
            if (!uMaterial8.equals("")){
                addTotalMaterialTakenSpecific(uMaterial8,cmp,consumerName,uQuantity8,uUnit8);
            }
            if (!uMaterial9.equals("")){
                addTotalMaterialTakenSpecific(uMaterial9,cmp,consumerName,uQuantity9,uUnit9);
            }
            if (!uMaterial10.equals("")){
                addTotalMaterialTakenSpecific(uMaterial10,cmp,consumerName,uQuantity10,uUnit10);
            }
            if (!uMaterial11.equals("")){
                addTotalMaterialTakenSpecific(uMaterial11,cmp,consumerName,uQuantity11,uUnit11);
            }
            if (!uMaterial12.equals("")){
                addTotalMaterialTakenSpecific(uMaterial12,cmp,consumerName,uQuantity12,uUnit12);
            }
            if (!uMaterial13.equals("")){
                addTotalMaterialTakenSpecific(uMaterial13,cmp,consumerName,uQuantity13,uUnit13);
            }
            if (!uMaterial14.equals("")){
                addTotalMaterialTakenSpecific(uMaterial14,cmp,consumerName,uQuantity14,uUnit14);
            }
            if (!uMaterial15.equals("")){
                addTotalMaterialTakenSpecific(uMaterial15,cmp,consumerName,uQuantity15,uUnit15);
            }
            if (!uMaterial16.equals("")){
                addTotalMaterialTakenSpecific(uMaterial16,cmp,consumerName,uQuantity16,uUnit16);
            }
            if (!uMaterial17.equals("")){
                addTotalMaterialTakenSpecific(uMaterial17,cmp,consumerName,uQuantity17,uUnit17);
            }
            if (!uMaterial18.equals("")){
                addTotalMaterialTakenSpecific(uMaterial18,cmp,consumerName,uQuantity18,uUnit18);
            }
            if (!uMaterial19.equals("")){
                addTotalMaterialTakenSpecific(uMaterial19,cmp,consumerName,uQuantity19,uUnit19);
            }
            if (!uMaterial20.equals("")){
                addTotalMaterialTakenSpecific(uMaterial20,cmp,consumerName,uQuantity20,uUnit20);
            }
            if (!uMaterial21.equals("")){
                addTotalMaterialTakenSpecific(uMaterial21,cmp,consumerName,uQuantity21,uUnit21);
            }
            if (!uMaterial22.equals("")){
                addTotalMaterialTakenSpecific(uMaterial22,cmp,consumerName,uQuantity22,uUnit22);
            }
            if (!uMaterial23.equals("")){
                addTotalMaterialTakenSpecific(uMaterial23,cmp,consumerName,uQuantity23,uUnit23);
            }
            if (!uMaterial24.equals("")){
                addTotalMaterialTakenSpecific(uMaterial24,cmp,consumerName,uQuantity24,uUnit24);
            }
            if (!uMaterial25.equals("")){
                addTotalMaterialTakenSpecific(uMaterial25,cmp,consumerName,uQuantity25,uUnit25);
            }
            if (!uMaterial26.equals("")){
                addTotalMaterialTakenSpecific(uMaterial26,cmp,consumerName,uQuantity26,uUnit26);
            }
            if (!uMaterial27.equals("")){
                addTotalMaterialTakenSpecific(uMaterial27,cmp,consumerName,uQuantity27,uUnit27);
            }
            if (!uMaterial28.equals("")){
                addTotalMaterialTakenSpecific(uMaterial28,cmp,consumerName,uQuantity28,uUnit28);
            }
            if (!uMaterial29.equals("")){
                addTotalMaterialTakenSpecific(uMaterial29,cmp,consumerName,uQuantity29,uUnit29);
            }
            if (!uMaterial30.equals("")){
                addTotalMaterialTakenSpecific(uMaterial30,cmp,consumerName,uQuantity30,uUnit30);
            }

        }

    }

    private void addTotalMaterialTakenSpecific(String material, String cmp, String consumerName, String quantity,String unit) {


        Map<String, Object> doc = new HashMap<>();
        doc.put("Id",material);
        doc.put("Material",material);
        doc.put("Unit",unit);
        doc.put("Quantity",quantity);

        fStore.collection(cmp+" "+consumerName).document(material).set(doc).addOnCompleteListener(new OnCompleteListener<Void>() {
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

    private void stock(String cmp, String uMaterial1, String uQuantity1, String uMaterial2, String uQuantity2, String uMaterial3, String uQuantity3, String uMaterial4, String uQuantity4, String uMaterial5, String uQuantity5, String uMaterial6, String uQuantity6, String uMaterial7, String uQuantity7, String uMaterial8, String uQuantity8, String uMaterial9, String uQuantity9, String uMaterial10, String uQuantity10, String uQuantity11, String uMaterial12, String uQuantity12, String uMaterial13, String uQuantity13, String uMaterial14, String uQuantity14, String uMaterial15, String uQuantity15, String uMaterial16, String uQuantity16, String uMaterial17, String uQuantity17, String uMaterial18, String uQuantity18, String uMaterial19, String uQuantity19, String uMaterial20, String uQuantity20, String uUnit1, String uUnit2, String uUnit3, String uUnit4, String uUnit5, String uUnit6, String uUnit7, String uUnit8, String uUnit9, String uUnit10, String uUnit11, String uUnit12, String uUnit13, String uUnit14, String uUnit15, String uUnit16, String uUnit17, String uUnit18, String uUnit19, String uUnit20, String uMaterial11, String uMaterial21, String uQuantity21, String uMaterial22, String uQuantity22, String uMaterial23, String uQuantity23, String uMaterial24, String uQuantity24, String uMaterial25, String uQuantity25, String uMaterial26, String uQuantity26, String uMaterial27, String uQuantity27, String uMaterial28, String uQuantity28, String uMaterial29, String uQuantity29, String uMaterial30, String uQuantity30, String uUnit21, String uUnit22, String uUnit23, String uUnit24, String uUnit25, String uUnit26, String uUnit27, String uUnit28, String uUnit29, String uUnit30) {

        Bundle bundle = getIntent().getExtras();

        if (bundle!=null){
            stock1(cmp,uMaterial1,uQuantity1,uMaterial2,uQuantity2,uMaterial3,uQuantity3,uMaterial4,uQuantity4,uMaterial5,uQuantity5,
                    uMaterial6,uQuantity6,uMaterial7,uQuantity7,uMaterial8,uQuantity8,uMaterial9,uQuantity9,uMaterial10,uQuantity10
                    ,uQuantity11,uMaterial12,uQuantity12,uMaterial13,uQuantity13,uMaterial14,uQuantity14,uMaterial15,uQuantity15,
                    uMaterial16,uQuantity16,uMaterial17,uQuantity17,uMaterial18,uQuantity18,uMaterial19,uQuantity19,uMaterial20,uQuantity20
                    ,uUnit1,uUnit2,uUnit3,uUnit4,uUnit5,uUnit6,uUnit7,uUnit8,uUnit9,uUnit10
                    ,uUnit11,uUnit12,uUnit13,uUnit14,uUnit15,uUnit16,uUnit17,uUnit18,uUnit19,uUnit20,uMaterial11
                    ,uMaterial21,uQuantity21,uMaterial22,uQuantity22,uMaterial23,uQuantity23,uMaterial24,uQuantity24,uMaterial25,uQuantity25,
                    uMaterial26,uQuantity26,uMaterial27,uQuantity27,uMaterial28,uQuantity28,uMaterial29,uQuantity29,uMaterial30,uQuantity30
                    ,uUnit21,uUnit22,uUnit23,uUnit24,uUnit25,uUnit26,uUnit27,uUnit28,uUnit29,uUnit30);

        }

        else {

            stock2(cmp,uMaterial1,uQuantity1,uMaterial2,uQuantity2,uMaterial3,uQuantity3,uMaterial4,uQuantity4,uMaterial5,uQuantity5,
                    uMaterial6,uQuantity6,uMaterial7,uQuantity7,uMaterial8,uQuantity8,uMaterial9,uQuantity9,uMaterial10,uQuantity10
                    ,uQuantity11,uMaterial12,uQuantity12,uMaterial13,uQuantity13,uMaterial14,uQuantity14,uMaterial15,uQuantity15,
                    uMaterial16,uQuantity16,uMaterial17,uQuantity17,uMaterial18,uQuantity18,uMaterial19,uQuantity19,uMaterial20,uQuantity20
                    ,uUnit1,uUnit2,uUnit3,uUnit4,uUnit5,uUnit6,uUnit7,uUnit8,uUnit9,uUnit10
                    ,uUnit11,uUnit12,uUnit13,uUnit14,uUnit15,uUnit16,uUnit17,uUnit18,uUnit19,uUnit20,uMaterial11
                    ,uMaterial21,uQuantity21,uMaterial22,uQuantity22,uMaterial23,uQuantity23,uMaterial24,uQuantity24,uMaterial25,uQuantity25,
                    uMaterial26,uQuantity26,uMaterial27,uQuantity27,uMaterial28,uQuantity28,uMaterial29,uQuantity29,uMaterial30,uQuantity30
                    ,uUnit21,uUnit22,uUnit23,uUnit24,uUnit25,uUnit26,uUnit27,uUnit28,uUnit29,uUnit30);






        }

    }
    private void stock1(String cmp, String uMaterial1, String uQuantity1, String uMaterial2, String uQuantity2, String uMaterial3, String uQuantity3, String uMaterial4, String uQuantity4, String uMaterial5, String uQuantity5, String uMaterial6, String uQuantity6, String uMaterial7, String uQuantity7, String uMaterial8, String uQuantity8, String uMaterial9, String uQuantity9, String uMaterial10, String uQuantity10, String uQuantity11, String uMaterial12, String uQuantity12, String uMaterial13, String uQuantity13, String uMaterial14, String uQuantity14, String uMaterial15, String uQuantity15, String uMaterial16, String uQuantity16, String uMaterial17, String uQuantity17, String uMaterial18, String uQuantity18, String uMaterial19, String uQuantity19, String uMaterial20, String uQuantity20, String uUnit1, String uUnit2, String uUnit3, String uUnit4, String uUnit5, String uUnit6, String uUnit7, String uUnit8, String uUnit9, String uUnit10, String uUnit11, String uUnit12, String uUnit13, String uUnit14, String uUnit15, String uUnit16, String uUnit17, String uUnit18, String uUnit19, String uUnit20, String uMaterial11, String uMaterial21, String uQuantity21, String uMaterial22, String uQuantity22, String uMaterial23, String uQuantity23, String uMaterial24, String uQuantity24, String uMaterial25, String uQuantity25, String uMaterial26, String uQuantity26, String uMaterial27, String uQuantity27, String uMaterial28, String uQuantity28, String uMaterial29, String uQuantity29, String uMaterial30, String uQuantity30, String uUnit21, String uUnit22, String uUnit23, String uUnit24, String uUnit25, String uUnit26, String uUnit27, String uUnit28, String uUnit29, String uUnit30) {


        for (int i=0;i<modelList.size();i++){

            if (modelList.get(i).getMaterial().equals(uMaterial1)){
                if (modelList.get(i).getQuantity().equals(uUnit1)){
                    float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(quantity1S) - Float.parseFloat(uQuantity1);
                    addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),a);
                    if (a<5){
                        sendMail(cmp,modelList.get(i).getMaterial());
                    }
                }
                else {
                    for (int j=0;j<modelListKN.size();j++){
                        if (modelListKN.get(j).getMaterial().equals(uMaterial1)){
                            float stock = Float.parseFloat(modelList.get(i).getUnit());
                            float oldQuantity = Float.parseFloat(quantity1S);
                            float quantity = Float.parseFloat(uQuantity1);
                            float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                            float oldDivision = (oldQuantity / conversion);
                            float division = (quantity / conversion);
                            float result = stock - division + oldDivision ;
                            addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),result);
                            if (result<5){
                                sendMail(cmp,modelList.get(i).getMaterial());

                            }
                        }

                    }
                }

            }

            if (modelList.get(i).getMaterial().equals(uMaterial2)){
                if (modelList.get(i).getQuantity().equals(uUnit2)){
                    float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(quantity2S) - Float.parseFloat(uQuantity2);
                    addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),a);
                    if (a<5){
                        sendMail(cmp,modelList.get(i).getMaterial());
                    }
                }
                else {
                    for (int j=0;j<modelListKN.size();j++){
                        if (modelListKN.get(j).getMaterial().equals(uMaterial2)){
                            float stock = Float.parseFloat(modelList.get(i).getUnit());
                            float oldQuantity = Float.parseFloat(quantity2S);
                            float quantity = Float.parseFloat(uQuantity2);
                            float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                            float oldDivision = (oldQuantity / conversion);
                            float division = (quantity / conversion);
                            float result = stock - division + oldDivision ;
                            addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),result);
                            if (result<5){
                                sendMail(cmp,modelList.get(i).getMaterial());

                            }
                        }

                    }
                }

            }

            if (modelList.get(i).getMaterial().equals(uMaterial3)){
                if (modelList.get(i).getQuantity().equals(uUnit3)){
                    float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(quantity3S) - Float.parseFloat(uQuantity3);
                    addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),a);
                    if (a<5){
                        sendMail(cmp,modelList.get(i).getMaterial());
                    }
                }
                else {
                    for (int j=0;j<modelListKN.size();j++){
                        if (modelListKN.get(j).getMaterial().equals(uMaterial3)){
                            float stock = Float.parseFloat(modelList.get(i).getUnit());
                            float oldQuantity = Float.parseFloat(quantity3S);
                            float quantity = Float.parseFloat(uQuantity3);
                            float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                            float oldDivision = (oldQuantity / conversion);
                            float division = (quantity / conversion);
                            float result = stock - division + oldDivision ;
                            addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),result);
                            if (result<5){
                                sendMail(cmp,modelList.get(i).getMaterial());

                            }
                        }

                    }
                }

            }

            if (modelList.get(i).getMaterial().equals(uMaterial4)){
                if (modelList.get(i).getQuantity().equals(uUnit4)){
                    float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(quantity4S) - Float.parseFloat(uQuantity4);
                    addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),a);
                    if (a<5){
                        sendMail(cmp,modelList.get(i).getMaterial());
                    }
                }
                else {
                    for (int j=0;j<modelListKN.size();j++){
                        if (modelListKN.get(j).getMaterial().equals(uMaterial4)){
                            float stock = Float.parseFloat(modelList.get(i).getUnit());
                            float oldQuantity = Float.parseFloat(quantity4S);
                            float quantity = Float.parseFloat(uQuantity4);
                            float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                            float oldDivision = (oldQuantity / conversion);
                            float division = (quantity / conversion);
                            float result = stock - division + oldDivision ;
                            addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),result);
                            if (result<5){
                                sendMail(cmp,modelList.get(i).getMaterial());

                            }
                        }

                    }
                }

            }


            if (modelList.get(i).getMaterial().equals(uMaterial5)){
                if (modelList.get(i).getQuantity().equals(uUnit5)){
                    float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(quantity5S) - Float.parseFloat(uQuantity5);
                    addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),a);
                    if (a<5){
                        sendMail(cmp,modelList.get(i).getMaterial());
                    }
                }
                else {
                    for (int j=0;j<modelListKN.size();j++){
                        if (modelListKN.get(j).getMaterial().equals(uMaterial5)){
                            float stock = Float.parseFloat(modelList.get(i).getUnit());
                            float oldQuantity = Float.parseFloat(quantity5S);
                            float quantity = Float.parseFloat(uQuantity5);
                            float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                            float oldDivision = (oldQuantity / conversion);
                            float division = (quantity / conversion);
                            float result = stock - division + oldDivision ;
                            addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),result);
                            if (result<5){
                                sendMail(cmp,modelList.get(i).getMaterial());

                            }
                        }

                    }
                }

            }


            if (modelList.get(i).getMaterial().equals(uMaterial6)){
                if (modelList.get(i).getQuantity().equals(uUnit6)){
                    float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(quantity6S) - Float.parseFloat(uQuantity6);
                    addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),a);
                    if (a<5){
                        sendMail(cmp,modelList.get(i).getMaterial());
                    }
                }
                else {
                    for (int j=0;j<modelListKN.size();j++){
                        if (modelListKN.get(j).getMaterial().equals(uMaterial6)){
                            float stock = Float.parseFloat(modelList.get(i).getUnit());
                            float oldQuantity = Float.parseFloat(quantity6S);
                            float quantity = Float.parseFloat(uQuantity6);
                            float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                            float oldDivision = (oldQuantity / conversion);
                            float division = (quantity / conversion);
                            float result = stock - division + oldDivision ;
                            addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),result);
                            if (result<5){
                                sendMail(cmp,modelList.get(i).getMaterial());

                            }
                        }

                    }
                }

            }

            if (modelList.get(i).getMaterial().equals(uMaterial7)){
                if (modelList.get(i).getQuantity().equals(uUnit7)){
                    float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(quantity7S) - Float.parseFloat(uQuantity7);
                    addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),a);
                    if (a<5){
                        sendMail(cmp,modelList.get(i).getMaterial());
                    }
                }
                else {
                    for (int j=0;j<modelListKN.size();j++){
                        if (modelListKN.get(j).getMaterial().equals(uMaterial7)){
                            float stock = Float.parseFloat(modelList.get(i).getUnit());
                            float oldQuantity = Float.parseFloat(quantity7S);
                            float quantity = Float.parseFloat(uQuantity7);
                            float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                            float oldDivision = (oldQuantity / conversion);
                            float division = (quantity / conversion);
                            float result = stock - division + oldDivision ;
                            addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),result);
                            if (result<5){
                                sendMail(cmp,modelList.get(i).getMaterial());

                            }
                        }

                    }
                }

            }

            if (modelList.get(i).getMaterial().equals(uMaterial8)){
                if (modelList.get(i).getQuantity().equals(uUnit8)){
                    float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(quantity8S) - Float.parseFloat(uQuantity8);
                    addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),a);
                    if (a<5){
                        sendMail(cmp,modelList.get(i).getMaterial());
                    }
                }
                else {
                    for (int j=0;j<modelListKN.size();j++){
                        if (modelListKN.get(j).getMaterial().equals(uMaterial8)){
                            float stock = Float.parseFloat(modelList.get(i).getUnit());
                            float oldQuantity = Float.parseFloat(quantity8S);
                            float quantity = Float.parseFloat(uQuantity8);
                            float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                            float oldDivision = (oldQuantity / conversion);
                            float division = (quantity / conversion);
                            float result = stock - division + oldDivision ;
                            addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),result);
                            if (result<5){
                                sendMail(cmp,modelList.get(i).getMaterial());

                            }
                        }

                    }
                }

            }

            if (modelList.get(i).getMaterial().equals(uMaterial9)){
                if (modelList.get(i).getQuantity().equals(uUnit9)){
                    float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(quantity9S) - Float.parseFloat(uQuantity9);
                    addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),a);
                    if (a<5){
                        sendMail(cmp,modelList.get(i).getMaterial());
                    }
                }
                else {
                    for (int j=0;j<modelListKN.size();j++){
                        if (modelListKN.get(j).getMaterial().equals(uMaterial9)){
                            float stock = Float.parseFloat(modelList.get(i).getUnit());
                            float oldQuantity = Float.parseFloat(quantity9S);
                            float quantity = Float.parseFloat(uQuantity9);
                            float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                            float oldDivision = (oldQuantity / conversion);
                            float division = (quantity / conversion);
                            float result = stock - division + oldDivision ;
                            addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),result);
                            if (result<5){
                                sendMail(cmp,modelList.get(i).getMaterial());

                            }
                        }

                    }
                }

            }

            if (modelList.get(i).getMaterial().equals(uMaterial10)){
                if (modelList.get(i).getQuantity().equals(uUnit10)){
                    float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(quantity10S) - Float.parseFloat(uQuantity10);
                    addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),a);
                    if (a<5){
                        sendMail(cmp,modelList.get(i).getMaterial());
                    }
                }
                else {
                    for (int j=0;j<modelListKN.size();j++){
                        if (modelListKN.get(j).getMaterial().equals(uMaterial10)){
                            float stock = Float.parseFloat(modelList.get(i).getUnit());
                            float oldQuantity = Float.parseFloat(quantity10S);
                            float quantity = Float.parseFloat(uQuantity10);
                            float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                            float oldDivision = (oldQuantity / conversion);
                            float division = (quantity / conversion);
                            float result = stock - division + oldDivision ;
                            addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),result);
                            if (result<5){
                                sendMail(cmp,modelList.get(i).getMaterial());

                            }
                        }

                    }
                }

            }

            if (modelList.get(i).getMaterial().equals(uMaterial11)){
                if (modelList.get(i).getQuantity().equals(uUnit11)){
                    float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(quantity11S) - Float.parseFloat(uQuantity11);
                    addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),a);
                    if (a<5){
                        sendMail(cmp,modelList.get(i).getMaterial());
                    }
                }
                else {
                    for (int j=0;j<modelListKN.size();j++){
                        if (modelListKN.get(j).getMaterial().equals(uMaterial11)){
                            float stock = Float.parseFloat(modelList.get(i).getUnit());
                            float oldQuantity = Float.parseFloat(quantity11S);
                            float quantity = Float.parseFloat(uQuantity11);
                            float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                            float oldDivision = (oldQuantity / conversion);
                            float division = (quantity / conversion);
                            float result = stock - division + oldDivision ;
                            addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),result);
                            if (result<5){
                                sendMail(cmp,modelList.get(i).getMaterial());

                            }
                        }

                    }
                }

            }

            if (modelList.get(i).getMaterial().equals(uMaterial12)){
                if (modelList.get(i).getQuantity().equals(uUnit12)){
                    float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(quantity12S) - Float.parseFloat(uQuantity12);
                    addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),a);
                    if (a<5){
                        sendMail(cmp,modelList.get(i).getMaterial());
                    }
                }
                else {
                    for (int j=0;j<modelListKN.size();j++){
                        if (modelListKN.get(j).getMaterial().equals(uMaterial12)){
                            float stock = Float.parseFloat(modelList.get(i).getUnit());
                            float oldQuantity = Float.parseFloat(quantity12S);
                            float quantity = Float.parseFloat(uQuantity12);
                            float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                            float oldDivision = (oldQuantity / conversion);
                            float division = (quantity / conversion);
                            float result = stock - division + oldDivision ;
                            addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),result);
                            if (result<5){
                                sendMail(cmp,modelList.get(i).getMaterial());

                            }
                        }

                    }
                }

            }

            if (modelList.get(i).getMaterial().equals(uMaterial13)){
                if (modelList.get(i).getQuantity().equals(uUnit13)){
                    float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(quantity13S) - Float.parseFloat(uQuantity13);
                    addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),a);
                    if (a<5){
                        sendMail(cmp,modelList.get(i).getMaterial());
                    }
                }
                else {
                    for (int j=0;j<modelListKN.size();j++){
                        if (modelListKN.get(j).getMaterial().equals(uMaterial13)){
                            float stock = Float.parseFloat(modelList.get(i).getUnit());
                            float oldQuantity = Float.parseFloat(quantity13S);
                            float quantity = Float.parseFloat(uQuantity13);
                            float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                            float oldDivision = (oldQuantity / conversion);
                            float division = (quantity / conversion);
                            float result = stock - division + oldDivision ;
                            addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),result);
                            if (result<5){
                                sendMail(cmp,modelList.get(i).getMaterial());

                            }
                        }

                    }
                }

            }

            if (modelList.get(i).getMaterial().equals(uMaterial14)){
                if (modelList.get(i).getQuantity().equals(uUnit14)){
                    float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(quantity14S) - Float.parseFloat(uQuantity14);
                    addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),a);
                    if (a<5){
                        sendMail(cmp,modelList.get(i).getMaterial());
                    }
                }
                else {
                    for (int j=0;j<modelListKN.size();j++){
                        if (modelListKN.get(j).getMaterial().equals(uMaterial14)){
                            float stock = Float.parseFloat(modelList.get(i).getUnit());
                            float oldQuantity = Float.parseFloat(quantity14S);
                            float quantity = Float.parseFloat(uQuantity14);
                            float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                            float oldDivision = (oldQuantity / conversion);
                            float division = (quantity / conversion);
                            float result = stock - division + oldDivision ;
                            addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),result);
                            if (result<5){
                                sendMail(cmp,modelList.get(i).getMaterial());

                            }
                        }

                    }
                }

            }

            if (modelList.get(i).getMaterial().equals(uMaterial15)){
                if (modelList.get(i).getQuantity().equals(uUnit15)){
                    float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(quantity15S) - Float.parseFloat(uQuantity15);
                    addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),a);
                    if (a<5){
                        sendMail(cmp,modelList.get(i).getMaterial());
                    }
                }
                else {
                    for (int j=0;j<modelListKN.size();j++){
                        if (modelListKN.get(j).getMaterial().equals(uMaterial15)){
                            float stock = Float.parseFloat(modelList.get(i).getUnit());
                            float oldQuantity = Float.parseFloat(quantity15S);
                            float quantity = Float.parseFloat(uQuantity15);
                            float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                            float oldDivision = (oldQuantity / conversion);
                            float division = (quantity / conversion);
                            float result = stock - division + oldDivision ;
                            addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),result);
                            if (result<5){
                                sendMail(cmp,modelList.get(i).getMaterial());

                            }
                        }

                    }
                }

            }

            if (modelList.get(i).getMaterial().equals(uMaterial16)){
                if (modelList.get(i).getQuantity().equals(uUnit16)){
                    float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(quantity16S) - Float.parseFloat(uQuantity16);
                    addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),a);
                    if (a<5){
                        sendMail(cmp,modelList.get(i).getMaterial());
                    }
                }
                else {
                    for (int j=0;j<modelListKN.size();j++){
                        if (modelListKN.get(j).getMaterial().equals(uMaterial16)){
                            float stock = Float.parseFloat(modelList.get(i).getUnit());
                            float oldQuantity = Float.parseFloat(quantity16S);
                            float quantity = Float.parseFloat(uQuantity16);
                            float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                            float oldDivision = (oldQuantity / conversion);
                            float division = (quantity / conversion);
                            float result = stock - division + oldDivision ;
                            addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),result);
                            if (result<5){
                                sendMail(cmp,modelList.get(i).getMaterial());

                            }
                        }

                    }
                }

            }

            if (modelList.get(i).getMaterial().equals(uMaterial17)){
                if (modelList.get(i).getQuantity().equals(uUnit17)){
                    float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(quantity17S) - Float.parseFloat(uQuantity17);
                    addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),a);
                    if (a<5){
                        sendMail(cmp,modelList.get(i).getMaterial());
                    }
                }
                else {
                    for (int j=0;j<modelListKN.size();j++){
                        if (modelListKN.get(j).getMaterial().equals(uMaterial17)){
                            float stock = Float.parseFloat(modelList.get(i).getUnit());
                            float oldQuantity = Float.parseFloat(quantity17S);
                            float quantity = Float.parseFloat(uQuantity17);
                            float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                            float oldDivision = (oldQuantity / conversion);
                            float division = (quantity / conversion);
                            float result = stock - division + oldDivision ;
                            addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),result);
                            if (result<5){
                                sendMail(cmp,modelList.get(i).getMaterial());

                            }
                        }

                    }
                }

            }

            if (modelList.get(i).getMaterial().equals(uMaterial18)){
                if (modelList.get(i).getQuantity().equals(uUnit18)){
                    float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(quantity18S) - Float.parseFloat(uQuantity18);
                    addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),a);
                    if (a<5){
                        sendMail(cmp,modelList.get(i).getMaterial());
                    }
                }
                else {
                    for (int j=0;j<modelListKN.size();j++){
                        if (modelListKN.get(j).getMaterial().equals(uMaterial18)){
                            float stock = Float.parseFloat(modelList.get(i).getUnit());
                            float oldQuantity = Float.parseFloat(quantity18S);
                            float quantity = Float.parseFloat(uQuantity18);
                            float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                            float oldDivision = (oldQuantity / conversion);
                            float division = (quantity / conversion);
                            float result = stock - division + oldDivision ;
                            addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),result);
                            if (result<5){
                                sendMail(cmp,modelList.get(i).getMaterial());

                            }
                        }

                    }
                }

            }

            if (modelList.get(i).getMaterial().equals(uMaterial19)){
                if (modelList.get(i).getQuantity().equals(uUnit19)){
                    float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(quantity19S) - Float.parseFloat(uQuantity19);
                    addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),a);
                    if (a<5){
                        sendMail(cmp,modelList.get(i).getMaterial());
                    }
                }
                else {
                    for (int j=0;j<modelListKN.size();j++){
                        if (modelListKN.get(j).getMaterial().equals(uMaterial19)){
                            float stock = Float.parseFloat(modelList.get(i).getUnit());
                            float oldQuantity = Float.parseFloat(quantity19S);
                            float quantity = Float.parseFloat(uQuantity19);
                            float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                            float oldDivision = (oldQuantity / conversion);
                            float division = (quantity / conversion);
                            float result = stock - division + oldDivision ;
                            addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),result);
                            if (result<5){
                                sendMail(cmp,modelList.get(i).getMaterial());

                            }
                        }

                    }
                }

            }

            if (modelList.get(i).getMaterial().equals(uMaterial20)){
                if (modelList.get(i).getQuantity().equals(uUnit20)){
                    float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(quantity20S) - Float.parseFloat(uQuantity20);
                    addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),a);
                    if (a<5){
                        sendMail(cmp,modelList.get(i).getMaterial());
                    }
                }
                else {
                    for (int j=0;j<modelListKN.size();j++){
                        if (modelListKN.get(j).getMaterial().equals(uMaterial20)){
                            float stock = Float.parseFloat(modelList.get(i).getUnit());
                            float oldQuantity = Float.parseFloat(quantity20S);
                            float quantity = Float.parseFloat(uQuantity20);
                            float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                            float oldDivision = (oldQuantity / conversion);
                            float division = (quantity / conversion);
                            float result = stock - division + oldDivision ;
                            addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),result);
                            if (result<5){
                                sendMail(cmp,modelList.get(i).getMaterial());

                            }
                        }

                    }
                }

            }

            if (modelList.get(i).getMaterial().equals(uMaterial21)){
                if (modelList.get(i).getQuantity().equals(uUnit21)){
                    float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(quantity21S) - Float.parseFloat(uQuantity21);
                    addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),a);
                    if (a<5){
                        sendMail(cmp,modelList.get(i).getMaterial());
                    }
                }
                else {
                    for (int j=0;j<modelListKN.size();j++){
                        if (modelListKN.get(j).getMaterial().equals(uMaterial21)){
                            float stock = Float.parseFloat(modelList.get(i).getUnit());
                            float oldQuantity = Float.parseFloat(quantity21S);
                            float quantity = Float.parseFloat(uQuantity21);
                            float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                            float oldDivision = (oldQuantity / conversion);
                            float division = (quantity / conversion);
                            float result = stock - division + oldDivision ;
                            addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),result);
                            if (result<5){
                                sendMail(cmp,modelList.get(i).getMaterial());

                            }
                        }

                    }
                }

            }

            if (modelList.get(i).getMaterial().equals(uMaterial22)){
                if (modelList.get(i).getQuantity().equals(uUnit22)){
                    float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(quantity22S) - Float.parseFloat(uQuantity22);
                    addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),a);
                    if (a<5){
                        sendMail(cmp,modelList.get(i).getMaterial());
                    }
                }
                else {
                    for (int j=0;j<modelListKN.size();j++){
                        if (modelListKN.get(j).getMaterial().equals(uMaterial22)){
                            float stock = Float.parseFloat(modelList.get(i).getUnit());
                            float oldQuantity = Float.parseFloat(quantity22S);
                            float quantity = Float.parseFloat(uQuantity22);
                            float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                            float oldDivision = (oldQuantity / conversion);
                            float division = (quantity / conversion);
                            float result = stock - division + oldDivision ;
                            addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),result);
                            if (result<5){
                                sendMail(cmp,modelList.get(i).getMaterial());

                            }
                        }

                    }
                }

            }

            if (modelList.get(i).getMaterial().equals(uMaterial23)){
                if (modelList.get(i).getQuantity().equals(uUnit23)){
                    float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(quantity23S) - Float.parseFloat(uQuantity23);
                    addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),a);
                    if (a<5){
                        sendMail(cmp,modelList.get(i).getMaterial());
                    }
                }
                else {
                    for (int j=0;j<modelListKN.size();j++){
                        if (modelListKN.get(j).getMaterial().equals(uMaterial23)){
                            float stock = Float.parseFloat(modelList.get(i).getUnit());
                            float oldQuantity = Float.parseFloat(quantity23S);
                            float quantity = Float.parseFloat(uQuantity23);
                            float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                            float oldDivision = (oldQuantity / conversion);
                            float division = (quantity / conversion);
                            float result = stock - division + oldDivision ;
                            addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),result);
                            if (result<5){
                                sendMail(cmp,modelList.get(i).getMaterial());

                            }
                        }

                    }
                }

            }

            if (modelList.get(i).getMaterial().equals(uMaterial24)){
                if (modelList.get(i).getQuantity().equals(uUnit24)){
                    float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(quantity24S) - Float.parseFloat(uQuantity24);
                    addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),a);
                    if (a<5){
                        sendMail(cmp,modelList.get(i).getMaterial());
                    }
                }
                else {
                    for (int j=0;j<modelListKN.size();j++){
                        if (modelListKN.get(j).getMaterial().equals(uMaterial24)){
                            float stock = Float.parseFloat(modelList.get(i).getUnit());
                            float oldQuantity = Float.parseFloat(quantity24S);
                            float quantity = Float.parseFloat(uQuantity24);
                            float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                            float oldDivision = (oldQuantity / conversion);
                            float division = (quantity / conversion);
                            float result = stock - division + oldDivision ;
                            addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),result);
                            if (result<5){
                                sendMail(cmp,modelList.get(i).getMaterial());

                            }
                        }

                    }
                }

            }

            if (modelList.get(i).getMaterial().equals(uMaterial25)){
                if (modelList.get(i).getQuantity().equals(uUnit25)){
                    float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(quantity25S) - Float.parseFloat(uQuantity25);
                    addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),a);
                    if (a<5){
                        sendMail(cmp,modelList.get(i).getMaterial());
                    }
                }
                else {
                    for (int j=0;j<modelListKN.size();j++){
                        if (modelListKN.get(j).getMaterial().equals(uMaterial25)){
                            float stock = Float.parseFloat(modelList.get(i).getUnit());
                            float oldQuantity = Float.parseFloat(quantity25S);
                            float quantity = Float.parseFloat(uQuantity25);
                            float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                            float oldDivision = (oldQuantity / conversion);
                            float division = (quantity / conversion);
                            float result = stock - division + oldDivision ;
                            addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),result);
                            if (result<5){
                                sendMail(cmp,modelList.get(i).getMaterial());

                            }
                        }

                    }
                }

            }

            if (modelList.get(i).getMaterial().equals(uMaterial26)){
                if (modelList.get(i).getQuantity().equals(uUnit26)){
                    float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(quantity26S) - Float.parseFloat(uQuantity26);
                    addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),a);
                    if (a<5){
                        sendMail(cmp,modelList.get(i).getMaterial());
                    }
                }
                else {
                    for (int j=0;j<modelListKN.size();j++){
                        if (modelListKN.get(j).getMaterial().equals(uMaterial26)){
                            float stock = Float.parseFloat(modelList.get(i).getUnit());
                            float oldQuantity = Float.parseFloat(quantity26S);
                            float quantity = Float.parseFloat(uQuantity26);
                            float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                            float oldDivision = (oldQuantity / conversion);
                            float division = (quantity / conversion);
                            float result = stock - division + oldDivision ;
                            addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),result);
                            if (result<5){
                                sendMail(cmp,modelList.get(i).getMaterial());

                            }
                        }

                    }
                }

            }

            if (modelList.get(i).getMaterial().equals(uMaterial27)){
                if (modelList.get(i).getQuantity().equals(uUnit27)){
                    float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(quantity27S) - Float.parseFloat(uQuantity27);
                    addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),a);
                    if (a<5){
                        sendMail(cmp,modelList.get(i).getMaterial());
                    }
                }
                else {
                    for (int j=0;j<modelListKN.size();j++){
                        if (modelListKN.get(j).getMaterial().equals(uMaterial27)){
                            float stock = Float.parseFloat(modelList.get(i).getUnit());
                            float oldQuantity = Float.parseFloat(quantity27S);
                            float quantity = Float.parseFloat(uQuantity27);
                            float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                            float oldDivision = (oldQuantity / conversion);
                            float division = (quantity / conversion);
                            float result = stock - division + oldDivision ;
                            addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),result);
                            if (result<5){
                                sendMail(cmp,modelList.get(i).getMaterial());

                            }
                        }

                    }
                }

            }

            if (modelList.get(i).getMaterial().equals(uMaterial28)){
                if (modelList.get(i).getQuantity().equals(uUnit28)){
                    float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(quantity28S) - Float.parseFloat(uQuantity28);
                    addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),a);
                    if (a<5){
                        sendMail(cmp,modelList.get(i).getMaterial());
                    }
                }
                else {
                    for (int j=0;j<modelListKN.size();j++){
                        if (modelListKN.get(j).getMaterial().equals(uMaterial28)){
                            float stock = Float.parseFloat(modelList.get(i).getUnit());
                            float oldQuantity = Float.parseFloat(quantity28S);
                            float quantity = Float.parseFloat(uQuantity28);
                            float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                            float oldDivision = (oldQuantity / conversion);
                            float division = (quantity / conversion);
                            float result = stock - division + oldDivision ;
                            addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),result);
                            if (result<5){
                                sendMail(cmp,modelList.get(i).getMaterial());

                            }
                        }

                    }
                }

            }

            if (modelList.get(i).getMaterial().equals(uMaterial29)){
                if (modelList.get(i).getQuantity().equals(uUnit29)){
                    float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(quantity29S) - Float.parseFloat(uQuantity29);
                    addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),a);
                    if (a<5){
                        sendMail(cmp,modelList.get(i).getMaterial());
                    }
                }
                else {
                    for (int j=0;j<modelListKN.size();j++){
                        if (modelListKN.get(j).getMaterial().equals(uMaterial29)){
                            float stock = Float.parseFloat(modelList.get(i).getUnit());
                            float oldQuantity = Float.parseFloat(quantity29S);
                            float quantity = Float.parseFloat(uQuantity29);
                            float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                            float oldDivision = (oldQuantity / conversion);
                            float division = (quantity / conversion);
                            float result = stock - division + oldDivision ;
                            addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),result);
                            if (result<5){
                                sendMail(cmp,modelList.get(i).getMaterial());

                            }
                        }

                    }
                }

            }

            if (modelList.get(i).getMaterial().equals(uMaterial30)){
                if (modelList.get(i).getQuantity().equals(uUnit30)){
                    float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(quantity30S) - Float.parseFloat(uQuantity30);
                    addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),a);
                    if (a<5){
                        sendMail(cmp,modelList.get(i).getMaterial());
                    }
                }
                else {
                    for (int j=0;j<modelListKN.size();j++){
                        if (modelListKN.get(j).getMaterial().equals(uMaterial30)){
                            float stock = Float.parseFloat(modelList.get(i).getUnit());
                            float oldQuantity = Float.parseFloat(quantity30S);
                            float quantity = Float.parseFloat(uQuantity30);
                            float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                            float oldDivision = (oldQuantity / conversion);
                            float division = (quantity / conversion);
                            float result = stock - division + oldDivision ;
                            addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),result);
                            if (result<5){
                                sendMail(cmp,modelList.get(i).getMaterial());

                            }
                        }

                    }
                }

            }



        }

    }
    private void stock2(String cmp, String uMaterial1, String uQuantity1, String uMaterial2, String uQuantity2, String uMaterial3, String uQuantity3, String uMaterial4, String uQuantity4, String uMaterial5, String uQuantity5, String uMaterial6, String uQuantity6, String uMaterial7, String uQuantity7, String uMaterial8, String uQuantity8, String uMaterial9, String uQuantity9, String uMaterial10, String uQuantity10, String uQuantity11, String uMaterial12, String uQuantity12, String uMaterial13, String uQuantity13, String uMaterial14, String uQuantity14, String uMaterial15, String uQuantity15, String uMaterial16, String uQuantity16, String uMaterial17, String uQuantity17, String uMaterial18, String uQuantity18, String uMaterial19, String uQuantity19, String uMaterial20, String uQuantity20, String uUnit1, String uUnit2, String uUnit3, String uUnit4, String uUnit5, String uUnit6, String uUnit7, String uUnit8, String uUnit9, String uUnit10, String uUnit11, String uUnit12, String uUnit13, String uUnit14, String uUnit15, String uUnit16, String uUnit17, String uUnit18, String uUnit19, String uUnit20, String uMaterial11, String uMaterial21, String uQuantity21, String uMaterial22, String uQuantity22, String uMaterial23, String uQuantity23, String uMaterial24, String uQuantity24, String uMaterial25, String uQuantity25, String uMaterial26, String uQuantity26, String uMaterial27, String uQuantity27, String uMaterial28, String uQuantity28, String uMaterial29, String uQuantity29, String uMaterial30, String uQuantity30, String uUnit21, String uUnit22, String uUnit23, String uUnit24, String uUnit25, String uUnit26, String uUnit27, String uUnit28, String uUnit29, String uUnit30) {

        for (int i=0;i<modelList.size();i++){

            if (modelList.get(i).getMaterial().equals(uMaterial1)){
                if (modelList.get(i).getQuantity().equals(uUnit1)){
                    float a = Float.parseFloat(modelList.get(i).getUnit()) - Float.parseFloat(uQuantity1);
                    addStock(cmp, modelList.get(i).getMaterial(), modelList.get(i).getQuantity(), a);
                    if (a < 5) {
                        sendMail(cmp, modelList.get(i).getMaterial());
                    }
                }
                else{
                    for (int j=0;j<modelListKN.size();j++){
                        if (modelListKN.get(j).getMaterial().equals(uMaterial1) && modelListKN.get(j).getUnit().equals(uUnit1)){
                            float stock = Float.parseFloat(modelList.get(i).getUnit());
                            float quantity = Float.parseFloat(uQuantity1);
                            float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                            float division = (quantity/conversion);
                            float result = stock - division;
                            addStock(cmp, modelList.get(i).getMaterial(), "KG", result);
                            if (result < 5) {
                                sendMail(cmp, modelList.get(i).getMaterial());
                            }
                        }
                    }
                }

            }

            if (modelList.get(i).getMaterial().equals(uMaterial2)){
                if (modelList.get(i).getQuantity().equals(uUnit2)){
                    float a = Float.parseFloat(modelList.get(i).getUnit()) - Float.parseFloat(uQuantity2);
                    addStock(cmp, modelList.get(i).getMaterial(), modelList.get(i).getQuantity(), a);
                    if (a < 5) {
                        sendMail(cmp, modelList.get(i).getMaterial());
                    }
                }
                else{
                    for (int j=0;j<modelListKN.size();j++){
                        if (modelListKN.get(j).getMaterial().equals(uMaterial2) && modelListKN.get(j).getUnit().equals(uUnit2)){
                            float stock = Float.parseFloat(modelList.get(i).getUnit());
                            float quantity = Float.parseFloat(uQuantity2);
                            float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                            float division = (quantity/conversion);
                            float result = stock - division;
                            addStock(cmp, modelList.get(i).getMaterial(), "KG", result);
                            if (result < 5) {
                                sendMail(cmp, modelList.get(i).getMaterial());
                            }
                        }
                    }
                }

            }

            if (modelList.get(i).getMaterial().equals(uMaterial3)){
                if (modelList.get(i).getQuantity().equals(uUnit3)){
                    float a = Float.parseFloat(modelList.get(i).getUnit()) - Float.parseFloat(uQuantity3);
                    addStock(cmp, modelList.get(i).getMaterial(), modelList.get(i).getQuantity(), a);
                    if (a < 5) {
                        sendMail(cmp, modelList.get(i).getMaterial());
                    }
                }
                else{
                    for (int j=0;j<modelListKN.size();j++){
                        if (modelListKN.get(j).getMaterial().equals(uMaterial3) && modelListKN.get(j).getUnit().equals(uUnit3)){
                            float stock = Float.parseFloat(modelList.get(i).getUnit());
                            float quantity = Float.parseFloat(uQuantity3);
                            float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                            float division = (quantity/conversion);
                            float result = stock - division;
                            addStock(cmp, modelList.get(i).getMaterial(), "KG", result);
                            if (result < 5) {
                                sendMail(cmp, modelList.get(i).getMaterial());
                            }
                        }
                    }
                }

            }

            if (modelList.get(i).getMaterial().equals(uMaterial4)){
                if (modelList.get(i).getQuantity().equals(uUnit4)){
                    float a = Float.parseFloat(modelList.get(i).getUnit()) - Float.parseFloat(uQuantity4);
                    addStock(cmp, modelList.get(i).getMaterial(), modelList.get(i).getQuantity(), a);
                    if (a < 5) {
                        sendMail(cmp, modelList.get(i).getMaterial());
                    }
                }
                else{
                    for (int j=0;j<modelListKN.size();j++){
                        if (modelListKN.get(j).getMaterial().equals(uMaterial4) && modelListKN.get(j).getUnit().equals(uUnit4)){
                            float stock = Float.parseFloat(modelList.get(i).getUnit());
                            float quantity = Float.parseFloat(uQuantity4);
                            float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                            float division = (quantity/conversion);
                            float result = stock - division;
                            addStock(cmp, modelList.get(i).getMaterial(), "KG", result);
                            if (result < 5) {
                                sendMail(cmp, modelList.get(i).getMaterial());
                            }
                        }
                    }
                }

            }

            if (modelList.get(i).getMaterial().equals(uMaterial5)){
                if (modelList.get(i).getQuantity().equals(uUnit5)){
                    float a = Float.parseFloat(modelList.get(i).getUnit()) - Float.parseFloat(uQuantity5);
                    addStock(cmp, modelList.get(i).getMaterial(), modelList.get(i).getQuantity(), a);
                    if (a < 5) {
                        sendMail(cmp, modelList.get(i).getMaterial());
                    }
                }
                else{
                    for (int j=0;j<modelListKN.size();j++){
                        if (modelListKN.get(j).getMaterial().equals(uMaterial5) && modelListKN.get(j).getUnit().equals(uUnit5)){
                            float stock = Float.parseFloat(modelList.get(i).getUnit());
                            float quantity = Float.parseFloat(uQuantity5);
                            float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                            float division = (quantity/conversion);
                            float result = stock - division;
                            addStock(cmp, modelList.get(i).getMaterial(), "KG", result);
                            if (result < 5) {
                                sendMail(cmp, modelList.get(i).getMaterial());
                            }
                        }
                    }
                }

            }

            if (modelList.get(i).getMaterial().equals(uMaterial6)){
                if (modelList.get(i).getQuantity().equals(uUnit6)){
                    float a = Float.parseFloat(modelList.get(i).getUnit()) - Float.parseFloat(uQuantity6);
                    addStock(cmp, modelList.get(i).getMaterial(), modelList.get(i).getQuantity(), a);
                    if (a < 5) {
                        sendMail(cmp, modelList.get(i).getMaterial());
                    }
                }
                else{
                    for (int j=0;j<modelListKN.size();j++){
                        if (modelListKN.get(j).getMaterial().equals(uMaterial6) && modelListKN.get(j).getUnit().equals(uUnit6)){
                            float stock = Float.parseFloat(modelList.get(i).getUnit());
                            float quantity = Float.parseFloat(uQuantity6);
                            float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                            float division = (quantity/conversion);
                            float result = stock - division;
                            addStock(cmp, modelList.get(i).getMaterial(), "KG", result);
                            if (result < 5) {
                                sendMail(cmp, modelList.get(i).getMaterial());
                            }
                        }
                    }
                }

            }

            if (modelList.get(i).getMaterial().equals(uMaterial7)){
                if (modelList.get(i).getQuantity().equals(uUnit7)){
                    float a = Float.parseFloat(modelList.get(i).getUnit()) - Float.parseFloat(uQuantity7);
                    addStock(cmp, modelList.get(i).getMaterial(), modelList.get(i).getQuantity(), a);
                    if (a < 5) {
                        sendMail(cmp, modelList.get(i).getMaterial());
                    }
                }
                else{
                    for (int j=0;j<modelListKN.size();j++){
                        if (modelListKN.get(j).getMaterial().equals(uMaterial7) && modelListKN.get(j).getUnit().equals(uUnit7)){
                            float stock = Float.parseFloat(modelList.get(i).getUnit());
                            float quantity = Float.parseFloat(uQuantity7);
                            float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                            float division = (quantity/conversion);
                            float result = stock - division;
                            addStock(cmp, modelList.get(i).getMaterial(), "KG", result);
                            if (result < 5) {
                                sendMail(cmp, modelList.get(i).getMaterial());
                            }
                        }
                    }
                }

            }

            if (modelList.get(i).getMaterial().equals(uMaterial8)){
                if (modelList.get(i).getQuantity().equals(uUnit8)){
                    float a = Float.parseFloat(modelList.get(i).getUnit()) - Float.parseFloat(uQuantity8);
                    addStock(cmp, modelList.get(i).getMaterial(), modelList.get(i).getQuantity(), a);
                    if (a < 5) {
                        sendMail(cmp, modelList.get(i).getMaterial());
                    }
                }
                else{
                    for (int j=0;j<modelListKN.size();j++){
                        if (modelListKN.get(j).getMaterial().equals(uMaterial8) && modelListKN.get(j).getUnit().equals(uUnit8)){
                            float stock = Float.parseFloat(modelList.get(i).getUnit());
                            float quantity = Float.parseFloat(uQuantity8);
                            float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                            float division = (quantity/conversion);
                            float result = stock - division;
                            addStock(cmp, modelList.get(i).getMaterial(), "KG", result);
                            if (result < 5) {
                                sendMail(cmp, modelList.get(i).getMaterial());
                            }
                        }
                    }
                }

            }

            if (modelList.get(i).getMaterial().equals(uMaterial9)){
                if (modelList.get(i).getQuantity().equals(uUnit9)){
                    float a = Float.parseFloat(modelList.get(i).getUnit()) - Float.parseFloat(uQuantity9);
                    addStock(cmp, modelList.get(i).getMaterial(), modelList.get(i).getQuantity(), a);
                    if (a < 5) {
                        sendMail(cmp, modelList.get(i).getMaterial());
                    }
                }
                else{
                    for (int j=0;j<modelListKN.size();j++){
                        if (modelListKN.get(j).getMaterial().equals(uMaterial9) && modelListKN.get(j).getUnit().equals(uUnit9)){
                            float stock = Float.parseFloat(modelList.get(i).getUnit());
                            float quantity = Float.parseFloat(uQuantity9);
                            float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                            float division = (quantity/conversion);
                            float result = stock - division;
                            addStock(cmp, modelList.get(i).getMaterial(), "KG", result);
                            if (result < 5) {
                                sendMail(cmp, modelList.get(i).getMaterial());
                            }
                        }
                    }
                }

            }

            if (modelList.get(i).getMaterial().equals(uMaterial10)){
                if (modelList.get(i).getQuantity().equals(uUnit10)){
                    float a = Float.parseFloat(modelList.get(i).getUnit()) - Float.parseFloat(uQuantity10);
                    addStock(cmp, modelList.get(i).getMaterial(), modelList.get(i).getQuantity(), a);
                    if (a < 5) {
                        sendMail(cmp, modelList.get(i).getMaterial());
                    }
                }
                else{
                    for (int j=0;j<modelListKN.size();j++){
                        if (modelListKN.get(j).getMaterial().equals(uMaterial10) && modelListKN.get(j).getUnit().equals(uUnit10)){
                            float stock = Float.parseFloat(modelList.get(i).getUnit());
                            float quantity = Float.parseFloat(uQuantity10);
                            float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                            float division = (quantity/conversion);
                            float result = stock - division;
                            addStock(cmp, modelList.get(i).getMaterial(), "KG", result);
                            if (result < 5) {
                                sendMail(cmp, modelList.get(i).getMaterial());
                            }
                        }
                    }
                }

            }

            if (modelList.get(i).getMaterial().equals(uMaterial11)){
                if (modelList.get(i).getQuantity().equals(uUnit11)){
                    float a = Float.parseFloat(modelList.get(i).getUnit()) - Float.parseFloat(uQuantity11);
                    addStock(cmp, modelList.get(i).getMaterial(), modelList.get(i).getQuantity(), a);
                    if (a < 5) {
                        sendMail(cmp, modelList.get(i).getMaterial());
                    }
                }
                else{
                    for (int j=0;j<modelListKN.size();j++){
                        if (modelListKN.get(j).getMaterial().equals(uMaterial11) && modelListKN.get(j).getUnit().equals(uUnit11)){
                            float stock = Float.parseFloat(modelList.get(i).getUnit());
                            float quantity = Float.parseFloat(uQuantity11);
                            float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                            float division = (quantity/conversion);
                            float result = stock - division;
                            addStock(cmp, modelList.get(i).getMaterial(), "KG", result);
                            if (result < 5) {
                                sendMail(cmp, modelList.get(i).getMaterial());
                            }
                        }
                    }
                }

            }

            if (modelList.get(i).getMaterial().equals(uMaterial12)){
                if (modelList.get(i).getQuantity().equals(uUnit12)){
                    float a = Float.parseFloat(modelList.get(i).getUnit()) - Float.parseFloat(uQuantity12);
                    addStock(cmp, modelList.get(i).getMaterial(), modelList.get(i).getQuantity(), a);
                    if (a < 5) {
                        sendMail(cmp, modelList.get(i).getMaterial());
                    }
                }
                else{
                    for (int j=0;j<modelListKN.size();j++){
                        if (modelListKN.get(j).getMaterial().equals(uMaterial12) && modelListKN.get(j).getUnit().equals(uUnit12)){
                            float stock = Float.parseFloat(modelList.get(i).getUnit());
                            float quantity = Float.parseFloat(uQuantity12);
                            float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                            float division = (quantity/conversion);
                            float result = stock - division;
                            addStock(cmp, modelList.get(i).getMaterial(), "KG", result);
                            if (result < 5) {
                                sendMail(cmp, modelList.get(i).getMaterial());
                            }
                        }
                    }
                }

            }

            if (modelList.get(i).getMaterial().equals(uMaterial13)){
                if (modelList.get(i).getQuantity().equals(uUnit13)){
                    float a = Float.parseFloat(modelList.get(i).getUnit()) - Float.parseFloat(uQuantity13);
                    addStock(cmp, modelList.get(i).getMaterial(), modelList.get(i).getQuantity(), a);
                    if (a < 5) {
                        sendMail(cmp, modelList.get(i).getMaterial());
                    }
                }
                else{
                    for (int j=0;j<modelListKN.size();j++){
                        if (modelListKN.get(j).getMaterial().equals(uMaterial13) && modelListKN.get(j).getUnit().equals(uUnit13)){
                            float stock = Float.parseFloat(modelList.get(i).getUnit());
                            float quantity = Float.parseFloat(uQuantity13);
                            float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                            float division = (quantity/conversion);
                            float result = stock - division;
                            addStock(cmp, modelList.get(i).getMaterial(), "KG", result);
                            if (result < 5) {
                                sendMail(cmp, modelList.get(i).getMaterial());
                            }
                        }
                    }
                }

            }

            if (modelList.get(i).getMaterial().equals(uMaterial14)){
                if (modelList.get(i).getQuantity().equals(uUnit14)){
                    float a = Float.parseFloat(modelList.get(i).getUnit()) - Float.parseFloat(uQuantity14);
                    addStock(cmp, modelList.get(i).getMaterial(), modelList.get(i).getQuantity(), a);
                    if (a < 5) {
                        sendMail(cmp, modelList.get(i).getMaterial());
                    }
                }
                else{
                    for (int j=0;j<modelListKN.size();j++){
                        if (modelListKN.get(j).getMaterial().equals(uMaterial14) && modelListKN.get(j).getUnit().equals(uUnit14)){
                            float stock = Float.parseFloat(modelList.get(i).getUnit());
                            float quantity = Float.parseFloat(uQuantity14);
                            float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                            float division = (quantity/conversion);
                            float result = stock - division;
                            addStock(cmp, modelList.get(i).getMaterial(), "KG", result);
                            if (result < 5) {
                                sendMail(cmp, modelList.get(i).getMaterial());
                            }
                        }
                    }
                }

            }

            if (modelList.get(i).getMaterial().equals(uMaterial15)){
                if (modelList.get(i).getQuantity().equals(uUnit15)){
                    float a = Float.parseFloat(modelList.get(i).getUnit()) - Float.parseFloat(uQuantity15);
                    addStock(cmp, modelList.get(i).getMaterial(), modelList.get(i).getQuantity(), a);
                    if (a < 5) {
                        sendMail(cmp, modelList.get(i).getMaterial());
                    }
                }
                else{
                    for (int j=0;j<modelListKN.size();j++){
                        if (modelListKN.get(j).getMaterial().equals(uMaterial15) && modelListKN.get(j).getUnit().equals(uUnit15)){
                            float stock = Float.parseFloat(modelList.get(i).getUnit());
                            float quantity = Float.parseFloat(uQuantity15);
                            float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                            float division = (quantity/conversion);
                            float result = stock - division;
                            addStock(cmp, modelList.get(i).getMaterial(), "KG", result);
                            if (result < 5) {
                                sendMail(cmp, modelList.get(i).getMaterial());
                            }
                        }
                    }
                }

            }

            if (modelList.get(i).getMaterial().equals(uMaterial16)){
                if (modelList.get(i).getQuantity().equals(uUnit16)){
                    float a = Float.parseFloat(modelList.get(i).getUnit()) - Float.parseFloat(uQuantity16);
                    addStock(cmp, modelList.get(i).getMaterial(), modelList.get(i).getQuantity(), a);
                    if (a < 5) {
                        sendMail(cmp, modelList.get(i).getMaterial());
                    }
                }
                else{
                    for (int j=0;j<modelListKN.size();j++){
                        if (modelListKN.get(j).getMaterial().equals(uMaterial16) && modelListKN.get(j).getUnit().equals(uUnit16)){
                            float stock = Float.parseFloat(modelList.get(i).getUnit());
                            float quantity = Float.parseFloat(uQuantity16);
                            float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                            float division = (quantity/conversion);
                            float result = stock - division;
                            addStock(cmp, modelList.get(i).getMaterial(), "KG", result);
                            if (result < 5) {
                                sendMail(cmp, modelList.get(i).getMaterial());
                            }
                        }
                    }
                }

            }

            if (modelList.get(i).getMaterial().equals(uMaterial17)){
                if (modelList.get(i).getQuantity().equals(uUnit17)){
                    float a = Float.parseFloat(modelList.get(i).getUnit()) - Float.parseFloat(uQuantity17);
                    addStock(cmp, modelList.get(i).getMaterial(), modelList.get(i).getQuantity(), a);
                    if (a < 5) {
                        sendMail(cmp, modelList.get(i).getMaterial());
                    }
                }
                else{
                    for (int j=0;j<modelListKN.size();j++){
                        if (modelListKN.get(j).getMaterial().equals(uMaterial17) && modelListKN.get(j).getUnit().equals(uUnit17)){
                            float stock = Float.parseFloat(modelList.get(i).getUnit());
                            float quantity = Float.parseFloat(uQuantity17);
                            float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                            float division = (quantity/conversion);
                            float result = stock - division;
                            addStock(cmp, modelList.get(i).getMaterial(), "KG", result);
                            if (result < 5) {
                                sendMail(cmp, modelList.get(i).getMaterial());
                            }
                        }
                    }
                }

            }

            if (modelList.get(i).getMaterial().equals(uMaterial18)){
                if (modelList.get(i).getQuantity().equals(uUnit18)){
                    float a = Float.parseFloat(modelList.get(i).getUnit()) - Float.parseFloat(uQuantity18);
                    addStock(cmp, modelList.get(i).getMaterial(), modelList.get(i).getQuantity(), a);
                    if (a < 5) {
                        sendMail(cmp, modelList.get(i).getMaterial());
                    }
                }
                else{
                    for (int j=0;j<modelListKN.size();j++){
                        if (modelListKN.get(j).getMaterial().equals(uMaterial18) && modelListKN.get(j).getUnit().equals(uUnit18)){
                            float stock = Float.parseFloat(modelList.get(i).getUnit());
                            float quantity = Float.parseFloat(uQuantity18);
                            float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                            float division = (quantity/conversion);
                            float result = stock - division;
                            addStock(cmp, modelList.get(i).getMaterial(), "KG", result);
                            if (result < 5) {
                                sendMail(cmp, modelList.get(i).getMaterial());
                            }
                        }
                    }
                }

            }

            if (modelList.get(i).getMaterial().equals(uMaterial19)){
                if (modelList.get(i).getQuantity().equals(uUnit19)){
                    float a = Float.parseFloat(modelList.get(i).getUnit()) - Float.parseFloat(uQuantity19);
                    addStock(cmp, modelList.get(i).getMaterial(), modelList.get(i).getQuantity(), a);
                    if (a < 5) {
                        sendMail(cmp, modelList.get(i).getMaterial());
                    }
                }
                else{
                    for (int j=0;j<modelListKN.size();j++){
                        if (modelListKN.get(j).getMaterial().equals(uMaterial19) && modelListKN.get(j).getUnit().equals(uUnit19)){
                            float stock = Float.parseFloat(modelList.get(i).getUnit());
                            float quantity = Float.parseFloat(uQuantity19);
                            float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                            float division = (quantity/conversion);
                            float result = stock - division;
                            addStock(cmp, modelList.get(i).getMaterial(), "KG", result);
                            if (result < 5) {
                                sendMail(cmp, modelList.get(i).getMaterial());
                            }
                        }
                    }
                }

            }

            if (modelList.get(i).getMaterial().equals(uMaterial20)){
                if (modelList.get(i).getQuantity().equals(uUnit20)){
                    float a = Float.parseFloat(modelList.get(i).getUnit()) - Float.parseFloat(uQuantity20);
                    addStock(cmp, modelList.get(i).getMaterial(), modelList.get(i).getQuantity(), a);
                    if (a < 5) {
                        sendMail(cmp, modelList.get(i).getMaterial());
                    }
                }
                else{
                    for (int j=0;j<modelListKN.size();j++){
                        if (modelListKN.get(j).getMaterial().equals(uMaterial20) && modelListKN.get(j).getUnit().equals(uUnit20)){
                            float stock = Float.parseFloat(modelList.get(i).getUnit());
                            float quantity = Float.parseFloat(uQuantity20);
                            float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                            float division = (quantity/conversion);
                            float result = stock - division;
                            addStock(cmp, modelList.get(i).getMaterial(), "KG", result);
                            if (result < 5) {
                                sendMail(cmp, modelList.get(i).getMaterial());
                            }
                        }
                    }
                }

            }

            if (modelList.get(i).getMaterial().equals(uMaterial21)){
                if (modelList.get(i).getQuantity().equals(uUnit21)){
                    float a = Float.parseFloat(modelList.get(i).getUnit()) - Float.parseFloat(uQuantity21);
                    addStock(cmp, modelList.get(i).getMaterial(), modelList.get(i).getQuantity(), a);
                    if (a < 5) {
                        sendMail(cmp, modelList.get(i).getMaterial());
                    }
                }
                else{
                    for (int j=0;j<modelListKN.size();j++){
                        if (modelListKN.get(j).getMaterial().equals(uMaterial21) && modelListKN.get(j).getUnit().equals(uUnit21)){
                            float stock = Float.parseFloat(modelList.get(i).getUnit());
                            float quantity = Float.parseFloat(uQuantity21);
                            float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                            float division = (quantity/conversion);
                            float result = stock - division;
                            addStock(cmp, modelList.get(i).getMaterial(), "KG", result);
                            if (result < 5) {
                                sendMail(cmp, modelList.get(i).getMaterial());
                            }
                        }
                    }
                }

            }

            if (modelList.get(i).getMaterial().equals(uMaterial22)){
                if (modelList.get(i).getQuantity().equals(uUnit22)){
                    float a = Float.parseFloat(modelList.get(i).getUnit()) - Float.parseFloat(uQuantity22);
                    addStock(cmp, modelList.get(i).getMaterial(), modelList.get(i).getQuantity(), a);
                    if (a < 5) {
                        sendMail(cmp, modelList.get(i).getMaterial());
                    }
                }
                else{
                    for (int j=0;j<modelListKN.size();j++){
                        if (modelListKN.get(j).getMaterial().equals(uMaterial22) && modelListKN.get(j).getUnit().equals(uUnit22)){
                            float stock = Float.parseFloat(modelList.get(i).getUnit());
                            float quantity = Float.parseFloat(uQuantity22);
                            float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                            float division = (quantity/conversion);
                            float result = stock - division;
                            addStock(cmp, modelList.get(i).getMaterial(), "KG", result);
                            if (result < 5) {
                                sendMail(cmp, modelList.get(i).getMaterial());
                            }
                        }
                    }
                }

            }

            if (modelList.get(i).getMaterial().equals(uMaterial23)){
                if (modelList.get(i).getQuantity().equals(uUnit23)){
                    float a = Float.parseFloat(modelList.get(i).getUnit()) - Float.parseFloat(uQuantity23);
                    addStock(cmp, modelList.get(i).getMaterial(), modelList.get(i).getQuantity(), a);
                    if (a < 5) {
                        sendMail(cmp, modelList.get(i).getMaterial());
                    }
                }
                else{
                    for (int j=0;j<modelListKN.size();j++){
                        if (modelListKN.get(j).getMaterial().equals(uMaterial23) && modelListKN.get(j).getUnit().equals(uUnit23)){
                            float stock = Float.parseFloat(modelList.get(i).getUnit());
                            float quantity = Float.parseFloat(uQuantity23);
                            float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                            float division = (quantity/conversion);
                            float result = stock - division;
                            addStock(cmp, modelList.get(i).getMaterial(), "KG", result);
                            if (result < 5) {
                                sendMail(cmp, modelList.get(i).getMaterial());
                            }
                        }
                    }
                }

            }

            if (modelList.get(i).getMaterial().equals(uMaterial24)){
                if (modelList.get(i).getQuantity().equals(uUnit24)){
                    float a = Float.parseFloat(modelList.get(i).getUnit()) - Float.parseFloat(uQuantity24);
                    addStock(cmp, modelList.get(i).getMaterial(), modelList.get(i).getQuantity(), a);
                    if (a < 5) {
                        sendMail(cmp, modelList.get(i).getMaterial());
                    }
                }
                else{
                    for (int j=0;j<modelListKN.size();j++){
                        if (modelListKN.get(j).getMaterial().equals(uMaterial24) && modelListKN.get(j).getUnit().equals(uUnit24)){
                            float stock = Float.parseFloat(modelList.get(i).getUnit());
                            float quantity = Float.parseFloat(uQuantity24);
                            float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                            float division = (quantity/conversion);
                            float result = stock - division;
                            addStock(cmp, modelList.get(i).getMaterial(), "KG", result);
                            if (result < 5) {
                                sendMail(cmp, modelList.get(i).getMaterial());
                            }
                        }
                    }
                }

            }

            if (modelList.get(i).getMaterial().equals(uMaterial25)){
                if (modelList.get(i).getQuantity().equals(uUnit25)){
                    float a = Float.parseFloat(modelList.get(i).getUnit()) - Float.parseFloat(uQuantity25);
                    addStock(cmp, modelList.get(i).getMaterial(), modelList.get(i).getQuantity(), a);
                    if (a < 5) {
                        sendMail(cmp, modelList.get(i).getMaterial());
                    }
                }
                else{
                    for (int j=0;j<modelListKN.size();j++){
                        if (modelListKN.get(j).getMaterial().equals(uMaterial25) && modelListKN.get(j).getUnit().equals(uUnit25)){
                            float stock = Float.parseFloat(modelList.get(i).getUnit());
                            float quantity = Float.parseFloat(uQuantity25);
                            float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                            float division = (quantity/conversion);
                            float result = stock - division;
                            addStock(cmp, modelList.get(i).getMaterial(), "KG", result);
                            if (result < 5) {
                                sendMail(cmp, modelList.get(i).getMaterial());
                            }
                        }
                    }
                }

            }

            if (modelList.get(i).getMaterial().equals(uMaterial26)){
                if (modelList.get(i).getQuantity().equals(uUnit26)){
                    float a = Float.parseFloat(modelList.get(i).getUnit()) - Float.parseFloat(uQuantity26);
                    addStock(cmp, modelList.get(i).getMaterial(), modelList.get(i).getQuantity(), a);
                    if (a < 5) {
                        sendMail(cmp, modelList.get(i).getMaterial());
                    }
                }
                else{
                    for (int j=0;j<modelListKN.size();j++){
                        if (modelListKN.get(j).getMaterial().equals(uMaterial26) && modelListKN.get(j).getUnit().equals(uUnit26)){
                            float stock = Float.parseFloat(modelList.get(i).getUnit());
                            float quantity = Float.parseFloat(uQuantity26);
                            float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                            float division = (quantity/conversion);
                            float result = stock - division;
                            addStock(cmp, modelList.get(i).getMaterial(), "KG", result);
                            if (result < 5) {
                                sendMail(cmp, modelList.get(i).getMaterial());
                            }
                        }
                    }
                }

            }

            if (modelList.get(i).getMaterial().equals(uMaterial27)){
                if (modelList.get(i).getQuantity().equals(uUnit27)){
                    float a = Float.parseFloat(modelList.get(i).getUnit()) - Float.parseFloat(uQuantity27);
                    addStock(cmp, modelList.get(i).getMaterial(), modelList.get(i).getQuantity(), a);
                    if (a < 5) {
                        sendMail(cmp, modelList.get(i).getMaterial());
                    }
                }
                else{
                    for (int j=0;j<modelListKN.size();j++){
                        if (modelListKN.get(j).getMaterial().equals(uMaterial27) && modelListKN.get(j).getUnit().equals(uUnit27)){
                            float stock = Float.parseFloat(modelList.get(i).getUnit());
                            float quantity = Float.parseFloat(uQuantity27);
                            float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                            float division = (quantity/conversion);
                            float result = stock - division;
                            addStock(cmp, modelList.get(i).getMaterial(), "KG", result);
                            if (result < 5) {
                                sendMail(cmp, modelList.get(i).getMaterial());
                            }
                        }
                    }
                }

            }

            if (modelList.get(i).getMaterial().equals(uMaterial28)){
                if (modelList.get(i).getQuantity().equals(uUnit28)){
                    float a = Float.parseFloat(modelList.get(i).getUnit()) - Float.parseFloat(uQuantity28);
                    addStock(cmp, modelList.get(i).getMaterial(), modelList.get(i).getQuantity(), a);
                    if (a < 5) {
                        sendMail(cmp, modelList.get(i).getMaterial());
                    }
                }
                else{
                    for (int j=0;j<modelListKN.size();j++){
                        if (modelListKN.get(j).getMaterial().equals(uMaterial28) && modelListKN.get(j).getUnit().equals(uUnit28)){
                            float stock = Float.parseFloat(modelList.get(i).getUnit());
                            float quantity = Float.parseFloat(uQuantity28);
                            float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                            float division = (quantity/conversion);
                            float result = stock - division;
                            addStock(cmp, modelList.get(i).getMaterial(), "KG", result);
                            if (result < 5) {
                                sendMail(cmp, modelList.get(i).getMaterial());
                            }
                        }
                    }
                }

            }

            if (modelList.get(i).getMaterial().equals(uMaterial29)){
                if (modelList.get(i).getQuantity().equals(uUnit29)){
                    float a = Float.parseFloat(modelList.get(i).getUnit()) - Float.parseFloat(uQuantity29);
                    addStock(cmp, modelList.get(i).getMaterial(), modelList.get(i).getQuantity(), a);
                    if (a < 5) {
                        sendMail(cmp, modelList.get(i).getMaterial());
                    }
                }
                else{
                    for (int j=0;j<modelListKN.size();j++){
                        if (modelListKN.get(j).getMaterial().equals(uMaterial29) && modelListKN.get(j).getUnit().equals(uUnit29)){
                            float stock = Float.parseFloat(modelList.get(i).getUnit());
                            float quantity = Float.parseFloat(uQuantity29);
                            float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                            float division = (quantity/conversion);
                            float result = stock - division;
                            addStock(cmp, modelList.get(i).getMaterial(), "KG", result);
                            if (result < 5) {
                                sendMail(cmp, modelList.get(i).getMaterial());
                            }
                        }
                    }
                }

            }

            if (modelList.get(i).getMaterial().equals(uMaterial30)){
                if (modelList.get(i).getQuantity().equals(uUnit30)){
                    float a = Float.parseFloat(modelList.get(i).getUnit()) - Float.parseFloat(uQuantity30);
                    addStock(cmp, modelList.get(i).getMaterial(), modelList.get(i).getQuantity(), a);
                    if (a < 5) {
                        sendMail(cmp, modelList.get(i).getMaterial());
                    }
                }
                else{
                    for (int j=0;j<modelListKN.size();j++){
                        if (modelListKN.get(j).getMaterial().equals(uMaterial30) && modelListKN.get(j).getUnit().equals(uUnit30)){
                            float stock = Float.parseFloat(modelList.get(i).getUnit());
                            float quantity = Float.parseFloat(uQuantity30);
                            float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                            float division = (quantity/conversion);
                            float result = stock - division;
                            addStock(cmp, modelList.get(i).getMaterial(), "KG", result);
                            if (result < 5) {
                                sendMail(cmp, modelList.get(i).getMaterial());
                            }
                        }
                    }
                }

            }

        }

    }
    private void sendMail(String cmp, String material) {
        String message = "You have less material quantity of " + material;
        JavaMailAPI javaMailAPI = new JavaMailAPI(this, cmp, "You have less material quantity of "+material, message);
        javaMailAPI.execute();

    }
    private void addStock(String cmp, String uMaterial1, String uUnit1, float a) {

        String quantity = String.valueOf(a);

        Map<String, Object> doc = new HashMap<>();
        doc.put("Id",uMaterial1);
        doc.put("Material",uMaterial1);
        doc.put("Unit",uUnit1);
        doc.put("Quantity",quantity);

        fStore.collection(cmp+" AddStock").document(uMaterial1).set(doc).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                //this will be called when data is added Successfully
                Toast.makeText(getApplicationContext(), "Add Stock "+a, Toast.LENGTH_SHORT).show();
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
    private void showMessage(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
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