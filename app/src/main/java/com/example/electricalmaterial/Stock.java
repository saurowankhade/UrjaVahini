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
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.UUID;

public class Stock extends AppCompatActivity {

    String r = "Required!!";

    //authorization
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String companyEmail;
    String userId;


    LinearLayout animation;

    ProgressDialog pd;
    Timer time;

    String cmp;

    MediaPlayer mediaPlayer;



    LinearLayout details,materialList,receiverDetails;

    //Unit
    AutoCompleteTextView unit1,unit2,unit3,unit4,unit5,unit6,unit7,unit8,unit9,unit10;
    AutoCompleteTextView unit11,unit12,unit13,unit14,unit15,unit16,unit17,unit18,unit19,unit20;
    AutoCompleteTextView unit21,unit22,unit23,unit24,unit25,unit26,unit27,unit28,unit29,unit30;
    ArrayAdapter<String> adapterUnit;
    ArrayList<String> spinnerDataListUnit;
    DatabaseReference databaseReferenceUnit;
    ValueEventListener listenerUnit;

    //Material
    AutoCompleteTextView material1,material2,material3,material4,material5,material6,material7,material8,material9,material10;
    AutoCompleteTextView material11,material12,material13,material14,material15,material16,material17,material18,material19,material20;
    AutoCompleteTextView material21,material22,material23,material24,material25,material26,material27,material28,material29,material30;
    ArrayAdapter<String> adapterMaterial;
    ArrayList<String> spinnerDataListMaterial;
    DatabaseReference databaseReferenceMaterial;
    ValueEventListener listenerMaterial;

    //Quantity
    TextInputEditText quantity1,quantity2,quantity3,quantity4,quantity5,quantity6,quantity7,quantity8,quantity9,quantity10;
    TextInputEditText quantity11,quantity12,quantity13,quantity14,quantity15,quantity16,quantity17,quantity18,quantity19,quantity20;
    TextInputEditText quantity21,quantity22,quantity23,quantity24,quantity25,quantity26,quantity27,quantity28,quantity29,quantity30;


    //date
    TextView mDateFormate;
    DatePickerDialog.OnDateSetListener onDateSetListener;
    String date;

    //Receiver
    AutoCompleteTextView receiver;
    ArrayAdapter<String> adapterReceiver;
    ArrayList<String> spinnerDataListReceiver;
    DatabaseReference databaseReferenceReceiver;
    ValueEventListener listenerReceiver;


    //Material Add btn greater than 5
    LinearLayout addMaterialLL1,addMaterialLL2,addMaterialLL3,addMaterialLL4,addMaterialLL5,addMaterialLL6;
    ImageView addIV1,addIV2,addIV3,addIV4,addIV5;


    //Details
    TextInputEditText supplierName  , supplierAddress , store , vehicalDetails , driverName,pass;

    //Material Next Btn
    MaterialButton detailsNext,nextToReceiver,add,doneAll;

    //Material Back Btn
    MaterialButton backToDetails,backToMaterialList;


    //Stock Material
    List<AddStockModel> modelList = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    StockAdapter adapterStock;



    int p1=0,q1=0;
    int p2=0,q2=0;
    int p3=0,q3=0;
    int p4=0,q4=0;
    int p5=0,q5=0;
    int p6=0,q6=0;
    int p7=0,q7=0;
    int p8=0,q8=0;
    int p9=0,q9=0;
    int p10=0,q10=0;
    int p11=0,q11=0;
    int p12=0,q12=0;
    int p13=0,q13=0;
    int p14=0,q14=0;
    int p15=0,q15=0;
    int p16=0,q16=0;
    int p17=0,q17=0;
    int p18=0,q18=0;
    int p19=0,q19=0;
    int p20=0,q20=0;
    int p21=0,q21=0;
    int p22=0,q22=0;
    int p23=0,q23=0;
    int p24=0,q24=0;
    int p25=0,q25=0;
    int p26=0,q26=0;
    int p27=0,q27=0;
    int p28=0,q28=0;
    int p29=0,q29=0;
    int p30=0,q30=0;



    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        pd = ProgressDialog.show(this,"Loading...","Please Wait",false,false);

        pd.dismiss();

        ActionBar bar = getSupportActionBar();
        assert bar != null;
        bar.setTitle("ADD STOCK");

        //Auth
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = mAuth.getCurrentUser().getUid();

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

        mediaPlayer = MediaPlayer.create(this,R.raw.sound);
        doneAll = findViewById(R.id.doneAll);

        //Details
        supplierName = findViewById(R.id.supplierName);
        supplierAddress = findViewById(R.id.supplierAddress);
        driverName = findViewById(R.id.driverName);
        store = findViewById(R.id.store);
        vehicalDetails = findViewById(R.id.vehicalDetails);
        pass = findViewById(R.id.pass);

        //Material Next Btn
        detailsNext = findViewById(R.id.detailsNext);
        nextToReceiver = findViewById(R.id.nextToReceiver);
        add = findViewById(R.id.add);


        //Material Back Btn
        backToDetails = findViewById(R.id.backToDetails);
        backToMaterialList = findViewById(R.id.backToMaterialList);


        //LL
        details = findViewById(R.id.details);
        materialList = findViewById(R.id.materialList);
        receiverDetails = findViewById(R.id.receiverDetails);

        DocumentReference documentReference = fStore.collection("Users")
                .document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                companyEmail = value.getString("companyEmail");
                cmp = companyEmail;
                companyEmail = companyEmail.replace("@", "");
                companyEmail = companyEmail.replace(".", "");

                String profile = value.getString("profile");

//                Stock Material
                recyclerView = findViewById(R.id.recyclerAM);
                recyclerView.setHasFixedSize(true);
                layoutManager = new LinearLayoutManager(Stock.this);
                recyclerView.setLayoutManager(layoutManager);
                modelList.clear();
                showData(cmp);

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
                                Stock.this, R.style.DatePicker1,
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
                        month = month + 1;
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

                //Material
                databaseReferenceMaterial = FirebaseDatabase.getInstance().getReference(companyEmail+" Material");
                spinnerDataListMaterial = new ArrayList<>();
                adapterMaterial = new ArrayAdapter<String>(Stock.this,R.layout.support_simple_spinner_dropdown_item,spinnerDataListMaterial);
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
                adapterUnit = new ArrayAdapter<String>(Stock.this,R.layout.support_simple_spinner_dropdown_item,spinnerDataListUnit);
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
                adapterReceiver = new ArrayAdapter<String>(Stock.this,R.layout.support_simple_spinner_dropdown_item,spinnerDataListReceiver);
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

                //Next Btn
                detailsNext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String date = mDateFormate.getText().toString().trim();
                        String supplier = supplierName.getText().toString().trim();
                        String supplierAdd = supplierAddress.getText().toString().trim();
                        String driver = driverName.getText().toString().trim();
                        String storeName = store.getText().toString().trim();
                        String vehical = vehicalDetails.getText().toString().trim();
                        if (date.isEmpty()){
                            mDateFormate.setError(r);
                            mDateFormate.requestFocus();
                        }
                        else if (supplier.isEmpty()){
                            supplierName.setError(r);
                            supplierName.requestFocus();
                        }
                        else if (supplierAdd.isEmpty()){
                            supplierAddress.setError(r);
                            supplierAddress.requestFocus();
                        }
                        else if (driver.isEmpty()){
                            driverName.setError(r);
                            driverName.requestFocus();
                        }
                        else if (storeName.isEmpty()){
                            store.setError(r);
                            store.requestFocus();
                        }
                        else if (vehical.isEmpty()){
                            vehicalDetails.setError(r);
                            vehicalDetails.requestFocus();
                        }
                        else {
                            details.setVisibility(View.GONE);
                            materialList.setVisibility(View.VISIBLE);
                        }

                    }
                });
                nextToReceiver.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String m1 = material1.getText().toString().trim();
                        String u1 = unit1.getText().toString().trim();
                        String q1 = quantity1.getText().toString().trim();
                        if (m1.isEmpty()){
                            material1.setError(r);
                            material1.requestFocus();
                        }
                        else if (u1.isEmpty()){
                            unit1.setError(r);
                            unit1.requestFocus();
                        }
                        else if (q1.isEmpty()){
                            quantity1.setError(r);
                            quantity1.requestFocus();
                        }
                        else {
                            materialList.setVisibility(View.GONE);
                            receiverDetails.setVisibility(View.VISIBLE);
                        }
                    }
                });

                //Back Btn
                backToDetails.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        details.setVisibility(View.VISIBLE);
                        materialList.setVisibility(View.GONE);
                    }
                });
                backToMaterialList.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        materialList.setVisibility(View.VISIBLE);
                        receiverDetails.setVisibility(View.GONE);
                    }
                });

                //Add Data to firebase
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Details
                        String date = mDateFormate.getText().toString().trim();
                        String supplier = supplierName.getText().toString().trim();
                        String supplierAdd = supplierAddress.getText().toString().trim();
                        String driver = driverName.getText().toString().trim();
                        String storeName = store.getText().toString().trim();
                        String vehical = vehicalDetails.getText().toString().trim();

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
                        if (pass.getText().toString().trim().isEmpty()){
                            pass.setError(r);
                            pass.requestFocus();
                        }
                       else{
                           verifyPass(date,supplier,supplierAdd,driver,storeName,vehical,uMaterial1,uQuantity1,uMaterial2,uQuantity2,uMaterial3,uQuantity3,uMaterial4,uQuantity4,uMaterial5,uQuantity5,
                                    uMaterial6,uQuantity6,uMaterial7,uQuantity7,uMaterial8,uQuantity8,uMaterial9,uQuantity9,uMaterial10,uQuantity10
                                    ,uQuantity11,uMaterial12,uQuantity12,uMaterial13,uQuantity13,uMaterial14,uQuantity14,uMaterial15,uQuantity15,
                                    uMaterial16,uQuantity16,uMaterial17,uQuantity17,uMaterial18,uQuantity18,uMaterial19,uQuantity19,uMaterial20,uQuantity20
                                    ,uUnit1,uUnit2,uUnit3,uUnit4,uUnit5,uUnit6,uUnit7,uUnit8,uUnit9,uUnit10
                                    ,uUnit11,uUnit12,uUnit13,uUnit14,uUnit15,uUnit16,uUnit17,uUnit18,uUnit19,uUnit20,uMaterial11
                                    ,uMaterial21,uQuantity21,uMaterial22,uQuantity22,uMaterial23,uQuantity23,uMaterial24,uQuantity24,uMaterial25,uQuantity25,
                                    uMaterial26,uQuantity26,uMaterial27,uQuantity27,uMaterial28,uQuantity28,uMaterial29,uQuantity29,uMaterial30,uQuantity30
                                    ,uUnit21,uUnit22,uUnit23,uUnit24,uUnit25,uUnit26,uUnit27,uUnit28,uUnit29,uUnit30,materialReceiver);
                        }
                    }
                });

                doneAll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (profile.equals("ADMIN")){
                            startActivity(new Intent(getApplicationContext(),Admin.class));
                        }
                        else {
                            startActivity(new Intent(getApplicationContext(),Supervisor.class));

                        }
                        finish();
                    }
                });

            }});
    }

    private void verifyPass(String date, String supplier, String supplierAdd, String driver, String storeName, String vehical, String uMaterial1, String uQuantity1, String uMaterial2, String uQuantity2, String uMaterial3, String uQuantity3, String uMaterial4, String uQuantity4, String uMaterial5, String uQuantity5, String uMaterial6, String uQuantity6, String uMaterial7, String uQuantity7, String uMaterial8, String uQuantity8, String uMaterial9, String uQuantity9, String uMaterial10, String uQuantity10, String uQuantity11, String uMaterial12, String uQuantity12, String uMaterial13, String uQuantity13, String uMaterial14, String uQuantity14, String uMaterial15, String uQuantity15, String uMaterial16, String uQuantity16, String uMaterial17, String uQuantity17, String uMaterial18, String uQuantity18, String uMaterial19, String uQuantity19, String uMaterial20, String uQuantity20, String uUnit1, String uUnit2, String uUnit3, String uUnit4, String uUnit5, String uUnit6, String uUnit7, String uUnit8, String uUnit9, String uUnit10, String uUnit11, String uUnit12, String uUnit13, String uUnit14, String uUnit15, String uUnit16, String uUnit17, String uUnit18, String uUnit19, String uUnit20, String uMaterial11, String uMaterial21, String uQuantity21, String uMaterial22, String uQuantity22, String uMaterial23, String uQuantity23, String uMaterial24, String uQuantity24, String uMaterial25, String uQuantity25, String uMaterial26, String uQuantity26, String uMaterial27, String uQuantity27, String uMaterial28, String uQuantity28, String uMaterial29, String uQuantity29, String uMaterial30, String uQuantity30, String uUnit21, String uUnit22, String uUnit23, String uUnit24, String uUnit25, String uUnit26, String uUnit27, String uUnit28, String uUnit29, String uUnit30, String materialReceiver) {

        DocumentReference a = fStore.collection(cmp+" ProfilePass")
                .document(materialReceiver.toLowerCase());
        a.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                String passReceiverS = value.getString("Password");

                if ((pass.getText().toString().trim()).equals(passReceiverS)) {
                    Toast.makeText(getApplicationContext(), "Password Match!!", Toast.LENGTH_SHORT).show();
                    addData(date, supplier, supplierAdd, driver, storeName, vehical, uMaterial1, uQuantity1, uMaterial2, uQuantity2, uMaterial3, uQuantity3, uMaterial4, uQuantity4, uMaterial5, uQuantity5,
                            uMaterial6, uQuantity6, uMaterial7, uQuantity7, uMaterial8, uQuantity8, uMaterial9, uQuantity9, uMaterial10, uQuantity10
                            , uQuantity11, uMaterial12, uQuantity12, uMaterial13, uQuantity13, uMaterial14, uQuantity14, uMaterial15, uQuantity15,
                            uMaterial16, uQuantity16, uMaterial17, uQuantity17, uMaterial18, uQuantity18, uMaterial19, uQuantity19, uMaterial20, uQuantity20
                            , uUnit1, uUnit2, uUnit3, uUnit4, uUnit5, uUnit6, uUnit7, uUnit8, uUnit9, uUnit10
                            , uUnit11, uUnit12, uUnit13, uUnit14, uUnit15, uUnit16, uUnit17, uUnit18, uUnit19, uUnit20, uMaterial11
                            , uMaterial21, uQuantity21, uMaterial22, uQuantity22, uMaterial23, uQuantity23, uMaterial24, uQuantity24, uMaterial25, uQuantity25,
                            uMaterial26, uQuantity26, uMaterial27, uQuantity27, uMaterial28, uQuantity28, uMaterial29, uQuantity29, uMaterial30, uQuantity30
                            , uUnit21, uUnit22, uUnit23, uUnit24, uUnit25, uUnit26, uUnit27, uUnit28, uUnit29, uUnit30, materialReceiver);
                } else {
                    pass.setError("Invalid!!");
                    pass.requestFocus();
                }

            }

        });


    }

    private void addData(String date, String supplier, String supplierAdd, String driver, String storeName, String vehical, String uMaterial1, String uQuantity1, String uMaterial2, String uQuantity2, String uMaterial3, String uQuantity3, String uMaterial4, String uQuantity4, String uMaterial5, String uQuantity5, String uMaterial6, String uQuantity6, String uMaterial7, String uQuantity7, String uMaterial8, String uQuantity8, String uMaterial9, String uQuantity9, String uMaterial10, String uQuantity10, String uQuantity11, String uMaterial12, String uQuantity12, String uMaterial13, String uQuantity13, String uMaterial14, String uQuantity14, String uMaterial15, String uQuantity15, String uMaterial16, String uQuantity16, String uMaterial17, String uQuantity17, String uMaterial18, String uQuantity18, String uMaterial19, String uQuantity19, String uMaterial20, String uQuantity20, String uUnit1, String uUnit2, String uUnit3, String uUnit4, String uUnit5, String uUnit6, String uUnit7, String uUnit8, String uUnit9, String uUnit10, String uUnit11, String uUnit12, String uUnit13, String uUnit14, String uUnit15, String uUnit16, String uUnit17, String uUnit18, String uUnit19, String uUnit20, String uMaterial11, String uMaterial21, String uQuantity21, String uMaterial22, String uQuantity22, String uMaterial23, String uQuantity23, String uMaterial24, String uQuantity24, String uMaterial25, String uQuantity25, String uMaterial26, String uQuantity26, String uMaterial27, String uQuantity27, String uMaterial28, String uQuantity28, String uMaterial29, String uQuantity29, String uMaterial30, String uQuantity30, String uUnit21, String uUnit22, String uUnit23, String uUnit24, String uUnit25, String uUnit26, String uUnit27, String uUnit28, String uUnit29, String uUnit30, String materialReceiver) {

        String id = UUID.randomUUID().toString();
        Map<String, Object> doc = new HashMap<>();

        doc.put("id", id);
        doc.put("Date",date);
        doc.put("SearchDate",date.toLowerCase());
        doc.put("Supplier",supplier);
        doc.put("SupplierAddress",supplierAdd);
        doc.put("DriverName",driver);
        doc.put("StoreName",storeName);
        doc.put("Vehical",vehical);

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

        fStore.collection(cmp+" StockEntry").document(id).set(doc).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                //this will be called when data is added Successfully
                Toast.makeText(getApplicationContext(), "Data Added!!", Toast.LENGTH_SHORT).show();
                animation.setVisibility(View.VISIBLE);
                mediaPlayer.start();
                receiverDetails.setVisibility(View.GONE);



                if (modelList.isEmpty()){

                    if (!uMaterial1.isEmpty() && !uQuantity1.isEmpty()){
                        addStock(cmp, uMaterial1, uUnit1, Float.parseFloat(uQuantity1));

                    }
                    if (!uMaterial2.isEmpty() && !uQuantity2.isEmpty()){
                        addStock(cmp, uMaterial2, uUnit2, Float.parseFloat(uQuantity2));

                    }
                    if (!uMaterial3.isEmpty() && !uQuantity3.isEmpty()){
                        addStock(cmp, uMaterial3, uUnit3, Float.parseFloat(uQuantity3));

                    }
                    if (!uMaterial4.isEmpty() && !uQuantity4.isEmpty()){
                        addStock(cmp, uMaterial4, uUnit4, Float.parseFloat(uQuantity4));

                    }
                    if (!uMaterial5.isEmpty() && !uQuantity5.isEmpty()){
                        addStock(cmp, uMaterial5, uUnit5, Float.parseFloat(uQuantity5));

                    }
                    if (!uMaterial6.isEmpty() && !uQuantity6.isEmpty()){
                        addStock(cmp, uMaterial6, uUnit6, Float.parseFloat(uQuantity6));

                    }
                    if (!uMaterial7.isEmpty() && !uQuantity7.isEmpty()){
                        addStock(cmp, uMaterial7, uUnit7, Float.parseFloat(uQuantity7));

                    }
                    if (!uMaterial8.isEmpty() && !uQuantity8.isEmpty()){
                        addStock(cmp, uMaterial8, uUnit8, Float.parseFloat(uQuantity8));

                    }
                    if (!uMaterial9.isEmpty() && !uQuantity9.isEmpty()){
                        addStock(cmp, uMaterial9, uUnit9, Float.parseFloat(uQuantity9));

                    }
                    if (!uMaterial10.isEmpty() && !uQuantity10.isEmpty()){
                        addStock(cmp, uMaterial10, uUnit10, Float.parseFloat(uQuantity10));

                    }
                    if (!uMaterial11.isEmpty() && !uQuantity11.isEmpty()){
                        addStock(cmp, uMaterial11, uUnit11, Float.parseFloat(uQuantity11));
                    }
                    if (!uMaterial12.isEmpty() && !uQuantity12.isEmpty()){
                        addStock(cmp, uMaterial12, uUnit12, Float.parseFloat(uQuantity12));
                    }
                    if (!uMaterial13.isEmpty() && !uQuantity13.isEmpty()){
                        addStock(cmp, uMaterial13, uUnit13, Float.parseFloat(uQuantity13));
                    }
                    if (!uMaterial14.isEmpty() && !uQuantity14.isEmpty()){
                        addStock(cmp, uMaterial14, uUnit14, Float.parseFloat(uQuantity14));
                    }
                    if (!uMaterial15.isEmpty() && !uQuantity15.isEmpty()){
                        addStock(cmp, uMaterial15, uUnit15, Float.parseFloat(uQuantity15));
                    }
                    if (!uMaterial16.isEmpty() && !uQuantity16.isEmpty()){
                        addStock(cmp, uMaterial16, uUnit16, Float.parseFloat(uQuantity16));
                    }
                    if (!uMaterial17.isEmpty() && !uQuantity17.isEmpty()){
                        addStock(cmp, uMaterial17, uUnit17, Float.parseFloat(uQuantity17));
                    }
                    if (!uMaterial18.isEmpty() && !uQuantity18.isEmpty()){
                        addStock(cmp, uMaterial18, uUnit18, Float.parseFloat(uQuantity18));
                    }
                    if (!uMaterial19.isEmpty() && !uQuantity19.isEmpty()){
                        addStock(cmp, uMaterial19, uUnit19, Float.parseFloat(uQuantity19));
                    }
                    if (!uMaterial20.isEmpty() && !uQuantity20.isEmpty()){
                        addStock(cmp, uMaterial20, uUnit20, Float.parseFloat(uQuantity20));
                    }
                    if (!uMaterial21.isEmpty() && !uQuantity21.isEmpty()){
                        addStock(cmp, uMaterial21, uUnit21, Float.parseFloat(uQuantity21));
                    }
                    if (!uMaterial22.isEmpty() && !uQuantity22.isEmpty()){
                        addStock(cmp, uMaterial22, uUnit22, Float.parseFloat(uQuantity22));
                    }
                    if (!uMaterial23.isEmpty() && !uQuantity23.isEmpty()){
                        addStock(cmp, uMaterial23, uUnit23, Float.parseFloat(uQuantity23));
                    }
                    if (!uMaterial24.isEmpty() && !uQuantity24.isEmpty()){
                        addStock(cmp, uMaterial24, uUnit24, Float.parseFloat(uQuantity24));
                    }
                    if (!uMaterial25.isEmpty() && !uQuantity25.isEmpty()){
                        addStock(cmp, uMaterial25, uUnit25, Float.parseFloat(uQuantity25));
                    }
                    if (!uMaterial26.isEmpty() && !uQuantity26.isEmpty()){
                        addStock(cmp, uMaterial26, uUnit26, Float.parseFloat(uQuantity26));
                    }
                    if (!uMaterial27.isEmpty() && !uQuantity27.isEmpty()){
                        addStock(cmp, uMaterial27, uUnit27, Float.parseFloat(uQuantity27));
                    }
                    if (!uMaterial28.isEmpty() && !uQuantity28.isEmpty()){
                        addStock(cmp, uMaterial28, uUnit28, Float.parseFloat(uQuantity28));
                    }
                    if (!uMaterial29.isEmpty() && !uQuantity29.isEmpty()){
                        addStock(cmp, uMaterial29, uUnit29, Float.parseFloat(uQuantity29));
                    }
                    if (!uMaterial30.isEmpty() && !uQuantity30.isEmpty()){
                        addStock(cmp, uMaterial30, uUnit30, Float.parseFloat(uQuantity30));
                    }

                }

                else {
                for (int i=0;i<modelList.size();i++){

                    if (modelList.get(i).getMaterial().equals(uMaterial1)){
                            float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(uQuantity1);
                            addStock(cmp, modelList.get(i).getMaterial(), uUnit1, a);
                            p1=1;
                    } else {
                        q1=1;
                    }

                    if (modelList.get(i).getMaterial().equals(uMaterial2)){
                        float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(uQuantity2);
                        addStock(cmp, modelList.get(i).getMaterial(), uUnit2, a);
                        p2=2;
                    } else {
                        q2=2;
                    }

                    if (modelList.get(i).getMaterial().equals(uMaterial3)){
                        float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(uQuantity3);
                        addStock(cmp, modelList.get(i).getMaterial(), uUnit3, a);
                        p3=3;
                    } else {
                        q3=3;
                    }

                    if (modelList.get(i).getMaterial().equals(uMaterial4)){
                        float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(uQuantity4);
                        addStock(cmp, modelList.get(i).getMaterial(), uUnit4, a);
                        p4=4;
                    } else {
                        q4=4;
                    }

                    if (modelList.get(i).getMaterial().equals(uMaterial5)){
                        float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(uQuantity5);
                        addStock(cmp, modelList.get(i).getMaterial(), uUnit5, a);
                        p5=5;
                    } else {
                        q5=5;
                    }

                    if (modelList.get(i).getMaterial().equals(uMaterial6)){
                        float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(uQuantity6);
                        addStock(cmp, modelList.get(i).getMaterial(), uUnit6, a);
                        p6=6;
                    } else {
                        q6=6;
                    }

                    if (modelList.get(i).getMaterial().equals(uMaterial7)){
                        float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(uQuantity7);
                        addStock(cmp, modelList.get(i).getMaterial(), uUnit7, a);
                        p7=7;
                    } else {
                        q7=7;
                    }

                    if (modelList.get(i).getMaterial().equals(uMaterial9)){
                        float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(uQuantity9);
                        addStock(cmp, modelList.get(i).getMaterial(), uUnit9, a);
                        p9=9;
                    } else {
                        q9=9;
                    }

                    if (modelList.get(i).getMaterial().equals(uMaterial10)){
                        float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(uQuantity10);
                        addStock(cmp, modelList.get(i).getMaterial(), uUnit10, a);
                        p10=10;
                    } else {
                        q10=10;
                    }

                    if (modelList.get(i).getMaterial().equals(uMaterial11)){
                        float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(uQuantity11);
                        addStock(cmp, modelList.get(i).getMaterial(), uUnit11, a);
                        p11=11;
                    } else {
                        q11=11;
                    }

                    if (modelList.get(i).getMaterial().equals(uMaterial12)){
                        float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(uQuantity12);
                        addStock(cmp, modelList.get(i).getMaterial(), uUnit12, a);
                        p12=12;
                    } else {
                        q12=12;
                    }

                    if (modelList.get(i).getMaterial().equals(uMaterial13)){
                        float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(uQuantity13);
                        addStock(cmp, modelList.get(i).getMaterial(), uUnit13, a);
                        p13=13;
                    } else {
                        q13=13;
                    }

                    if (modelList.get(i).getMaterial().equals(uMaterial14)){
                        float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(uQuantity14);
                        addStock(cmp, modelList.get(i).getMaterial(), uUnit14, a);
                        p14=14;
                    } else {
                        q14=14;
                    }

                    if (modelList.get(i).getMaterial().equals(uMaterial15)){
                        float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(uQuantity15);
                        addStock(cmp, modelList.get(i).getMaterial(), uUnit15, a);
                        p15=15;
                    } else {
                        q15=15;
                    }

                    if (modelList.get(i).getMaterial().equals(uMaterial16)){
                        float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(uQuantity16);
                        addStock(cmp, modelList.get(i).getMaterial(), uUnit16, a);
                        p16=16;
                    } else {
                        q16=16;
                    }

                    if (modelList.get(i).getMaterial().equals(uMaterial17)){
                        float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(uQuantity17);
                        addStock(cmp, modelList.get(i).getMaterial(), uUnit17, a);
                        p17=17;
                    } else {
                        q17=17;
                    }

                    if (modelList.get(i).getMaterial().equals(uMaterial18)){
                        float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(uQuantity18);
                        addStock(cmp, modelList.get(i).getMaterial(), uUnit18, a);
                        p18=18;
                    } else {
                        q18=18;
                    }

                    if (modelList.get(i).getMaterial().equals(uMaterial19)){
                        float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(uQuantity19);
                        addStock(cmp, modelList.get(i).getMaterial(), uUnit19, a);
                        p19=19;
                    } else {
                        q19=19;
                    }

                    if (modelList.get(i).getMaterial().equals(uMaterial20)){
                        float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(uQuantity20);
                        addStock(cmp, modelList.get(i).getMaterial(), uUnit20, a);
                        p20=20;
                    } else {
                        q20=20;
                    }

                    if (modelList.get(i).getMaterial().equals(uMaterial21)){
                        float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(uQuantity21);
                        addStock(cmp, modelList.get(i).getMaterial(), uUnit21, a);
                        p21=21;
                    } else {
                        q21=21;
                    }

                    if (modelList.get(i).getMaterial().equals(uMaterial22)){
                        float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(uQuantity22);
                        addStock(cmp, modelList.get(i).getMaterial(), uUnit22, a);
                        p22=22;
                    } else {
                        q22=22;
                    }

                    if (modelList.get(i).getMaterial().equals(uMaterial23)){
                        float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(uQuantity23);
                        addStock(cmp, modelList.get(i).getMaterial(), uUnit23, a);
                        p23=23;
                    } else {
                        q23=23;
                    }

                    if (modelList.get(i).getMaterial().equals(uMaterial24)){
                        float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(uQuantity24);
                        addStock(cmp, modelList.get(i).getMaterial(), uUnit24, a);
                        p24=24;
                    } else {
                        q24=24;
                    }

                    if (modelList.get(i).getMaterial().equals(uMaterial25)){
                        float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(uQuantity25);
                        addStock(cmp, modelList.get(i).getMaterial(), uUnit25, a);
                        p25=25;
                    } else {
                        q25=25;
                    }

                    if (modelList.get(i).getMaterial().equals(uMaterial26)){
                        float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(uQuantity26);
                        addStock(cmp, modelList.get(i).getMaterial(), uUnit26, a);
                        p26=26;
                    } else {
                        q26=26;
                    }

                    if (modelList.get(i).getMaterial().equals(uMaterial27)){
                        float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(uQuantity27);
                        addStock(cmp, modelList.get(i).getMaterial(), uUnit27, a);
                        p27=27;
                    } else {
                        q27=27;
                    }

                    if (modelList.get(i).getMaterial().equals(uMaterial28)){
                        float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(uQuantity28);
                        addStock(cmp, modelList.get(i).getMaterial(), uUnit28, a);
                        p28=28;
                    } else {
                        q28=28;
                    }

                    if (modelList.get(i).getMaterial().equals(uMaterial29)){
                        float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(uQuantity29);
                        addStock(cmp, modelList.get(i).getMaterial(), uUnit29, a);
                        p29=29;
                    } else {
                        q29=29;
                    }

                    if (modelList.get(i).getMaterial().equals(uMaterial30)){
                        float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(uQuantity30);
                        addStock(cmp, modelList.get(i).getMaterial(), uUnit30, a);
                        p30=30;
                    } else {
                        q30=30;
                    }






                    }
                }

                if (!uMaterial1.isEmpty() && !uQuantity1.isEmpty()) {
                    if (p1 != 1 && q1 == 1) {
                        addStock(cmp, uMaterial1, uUnit1, Float.parseFloat(uQuantity1));
                    }
                }
                if (!uMaterial2.isEmpty() && !uQuantity2.isEmpty()) {
                    if (p2 != 2 && q2 == 2) {
                        addStock(cmp, uMaterial2, uUnit2, Float.parseFloat(uQuantity2));
                    }
                }
                if (!uMaterial3.isEmpty() && !uQuantity3.isEmpty()) {
                    if (p3 != 3 && q3 == 3) {
                        addStock(cmp, uMaterial3, uUnit3, Float.parseFloat(uQuantity3));
                    }
                }
                if (!uMaterial4.isEmpty() && !uQuantity4.isEmpty()) {
                    if (p4 != 4 && q4 == 4) {
                        addStock(cmp, uMaterial4, uUnit4, Float.parseFloat(uQuantity4));
                    }
                }
                if (!uMaterial5.isEmpty() && !uQuantity5.isEmpty()) {
                    if (p5 != 5 && q5 == 5) {
                        addStock(cmp, uMaterial5, uUnit5, Float.parseFloat(uQuantity5));
                    }
                }
                if (!uMaterial6.isEmpty() && !uQuantity6.isEmpty()) {
                    if (p6 != 6 && q6 == 6) {
                        addStock(cmp, uMaterial6, uUnit6, Float.parseFloat(uQuantity6));
                    }
                }
                if (!uMaterial7.isEmpty() && !uQuantity7.isEmpty()) {
                    if (p7 != 7 && q7 == 7) {
                        addStock(cmp, uMaterial7, uUnit7, Float.parseFloat(uQuantity7));
                    }
                }
                if (!uMaterial8.isEmpty() && !uQuantity8.isEmpty()) {
                    if (p8 != 8 && q8 == 8) {
                        addStock(cmp, uMaterial8, uUnit8, Float.parseFloat(uQuantity8));
                    }
                }
                if (!uMaterial9.isEmpty() && !uQuantity9.isEmpty()) {
                    if (p9 != 9 && q9 == 9) {
                        addStock(cmp, uMaterial9, uUnit9, Float.parseFloat(uQuantity9));
                    }
                }
                if (!uMaterial10.isEmpty() && !uQuantity10.isEmpty()) {
                    if (p10 != 10 && q10 == 10) {
                        addStock(cmp, uMaterial10, uUnit10, Float.parseFloat(uQuantity10));
                    }
                }
                if (!uMaterial11.isEmpty() && !uQuantity11.isEmpty()) {
                    if (p11 != 11 && q11 == 11) {
                        addStock(cmp, uMaterial11, uUnit11, Float.parseFloat(uQuantity11));
                    }
                }
                if (!uMaterial12.isEmpty() && !uQuantity12.isEmpty()) {
                    if (p12 != 12 && q12 == 12) {
                        addStock(cmp, uMaterial12, uUnit12, Float.parseFloat(uQuantity12));
                    }
                }
                if (!uMaterial13.isEmpty() && !uQuantity13.isEmpty()) {
                    if (p13 != 13 && q13 == 13) {
                        addStock(cmp, uMaterial13, uUnit13, Float.parseFloat(uQuantity13));
                    }
                }
                if (!uMaterial14.isEmpty() && !uQuantity14.isEmpty()) {
                    if (p14 != 14 && q14 == 14) {
                        addStock(cmp, uMaterial14, uUnit14, Float.parseFloat(uQuantity14));
                    }
                }
                if (!uMaterial15.isEmpty() && !uQuantity15.isEmpty()) {
                    if (p15 != 15 && q15 == 15) {
                        addStock(cmp, uMaterial15, uUnit15, Float.parseFloat(uQuantity15));
                    }
                }
                if (!uMaterial16.isEmpty() && !uQuantity16.isEmpty()) {
                    if (p16 != 16 && q16 == 16) {
                        addStock(cmp, uMaterial16, uUnit16, Float.parseFloat(uQuantity16));
                    }
                }
                if (!uMaterial17.isEmpty() && !uQuantity17.isEmpty()) {
                    if (p17 != 17 && q17 == 17) {
                        addStock(cmp, uMaterial17, uUnit17, Float.parseFloat(uQuantity17));
                    }
                }
                if (!uMaterial18.isEmpty() && !uQuantity18.isEmpty()) {
                    if (p18 != 18 && q18 == 18) {
                        addStock(cmp, uMaterial18, uUnit18, Float.parseFloat(uQuantity18));
                    }
                }
                if (!uMaterial19.isEmpty() && !uQuantity19.isEmpty()) {
                    if (p19 != 19 && q19 == 19) {
                        addStock(cmp, uMaterial19, uUnit19, Float.parseFloat(uQuantity19));
                    }
                }
                if (!uMaterial20.isEmpty() && !uQuantity20.isEmpty()) {
                    if (p20 != 20 && q20 == 20) {
                        addStock(cmp, uMaterial20, uUnit20, Float.parseFloat(uQuantity20));
                    }
                }
                if (!uMaterial21.isEmpty() && !uQuantity21.isEmpty()) {
                    if (p21 != 21 && q21 == 21) {
                        addStock(cmp, uMaterial21, uUnit21, Float.parseFloat(uQuantity21));
                    }
                }
                if (!uMaterial22.isEmpty() && !uQuantity22.isEmpty()) {
                    if (p22 != 22 && q22 == 22) {
                        addStock(cmp, uMaterial22, uUnit22, Float.parseFloat(uQuantity22));
                    }
                }
                if (!uMaterial23.isEmpty() && !uQuantity23.isEmpty()) {
                    if (p23 != 23 && q23 == 23) {
                        addStock(cmp, uMaterial23, uUnit23, Float.parseFloat(uQuantity23));
                    }
                }
                if (!uMaterial24.isEmpty() && !uQuantity24.isEmpty()) {
                    if (p24 != 24 && q24 == 24) {
                        addStock(cmp, uMaterial24, uUnit24, Float.parseFloat(uQuantity24));
                    }
                }
                if (!uMaterial25.isEmpty() && !uQuantity25.isEmpty()) {
                    if (p25 != 25 && q25 == 25) {
                        addStock(cmp, uMaterial25, uUnit25, Float.parseFloat(uQuantity25));
                    }
                }
                if (!uMaterial26.isEmpty() && !uQuantity26.isEmpty()) {
                    if (p26 != 26 && q26 == 26) {
                        addStock(cmp, uMaterial26, uUnit26, Float.parseFloat(uQuantity26));
                    }
                }
                if (!uMaterial27.isEmpty() && !uQuantity27.isEmpty()) {
                    if (p27 != 27 && q27 == 27) {
                        addStock(cmp, uMaterial27, uUnit27, Float.parseFloat(uQuantity27));
                    }
                }
                if (!uMaterial28.isEmpty() && !uQuantity28.isEmpty()) {
                    if (p28 != 28 && q28 == 28) {
                        addStock(cmp, uMaterial28, uUnit28, Float.parseFloat(uQuantity28));
                    }
                }
                if (!uMaterial29.isEmpty() && !uQuantity29.isEmpty()) {
                    if (p29 != 29 && q29 == 29) {
                        addStock(cmp, uMaterial29, uUnit29, Float.parseFloat(uQuantity29));
                    }
                }
                if (!uMaterial30.isEmpty() && !uQuantity30.isEmpty()) {
                    if (p30 != 30 && q30 == 30) {
                        addStock(cmp, uMaterial30, uUnit30, Float.parseFloat(uQuantity30));
                    }
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //this will be called when data is added Failed
                Toast.makeText(getApplicationContext(), "Failed to add data "+e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

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
                animation.setVisibility(View.VISIBLE);
                mediaPlayer.start();
                receiverDetails.setVisibility(View.GONE);

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
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
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

    private void showData(String cmp) {
        fStore.collection(cmp+" AddStock").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                modelList.clear();
                pd.dismiss();
                for (DocumentSnapshot doc : task.getResult()){
                    AddStockModel model = new AddStockModel(
                            doc.getString("Id"),
                            doc.getString("Material"),
                            doc.getString("Unit"),
                            doc.getString("Quantity")
                    );
                    modelList.add(model);
                }
                adapterStock = new StockAdapter(Stock.this,modelList);
                recyclerView.setAdapter(adapterStock);
            }}).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(Stock.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}

