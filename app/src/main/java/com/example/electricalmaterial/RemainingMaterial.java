package com.example.electricalmaterial;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
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

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class RemainingMaterial extends AppCompatActivity {

    List<ReaminingMaterialModel> modelList = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String companyEmail;
    String userId;

    ProgressDialog pd,pd1;

    RemainingMaterialCustomAdapter adapter;
    ImageView mAddBtn;

    //Material
    List<RemainingModel> list = new ArrayList<>();
    RecyclerView recycler;
    RecyclerView.LayoutManager layout;
    RemainingCustomAdapter materialCustomAdapter;

    String cmp;


    AutoCompleteTextView selectSearch;
    ArrayAdapter <String> adapterSearch;
    String [] item = {"Date","Team Name","Tender","Center","Village"};


    //date
    TextView mDateFormate;
    DatePickerDialog.OnDateSetListener onDateSetListener;
    String date;

    //date
    TextView mDateFormate1;
    DatePickerDialog.OnDateSetListener onDateSetListener1;
    String date1;

    TextInputLayout teamNameTIL,tenderTIL,centerTIL, villageTIL;


    //team name
    AutoCompleteTextView teamName;
    ArrayAdapter<String> adapterTeam;
    ArrayList<String> spinnerDataList;
    DatabaseReference databaseReference;
    ValueEventListener listener;

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

    ImageView searchButton,searchButton1;


    SwipeRefreshLayout refreshLayout;


    String [] arr;

    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b10;
    Button per_data,consumer_data;
    Button b11,b12,b13,b14,b15,b16,b17,b18,b19,b20;
    Button b21,b22,b23,b24,b25,b26,b27,b28,b29,b30;


    @SuppressLint({"SourceLockedOrientationActivity", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remaining_material);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        fStore = FirebaseFirestore.getInstance();
        pd = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = mAuth.getCurrentUser().getUid();

        pd1 = ProgressDialog.show(this,"Loading...","Please Wait",false,false);
        pd1.dismiss();


        ActionBar bar = getSupportActionBar();
        assert bar != null;
        bar.setTitle("USED MATERIAL");

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        modelList.clear();
        mAddBtn = findViewById(R.id.addBtn);
        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arr = new String[list.size()];
                AlertDialog.Builder builder = new AlertDialog.Builder(RemainingMaterial.this);
                String [] options = {"Export to Excel"};
                builder.setTitle("Select any one ");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        if (which==0){
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                                    checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                            }
                            else
                            {
                                //your code
                                createXlFile();
                            }

                        }
                    }
                }).create().show();


            }
        });





        showData();

        recycler = findViewById(R.id.recycler);
        recycler.setHasFixedSize(true);
        layout = new LinearLayoutManager(this);
        recycler.setLayoutManager(layout);
        list.clear();
        showMaterial();

        selectSearch = findViewById(R.id.selectSearch);

        //TextInputLayout
        teamNameTIL = findViewById(R.id.teamNameTIL);
        tenderTIL = findViewById(R.id.tenderTIL);

        centerTIL = findViewById(R.id.centerTIL);
        villageTIL = findViewById(R.id.villageTIL);


        //Team Name
        teamName = findViewById(R.id.teamName);

        //Tender
        tender = findViewById(R.id.tender);

        //Center
        center = findViewById(R.id.center);

        //Village
        village = findViewById(R.id.village);





        per_data = findViewById(R.id.per_data);
        consumer_data = findViewById(R.id.consumer_data);
        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);
        b4 = findViewById(R.id.b4);
        b5 = findViewById(R.id.b5);
        b6 = findViewById(R.id.b6);
        b7 = findViewById(R.id.b7);
        b8 = findViewById(R.id.b8);
        b9 = findViewById(R.id.b9);
        b10 = findViewById(R.id.b10);
        b11 = findViewById(R.id.b11);
        b12 = findViewById(R.id.b12);
        b13 = findViewById(R.id.b13);
        b14 = findViewById(R.id.b14);
        b15 = findViewById(R.id.b15);
        b16 = findViewById(R.id.b16);
        b17 = findViewById(R.id.b17);
        b18 = findViewById(R.id.b18);
        b19 = findViewById(R.id.b19);
        b20 = findViewById(R.id.b20);
        b21 = findViewById(R.id.b21);
        b22 = findViewById(R.id.b22);
        b23 = findViewById(R.id.b23);
        b24 = findViewById(R.id.b24);
        b25 = findViewById(R.id.b25);
        b26 = findViewById(R.id.b26);
        b27 = findViewById(R.id.b27);
        b28 = findViewById(R.id.b28);
        b29 = findViewById(R.id.b29);
        b30 = findViewById(R.id.b30);


        //Search
        searchButton = findViewById(R.id.searchButton);

        DocumentReference documentReference = fStore.collection("Users")
                .document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                companyEmail = value.getString("companyEmail");
                cmp = companyEmail;
                companyEmail = companyEmail.replace("@", "");
                companyEmail = companyEmail.replace(".", "");

                //Date
                mDateFormate = findViewById(R.id.date);
                mDateFormate1 = findViewById(R.id.date1);
                searchButton1 = findViewById(R.id.searchButton1);
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                mDateFormate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatePickerDialog datePickerDialog = new DatePickerDialog(
                                RemainingMaterial.this, android.R.style.Theme_Material_Light_Dialog_MinWidth,
                                onDateSetListener, year, month, day);

                        datePickerDialog.getWindow()
                                .setBackgroundDrawable(new ColorDrawable(Color.GREEN));
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

                mDateFormate1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatePickerDialog datePickerDialog = new DatePickerDialog(
                                RemainingMaterial.this, android.R.style.Theme_Material_Light_Dialog_MinWidth,
                                onDateSetListener1, year, month, day);

                        datePickerDialog.getWindow()
                                .setBackgroundDrawable(new ColorDrawable(Color.GREEN));
                        datePickerDialog.show();
                    }
                });
                onDateSetListener1 = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        if (month<=9){ //
                            date1  = year+"/"+"0"+month+"/"+dayOfMonth;
                        }
                        else if (dayOfMonth<=9){
                            date1  = year+"/"+month+"/"+"0"+dayOfMonth;
                        }
                        else {
                            date1  = year+"/"+month+"/"+dayOfMonth;
                        }
                        if (month<=9 && dayOfMonth<=9){
                            date1  = year+"/"+"0"+month+"/"+"0"+dayOfMonth;
                        }
                        mDateFormate1.setText(date1);

                    }
                };

                adapterSearch = new ArrayAdapter<String>(RemainingMaterial.this,R.layout.support_simple_spinner_dropdown_item,item);
                selectSearch.setAdapter(adapterSearch);

                selectSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String n = parent.getItemAtPosition(position).toString().trim();
                        switch (n) {
                            case "Date" :
                                mDateFormate.setVisibility(View.VISIBLE);
                                teamNameTIL.setVisibility(View.GONE);
                                tenderTIL.setVisibility(View.GONE);
                                searchButton.setVisibility(View.GONE);
                                centerTIL.setVisibility(View.GONE);
                                villageTIL.setVisibility(View.GONE);
                                mDateFormate1.setVisibility(View.VISIBLE);
                                searchButton1.setVisibility(View.VISIBLE);
                                break;
                            case "Team Name":
                                mDateFormate.setVisibility(View.GONE);
                                teamNameTIL.setVisibility(View.VISIBLE);
                                tenderTIL.setVisibility(View.GONE);
                                searchButton.setVisibility(View.VISIBLE);
                                centerTIL.setVisibility(View.GONE);
                                villageTIL.setVisibility(View.GONE);
                                mDateFormate1.setVisibility(View.GONE);
                                searchButton1.setVisibility(View.GONE);
                                //Team Name
                                databaseReference = FirebaseDatabase.getInstance().getReference(companyEmail + " teamName");
                                spinnerDataList = new ArrayList<>();
                                adapterTeam = new ArrayAdapter<String>(RemainingMaterial.this, R.layout.support_simple_spinner_dropdown_item, spinnerDataList);
                                teamName.setAdapter(adapterTeam);
                                retrieveData();
                                break;
                            case "Tender":
                                mDateFormate.setVisibility(View.GONE);
                                teamNameTIL.setVisibility(View.GONE);
                                tenderTIL.setVisibility(View.VISIBLE);
                                searchButton.setVisibility(View.VISIBLE);
                                centerTIL.setVisibility(View.GONE);
                                villageTIL.setVisibility(View.GONE);
                                mDateFormate1.setVisibility(View.GONE);
                                searchButton1.setVisibility(View.GONE);
                                //Tender
                                databaseReferenceTender = FirebaseDatabase.getInstance().getReference(companyEmail + " Tender");
                                spinnerDataListTender = new ArrayList<>();
                                adapterTender = new ArrayAdapter<String>(RemainingMaterial.this, R.layout.support_simple_spinner_dropdown_item, spinnerDataListTender);
                                tender.setAdapter(adapterTender);
                                retrieveDataTender();
                                break;
                            case "Center":
                                mDateFormate.setVisibility(View.GONE);
                                teamNameTIL.setVisibility(View.GONE);
                                tenderTIL.setVisibility(View.GONE);
                                searchButton.setVisibility(View.VISIBLE);
                                centerTIL.setVisibility(View.VISIBLE);
                                villageTIL.setVisibility(View.GONE);
                                mDateFormate1.setVisibility(View.GONE);
                                searchButton1.setVisibility(View.GONE);
                                //Center
                                databaseReferenceCenter = FirebaseDatabase.getInstance().getReference(companyEmail + " Center");
                                spinnerDataListCenter = new ArrayList<>();
                                adapterCenter = new ArrayAdapter<String>(RemainingMaterial.this, R.layout.support_simple_spinner_dropdown_item, spinnerDataListCenter);
                                center.setAdapter(adapterCenter);
                                retrieveDataCenter();
                                break;
                            case "Village":
                                mDateFormate.setVisibility(View.GONE);
                                teamNameTIL.setVisibility(View.GONE);
                                tenderTIL.setVisibility(View.GONE);
                                searchButton.setVisibility(View.VISIBLE);
                                centerTIL.setVisibility(View.GONE);
                                villageTIL.setVisibility(View.VISIBLE);
                                mDateFormate1.setVisibility(View.GONE);
                                searchButton1.setVisibility(View.GONE);

                                //Village
                                databaseReferenceVillage = FirebaseDatabase.getInstance().getReference(companyEmail + " Village");
                                spinnerDataListVillage = new ArrayList<>();
                                adapterVillage = new ArrayAdapter<String>(RemainingMaterial.this, R.layout.support_simple_spinner_dropdown_item, spinnerDataListVillage);
                                village.setAdapter(adapterVillage);
                                retrieveDataVillage();

                                break;
                            default:
                                mDateFormate.setVisibility(View.GONE);
                                mDateFormate1.setVisibility(View.GONE);
                                searchButton1.setVisibility(View.GONE);
                                teamNameTIL.setVisibility(View.GONE);
                                tenderTIL.setVisibility(View.GONE);
                                searchButton.setVisibility(View.GONE);
                                centerTIL.setVisibility(View.GONE);
                                villageTIL.setVisibility(View.GONE);
                                break;
                        }
                    }
                });

                searchButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String search = selectSearch.getText().toString().trim();
                        String teamNameS = teamName.getText().toString().trim();
                        String tenderS = tender.getText().toString().trim();
                        String centerS = center.getText().toString().trim();
                        String villageS = village.getText().toString().trim();

                        switch (search) {
                            case "Team Name":
                                if (teamNameS.isEmpty()) {
                                    teamName.setError("Select any one!!");
                                    teamName.requestFocus();
                                    showMessage("Please select team name to search");
                                } else {
                                    searchTeamName(cmp, teamNameS);
                                }
                                break;
                            case "Tender":
                                if (tenderS.isEmpty()) {
                                    tender.setError("Select any one!!");
                                    tender.requestFocus();
                                    showMessage("Please select tender to search");
                                } else {
                                    searchTender(cmp, tenderS);
                                }
                                break;
                            case "Center":
                                if (centerS.isEmpty()) {
                                    center.setError("Select any one!!");
                                    center.requestFocus();
                                    showMessage("Please select Center to search");
                                } else {
                                    searchCenter(cmp, centerS);
                                }
                                break;
                            case "Village":
                                if (villageS.isEmpty()) {
                                    village.setError("Select any one!!");
                                    village.requestFocus();
                                    showMessage("Please select Village to search");
                                } else {
                                    searchVillage(cmp, villageS);
                                }
                                break;
                        }





                    }
                });

                refreshLayout = findViewById(R.id.refresh1);
                refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        showData();
                        refreshLayout.setRefreshing(false);
                        selectSearch.setText("");
                    }
                });

                searchButton1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String d1 = mDateFormate.getText().toString().trim();
                        String d2 = mDateFormate1.getText().toString().trim();
                        if (d1.isEmpty()||d2.isEmpty()){
                            showMessage("Please Select Date range to search");
                        }
                        else {
                            searchDate(cmp,date,date1);
                        }

                    }
                });

                per_data.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addMaterialData(cmp);
                    }
                });

                consumer_data.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        addConsumerName(cmp);

                    }
                });

                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addTotalMaterialTaken1(cmp);
                    }
                });

                b2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addTotalMaterialTaken2(cmp);
                    }
                });

                b3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addTotalMaterialTaken3(cmp);
                    }
                });

                b4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addTotalMaterialTaken4(cmp);
                    }
                });

                b5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addTotalMaterialTaken5(cmp);
                    }
                });

                b6.setOnClickListener(v -> addTotalMaterialTaken6(cmp) );

                b7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addTotalMaterialTaken7(cmp);
                    }
                });

                b8.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addTotalMaterialTaken8(cmp);
                    }
                });

                b9.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addTotalMaterialTaken9(cmp);
                    }
                });

                b10.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addTotalMaterialTaken10(cmp);
                    }
                });

                b11.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addTotalMaterialTaken11(cmp);
                    }
                });

                b12.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addTotalMaterialTaken12(cmp);
                    }
                });

                b13.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addTotalMaterialTaken13(cmp);
                    }
                });

                b14.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addTotalMaterialTaken14(cmp);
                    }
                });

                b15.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addTotalMaterialTaken15(cmp);
                    }
                });

                b16.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addTotalMaterialTaken16(cmp);
                    }
                });

                b17.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addTotalMaterialTaken17(cmp);
                    }
                });

                b18.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addTotalMaterialTaken18(cmp);
                    }
                });

                b19.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addTotalMaterialTaken19(cmp);
                    }
                });

                b20.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addTotalMaterialTaken20(cmp);
                    }
                });

                b21.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addTotalMaterialTaken21(cmp);
                    }
                });

                b22.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addTotalMaterialTaken22(cmp);
                    }
                });

                b23.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addTotalMaterialTaken23(cmp);
                    }
                });

                b24.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addTotalMaterialTaken24(cmp);
                    }
                });

                b25.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addTotalMaterialTaken25(cmp);
                    }
                });

                b26.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addTotalMaterialTaken26(cmp);
                    }
                });

                b27.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addTotalMaterialTaken27(cmp);
                    }
                });

                b28.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addTotalMaterialTaken28(cmp);
                    }
                });

                b29.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addTotalMaterialTaken29(cmp);
                    }
                });

                b30.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addTotalMaterialTaken30(cmp);
                    }
                });


//                return false;
            }

        });


    }

    private void addConsumerName(String cmp) {
        for (int i=0;i<modelList.size();i++){
            String consumerName = modelList.get(i).getConsumerName();
            String consumerId = removeSpecialSymbols(consumerName);

            addAllConsumerSpineer(cmp,consumerId,consumerName);

        }
    }

    private void addAllConsumerSpineer(String cmp, String consumerId, String consumerName) {

    }

    private  String removeSpecialSymbols(String string) {
        Pattern pattern = Pattern.compile("[\\W_]+");
        return pattern.matcher(string).replaceAll("");
    }

    private void addTotalMaterialTaken1(String cmp) {

        int a = 0;

        for (int i = 0; i < modelList.size(); i++) {

            for (int j = 0; j < modelList.size(); j++) {
                if (modelList.get(i).getConsumerName().equals(modelList.get(j).getConsumerName()) &&
                    modelList.get(i).getTeamName().equals(modelList.get(j).getTeamName()) &&
                    !modelList.get(i).getDate().equals(modelList.get(j).getDate())){
                    a = 0;
                        if (modelList.get(i).getMaterial1().equals(modelList.get(j).getMaterial1())&&
                                modelList.get(i).getUnit1().equals(modelList.get(j).getUnit1())
                            && ! modelList.get(i).getMaterial1().isEmpty()){
                            String q = addTwoString(modelList.get(i).getQuantity1(),modelList.get(j).getQuantity1());

                            addTotalMaterialTakenSpecific(modelList.get(i).getMaterial1(),cmp,modelList.get(i).getConsumerName(),
                                    modelList.get(i).getTeamName(),q,modelList.get(i).getUnit1());

                        } else if (! modelList.get(i).getMaterial1().isEmpty() && !modelList.get(j).getMaterial1().isEmpty()){
                            addTotalMaterialTakenSpecific(modelList.get(i).getMaterial1(),cmp,modelList.get(i).getConsumerName(),
                                    modelList.get(i).getTeamName(),modelList.get(i).getQuantity1(),modelList.get(i).getUnit1());
                        }

                }
                else {
                    a = 1;
                }
            }

            if (a ==1  && !modelList.get(i).getMaterial1().isEmpty()) {
                addTotalMaterialTakenSpecific(modelList.get(i).getMaterial1(),cmp,modelList.get(i).getConsumerName(),
                        modelList.get(i).getTeamName(),modelList.get(i).getQuantity1(),modelList.get(i).getUnit1());
            }

        }


    }

    private void addTotalMaterialTaken2(String cmp) {

        int a = 0;

        for (int i = 0; i < modelList.size(); i++) {
            for (int j = 0; j < modelList.size(); j++) {
                if (modelList.get(i).getConsumerName().equals(modelList.get(j).getConsumerName()) &&
                        modelList.get(i).getTeamName().equals(modelList.get(j).getTeamName()) &&
                        !modelList.get(i).getDate().equals(modelList.get(j).getDate())){
                    a = 0;
                    if (modelList.get(i).getMaterial2().equals(modelList.get(j).getMaterial2())&&
                            modelList.get(i).getQuantity2().equals(modelList.get(j).getQuantity2())
                            && ! modelList.get(i).getMaterial2().isEmpty()){
                        String q = addTwoString(modelList.get(i).getUnit2(),modelList.get(j).getUnit2());

                        addTotalMaterialTakenSpecific(modelList.get(i).getMaterial2(),cmp,modelList.get(i).getConsumerName(),
                                modelList.get(i).getTeamName(),q,modelList.get(i).getQuantity2());

                    } else if (! modelList.get(i).getMaterial2().isEmpty() && !modelList.get(j).getMaterial2().isEmpty()){
                        addTotalMaterialTakenSpecific(modelList.get(i).getMaterial2(),cmp,modelList.get(i).getConsumerName(),
                                modelList.get(i).getTeamName(),modelList.get(i).getUnit2(),modelList.get(i).getQuantity2());
                    }

                }
                else {
                    a = 1;
                }
            }

            if (a == 1&& !modelList.get(i).getMaterial2().isEmpty()) {
                addTotalMaterialTakenSpecific(modelList.get(i).getMaterial2(),cmp,modelList.get(i).getConsumerName(),
                        modelList.get(i).getTeamName(),modelList.get(i).getUnit2(),modelList.get(i).getQuantity2());
            }

        }


    }

    private void addTotalMaterialTaken3(String cmp) {

        int a = 0;

        for (int i = 0; i < modelList.size(); i++) {
            for (int j = 0; j < modelList.size(); j++) {
                if (modelList.get(i).getConsumerName().equals(modelList.get(j).getConsumerName()) &&
                        modelList.get(i).getTeamName().equals(modelList.get(j).getTeamName()) &&
                        !modelList.get(i).getDate().equals(modelList.get(j).getDate())){
                    a = 0;
                    if (modelList.get(i).getMaterial3().equals(modelList.get(j).getMaterial3())&&
                            modelList.get(i).getUnit3().equals(modelList.get(j).getUnit3())
                            && ! modelList.get(i).getMaterial3().isEmpty()){
                        String q = addTwoString(modelList.get(i).getQuantity3(),modelList.get(j).getQuantity3());

                        addTotalMaterialTakenSpecific(modelList.get(i).getMaterial3(),cmp,modelList.get(i).getConsumerName(),
                                modelList.get(i).getTeamName(),q,modelList.get(i).getUnit3());

                    } else if (! modelList.get(i).getMaterial3().isEmpty() && !modelList.get(j).getMaterial3().isEmpty()){
                        addTotalMaterialTakenSpecific(modelList.get(i).getMaterial3(),cmp,modelList.get(i).getConsumerName(),
                                modelList.get(i).getTeamName(),modelList.get(i).getQuantity3(),modelList.get(i).getUnit3());
                    }

                }
                else {
                    a = 1;
                }
            }

            if (a == 1&& !modelList.get(i).getMaterial3().isEmpty()) {
                addTotalMaterialTakenSpecific(modelList.get(i).getMaterial3(),cmp,modelList.get(i).getConsumerName(),
                        modelList.get(i).getTeamName(),modelList.get(i).getQuantity3(),modelList.get(i).getUnit3());
            }

        }


    }

    private void addTotalMaterialTaken4(String cmp) {

        int a = 0;

        for (int i = 0; i < modelList.size(); i++) {
            for (int j = 0; j < modelList.size(); j++) {
                if (modelList.get(i).getConsumerName().equals(modelList.get(j).getConsumerName()) &&
                        modelList.get(i).getTeamName().equals(modelList.get(j).getTeamName()) &&
                        !modelList.get(i).getDate().equals(modelList.get(j).getDate())){
                    a = 0;
                    if (modelList.get(i).getMaterial4().equals(modelList.get(j).getMaterial4())&&
                            modelList.get(i).getUnit4().equals(modelList.get(j).getUnit4())
                            && ! modelList.get(i).getMaterial4().isEmpty()){
                        String q = addTwoString(modelList.get(i).getQuantity4(),modelList.get(j).getQuantity4());

                        addTotalMaterialTakenSpecific(modelList.get(i).getMaterial4(),cmp,modelList.get(i).getConsumerName(),
                                modelList.get(i).getTeamName(),q,modelList.get(i).getUnit4());

                    } else if (! modelList.get(i).getMaterial4().isEmpty() && !modelList.get(j).getMaterial4().isEmpty()){
                        addTotalMaterialTakenSpecific(modelList.get(i).getMaterial4(),cmp,modelList.get(i).getConsumerName(),
                                modelList.get(i).getTeamName(),modelList.get(i).getQuantity4(),modelList.get(i).getUnit4());
                    }

                }
                else {
                    a = 1;
                }
            }

            if (a == 1 && !modelList.get(i).getMaterial4().isEmpty()) {
                addTotalMaterialTakenSpecific(modelList.get(i).getMaterial4(),cmp,modelList.get(i).getConsumerName(),
                        modelList.get(i).getTeamName(),modelList.get(i).getQuantity4(),modelList.get(i).getUnit4());
            }

        }


    }

    private void addTotalMaterialTaken5(String cmp) {

        int a = 0;

        for (int i = 0; i < modelList.size(); i++) {
            for (int j = 0; j < modelList.size(); j++) {
                if (modelList.get(i).getConsumerName().equals(modelList.get(j).getConsumerName()) &&
                        modelList.get(i).getTeamName().equals(modelList.get(j).getTeamName()) &&
                        !modelList.get(i).getDate().equals(modelList.get(j).getDate())){
                    a = 0;
                    if (modelList.get(i).getMaterial5().equals(modelList.get(j).getMaterial5())&&
                            modelList.get(i).getUnit5().equals(modelList.get(j).getUnit5())
                            && ! modelList.get(i).getMaterial5().isEmpty()){
                        String q = addTwoString(modelList.get(i).getQuantity5(),modelList.get(j).getQuantity5());

                        addTotalMaterialTakenSpecific(modelList.get(i).getMaterial5(),cmp,modelList.get(i).getConsumerName(),
                                modelList.get(i).getTeamName(),q,modelList.get(i).getUnit5());

                    } else if (! modelList.get(i).getMaterial5().isEmpty() && !modelList.get(j).getMaterial5().isEmpty()){
                        addTotalMaterialTakenSpecific(modelList.get(i).getMaterial5(),cmp,modelList.get(i).getConsumerName(),
                                modelList.get(i).getTeamName(),modelList.get(i).getQuantity5(),modelList.get(i).getUnit5());
                    }

                }
                else {
                    a = 1;
                }
            }

            if (a == 1 && !modelList.get(i).getMaterial5().isEmpty()) {
                addTotalMaterialTakenSpecific(modelList.get(i).getMaterial5(),cmp,modelList.get(i).getConsumerName(),
                        modelList.get(i).getTeamName(),modelList.get(i).getQuantity5(),modelList.get(i).getUnit5());
            }

            //

        }


    }

    private void addTotalMaterialTaken6(String cmp) {

        int a = 0;

        for (int i = 0; i < modelList.size(); i++) {

            for (int j = 0; j < modelList.size(); j++) {
                if (modelList.get(i).getConsumerName().equals(modelList.get(j).getConsumerName()) &&
                        modelList.get(i).getTeamName().equals(modelList.get(j).getTeamName()) &&
                        !modelList.get(i).getDate().equals(modelList.get(j).getDate())){
                    a = 0;
                    if (modelList.get(i).getMaterial6().equals(modelList.get(j).getMaterial6())&&
                            modelList.get(i).getUnit6().equals(modelList.get(j).getUnit6())
                            && ! modelList.get(i).getMaterial6().isEmpty()){
                        String q = addTwoString(modelList.get(i).getQuantity6(),modelList.get(j).getQuantity6());

                        addTotalMaterialTakenSpecific(modelList.get(i).getMaterial6(),cmp,modelList.get(i).getConsumerName(),
                                modelList.get(i).getTeamName(),q,modelList.get(i).getUnit6());

                    } else if (! modelList.get(i).getMaterial6().isEmpty() && !modelList.get(j).getMaterial6().isEmpty()){
                        addTotalMaterialTakenSpecific(modelList.get(i).getMaterial6(),cmp,modelList.get(i).getConsumerName(),
                                modelList.get(i).getTeamName(),modelList.get(i).getQuantity6(),modelList.get(i).getUnit6());
                    }

                }
                else {
                    a = 1;
                }
            }

            if (a == 1 && !modelList.get(i).getMaterial6().isEmpty()) {
                addTotalMaterialTakenSpecific(modelList.get(i).getMaterial6(),cmp,modelList.get(i).getConsumerName(),
                        modelList.get(i).getTeamName(),modelList.get(i).getQuantity6(),modelList.get(i).getUnit6());
            }

        }


    }

    private void addTotalMaterialTaken7(String cmp) {

        int a = 0;

        for (int i = 0; i < modelList.size(); i++) {
            for (int j = 0; j < modelList.size(); j++) {
                if (modelList.get(i).getConsumerName().equals(modelList.get(j).getConsumerName()) &&
                        modelList.get(i).getTeamName().equals(modelList.get(j).getTeamName()) &&
                        !modelList.get(i).getDate().equals(modelList.get(j).getDate())){
                    a = 0;
                    if (modelList.get(i).getMaterial7().equals(modelList.get(j).getMaterial7())&&
                            modelList.get(i).getUnit7().equals(modelList.get(j).getUnit7())
                            && ! modelList.get(i).getMaterial7().isEmpty()){
                        String q = addTwoString(modelList.get(i).getQuantity7(),modelList.get(j).getQuantity7());

                        addTotalMaterialTakenSpecific(modelList.get(i).getMaterial7(),cmp,modelList.get(i).getConsumerName(),
                                modelList.get(i).getTeamName(),q,modelList.get(i).getUnit7());

                    } else if (! modelList.get(i).getMaterial7().isEmpty() && !modelList.get(j).getMaterial7().isEmpty()){
                        addTotalMaterialTakenSpecific(modelList.get(i).getMaterial7(),cmp,modelList.get(i).getConsumerName(),
                                modelList.get(i).getTeamName(),modelList.get(i).getQuantity7(),modelList.get(i).getUnit7());
                    }

                }
                else {
                    a = 1;
                }
            }

            if (a == 1 && !modelList.get(i).getMaterial7().isEmpty()) {
                addTotalMaterialTakenSpecific(modelList.get(i).getMaterial7(),cmp,modelList.get(i).getConsumerName(),
                        modelList.get(i).getTeamName(),modelList.get(i).getQuantity7(),modelList.get(i).getUnit7());
            }

        }


    }

    private void addTotalMaterialTaken8(String cmp) {

        int a = 0;

        for (int i = 0; i < modelList.size(); i++) {
            for (int j = 0; j < modelList.size(); j++) {
                if (modelList.get(i).getConsumerName().equals(modelList.get(j).getConsumerName()) &&
                        modelList.get(i).getTeamName().equals(modelList.get(j).getTeamName()) &&
                        !modelList.get(i).getDate().equals(modelList.get(j).getDate())){
                    a = 0;
                    if (modelList.get(i).getMaterial8().equals(modelList.get(j).getMaterial8())&&
                            modelList.get(i).getUnit8().equals(modelList.get(j).getUnit8())
                            && ! modelList.get(i).getMaterial8().isEmpty()){
                        String q = addTwoString(modelList.get(i).getQuantity8(),modelList.get(j).getQuantity8());

                        addTotalMaterialTakenSpecific(modelList.get(i).getMaterial8(),cmp,modelList.get(i).getConsumerName(),
                                modelList.get(i).getTeamName(),q,modelList.get(i).getUnit8());

                    } else if (! modelList.get(i).getMaterial8().isEmpty() && !modelList.get(j).getMaterial8().isEmpty()){
                        addTotalMaterialTakenSpecific(modelList.get(i).getMaterial8(),cmp,modelList.get(i).getConsumerName(),
                                modelList.get(i).getTeamName(),modelList.get(i).getQuantity8(),modelList.get(i).getUnit8());
                    }

                }
                else {
                    a = 1;
                }
            }

            if (a == 1&& !modelList.get(i).getMaterial8().isEmpty()) {
                addTotalMaterialTakenSpecific(modelList.get(i).getMaterial8(),cmp,modelList.get(i).getConsumerName(),
                        modelList.get(i).getTeamName(),modelList.get(i).getQuantity8(),modelList.get(i).getUnit8());
            }

        }


    }

    private void addTotalMaterialTaken9(String cmp) {

        int a = 0;

        for (int i = 0; i < modelList.size(); i++) {
            for (int j = 0; j < modelList.size(); j++) {
                if (modelList.get(i).getConsumerName().equals(modelList.get(j).getConsumerName()) &&
                        modelList.get(i).getTeamName().equals(modelList.get(j).getTeamName()) &&
                        !modelList.get(i).getDate().equals(modelList.get(j).getDate())){
                    a = 0;
                    if (modelList.get(i).getMaterial9().equals(modelList.get(j).getMaterial9())&&
                            modelList.get(i).getUnit9().equals(modelList.get(j).getUnit9())
                            && ! modelList.get(i).getMaterial9().isEmpty()){
                        String q = addTwoString(modelList.get(i).getQuantity9(),modelList.get(j).getQuantity9());

                        addTotalMaterialTakenSpecific(modelList.get(i).getMaterial9(),cmp,modelList.get(i).getConsumerName(),
                                modelList.get(i).getTeamName(),q,modelList.get(i).getUnit9());

                    } else if (! modelList.get(i).getMaterial9().isEmpty() && !modelList.get(j).getMaterial9().isEmpty()){
                        addTotalMaterialTakenSpecific(modelList.get(i).getMaterial9(),cmp,modelList.get(i).getConsumerName(),
                                modelList.get(i).getTeamName(),modelList.get(i).getQuantity9(),modelList.get(i).getUnit9());
                    }

                }
                else {
                    a = 1;
                }
            }

            if (a == 1 && !modelList.get(i).getMaterial9().isEmpty()) {
                addTotalMaterialTakenSpecific(modelList.get(i).getMaterial9(),cmp,modelList.get(i).getConsumerName(),
                        modelList.get(i).getTeamName(),modelList.get(i).getQuantity9(),modelList.get(i).getUnit9());
            }

        }


    }

    private void addTotalMaterialTaken10(String cmp) {

        int a = 0;

        for (int i = 0; i < modelList.size(); i++) {
            for (int j = 0; j < modelList.size(); j++) {
                if (modelList.get(i).getConsumerName().equals(modelList.get(j).getConsumerName()) &&
                        modelList.get(i).getTeamName().equals(modelList.get(j).getTeamName()) &&
                        !modelList.get(i).getDate().equals(modelList.get(j).getDate())){
                    a = 0;
                    if (modelList.get(i).getMaterial10().equals(modelList.get(j).getMaterial10())&&
                            modelList.get(i).getUnit10().equals(modelList.get(j).getUnit10())
                            && ! modelList.get(i).getMaterial10().isEmpty()){
                        String q = addTwoString(modelList.get(i).getQuantity10(),modelList.get(j).getQuantity10());

                        addTotalMaterialTakenSpecific(modelList.get(i).getMaterial10(),cmp,modelList.get(i).getConsumerName(),
                                modelList.get(i).getTeamName(),q,modelList.get(i).getUnit10());

                    } else if (! modelList.get(i).getMaterial10().isEmpty() && !modelList.get(j).getMaterial10().isEmpty()){
                        addTotalMaterialTakenSpecific(modelList.get(i).getMaterial10(),cmp,modelList.get(i).getConsumerName(),
                                modelList.get(i).getTeamName(),modelList.get(i).getQuantity10(),modelList.get(i).getUnit10());
                    }

                }
                else {
                    a = 1;
                }
            }

            if (a == 1 && !modelList.get(i).getMaterial10().isEmpty()) {
                addTotalMaterialTakenSpecific(modelList.get(i).getMaterial10(),cmp,modelList.get(i).getConsumerName(),
                        modelList.get(i).getTeamName(),modelList.get(i).getQuantity10(),modelList.get(i).getUnit10());
            }

            // 1

        }


    }


    private void addTotalMaterialTaken11(String cmp) {

        int a = 0;

        for (int i = 0; i < modelList.size(); i++) {
            for (int j = 0; j < modelList.size(); j++) {
                if (modelList.get(i).getConsumerName().equals(modelList.get(j).getConsumerName()) &&
                        modelList.get(i).getTeamName().equals(modelList.get(j).getTeamName()) &&
                        !modelList.get(i).getDate().equals(modelList.get(j).getDate())){
                    a = 0;
                    if (modelList.get(i).getMaterial11().equals(modelList.get(j).getMaterial11())&&
                            modelList.get(i).getUnit11().equals(modelList.get(j).getUnit11())
                            && ! modelList.get(i).getMaterial11().isEmpty()){
                        String q = addTwoString(modelList.get(i).getQuantity11(),modelList.get(j).getQuantity11());

                        addTotalMaterialTakenSpecific(modelList.get(i).getMaterial11(),cmp,modelList.get(i).getConsumerName(),
                                modelList.get(i).getTeamName(),q,modelList.get(i).getUnit11());

                    } else if (! modelList.get(i).getMaterial11().isEmpty() && !modelList.get(j).getMaterial11().isEmpty()){
                        addTotalMaterialTakenSpecific(modelList.get(i).getMaterial11(),cmp,modelList.get(i).getConsumerName(),
                                modelList.get(i).getTeamName(),modelList.get(i).getQuantity11(),modelList.get(i).getUnit11());
                    }

                }
                else {
                    a = 1;
                }
            }

            if (a == 1 && !modelList.get(i).getMaterial11().isEmpty()) {
                addTotalMaterialTakenSpecific(modelList.get(i).getMaterial11(),cmp,modelList.get(i).getConsumerName(),
                        modelList.get(i).getTeamName(),modelList.get(i).getQuantity11(),modelList.get(i).getUnit11());
            }

            // 1

        }


    }

    private void addTotalMaterialTaken12(String cmp) {

        int a = 0;

        for (int i = 0; i < modelList.size(); i++) {
            for (int j = 0; j < modelList.size(); j++) {
                if (modelList.get(i).getConsumerName().equals(modelList.get(j).getConsumerName()) &&
                        modelList.get(i).getTeamName().equals(modelList.get(j).getTeamName()) &&
                        !modelList.get(i).getDate().equals(modelList.get(j).getDate())){
                    a = 0;
                    if (modelList.get(i).getMaterial12().equals(modelList.get(j).getMaterial12())&&
                            modelList.get(i).getUnit12().equals(modelList.get(j).getUnit12())
                            && ! modelList.get(i).getMaterial12().isEmpty()){
                        String q = addTwoString(modelList.get(i).getQuantity12(),modelList.get(j).getQuantity12());

                        addTotalMaterialTakenSpecific(modelList.get(i).getMaterial12(),cmp,modelList.get(i).getConsumerName(),
                                modelList.get(i).getTeamName(),q,modelList.get(i).getUnit12());

                    } else if (! modelList.get(i).getMaterial12().isEmpty() && !modelList.get(j).getMaterial12().isEmpty()){
                        addTotalMaterialTakenSpecific(modelList.get(i).getMaterial12(),cmp,modelList.get(i).getConsumerName(),
                                modelList.get(i).getTeamName(),modelList.get(i).getQuantity12(),modelList.get(i).getUnit12());
                    }

                }
                else {
                    a = 1;
                }
            }

            if (a == 1 && !modelList.get(i).getMaterial12().isEmpty()) {
                addTotalMaterialTakenSpecific(modelList.get(i).getMaterial12(),cmp,modelList.get(i).getConsumerName(),
                        modelList.get(i).getTeamName(),modelList.get(i).getQuantity12(),modelList.get(i).getUnit12());
            }

            // 1

        }


    }

    private void addTotalMaterialTaken13(String cmp) {

        int a = 0;

        for (int i = 0; i < modelList.size(); i++) {
            for (int j = 0; j < modelList.size(); j++) {
                if (modelList.get(i).getConsumerName().equals(modelList.get(j).getConsumerName()) &&
                        modelList.get(i).getTeamName().equals(modelList.get(j).getTeamName()) &&
                        !modelList.get(i).getDate().equals(modelList.get(j).getDate())){
                    a = 0;
                    if (modelList.get(i).getMaterial13().equals(modelList.get(j).getMaterial13())&&
                            modelList.get(i).getUnit13().equals(modelList.get(j).getUnit13())
                            && ! modelList.get(i).getMaterial13().isEmpty()){
                        String q = addTwoString(modelList.get(i).getQuantity13(),modelList.get(j).getQuantity13());

                        addTotalMaterialTakenSpecific(modelList.get(i).getMaterial13(),cmp,modelList.get(i).getConsumerName(),
                                modelList.get(i).getTeamName(),q,modelList.get(i).getUnit13());

                    } else if (! modelList.get(i).getMaterial13().isEmpty() && !modelList.get(j).getMaterial13().isEmpty()){
                        addTotalMaterialTakenSpecific(modelList.get(i).getMaterial13(),cmp,modelList.get(i).getConsumerName(),
                                modelList.get(i).getTeamName(),modelList.get(i).getQuantity13(),modelList.get(i).getUnit13());
                    }

                }
                else {
                    a = 1;
                }
            }

            if (a == 1 && !modelList.get(i).getMaterial13().isEmpty()) {
                addTotalMaterialTakenSpecific(modelList.get(i).getMaterial13(),cmp,modelList.get(i).getConsumerName(),
                        modelList.get(i).getTeamName(),modelList.get(i).getQuantity13(),modelList.get(i).getUnit13());
            }

            // 1

        }


    }

    private void addTotalMaterialTaken14(String cmp) {

        int a = 0;

        for (int i = 0; i < modelList.size(); i++) {
            for (int j = 0; j < modelList.size(); j++) {
                if (modelList.get(i).getConsumerName().equals(modelList.get(j).getConsumerName()) &&
                        modelList.get(i).getTeamName().equals(modelList.get(j).getTeamName()) &&
                        !modelList.get(i).getDate().equals(modelList.get(j).getDate())){
                    a = 0;
                    if (modelList.get(i).getMaterial14().equals(modelList.get(j).getMaterial14())&&
                            modelList.get(i).getUnit14().equals(modelList.get(j).getUnit14())
                            && ! modelList.get(i).getMaterial14().isEmpty()){
                        String q = addTwoString(modelList.get(i).getQuantity14(),modelList.get(j).getQuantity14());

                        addTotalMaterialTakenSpecific(modelList.get(i).getMaterial14(),cmp,modelList.get(i).getConsumerName(),
                                modelList.get(i).getTeamName(),q,modelList.get(i).getUnit14());

                    } else if (! modelList.get(i).getMaterial14().isEmpty() && !modelList.get(j).getMaterial14().isEmpty()){
                        addTotalMaterialTakenSpecific(modelList.get(i).getMaterial14(),cmp,modelList.get(i).getConsumerName(),
                                modelList.get(i).getTeamName(),modelList.get(i).getQuantity14(),modelList.get(i).getUnit14());
                    }

                }
                else {
                    a = 1;
                }
            }

            if (a == 1 && !modelList.get(i).getMaterial14().isEmpty()) {
                addTotalMaterialTakenSpecific(modelList.get(i).getMaterial14(),cmp,modelList.get(i).getConsumerName(),
                        modelList.get(i).getTeamName(),modelList.get(i).getQuantity14(),modelList.get(i).getUnit14());
            }

            // 1

        }


    }

    private void addTotalMaterialTaken15(String cmp) {

        int a = 0;

        for (int i = 0; i < modelList.size(); i++) {
            for (int j = 0; j < modelList.size(); j++) {
                if (modelList.get(i).getConsumerName().equals(modelList.get(j).getConsumerName()) &&
                        modelList.get(i).getTeamName().equals(modelList.get(j).getTeamName()) &&
                        !modelList.get(i).getDate().equals(modelList.get(j).getDate())){
                    a = 0;
                    if (modelList.get(i).getMaterial15().equals(modelList.get(j).getMaterial15())&&
                            modelList.get(i).getUnit15().equals(modelList.get(j).getUnit15())
                            && ! modelList.get(i).getMaterial15().isEmpty()){
                        String q = addTwoString(modelList.get(i).getQuantity15(),modelList.get(j).getQuantity15());

                        addTotalMaterialTakenSpecific(modelList.get(i).getMaterial15(),cmp,modelList.get(i).getConsumerName(),
                                modelList.get(i).getTeamName(),q,modelList.get(i).getUnit15());

                    } else if (! modelList.get(i).getMaterial15().isEmpty() && !modelList.get(j).getMaterial15().isEmpty()){
                        addTotalMaterialTakenSpecific(modelList.get(i).getMaterial15(),cmp,modelList.get(i).getConsumerName(),
                                modelList.get(i).getTeamName(),modelList.get(i).getQuantity15(),modelList.get(i).getUnit15());
                    }

                }
                else {
                    a = 1;
                }
            }

            if (a == 1 && !modelList.get(i).getMaterial15().isEmpty()) {
                addTotalMaterialTakenSpecific(modelList.get(i).getMaterial15(),cmp,modelList.get(i).getConsumerName(),
                        modelList.get(i).getTeamName(),modelList.get(i).getQuantity15(),modelList.get(i).getUnit15());
            }

            // 1

        }


    }

    private void addTotalMaterialTaken16(String cmp) {

        int a = 0;

        for (int i = 0; i < modelList.size(); i++) {
            for (int j = 0; j < modelList.size(); j++) {
                if (modelList.get(i).getConsumerName().equals(modelList.get(j).getConsumerName()) &&
                        modelList.get(i).getTeamName().equals(modelList.get(j).getTeamName()) &&
                        !modelList.get(i).getDate().equals(modelList.get(j).getDate())){
                    a = 0;
                    if (modelList.get(i).getMaterial16().equals(modelList.get(j).getMaterial16())&&
                            modelList.get(i).getUnit16().equals(modelList.get(j).getUnit16())
                            && ! modelList.get(i).getMaterial16().isEmpty()){
                        String q = addTwoString(modelList.get(i).getQuantity16(),modelList.get(j).getQuantity16());

                        addTotalMaterialTakenSpecific(modelList.get(i).getMaterial16(),cmp,modelList.get(i).getConsumerName(),
                                modelList.get(i).getTeamName(),q,modelList.get(i).getUnit16());

                    } else if (! modelList.get(i).getMaterial16().isEmpty() && !modelList.get(j).getMaterial16().isEmpty()){
                        addTotalMaterialTakenSpecific(modelList.get(i).getMaterial16(),cmp,modelList.get(i).getConsumerName(),
                                modelList.get(i).getTeamName(),modelList.get(i).getQuantity16(),modelList.get(i).getUnit16());
                    }

                }
                else {
                    a = 1;
                }
            }

            if (a == 1 && !modelList.get(i).getMaterial16().isEmpty()) {
                addTotalMaterialTakenSpecific(modelList.get(i).getMaterial16(),cmp,modelList.get(i).getConsumerName(),
                        modelList.get(i).getTeamName(),modelList.get(i).getQuantity16(),modelList.get(i).getUnit16());
            }

            // 1

        }


    }

    private void addTotalMaterialTaken17(String cmp) {

        int a = 0;

        for (int i = 0; i < modelList.size(); i++) {
            for (int j = 0; j < modelList.size(); j++) {
                if (modelList.get(i).getConsumerName().equals(modelList.get(j).getConsumerName()) &&
                        modelList.get(i).getTeamName().equals(modelList.get(j).getTeamName()) &&
                        !modelList.get(i).getDate().equals(modelList.get(j).getDate())){
                    a = 0;
                    if (modelList.get(i).getMaterial17().equals(modelList.get(j).getMaterial17())&&
                            modelList.get(i).getUnit17().equals(modelList.get(j).getUnit17())
                            && ! modelList.get(i).getMaterial17().isEmpty()){
                        String q = addTwoString(modelList.get(i).getQuantity17(),modelList.get(j).getQuantity17());

                        addTotalMaterialTakenSpecific(modelList.get(i).getMaterial17(),cmp,modelList.get(i).getConsumerName(),
                                modelList.get(i).getTeamName(),q,modelList.get(i).getUnit17());

                    } else if (! modelList.get(i).getMaterial17().isEmpty() && !modelList.get(j).getMaterial17().isEmpty()){
                        addTotalMaterialTakenSpecific(modelList.get(i).getMaterial17(),cmp,modelList.get(i).getConsumerName(),
                                modelList.get(i).getTeamName(),modelList.get(i).getQuantity17(),modelList.get(i).getUnit17());
                    }

                }
                else {
                    a = 1;
                }
            }

            if (a == 1 && !modelList.get(i).getMaterial17().isEmpty()) {
                addTotalMaterialTakenSpecific(modelList.get(i).getMaterial17(),cmp,modelList.get(i).getConsumerName(),
                        modelList.get(i).getTeamName(),modelList.get(i).getQuantity17(),modelList.get(i).getUnit17());
            }

            // 1

        }


    }

    private void addTotalMaterialTaken18(String cmp) {

        int a = 0;

        for (int i = 0; i < modelList.size(); i++) {
            for (int j = 0; j < modelList.size(); j++) {
                if (modelList.get(i).getConsumerName().equals(modelList.get(j).getConsumerName()) &&
                        modelList.get(i).getTeamName().equals(modelList.get(j).getTeamName()) &&
                        !modelList.get(i).getDate().equals(modelList.get(j).getDate())){
                    a = 0;
                    if (modelList.get(i).getMaterial18().equals(modelList.get(j).getMaterial18())&&
                            modelList.get(i).getUnit18().equals(modelList.get(j).getUnit18())
                            && ! modelList.get(i).getMaterial18().isEmpty()){
                        String q = addTwoString(modelList.get(i).getQuantity18(),modelList.get(j).getQuantity18());

                        addTotalMaterialTakenSpecific(modelList.get(i).getMaterial18(),cmp,modelList.get(i).getConsumerName(),
                                modelList.get(i).getTeamName(),q,modelList.get(i).getUnit18());

                    } else if (! modelList.get(i).getMaterial18().isEmpty() && !modelList.get(j).getMaterial18().isEmpty()){
                        addTotalMaterialTakenSpecific(modelList.get(i).getMaterial18(),cmp,modelList.get(i).getConsumerName(),
                                modelList.get(i).getTeamName(),modelList.get(i).getQuantity18(),modelList.get(i).getUnit18());
                    }

                }
                else {
                    a = 1;
                }
            }

            if (a == 1 && !modelList.get(i).getMaterial18().isEmpty()) {
                addTotalMaterialTakenSpecific(modelList.get(i).getMaterial18(),cmp,modelList.get(i).getConsumerName(),
                        modelList.get(i).getTeamName(),modelList.get(i).getQuantity18(),modelList.get(i).getUnit18());
            }

            // 1

        }


    }

    private void addTotalMaterialTaken19(String cmp) {

        int a = 0;

        for (int i = 0; i < modelList.size(); i++) {
            for (int j = 0; j < modelList.size(); j++) {
                if (modelList.get(i).getConsumerName().equals(modelList.get(j).getConsumerName()) &&
                        modelList.get(i).getTeamName().equals(modelList.get(j).getTeamName()) &&
                        !modelList.get(i).getDate().equals(modelList.get(j).getDate())){
                    a = 0;
                    if (modelList.get(i).getMaterial19().equals(modelList.get(j).getMaterial19())&&
                            modelList.get(i).getUnit19().equals(modelList.get(j).getUnit19())
                            && ! modelList.get(i).getMaterial19().isEmpty()){
                        String q = addTwoString(modelList.get(i).getQuantity19(),modelList.get(j).getQuantity19());

                        addTotalMaterialTakenSpecific(modelList.get(i).getMaterial19(),cmp,modelList.get(i).getConsumerName(),
                                modelList.get(i).getTeamName(),q,modelList.get(i).getUnit19());

                    } else if (! modelList.get(i).getMaterial19().isEmpty() && !modelList.get(j).getMaterial19().isEmpty()){
                        addTotalMaterialTakenSpecific(modelList.get(i).getMaterial19(),cmp,modelList.get(i).getConsumerName(),
                                modelList.get(i).getTeamName(),modelList.get(i).getQuantity19(),modelList.get(i).getUnit19());
                    }

                }
                else {
                    a = 1;
                }
            }

            if (a == 1 && !modelList.get(i).getMaterial19().isEmpty()) {
                addTotalMaterialTakenSpecific(modelList.get(i).getMaterial19(),cmp,modelList.get(i).getConsumerName(),
                        modelList.get(i).getTeamName(),modelList.get(i).getQuantity19(),modelList.get(i).getUnit19());
            }

            // 1

        }


    }

    private void addTotalMaterialTaken20(String cmp) {

        int a = 0;

        for (int i = 0; i < modelList.size(); i++) {
            for (int j = 0; j < modelList.size(); j++) {
                if (modelList.get(i).getConsumerName().equals(modelList.get(j).getConsumerName()) &&
                        modelList.get(i).getTeamName().equals(modelList.get(j).getTeamName()) &&
                        !modelList.get(i).getDate().equals(modelList.get(j).getDate())){
                    a = 0;
                    if (modelList.get(i).getMaterial20().equals(modelList.get(j).getMaterial20())&&
                            modelList.get(i).getUnit20().equals(modelList.get(j).getUnit20())
                            && ! modelList.get(i).getMaterial20().isEmpty()){
                        String q = addTwoString(modelList.get(i).getQuantity20(),modelList.get(j).getQuantity20());

                        addTotalMaterialTakenSpecific(modelList.get(i).getMaterial20(),cmp,modelList.get(i).getConsumerName(),
                                modelList.get(i).getTeamName(),q,modelList.get(i).getUnit20());

                    } else if (! modelList.get(i).getMaterial20().isEmpty() && !modelList.get(j).getMaterial20().isEmpty()){
                        addTotalMaterialTakenSpecific(modelList.get(i).getMaterial20(),cmp,modelList.get(i).getConsumerName(),
                                modelList.get(i).getTeamName(),modelList.get(i).getQuantity20(),modelList.get(i).getUnit20());
                    }

                }
                else {
                    a = 1;
                }
            }

            if (a == 1 && !modelList.get(i).getMaterial20().isEmpty()) {
                addTotalMaterialTakenSpecific(modelList.get(i).getMaterial20(),cmp,modelList.get(i).getConsumerName(),
                        modelList.get(i).getTeamName(),modelList.get(i).getQuantity20(),modelList.get(i).getUnit20());
            }

            // 1

        }


    }

    private void addTotalMaterialTaken21(String cmp) {

        int a = 0;

        for (int i = 0; i < modelList.size(); i++) {
            for (int j = 0; j < modelList.size(); j++) {
                if (modelList.get(i).getConsumerName().equals(modelList.get(j).getConsumerName()) &&
                        modelList.get(i).getTeamName().equals(modelList.get(j).getTeamName()) &&
                        !modelList.get(i).getDate().equals(modelList.get(j).getDate())){
                    a = 0;
                    if (modelList.get(i).getMaterial21().equals(modelList.get(j).getMaterial21())&&
                            modelList.get(i).getUnit21().equals(modelList.get(j).getUnit21())
                            && ! modelList.get(i).getMaterial21().isEmpty()){
                        String q = addTwoString(modelList.get(i).getQuantity21(),modelList.get(j).getQuantity21());

                        addTotalMaterialTakenSpecific(modelList.get(i).getMaterial21(),cmp,modelList.get(i).getConsumerName(),
                                modelList.get(i).getTeamName(),q,modelList.get(i).getUnit21());

                    } else if (! modelList.get(i).getMaterial21().isEmpty() && !modelList.get(j).getMaterial21().isEmpty()){
                        addTotalMaterialTakenSpecific(modelList.get(i).getMaterial21(),cmp,modelList.get(i).getConsumerName(),
                                modelList.get(i).getTeamName(),modelList.get(i).getQuantity21(),modelList.get(i).getUnit21());
                    }

                }
                else {
                    a = 1;
                }
            }

            if (a == 1 && !modelList.get(i).getMaterial21().isEmpty()) {
                addTotalMaterialTakenSpecific(modelList.get(i).getMaterial21(),cmp,modelList.get(i).getConsumerName(),
                        modelList.get(i).getTeamName(),modelList.get(i).getQuantity21(),modelList.get(i).getUnit21());
            }

            // 1

        }


    }

    private void addTotalMaterialTaken22(String cmp) {

        int a = 0;

        for (int i = 0; i < modelList.size(); i++) {
            for (int j = 0; j < modelList.size(); j++) {
                if (modelList.get(i).getConsumerName().equals(modelList.get(j).getConsumerName()) &&
                        modelList.get(i).getTeamName().equals(modelList.get(j).getTeamName()) &&
                        !modelList.get(i).getDate().equals(modelList.get(j).getDate())){
                    a = 0;
                    if (modelList.get(i).getMaterial22().equals(modelList.get(j).getMaterial22())&&
                            modelList.get(i).getUnit22().equals(modelList.get(j).getUnit22())
                            && ! modelList.get(i).getMaterial22().isEmpty()){
                        String q = addTwoString(modelList.get(i).getQuantity22(),modelList.get(j).getQuantity22());

                        addTotalMaterialTakenSpecific(modelList.get(i).getMaterial22(),cmp,modelList.get(i).getConsumerName(),
                                modelList.get(i).getTeamName(),q,modelList.get(i).getUnit22());

                    } else if (! modelList.get(i).getMaterial22().isEmpty() && !modelList.get(j).getMaterial22().isEmpty()){
                        addTotalMaterialTakenSpecific(modelList.get(i).getMaterial22(),cmp,modelList.get(i).getConsumerName(),
                                modelList.get(i).getTeamName(),modelList.get(i).getQuantity22(),modelList.get(i).getUnit22());
                    }

                }
                else {
                    a = 1;
                }
            }

            if (a == 1 && !modelList.get(i).getMaterial22().isEmpty()) {
                addTotalMaterialTakenSpecific(modelList.get(i).getMaterial22(),cmp,modelList.get(i).getConsumerName(),
                        modelList.get(i).getTeamName(),modelList.get(i).getQuantity22(),modelList.get(i).getUnit22());
            }

            // 1

        }


    }

    private void addTotalMaterialTaken23(String cmp) {

        int a = 0;

        for (int i = 0; i < modelList.size(); i++) {
            for (int j = 0; j < modelList.size(); j++) {
                if (modelList.get(i).getConsumerName().equals(modelList.get(j).getConsumerName()) &&
                        modelList.get(i).getTeamName().equals(modelList.get(j).getTeamName()) &&
                        !modelList.get(i).getDate().equals(modelList.get(j).getDate())){
                    a = 0;
                    if (modelList.get(i).getMaterial23().equals(modelList.get(j).getMaterial23())&&
                            modelList.get(i).getUnit23().equals(modelList.get(j).getUnit23())
                            && ! modelList.get(i).getMaterial23().isEmpty()){
                        String q = addTwoString(modelList.get(i).getQuantity23(),modelList.get(j).getQuantity23());

                        addTotalMaterialTakenSpecific(modelList.get(i).getMaterial23(),cmp,modelList.get(i).getConsumerName(),
                                modelList.get(i).getTeamName(),q,modelList.get(i).getUnit23());

                    } else if (! modelList.get(i).getMaterial23().isEmpty() && !modelList.get(j).getMaterial23().isEmpty()){
                        addTotalMaterialTakenSpecific(modelList.get(i).getMaterial23(),cmp,modelList.get(i).getConsumerName(),
                                modelList.get(i).getTeamName(),modelList.get(i).getQuantity23(),modelList.get(i).getUnit23());
                    }

                }
                else {
                    a = 1;
                }
            }

            if (a == 1 && !modelList.get(i).getMaterial23().isEmpty()) {
                addTotalMaterialTakenSpecific(modelList.get(i).getMaterial23(),cmp,modelList.get(i).getConsumerName(),
                        modelList.get(i).getTeamName(),modelList.get(i).getQuantity23(),modelList.get(i).getUnit23());
            }

            // 1

        }


    }

    private void addTotalMaterialTaken24(String cmp) {

        int a = 0;

        for (int i = 0; i < modelList.size(); i++) {
            for (int j = 0; j < modelList.size(); j++) {
                if (modelList.get(i).getConsumerName().equals(modelList.get(j).getConsumerName()) &&
                        modelList.get(i).getTeamName().equals(modelList.get(j).getTeamName()) &&
                        !modelList.get(i).getDate().equals(modelList.get(j).getDate())){
                    a = 0;
                    if (modelList.get(i).getMaterial24().equals(modelList.get(j).getMaterial24())&&
                            modelList.get(i).getUnit24().equals(modelList.get(j).getUnit24())
                            && ! modelList.get(i).getMaterial24().isEmpty()){
                        String q = addTwoString(modelList.get(i).getQuantity24(),modelList.get(j).getQuantity24());

                        addTotalMaterialTakenSpecific(modelList.get(i).getMaterial24(),cmp,modelList.get(i).getConsumerName(),
                                modelList.get(i).getTeamName(),q,modelList.get(i).getUnit24());

                    } else if (! modelList.get(i).getMaterial24().isEmpty() && !modelList.get(j).getMaterial24().isEmpty()){
                        addTotalMaterialTakenSpecific(modelList.get(i).getMaterial24(),cmp,modelList.get(i).getConsumerName(),
                                modelList.get(i).getTeamName(),modelList.get(i).getQuantity24(),modelList.get(i).getUnit24());
                    }

                }
                else {
                    a = 1;
                }
            }

            if (a == 1 && !modelList.get(i).getMaterial24().isEmpty()) {
                addTotalMaterialTakenSpecific(modelList.get(i).getMaterial24(),cmp,modelList.get(i).getConsumerName(),
                        modelList.get(i).getTeamName(),modelList.get(i).getQuantity24(),modelList.get(i).getUnit24());
            }

            // 1

        }


    }

    private void addTotalMaterialTaken25(String cmp) {

        int a = 0;

        for (int i = 0; i < modelList.size(); i++) {
            for (int j = 0; j < modelList.size(); j++) {
                if (modelList.get(i).getConsumerName().equals(modelList.get(j).getConsumerName()) &&
                        modelList.get(i).getTeamName().equals(modelList.get(j).getTeamName()) &&
                        !modelList.get(i).getDate().equals(modelList.get(j).getDate())){
                    a = 0;
                    if (modelList.get(i).getMaterial25().equals(modelList.get(j).getMaterial25())&&
                            modelList.get(i).getUnit25().equals(modelList.get(j).getUnit25())
                            && ! modelList.get(i).getMaterial25().isEmpty()){
                        String q = addTwoString(modelList.get(i).getQuantity25(),modelList.get(j).getQuantity25());

                        addTotalMaterialTakenSpecific(modelList.get(i).getMaterial25(),cmp,modelList.get(i).getConsumerName(),
                                modelList.get(i).getTeamName(),q,modelList.get(i).getUnit25());

                    } else if (! modelList.get(i).getMaterial25().isEmpty() && !modelList.get(j).getMaterial25().isEmpty()){
                        addTotalMaterialTakenSpecific(modelList.get(i).getMaterial25(),cmp,modelList.get(i).getConsumerName(),
                                modelList.get(i).getTeamName(),modelList.get(i).getQuantity25(),modelList.get(i).getUnit25());
                    }

                }
                else {
                    a = 1;
                }
            }

            if (a == 1 && !modelList.get(i).getMaterial25().isEmpty()) {
                addTotalMaterialTakenSpecific(modelList.get(i).getMaterial25(),cmp,modelList.get(i).getConsumerName(),
                        modelList.get(i).getTeamName(),modelList.get(i).getQuantity25(),modelList.get(i).getUnit25());
            }

            // 1

        }


    }

    private void addTotalMaterialTaken26(String cmp) {

        int a = 0;

        for (int i = 0; i < modelList.size(); i++) {
            for (int j = 0; j < modelList.size(); j++) {
                if (modelList.get(i).getConsumerName().equals(modelList.get(j).getConsumerName()) &&
                        modelList.get(i).getTeamName().equals(modelList.get(j).getTeamName()) &&
                        !modelList.get(i).getDate().equals(modelList.get(j).getDate())){
                    a = 0;
                    if (modelList.get(i).getMaterial26().equals(modelList.get(j).getMaterial26())&&
                            modelList.get(i).getUnit26().equals(modelList.get(j).getUnit26())
                            && ! modelList.get(i).getMaterial26().isEmpty()){
                        String q = addTwoString(modelList.get(i).getQuantity26(),modelList.get(j).getQuantity26());

                        addTotalMaterialTakenSpecific(modelList.get(i).getMaterial26(),cmp,modelList.get(i).getConsumerName(),
                                modelList.get(i).getTeamName(),q,modelList.get(i).getUnit26());

                    } else if (! modelList.get(i).getMaterial26().isEmpty() && !modelList.get(j).getMaterial26().isEmpty()){
                        addTotalMaterialTakenSpecific(modelList.get(i).getMaterial26(),cmp,modelList.get(i).getConsumerName(),
                                modelList.get(i).getTeamName(),modelList.get(i).getQuantity26(),modelList.get(i).getUnit26());
                    }

                }
                else {
                    a = 1;
                }
            }

            if (a == 1 && !modelList.get(i).getMaterial26().isEmpty()) {
                addTotalMaterialTakenSpecific(modelList.get(i).getMaterial26(),cmp,modelList.get(i).getConsumerName(),
                        modelList.get(i).getTeamName(),modelList.get(i).getQuantity26(),modelList.get(i).getUnit26());
            }

            // 1

        }


    }

    private void addTotalMaterialTaken27(String cmp) {

        int a = 0;

        for (int i = 0; i < modelList.size(); i++) {
            for (int j = 0; j < modelList.size(); j++) {
                if (modelList.get(i).getConsumerName().equals(modelList.get(j).getConsumerName()) &&
                        modelList.get(i).getTeamName().equals(modelList.get(j).getTeamName()) &&
                        !modelList.get(i).getDate().equals(modelList.get(j).getDate())){
                    a = 0;
                    if (modelList.get(i).getMaterial27().equals(modelList.get(j).getMaterial27())&&
                            modelList.get(i).getUnit27().equals(modelList.get(j).getUnit27())
                            && ! modelList.get(i).getMaterial27().isEmpty()){
                        String q = addTwoString(modelList.get(i).getQuantity27(),modelList.get(j).getQuantity27());

                        addTotalMaterialTakenSpecific(modelList.get(i).getMaterial27(),cmp,modelList.get(i).getConsumerName(),
                                modelList.get(i).getTeamName(),q,modelList.get(i).getUnit27());

                    } else if (! modelList.get(i).getMaterial27().isEmpty() && !modelList.get(j).getMaterial27().isEmpty()){
                        addTotalMaterialTakenSpecific(modelList.get(i).getMaterial27(),cmp,modelList.get(i).getConsumerName(),
                                modelList.get(i).getTeamName(),modelList.get(i).getQuantity27(),modelList.get(i).getUnit27());
                    }

                }
                else {
                    a = 1;
                }
            }

            if (a == 1 && !modelList.get(i).getMaterial27().isEmpty()) {
                addTotalMaterialTakenSpecific(modelList.get(i).getMaterial27(),cmp,modelList.get(i).getConsumerName(),
                        modelList.get(i).getTeamName(),modelList.get(i).getQuantity27(),modelList.get(i).getUnit27());
            }

            // 1

        }


    }

    private void addTotalMaterialTaken28(String cmp) {

        int a = 0;

        for (int i = 0; i < modelList.size(); i++) {
            for (int j = 0; j < modelList.size(); j++) {
                if (modelList.get(i).getConsumerName().equals(modelList.get(j).getConsumerName()) &&
                        modelList.get(i).getTeamName().equals(modelList.get(j).getTeamName()) &&
                        !modelList.get(i).getDate().equals(modelList.get(j).getDate())){
                    a = 0;
                    if (modelList.get(i).getMaterial28().equals(modelList.get(j).getMaterial28())&&
                            modelList.get(i).getUnit28().equals(modelList.get(j).getUnit28())
                            && ! modelList.get(i).getMaterial28().isEmpty()){
                        String q = addTwoString(modelList.get(i).getQuantity28(),modelList.get(j).getQuantity28());

                        addTotalMaterialTakenSpecific(modelList.get(i).getMaterial28(),cmp,modelList.get(i).getConsumerName(),
                                modelList.get(i).getTeamName(),q,modelList.get(i).getUnit28());

                    } else if (! modelList.get(i).getMaterial28().isEmpty() && !modelList.get(j).getMaterial28().isEmpty()){
                        addTotalMaterialTakenSpecific(modelList.get(i).getMaterial28(),cmp,modelList.get(i).getConsumerName(),
                                modelList.get(i).getTeamName(),modelList.get(i).getQuantity28(),modelList.get(i).getUnit28());
                    }

                }
                else {
                    a = 1;
                }
            }

            if (a == 1 && !modelList.get(i).getMaterial28().isEmpty()) {
                addTotalMaterialTakenSpecific(modelList.get(i).getMaterial28(),cmp,modelList.get(i).getConsumerName(),
                        modelList.get(i).getTeamName(),modelList.get(i).getQuantity28(),modelList.get(i).getUnit28());
            }

            // 1

        }


    }

    private void addTotalMaterialTaken29(String cmp) {

        int a = 0;

        for (int i = 0; i < modelList.size(); i++) {
            for (int j = 0; j < modelList.size(); j++) {
                if (modelList.get(i).getConsumerName().equals(modelList.get(j).getConsumerName()) &&
                        modelList.get(i).getTeamName().equals(modelList.get(j).getTeamName()) &&
                        !modelList.get(i).getDate().equals(modelList.get(j).getDate())){
                    a = 0;
                    if (modelList.get(i).getMaterial29().equals(modelList.get(j).getMaterial29())&&
                            modelList.get(i).getUnit29().equals(modelList.get(j).getUnit29())
                            && ! modelList.get(i).getMaterial29().isEmpty()){
                        String q = addTwoString(modelList.get(i).getQuantity29(),modelList.get(j).getQuantity29());

                        addTotalMaterialTakenSpecific(modelList.get(i).getMaterial29(),cmp,modelList.get(i).getConsumerName(),
                                modelList.get(i).getTeamName(),q,modelList.get(i).getUnit29());

                    } else if (! modelList.get(i).getMaterial29().isEmpty() && !modelList.get(j).getMaterial29().isEmpty()){
                        addTotalMaterialTakenSpecific(modelList.get(i).getMaterial29(),cmp,modelList.get(i).getConsumerName(),
                                modelList.get(i).getTeamName(),modelList.get(i).getQuantity29(),modelList.get(i).getUnit29());
                    }

                }
                else {
                    a = 1;
                }
            }

            if (a == 1 && !modelList.get(i).getMaterial29().isEmpty()) {
                addTotalMaterialTakenSpecific(modelList.get(i).getMaterial29(),cmp,modelList.get(i).getConsumerName(),
                        modelList.get(i).getTeamName(),modelList.get(i).getQuantity29(),modelList.get(i).getUnit29());
            }

            // 1

        }


    }

    private void addTotalMaterialTaken30(String cmp) {

        int a = 0;

        for (int i = 0; i < modelList.size(); i++) {
            for (int j = 0; j < modelList.size(); j++) {
                if (modelList.get(i).getConsumerName().equals(modelList.get(j).getConsumerName()) &&
                        modelList.get(i).getTeamName().equals(modelList.get(j).getTeamName()) &&
                        !modelList.get(i).getDate().equals(modelList.get(j).getDate())){
                    a = 0;
                    if (modelList.get(i).getMaterial30().equals(modelList.get(j).getMaterial30())&&
                            modelList.get(i).getUnit30().equals(modelList.get(j).getUnit30())
                            && ! modelList.get(i).getMaterial30().isEmpty()){
                        String q = addTwoString(modelList.get(i).getQuantity30(),modelList.get(j).getQuantity30());

                        addTotalMaterialTakenSpecific(modelList.get(i).getMaterial30(),cmp,modelList.get(i).getConsumerName(),
                                modelList.get(i).getTeamName(),q,modelList.get(i).getUnit30());

                    } else if (! modelList.get(i).getMaterial30().isEmpty() && !modelList.get(j).getMaterial30().isEmpty()){
                        addTotalMaterialTakenSpecific(modelList.get(i).getMaterial30(),cmp,modelList.get(i).getConsumerName(),
                                modelList.get(i).getTeamName(),modelList.get(i).getQuantity30(),modelList.get(i).getUnit30());
                    }

                }
                else {
                    a = 1;
                }
            }

            if (a == 1 && !modelList.get(i).getMaterial30().isEmpty()) {
                addTotalMaterialTakenSpecific(modelList.get(i).getMaterial30(),cmp,modelList.get(i).getConsumerName(),
                        modelList.get(i).getTeamName(),modelList.get(i).getQuantity30(),modelList.get(i).getUnit30());
            }

            // 1

        }


    }


    private String addTwoString(String a,String b){

        float f1 = a.isEmpty() ? 0.0f : Float.parseFloat(a);
        float f2 = b.isEmpty() ? 0.0f : Float.parseFloat(b);
        return f1+f2==0.0f ? String.valueOf(0) : String.valueOf(f1+f2);
    }


    private  void addMaterialData(String cmp){
        for (int i=0;i<modelList.size();i++){
            addTotalMaterialTakenByUser(cmp,
                    modelList.get(i).getDate(),
                    modelList.get(i).getTeamName(),
                    modelList.get(i).getLine(),
                    modelList.get(i).getTender(),
                    modelList.get(i).getDriverName(),
                    modelList.get(i).getVehicalName(),
                    modelList.get(i).getConsumerName(),
                    modelList.get(i).getSite(),
                    modelList.get(i).getMaterialReceiverName(),
                    modelList.get(i).getCenter(),
                    modelList.get(i).getVillage()
            );
        }
    }


    private void addTotalMaterialTakenByUser( String cmp, String uDate, String uTeamName, String uLine, String uTender, String uDriverName,
                                              String uVehicalName, String uConsumerName, String uSite, String materialReceiver,
                                              String centerS, String villageS) {
        Map<String, Object> doc = new HashMap<>();
        doc.put("Id",uConsumerName+" "+uTeamName);

        doc.put("Date",uDate);
        doc.put("Team Name",uTeamName);
        doc.put("Line",uLine);
        doc.put("Tender",uTender);
        doc.put("Driver Name",uDriverName);
        doc.put("Vehical Name",uVehicalName);
        doc.put("Consumer Name",uConsumerName);
        doc.put("Site Name",uSite);

        doc.put("Material Receiver Name",materialReceiver);
        doc.put("Center",centerS);
        doc.put("Village",villageS);

        pd1.show();

        fStore.collection(cmp+" BalanceMaterial")
                .document(uConsumerName+" "+uTeamName)
                .set(doc).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //this will be called when data is added Successfully
                        Toast.makeText(getApplicationContext(), "Total Material Added", Toast.LENGTH_SHORT).show();
                        pd1.dismiss();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //this will be called when data is added Failed
                        Toast.makeText(getApplicationContext(), "Failed to add data "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        pd1.dismiss();
                    }
                });


    }

    private void addTotalMaterialTakenSpecific(String material, String cmp, String consumerName, String teamName, String quantity,String unit) {
        Map<String, Object> doc = new HashMap<>();
        doc.put("Id",material);
        doc.put("Material",material);
        doc.put("Unit",unit);
        doc.put("Quantity",quantity);

        pd1.show();

        fStore.collection(cmp+" BalanceMaterial")
                .document(consumerName+" "+teamName)

                .collection("MaterialDetails")
                .document(material+" ")
                .set(doc).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //this will be called when data is added Successfully
                        Toast.makeText(getApplicationContext(), "Total Material Added", Toast.LENGTH_SHORT).show();
                        pd1.dismiss();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //this will be called when data is added Failed
                        Toast.makeText(getApplicationContext(), "Failed to add data "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        pd1.dismiss();
                    }
                });

    }



    private void searchCenter(String cmp, String center) {
        pd.setTitle("Searching...");
        pd.show();

        fStore.collection(cmp+" RemainingMaterial").whereEqualTo("Center",center)
                .orderBy("Date", Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        modelList.clear();
                        pd.dismiss();
                        for (DocumentSnapshot doc : queryDocumentSnapshots){
                            ReaminingMaterialModel model = new ReaminingMaterialModel(
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
                        adapter = new RemainingMaterialCustomAdapter(RemainingMaterial.this,modelList);
                        recyclerView.setAdapter(adapter);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();

                    }
                });
    }

    private void searchVillage(String cmp, String village) {
        pd.setTitle("Searching...");
        pd.show();
        fStore.collection(cmp+" RemainingMaterial").whereEqualTo("Village",village)
                .orderBy("Date", Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        modelList.clear();
                        pd.dismiss();
                        for (DocumentSnapshot doc : queryDocumentSnapshots){
                            ReaminingMaterialModel model = new ReaminingMaterialModel(
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
                        adapter = new RemainingMaterialCustomAdapter(RemainingMaterial.this,modelList);
                        recyclerView.setAdapter(adapter);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();

                    }
                });
    }

    private void searchTender(String cmp, String tenderS) {
        pd.setTitle("Searching...");
        pd.show();
        fStore.collection(cmp+" RemainingMaterial").whereEqualTo("Tender",tenderS)
                .orderBy("Date", Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        modelList.clear();
                        pd.dismiss();
                        for (DocumentSnapshot doc : queryDocumentSnapshots){
                            ReaminingMaterialModel model = new ReaminingMaterialModel(
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
                        adapter = new RemainingMaterialCustomAdapter(RemainingMaterial.this,modelList);
                        recyclerView.setAdapter(adapter);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();

                    }
                });
    }

    private void showMessage(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }

    private void searchTeamName(String cmp, String teamNameS) {
        pd.setTitle("Searching...");
        pd.show();
        fStore.collection(cmp+" RemainingMaterial").whereEqualTo("SearchTeamName",teamNameS.toLowerCase())
                .orderBy("Date", Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        modelList.clear();
                        pd.dismiss();
                        for (DocumentSnapshot doc : queryDocumentSnapshots){
                            ReaminingMaterialModel model = new ReaminingMaterialModel(
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
                        adapter = new RemainingMaterialCustomAdapter(RemainingMaterial.this,modelList);
                        recyclerView.setAdapter(adapter);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();

                    }
                });

    }

    private void searchDate(String cmp, String date,String date1) {

        pd.setTitle("Searching...");
        pd.show();
        fStore.collection(cmp+" RemainingMaterial")
                .orderBy("Date", Query.Direction.DESCENDING)
                .startAt(date1).endAt(date)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        modelList.clear();
                        pd.dismiss();
                        for (DocumentSnapshot doc : task.getResult()){
                            ReaminingMaterialModel model = new ReaminingMaterialModel(
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
                        adapter = new RemainingMaterialCustomAdapter(RemainingMaterial.this,modelList);
                        recyclerView.setAdapter(adapter);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();

                    }
                });





    }

    private void showMaterial() {

        DocumentReference documentReference = fStore.collection("Users")
                .document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                companyEmail = value.getString("companyEmail");
                String cmp = companyEmail;


                fStore.collection(cmp+" Material").get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                list.clear();
                                pd.dismiss();
                                for (DocumentSnapshot doc : task.getResult()){
                                    RemainingModel model = new RemainingModel(
                                            doc.getString("id"),
                                            doc.getString("Material")
                                    );
                                    list.add(model);
                                }
                                materialCustomAdapter = new RemainingCustomAdapter(RemainingMaterial.this,list);
                                recycler.setAdapter(materialCustomAdapter);

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                pd.dismiss();
                                Toast.makeText(getApplicationContext(), "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

//                return false;
            }

        });
    }

    private void showData() {
        pd.setTitle("Loading Data...");
        pd.show();

        DocumentReference documentReference = fStore.collection("Users")
                .document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                companyEmail = value.getString("companyEmail");
                String cmp = companyEmail;

                fStore.collection(cmp+" RemainingMaterial")
                        .limitToLast(384).orderBy("Date", Query.Direction.DESCENDING)
                        .get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                modelList.clear();
                                pd.dismiss();
                                for (DocumentSnapshot doc : queryDocumentSnapshots){
                                    ReaminingMaterialModel model = new ReaminingMaterialModel(
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
                                adapter = new RemainingMaterialCustomAdapter(RemainingMaterial.this,modelList);
                                recyclerView.setAdapter(adapter);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                pd.dismiss();
                                Toast.makeText(getApplicationContext(), "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
//                return false;
            }
        });

    }

    public void deleteData(int position) {
        pd.setTitle("Deleting Data...");
        pd.show();


        DocumentReference documentReference = fStore.collection("Users")
                .document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                companyEmail = value.getString("companyEmail");
                String cmp = companyEmail;

                fStore.collection(cmp+" RemainingMaterial").document(modelList.get(position).getId())
                        .delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                pd.dismiss();
                                Toast.makeText(RemainingMaterial.this,"Data Deleted",Toast.LENGTH_SHORT).show();
                                showData();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });

//                return false;
            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            createXlFile();
        } else {
            Toast.makeText(getApplicationContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("SetTextI18n")
    private void createXlFile() {

        excel();
        Workbook wb = new HSSFWorkbook();
        Cell cell = null;
        Sheet sheet;
        sheet = wb.createSheet("Used Material List");

        excel1(cell, sheet);

        excel2(wb, cell, sheet);

        String folderName = "UrjaVahini";
        String fileName = "Used Material List" + System.currentTimeMillis() + ".xls";
        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + File.separator + folderName + File.separator + fileName;

        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + File.separator + folderName);
        if (!file.exists()) {
            file.mkdirs();
        }

        FileOutputStream outputStream = null;

        try {
            outputStream = new FileOutputStream(path);
            wb.write(outputStream);
            // ShareViaEmail(file.getParentFile().getName(),file.getName());
            Toast.makeText(getApplicationContext(), "Excel Created in " + path, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Not OK " + e.getMessage(), Toast.LENGTH_LONG).show();
            try {
                outputStream.close();
                Toast.makeText(getApplicationContext(), "Sorry  " + e.getMessage(), Toast.LENGTH_LONG).show();

            } catch (Exception ex) {
                ex.printStackTrace();
                Toast.makeText(getApplicationContext(), "Error " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }

    private void excel(){

        for (int i=0;i<list.size();i++){
            arr[i] = list.get(i).getMaterial();
        }

    }

    private void excel1(Cell cell,Sheet sheet) {


        //Now column and row
        Row row = sheet.createRow(0);

        cell = row.createCell(0);
        cell.setCellValue("Date");

        cell = row.createCell(1);
        cell.setCellValue("Team Name");

        cell = row.createCell(2);
        cell.setCellValue("Line");

        cell = row.createCell(3);
        cell.setCellValue("Tender Name");

        cell = row.createCell(4);
        cell.setCellValue("Driver Name");

        cell = row.createCell(5);
        cell.setCellValue("Vehical Name");

        cell = row.createCell(6);
        cell.setCellValue("Consumer Name");

        cell = row.createCell(7);
        cell.setCellValue("Site Name");

        cell = row.createCell(8);
        cell.setCellValue("Material Receiver Name");

        cell = row.createCell(9);
        cell.setCellValue("Center");

        cell = row.createCell(10);
        cell.setCellValue("Village");

        for (int i=0;i<arr.length;i++){
            int j = 11+i;
            cell = row.createCell(j);
            cell.setCellValue(arr[i]);
        }

        for (int i=0;i<arr.length+10;i++){
            sheet.setColumnWidth(i, (30 * 200));
        }


    }

    private void excel2(Workbook wb, Cell cell, Sheet sheet) {
        for (int i = 0; i < modelList.size(); i++) {

            Row row1 = sheet.createRow(i + 1);

            cell = row1.createCell(0);
            cell.setCellValue(modelList.get(i).getDate());

            cell = row1.createCell(1);
            cell.setCellValue((modelList.get(i).getTeamName()));

            // cell.setCellStyle(cellStyle);

            cell = row1.createCell(2);
            cell.setCellValue(modelList.get(i).getLine());

            cell = row1.createCell(3);
            cell.setCellValue(modelList.get(i).getTender());

            cell = row1.createCell(4);
            cell.setCellValue(modelList.get(i).getDriverName());

            cell = row1.createCell(5);
            cell.setCellValue(modelList.get(i).getVehicalName());

            cell = row1.createCell(6);
            cell.setCellValue(modelList.get(i).getConsumerName());

            cell = row1.createCell(7);
            cell.setCellValue(modelList.get(i).getSite());

            cell = row1.createCell(8);
            cell.setCellValue(modelList.get(i).getMaterialReceiverName());

            cell = row1.createCell(9);
            cell.setCellValue(modelList.get(i).getCenter());

            cell = row1.createCell(10);
            cell.setCellValue(modelList.get(i).getVillage());


            for(int j=0;j<arr.length;j++){

                int k = 11+j;

                if (arr[j].equals(modelList.get(i).getMaterial1())){
                    cell = row1.createCell(k);
                    cell.setCellValue((modelList.get(i).getQuantity1()));
                }
                else if (arr[j].equals(modelList.get(i).getMaterial2())){
                    cell = row1.createCell(k);
                    cell.setCellValue((modelList.get(i).getUnit2()));
                }
                else if (arr[j].equals(modelList.get(i).getMaterial3())){

                    cell = row1.createCell(k);
                    cell.setCellValue((modelList.get(i).getQuantity3()));
                }
                else if (arr[j].equals(modelList.get(i).getMaterial4())){
                    cell = row1.createCell(k);
                    cell.setCellValue((modelList.get(i).getQuantity4()));
                }
                else if (arr[j].equals(modelList.get(i).getMaterial5())){
                    cell = row1.createCell(k);
                    cell.setCellValue((modelList.get(i).getQuantity5()));
                }
                else if (arr[j].equals(modelList.get(i).getMaterial6())){
                    cell = row1.createCell(k);
                    cell.setCellValue((modelList.get(i).getQuantity6()));
                }
                else if (arr[j].equals(modelList.get(i).getMaterial7())){
                    cell = row1.createCell(k);
                    cell.setCellValue((modelList.get(i).getQuantity7()));
                }
                else if (arr[j].equals(modelList.get(i).getMaterial8())){
                    cell = row1.createCell(k);
                    cell.setCellValue((modelList.get(i).getQuantity8()));
                }
                else if (arr[j].equals(modelList.get(i).getMaterial9())){
                    cell = row1.createCell(k);
                    cell.setCellValue((modelList.get(i).getQuantity9()));
                }
                else if (arr[j].equals(modelList.get(i).getMaterial10())){
                    cell = row1.createCell(k);
                    cell.setCellValue((modelList.get(i).getQuantity10()));
                }
                else if (arr[j].equals(modelList.get(i).getMaterial11())){
                    cell = row1.createCell(k);
                    cell.setCellValue((modelList.get(i).getQuantity11()));
                }
                else if (arr[j].equals(modelList.get(i).getMaterial12())){

                    cell = row1.createCell(k);
                    cell.setCellValue((modelList.get(i).getQuantity12()));
                }
                else if (arr[j].equals(modelList.get(i).getMaterial13())){

                    cell = row1.createCell(k);
                    cell.setCellValue((modelList.get(i).getQuantity13()));
                }
                else if (arr[j].equals(modelList.get(i).getMaterial14())){
                    cell = row1.createCell(k);
                    cell.setCellValue((modelList.get(i).getQuantity14()));
                }
                else if (arr[j].equals(modelList.get(i).getMaterial15())){
                    cell = row1.createCell(k);
                    cell.setCellValue((modelList.get(i).getQuantity15()));
                }
                else if (arr[j].equals(modelList.get(i).getMaterial16())){
                    cell = row1.createCell(k);
                    cell.setCellValue((modelList.get(i).getQuantity16()));
                }
                else if (arr[j].equals(modelList.get(i).getMaterial17())){
                    cell = row1.createCell(k);
                    cell.setCellValue((modelList.get(i).getQuantity17()));
                }
                else if (arr[j].equals(modelList.get(i).getMaterial18())){
                    cell = row1.createCell(k);
                    cell.setCellValue((modelList.get(i).getQuantity18()));
                }
                else if (arr[j].equals(modelList.get(i).getMaterial19())){
                    cell = row1.createCell(k);
                    cell.setCellValue((modelList.get(i).getQuantity19()));
                }
                else if (arr[j].equals(modelList.get(i).getMaterial20())){
                    cell = row1.createCell(k);
                    cell.setCellValue((modelList.get(i).getQuantity20()));
                }
                else if (arr[j].equals(modelList.get(i).getMaterial21())){
                    cell = row1.createCell(k);
                    cell.setCellValue((modelList.get(i).getQuantity21()));
                }
                else if (arr[j].equals(modelList.get(i).getMaterial22())){

                    cell = row1.createCell(k);
                    cell.setCellValue((modelList.get(i).getQuantity22()));
                }
                else if (arr[j].equals(modelList.get(i).getMaterial23())){
                    cell = row1.createCell(k);
                    cell.setCellValue((modelList.get(i).getQuantity23()));
                }
                else if (arr[j].equals(modelList.get(i).getMaterial24())){
                    cell = row1.createCell(k);
                    cell.setCellValue((modelList.get(i).getQuantity24()));
                }
                else if (arr[j].equals(modelList.get(i).getMaterial25())){
                    cell = row1.createCell(k);
                    cell.setCellValue((modelList.get(i).getQuantity25()));
                }
                else if (arr[j].equals(modelList.get(i).getMaterial26())){
                    cell = row1.createCell(k);
                    cell.setCellValue((modelList.get(i).getQuantity26()));
                }
                else if (arr[j].equals(modelList.get(i).getMaterial27())){
                    cell = row1.createCell(k);
                    cell.setCellValue((modelList.get(i).getQuantity27()));
                }
                else if (arr[j].equals(modelList.get(i).getMaterial28())){
                    cell = row1.createCell(k);
                    cell.setCellValue((modelList.get(i).getQuantity28()));
                }
                else if (arr[j].equals(modelList.get(i).getMaterial29())){
                    cell = row1.createCell(k);
                    cell.setCellValue((modelList.get(i).getQuantity29()));
                }
                else if (arr[j].equals(modelList.get(i).getMaterial30())){
                    cell = row1.createCell(k);
                    cell.setCellValue((modelList.get(i).getQuantity30()));
                }

            }


            for (int j=0;j<arr.length+10;j++){
                sheet.setColumnWidth(i, (20 * 200));
            }

        }
    }


    public void retrieveData(){
        listener = databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
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

}