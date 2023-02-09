package com.example.electricalmaterial;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
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

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddDetails extends AppCompatActivity {

    Pattern pattern = Pattern.compile("[^a-z A-Z0-9]");
    String s = "Spacial Symbol does allow";

    //authorization
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String companyEmail;
    String userId;

    //team name
    TextView addTeamName;
    AutoCompleteTextView teamName;
    String textData;
    ArrayAdapter<String> adapter;
    ArrayList<String> spinnerDataList;
    LinearLayout team,teamNameLL;
    EditText teamNameETAdd;
    Button addTeamNameBtn;
    TextView viewTeamName;
    DatabaseReference databaseReference;
    ValueEventListener listener;
    TextView deleteTeamName;

    //line
    TextView addLine;
    AutoCompleteTextView line;
    String lineData;
    ArrayAdapter<String> adapterLine;
    ArrayList<String> spinnerLine;
    LinearLayout lineLL,addLineLL;
    EditText lineETAdd;
    Button addLineBtn;
    TextView viewLine;
    DatabaseReference databaseReferenceLine;
    ValueEventListener listenerLine;
    TextView deleteLine;

    //Tender
    TextView addTender;
    AutoCompleteTextView tender;
    String tenderData;
    ArrayAdapter<String> adapterTender;
    ArrayList<String> spinnerTender;
    LinearLayout tenderLL,addTenderLL;
    EditText tenderETAdd;
    Button addTenderBtn;
    TextView viewTender;
    DatabaseReference databaseReferenceTender;
    ValueEventListener listenerTender;
    TextView deleteTender;

    //Driver
    TextView addDriver;
    AutoCompleteTextView driver;
    String driverData;
    ArrayAdapter<String> adapterDriver;
    ArrayList<String> spinnerDriver;
    LinearLayout driverLL,addDriverLL;
    EditText driverETAdd;
    Button addDriverBtn;
    TextView viewDriver;
    DatabaseReference databaseReferenceDriver;
    ValueEventListener listenerDriver;
    TextView deleteDriver;


    //Vehical
    TextView addVehical;
    AutoCompleteTextView vehical;
    String vehicalData;
    ArrayAdapter<String> adapterVehical;
    ArrayList<String> spinnerVehical;
    LinearLayout vehicalLL,addVehicalLL;
    EditText vehicalETAdd;
    Button addVehicalBtn;
    TextView viewVehical;
    DatabaseReference databaseReferenceVehical;
    ValueEventListener listenerVehical;
    TextView deleteVehical;

    //Receiver
    TextView addReceiver;
    AutoCompleteTextView receiver;
    String receiverData;
    ArrayAdapter<String> adapterReceiver;
    ArrayList<String> spinnerReceiver;
    LinearLayout receiverLL,addReceiverLL;
    EditText receiverETAdd;
    Button addReceiverBtn;
    TextView viewReceiver;
    DatabaseReference databaseReferenceReceiver;
    ValueEventListener listenerReceiver;
    TextView deleteReceiver;

    //State
    TextView addState;
    AutoCompleteTextView state;
    String stateData;
    ArrayAdapter<String> adapterState;
    ArrayList<String> spinnerState;
    LinearLayout stateLL,addStateLL;
    EditText stateETAdd;
    Button addStateBtn;
    TextView viewState;
    DatabaseReference databaseReferenceState;
    ValueEventListener listenerState;
    TextView deleteState;

    //District
    TextView addDistrict;
    AutoCompleteTextView district;
    String districtData;
    ArrayAdapter<String> adapterDistrict;
    ArrayList<String> spinnerDistrict;
    LinearLayout districtLL,addDistrictLL;
    EditText districtETAdd;
    Button addDistrictBtn;
    TextView viewDistrict;
    DatabaseReference databaseReferenceDistrict;
    ValueEventListener listenerDistrict;
    TextView deleteDistrict;

    //Taluka
    TextView addTaluka;
    AutoCompleteTextView taluka;
    String talukaData;
    ArrayAdapter<String> adapterTaluka;
    ArrayList<String> spinnerTaluka;
    LinearLayout talukaLL,addTalukaLL;
    EditText talukaETAdd;
    Button addTalukaBtn;
    TextView viewTaluka;
    DatabaseReference databaseReferenceTaluka;
    ValueEventListener listenerTaluka;
    TextView deleteTaluka;

    //Material
    TextView addMaterial;
    AutoCompleteTextView material;
    String materialData;
    ArrayAdapter<String> adapterMaterial;
    ArrayList<String> spinnerMaterial;
    LinearLayout materialLL,addMaterialLL;
    EditText materialETAdd;
    Button addMaterialBtn;
    TextView viewMaterial;
    DatabaseReference databaseReferenceMaterial;
    ValueEventListener listenerMaterial;
    TextView deleteMaterial;

    //Center
    TextView addCenter;
    AutoCompleteTextView center;
    String centerData;
    ArrayAdapter<String> adapterCenter;
    ArrayList<String> spinnerCenter;
    LinearLayout centerLL,addCenterLL;
    EditText centerETAdd;
    Button addCenterBtn;
    TextView viewCenter;
    DatabaseReference databaseReferenceCenter;
    ValueEventListener listenerCenter;
    TextView deleteCenter;

    //Village
    TextView addVillage;
    AutoCompleteTextView village;
    String villageData;
    ArrayAdapter<String> adapterVillage;
    ArrayList<String> spinnerVillage;
    LinearLayout villageLL,addVillageLL;
    EditText villageETAdd;
    Button addVillageBtn;
    TextView viewVillage;
    DatabaseReference databaseReferenceVillage;
    ValueEventListener listenerVillage;
    TextView deleteVillage;

    //Unit
    TextView addUnit;
    AutoCompleteTextView unit;
    String unitData;
    ArrayAdapter<String> adapterUnit;
    ArrayList<String> spinnerUnit;
    LinearLayout unitLL,addUnitLL;
    EditText unitETAdd;
    Button addUnitBtn;
    TextView viewUnit;
    DatabaseReference databaseReferenceUnit;
    ValueEventListener listenerUnit;
    TextView deleteUnit;


    //WorkDone
    TextView addWorkDone;
    AutoCompleteTextView workDone;
    String workDoneData;
    ArrayAdapter<String> adapterWorkDone;
    ArrayList<String> spinnerWorkDone;
    LinearLayout workDoneLL,addWorkDoneLL;
    EditText workDoneETAdd;
    Button addWorkDoneBtn;
    TextView viewWorkDone;
    DatabaseReference databaseReferenceWorkDone;
    ValueEventListener listenerWorkDone;
    TextView deleteWorkDone;

    Timer time;
    ProgressDialog pd;

    TextView tv;

    LinearLayout pairLL;



    AlertDialog.Builder builder;


    TextView kgToNo;

    TextView materialPair;


//    UPDATE DATA

    TextView updateMaterial;

    String updateMaterialName;



//    Data form added material for changes material and additional
    List<AdminModel> modelList = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    AddedDataChangesCustomAdapter adminCustomAdapter;

    @SuppressLint({"SourceLockedOrientationActivity", "MissingInflatedId"})
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_details);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        pd = ProgressDialog.show(this,"Loading...","Please Wait",false,false);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        builder = new AlertDialog.Builder(this);

        time = new Timer();
        time.schedule(new TimerTask() {
            @Override
            public void run() {
                pd.dismiss();
            }
        },3000);
        //Auth
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = mAuth.getCurrentUser().getUid();

        ActionBar bar = getSupportActionBar();
        assert bar != null;
        bar.setTitle("DATA LIBRARY");

        //Team Name
        team = findViewById(R.id.teamLL);
        teamNameLL = findViewById(R.id.addTeamLL);
        teamName = findViewById(R.id.teamName);
        addTeamName = findViewById(R.id.addTeamName);
        teamNameETAdd = findViewById(R.id.teamNameETAdd);
        addTeamNameBtn = findViewById(R.id.addTeamNameBtn);
        viewTeamName = findViewById(R.id.viewTeamName);
        deleteTeamName = findViewById(R.id.deleteTeamName);

        //Line
        addLine = findViewById(R.id.addLine);
        line = findViewById(R.id.lineSpineer);
        lineLL = findViewById(R.id.lineLL);
        addLineLL = findViewById(R.id.addLineLL);
        lineETAdd = findViewById(R.id.lineETAdd);
        addLineBtn = findViewById(R.id.lineBtn);
        viewLine = findViewById(R.id.viewLine);
        deleteLine = findViewById(R.id.deleteLine);

        //Tender
        addTender = findViewById(R.id.addTender);
        tender = findViewById(R.id.tenderSpineer);
        tenderLL = findViewById(R.id.tenderLL);
        addTenderLL = findViewById(R.id.addTenderLL);
        tenderETAdd = findViewById(R.id.tenderETAdd);
        addTenderBtn = findViewById(R.id.tenderBtn);
        viewTender = findViewById(R.id.viewTender);
        deleteTender = findViewById(R.id.deleteTender);

        //Driver
        addDriver = findViewById(R.id.addDriver);
        driver = findViewById(R.id.driverSpineer);
        driverLL = findViewById(R.id.driverLL);
        addDriverLL = findViewById(R.id.addDriverLL);
        driverETAdd = findViewById(R.id.driverETAdd);
        addDriverBtn = findViewById(R.id.driverBtn);
        viewDriver = findViewById(R.id.viewDriver);
        deleteDriver = findViewById(R.id.deleteDriver);

        //Vehical
        addVehical = findViewById(R.id.addVehical);
        vehical = findViewById(R.id.vehicalSpineer);
        vehicalLL = findViewById(R.id.vehicalLL);
        addVehicalLL = findViewById(R.id.addVehicalLL);
        vehicalETAdd = findViewById(R.id.vehicalETAdd);
        addVehicalBtn = findViewById(R.id.vehicalBtn);
        viewVehical = findViewById(R.id.viewVehical);
        deleteVehical = findViewById(R.id.deleteVehical);

        //Receiver
        addReceiver = findViewById(R.id.addReceiver);
        receiver = findViewById(R.id.receiverSpineer);
        receiverLL = findViewById(R.id.receiverLL);
        addReceiverLL = findViewById(R.id.addReceiverLL);
        receiverETAdd = findViewById(R.id.receiverETAdd);
        addReceiverBtn = findViewById(R.id.receiverBtn);
        viewReceiver = findViewById(R.id.viewReceiver);
        deleteReceiver = findViewById(R.id.deleteReceiver);

        //Material
        addMaterial = findViewById(R.id.addMaterial);
        material = findViewById(R.id.materialSpineer);
        materialLL = findViewById(R.id.materialLL);
        addMaterialLL = findViewById(R.id.addMaterialLL);
        materialETAdd = findViewById(R.id.materialETAdd);
        addMaterialBtn = findViewById(R.id.materialBtn);
        viewMaterial = findViewById(R.id.viewMaterial);
        deleteMaterial = findViewById(R.id.deleteMaterial);
        updateMaterial = findViewById(R.id.updateMaterial); // update material id find

        //Center
        addCenter = findViewById(R.id.addCenter);
        center = findViewById(R.id.centerSpineer);
        centerLL = findViewById(R.id.centerLL);
        addCenterLL = findViewById(R.id.addCenterLL);
        centerETAdd = findViewById(R.id.centerETAdd);
        addCenterBtn = findViewById(R.id.centerBtn);
        viewCenter = findViewById(R.id.viewCenter);
        deleteCenter = findViewById(R.id.deleteCenter);

        //Village
        addVillage = findViewById(R.id.addVillage);
        village = findViewById(R.id.villageSpineer);
        villageLL = findViewById(R.id.villageLL);
        addVillageLL = findViewById(R.id.addVillageLL);
        villageETAdd = findViewById(R.id.villageETAdd);
        addVillageBtn = findViewById(R.id.villageBtn);
        viewVillage = findViewById(R.id.viewVillage);
        deleteVillage = findViewById(R.id.deleteVillage);

        //District
        addDistrict = findViewById(R.id.addDistrict);
        district = findViewById(R.id.districtSpineer);
        districtLL = findViewById(R.id.districtLL);
        addDistrictLL = findViewById(R.id.addDistrictLL);
        districtETAdd = findViewById(R.id.districtETAdd);
        addDistrictBtn = findViewById(R.id.districtBtn);
        viewDistrict = findViewById(R.id.viewDistrict);
        deleteDistrict = findViewById(R.id.deleteDistrict);


        //State
        addState = findViewById(R.id.addState);
        state = findViewById(R.id.stateSpineer);
        stateLL = findViewById(R.id.stateLL);
        addStateLL = findViewById(R.id.addStateLL);
        stateETAdd = findViewById(R.id.stateETAdd);
        addStateBtn = findViewById(R.id.stateBtn);
        viewState = findViewById(R.id.viewState);
        deleteState = findViewById(R.id.deleteState);


        //Taluka
        addTaluka = findViewById(R.id.addTaluka);
        taluka = findViewById(R.id.talukaSpineer);
        talukaLL = findViewById(R.id.talukaLL);
        addTalukaLL = findViewById(R.id.addTalukaLL);
        talukaETAdd = findViewById(R.id.talukaETAdd);
        addTalukaBtn = findViewById(R.id.talukaBtn);
        viewTaluka = findViewById(R.id.viewTaluka);
        deleteTaluka = findViewById(R.id.deleteTaluka);


        //Unit
        addUnit = findViewById(R.id.addUnit);
        unit = findViewById(R.id.unitSpineer);
        unitLL = findViewById(R.id.unitLL);
        addUnitLL = findViewById(R.id.addUnitLL);
        unitETAdd = findViewById(R.id.unitETAdd);
        addUnitBtn = findViewById(R.id.unitBtn);
        viewUnit = findViewById(R.id.viewUnit);
        deleteUnit = findViewById(R.id.deleteUnit);


        //WorkDone
        addWorkDone = findViewById(R.id.addWorkDone);
        workDone = findViewById(R.id.workDoneSpineer);
        workDoneLL = findViewById(R.id.workDoneLL);
        addWorkDoneLL = findViewById(R.id.addWorkDoneLL);
        workDoneETAdd = findViewById(R.id.workDoneETAdd);
        addWorkDoneBtn = findViewById(R.id.workDoneBtn);
        viewWorkDone = findViewById(R.id.viewWorkDone);
        deleteWorkDone = findViewById(R.id.deleteWorkDone);


        //TextView
        tv = findViewById(R.id.makePair);
//        pairLL = findViewById(R.id.pairLL);




//        Retrive data of added material
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        modelList.clear();




        //KG TO NO
        kgToNo = findViewById(R.id.kgToNo);
        kgToNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),KgToNo.class));
                finish();
            }
        });

        materialPair = findViewById(R.id.materialPair);
        materialPair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MaterialPair.class));
                finish();
            }
        });

        DocumentReference documentReference = fStore.collection("Users")
                .document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                companyEmail = value.getString("companyEmail");
                String cmp = companyEmail;
                companyEmail = companyEmail.replace("@","");
                companyEmail = companyEmail.replace(".","");

                showData(cmp);

                        //Team Name
                        addTeamName.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                team.setVisibility(View.GONE);
                                teamNameLL.setVisibility(View.VISIBLE);
                            }
                        });
                        viewTeamName.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                team.setVisibility(View.VISIBLE);
                                teamNameLL.setVisibility(View.GONE);
                            }
                        });
                        databaseReference = FirebaseDatabase.getInstance().getReference(companyEmail+" teamName");
                        spinnerDataList = new ArrayList<>();
                        adapter = new ArrayAdapter<String>(AddDetails.this,R.layout.support_simple_spinner_dropdown_item,spinnerDataList);
                        teamName.setAdapter(adapter);
                        retrieveData();
                        addTeamNameBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                textData = teamNameETAdd.getText().toString().trim();

                                if (textData.isEmpty()){
                                    teamNameETAdd.setError("Required");
                                    teamNameETAdd.requestFocus();
                                }
                                else if (SpecialCharacter(textData)){
                                    teamNameETAdd.setError(s);
                                    teamNameETAdd.requestFocus();
                                }
                                if (textData.equals(adapter.toString())){
                                    Toast.makeText(AddDetails.this, "Already Have this", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    databaseReference.child(textData).setValue(textData)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    teamNameETAdd.setText(" ");
                                                    spinnerDataList.clear();
                                                    retrieveData();
                                                    adapter.notifyDataSetChanged();
                                                    Toast.makeText(getApplicationContext(), "Team name inserted", Toast.LENGTH_SHORT).show();
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(getApplicationContext(), "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();

                                        }
                                    });
                                }
                            }
                        });
                        spinnerDataList.clear();
                        deleteTeamName.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String name = teamName.getText().toString().trim();
                                if (name.isEmpty()){
                                    showMessage("Empty!");
                                }
                                else {
                                    String[] options = {"No", "Yes"};
                                    builder.setTitle("Are you Sure ?");
                                    builder.setIcon(R.drawable.ic_action_delete);
                                    builder.setItems(options, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (which == 0) {
                                                String not = "Cancel";
                                                Toast.makeText(getApplicationContext(), not.toUpperCase(), Toast.LENGTH_SHORT).show();
                                            } else if (name.isEmpty()) {
                                                showMessage("Team Name is empty");
                                            } else {
                                                spinnerDataList.clear();
                                                databaseReference.child(name).removeValue();
                                                adapter.notifyDataSetChanged();
                                                teamName.setText(" ");
                                                showMessage(name + " is remove");
                                            }
                                        }
                                    }).create().show();

                                }

                            }
                        });
                        spinnerDataList.clear();


                        //Line
                addLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lineLL.setVisibility(View.GONE);
                addLineLL.setVisibility(View.VISIBLE);
            }
        });
                viewLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lineLL.setVisibility(View.VISIBLE);
                addLineLL.setVisibility(View.GONE);

            }
        });
                databaseReferenceLine = FirebaseDatabase.getInstance().getReference(companyEmail+" Line");
                spinnerLine = new ArrayList<>();
                adapterLine = new ArrayAdapter<String>(AddDetails.this,R.layout.support_simple_spinner_dropdown_item,spinnerLine);
                line.setAdapter(adapterLine);
                retrieveDataLine();
                addLineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lineData = lineETAdd.getText().toString().trim();
                Matcher matcher = pattern.matcher(lineData);
                boolean isStringContainsSpecialCharacter = matcher.find();
                if (lineData.isEmpty()){
                    lineETAdd.setError("Required");
                    lineETAdd.requestFocus();
                }
               else if(isStringContainsSpecialCharacter){
                    lineETAdd.setError(s);
                    lineETAdd.requestFocus();
                }
                else {
                    databaseReferenceLine.child(lineData).setValue(lineData)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    lineETAdd.setText(" ");
                                    spinnerLine.clear();
                                    retrieveDataLine();
                                    adapterLine.notifyDataSetChanged();
                                    Toast.makeText(getApplicationContext(), "Line Inserted", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
                }

            }
        });
                spinnerLine.clear();
                deleteLine.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = line.getText().toString().trim();
                        if (name.isEmpty()){
                            showMessage("Empty!");
                        }
                        else {
                            String[] options = {"No", "Yes"};
                            builder.setTitle("Are you Sure ?");
                            builder.setIcon(R.drawable.ic_action_delete);
                            builder.setItems(options, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (which == 0) {
                                        String not = "Cancel";
                                        Toast.makeText(getApplicationContext(), not.toUpperCase(), Toast.LENGTH_SHORT).show();
                                    } else if (name.isEmpty()) {
                                        showMessage("Empty");
                                    } else {
                                        spinnerLine.clear();
                                        databaseReferenceLine.child(name).removeValue();
                                        adapterLine.notifyDataSetChanged();
                                        line.setText(" ");
                                        showMessage(name + " is remove");
                                    }
                                }
                            }).create().show();
                        }
                    }
                });
                spinnerLine.clear();


                //Tender
                addTender.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tenderLL.setVisibility(View.GONE);
                        addTenderLL.setVisibility(View.VISIBLE);
                    }
                });
                viewTender.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tenderLL.setVisibility(View.VISIBLE);
                        addTenderLL.setVisibility(View.GONE);
                    }
                });
                databaseReferenceTender = FirebaseDatabase.getInstance().getReference(companyEmail+" Tender");
                spinnerTender = new ArrayList<>();
                adapterTender = new ArrayAdapter<String>(AddDetails.this,R.layout.support_simple_spinner_dropdown_item,spinnerTender);
                tender.setAdapter(adapterTender);
                retrieveDataTender();
                addTenderBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tenderData = tenderETAdd.getText().toString().trim();
                        if (tenderData.isEmpty()){
                            tenderETAdd.setError("Required");
                            tenderETAdd.requestFocus();
                        }
                        else if (SpecialCharacter(tenderData)){
                            tenderETAdd.setError(s);
                            tenderETAdd.requestFocus();
                        }
                        else {
                            databaseReferenceTender.child(tenderData).setValue(tenderData)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            tenderETAdd.setText(" ");
                                            spinnerTender.clear();
                                            retrieveDataTender();
                                            adapterTender.notifyDataSetChanged();
                                           showMessage("Tender is inserted");
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getApplicationContext(), "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            });
                        }
                    }
                });
                spinnerTender.clear();
                deleteTender.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = tender.getText().toString().trim();
                        if (name.isEmpty()){
                            showMessage("Empty!");
                        }
                        else {
                            String[] options = {"No", "Yes"};
                            builder.setTitle("Are you Sure ?");
                            builder.setIcon(R.drawable.ic_action_delete);
                            builder.setItems(options, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (which == 0) {
                                        String not = "Cancel";
                                        Toast.makeText(getApplicationContext(), not.toUpperCase(), Toast.LENGTH_SHORT).show();
                                    } else if (name.isEmpty()) {
                                        showMessage("Empty");
                                    } else {
                                        spinnerTender.clear();
                                        databaseReferenceTender.child(name).removeValue();
                                        adapterTender.notifyDataSetChanged();
                                        tender.setText(" ");
                                        showMessage(name + " is remove");
                                    }
                                }
                            }).create().show();
                        }


                    }
                });
                spinnerTender.clear();

                //Driver
                addDriver.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        driverLL.setVisibility(View.GONE);
                        addDriverLL.setVisibility(View.VISIBLE);
                    }
                });
                viewDriver.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        driverLL.setVisibility(View.VISIBLE);
                        addDriverLL.setVisibility(View.GONE);
                    }
                });
                databaseReferenceDriver = FirebaseDatabase.getInstance().getReference(companyEmail+" Driver");
                spinnerDriver = new ArrayList<>();
                adapterDriver = new ArrayAdapter<String>(AddDetails.this,R.layout.support_simple_spinner_dropdown_item,spinnerDriver);
                driver.setAdapter(adapterDriver);
                retrieveDataDriver();
                addDriverBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        driverData = driverETAdd.getText().toString().trim();
                        if (driverData.isEmpty()){
                            driverETAdd.setError("Required");
                            driverETAdd.requestFocus();
                        }
                        else if (SpecialCharacter(driverData)){
                            driverETAdd.setError(s);
                            driverETAdd.requestFocus();
                        }
                        else {
                            databaseReferenceDriver.child(driverData).setValue(driverData)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            driverETAdd.setText(" ");
                                            spinnerDriver.clear();
                                            retrieveDataDriver();
                                            adapterDriver.notifyDataSetChanged();
                                            showMessage("Driver is inserted");
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getApplicationContext(), "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            });
                        }
                    }
                });
                spinnerDriver.clear();
                deleteDriver.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = driver.getText().toString().trim();
                        if (name.isEmpty()){
                            showMessage("Empty!");
                        }
                        else {
                            String [] options = {"No","Yes"};
                            builder.setTitle("Are you Sure ?");
                            builder.setIcon(R.drawable.ic_action_delete);
                            builder.setItems(options, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (which==0){
                                        String not = "Cancel";
                                        Toast.makeText(getApplicationContext(), not.toUpperCase(), Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        spinnerDriver.clear();
                                        databaseReferenceDriver.child(name).removeValue();
                                        adapterDriver.notifyDataSetChanged();
                                        driver.setText(" ");
                                        showMessage(name+" is remove");
                                    }

                                }
                            }).create().show();
                        }

                    }
                });
                spinnerDriver.clear();

                //Vehical
                addVehical.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        vehicalLL.setVisibility(View.GONE);
                        addVehicalLL.setVisibility(View.VISIBLE);
                    }
                });
                viewVehical.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        vehicalLL.setVisibility(View.VISIBLE);
                        addVehicalLL.setVisibility(View.GONE);
                    }
                });
                databaseReferenceVehical = FirebaseDatabase.getInstance().getReference(companyEmail+" Vehical");
                spinnerVehical = new ArrayList<>();
                adapterVehical = new ArrayAdapter<String>(AddDetails.this,R.layout.support_simple_spinner_dropdown_item,spinnerVehical);
                vehical.setAdapter(adapterVehical);
                retrieveDataVehical();
                addVehicalBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        vehicalData = vehicalETAdd.getText().toString().trim();
                        if (vehicalData.isEmpty()){
                            vehicalETAdd.setError("Required");
                            vehicalETAdd.requestFocus();
                        }
                        else if (SpecialCharacter(vehicalData)){
                            vehicalETAdd.setError(s);
                            vehicalETAdd.requestFocus();
                        }
                        else {
                            databaseReferenceVehical.child(vehicalData).setValue(vehicalData)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            vehicalETAdd.setText(" ");
                                            spinnerVehical.clear();
                                            retrieveDataVehical();
                                            adapterVehical.notifyDataSetChanged();
                                            showMessage("Vehical is inserted");
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getApplicationContext(), "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            });
                        }
                    }
                });
                spinnerVehical.clear();
                deleteVehical.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = vehical.getText().toString().trim();

                        if (name.isEmpty()){
                            showMessage("Empty!");
                        }
                        else {
                            String[] options = {"No", "Yes"};
                            builder.setTitle("Are you Sure ?");
                            builder.setIcon(R.drawable.ic_action_delete);
                            builder.setItems(options, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (which == 0) {
                                        String not = "Cancel";
                                        Toast.makeText(getApplicationContext(), not.toUpperCase(), Toast.LENGTH_SHORT).show();
                                    } else if (name.isEmpty()) {
                                        showMessage("Empty");
                                    } else {
                                        spinnerVehical.clear();
                                        databaseReferenceVehical.child(name).removeValue();
                                        adapterVehical.notifyDataSetChanged();
                                        vehical.setText(" ");
                                        showMessage(name + " is remove");
                                    }

                                }
                            }).create().show();

                        }

                    }
                });
                spinnerVehical.clear();

                //Receiver
                addReceiver.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        receiverLL.setVisibility(View.GONE);
                        addReceiverLL.setVisibility(View.VISIBLE);
                    }
                });
                viewReceiver.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        receiverLL.setVisibility(View.VISIBLE);
                        addReceiverLL.setVisibility(View.GONE);
                    }
                });
                databaseReferenceReceiver = FirebaseDatabase.getInstance().getReference(companyEmail+" Receiver");
                spinnerReceiver = new ArrayList<>();
                adapterReceiver = new ArrayAdapter<String>(AddDetails.this,R.layout.support_simple_spinner_dropdown_item,spinnerReceiver);
                receiver.setAdapter(adapterReceiver);
                retrieveDataReceiver();
                addReceiverBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        receiverData = receiverETAdd.getText().toString().trim();
                        if (receiverData.isEmpty()){
                            receiverETAdd.setError("Required");
                            receiverETAdd.requestFocus();
                        }
                        else if (SpecialCharacter(receiverData)){
                            receiverETAdd.setError(s);
                            receiverETAdd.requestFocus();
                        }
                        else {
                            databaseReferenceReceiver.child(receiverData.toLowerCase()).setValue(receiverData.toLowerCase())
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            receiverETAdd.setText(" ");
                                            spinnerReceiver.clear();
                                            retrieveDataReceiver();
                                            adapterReceiver.notifyDataSetChanged();
                                            showMessage("Receiver is inserted");
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getApplicationContext(), "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            });
                        }
                    }
                });
                spinnerReceiver.clear();
                deleteReceiver.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = receiver.getText().toString().trim();
                        if (name.isEmpty()){
                            showMessage("Empty!");
                        }
                        else {
                            String[] options = {"No", "Yes"};
                            builder.setTitle("Are you Sure ?");
                            builder.setIcon(R.drawable.ic_action_delete);
                            builder.setItems(options, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (which == 0) {
                                        String not = "Cancel";
                                        Toast.makeText(getApplicationContext(), not.toUpperCase(), Toast.LENGTH_SHORT).show();
                                    } else if (name.isEmpty()) {
                                        showMessage("Empty");
                                    } else {
                                        spinnerReceiver.clear();
                                        databaseReferenceReceiver.child(name).removeValue();
                                        adapterReceiver.notifyDataSetChanged();
                                        receiver.setText(" ");
                                        showMessage(name + " is remove");
                                    }

                                }
                            }).create().show();
                        }

                    }
                });
                spinnerReceiver.clear();

                //Material
                addMaterial.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        materialLL.setVisibility(View.GONE);
                        addMaterialLL.setVisibility(View.VISIBLE);
                    }
                });
                viewMaterial.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        materialLL.setVisibility(View.VISIBLE);
                        addMaterialLL.setVisibility(View.GONE);
                    }
                });
                databaseReferenceMaterial = FirebaseDatabase.getInstance().getReference(companyEmail+" Material");
                spinnerMaterial = new ArrayList<>();
                adapterMaterial = new ArrayAdapter<String>(AddDetails.this,R.layout.support_simple_spinner_dropdown_item,spinnerMaterial);
                material.setAdapter(adapterMaterial);
                retrieveDataMaterial();
                addMaterialBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        materialData = materialETAdd.getText().toString().trim();
                        String btn = addMaterialBtn.getText().toString().trim();
                        if (btn.equalsIgnoreCase("Add")) {
                            if (materialData.isEmpty()) {
                                materialETAdd.setError("Required");
                                materialETAdd.requestFocus();
                            } else if (SpecialCharacter(materialData)) {
                                materialETAdd.setError(s);
                                materialETAdd.requestFocus();
                            } else {
                                databaseReferenceMaterial.child(materialData).setValue(materialData)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                materialETAdd.setText(" ");
                                                spinnerMaterial.clear();
                                                retrieveDataMaterial();
                                                adapterMaterial.notifyDataSetChanged();
                                                showMessage("Material is inserted");
                                                uploadMaterial(materialData, cmp);
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(getApplicationContext(), "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();

                                            }
                                        });
                            }
                        }
                        else if (btn.equalsIgnoreCase("UPDATE")){
                            if (materialData.isEmpty()) {
                                materialETAdd.setError("Required");
                                materialETAdd.requestFocus();
                            } else if (SpecialCharacter(materialData)) {
                                materialETAdd.setError(s);
                                materialETAdd.requestFocus();
                            } else {
                                databaseReferenceMaterial.child(materialData).setValue(materialData)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                materialETAdd.setText(" ");
                                                spinnerMaterial.clear();
                                                retrieveDataMaterial();
                                                adapterMaterial.notifyDataSetChanged();
                                                showMessage("Material is inserted");
                                                uploadMaterial(materialData, cmp);

                                                spinnerMaterial.clear();
                                                databaseReferenceMaterial.child(updateMaterialName).removeValue();
                                                adapterMaterial.notifyDataSetChanged();
                                                material.setText(" ");
                                                showMessage(updateMaterialName + " is remove");
                                                deleteData(cmp,updateMaterialName);


                                                updateMaterialNameData(materialData,updateMaterialName,cmp);




                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(getApplicationContext(), "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }
                        }
                    }
                });
                spinnerMaterial.clear();
                deleteMaterial.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = material.getText().toString().trim();
                        if (name.isEmpty()){
                            showMessage("Empty!");
                        }
                        else {
                            String[] options = {"No", "Yes"};
                            builder.setTitle("Are you Sure ?");
                            builder.setIcon(R.drawable.ic_action_delete);
                            builder.setItems(options, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (name.isEmpty()) {
                                        showMessage("Empty");
                                    }
                                    else  if (which == 0) {
                                        String not = "Cancel";
                                        Toast.makeText(getApplicationContext(), not.toUpperCase(), Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        spinnerMaterial.clear();
                                        databaseReferenceMaterial.child(name).removeValue();
                                        adapterMaterial.notifyDataSetChanged();
                                        material.setText(" ");
                                        showMessage(name + " is remove");
                                        deleteData(cmp,name);
                                    }
                                }
                            }).create().show();
                        }
                    }
                });
                spinnerMaterial.clear();

                updateMaterial.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        updateMaterialName = material.getText().toString().trim();
                        if (updateMaterialName.isEmpty()){
                            showMessage("Select Material");
                        }
                        else{
                            materialLL.setVisibility(View.GONE);
                            addMaterialLL.setVisibility(View.VISIBLE);
                            addMaterialBtn.setText("UPDATE");
                            materialETAdd.setText(updateMaterialName);

                        }
                    }
                });



                //Center
                addCenter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        centerLL.setVisibility(View.GONE);
                        addCenterLL.setVisibility(View.VISIBLE);
                    }
                });
                viewCenter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        centerLL.setVisibility(View.VISIBLE);
                        addCenterLL.setVisibility(View.GONE);
                    }
                });
                databaseReferenceCenter = FirebaseDatabase.getInstance().getReference(companyEmail+" Center");
                spinnerCenter = new ArrayList<>();
                adapterCenter = new ArrayAdapter<String>(AddDetails.this,R.layout.support_simple_spinner_dropdown_item,spinnerCenter);
                center.setAdapter(adapterCenter);
                retrieveDataCenter();
                addCenterBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        centerData = centerETAdd.getText().toString().trim();
                        if (centerData.isEmpty()){
                            centerETAdd.setError("Required");
                            centerETAdd.requestFocus();
                        }
                        else if (SpecialCharacter(centerData)){
                            centerETAdd.setError(s);
                            centerETAdd.requestFocus();
                        }
                        else {
                            databaseReferenceCenter.child(centerData).setValue(centerData)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            centerETAdd.setText(" ");
                                            spinnerCenter.clear();
                                            retrieveDataCenter();
                                            adapterCenter.notifyDataSetChanged();
                                            showMessage("Center is inserted");
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getApplicationContext(), "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            });
                        }
                    }
                });
                spinnerCenter.clear();
                deleteCenter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = center.getText().toString().trim();
                        if (name.isEmpty()){
                            showMessage("Empty!");
                        }
                        else {
                            String[] options = {"No", "Yes"};
                            builder.setTitle("Are you Sure ?");
                            builder.setIcon(R.drawable.ic_action_delete);
                            builder.setItems(options, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (which == 0) {
                                        String not = "Cancel";
                                        Toast.makeText(getApplicationContext(), not.toUpperCase(), Toast.LENGTH_SHORT).show();
                                    } else if (name.isEmpty()) {
                                        showMessage("Empty");
                                    } else {
                                        spinnerCenter.clear();
                                        databaseReferenceCenter.child(name).removeValue();
                                        adapterCenter.notifyDataSetChanged();
                                        center.setText(" ");
                                        showMessage(name + " is remove");
                                    }

                                }
                            }).create().show();
                        }

                    }
                });
                spinnerCenter.clear();


                //Village
                addVillage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        villageLL.setVisibility(View.GONE);
                        addVillageLL.setVisibility(View.VISIBLE);
                    }
                });
                viewVillage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        villageLL.setVisibility(View.VISIBLE);
                        addVillageLL.setVisibility(View.GONE);
                    }
                });
                databaseReferenceVillage = FirebaseDatabase.getInstance().getReference(companyEmail+" Village");
                spinnerVillage = new ArrayList<>();
                adapterVillage = new ArrayAdapter<String>(AddDetails.this,R.layout.support_simple_spinner_dropdown_item,spinnerVillage);
                village.setAdapter(adapterVillage);
                retrieveDataVillage();
                addVillageBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        villageData = villageETAdd.getText().toString().trim();
                        if (villageData.isEmpty()){
                            villageETAdd.setError("Required");
                            villageETAdd.requestFocus();
                        }
                        else if (SpecialCharacter(villageData)){
                            villageETAdd.setError(s);
                            villageETAdd.requestFocus();
                        }
                        else {
                            databaseReferenceVillage.child(villageData).setValue(villageData)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            villageETAdd.setText(" ");
                                            spinnerVillage.clear();
                                            retrieveDataVillage();
                                            adapterVillage.notifyDataSetChanged();
                                            showMessage("Village is inserted");
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getApplicationContext(), "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            });
                        }
                    }
                });
                spinnerVillage.clear();
                deleteVillage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = village.getText().toString().trim();
                        if (name.isEmpty()){
                            showMessage("Empty!");
                        }
                        else {
                            String[] options = {"No", "Yes"};
                            builder.setTitle("Are you Sure ?");
                            builder.setIcon(R.drawable.ic_action_delete);
                            builder.setItems(options, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (which == 0) {
                                        String not = "Cancel";
                                        Toast.makeText(getApplicationContext(), not.toUpperCase(), Toast.LENGTH_SHORT).show();
                                    } else if (name.isEmpty()) {
                                        showMessage("Empty");
                                    } else {
                                        spinnerVillage.clear();
                                        databaseReferenceVillage.child(name).removeValue();
                                        adapterVillage.notifyDataSetChanged();
                                        village.setText(" ");
                                        showMessage(name + " is remove");
                                    }

                                }
                            }).create().show();
                        }

                    }
                });
                spinnerVillage.clear();

                //District
                addDistrict.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        districtLL.setVisibility(View.GONE);
                        addDistrictLL.setVisibility(View.VISIBLE);
                    }
                });
                viewDistrict.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        districtLL.setVisibility(View.VISIBLE);
                        addDistrictLL.setVisibility(View.GONE);
                    }
                });
                databaseReferenceDistrict = FirebaseDatabase.getInstance().getReference(companyEmail+" District");
                spinnerDistrict = new ArrayList<>();
                adapterDistrict = new ArrayAdapter<String>(AddDetails.this,R.layout.support_simple_spinner_dropdown_item,spinnerDistrict);
                district.setAdapter(adapterDistrict);
                retrieveDataDistrict();
                addDistrictBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        districtData = districtETAdd.getText().toString().trim();
                        if (districtData.isEmpty()){
                            districtETAdd.setError("Required");
                            districtETAdd.requestFocus();
                        }
                        else if (SpecialCharacter(districtData)){
                            districtETAdd.setError(s);
                            districtETAdd.requestFocus();
                        }
                        else {
                            databaseReferenceDistrict.child(districtData).setValue(districtData)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            districtETAdd.setText(" ");
                                            spinnerDistrict.clear();
                                            retrieveDataDistrict();
                                            adapterDistrict.notifyDataSetChanged();
                                            showMessage("District is inserted");
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getApplicationContext(), "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            });
                        }
                    }
                });
                spinnerDistrict.clear();
                deleteDistrict.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = district.getText().toString().trim();
                        if (name.isEmpty()){
                            showMessage("Empty!");
                        }
                        else {
                            String[] options = {"No", "Yes"};
                            builder.setTitle("Are you Sure ?");
                            builder.setIcon(R.drawable.ic_action_delete);
                            builder.setItems(options, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (which == 0) {
                                        String not = "Cancel";
                                        Toast.makeText(getApplicationContext(), not.toUpperCase(), Toast.LENGTH_SHORT).show();
                                    } else if (name.isEmpty()) {
                                        showMessage("Empty");
                                    } else {
                                        spinnerDistrict.clear();
                                        databaseReferenceDistrict.child(name).removeValue();
                                        adapterDistrict.notifyDataSetChanged();
                                        district.setText(" ");
                                        showMessage(name + " is remove");
                                    }

                                }
                            }).create().show();
                        }
                    }
                });
                spinnerDistrict.clear();

                //State
                addState.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        stateLL.setVisibility(View.GONE);
                        addStateLL.setVisibility(View.VISIBLE);
                    }
                });
                viewState.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        stateLL.setVisibility(View.VISIBLE);
                        addStateLL.setVisibility(View.GONE);
                    }
                });
                databaseReferenceState = FirebaseDatabase.getInstance().getReference(companyEmail+" State");
                spinnerState = new ArrayList<>();
                adapterState = new ArrayAdapter<String>(AddDetails.this,R.layout.support_simple_spinner_dropdown_item,spinnerState);
                state.setAdapter(adapterState);
                retrieveDataState();
                addStateBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        stateData = stateETAdd.getText().toString().trim();
                        if (stateData.isEmpty()){
                            stateETAdd.setError("Required");
                            stateETAdd.requestFocus();
                        }
                        else if (SpecialCharacter(stateData)){
                            stateETAdd.setError(s);
                            stateETAdd.requestFocus();
                        }
                        else {
                            databaseReferenceState.child(stateData).setValue(stateData)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            stateETAdd.setText(" ");
                                            spinnerState.clear();
                                            retrieveDataState();
                                            adapterState.notifyDataSetChanged();
                                            showMessage("State is inserted");
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getApplicationContext(), "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            });
                        }
                    }
                });
                spinnerState.clear();
                deleteState.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = state.getText().toString().trim();
                        if (name.isEmpty()){
                            showMessage("Empty!");
                        }
                        else {
                            String[] options = {"No", "Yes"};
                            builder.setTitle("Are you Sure ?");
                            builder.setIcon(R.drawable.ic_action_delete);
                            builder.setItems(options, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (which == 0) {
                                        String not = "Cancel";
                                        Toast.makeText(getApplicationContext(), not.toUpperCase(), Toast.LENGTH_SHORT).show();
                                    } else if (name.isEmpty()) {
                                        showMessage("Empty");
                                    } else {
                                        spinnerState.clear();
                                        databaseReferenceState.child(name).removeValue();
                                        adapterState.notifyDataSetChanged();
                                        state.setText(" ");
                                        showMessage(name + " is remove");
                                    }


                                }
                            }).create().show();
                        }

                    }
                });
                spinnerState.clear();


                //Taluka
                addTaluka.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        talukaLL.setVisibility(View.GONE);
                        addTalukaLL.setVisibility(View.VISIBLE);
                    }
                });
                viewTaluka.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        talukaLL.setVisibility(View.VISIBLE);
                        addTalukaLL.setVisibility(View.GONE);
                    }
                });
                databaseReferenceTaluka = FirebaseDatabase.getInstance().getReference(companyEmail+" Taluka");
                spinnerTaluka = new ArrayList<>();
                adapterTaluka = new ArrayAdapter<String>(AddDetails.this,R.layout.support_simple_spinner_dropdown_item,spinnerTaluka);
                taluka.setAdapter(adapterTaluka);
                retrieveDataTaluka();
                addTalukaBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        talukaData = talukaETAdd.getText().toString().trim();
                        if (talukaData.isEmpty()){
                            talukaETAdd.setError("Required");
                            talukaETAdd.requestFocus();
                        }
                        else if (SpecialCharacter(talukaData)){
                            talukaETAdd.setError(s);
                            talukaETAdd.requestFocus();
                        }
                        else {
                            databaseReferenceTaluka.child(talukaData).setValue(talukaData)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            talukaETAdd.setText(" ");
                                            spinnerTaluka.clear();
                                            retrieveDataTaluka();
                                            adapterTaluka.notifyDataSetChanged();
                                            showMessage("Taluka is inserted");
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getApplicationContext(), "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            });
                        }
                    }
                });
                spinnerTaluka.clear();
                deleteTaluka.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = taluka.getText().toString().trim();
                        if (name.isEmpty()){
                            showMessage("Empty!");
                        }
                        else {
                            String[] options = {"No", "Yes"};
                            builder.setTitle("Are you Sure ?");
                            builder.setIcon(R.drawable.ic_action_delete);
                            builder.setItems(options, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (which == 0) {
                                        String not = "Cancel";
                                        Toast.makeText(getApplicationContext(), not.toUpperCase(), Toast.LENGTH_SHORT).show();
                                    } else if (name.isEmpty()) {
                                        showMessage("Empty");
                                    } else {
                                        spinnerTaluka.clear();
                                        databaseReferenceTaluka.child(name).removeValue();
                                        adapterTaluka.notifyDataSetChanged();
                                        taluka.setText(" ");
                                        showMessage(name + " is remove");
                                    }

                                }
                            }).create().show();
                        }

                    }
                });
                spinnerTaluka.clear();

                //Unit
                addUnit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        unitLL.setVisibility(View.GONE);
                        addUnitLL.setVisibility(View.VISIBLE);
                    }
                });
                viewUnit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        unitLL.setVisibility(View.VISIBLE);
                        addUnitLL.setVisibility(View.GONE);
                    }
                });
                databaseReferenceUnit = FirebaseDatabase.getInstance().getReference(companyEmail+" Unit");
                spinnerUnit = new ArrayList<>();
                adapterUnit = new ArrayAdapter<String>(AddDetails.this,R.layout.support_simple_spinner_dropdown_item,spinnerUnit);
                unit.setAdapter(adapterUnit);
                retrieveDataUnit();
                addUnitBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        unitData = unitETAdd.getText().toString().trim();
                        if (unitData.isEmpty()){
                            unitETAdd.setError("Required");
                            unitETAdd.requestFocus();
                        }
                        else if (SpecialCharacter(unitData)){
                            unitETAdd.setError(s);
                            unitETAdd.requestFocus();
                        }
                        else {
                            databaseReferenceUnit.child(unitData).setValue(unitData)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            unitETAdd.setText(" ");
                                            spinnerUnit.clear();
                                            retrieveDataUnit();
                                            adapterUnit.notifyDataSetChanged();
                                            showMessage("Unit is inserted");
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getApplicationContext(), "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            });
                        }
                    }
                });
                spinnerUnit.clear();
                deleteUnit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = unit.getText().toString().trim();
                        if (name.isEmpty()){
                            showMessage("Empty!");
                        }
                        else {
                            String[] options = {"No", "Yes"};
                            builder.setTitle("Are you Sure ?");
                            builder.setIcon(R.drawable.ic_action_delete);
                            builder.setItems(options, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (which == 0) {
                                        String not = "Cancel";
                                        Toast.makeText(getApplicationContext(), not.toUpperCase(), Toast.LENGTH_SHORT).show();
                                    } else if (name.isEmpty()) {
                                        showMessage("Empty");
                                    } else {
                                        spinnerUnit.clear();
                                        databaseReferenceUnit.child(name).removeValue();
                                        adapterUnit.notifyDataSetChanged();
                                        unit.setText(" ");
                                        showMessage(name + " is remove");
                                    }


                                }
                            }).create().show();
                        }

                    }
                });
                spinnerUnit.clear();







                //WorkDone
                addWorkDone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        workDoneLL.setVisibility(View.GONE);
                        addWorkDoneLL.setVisibility(View.VISIBLE);
                    }
                });
                viewWorkDone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        workDoneLL.setVisibility(View.VISIBLE);
                        addWorkDoneLL.setVisibility(View.GONE);
                    }
                });
                databaseReferenceWorkDone = FirebaseDatabase.getInstance().getReference(companyEmail+" WorkDone");
                spinnerWorkDone = new ArrayList<>();
                adapterWorkDone = new ArrayAdapter<String>(AddDetails.this,R.layout.support_simple_spinner_dropdown_item,spinnerWorkDone);
                workDone.setAdapter(adapterWorkDone);
                retrieveDataWorkDone();
                addWorkDoneBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        workDoneData = workDoneETAdd.getText().toString().trim();
                        if (workDoneData.isEmpty()){
                            workDoneETAdd.setError("Required");
                            workDoneETAdd.requestFocus();
                        }
                        else if (SpecialCharacter(workDoneData)){
                            workDoneETAdd.setError(s);
                            workDoneETAdd.requestFocus();
                        }
                        else {
                            databaseReferenceWorkDone.child(workDoneData).setValue(workDoneData)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            workDoneETAdd.setText(" ");
                                            spinnerWorkDone.clear();
                                            retrieveDataWorkDone();
                                            adapterWorkDone.notifyDataSetChanged();
                                            showMessage("WorkDone is inserted");
                                            uploadWorkDone(workDoneData,cmp);
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(getApplicationContext(), "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();

                                        }
                                    });
                        }
                    }
                });
                spinnerWorkDone.clear();
                deleteWorkDone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = workDone.getText().toString().trim();
                        if (name.isEmpty()){
                            showMessage("Empty!");
                        }
                        else {
                            String[] options = {"No", "Yes"};
                            builder.setTitle("Are you Sure ?");
                            builder.setIcon(R.drawable.ic_action_delete);
                            builder.setItems(options, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (which == 0) {
                                        String not = "Cancel";
                                        Toast.makeText(getApplicationContext(), not.toUpperCase(), Toast.LENGTH_SHORT).show();
                                    } else if (name.isEmpty()) {
                                        showMessage("Empty");
                                    } else {
                                        spinnerWorkDone.clear();
                                        databaseReferenceWorkDone.child(name).removeValue();
                                        adapterWorkDone.notifyDataSetChanged();
                                        workDone.setText(" ");
                                        showMessage(name + " is remove");
                                    }


                                }
                            }).create().show();
                        }

                    }
                });
                spinnerWorkDone.clear();

                //Pair With Tender and center
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       startActivity(new Intent(getApplicationContext(),PairTenderWithSite.class));
                    }
                });



            }
        });

    }

    private void updateMaterialNameData(String materialData, String updateMaterialName, String cmp) {
        for (int i=0;i<modelList.size();i++){
            if (modelList.get(i).getMaterial1().equalsIgnoreCase(updateMaterialName)){
                updateMaterialNameOneField(materialData,cmp,"Material 1",modelList.get(i).getId());

            }
            else  if (modelList.get(i).getMaterial2().equalsIgnoreCase(updateMaterialName)){
                updateMaterialNameOneField(materialData,cmp,"Material 2",modelList.get(i).getId());
            }
            else  if (modelList.get(i).getMaterial3().equalsIgnoreCase(updateMaterialName)){
                updateMaterialNameOneField(materialData,cmp,"Material 3",modelList.get(i).getId());
            }
             else  if (modelList.get(i).getMaterial4().equalsIgnoreCase(updateMaterialName)){
                updateMaterialNameOneField(materialData,cmp,"Material 4",modelList.get(i).getId());
            }
             else  if (modelList.get(i).getMaterial5().equalsIgnoreCase(updateMaterialName)){
                updateMaterialNameOneField(materialData,cmp,"Material 5",modelList.get(i).getId());
            }
             else  if (modelList.get(i).getMaterial6().equalsIgnoreCase(updateMaterialName)){
                updateMaterialNameOneField(materialData,cmp,"Material 6",modelList.get(i).getId());
            }
             else  if (modelList.get(i).getMaterial7().equalsIgnoreCase(updateMaterialName)){
                updateMaterialNameOneField(materialData,cmp,"Material 7",modelList.get(i).getId());
            }
             else  if (modelList.get(i).getMaterial8().equalsIgnoreCase(updateMaterialName)){
                updateMaterialNameOneField(materialData,cmp,"Material 8",modelList.get(i).getId());
            }
             else  if (modelList.get(i).getMaterial9().equalsIgnoreCase(updateMaterialName)){
                updateMaterialNameOneField(materialData,cmp,"Material 9",modelList.get(i).getId());
            }
             else  if (modelList.get(i).getMaterial10().equalsIgnoreCase(updateMaterialName)){
                updateMaterialNameOneField(materialData,cmp,"Material 10",modelList.get(i).getId());
            }
            else if (modelList.get(i).getMaterial11().equalsIgnoreCase(updateMaterialName)){
                updateMaterialNameOneField(materialData,cmp,"Material 11",modelList.get(i).getId());

            }

            else if (modelList.get(i).getMaterial12().equalsIgnoreCase(updateMaterialName)){
                updateMaterialNameOneField(materialData,cmp,"Material 12",modelList.get(i).getId());

            }

            else if (modelList.get(i).getMaterial13().equalsIgnoreCase(updateMaterialName)){
                updateMaterialNameOneField(materialData,cmp,"Material 13",modelList.get(i).getId());

            }

            else if (modelList.get(i).getMaterial14().equalsIgnoreCase(updateMaterialName)){
                updateMaterialNameOneField(materialData,cmp,"Material 14",modelList.get(i).getId());

            }

            else if (modelList.get(i).getMaterial15().equalsIgnoreCase(updateMaterialName)){
                updateMaterialNameOneField(materialData,cmp,"Material 15",modelList.get(i).getId());

            }

            else if (modelList.get(i).getMaterial16().equalsIgnoreCase(updateMaterialName)){
                updateMaterialNameOneField(materialData,cmp,"Material 16",modelList.get(i).getId());

            }

            else if (modelList.get(i).getMaterial17().equalsIgnoreCase(updateMaterialName)){
                updateMaterialNameOneField(materialData,cmp,"Material 17",modelList.get(i).getId());

            }

            else if (modelList.get(i).getMaterial18().equalsIgnoreCase(updateMaterialName)){
                updateMaterialNameOneField(materialData,cmp,"Material 18",modelList.get(i).getId());

            }

            else if (modelList.get(i).getMaterial19().equalsIgnoreCase(updateMaterialName)){
                updateMaterialNameOneField(materialData,cmp,"Material 19",modelList.get(i).getId());

            }

            else if (modelList.get(i).getMaterial20().equalsIgnoreCase(updateMaterialName)){
                updateMaterialNameOneField(materialData,cmp,"Material 20",modelList.get(i).getId());

            }

            else if (modelList.get(i).getMaterial21().equalsIgnoreCase(updateMaterialName)){
                updateMaterialNameOneField(materialData,cmp,"Material 21",modelList.get(i).getId());

            }

            else if (modelList.get(i).getMaterial22().equalsIgnoreCase(updateMaterialName)){
                updateMaterialNameOneField(materialData,cmp,"Material 22",modelList.get(i).getId());

            }

            else if (modelList.get(i).getMaterial23().equalsIgnoreCase(updateMaterialName)){
                updateMaterialNameOneField(materialData,cmp,"Material 23",modelList.get(i).getId());

            }

            else if (modelList.get(i).getMaterial24().equalsIgnoreCase(updateMaterialName)){
                updateMaterialNameOneField(materialData,cmp,"Material 24",modelList.get(i).getId());

            }

            else if (modelList.get(i).getMaterial25().equalsIgnoreCase(updateMaterialName)){
                updateMaterialNameOneField(materialData,cmp,"Material 25",modelList.get(i).getId());

            }

            else if (modelList.get(i).getMaterial26().equalsIgnoreCase(updateMaterialName)){
                updateMaterialNameOneField(materialData,cmp,"Material 26",modelList.get(i).getId());

            }

            else if (modelList.get(i).getMaterial27().equalsIgnoreCase(updateMaterialName)){
                updateMaterialNameOneField(materialData,cmp,"Material 27",modelList.get(i).getId());

            }

            else if (modelList.get(i).getMaterial28().equalsIgnoreCase(updateMaterialName)){
                updateMaterialNameOneField(materialData,cmp,"Material 28",modelList.get(i).getId());

            }
            else if (modelList.get(i).getMaterial29().equalsIgnoreCase(updateMaterialName)){
                updateMaterialNameOneField(materialData,cmp,"Material 29",modelList.get(i).getId());

            }
            else if (modelList.get(i).getMaterial30().equalsIgnoreCase(updateMaterialName)){
                updateMaterialNameOneField(materialData,cmp,"Material 30",modelList.get(i).getId());

            }

        }
    }

    private void updateBalanceUsedMaterialNameOneField(String materialData, String cmp, String material, String id) {
        Map<String,Object> map = new HashMap<>();
        map.put(material,materialData);
        FirebaseFirestore.getInstance().collection(cmp+" BalanceMaterialOnSite")
                .document(id)
                .update(map).addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        Toast.makeText(AddDetails.this, "Done", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddDetails.this, "Error "+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void updateActualUsedMaterialNameOneField(String materialData, String cmp, String material, String id) {
        Map<String,Object> map = new HashMap<>();
        map.put(material,materialData);
        FirebaseFirestore.getInstance().collection(cmp+" AddActualData")
                .document(id)
                .update(map).addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        Toast.makeText(AddDetails.this, "Done", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddDetails.this, "Error "+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void updateUsedMaterialNameOneField(String materialData, String cmp, String material, String id) {
        Map<String,Object> map = new HashMap<>();
        map.put(material,materialData);
        FirebaseFirestore.getInstance().collection(cmp+" RemainingMaterial")
                .document(id)
                .update(map).addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        Toast.makeText(AddDetails.this, "Done", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddDetails.this, "Error "+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void updateRetrunMaterialNameOneField(String materialData, String cmp, String material, String id) {
        Map<String,Object> map = new HashMap<>();
        map.put(material,materialData);
        FirebaseFirestore.getInstance().collection(cmp+" AddReturnData")
                .document(id)
                .update(map).addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        Toast.makeText(AddDetails.this, "Done", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddDetails.this, "Error "+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void updateMaterialNameOneField(String materialData, String cmp, String material,String id) {
        Map<String,Object> map = new HashMap<>();
        map.put(material,materialData);
        FirebaseFirestore.getInstance().collection(cmp+" AddData")
                .document(id)
                .update(map).addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        updateRetrunMaterialNameOneField(materialData,cmp,material,id);
                        updateUsedMaterialNameOneField(materialData,cmp,material,id);
                        updateActualUsedMaterialNameOneField(materialData,cmp,material,id);
                        updateBalanceUsedMaterialNameOneField(materialData,cmp,material,id);
                        Toast.makeText(AddDetails.this, "Done", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddDetails.this, "Error "+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void showData(String cmp) {
        fStore.collection(cmp + " AddData").orderBy("Date", Query.Direction.DESCENDING).get()
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
                        adminCustomAdapter = new AddedDataChangesCustomAdapter(AddDetails.this, modelList);
                        recyclerView.setAdapter(adminCustomAdapter);
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

    private void retrieveDataWorkDone() {

        listenerWorkDone = databaseReferenceWorkDone.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot item : snapshot.getChildren()){
                    spinnerWorkDone.add(item.getValue().toString());
                }
                adapterWorkDone.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void deleteData(String cmp, String name) {
        fStore.collection(cmp + " AddStock").document(name)
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        pd.dismiss();
                        Toast.makeText(AddDetails.this, "Data Deleted", Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void uploadMaterial(String materialData, String cmp) {

        String id = UUID.randomUUID().toString();
        Map<String, Object> doc = new HashMap<>();

        doc.put("id", id);
        doc.put("Material",materialData);

        fStore.collection(cmp+" Material").document(id).set(doc).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                //this will be called when data is added Successfully
                Toast.makeText(getApplicationContext(), "Data Added !!", Toast.LENGTH_SHORT).show();
                addStockEntry(cmp,materialData);
            }
        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //this will be called when data is added Failed
                        Toast.makeText(getApplicationContext(), "Failed to add data "+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
        });

    }

    private void uploadWorkDone(String materialData, String cmp) {
        String id = UUID.randomUUID().toString();
        Map<String, Object> doc = new HashMap<>();

        doc.put("id", id);
        doc.put("WorkDone",materialData);

        fStore.collection(cmp+" WorkDoneMaterialList").document(id).set(doc).addOnCompleteListener(new OnCompleteListener<Void>() {
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

    private void addStockEntry(String cmp, String materialData) {
        Map<String, Object> doc = new HashMap<>();

        doc.put("id", materialData);
        doc.put("Material",materialData);
        doc.put("Unit","");
        doc.put("Quantity","0");

        fStore.collection(cmp+" AddStock").document(materialData).set(doc).addOnCompleteListener(new OnCompleteListener<Void>() {
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
                    spinnerLine.add(item.getValue().toString());
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
                    spinnerTender.add(item.getValue().toString());
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
                    spinnerDriver.add(item.getValue().toString());
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
                    spinnerVehical.add(item.getValue().toString());
                }
                adapterVehical.notifyDataSetChanged();
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
                    spinnerReceiver.add(item.getValue().toString());
                }
                adapterReceiver.notifyDataSetChanged();
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
                    spinnerMaterial.add(item.getValue().toString());
                }
                adapterMaterial.notifyDataSetChanged();
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
                    spinnerCenter.add(item.getValue().toString());
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
                    spinnerVillage.add(item.getValue().toString());
                }
                adapterVillage.notifyDataSetChanged();
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
                    spinnerDistrict.add(item.getValue().toString());
                }
                adapterDistrict.notifyDataSetChanged();
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
                    spinnerState.add(item.getValue().toString());
                }
                adapterState.notifyDataSetChanged();
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
                    spinnerTaluka.add(item.getValue().toString());
                }
                adapterTaluka.notifyDataSetChanged();
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
                    spinnerUnit.add(item.getValue().toString());
                }
                adapterUnit.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void showMessage(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }

    private boolean SpecialCharacter(String name){

        Matcher matcher = pattern.matcher(name);
        return matcher.find();
    }

}