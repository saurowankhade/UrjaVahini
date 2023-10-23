package com.example.electricalmaterial.material.updation;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

import com.example.electricalmaterial.AddTotalMaterialTakenModel;
import com.example.electricalmaterial.AdminCustomAdapter;
import com.example.electricalmaterial.R;
import com.example.electricalmaterial.getinfromation.GetUserInfromation;
import com.example.electricalmaterial.settingsprinner.CustomSpinner;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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

public class AdminViewMaterial extends AppCompatActivity {

    private int count = 0; // user can click actv at one time -> actv (AutoCompleteTextView)

    private GetUserInfromation userInfromation = new GetUserInfromation();
    // AUth
    private FirebaseAuth mAuth;
    private FirebaseFirestore fStore;
    private String companyEmail;

    private String cmp;
    private String userId;
    private ProgressDialog pd;
    private List<ModelUpdation> modelList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private AdminCustomAdapter adapter;
    private FloatingActionButton mAddBtn;

    private SwipeRefreshLayout refreshLayout;

    private androidx.appcompat.widget.SearchView searchView;


    //date
    private DatePickerDialog.OnDateSetListener form ;
    private DatePickerDialog.OnDateSetListener to;

    private Button exportToExcelButton;
    protected CustomSpinner csTeamName,csConsumerName,csTender;

    private Dialog dialog;
    private String exportBy; // Date , TeamName ect..
    private List<String> materialList = new ArrayList<>();

    private ArrayList<AddTotalMaterialTakenModel> [] materialModelList;



    private ArrayList<AddTotalMaterialTakenModel> preMainData = new ArrayList<>();
    private ArrayList<AddTotalMaterialTakenModel> preTotalMaterial = new ArrayList<>();
    private ArrayList<AddTotalMaterialTakenModel> preBalanceMaterial = new ArrayList<>();



    @SuppressLint({"SourceLockedOrientationActivity", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_material);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        // Declaring name
        ActionBar bar = getSupportActionBar();
        assert bar != null;
        bar.setTitle("MATERIAL UPDATION");


        // initializing
        fStore = FirebaseFirestore.getInstance();
        pd = ProgressDialog.show(this, "Loading...", "Please Wait", false, false);
        pd.dismiss();
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = mAuth.getCurrentUser().getUid();
        recyclerView = findViewById(R.id.recycler_view);
        refreshLayout = findViewById(R.id.refresh);
        searchView = findViewById(R.id.searchView);

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        modelList.clear();
        mAddBtn = findViewById(R.id.addBtn);





        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                ArrayList<ModelUpdation> models = new ArrayList<>();
                for (ModelUpdation model : modelList){
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
                    else if (model.getCenterName().toLowerCase().contains(s.toLowerCase())){
                        models.add(model);
                    }
                    else if (model.getSite().toLowerCase().contains(s.toLowerCase())){
                        models.add(model);
                    } else {
                        // when data not found
                        Toast.makeText(AdminViewMaterial.this, "No found!", Toast.LENGTH_SHORT).show();
                    }
                }

                AdminCustomAdapter adminCustomAdapter = new AdminCustomAdapter(AdminViewMaterial.this,models);
                recyclerView.setAdapter(adminCustomAdapter);

                return true;
            }
        });


        DocumentReference documentReference = fStore.collection("Users")
                .document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                companyEmail = value.getString("companyEmail");
                cmp = companyEmail;
                companyEmail = companyEmail.replace("@", "");
                companyEmail = companyEmail.replace(".", "");

                materialList.clear();
                showMaterial(companyEmail);

                modelList.clear();
                showData(cmp);

                refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        showData(cmp);
                        refreshLayout.setRefreshing(false);
                    }
                });

                mAddBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showCustomDialog(cmp);
                    }
                });
            }
        });

    }

    private void showCustomDialog(String cmp) {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.export_to_excel_checking);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.back_background);
        dialog.show();

        exportToExcelButton = dialog.findViewById(R.id.exportToExcel);

        exportToExcelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                        checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                }
                else {
                    //your code
                    createXlFile();
                }
            }
        });

        LinearLayout whenDateSelect = dialog.findViewById(R.id.whenDateSelect);
        LinearLayout whenOtherSelect = dialog.findViewById(R.id.whenOtherSelect);

        RadioGroup r = dialog.findViewById(R.id.ed);

        final RadioButton[] radio_button = new RadioButton[4];

        r.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                for(int i=0; i<r.getChildCount(); i++) {
                    radio_button[0] = (RadioButton) r.getChildAt(0);
                    radio_button[1] = (RadioButton) r.getChildAt(1);
                    radio_button[2] = (RadioButton) r.getChildAt(2);
                    radio_button[3] = (RadioButton) r.getChildAt(3);
                    if(radio_button[0].getId() == checkedId) {
                        exportBy = radio_button[0].getText().toString();
                        whenDateSelect.setVisibility(View.VISIBLE);
                        whenOtherSelect.setVisibility(View.GONE);
                    }
                    else{
                        if(radio_button[1].getId() == checkedId) {
                            exportBy = radio_button[1].getText().toString();
                        }
                        else if(radio_button[2].getId() == checkedId) {
                            exportBy = radio_button[2].getText().toString();
                        }
                        else if(radio_button[3].getId() == checkedId) {
                            exportBy = radio_button[3].getText().toString();
                        }
                        whenDateSelect.setVisibility(View.GONE);
                        whenOtherSelect.setVisibility(View.VISIBLE);
                    }
                }

            }
        });

        // when use wants to export excel sheet by date -> (form-to)
        TextView formDate = dialog.findViewById(R.id.formDate);
        TextView toDate = dialog.findViewById(R.id.toDate);
        TextView dateCheck = dialog.findViewById(R.id.dateCheck);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        formDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        AdminViewMaterial.this, android.R.style.Theme_Material_Light_Dialog_MinWidth,
                        form, year, month, day);

                datePickerDialog.getWindow()
                        .setBackgroundDrawable(new ColorDrawable(Color.GREEN));
                datePickerDialog.show();
            }
        });
        form = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = "";
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

                formDate.setText(date);

            }
        };

        toDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        AdminViewMaterial.this, android.R.style.Theme_Material_Light_Dialog_MinWidth,
                        to, year, month, day);

                datePickerDialog.getWindow()
                        .setBackgroundDrawable(new ColorDrawable(Color.GREEN));
                datePickerDialog.show();
            }
        });
        to = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date1 = "";
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
                toDate.setText(date1);

            }
        };
        dateCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dateFrom = formDate.getText().toString().trim();
                String dateTo = toDate.getText().toString().trim();
                if (dateFrom.equalsIgnoreCase("from")){
                    formDate.setError("Select date");
                } else if (dateTo.equalsIgnoreCase("to")) {
                    toDate.setError("Select date");
                } else {
                    showDate(cmp,dateFrom,dateTo );
                }
            }
        });



        // when use wants to export excel sheet in other mode like TeamName, consumerName and Tender

        AutoCompleteTextView actvName = dialog.findViewById(R.id.actvName);
        Button nameCheck = dialog.findViewById(R.id.nameCheck);


        actvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count == 0){

                    if (exportBy.contains("Team")){
                        showSprineer(csTeamName,"teamName",actvName); // teamName
                    } else if (exportBy.contains("Consumer")) {
                        showSprineer(csConsumerName,"consumerName",actvName); // consumerName
                    } else if (exportBy.contains("Tender")) {
                        showSprineer(csTender,"Tender",actvName); // Tender
                    }
                }
                count++;

            }
        });
        nameCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = actvName.getText().toString().trim();

                if (name.isEmpty()){
                    actvName.setError("Required!");
                } else {
                    showName(cmp,name,exportBy);
                }
            }
        });

    }

    private void showName(String cmp, String name,String exportBy) {
        pd.setTitle("Checking...");
        pd.show();
        fStore.collection(cmp+" MainAddData")
                .orderBy("Date", Query.Direction.DESCENDING)
                .whereEqualTo(exportBy,name)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        modelList.clear();
                        pd.dismiss();
                        for (DocumentSnapshot doc : task.getResult()){
                            ModelUpdation model = new ModelUpdation(
                                    doc.getString("id"),
                                    doc.getString("Date"),
                                    doc.getString("Team Name"),
                                    doc.getString("Line"),
                                    doc.getString("Tender"),
                                    doc.getString("Driver Name"),
                                    doc.getString("Vehical Name"),
                                    doc.getString("Consumer Name"),
                                    doc.getString("State"),
                                    doc.getString("District"),
                                    doc.getString("Taluka"),
                                    doc.getString("Center"),
                                    doc.getString("Village"),
                                    doc.getString("Site Name"),
                                    doc.getString("Material Receiver Name"),
                                    doc.getString("Material Giver Name"),
                                    doc.getString("Note")
                            );
                            modelList.add(model);
                        }
                        materialModelList = new ArrayList[modelList.size()];
                        for (int i = 0; i < modelList.size(); i++) {
                            showMaterial(cmp,  modelList.get(i).getId(), i);

                        }
                        exportToExcelButton.setClickable(true);
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

    private void showDate(String cmp, String dateFrom, String dateTo) {
        pd.setTitle("Checking...");
        pd.show();
        fStore.collection(cmp+" MainAddData")
                .orderBy("Date", Query.Direction.DESCENDING)
                .startAt(dateTo).endAt(dateFrom)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        modelList.clear();
                        pd.dismiss();
                        for (DocumentSnapshot doc : task.getResult()){
                            ModelUpdation model = new ModelUpdation(
                                    doc.getString("id"),
                                    doc.getString("Date"),
                                    doc.getString("Team Name"),
                                    doc.getString("Line"),
                                    doc.getString("Tender"),
                                    doc.getString("Driver Name"),
                                    doc.getString("Vehical Name"),
                                    doc.getString("Consumer Name"),
                                    doc.getString("State"),
                                    doc.getString("District"),
                                    doc.getString("Taluka"),
                                    doc.getString("Center"),
                                    doc.getString("Village"),
                                    doc.getString("Site Name"),
                                    doc.getString("Material Receiver Name"),
                                    doc.getString("Material Giver Name"),
                                    doc.getString("Note")
                            );
                            modelList.add(model);
                        }


                        materialModelList = new ArrayList[modelList.size()];
                        for (int i = 0; i < modelList.size(); i++) {
                            showMaterial(cmp, modelList.get(i).getId(), i);
                        }

                        exportToExcelButton.setClickable(true);
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

    private void showData(String cmp) {
        pd.setTitle("Loading Data...");
        pd.show();

        fStore.collection(cmp + " MainAddData")
                .orderBy("Date", Query.Direction.DESCENDING).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        modelList.clear();
                        pd.dismiss();
                        refreshLayout.setRefreshing(false);
                        for (DocumentSnapshot doc : queryDocumentSnapshots) {
                            ModelUpdation model = new ModelUpdation(
                                    doc.getString("id"),
                                    doc.getString("Date"),
                                    doc.getString("Team Name"),
                                    doc.getString("Line"),
                                    doc.getString("Tender"),
                                    doc.getString("Driver Name"),
                                    doc.getString("Vehical Name"),
                                    doc.getString("Consumer Name"),
                                    doc.getString("State"),
                                    doc.getString("District"),
                                    doc.getString("Taluka"),
                                    doc.getString("Center"),
                                    doc.getString("Village"),
                                    doc.getString("Site Name"),
                                    doc.getString("Material Receiver Name"),
                                    doc.getString("Material Giver Name"),
                                    doc.getString("Note")
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

    private void showMaterial(String cmp, String id, int position) {
        pd.show();
        pd.setTitle("Loading...");
        materialModelList[position] = new ArrayList<>();
        fStore.collection(cmp + " MainAddData")
                .document(id)
                .collection("MaterialList")
                .orderBy("Material", Query.Direction.ASCENDING)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        materialModelList[position].clear();
                        for (DocumentSnapshot doc : task.getResult()) {
                            AddTotalMaterialTakenModel model = new AddTotalMaterialTakenModel(
                                    doc.getString("Id"),
                                    doc.getString("Material"),
                                    doc.getString("Unit"),
                                    doc.getString("Quantity")
                            );

                            // Access the array element safely

                            materialModelList[position].add(model);

                        }
                        exportToExcelButton.setClickable(true);
                        pd.dismiss();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AdminViewMaterial.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


    }


    private void showMaterial(String companyEmail) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(companyEmail + " " + "Material");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                materialList.clear(); // Clear the ArrayList to avoid duplicates if onDataChange is called multiple times

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String data = snapshot.getValue(String.class);
                    materialList.add(data); // Add the data to the ArrayList
                    // Toast.makeText(ViewTotalMaterialTaken.this, "Data : " + data, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(AdminViewMaterial.this, "Error : " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void deleteData(int position) {

        DocumentReference documentReference = fStore.collection("Users")
                .document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                String cmp = value.getString("companyEmail");
                fStore.collection(cmp + " MainAddData").document(modelList.get(position).getId()).delete();
                fStore.collection(cmp + " MainReturnData").document(modelList.get(position).getId()).delete();
                showTotalMaterialTaken(cmp, modelList.get(position).getConsumerName(), modelList.get(position).getTeamName());
                showMainData(cmp, modelList.get(position).getId());
                showBalanceMaterial(cmp, modelList.get(position).getConsumerName(), modelList.get(position).getTeamName());
                showData(cmp);
            }
        });

    }

    private void showMainData(String cmp, String id) {
        fStore.collection(cmp + " MainAddData")
                .document(id)
                .collection("MaterialList")
                .orderBy("Material", Query.Direction.ASCENDING)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        preMainData.clear();
                        for (DocumentSnapshot doc : task.getResult()) {
                            AddTotalMaterialTakenModel model = new AddTotalMaterialTakenModel(
                                    doc.getString("Id"),
                                    doc.getString("Material"),
                                    doc.getString("Unit"),
                                    doc.getString("Quantity")
                            );

                            // Access the array element safely
                            preMainData.add(model);

                        }



                    }
                });
    }

    private void showBalanceMaterial(String cmp, String consumerName, String teamName) {
        fStore.collection(cmp + " BalanceMaterialData")
                .document(consumerName+" "+teamName)
                .collection("MaterialList")
                .orderBy("Material", Query.Direction.ASCENDING)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        preBalanceMaterial.clear();
                        for (DocumentSnapshot doc : task.getResult()) {
                            AddTotalMaterialTakenModel model = new AddTotalMaterialTakenModel(
                                    doc.getString("Id"),
                                    doc.getString("Material"),
                                    doc.getString("Unit"),
                                    doc.getString("Quantity")
                            );

                            // Access the array element safely
                            preBalanceMaterial.add(model);

                        }

                        removeDataFromBalance(cmp,consumerName,teamName,preBalanceMaterial);


                    }
                });

    }

    private void removeDataFromBalance(String cmp, String consumerName, String teamName, ArrayList<AddTotalMaterialTakenModel> preBalanceMaterial) {
        HashMap<String,AddTotalMaterialTakenModel> balanceData = new HashMap<>();
        HashMap<String,AddTotalMaterialTakenModel> mainData = new HashMap<>();

        for (int i = 0; i < preBalanceMaterial.size(); i++) {
            balanceData.put(preBalanceMaterial.get(i).getMaterial(),new AddTotalMaterialTakenModel(
                    preBalanceMaterial.get(i).getMaterial(),
                    preBalanceMaterial.get(i).getMaterial(),
                    preBalanceMaterial.get(i).getUnit(),
                    preBalanceMaterial.get(i).getQuantity()
            ));
        }

        for (int i = 0; i < preMainData.size(); i++) {
            mainData.put(preMainData.get(i).getMaterial(),new AddTotalMaterialTakenModel(
                    preMainData.get(i).getMaterial(),
                    preMainData.get(i).getMaterial(),
                    preMainData.get(i).getUnit(),
                    preMainData.get(i).getQuantity()
            ));
        }

        for (String material : mainData.keySet()){
            if (material.contains(balanceData.get(material).getMaterial().trim())){
                float bQ = Float.parseFloat(balanceData.get(material).getQuantity().trim());
                float mQ = Float.parseFloat(mainData.get(material).getQuantity().trim());
                float tQ = bQ - mQ;
                addBalanceMaterial(cmp,consumerName,teamName,balanceData.get(material).getMaterial().trim(),balanceData.get(material).getUnit().trim(),String.valueOf(tQ));
            }
        }





    }

    private void removeDataFromTotal(String cmp, String consumerName, String teamName, ArrayList<AddTotalMaterialTakenModel> preBalanceMaterial) {
        HashMap<String,AddTotalMaterialTakenModel> balanceData = new HashMap<>();
        HashMap<String,AddTotalMaterialTakenModel> mainData = new HashMap<>();

        for (int i = 0; i < preBalanceMaterial.size(); i++) {
            balanceData.put(preBalanceMaterial.get(i).getMaterial(),new AddTotalMaterialTakenModel(
                    preBalanceMaterial.get(i).getMaterial(),
                    preBalanceMaterial.get(i).getMaterial(),
                    preBalanceMaterial.get(i).getUnit(),
                    preBalanceMaterial.get(i).getQuantity()
            ));
        }

        for (int i = 0; i < preMainData.size(); i++) {
            mainData.put(preMainData.get(i).getMaterial(),new AddTotalMaterialTakenModel(
                    preMainData.get(i).getMaterial(),
                    preMainData.get(i).getMaterial(),
                    preMainData.get(i).getUnit(),
                    preMainData.get(i).getQuantity()
            ));
        }

        for (String material : mainData.keySet()){
            if (material.contains(balanceData.get(material).getMaterial().trim())){
                float bQ = Float.parseFloat(balanceData.get(material).getQuantity().trim());
                float mQ = Float.parseFloat(mainData.get(material).getQuantity().trim());
                float tQ = bQ - mQ;
                addTotalMaterial(cmp,consumerName,teamName,balanceData.get(material).getMaterial().trim(),balanceData.get(material).getUnit().trim(),String.valueOf(tQ));
            }
        }

    }

    private void addBalanceMaterial(String cmp, String consumerName, String teamName, String material, String unit, String quantity) {
        Map<String, Object> doc = new HashMap<>();
        doc.put("Id", material);
        doc.put("Material", material);
        doc.put("Unit", unit);
        doc.put("Quantity", quantity);

        fStore.collection(cmp + " BalanceMaterialData")
                .document(consumerName + " " + teamName)
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

    private void addTotalMaterial(String cmp, String consumerName, String teamName, String material, String unit, String quantity) {
        Map<String, Object> doc = new HashMap<>();
        doc.put("Id", material);
        doc.put("Material", material);
        doc.put("Unit", unit);
        doc.put("Quantity", quantity);

        fStore.collection(cmp + " TotalMaterialData")
                .document(consumerName + " " + teamName)
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

    private void showTotalMaterialTaken(String cmp, String consumerName, String teamName) {

        fStore.collection(cmp + " TotalMaterialData")
                .document(consumerName+" "+teamName)
                .collection("MaterialList")
                .orderBy("Material", Query.Direction.ASCENDING)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        preTotalMaterial.clear();
                        for (DocumentSnapshot doc : task.getResult()) {
                            AddTotalMaterialTakenModel model = new AddTotalMaterialTakenModel(
                                    doc.getString("Id"),
                                    doc.getString("Material"),
                                    doc.getString("Unit"),
                                    doc.getString("Quantity")
                            );

                            // Access the array element safely

                            preTotalMaterial.add(model);

                        }
                        removeDataFromTotal(cmp,consumerName,teamName,preTotalMaterial);
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


        Workbook wb = new HSSFWorkbook();
        Cell cell = null;
        Sheet sheet;
        sheet = wb.createSheet("Added Material List");

        settingHeader(cell, sheet);

        settingMainData(wb, cell, sheet);

        String folderName = "UrjaVahini";
        String fileName = "Total Material List" + System.currentTimeMillis() + ".xls";
        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + File.separator + folderName + File.separator + fileName;

        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + File.separator + folderName);
        file.mkdirs();
//        if (!file.exists()) {
//            file.mkdirs();
//        }

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

    private void settingHeader(Cell cell, Sheet sheet) {

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
        cell.setCellValue("State");

        cell = row.createCell(8);
        cell.setCellValue("District");

        cell = row.createCell(9);
        cell.setCellValue("Taluka");

        cell = row.createCell(10);
        cell.setCellValue("Center");

        cell = row.createCell(11);
        cell.setCellValue("Village");

        cell = row.createCell(12);
        cell.setCellValue("Site Name");

        cell = row.createCell(13);
        cell.setCellValue("Material Receiver Name");

        cell = row.createCell(14);
        cell.setCellValue("Material Giver Name");
        cell = row.createCell(15);
        cell.setCellValue("Note");


        for (int i = 0; i < materialList.size(); i++) {
            cell = row.createCell(16 + i);
            cell.setCellValue(materialList.get(i));
            sheet.setColumnWidth(i + 10, (30 * 200));
        }


    }

    private void settingMainData(Workbook wb, Cell cell, Sheet sheet) {
        for (int i = 0; i < modelList.size(); i++) {

            Row row = sheet.createRow(i + 1);

            cell = row.createCell(0);
            cell.setCellValue(modelList.get(i).getDate());

            cell = row.createCell(1);
            cell.setCellValue((modelList.get(i).getTeamName()));

            // cell.setCellStyle(cellStyle);

            cell = row.createCell(2);
            cell.setCellValue(modelList.get(i).getLine());

            cell = row.createCell(3);
            cell.setCellValue(modelList.get(i).getTender());

            cell = row.createCell(4);
            cell.setCellValue(modelList.get(i).getDriverName());

            cell = row.createCell(5);
            cell.setCellValue(modelList.get(i).getVehicalName());

            cell = row.createCell(6);
            cell.setCellValue(modelList.get(i).getConsumerName());

            cell = row.createCell(7);
            cell.setCellValue(modelList.get(i).getState());

            cell = row.createCell(8);
            cell.setCellValue(modelList.get(i).getDistrict());

            cell = row.createCell(9);
            cell.setCellValue(modelList.get(i).getTaluka());

            cell = row.createCell(10);
            cell.setCellValue(modelList.get(i).getCenterName());

            cell = row.createCell(11);
            cell.setCellValue(modelList.get(i).getVillage());

            cell = row.createCell(12);
            cell.setCellValue(modelList.get(i).getSite());

            cell = row.createCell(13);
            cell.setCellValue(modelList.get(i).getMaterialReceiverName());

            cell = row.createCell(14);
            cell.setCellValue(modelList.get(i).getMaterialGiverName());
            cell = row.createCell(15);
            cell.setCellValue(modelList.get(i).getNote());

            for (int ml = 0; ml < materialList.size(); ml++) {
                for (int subMML = 0; subMML < materialModelList[i].size(); subMML++) {
                    if (materialList.get(ml).trim().equalsIgnoreCase(materialModelList[i].get(subMML).getMaterial().trim())) {
                        int count = ml + 16;
                        cell = row.createCell(count);
                        cell.setCellValue(materialModelList[i].get(subMML).getQuantity().trim());
                    }
                }
            }

        }

    }

    private void showSprineer(CustomSpinner customSpinner, String type, AutoCompleteTextView autoCompleteTextView) {
       // Toast.makeText(this, "user : "+userInfromation.getUserRole(), Toast.LENGTH_SHORT).show();
        customSpinner = new CustomSpinner(getApplicationContext(), userInfromation.getCompanyEmailWithOutExtension(), type);
        autoCompleteTextView.setAdapter(customSpinner.getAdapter());
        customSpinner.retrieveData();
    }

}