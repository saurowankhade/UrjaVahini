package com.example.electricalmaterial;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.SearchView;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
import com.google.firebase.firestore.QueryDocumentSnapshot;
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

public class AdminViewMaterial extends AppCompatActivity {

    List<AdminModel> modelList = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    AdminCustomAdapter adapter;
    FloatingActionButton mAddBtn;

    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String companyEmail;
    String userId;

    ProgressDialog pd;


    //Material
    List<MaterialModel> list = new ArrayList<>();
    RecyclerView recycler;
    RecyclerView.LayoutManager layout;
    MaterialCustomAdapter materialCustomAdapter;




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

    TextInputLayout teamNameTIL, tenderTIL,centerTIL, villageTIL;


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



    //Stock Material
    List<AddStockModel> modelListStock = new ArrayList<>();
    RecyclerView recyclerViewStock;
    RecyclerView.LayoutManager layoutManagerStock;
    AdminViewDeleteStockAdapter adapterStock;

    //Kg to no
    List<KgToNoModel> modelListKN = new ArrayList<>();
    RecyclerView recyclerViewKN;
    RecyclerView.LayoutManager layoutManagerKN;
    AdminViewKgToNoAdapter adapterStockKN;




    String [] arr;
    String [] ma;

    SearchView searchView;


    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_material);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        fStore = FirebaseFirestore.getInstance();
        pd = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = mAuth.getCurrentUser().getUid();


        ActionBar bar = getSupportActionBar();
        assert bar != null;
        bar.setTitle("MATERIAL UPDATION");






        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        modelList.clear();


        mAddBtn = findViewById(R.id.addBtn);
        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ma = new String[modelList.size()];
                for (int i=0;i<modelList.size();i++){
                    ma[i] = modelList.get(i).getMaterial1() +"\t\t"+ modelList.get(i).getUnit1() + "\t\t"+modelList.get(i).getQuantity1();
                }


                Toast.makeText(AdminViewMaterial.this, ma[0], Toast.LENGTH_LONG).show();



                arr = new String[list.size()];


                AlertDialog.Builder builder = new AlertDialog.Builder(AdminViewMaterial.this);
                String[] options = {"Export to Excel", "Add"};
                builder.setTitle("Select any one ");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (which == 0) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                                    checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                            }
                            else {
                                //your cod
                                createXlFile();
                            }

                        }


                        else {
                            startActivity(new Intent(getApplicationContext(), AddMaterial.class));
                            finish();
                        }
                    }
                }).create().show();


            }
        });

        refreshLayout = findViewById(R.id.refresh);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                showData();
                refreshLayout.setRefreshing(false);
            }
        });


        showData();




        searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                ArrayList<AdminModel> models = new ArrayList<>();
                for (AdminModel model : modelList){
                    if (model.getDate().toLowerCase().contains(s.toLowerCase())){
                        models.add(model);
                    }
                    else if (model.getTeamName().toLowerCase().contains(s.toLowerCase())){
                        models.add(model);
                    }
                    else if (model.getTender().toLowerCase().contains(s.toLowerCase())){
                        models.add(model);
                    }
                    else if (model.getConsumerName().toLowerCase().contains(s.toLowerCase())){
                        models.add(model);
                    }
                    else if (model.getCenter().toLowerCase().contains(s.toLowerCase())){
                        models.add(model);
                    }
                    else if (model.getSite().toLowerCase().contains(s.toLowerCase())){
                        models.add(model);
                    }
                }

                AdminCustomAdapter adminCustomAdapter = new AdminCustomAdapter(AdminViewMaterial.this,models);
                recyclerView.setAdapter(adminCustomAdapter);

                return true;
            }
        });





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



                //Stock Material
                recyclerViewStock = findViewById(R.id.recyclerAM);
                recyclerViewStock.setHasFixedSize(true);
                layoutManagerStock = new LinearLayoutManager(AdminViewMaterial.this);
                recyclerViewStock.setLayoutManager(layoutManagerStock);
                modelListStock.clear();
                showStock(cmp);


                //Stock Material
                recyclerViewKN = findViewById(R.id.recyclerKN);
                recyclerViewKN.setHasFixedSize(true);
                layoutManagerKN = new LinearLayoutManager(AdminViewMaterial.this);
                recyclerViewKN.setLayoutManager(layoutManagerKN);
                modelListKN.clear();
                showDataKN(cmp);



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
                                AdminViewMaterial.this, android.R.style.Theme_Material_Light_Dialog_MinWidth,
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
                                AdminViewMaterial.this, android.R.style.Theme_Material_Light_Dialog_MinWidth,
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


                adapterSearch = new ArrayAdapter<String>(AdminViewMaterial.this,R.layout.support_simple_spinner_dropdown_item,item);
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
                                mDateFormate1.setVisibility(View.VISIBLE);
                                searchButton.setVisibility(View.GONE);
                                centerTIL.setVisibility(View.GONE);
                                villageTIL.setVisibility(View.GONE);
                                searchButton1.setVisibility(View.VISIBLE);
                                break;

                            case "Team Name":
                                mDateFormate.setVisibility(View.GONE);
                                teamNameTIL.setVisibility(View.VISIBLE);
                                tenderTIL.setVisibility(View.GONE);
                                mDateFormate1.setVisibility(View.GONE);
                                searchButton1.setVisibility(View.GONE);
                                searchButton.setVisibility(View.VISIBLE);
                                centerTIL.setVisibility(View.GONE);
                                villageTIL.setVisibility(View.GONE);
                                //Team Name
                                databaseReference = FirebaseDatabase.getInstance().getReference(companyEmail + " teamName");
                                spinnerDataList = new ArrayList<>();
                                adapterTeam = new ArrayAdapter<String>(AdminViewMaterial.this, R.layout.support_simple_spinner_dropdown_item, spinnerDataList);
                                teamName.setAdapter(adapterTeam);
                                retrieveData();
                                break;
                            case "Tender":
                                mDateFormate.setVisibility(View.GONE);
                                teamNameTIL.setVisibility(View.GONE);
                                tenderTIL.setVisibility(View.VISIBLE);
                                mDateFormate1.setVisibility(View.GONE);
                                searchButton.setVisibility(View.VISIBLE);
                                centerTIL.setVisibility(View.GONE);
                                villageTIL.setVisibility(View.GONE);
                                searchButton1.setVisibility(View.GONE);
                                //Tender
                                databaseReferenceTender = FirebaseDatabase.getInstance().getReference(companyEmail + " Tender");
                                spinnerDataListTender = new ArrayList<>();
                                adapterTender = new ArrayAdapter<String>(AdminViewMaterial.this, R.layout.support_simple_spinner_dropdown_item, spinnerDataListTender);
                                tender.setAdapter(adapterTender);
                                retrieveDataTender();
                                break;
                            case "Center":
                                mDateFormate.setVisibility(View.GONE);
                                teamNameTIL.setVisibility(View.GONE);
                                tenderTIL.setVisibility(View.GONE);
                                mDateFormate1.setVisibility(View.GONE);
                                searchButton.setVisibility(View.VISIBLE);
                                centerTIL.setVisibility(View.VISIBLE);
                                villageTIL.setVisibility(View.GONE);
                                searchButton1.setVisibility(View.GONE);

                                //Center
                                databaseReferenceCenter = FirebaseDatabase.getInstance().getReference(companyEmail + " Center");
                                spinnerDataListCenter = new ArrayList<>();
                                adapterCenter = new ArrayAdapter<String>(AdminViewMaterial.this, R.layout.support_simple_spinner_dropdown_item, spinnerDataListCenter);
                                center.setAdapter(adapterCenter);
                                retrieveDataCenter();

                                break;
                            case "Village":
                                mDateFormate.setVisibility(View.GONE);
                                teamNameTIL.setVisibility(View.GONE);
                                tenderTIL.setVisibility(View.GONE);
                                searchButton.setVisibility(View.VISIBLE);
                                centerTIL.setVisibility(View.GONE);
                                mDateFormate1.setVisibility(View.GONE);
                                villageTIL.setVisibility(View.VISIBLE);
                                searchButton1.setVisibility(View.GONE);

                                //Village
                                databaseReferenceVillage = FirebaseDatabase.getInstance().getReference(companyEmail + " Village");
                                spinnerDataListVillage = new ArrayList<>();
                                adapterVillage = new ArrayAdapter<String>(AdminViewMaterial.this, R.layout.support_simple_spinner_dropdown_item, spinnerDataListVillage);
                                village.setAdapter(adapterVillage);
                                retrieveDataVillage();

                                break;
                            default:
                                mDateFormate.setVisibility(View.GONE);
                                teamNameTIL.setVisibility(View.GONE);
                                tenderTIL.setVisibility(View.GONE);
                                mDateFormate1.setVisibility(View.GONE);
                                searchButton.setVisibility(View.GONE);
                                centerTIL.setVisibility(View.GONE);
                                villageTIL.setVisibility(View.GONE);
                                searchButton1.setVisibility(View.GONE);
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

    private void showStock(String cmp) {
        fStore.collection(cmp+" AddStock").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                modelListStock.clear();
                pd.dismiss();
                for (DocumentSnapshot doc : task.getResult()){
                    AddStockModel model = new AddStockModel(
                            doc.getString("Id"),
                            doc.getString("Material"),
                            doc.getString("Unit"),
                            doc.getString("Quantity")
                    );
                    modelListStock.add(model);
                }
                adapterStock = new AdminViewDeleteStockAdapter(AdminViewMaterial.this,modelListStock);
                recyclerViewStock.setAdapter(adapterStock);
            }}).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(AdminViewMaterial.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void searchCenter(String cmp, String centerS) {
        pd.setTitle("Searching...");
        pd.show();
        fStore.collection(cmp + " AddData")
                .whereEqualTo("Center",centerS)
                .orderBy("Date", Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        modelList.clear();
                        pd.dismiss();
                        for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
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
                            modelList.add(model);
                        }
                        adapter = new AdminCustomAdapter(AdminViewMaterial.this, modelList);
                        recyclerView.setAdapter(adapter);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AdminViewMaterial.this, e.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });


    }

    private void searchVillage(String cmp, String villageS) {
        pd.setTitle("Searching...");
        pd.show();
        fStore.collection(cmp + " AddData")
                .whereEqualTo("Village",villageS)
                .orderBy("Date", Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        modelList.clear();
                        pd.dismiss();
                        for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
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
                            modelList.add(model);
                        }
                        adapter = new AdminCustomAdapter(AdminViewMaterial.this, modelList);
                        recyclerView.setAdapter(adapter);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AdminViewMaterial.this, e.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });

    }

    private void searchTender(String cmp, String tenderS) {
        pd.setTitle("Searching...");
        pd.show();
        fStore.collection(cmp + " AddData")
                .whereEqualTo("Tender",tenderS)
                .orderBy("Date", Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        modelList.clear();
                        pd.dismiss();
                        for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
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
                            modelList.add(model);
                        }
                        adapter = new AdminCustomAdapter(AdminViewMaterial.this, modelList);
                        recyclerView.setAdapter(adapter);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AdminViewMaterial.this, e.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });

    }

    private void showMessage(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }

    private void showDataKN(String cmp) {
        fStore.collection(cmp+" KgToNo").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                modelListKN.clear();
                pd.dismiss();
                for (DocumentSnapshot doc : task.getResult()){
                    KgToNoModel model = new KgToNoModel(
                            doc.getString("Id"),
                            doc.getString("Material"),
                            doc.getString("Number"),
                            doc.getString("Unit")
                    );
                    modelListKN.add(model);
                }
                adapterStockKN = new AdminViewKgToNoAdapter(AdminViewMaterial.this,modelListKN);
                recyclerViewKN.setAdapter(adapterStockKN);
            }}).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(AdminViewMaterial.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void searchTeamName(String cmp, String teamNameS) {
        pd.setTitle("Searching...");
        pd.show();
        fStore.collection(cmp + " AddData")
                .whereEqualTo("SearchTeamName",teamNameS.toLowerCase())
                .orderBy("Date", Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        modelList.clear();
                        pd.dismiss();
                        for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
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
                            modelList.add(model);
                        }
                        adapter = new AdminCustomAdapter(AdminViewMaterial.this, modelList);
                        recyclerView.setAdapter(adapter);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AdminViewMaterial.this, e.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });

    }

    private void searchDate(String cmp, String date,String date1) {
        pd.setTitle("Searching...");
        pd.show();
        fStore.collection(cmp + " AddData").orderBy("Date", Query.Direction.DESCENDING).startAt(date1).endAt(date)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        modelList.clear();
                        pd.dismiss();
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
                            modelList.add(model);
                        }
                        adapter = new AdminCustomAdapter(AdminViewMaterial.this, modelList);
                        recyclerView.setAdapter(adapter);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

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


                fStore.collection(cmp + " Material").get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                list.clear();
                                pd.dismiss();
                                for (DocumentSnapshot doc : task.getResult()) {
                                    MaterialModel model = new MaterialModel(
                                            doc.getString("id"),
                                            doc.getString("Material")
                                    );
                                    list.add(model);
                                }
                                materialCustomAdapter = new MaterialCustomAdapter(AdminViewMaterial.this, list);
                                recycler.setAdapter(materialCustomAdapter);

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                pd.dismiss();
                                Toast.makeText(getApplicationContext(), "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
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

                fStore.collection(cmp + " AddData").orderBy("Date", Query.Direction.DESCENDING).get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                modelList.clear();
                                pd.dismiss();
                                refreshLayout.setRefreshing(false);
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

                                    modelList.add(model);
                                }
                                adapter = new AdminCustomAdapter(AdminViewMaterial.this, modelList);
                                recyclerView.setAdapter(adapter);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                pd.dismiss();
                                Toast.makeText(getApplicationContext(), "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
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
                String a = modelList.get(position).getId();

                fStore.collection(cmp + " AddData").document(modelList.get(position).getId())
                        .delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                pd.dismiss();
                                Toast.makeText(AdminViewMaterial.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                                showData();
                                try {
                                    deleteReturnData(cmp,a);

                                }
                                catch (Exception e){
                                    Toast.makeText(AdminViewMaterial.this, "Failed : "+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }


                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });


    }

    private void retrive(String cmp, String a) {
        DocumentReference document = fStore.collection(cmp+" RemainingMaterial")
                .document(a);
        document.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                String m1 = value.getString("Material 1");
                String m2 = value.getString("Material 2");
                String m3 = value.getString("Material 3");
                String m4 = value.getString("Material 4");
                String m5 = value.getString("Material 5");
                String m6 = value.getString("Material 6");
                String m7 = value.getString("Material 7");
                String m8 = value.getString("Material 8");
                String m9 = value.getString("Material 9");
                String m10 = value.getString("Material 10");
                String m11 = value.getString("Material 11");
                String m12 = value.getString("Material 12");
                String m13 = value.getString("Material 13");
                String m14 = value.getString("Material 14");
                String m15 = value.getString("Material 15");
                String m16 = value.getString("Material 16");
                String m17 = value.getString("Material 17");
                String m18 = value.getString("Material 18");
                String m19 = value.getString("Material 19");
                String m20 = value.getString("Material 20");
                String m21 = value.getString("Material 21");
                String m22 = value.getString("Material 22");
                String m23 = value.getString("Material 23");
                String m24 = value.getString("Material 24");
                String m25 = value.getString("Material 25");
                String m26 = value.getString("Material 26");
                String m27 = value.getString("Material 27");
                String m28 = value.getString("Material 28");
                String m29 = value.getString("Material 29");
                String m30 = value.getString("Material 30");

                String u1 = value.getString("Unit 1");
                String u2 = value.getString("Unit 2");
                String u3 = value.getString("Unit 3");
                String u4 = value.getString("Unit 4");
                String u5 = value.getString("Unit 5");
                String u6 = value.getString("Unit 6");
                String u7 = value.getString("Unit 7");
                String u8 = value.getString("Unit 8");
                String u9 = value.getString("Unit 9");
                String u10 = value.getString("Unit 10");
                String u11 = value.getString("Unit 11");
                String u12 = value.getString("Unit 12");
                String u13 = value.getString("Unit 13");
                String u14 = value.getString("Unit 14");
                String u15 = value.getString("Unit 15");
                String u16 = value.getString("Unit 16");
                String u17 = value.getString("Unit 17");
                String u18 = value.getString("Unit 18");
                String u19 = value.getString("Unit 19");
                String u20 = value.getString("Unit 20");
                String u21 = value.getString("Unit 21");
                String u22 = value.getString("Unit 22");
                String u23 = value.getString("Unit 23");
                String u24 = value.getString("Unit 24");
                String u25 = value.getString("Unit 25");
                String u26 = value.getString("Unit 26");
                String u27 = value.getString("Unit 27");
                String u28 = value.getString("Unit 28");
                String u29 = value.getString("Unit 29");
                String u30 = value.getString("Unit 30");

                String q1 = value.getString("Quantity 1");
                String q2 = value.getString("Quantity 2");
                String q3 = value.getString("Quantity 3");
                String q4 = value.getString("Quantity 4");
                String q5 = value.getString("Quantity 5");
                String q6 = value.getString("Quantity 6");
                String q7 = value.getString("Quantity 7");
                String q8 = value.getString("Quantity 8");
                String q9 = value.getString("Quantity 9");
                String q10 = value.getString("Quantity 10");
                String q11 = value.getString("Quantity 11");
                String q12 = value.getString("Quantity 12");
                String q13 = value.getString("Quantity 13");
                String q14 = value.getString("Quantity 14");
                String q15 = value.getString("Quantity 15");
                String q16 = value.getString("Quantity 16");
                String q17 = value.getString("Quantity 17");
                String q18 = value.getString("Quantity 18");
                String q19 = value.getString("Quantity 19");
                String q20 = value.getString("Quantity 20");
                String q21 = value.getString("Quantity 21");
                String q22 = value.getString("Quantity 22");
                String q23 = value.getString("Quantity 23");
                String q24 = value.getString("Quantity 24");
                String q25 = value.getString("Quantity 25");
                String q26 = value.getString("Quantity 26");
                String q27 = value.getString("Quantity 27");
                String q28 = value.getString("Quantity 28");
                String q29 = value.getString("Quantity 29");
                String q30 = value.getString("Quantity 30");

                addStock(cmp,a, m1, m2, m3,
                        m4,
                        m5,
                        m1,
                        m6,
                        m7,
                        m8,
                        m9,
                        m10,
                        m11,
                        m12,
                        m13,
                        m14,
                        m15,
                        m16,
                        m17,
                        m18,
                        m19,
                        m20,
                        m21,
                        m22,
                        m23,
                        m24,
                        m25,
                        m26,
                        m27,
                        m28,
                        m29,
                        m30,

                        u1,
                        u2,
                        u3,
                        u4,
                        u5,
                        u1,
                        u6,
                        u7,
                        u8,
                        u9,
                        u10,
                        u11,
                        u12,
                        u13,
                        u14,
                        u15,
                        u16,
                        u17,
                        u18,
                        u19,
                        u20,
                        u21,
                        u22,
                        u23,
                        u24,
                        u25,
                        u26,
                        u27,
                        u28,
                        u29,
                        u30,

                        q1,
                        q2,
                        q3,
                        q4,
                        q5,
                        q1,
                        q6,
                        q7,
                        q8,
                        q9,
                        q10,
                        q11,
                        q12,
                        q13,
                        q14,
                        q15,
                        q16,
                        q17,
                        q18,
                        q19,
                        q20,
                        q21,
                        q22,
                        q23,
                        q24,
                        q25,
                        q26,
                        q27,
                        q28,
                        q29,
                        q30);


            }


        });
    }

    private void addStock(String cmp,String id, String m1, String m2, String m3, String m4, String m5, String m11, String m6, String m7, String m8, String m9, String m10, String m111, String m12, String m13, String m14, String m15, String m16, String m17, String m18, String m19, String m20, String m21, String m22, String m23, String m24, String m25, String m26, String m27, String m28, String m29, String m30, String u1, String u2, String u3, String u4, String u5, String u11, String u6, String u7, String u8, String u9, String u10, String u111, String u12, String u13, String u14, String u15, String u16, String u17, String u18, String u19, String u20, String u21, String u22, String u23, String u24, String u25, String u26, String u27, String u28, String u29, String u30, String q1, String q2, String q3, String q4, String q5, String q11, String q6, String q7, String q8, String q9, String q10, String q111, String q12, String q13, String q14, String q15, String q16, String q17, String q18, String q19, String q20, String q21, String q22, String q23, String q24, String q25, String q26, String q27, String q28, String q29, String q30) {

        for (int i=0;i<modelListStock.size();i++){

            if (modelListStock.get(i).getMaterial().equals(m1)){
                if (modelListStock.get(i).getQuantity().equals(u1)){
                    float a = Float.parseFloat(modelListStock.get(i).getUnit()) + Float.parseFloat(q1);
                    addStock(cmp, modelListStock.get(i).getMaterial(), modelListStock.get(i).getQuantity(), a);
                }

               else{
                   for (int j=0;j<modelListKN.size();j++){
                        if ((modelListKN.get(i).getMaterial()).equals(m1)){
                            float stock = Float.parseFloat(modelListStock.get(i).getUnit());
                            float quantity = Float.parseFloat(q1);
                            float conversion = Float.parseFloat(modelListKN.get(i).getNumber());
                            float r = quantity / conversion;
                            float result = r + stock;
                            addStock(cmp,modelListStock.get(i).getMaterial(),modelListStock.get(i).getQuantity(),result);
                        }
                    }
                }
            }

            if (modelListStock.get(i).getMaterial().equals(m2)){
                if (modelListStock.get(i).getQuantity().equals(u2)){
                    float a = Float.parseFloat(modelListStock.get(i).getUnit()) + Float.parseFloat(q2);
                    addStock(cmp, modelListStock.get(i).getMaterial(), modelListStock.get(i).getQuantity(), a);
                }

                else{
                    for (int j=0;j<modelListKN.size();j++){
                        if ((modelListKN.get(i).getMaterial()).equals(m2)){
                            float stock = Float.parseFloat(modelListStock.get(i).getUnit());
                            float quantity = Float.parseFloat(q2);
                            float conversion = Float.parseFloat(modelListKN.get(i).getNumber());
                            float r = quantity / conversion;
                            float result = r + stock;
                            addStock(cmp,modelListStock.get(i).getMaterial(),modelListStock.get(i).getQuantity(),result);
                        }
                    }
                }
            }

            if (modelListStock.get(i).getMaterial().equals(m3)){
                if (modelListStock.get(i).getQuantity().equals(u3)){
                    float a = Float.parseFloat(modelListStock.get(i).getUnit()) + Float.parseFloat(q3);
                    addStock(cmp, modelListStock.get(i).getMaterial(), modelListStock.get(i).getQuantity(), a);
                }

                else{
                    for (int j=0;j<modelListKN.size();j++){
                        if ((modelListKN.get(i).getMaterial()).equals(m3)){
                            float stock = Float.parseFloat(modelListStock.get(i).getUnit());
                            float quantity = Float.parseFloat(q3);
                            float conversion = Float.parseFloat(modelListKN.get(i).getNumber());
                            float r = quantity / conversion;
                            float result = r + stock;
                            addStock(cmp,modelListStock.get(i).getMaterial(),modelListStock.get(i).getQuantity(),result);
                        }
                    }
                }
            }

            if (modelListStock.get(i).getMaterial().equals(m4)){
                if (modelListStock.get(i).getQuantity().equals(u4)){
                    float a = Float.parseFloat(modelListStock.get(i).getUnit()) + Float.parseFloat(q4);
                    addStock(cmp, modelListStock.get(i).getMaterial(), modelListStock.get(i).getQuantity(), a);
                }

                else{
                    for (int j=0;j<modelListKN.size();j++){
                        if ((modelListKN.get(i).getMaterial()).equals(m4)){
                            float stock = Float.parseFloat(modelListStock.get(i).getUnit());
                            float quantity = Float.parseFloat(q4);
                            float conversion = Float.parseFloat(modelListKN.get(i).getNumber());
                            float r = quantity / conversion;
                            float result = r + stock;
                            addStock(cmp,modelListStock.get(i).getMaterial(),modelListStock.get(i).getQuantity(),result);
                        }
                    }
                }
            }

            if (modelListStock.get(i).getMaterial().equals(m5)){
                if (modelListStock.get(i).getQuantity().equals(u5)){
                    float a = Float.parseFloat(modelListStock.get(i).getUnit()) + Float.parseFloat(q5);
                    addStock(cmp, modelListStock.get(i).getMaterial(), modelListStock.get(i).getQuantity(), a);
                }

                else{
                    for (int j=0;j<modelListKN.size();j++){
                        if ((modelListKN.get(i).getMaterial()).equals(m5)){
                            float stock = Float.parseFloat(modelListStock.get(i).getUnit());
                            float quantity = Float.parseFloat(q5);
                            float conversion = Float.parseFloat(modelListKN.get(i).getNumber());
                            float r = quantity / conversion;
                            float result = r + stock;
                            addStock(cmp,modelListStock.get(i).getMaterial(),modelListStock.get(i).getQuantity(),result);
                        }
                    }
                }
            }

            if (modelListStock.get(i).getMaterial().equals(m6)){
                if (modelListStock.get(i).getQuantity().equals(u6)){
                    float a = Float.parseFloat(modelListStock.get(i).getUnit()) + Float.parseFloat(q6);
                    addStock(cmp, modelListStock.get(i).getMaterial(), modelListStock.get(i).getQuantity(), a);
                }

                else{
                    for (int j=0;j<modelListKN.size();j++){
                        if ((modelListKN.get(i).getMaterial()).equals(m6)){
                            float stock = Float.parseFloat(modelListStock.get(i).getUnit());
                            float quantity = Float.parseFloat(q6);
                            float conversion = Float.parseFloat(modelListKN.get(i).getNumber());
                            float r = quantity / conversion;
                            float result = r + stock;
                            addStock(cmp,modelListStock.get(i).getMaterial(),modelListStock.get(i).getQuantity(),result);
                        }
                    }
                }
            }

            if (modelListStock.get(i).getMaterial().equals(m7)){
                if (modelListStock.get(i).getQuantity().equals(u7)){
                    float a = Float.parseFloat(modelListStock.get(i).getUnit()) + Float.parseFloat(q7);
                    addStock(cmp, modelListStock.get(i).getMaterial(), modelListStock.get(i).getQuantity(), a);
                }

                else{
                    for (int j=0;j<modelListKN.size();j++){
                        if ((modelListKN.get(i).getMaterial()).equals(m7)){
                            float stock = Float.parseFloat(modelListStock.get(i).getUnit());
                            float quantity = Float.parseFloat(q7);
                            float conversion = Float.parseFloat(modelListKN.get(i).getNumber());
                            float r = quantity / conversion;
                            float result = r + stock;
                            addStock(cmp,modelListStock.get(i).getMaterial(),modelListStock.get(i).getQuantity(),result);
                        }
                    }
                }
            }

            if (modelListStock.get(i).getMaterial().equals(m8)){
                if (modelListStock.get(i).getQuantity().equals(u8)){
                    float a = Float.parseFloat(modelListStock.get(i).getUnit()) + Float.parseFloat(q8);
                    addStock(cmp, modelListStock.get(i).getMaterial(), modelListStock.get(i).getQuantity(), a);
                }

                else{
                    for (int j=0;j<modelListKN.size();j++){
                        if ((modelListKN.get(i).getMaterial()).equals(m8)){
                            float stock = Float.parseFloat(modelListStock.get(i).getUnit());
                            float quantity = Float.parseFloat(q8);
                            float conversion = Float.parseFloat(modelListKN.get(i).getNumber());
                            float r = quantity / conversion;
                            float result = r + stock;
                            addStock(cmp,modelListStock.get(i).getMaterial(),modelListStock.get(i).getQuantity(),result);
                        }
                    }
                }
            }

            if (modelListStock.get(i).getMaterial().equals(m9)){
                if (modelListStock.get(i).getQuantity().equals(u9)){
                    float a = Float.parseFloat(modelListStock.get(i).getUnit()) + Float.parseFloat(q9);
                    addStock(cmp, modelListStock.get(i).getMaterial(), modelListStock.get(i).getQuantity(), a);
                }

                else{
                    for (int j=0;j<modelListKN.size();j++){
                        if ((modelListKN.get(i).getMaterial()).equals(m9)){
                            float stock = Float.parseFloat(modelListStock.get(i).getUnit());
                            float quantity = Float.parseFloat(q9);
                            float conversion = Float.parseFloat(modelListKN.get(i).getNumber());
                            float r = quantity / conversion;
                            float result = r + stock;
                            addStock(cmp,modelListStock.get(i).getMaterial(),modelListStock.get(i).getQuantity(),result);
                        }
                    }
                }
            }

            if (modelListStock.get(i).getMaterial().equals(m10)){
                if (modelListStock.get(i).getQuantity().equals(u10)){
                    float a = Float.parseFloat(modelListStock.get(i).getUnit()) + Float.parseFloat(q10);
                    addStock(cmp, modelListStock.get(i).getMaterial(), modelListStock.get(i).getQuantity(), a);
                }

                else{
                    for (int j=0;j<modelListKN.size();j++){
                        if ((modelListKN.get(i).getMaterial()).equals(m10)){
                            float stock = Float.parseFloat(modelListStock.get(i).getUnit());
                            float quantity = Float.parseFloat(q10);
                            float conversion = Float.parseFloat(modelListKN.get(i).getNumber());
                            float r = quantity / conversion;
                            float result = r + stock;
                            addStock(cmp,modelListStock.get(i).getMaterial(),modelListStock.get(i).getQuantity(),result);
                        }
                    }
                }
            }

            if (modelListStock.get(i).getMaterial().equals(m11)){
                if (modelListStock.get(i).getQuantity().equals(u11)){
                    float a = Float.parseFloat(modelListStock.get(i).getUnit()) + Float.parseFloat(q11);
                    addStock(cmp, modelListStock.get(i).getMaterial(), modelListStock.get(i).getQuantity(), a);
                }

                else{
                    for (int j=0;j<modelListKN.size();j++){
                        if ((modelListKN.get(i).getMaterial()).equals(m11)){
                            float stock = Float.parseFloat(modelListStock.get(i).getUnit());
                            float quantity = Float.parseFloat(q11);
                            float conversion = Float.parseFloat(modelListKN.get(i).getNumber());
                            float r = quantity / conversion;
                            float result = r + stock;
                            addStock(cmp,modelListStock.get(i).getMaterial(),modelListStock.get(i).getQuantity(),result);
                        }
                    }
                }
            }

            if (modelListStock.get(i).getMaterial().equals(m12)){
                if (modelListStock.get(i).getQuantity().equals(u12)){
                    float a = Float.parseFloat(modelListStock.get(i).getUnit()) + Float.parseFloat(q12);
                    addStock(cmp, modelListStock.get(i).getMaterial(), modelListStock.get(i).getQuantity(), a);
                }

                else{
                    for (int j=0;j<modelListKN.size();j++){
                        if ((modelListKN.get(i).getMaterial()).equals(m12)){
                            float stock = Float.parseFloat(modelListStock.get(i).getUnit());
                            float quantity = Float.parseFloat(q12);
                            float conversion = Float.parseFloat(modelListKN.get(i).getNumber());
                            float r = quantity / conversion;
                            float result = r + stock;
                            addStock(cmp,modelListStock.get(i).getMaterial(),modelListStock.get(i).getQuantity(),result);
                        }
                    }
                }
            }

            if (modelListStock.get(i).getMaterial().equals(m13)){
                if (modelListStock.get(i).getQuantity().equals(u13)){
                    float a = Float.parseFloat(modelListStock.get(i).getUnit()) + Float.parseFloat(q13);
                    addStock(cmp, modelListStock.get(i).getMaterial(), modelListStock.get(i).getQuantity(), a);
                }

                else{
                    for (int j=0;j<modelListKN.size();j++){
                        if ((modelListKN.get(i).getMaterial()).equals(m13)){
                            float stock = Float.parseFloat(modelListStock.get(i).getUnit());
                            float quantity = Float.parseFloat(q13);
                            float conversion = Float.parseFloat(modelListKN.get(i).getNumber());
                            float r = quantity / conversion;
                            float result = r + stock;
                            addStock(cmp,modelListStock.get(i).getMaterial(),modelListStock.get(i).getQuantity(),result);
                        }
                    }
                }
            }

            if (modelListStock.get(i).getMaterial().equals(m14)){
                if (modelListStock.get(i).getQuantity().equals(u14)){
                    float a = Float.parseFloat(modelListStock.get(i).getUnit()) + Float.parseFloat(q14);
                    addStock(cmp, modelListStock.get(i).getMaterial(), modelListStock.get(i).getQuantity(), a);
                }

                else{
                    for (int j=0;j<modelListKN.size();j++){
                        if ((modelListKN.get(i).getMaterial()).equals(m14)){
                            float stock = Float.parseFloat(modelListStock.get(i).getUnit());
                            float quantity = Float.parseFloat(q14);
                            float conversion = Float.parseFloat(modelListKN.get(i).getNumber());
                            float r = quantity / conversion;
                            float result = r + stock;
                            addStock(cmp,modelListStock.get(i).getMaterial(),modelListStock.get(i).getQuantity(),result);
                        }
                    }
                }
            }

            if (modelListStock.get(i).getMaterial().equals(m15)){
                if (modelListStock.get(i).getQuantity().equals(u15)){
                    float a = Float.parseFloat(modelListStock.get(i).getUnit()) + Float.parseFloat(q15);
                    addStock(cmp, modelListStock.get(i).getMaterial(), modelListStock.get(i).getQuantity(), a);
                }

                else{
                    for (int j=0;j<modelListKN.size();j++){
                        if ((modelListKN.get(i).getMaterial()).equals(m15)){
                            float stock = Float.parseFloat(modelListStock.get(i).getUnit());
                            float quantity = Float.parseFloat(q15);
                            float conversion = Float.parseFloat(modelListKN.get(i).getNumber());
                            float r = quantity / conversion;
                            float result = r + stock;
                            addStock(cmp,modelListStock.get(i).getMaterial(),modelListStock.get(i).getQuantity(),result);
                        }
                    }
                }
            }

            if (modelListStock.get(i).getMaterial().equals(m16)){
                if (modelListStock.get(i).getQuantity().equals(u16)){
                    float a = Float.parseFloat(modelListStock.get(i).getUnit()) + Float.parseFloat(q16);
                    addStock(cmp, modelListStock.get(i).getMaterial(), modelListStock.get(i).getQuantity(), a);
                }

                else{
                    for (int j=0;j<modelListKN.size();j++){
                        if ((modelListKN.get(i).getMaterial()).equals(m16)){
                            float stock = Float.parseFloat(modelListStock.get(i).getUnit());
                            float quantity = Float.parseFloat(q16);
                            float conversion = Float.parseFloat(modelListKN.get(i).getNumber());
                            float r = quantity / conversion;
                            float result = r + stock;
                            addStock(cmp,modelListStock.get(i).getMaterial(),modelListStock.get(i).getQuantity(),result);
                        }
                    }
                }
            }

            if (modelListStock.get(i).getMaterial().equals(m17)){
                if (modelListStock.get(i).getQuantity().equals(u17)){
                    float a = Float.parseFloat(modelListStock.get(i).getUnit()) + Float.parseFloat(q17);
                    addStock(cmp, modelListStock.get(i).getMaterial(), modelListStock.get(i).getQuantity(), a);
                }

                else{
                    for (int j=0;j<modelListKN.size();j++){
                        if ((modelListKN.get(i).getMaterial()).equals(m17)){
                            float stock = Float.parseFloat(modelListStock.get(i).getUnit());
                            float quantity = Float.parseFloat(q17);
                            float conversion = Float.parseFloat(modelListKN.get(i).getNumber());
                            float r = quantity / conversion;
                            float result = r + stock;
                            addStock(cmp,modelListStock.get(i).getMaterial(),modelListStock.get(i).getQuantity(),result);
                        }
                    }
                }
            }

            if (modelListStock.get(i).getMaterial().equals(m18)){
                if (modelListStock.get(i).getQuantity().equals(u18)){
                    float a = Float.parseFloat(modelListStock.get(i).getUnit()) + Float.parseFloat(q18);
                    addStock(cmp, modelListStock.get(i).getMaterial(), modelListStock.get(i).getQuantity(), a);
                }

                else{
                    for (int j=0;j<modelListKN.size();j++){
                        if ((modelListKN.get(i).getMaterial()).equals(m18)){
                            float stock = Float.parseFloat(modelListStock.get(i).getUnit());
                            float quantity = Float.parseFloat(q18);
                            float conversion = Float.parseFloat(modelListKN.get(i).getNumber());
                            float r = quantity / conversion;
                            float result = r + stock;
                            addStock(cmp,modelListStock.get(i).getMaterial(),modelListStock.get(i).getQuantity(),result);
                        }
                    }
                }
            }

            if (modelListStock.get(i).getMaterial().equals(m19)){
                if (modelListStock.get(i).getQuantity().equals(u19)){
                    float a = Float.parseFloat(modelListStock.get(i).getUnit()) + Float.parseFloat(q19);
                    addStock(cmp, modelListStock.get(i).getMaterial(), modelListStock.get(i).getQuantity(), a);
                }

                else{
                    for (int j=0;j<modelListKN.size();j++){
                        if ((modelListKN.get(i).getMaterial()).equals(m19)){
                            float stock = Float.parseFloat(modelListStock.get(i).getUnit());
                            float quantity = Float.parseFloat(q19);
                            float conversion = Float.parseFloat(modelListKN.get(i).getNumber());
                            float r = quantity / conversion;
                            float result = r + stock;
                            addStock(cmp,modelListStock.get(i).getMaterial(),modelListStock.get(i).getQuantity(),result);
                        }
                    }
                }
            }

            if (modelListStock.get(i).getMaterial().equals(m20)){
                if (modelListStock.get(i).getQuantity().equals(u20)){
                    float a = Float.parseFloat(modelListStock.get(i).getUnit()) + Float.parseFloat(q20);
                    addStock(cmp, modelListStock.get(i).getMaterial(), modelListStock.get(i).getQuantity(), a);
                }

                else{
                    for (int j=0;j<modelListKN.size();j++){
                        if ((modelListKN.get(i).getMaterial()).equals(m20)){
                            float stock = Float.parseFloat(modelListStock.get(i).getUnit());
                            float quantity = Float.parseFloat(q20);
                            float conversion = Float.parseFloat(modelListKN.get(i).getNumber());
                            float r = quantity / conversion;
                            float result = r + stock;
                            addStock(cmp,modelListStock.get(i).getMaterial(),modelListStock.get(i).getQuantity(),result);
                        }
                    }
                }
            }

            if (modelListStock.get(i).getMaterial().equals(m21)){
                if (modelListStock.get(i).getQuantity().equals(u21)){
                    float a = Float.parseFloat(modelListStock.get(i).getUnit()) + Float.parseFloat(q21);
                    addStock(cmp, modelListStock.get(i).getMaterial(), modelListStock.get(i).getQuantity(), a);
                }

                else{
                    for (int j=0;j<modelListKN.size();j++){
                        if ((modelListKN.get(i).getMaterial()).equals(m21)){
                            float stock = Float.parseFloat(modelListStock.get(i).getUnit());
                            float quantity = Float.parseFloat(q21);
                            float conversion = Float.parseFloat(modelListKN.get(i).getNumber());
                            float r = quantity / conversion;
                            float result = r + stock;
                            addStock(cmp,modelListStock.get(i).getMaterial(),modelListStock.get(i).getQuantity(),result);
                        }
                    }
                }
            }

            if (modelListStock.get(i).getMaterial().equals(m22)){
                if (modelListStock.get(i).getQuantity().equals(u22)){
                    float a = Float.parseFloat(modelListStock.get(i).getUnit()) + Float.parseFloat(q22);
                    addStock(cmp, modelListStock.get(i).getMaterial(), modelListStock.get(i).getQuantity(), a);
                }

                else{
                    for (int j=0;j<modelListKN.size();j++){
                        if ((modelListKN.get(i).getMaterial()).equals(m22)){
                            float stock = Float.parseFloat(modelListStock.get(i).getUnit());
                            float quantity = Float.parseFloat(q22);
                            float conversion = Float.parseFloat(modelListKN.get(i).getNumber());
                            float r = quantity / conversion;
                            float result = r + stock;
                            addStock(cmp,modelListStock.get(i).getMaterial(),modelListStock.get(i).getQuantity(),result);
                        }
                    }
                }
            }

            if (modelListStock.get(i).getMaterial().equals(m23)){
                if (modelListStock.get(i).getQuantity().equals(u23)){
                    float a = Float.parseFloat(modelListStock.get(i).getUnit()) + Float.parseFloat(q23);
                    addStock(cmp, modelListStock.get(i).getMaterial(), modelListStock.get(i).getQuantity(), a);
                }

                else{
                    for (int j=0;j<modelListKN.size();j++){
                        if ((modelListKN.get(i).getMaterial()).equals(m23)){
                            float stock = Float.parseFloat(modelListStock.get(i).getUnit());
                            float quantity = Float.parseFloat(q23);
                            float conversion = Float.parseFloat(modelListKN.get(i).getNumber());
                            float r = quantity / conversion;
                            float result = r + stock;
                            addStock(cmp,modelListStock.get(i).getMaterial(),modelListStock.get(i).getQuantity(),result);
                        }
                    }
                }
            }

            if (modelListStock.get(i).getMaterial().equals(m24)){
                if (modelListStock.get(i).getQuantity().equals(u24)){
                    float a = Float.parseFloat(modelListStock.get(i).getUnit()) + Float.parseFloat(q24);
                    addStock(cmp, modelListStock.get(i).getMaterial(), modelListStock.get(i).getQuantity(), a);
                }

                else{
                    for (int j=0;j<modelListKN.size();j++){
                        if ((modelListKN.get(i).getMaterial()).equals(m24)){
                            float stock = Float.parseFloat(modelListStock.get(i).getUnit());
                            float quantity = Float.parseFloat(q24);
                            float conversion = Float.parseFloat(modelListKN.get(i).getNumber());
                            float r = quantity / conversion;
                            float result = r + stock;
                            addStock(cmp,modelListStock.get(i).getMaterial(),modelListStock.get(i).getQuantity(),result);
                        }
                    }
                }
            }

            if (modelListStock.get(i).getMaterial().equals(m25)){
                if (modelListStock.get(i).getQuantity().equals(u25)){
                    float a = Float.parseFloat(modelListStock.get(i).getUnit()) + Float.parseFloat(q25);
                    addStock(cmp, modelListStock.get(i).getMaterial(), modelListStock.get(i).getQuantity(), a);
                }

                else{
                    for (int j=0;j<modelListKN.size();j++){
                        if ((modelListKN.get(i).getMaterial()).equals(m25)){
                            float stock = Float.parseFloat(modelListStock.get(i).getUnit());
                            float quantity = Float.parseFloat(q25);
                            float conversion = Float.parseFloat(modelListKN.get(i).getNumber());
                            float r = quantity / conversion;
                            float result = r + stock;
                            addStock(cmp,modelListStock.get(i).getMaterial(),modelListStock.get(i).getQuantity(),result);
                        }
                    }
                }
            }

            if (modelListStock.get(i).getMaterial().equals(m26)){
                if (modelListStock.get(i).getQuantity().equals(u26)){
                    float a = Float.parseFloat(modelListStock.get(i).getUnit()) + Float.parseFloat(q26);
                    addStock(cmp, modelListStock.get(i).getMaterial(), modelListStock.get(i).getQuantity(), a);
                }

                else{
                    for (int j=0;j<modelListKN.size();j++){
                        if ((modelListKN.get(i).getMaterial()).equals(m26)){
                            float stock = Float.parseFloat(modelListStock.get(i).getUnit());
                            float quantity = Float.parseFloat(q26);
                            float conversion = Float.parseFloat(modelListKN.get(i).getNumber());
                            float r = quantity / conversion;
                            float result = r + stock;
                            addStock(cmp,modelListStock.get(i).getMaterial(),modelListStock.get(i).getQuantity(),result);
                        }
                    }
                }
            }

            if (modelListStock.get(i).getMaterial().equals(m27)){
                if (modelListStock.get(i).getQuantity().equals(u27)){
                    float a = Float.parseFloat(modelListStock.get(i).getUnit()) + Float.parseFloat(q27);
                    addStock(cmp, modelListStock.get(i).getMaterial(), modelListStock.get(i).getQuantity(), a);
                }

                else{
                    for (int j=0;j<modelListKN.size();j++){
                        if ((modelListKN.get(i).getMaterial()).equals(m27)){
                            float stock = Float.parseFloat(modelListStock.get(i).getUnit());
                            float quantity = Float.parseFloat(q27);
                            float conversion = Float.parseFloat(modelListKN.get(i).getNumber());
                            float r = quantity / conversion;
                            float result = r + stock;
                            addStock(cmp,modelListStock.get(i).getMaterial(),modelListStock.get(i).getQuantity(),result);
                        }
                    }
                }
            }

            if (modelListStock.get(i).getMaterial().equals(m28)){
                if (modelListStock.get(i).getQuantity().equals(u28)){
                    float a = Float.parseFloat(modelListStock.get(i).getUnit()) + Float.parseFloat(q28);
                    addStock(cmp, modelListStock.get(i).getMaterial(), modelListStock.get(i).getQuantity(), a);
                }

                else{
                    for (int j=0;j<modelListKN.size();j++){
                        if ((modelListKN.get(i).getMaterial()).equals(m28)){
                            float stock = Float.parseFloat(modelListStock.get(i).getUnit());
                            float quantity = Float.parseFloat(q28);
                            float conversion = Float.parseFloat(modelListKN.get(i).getNumber());
                            float r = quantity / conversion;
                            float result = r + stock;
                            addStock(cmp,modelListStock.get(i).getMaterial(),modelListStock.get(i).getQuantity(),result);
                        }
                    }
                }
            }

            if (modelListStock.get(i).getMaterial().equals(m29)){
                if (modelListStock.get(i).getQuantity().equals(u29)){
                    float a = Float.parseFloat(modelListStock.get(i).getUnit()) + Float.parseFloat(q29);
                    addStock(cmp, modelListStock.get(i).getMaterial(), modelListStock.get(i).getQuantity(), a);
                }

                else{
                    for (int j=0;j<modelListKN.size();j++){
                        if ((modelListKN.get(i).getMaterial()).equals(m29)){
                            float stock = Float.parseFloat(modelListStock.get(i).getUnit());
                            float quantity = Float.parseFloat(q29);
                            float conversion = Float.parseFloat(modelListKN.get(i).getNumber());
                            float r = quantity / conversion;
                            float result = r + stock;
                            addStock(cmp,modelListStock.get(i).getMaterial(),modelListStock.get(i).getQuantity(),result);
                        }
                    }
                }
            }

            if (modelListStock.get(i).getMaterial().equals(m30)){
                if (modelListStock.get(i).getQuantity().equals(u30)){
                    float a = Float.parseFloat(modelListStock.get(i).getUnit()) + Float.parseFloat(q30);
                    addStock(cmp, modelListStock.get(i).getMaterial(), modelListStock.get(i).getQuantity(), a);
                }

                else{
                    for (int j=0;j<modelListKN.size();j++){
                        if ((modelListKN.get(i).getMaterial()).equals(m30)){
                            float stock = Float.parseFloat(modelListStock.get(i).getUnit());
                            float quantity = Float.parseFloat(q30);
                            float conversion = Float.parseFloat(modelListKN.get(i).getNumber());
                            float r = quantity / conversion;
                            float result = r + stock;
                            addStock(cmp,modelListStock.get(i).getMaterial(),modelListStock.get(i).getQuantity(),result);
                        }
                    }
                }
            }

        }




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
                Toast.makeText(getApplicationContext(), "Add Stock", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //this will be called when data is added Failed
                Toast.makeText(getApplicationContext(), "Failed to add data "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void deleteUsedData(String cmp,String a) {
        fStore.collection(cmp + " RemainingMaterial").document(a)
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        pd.dismiss();
                        Toast.makeText(AdminViewMaterial.this, "Return Data Delete!", Toast.LENGTH_SHORT).show();
                        showData();

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteReturnData(String cmp,String a) {
        fStore.collection(cmp + " AddReturnData").document(a)
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        pd.dismiss();
                        Toast.makeText(AdminViewMaterial.this, "Return Data Delete!", Toast.LENGTH_SHORT).show();
                        showData();
                        deleteUsedData(cmp,a);
                        retrive(cmp,a);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
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
        sheet = wb.createSheet("Added Material List");


        excel1(cell, sheet);

        excel2(wb, cell, sheet);

        String folderName = "UrjaVahini";
        String fileName = "Added Material List" + System.currentTimeMillis() + ".xls";
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
//            m0 = list.get(i).getMaterial();
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