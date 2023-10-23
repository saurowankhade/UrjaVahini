package com.example.electricalmaterial.add_material;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.electricalmaterial.AddStockModel;
import com.example.electricalmaterial.AddTotalMaterialTakenModel;
import com.example.electricalmaterial.Admin;
import com.example.electricalmaterial.KgToNoModel;
import com.example.electricalmaterial.R;
import com.example.electricalmaterial.Supervisor;
import com.example.electricalmaterial.TeamLearder;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class AddMaterial extends AppCompatActivity {
    //Authorization
    private FirebaseAuth mAuth;
    private FirebaseFirestore fStore;
    private String companyEmail;
    private String userId;
    private String cmp;

    private String id = UUID.randomUUID().toString();

    //Stock Material
    List<AddStockModel> modelList = new ArrayList<>();

    //Kg to no
    List<KgToNoModel> modelListKN = new ArrayList<>();

    // data form
    private NonSwipeableViewPager viewPager;


    // Bacis Infromation

    private String sDate;
    private String sTeamName;
    private String sLine;
    private String sTender;
    private String sDriverName;
    private String sVehicalName;
    //Site Infromation
    private String sConsumerName;
    private String sState;
    private String sDistrict;
    private String sTaluka;
    private String sCenter;
    private String sVillage;
    private String sSiteName;
    // Material Infromation
    private ArrayList<AddTotalMaterialTakenModel> materialTakenModelArrayList;

    // End Infromation
    private String sNote;
    private String sMaterialReceiverName;
    private String sMaterialGiverName;
    private String sCompanyEmail;

    private ArrayList<AddTotalMaterialTakenModel> alPreMainAddData = new ArrayList<>();
    private List<AddTotalMaterialTakenModel> alTotalMaterialData = new ArrayList<>();
    private List<AddTotalMaterialTakenModel> alBalanceMaterialData = new ArrayList<>();


    // while update
    private String upDate;
    private String upTeamName;
    private String upLine;
    private String upTender;
    private String upDriverName;
    private String upVehicalName;
    //Site Infromation
    private String upConsumerName;
    private String upState;
    private String upDistrict;
    private String upTaluka;
    private String upCenter;
    private String upVillage;
    private String upSiteName;

    // End Infromation
    private String upNote;
    private String upMaterialReceiverName;

    private String forWhat;


    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_material);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        ActionBar bar = getSupportActionBar();
        assert bar != null;
        bar.setTitle("MATERIAL DELIVERY");


        //Auth
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = mAuth.getCurrentUser().getUid();

        // fragment
        viewPager = findViewById(R.id.viewPager);


        DocumentReference documentReference = fStore.collection("Users")
                .document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {


                companyEmail = value.getString("companyEmail");
                cmp = companyEmail;
                companyEmail = companyEmail.replace("@", "");
                companyEmail = companyEmail.replace(".", "");

                // when we want update
                Bundle bundle = getIntent().getExtras();
                if (bundle != null) {

                    id = bundle.getString("Id");
                    forWhat= bundle.getString("forWhat");
                    id = bundle.getString("Id");
                    upDate = bundle.getString("Date");
                    upTeamName = bundle.getString("TeamName");
                    upLine = bundle.getString("Line");
                    upTender = bundle.getString("Tender");
                    upDriverName = bundle.getString("Driver Name");
                    upVehicalName = bundle.getString("Vehical Name");
                    upConsumerName = bundle.getString("Consumer Name");
                    upState = bundle.getString("State");
                    upDistrict = bundle.getString("District");
                    upTaluka = bundle.getString("Taluka");
                    upCenter = bundle.getString("Center Name");
                    upVillage = bundle.getString("Village");
                    upSiteName = bundle.getString("Site");
                    upNote = bundle.getString("Note");
                    upMaterialReceiverName = bundle.getString("MaterialReceiverName");

                    showMainAddData(bundle.getString("Id"),cmp);

                    setsDate(upDate);
                    setsTeamName(upTeamName);
                    setsTender(upTender);
                    setsLine(upLine);
                    setsDriverName(upDriverName);
                    setsVehicalName(upVehicalName);
                    setsConsumerName(upConsumerName);
                    setsState(upState);
                    setsDistrict(upDistrict);
                    setsTaluka(upTaluka);
                    setsCenter(upCenter);
                    setsVillage(upVillage);
                    setsSiteName(upSiteName);
                    setsNote(upNote);
                    setsMaterialReceiverName(upMaterialReceiverName);

                    // pre AddMain Material List

                }


                AddMaterialFormPagerAdapter addMaterialFormPagerAdapter = new AddMaterialFormPagerAdapter(getSupportFragmentManager());
                viewPager.setAdapter(addMaterialFormPagerAdapter);
                viewPager.setSwipeEnabled(false);


                modelList.clear();
                showData(cmp);

                //Stock Material
                modelListKN.clear();
                showDataKN(cmp);
            }
        });


    }



    private void showData(String cmp) {
        fStore.collection(cmp + " AddStock")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        modelList.clear();
                        for (DocumentSnapshot doc : task.getResult()) {
                            AddStockModel model = new AddStockModel(
                                    doc.getString("Id"),
                                    doc.getString("Material"),
                                    doc.getString("Unit"),
                                    doc.getString("Quantity")
                            );
                            modelList.add(model);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddMaterial.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void showDataKN(String cmp) {
        fStore.collection(cmp + " KgToNo").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                modelListKN.clear();
                for (DocumentSnapshot doc : task.getResult()) {
                    KgToNoModel model = new KgToNoModel(
                            doc.getString("Id"),
                            doc.getString("Material"),
                            doc.getString("Number"),
                            doc.getString("Unit")
                    );
                    modelListKN.add(model);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AddMaterial.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    // fragment work

    private void showMainAddData(String id,String cmp) {
        fStore.collection(cmp + " MainAddData")
                .document(id)
                .collection("MaterialList")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        alPreMainAddData.clear();
                        for (DocumentSnapshot doc : task.getResult()) {
                            AddTotalMaterialTakenModel model = new AddTotalMaterialTakenModel(
                                    doc.getString("Id"),
                                    doc.getString("Material"),
                                    doc.getString("Unit"),
                                    doc.getString("Quantity")
                            );
                            alPreMainAddData.add(model);
                            setMaterialTakenModelArrayList(alPreMainAddData);
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddMaterial.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void showTotalMaterialData(String consumerName, String teamName) {
        fStore.collection(cmp + " TotalMaterialData")
                .document(consumerName + " " + teamName)
                .collection("MaterialList")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        alTotalMaterialData.clear();
                        for (DocumentSnapshot doc : task.getResult()) {
                            AddTotalMaterialTakenModel model = new AddTotalMaterialTakenModel(
                                    doc.getString("Id"),
                                    doc.getString("Material"),
                                    doc.getString("Unit"),
                                    doc.getString("Quantity")
                            );
                            alTotalMaterialData.add(model);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddMaterial.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void showBalanceMaterialData(String consumerName, String teamName) {
        fStore.collection(cmp + " BalanceMaterialData")
                .document(consumerName + " " + teamName)
                .collection("MaterialList")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        alBalanceMaterialData.clear();
                        for (DocumentSnapshot doc : task.getResult()) {
                            AddTotalMaterialTakenModel model = new AddTotalMaterialTakenModel(
                                    doc.getString("Id"),
                                    doc.getString("Material"),
                                    doc.getString("Unit"),
                                    doc.getString("Quantity")
                            );
                            alBalanceMaterialData.add(model);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddMaterial.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }


    // when we want to go pre
    public void goToBacisInformation() {
        viewPager.setCurrentItem(0, true);
    }

    public void goToSiteInformation() {
        viewPager.setCurrentItem(1, true);
    }

    public void goToMaterialInformation() {
        viewPager.setCurrentItem(2, true);
    }

    // when we wat to go next
    public void goToSiteInformation(String companyEmail, String date, String teamName, String line, String tender, String driverName, String vehicalName) {
        this.sCompanyEmail = companyEmail;
        this.sDate = date;
        this.sTeamName = teamName;
        this.sLine = line;
        this.sTender = tender;
        this.sDriverName = driverName;
        this.sVehicalName = vehicalName;
        viewPager.setCurrentItem(1, true);

    }

    public void goToMaterialInformation(String consumerName, String state, String district, String taluka, String center, String village, String siteName) {
        this.sConsumerName = consumerName;
        this.sState = state;
        this.sDistrict = district;
        this.sTaluka = taluka;
        this.sCenter = center;
        this.sVillage = village;
        this.sSiteName = siteName;
        showTotalMaterialData(sConsumerName, sTeamName);
        showBalanceMaterialData(sConsumerName, sTeamName);
        viewPager.setCurrentItem(2, true);
    }

    public void goToEndInformation(ArrayList<AddTotalMaterialTakenModel> materialTakenModelArrayList) {
        this.materialTakenModelArrayList = materialTakenModelArrayList;
        viewPager.setCurrentItem(3, true);
    }

    public void goToDoneAnimation(String note, String materialReceiverName, String materialGiverName) {
        this.sNote = note;
        this.sMaterialReceiverName = materialReceiverName;
        this.sMaterialGiverName = materialGiverName;
        viewPager.setCurrentItem(4, true);

        if (forWhat.contains("Return Material")){
            addDataToFireBase("MainReturnData");
        } else {
            addDataToFireBase("MainAddData");
            addDataToFireBaseForTotalMaterial();

            addTotalMaterial();
            addBalanceMaterial();
        }
        addMaterial();


    }

    private void addDataToFireBase(String forWhat) {
        Map<String, Object> doc = new HashMap<>();
        doc.put("id",id);
        doc.put("Date", sDate);
        doc.put("Team Name", sTeamName);
        doc.put("Line", sLine);
        doc.put("Tender", sTender);
        doc.put("Driver Name", sDriverName);
        doc.put("Vehical Name", sVehicalName);
        doc.put("Consumer Name", sConsumerName);
        doc.put("State", sState);
        doc.put("District", sDistrict);
        doc.put("Taluka", sTaluka);
        doc.put("Center", sCenter);
        doc.put("Village", sVillage);
        doc.put("Site Name", sSiteName);
        doc.put("Note", sNote);
        doc.put("Material Receiver Name", sMaterialReceiverName);
        doc.put("Material Giver Name", sMaterialGiverName);

        fStore.collection(sCompanyEmail + " "+forWhat)
                .document(id)
                .set(doc).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //this will be called when data is added Successfully
                        viewPager.setCurrentItem(4, true);
                        Toast.makeText(getApplicationContext(), "Data Added!!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //this will be called when data is not added or  Failed
                        Toast.makeText(getApplicationContext(), "Failed to add data " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void addDataToFireBaseForTotalMaterial() {
        Map<String, Object> doc = new HashMap<>();
        doc.put("id", sConsumerName + " " + sTeamName);
        doc.put("Date", sDate);
        doc.put("Team Name", sTeamName);
        doc.put("Line", sLine);
        doc.put("Tender", sTender);
        doc.put("Driver Name", sDriverName);
        doc.put("Vehical Name", sVehicalName);
        doc.put("Consumer Name", sConsumerName);
        doc.put("State", sState);
        doc.put("District", sDistrict);
        doc.put("Taluka", sTaluka);
        doc.put("Center", sCenter);
        doc.put("Village", sVillage);
        doc.put("Site Name", sSiteName);
        doc.put("Note", sNote);
        doc.put("Material Receiver Name", sMaterialReceiverName);
        doc.put("Material Giver Name", sMaterialGiverName);

        fStore.collection(sCompanyEmail + " TotalMaterialData")
                .document(sConsumerName + " " + sTeamName)
                .set(doc).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //this will be called when data is added Successfully
                        viewPager.setCurrentItem(4, true);
                        Toast.makeText(getApplicationContext(), "Data Added!!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //this will be called when data is not added or  Failed
                        Toast.makeText(getApplicationContext(), "Failed to add data " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void addMaterial() {

        if (forWhat.contains("Return material")){
            for (int i = 0; i < materialTakenModelArrayList.size(); i++) {
                addMaterialList(
                        materialTakenModelArrayList.get(i).getMaterial(),
                        materialTakenModelArrayList.get(i).getUnit(),
                        materialTakenModelArrayList.get(i).getQuantity(),
                        "MainReturnData"
                );
                if (i == materialTakenModelArrayList.size() - 1) {
                    Toast.makeText(getApplicationContext(), "Data Added ", Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            for (int i = 0; i < materialTakenModelArrayList.size(); i++) {
                addMaterialList(
                        materialTakenModelArrayList.get(i).getMaterial(),
                        materialTakenModelArrayList.get(i).getUnit(),
                        materialTakenModelArrayList.get(i).getQuantity(),
                        "MainAddData"
                );
                if (i == materialTakenModelArrayList.size() - 1) {
                    Toast.makeText(getApplicationContext(), "Data Added ", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }

    private void addTotalMaterial() {

        if (forWhat.contains("Return material")){

        }


        if (forWhat.contains("Update")) {
            // chanage
            // formula will be -> TMT -(newQuantity - PreQuantity )
            // TMT -> total material taken material list , newQuantity -> new added materialList, preQuantity -> pre quantity list which is present in addMainData

            HashMap<String, AddTotalMaterialTakenModel> tmtML = new HashMap<>();
            HashMap<String, AddTotalMaterialTakenModel> preML = new HashMap<>();
            HashMap<String, AddTotalMaterialTakenModel> newML = new HashMap<>();

            for (int i = 0; i < alTotalMaterialData.size(); i++) {
                tmtML.put(alTotalMaterialData.get(i).getMaterial().trim(), new AddTotalMaterialTakenModel(
                        alTotalMaterialData.get(i).getMaterial().trim(),
                        alTotalMaterialData.get(i).getMaterial().trim(),
                        alTotalMaterialData.get(i).getUnit().trim(),
                        alTotalMaterialData.get(i).getQuantity().trim()
                ));
            }

            for (int i = 0; i < materialTakenModelArrayList.size(); i++) {
                newML.put(materialTakenModelArrayList.get(i).getMaterial().trim(),
                        new AddTotalMaterialTakenModel(
                                materialTakenModelArrayList.get(i).getMaterial().trim(),
                                materialTakenModelArrayList.get(i).getMaterial().trim(),
                                materialTakenModelArrayList.get(i).getUnit().trim(),
                                materialTakenModelArrayList.get(i).getQuantity().trim()
                        ));
            }

            for (int i = 0; i < alPreMainAddData.size(); i++) {
                preML.put(alPreMainAddData.get(i).getMaterial().trim(), new AddTotalMaterialTakenModel(
                        alPreMainAddData.get(i).getMaterial().trim(),
                        alPreMainAddData.get(i).getMaterial().trim(),
                        alPreMainAddData.get(i).getUnit().trim(),
                        alPreMainAddData.get(i).getQuantity().trim()
                ));
            }

            for (String ma : newML.keySet()){
                    if (newML.get(ma).getMaterial().trim().equalsIgnoreCase(tmtML.get(ma).getMaterial().trim())){
                        if (newML.get(ma).getMaterial().trim().equalsIgnoreCase(preML.get(ma).getMaterial().trim())){
                            // tmt-(new-pre)
                            String quantity = quantityCal(tmtML.get(ma).getQuantity().trim(),newML.get(ma).getQuantity().trim(),preML.get(ma).getMaterial().trim());

                            addTotalMaterialList(
                                    newML.get(ma).getMaterial().trim(),
                                    newML.get(ma).getUnit().trim(),
                                    quantity
                            );


                        }

                        else {
                            // tmt+new

                            float qu = Float.parseFloat(tmtML.get(ma).getQuantity().trim()) + Float.parseFloat(newML.get(ma).getQuantity().trim());
                            String quantity = String.valueOf(qu);

                            addTotalMaterialList(
                                    newML.get(ma).getMaterial().trim(),
                                    newML.get(ma).getUnit().trim(),
                                    quantity
                            );
                        }
                    }
                    else { // addNewData
                        addTotalMaterialList(
                                newML.get(ma).getMaterial().trim(),
                                newML.get(ma).getUnit().trim(),
                                newML.get(ma).getQuantity().trim()
                        );
                    }
                }






            }

        else {
            if (alTotalMaterialData.isEmpty()) {
                for (int i = 0; i < materialTakenModelArrayList.size(); i++) {
                    addTotalMaterialList(
                            materialTakenModelArrayList.get(i).getMaterial(),
                            materialTakenModelArrayList.get(i).getUnit(),
                            materialTakenModelArrayList.get(i).getQuantity()
                    );
                    if (i == materialTakenModelArrayList.size() - 1) {
                        Toast.makeText(getApplicationContext(), "Data Added ", Toast.LENGTH_SHORT).show();
                    }
                }
            } else {
                for (int i = 0; i < materialTakenModelArrayList.size(); i++) {
                    for (int j = 0; j < alTotalMaterialData.size(); j++) {
                        if (materialTakenModelArrayList.get(i).getMaterial().trim().equalsIgnoreCase(alTotalMaterialData.get(j).getMaterial().trim())) {
                            float qPre = Float.parseFloat(alTotalMaterialData.get(j).getQuantity().trim());
                            float qNext = Float.parseFloat(materialTakenModelArrayList.get(i).getQuantity().trim());
                            float qTotal = qPre + qNext;
                            String quantity = String.valueOf(qTotal);
                            addTotalMaterialList(
                                    materialTakenModelArrayList.get(i).getMaterial(),
                                    materialTakenModelArrayList.get(i).getUnit(),
                                    quantity
                            );
                        }
                    }
                }
            }
        }

    }

    private String quantityCal(String tmtMl, String newMl, String preMl) {
        float qu = Float.parseFloat(tmtMl) - ( Float.parseFloat(newMl) - Float.parseFloat(preMl) );
        return String.valueOf(qu);
    }

    private void addBalanceMaterial() {
        // update pre data
        if (forWhat.contains("Update")) {
            // chanage

            // formula will be -> TMT -(newQuantity - PreQuantity )
            // TMT -> total material taken material list , newQuantity -> new added materialList, preQuantity -> pre quantity list which is present in addMainData

            HashMap<String, AddTotalMaterialTakenModel> tmtML = new HashMap<>();
            HashMap<String, AddTotalMaterialTakenModel> preML = new HashMap<>();
            HashMap<String, AddTotalMaterialTakenModel> newML = new HashMap<>();

            for (int i = 0; i < alBalanceMaterialData.size(); i++) {
                tmtML.put(alBalanceMaterialData.get(i).getMaterial().trim(), new AddTotalMaterialTakenModel(
                        alBalanceMaterialData.get(i).getMaterial().trim(),
                        alBalanceMaterialData.get(i).getMaterial().trim(),
                        alBalanceMaterialData.get(i).getUnit().trim(),
                        alBalanceMaterialData.get(i).getQuantity().trim()
                ));
            }

            for (int i = 0; i < materialTakenModelArrayList.size(); i++) {
                newML.put(materialTakenModelArrayList.get(i).getMaterial().trim(),
                        new AddTotalMaterialTakenModel(
                                materialTakenModelArrayList.get(i).getMaterial().trim(),
                                materialTakenModelArrayList.get(i).getMaterial().trim(),
                                materialTakenModelArrayList.get(i).getUnit().trim(),
                                materialTakenModelArrayList.get(i).getQuantity().trim()
                        ));
            }

            for (int i = 0; i < alPreMainAddData.size(); i++) {
                preML.put(alPreMainAddData.get(i).getMaterial().trim(), new AddTotalMaterialTakenModel(
                        alPreMainAddData.get(i).getMaterial().trim(),
                        alPreMainAddData.get(i).getMaterial().trim(),
                        alPreMainAddData.get(i).getUnit().trim(),
                        alPreMainAddData.get(i).getQuantity().trim()
                ));


                for (String ma : newML.keySet()){
                    if (newML.get(ma).getMaterial().trim().equalsIgnoreCase(tmtML.get(ma).getMaterial().trim())){
                        if (newML.get(ma).getMaterial().trim().equalsIgnoreCase(preML.get(ma).getMaterial().trim())){
                            // tmt-(new-pre)
                            String quantity = quantityCal(tmtML.get(ma).getQuantity().trim(),newML.get(ma).getQuantity().trim(),preML.get(ma).getMaterial().trim());

                            addBalanceMaterialList(
                                    newML.get(ma).getMaterial().trim(),
                                    newML.get(ma).getUnit().trim(),
                                    quantity
                            );


                        }

                        else {
                            // tmt+new

                            float qu = Float.parseFloat(tmtML.get(ma).getQuantity().trim()) + Float.parseFloat(newML.get(ma).getQuantity().trim());
                            String quantity = String.valueOf(qu);

                            addBalanceMaterialList(
                                    newML.get(ma).getMaterial().trim(),
                                    newML.get(ma).getUnit().trim(),
                                    quantity
                            );
                        }
                    }
                    else { // addNewData
                        addBalanceMaterialList(
                                newML.get(ma).getMaterial().trim(),
                                newML.get(ma).getUnit().trim(),
                                newML.get(ma).getQuantity().trim()
                        );
                    }
                }

            }


        }
        // add new data
        else {
            if (alBalanceMaterialData.isEmpty()) {
                for (int i = 0; i < materialTakenModelArrayList.size(); i++) {
                    addBalanceMaterialList(
                            materialTakenModelArrayList.get(i).getMaterial(),
                            materialTakenModelArrayList.get(i).getUnit(),
                            materialTakenModelArrayList.get(i).getQuantity()
                    );
                    if (i == materialTakenModelArrayList.size() - 1) {
                        Toast.makeText(getApplicationContext(), "Data Added ", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            else {
                for (int i = 0; i < materialTakenModelArrayList.size(); i++) {
                    for (int j = 0; j < alBalanceMaterialData.size(); j++) {
                        if (materialTakenModelArrayList.get(i).getMaterial().trim().equalsIgnoreCase(alBalanceMaterialData.get(j).getMaterial().trim())) {
                            float qPre = Float.parseFloat(alBalanceMaterialData.get(j).getQuantity().trim());
                            float qNext = Float.parseFloat(materialTakenModelArrayList.get(i).getQuantity().trim());
                            float qTotal = qPre + qNext;
                            String quantity = String.valueOf(qTotal);
                            addBalanceMaterialList(
                                    materialTakenModelArrayList.get(i).getMaterial(),
                                    materialTakenModelArrayList.get(i).getUnit(),
                                    quantity
                            );
                        }
                    }
                }
            }
        }

    }

    private void addMaterialList(String material, String unit, String quantity,String forWhat) {
        Map<String, Object> doc = new HashMap<>();
        doc.put("Id", material);
        doc.put("Material", material);
        doc.put("Unit", unit);
        doc.put("Quantity", quantity);

        fStore.collection(cmp + " "+forWhat)
                .document(id)
                .collection("MaterialList")
                .document(material)
                .set(doc).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //this will be called when data is added Successfully
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //this will be called when data is added Failed
                        Toast.makeText(getApplicationContext(), "Failed to add data " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void addTotalMaterialList(String material, String unit, String quantity) {
        Map<String, Object> doc = new HashMap<>();
        doc.put("Id", material);
        doc.put("Material", material);
        doc.put("Unit", unit);
        doc.put("Quantity", quantity);

        fStore.collection(cmp + " TotalMaterialData")
                .document(sConsumerName + " " + sTeamName)
                .collection("MaterialList")
                .document(material)
                .set(doc).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //this will be called when data is added Successfully
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //this will be called when data is added Failed
                        Toast.makeText(getApplicationContext(), "Failed to add data " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void addBalanceMaterialList(String material, String unit, String quantity) {
        Map<String, Object> doc = new HashMap<>();
        doc.put("Id", material);
        doc.put("Material", material);
        doc.put("Unit", unit);
        doc.put("Quantity", quantity);

        fStore.collection(cmp + " BalanceMaterialData")
                .document(sConsumerName + " " + sTeamName)
                .collection("MaterialList")
                .document(material)
                .set(doc).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //this will be called when data is added Successfully
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //this will be called when data is added Failed
                        Toast.makeText(getApplicationContext(), "Failed to add data " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

    // getter


    public String getId() {
        return id;
    }

    public String getsDate() {
        return sDate;
    }

    public String getsTeamName() {
        return sTeamName;
    }

    public String getsLine() {
        return sLine;
    }

    public String getsTender() {
        return sTender;
    }

    public String getsDriverName() {
        return sDriverName;
    }

    public String getsVehicalName() {
        return sVehicalName;
    }

    public String getsConsumerName() {
        return sConsumerName;
    }

    public String getsState() {
        return sState;
    }

    public String getsDistrict() {
        return sDistrict;
    }

    public String getsTaluka() {
        return sTaluka;
    }

    public String getsCenter() {
        return sCenter;
    }

    public String getsVillage() {
        return sVillage;
    }

    public String getsSiteName() {
        return sSiteName;
    }

    public String getsNote() {
        return sNote;
    }

    public String getsMaterialReceiverName() {
        return sMaterialReceiverName;
    }


    // setter

    public void setsDate(String sDate) {
        this.sDate = sDate;
    }

    public void setsTeamName(String sTeamName) {
        this.sTeamName = sTeamName;
    }

    public void setsLine(String sLine) {
        this.sLine = sLine;
    }

    public void setsTender(String sTender) {
        this.sTender = sTender;
    }

    public void setsDriverName(String sDriverName) {
        this.sDriverName = sDriverName;
    }

    public void setsVehicalName(String sVehicalName) {
        this.sVehicalName = sVehicalName;
    }

    public void setsConsumerName(String sConsumerName) {
        this.sConsumerName = sConsumerName;
    }

    public void setsState(String sState) {
        this.sState = sState;
    }

    public void setsDistrict(String SDistrict) {
        this.sDistrict = SDistrict;
    }

    public void setsTaluka(String sTaluka) {
        this.sTaluka = sTaluka;
    }

    public void setsCenter(String sCenter) {
        this.sCenter = sCenter;
    }

    public void setsVillage(String sVillage) {
        this.sVillage = sVillage;
    }

    public void setsSiteName(String sSiteName) {
        this.sSiteName = sSiteName;
    }

    public ArrayList<AddTotalMaterialTakenModel> getMaterialTakenModelArrayList() {
        return materialTakenModelArrayList;
    }

    public void setMaterialTakenModelArrayList(ArrayList<AddTotalMaterialTakenModel> materialTakenModelArrayList) {
        this.materialTakenModelArrayList = materialTakenModelArrayList;
    }

    public void setsNote(String sNote) {
        this.sNote = sNote;
    }

    public void setsMaterialReceiverName(String sMaterialReceiverName) {
        this.sMaterialReceiverName = sMaterialReceiverName;
    }



    private int bacPressCount = 0;

    @Override
    public void onBackPressed() {

        if (bacPressCount == 1) {
            profileBack();
        } else {
            Toast.makeText(this, "Go to back press 2 times back button!", Toast.LENGTH_SHORT).show();
        }

        bacPressCount++;

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                bacPressCount = 0;
            }
        }, 2000);


    }

    private void profileBack() {

        DocumentReference documentReference = fStore.collection("Users")
                .document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                String profile = value.getString("profile");

                if (profile.equalsIgnoreCase("ADMIN")) {
                    startActivity(new Intent(getApplicationContext(), Admin.class));
                } else if (profile.equalsIgnoreCase("SUPERVISOR")) {
                    startActivity(new Intent(getApplicationContext(), Supervisor.class));
                } else {
                    startActivity(new Intent(getApplicationContext(), TeamLearder.class));
                }
                finish();

            }
        });


    }
}