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
import com.google.android.material.button.MaterialButton;
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
import java.util.List;

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

    MaterialButton con;

    String []a;

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

        con = findViewById(R.id.con);


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


                con.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (int i=0;i<modelList.size();i++){
                            addDataConsumer(modelList.get(i).getConsumerName());
                        }
                    }
                });






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


            }



        });


        
    }


    private void addDataConsumer(String consumerName) {

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
        fStore.collection(cmp+" RemainingMaterial").orderBy("Date", Query.Direction.DESCENDING).startAt(date1).endAt(date)
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

                fStore.collection(cmp+" RemainingMaterial").orderBy("Date", Query.Direction.DESCENDING).get()
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