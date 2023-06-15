package com.example.electricalmaterial;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TeamLeaderViewData extends AppCompatActivity {
    List<TeamLeaderModel> modelList = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String companyEmail;
    String userId;

    ProgressDialog pd;

    TeamLeaderCustomAdapter adapter;

    String cmp;


    AutoCompleteTextView selectSearch;
    ArrayAdapter <String> adapterSearch;
    String [] item = {"Date","Team Name","Tender","Center","Village"};

    //date
    TextView mDateFormate;
    DatePickerDialog.OnDateSetListener onDateSetListener;
    String date;

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

    SwipeRefreshLayout refreshLayout;

    ImageView searchButton;
    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_leader_view_data);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        fStore = FirebaseFirestore.getInstance();
        pd = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = mAuth.getCurrentUser().getUid();

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        modelList.clear();
        showData();

        refreshLayout = findViewById(R.id.refresh);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                showData();
                refreshLayout.setRefreshing(false);
            }
        });

        selectSearch = findViewById(R.id.selectSearch);

        //TextInputLayout
        teamNameTIL = findViewById(R.id.teamNameTIL);
        tenderTIL = findViewById(R.id.tenderTIL);

        centerTIL = findViewById(R.id.centerTL);
        villageTIL = findViewById(R.id.villageTL);



        //Team Name
        teamName = findViewById(R.id.teamName);

        //Tender
        tender = findViewById(R.id.tender);

        //Center
        center = findViewById(R.id.centerTLV);

        //Village
        village = findViewById(R.id.villageTLV);


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


                ActionBar bar = getSupportActionBar();
                assert bar != null;
                bar.setTitle("MATERIAL UPDATION");


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
                                TeamLeaderViewData.this, android.R.style.Theme_Material_Light_Dialog_MinWidth,
                                onDateSetListener,year,month,day);

                        datePickerDialog.getWindow()
                                .setBackgroundDrawable(new ColorDrawable(Color.GREEN));
                        datePickerDialog.show();
                    }
                });
                onDateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month+1;
                        date  = year+"/"+month+"/"+dayOfMonth ;
                        mDateFormate.setText(date);
                        searchDate(cmp,date);

                    }
                };



                adapterSearch = new ArrayAdapter<String>(TeamLeaderViewData.this,R.layout.support_simple_spinner_dropdown_item,item);
                selectSearch.setAdapter(adapterSearch);

                selectSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String n = parent.getItemAtPosition(position).toString().trim();
                        switch (n) {
                            case "Date":
                                mDateFormate.setVisibility(View.VISIBLE);
                                teamNameTIL.setVisibility(View.GONE);
                                tenderTIL.setVisibility(View.GONE);
                                searchButton.setVisibility(View.GONE);
                                centerTIL.setVisibility(View.GONE);
                                villageTIL.setVisibility(View.GONE);
                                break;
                            case "Team Name":
                                mDateFormate.setVisibility(View.GONE);
                                teamNameTIL.setVisibility(View.VISIBLE);
                                tenderTIL.setVisibility(View.GONE);
                                searchButton.setVisibility(View.VISIBLE);
                                centerTIL.setVisibility(View.GONE);
                                villageTIL.setVisibility(View.GONE);
                                //Team Name
                                databaseReference = FirebaseDatabase.getInstance().getReference(companyEmail + " teamName");
                                spinnerDataList = new ArrayList<>();
                                adapterTeam = new ArrayAdapter<String>(TeamLeaderViewData.this, R.layout.support_simple_spinner_dropdown_item, spinnerDataList);
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
                                //Tender
                                databaseReferenceTender = FirebaseDatabase.getInstance().getReference(companyEmail + " Tender");
                                spinnerDataListTender = new ArrayList<>();
                                adapterTender = new ArrayAdapter<String>(TeamLeaderViewData.this, R.layout.support_simple_spinner_dropdown_item, spinnerDataListTender);
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

                                //Center
                                databaseReferenceCenter = FirebaseDatabase.getInstance().getReference(companyEmail + " Center");
                                spinnerDataListCenter = new ArrayList<>();
                                adapterCenter = new ArrayAdapter<String>(TeamLeaderViewData.this, R.layout.support_simple_spinner_dropdown_item, spinnerDataListCenter);
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

                                //Village
                                databaseReferenceVillage = FirebaseDatabase.getInstance().getReference(companyEmail + " Village");
                                spinnerDataListVillage = new ArrayList<>();
                                adapterVillage = new ArrayAdapter<String>(TeamLeaderViewData.this, R.layout.support_simple_spinner_dropdown_item, spinnerDataListVillage);
                                village.setAdapter(adapterVillage);
                                retrieveDataVillage();

                                break;
                            default:
                                mDateFormate.setVisibility(View.GONE);
                                teamNameTIL.setVisibility(View.GONE);
                                tenderTIL.setVisibility(View.GONE);
                                searchButton.setVisibility(View.GONE);
                                centerTIL.setVisibility(View.GONE);
                                villageTIL.setVisibility(View.GONE);
                                break;
                        }
                    }
                });

//                searchButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        String search = selectSearch.getText().toString().trim();
//                        String teamNameS = teamName.getText().toString().trim();
//                        String tenderS = tender.getText().toString().trim();
//                        String centerS = center.getText().toString().trim();
//                        String villageS = village.getText().toString().trim();
//
//                        switch (search) {
//                            case "Team Name":
//                                if (teamNameS.isEmpty()) {
//                                    teamName.setError("Select any one!!");
//                                    teamName.requestFocus();
//                                    showMessage("Please select team name to search");
//                                } else {
//                                    searchTeamName(cmp, teamNameS);
//                                }
//                                break;
//                            case "Tender":
//                                if (tenderS.isEmpty()) {
//                                    tender.setError("Select any one!!");
//                                    tender.requestFocus();
//                                    showMessage("Please select tender to search");
//                                } else {
//                                    searchTender(cmp, tenderS);
//                                }
//                                break;
//                            case "Center":
//                                if (centerS.isEmpty()) {
//                                    center.setError("Select any one!!");
//                                    center.requestFocus();
//                                    showMessage("Please select Center to search");
//                                } else {
//                                    searchCenter(cmp, centerS);
//                                }
//                                break;
//                            case "Village":
//                                if (villageS.isEmpty()) {
//                                    village.setError("Select any one!!");
//                                    village.requestFocus();
//                                    showMessage("Please select Village to search");
//                                } else {
//                                    searchVillage(cmp, villageS);
//                                }
//                                break;
//                        }
//
//
//
//
//
//                    }
//                });










            }
        });
    }


    private void searchVillage(String cmp, String villageS) {
        pd.setTitle("Searching...");
        pd.show();
        fStore.collection(cmp+" AddData").whereEqualTo("Village",villageS)
                .orderBy("Date", Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        modelList.clear();
                        pd.dismiss();
                        for (DocumentSnapshot doc : queryDocumentSnapshots){
                            TeamLeaderModel model = new TeamLeaderModel(
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
                        adapter = new TeamLeaderCustomAdapter(TeamLeaderViewData.this,modelList);
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

    private void searchCenter(String cmp, String centerS) {
        pd.setTitle("Searching...");
        pd.show();
        fStore.collection(cmp+" AddData").whereEqualTo("Center",centerS)
                .orderBy("Date", Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        modelList.clear();
                        pd.dismiss();
                        for (DocumentSnapshot doc : queryDocumentSnapshots){
                            TeamLeaderModel model = new TeamLeaderModel(
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
                        adapter = new TeamLeaderCustomAdapter(TeamLeaderViewData.this,modelList);
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
        fStore.collection(cmp+" AddData").whereEqualTo("Tender",tenderS)
                .orderBy("Date", Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        modelList.clear();
                        pd.dismiss();
                        for (DocumentSnapshot doc : queryDocumentSnapshots){
                            TeamLeaderModel model = new TeamLeaderModel(
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
                        adapter = new TeamLeaderCustomAdapter(TeamLeaderViewData.this,modelList);
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
        fStore.collection(cmp+" AddData").whereEqualTo("SearchTeamName",teamNameS.toLowerCase())
                .orderBy("Date", Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        modelList.clear();
                        pd.dismiss();
                        for (DocumentSnapshot doc : queryDocumentSnapshots){
                            TeamLeaderModel model = new TeamLeaderModel(
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
                        adapter = new TeamLeaderCustomAdapter(TeamLeaderViewData.this,modelList);
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

    private void searchDate(String cmp, String date) {

        pd.setTitle("Searching...");
        pd.show();
        fStore.collection(cmp+" AddData").whereEqualTo("Date",date.toLowerCase())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        modelList.clear();
                        pd.dismiss();
                        for (DocumentSnapshot doc : task.getResult()){
                            TeamLeaderModel model = new TeamLeaderModel(
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
                        adapter = new TeamLeaderCustomAdapter(TeamLeaderViewData.this,modelList);
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

                fStore.collection(cmp+" AddData").orderBy("Date", Query.Direction.DESCENDING).get()

                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                modelList.clear();
                                pd.dismiss();
                                for (DocumentSnapshot doc :queryDocumentSnapshots){
                                    TeamLeaderModel model = new TeamLeaderModel(
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
                                adapter = new TeamLeaderCustomAdapter(TeamLeaderViewData.this,modelList);
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