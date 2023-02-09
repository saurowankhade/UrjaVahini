package com.example.electricalmaterial;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
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
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StockEntry extends AppCompatActivity {

    List<StockEntryModel> modelList = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    SwipeRefreshLayout refreshLayout;

    StockEntryAdapter adapter;
    FloatingActionButton mAddBtn;

    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String companyEmail;
    String userId;
    ProgressDialog pd;
    String cmp;


    List<AddStockModel> modelListAddStock = new ArrayList<>();
    RecyclerView recyclerViewAddStock;
    RecyclerView.LayoutManager layoutManagerAddStock;
    StockEntryAddStockAdapter adapterAddStock;


    //Search
    AutoCompleteTextView selectSearch, materialReceiverName;
    TextView dateFrom,dateTo;
    ImageView searchDateBtn;
    ArrayAdapter <String> adapterSearch;
    String [] item = {"Date","Receiver"};


    //Receiver
    ArrayAdapter<String> adapterReceiver;
    ArrayList<String> spinnerDataListReceiver;
    DatabaseReference databaseReferenceReceiver;
    ValueEventListener listenerReceiver;

    //date
    DatePickerDialog.OnDateSetListener onDateSetListener;
    String date;
    DatePickerDialog.OnDateSetListener onDateSetListener1;
    String date1;

    //TextInput
    TextInputLayout materialReceiverNameTextInput;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_entry);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        fStore = FirebaseFirestore.getInstance();
        pd = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = mAuth.getCurrentUser().getUid();


        ActionBar bar = getSupportActionBar();
        assert bar != null;
        bar.setTitle("STOCK ENTRY");


        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        modelList.clear();
        mAddBtn = findViewById(R.id.addBtn);



        recyclerViewAddStock = findViewById(R.id.recycler);
        recyclerViewAddStock.setHasFixedSize(true);
        layoutManagerAddStock = new LinearLayoutManager(this);
        recyclerViewAddStock.setLayoutManager(layoutManagerAddStock);
        modelListAddStock.clear();


        //search
        //selectSearch
        selectSearch = findViewById(R.id.selectSearch);
        adapterSearch = new ArrayAdapter<String>(StockEntry.this,R.layout.support_simple_spinner_dropdown_item,item);
        selectSearch.setAdapter(adapterSearch);

        materialReceiverName = findViewById(R.id.materialReceiverName);
        dateFrom = findViewById(R.id.dateFrom);
        dateTo = findViewById(R.id.dateTo);
        //Date
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        dateFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        StockEntry.this, android.R.style.Theme_Material_Light_Dialog_MinWidth,
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

                dateFrom.setText(date);

            }
        };

        dateTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        StockEntry.this, android.R.style.Theme_Material_Light_Dialog_MinWidth,
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
                dateTo.setText(date1);

            }
        };
        searchDateBtn = findViewById(R.id.dateSearchBtn);
        materialReceiverNameTextInput = findViewById(R.id.materialReceiverNameTextInput);





        DocumentReference documentReference = fStore.collection("Users")
                .document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                companyEmail = value.getString("companyEmail");
                cmp = companyEmail;
                companyEmail = companyEmail.replace("@", "");
                companyEmail = companyEmail.replace(".", "");

                showData(cmp);
                showAddStockData(cmp);

                refreshLayout = findViewById(R.id.refresh);
                refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        showData(cmp);
                        showAddStockData(cmp);

                    }
                });
                mAddBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                            startActivity(new Intent(getApplicationContext(), Stock.class));
                            finish();

                    }
                });

                //Receiver
                databaseReferenceReceiver = FirebaseDatabase.getInstance().getReference(companyEmail+" Receiver");
                spinnerDataListReceiver = new ArrayList<>();
                adapterReceiver = new ArrayAdapter<String>(StockEntry.this,R.layout.support_simple_spinner_dropdown_item,spinnerDataListReceiver);
                materialReceiverName.setAdapter(adapterReceiver);
                retrieveDataReceiver();

                selectSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String n = parent.getItemAtPosition(position).toString().trim();
                        switch (n){
                            case "Date" :
                                dateFrom.setVisibility(View.VISIBLE);
                                dateTo.setVisibility(View.VISIBLE);
                                searchDateBtn.setVisibility(View.VISIBLE);
                                materialReceiverNameTextInput.setVisibility(View.GONE);
                              break;

                            case "Receiver":
                                dateFrom.setVisibility(View.GONE);
                                dateTo.setVisibility(View.GONE);
                                searchDateBtn.setVisibility(View.GONE);
                                materialReceiverNameTextInput.setVisibility(View.VISIBLE);
                                break;
                        }
                    }
                });

                searchDateBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String d1 = dateFrom.getText().toString().trim();
                        String d2 = dateTo.getText().toString().trim();
                        if (d1.isEmpty()||d2.isEmpty()){
                            showMessage();
                        }
                        else {
                            searchDate(cmp,date,date1);
                        }
                    }
                });

                materialReceiverName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String n = parent.getItemAtPosition(position).toString().trim();
                        showMaterialReceiver(cmp,n);
                    }
                });


            }

        });

    }

    private void showMaterialReceiver(String cmp, String n) {
        pd.setTitle("Searching...");
        pd.show();
        fStore.collection(cmp + " StockEntry").whereEqualTo("Material Receiver Name",n)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        modelList.clear();
                        pd.dismiss();
                        for (DocumentSnapshot doc : task.getResult()) {
                            StockEntryModel model = new StockEntryModel(
                                    doc.getString("id"),
                                    doc.getString("Date"),
                                    doc.getString("Supplier"),
                                    doc.getString("SupplierAddress"),
                                    doc.getString("DriverName"),
                                    doc.getString("StoreName"),
                                    doc.getString("Vehical"),
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
                                    doc.getString("Material Receiver Name")
                            );
                            modelList.add(model);
                        }
                        adapter = new StockEntryAdapter(StockEntry.this, modelList);
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

    private void searchDate(String cmp, String date, String date1) {
        pd.setTitle("Searching...");
        pd.show();
        fStore.collection(cmp + " StockEntry").orderBy("Date", Query.Direction.DESCENDING).startAt(date1).endAt(date)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        modelList.clear();
                        pd.dismiss();
                        for (DocumentSnapshot doc : task.getResult()) {
                            StockEntryModel model = new StockEntryModel(
                                    doc.getString("id"),
                                    doc.getString("Date"),
                                    doc.getString("Supplier"),
                                    doc.getString("SupplierAddress"),
                                    doc.getString("DriverName"),
                                    doc.getString("StoreName"),
                                    doc.getString("Vehical"),
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
                                    doc.getString("Material Receiver Name")
                            );
                            modelList.add(model);
                        }
                        adapter = new StockEntryAdapter(StockEntry.this, modelList);
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

    private void showMessage() {
        Toast.makeText(this, "Please Select Date range to search", Toast.LENGTH_SHORT).show();
    }

    private void showAddStockData(String cmp) {
        fStore.collection(cmp+" AddStock").orderBy("Quantity", Query.Direction.ASCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                modelListAddStock.clear();
                pd.dismiss();
                refreshLayout.setRefreshing(false);
                for (DocumentSnapshot doc : task.getResult()){
                    AddStockModel model = new AddStockModel(
                            doc.getString("Id"),
                            doc.getString("Material"),
                            doc.getString("Unit"),
                            doc.getString("Quantity")
                    );
                    modelListAddStock.add(model);
                }
                adapterAddStock = new StockEntryAddStockAdapter(StockEntry.this,modelListAddStock);
                recyclerViewAddStock.setAdapter(adapterAddStock);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                refreshLayout.setRefreshing(false);
                Toast.makeText(StockEntry.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void showData(String cmp) {
        pd.setTitle("Searching...");
        pd.show();
        fStore.collection(cmp + " StockEntry").orderBy("Date", Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        modelList.clear();
                        refreshLayout.setRefreshing(false);
                        pd.dismiss();
                        for (DocumentSnapshot doc : queryDocumentSnapshots) {
                            StockEntryModel model = new StockEntryModel(
                                    doc.getString("id"),
                                    doc.getString("Date"),
                                    doc.getString("Supplier"),
                                    doc.getString("SupplierAddress"),
                                    doc.getString("DriverName"),
                                    doc.getString("StoreName"),
                                    doc.getString("Vehical"),
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
                                    doc.getString("Material Receiver Name")
                            );
                            modelList.add(model);
                        }
                        adapter = new StockEntryAdapter(StockEntry.this, modelList);
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

    public void deleteData(int position) {


        DocumentReference documentReference = fStore.collection("Users")
                .document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                companyEmail = value.getString("companyEmail");
                String cmp = companyEmail;

                retriveData(cmp,position);

                fStore.collection(cmp + " StockEntry").document(modelList.get(position).getId())
                        .delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                pd.dismiss();
                                Toast.makeText(StockEntry.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                                showData(cmp);
                                retriveData(cmp,position);

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

    private void retriveData(String cmp,int i) {
        String m1 = modelList.get(i).getMaterialReceiver();
        String u1 = modelList.get(i).getuMaterial1();
        String q1 = modelList.get(i).getuMaterial2();

        String m2 = modelList.get(i).getuMaterial3();
        String u2 = modelList.get(i).getuMaterial4();
        String q2 = modelList.get(i).getuMaterial5();

        String m3 = modelList.get(i).getuMaterial6();
        String u3 = modelList.get(i).getuMaterial7();
        String q3 = modelList.get(i).getuMaterial8();

        String m4 = modelList.get(i).getuMaterial9();
        String u4 = modelList.get(i).getuMaterial10();
        String q4 = modelList.get(i).getuMaterial11();

        String m5 = modelList.get(i).getuMaterial12();
        String u5 = modelList.get(i).getuMaterial13();
        String q5 = modelList.get(i).getuMaterial14();

        String m6 = modelList.get(i).getuMaterial15();
        String u6 = modelList.get(i).getuMaterial16();
        String q6 = modelList.get(i).getuMaterial17();

        String m7 = modelList.get(i).getuMaterial18();
        String u7 = modelList.get(i).getuMaterial19();
        String q7 = modelList.get(i).getuMaterial20();

        String m8 = modelList.get(i).getuMaterial21();
        String u8 = modelList.get(i).getuMaterial22();
        String q8 = modelList.get(i).getuMaterial23();

        String m9 = modelList.get(i).getuMaterial24();
        String u9 = modelList.get(i).getuMaterial25();
        String q9 = modelList.get(i).getuMaterial26();

        String m10 = modelList.get(i).getuMaterial27();
        String u10 = modelList.get(i).getuMaterial28();
        String q10 = modelList.get(i).getuMaterial29();

        String m11 = modelList.get(i).getuMaterial30();
        String u11 = modelList.get(i).getuQuantity1();
        String q11 = modelList.get(i).getuUnit2();

        String m12 = modelList.get(i).getuQuantity3();
        String u12 = modelList.get(i).getuQuantity4();
        String q12 = modelList.get(i).getuQuantity5();

        String m13 = modelList.get(i).getuQuantity6();
        String u13 = modelList.get(i).getuQuantity7();
        String q13 = modelList.get(i).getuQuantity8();

        String m14 = modelList.get(i).getuQuantity9();
        String u14 = modelList.get(i).getuQuantity10();
        String q14 = modelList.get(i).getuQuantity11();

        String m15 = modelList.get(i).getuQuantity12();
        String u15 = modelList.get(i).getuQuantity13();
        String q15 = modelList.get(i).getuQuantity14();

        String m16 = modelList.get(i).getuQuantity15();
        String u16 = modelList.get(i).getuQuantity16();
        String q16 = modelList.get(i).getuQuantity17();

        String m17 = modelList.get(i).getuQuantity18();
        String u17 = modelList.get(i).getuQuantity19();
        String q17 = modelList.get(i).getuQuantity20();

        String m18 = modelList.get(i).getuQuantity21();
        String u18 = modelList.get(i).getuQuantity22();
        String q18 = modelList.get(i).getuQuantity23();

        String m19 = modelList.get(i).getuQuantity24();
        String u19 = modelList.get(i).getuQuantity25();
        String q19 = modelList.get(i).getuQuantity26();

        String m20 = modelList.get(i).getuQuantity27();
        String u20 = modelList.get(i).getuQuantity28();
        String q20 = modelList.get(i).getuQuantity29();

        String m21 = modelList.get(i).getuQuantity30();
        String u21 = modelList.get(i).getuUnit1();
        String q21 = modelList.get(i).getuQuantity2();

        String m22 = modelList.get(i).getuUnit3();
        String u22 = modelList.get(i).getuUnit4();
        String q22 = modelList.get(i).getuUnit5();

        String m23 = modelList.get(i).getuUnit6();
        String u23 = modelList.get(i).getuUnit7();
        String q23 = modelList.get(i).getuUnit8();

        String m24 = modelList.get(i).getuUnit9();
        String u24 = modelList.get(i).getuUnit10();
        String q24 = modelList.get(i).getuUnit11();

        String m25 = modelList.get(i).getuUnit12();
        String u25 = modelList.get(i).getuUnit13();
        String q25 = modelList.get(i).getuUnit14();

        String m26 = modelList.get(i).getuUnit15();
        String u26 = modelList.get(i).getuUnit16();
        String q26 = modelList.get(i).getuUnit17();

        String m27 = modelList.get(i).getuUnit18();
        String u27 = modelList.get(i).getuUnit19();
        String q27 = modelList.get(i).getuUnit20();

        String m28 = modelList.get(i).getuUnit21();
        String u28 = modelList.get(i).getuUnit22();
        String q28 = modelList.get(i).getuUnit23();

        String m29 = modelList.get(i).getuUnit24();
        String u29 = modelList.get(i).getuUnit25();
        String q29 = modelList.get(i).getuUnit26();

        String m30 = modelList.get(i).getuUnit27();
        String u30 = modelList.get(i).getuUnit28();
        String q30 = modelList.get(i).getuUnit29();

        for (int j=0;j<modelListAddStock.size();j++){

            if (!m1.isEmpty()){
                if (modelListAddStock.get(j).getMaterial().equals(m1)){
                    float a =  Float.parseFloat(modelListAddStock.get(j).getUnit()) - Float.parseFloat(q1) ;
                    addStock(cmp,modelListAddStock.get(j).getMaterial(),modelListAddStock.get(j).getQuantity(),a);
                }
            }

            if (!m2.isEmpty()){
                if (modelListAddStock.get(j).getMaterial().equals(m2)){
                    float a =  Float.parseFloat(modelListAddStock.get(j).getUnit()) - Float.parseFloat(q2) ;
                    addStock(cmp,modelListAddStock.get(j).getMaterial(),modelListAddStock.get(j).getQuantity(),a);
                }
            }

            if (!m3.isEmpty()){
                if (modelListAddStock.get(j).getMaterial().equals(m3)){
                    float a =  Float.parseFloat(modelListAddStock.get(j).getUnit()) - Float.parseFloat(q3) ;
                    addStock(cmp,modelListAddStock.get(j).getMaterial(),modelListAddStock.get(j).getQuantity(),a);
                }
            }

            if (!m4.isEmpty()){
                if (modelListAddStock.get(j).getMaterial().equals(m4)){
                    float a =  Float.parseFloat(modelListAddStock.get(j).getUnit()) - Float.parseFloat(q4) ;
                    addStock(cmp,modelListAddStock.get(j).getMaterial(),modelListAddStock.get(j).getQuantity(),a);
                }
            }

            if (!m5.isEmpty()){
                if (modelListAddStock.get(j).getMaterial().equals(m5)){
                    float a =  Float.parseFloat(modelListAddStock.get(j).getUnit()) - Float.parseFloat(q5) ;
                    addStock(cmp,modelListAddStock.get(j).getMaterial(),modelListAddStock.get(j).getQuantity(),a);
                }
            }

            if (!m6.isEmpty()){
                if (modelListAddStock.get(j).getMaterial().equals(m6)){
                    float a =  Float.parseFloat(modelListAddStock.get(j).getUnit()) - Float.parseFloat(q6) ;
                    addStock(cmp,modelListAddStock.get(j).getMaterial(),modelListAddStock.get(j).getQuantity(),a);
                }
            }

            if (!m7.isEmpty()){
                if (modelListAddStock.get(j).getMaterial().equals(m7)){
                    float a =  Float.parseFloat(modelListAddStock.get(j).getUnit()) - Float.parseFloat(q7) ;
                    addStock(cmp,modelListAddStock.get(j).getMaterial(),modelListAddStock.get(j).getQuantity(),a);
                }
            }

            if (!m8.isEmpty()){
                if (modelListAddStock.get(j).getMaterial().equals(m8)){
                    float a =  Float.parseFloat(modelListAddStock.get(j).getUnit()) - Float.parseFloat(q8) ;
                    addStock(cmp,modelListAddStock.get(j).getMaterial(),modelListAddStock.get(j).getQuantity(),a);
                }
            }

            if (!m9.isEmpty()){
                if (modelListAddStock.get(j).getMaterial().equals(m9)){
                    float a =  Float.parseFloat(modelListAddStock.get(j).getUnit()) - Float.parseFloat(q9) ;
                    addStock(cmp,modelListAddStock.get(j).getMaterial(),modelListAddStock.get(j).getQuantity(),a);
                }
            }

            if (!m10.isEmpty()){
                if (modelListAddStock.get(j).getMaterial().equals(m10)){
                    float a =  Float.parseFloat(modelListAddStock.get(j).getUnit()) - Float.parseFloat(q10) ;
                    addStock(cmp,modelListAddStock.get(j).getMaterial(),modelListAddStock.get(j).getQuantity(),a);
                }
            }

            if (!m11.isEmpty()){
                if (modelListAddStock.get(j).getMaterial().equals(m11)){
                    float a =  Float.parseFloat(modelListAddStock.get(j).getUnit()) - Float.parseFloat(q11) ;
                    addStock(cmp,modelListAddStock.get(j).getMaterial(),modelListAddStock.get(j).getQuantity(),a);
                }
            }

            if (!m12.isEmpty()){
                if (modelListAddStock.get(j).getMaterial().equals(m12)){
                    float a =  Float.parseFloat(modelListAddStock.get(j).getUnit()) - Float.parseFloat(q12) ;
                    addStock(cmp,modelListAddStock.get(j).getMaterial(),modelListAddStock.get(j).getQuantity(),a);
                }
            }

            if (!m13.isEmpty()){
                if (modelListAddStock.get(j).getMaterial().equals(m13)){
                    float a =  Float.parseFloat(modelListAddStock.get(j).getUnit()) - Float.parseFloat(q13) ;
                    addStock(cmp,modelListAddStock.get(j).getMaterial(),modelListAddStock.get(j).getQuantity(),a);
                }
            }

            if (!m14.isEmpty()){
                if (modelListAddStock.get(j).getMaterial().equals(m14)){
                    float a =  Float.parseFloat(modelListAddStock.get(j).getUnit()) - Float.parseFloat(q14) ;
                    addStock(cmp,modelListAddStock.get(j).getMaterial(),modelListAddStock.get(j).getQuantity(),a);
                }
            }

            if (!m15.isEmpty()){
                if (modelListAddStock.get(j).getMaterial().equals(m15)){
                    float a =  Float.parseFloat(modelListAddStock.get(j).getUnit()) - Float.parseFloat(q15) ;
                    addStock(cmp,modelListAddStock.get(j).getMaterial(),modelListAddStock.get(j).getQuantity(),a);
                }
            }

            if (!m16.isEmpty()){
                if (modelListAddStock.get(j).getMaterial().equals(m16)){
                    float a =  Float.parseFloat(modelListAddStock.get(j).getUnit()) - Float.parseFloat(q16) ;
                    addStock(cmp,modelListAddStock.get(j).getMaterial(),modelListAddStock.get(j).getQuantity(),a);
                }
            }

            if (!m17.isEmpty()){
                if (modelListAddStock.get(j).getMaterial().equals(m17)){
                    float a =  Float.parseFloat(modelListAddStock.get(j).getUnit()) - Float.parseFloat(q17) ;
                    addStock(cmp,modelListAddStock.get(j).getMaterial(),modelListAddStock.get(j).getQuantity(),a);
                }
            }

            if (!m18.isEmpty()){
                if (modelListAddStock.get(j).getMaterial().equals(m18)){
                    float a =  Float.parseFloat(modelListAddStock.get(j).getUnit()) - Float.parseFloat(q18) ;
                    addStock(cmp,modelListAddStock.get(j).getMaterial(),modelListAddStock.get(j).getQuantity(),a);
                }
            }

            if (!m19.isEmpty()){
                if (modelListAddStock.get(j).getMaterial().equals(m19)){
                    float a =  Float.parseFloat(modelListAddStock.get(j).getUnit()) - Float.parseFloat(q19) ;
                    addStock(cmp,modelListAddStock.get(j).getMaterial(),modelListAddStock.get(j).getQuantity(),a);
                }
            }

            if (!m20.isEmpty()){
                if (modelListAddStock.get(j).getMaterial().equals(m20)){
                    float a =  Float.parseFloat(modelListAddStock.get(j).getUnit()) - Float.parseFloat(q20) ;
                    addStock(cmp,modelListAddStock.get(j).getMaterial(),modelListAddStock.get(j).getQuantity(),a);
                }
            }

            if (!m21.isEmpty()){
                if (modelListAddStock.get(j).getMaterial().equals(m21)){
                    float a =  Float.parseFloat(modelListAddStock.get(j).getUnit()) - Float.parseFloat(q21) ;
                    addStock(cmp,modelListAddStock.get(j).getMaterial(),modelListAddStock.get(j).getQuantity(),a);
                }
            }

            if (!m22.isEmpty()){
                if (modelListAddStock.get(j).getMaterial().equals(m22)){
                    float a =  Float.parseFloat(modelListAddStock.get(j).getUnit()) - Float.parseFloat(q22) ;
                    addStock(cmp,modelListAddStock.get(j).getMaterial(),modelListAddStock.get(j).getQuantity(),a);
                }
            }

            if (!m23.isEmpty()){
                if (modelListAddStock.get(j).getMaterial().equals(m23)){
                    float a =  Float.parseFloat(modelListAddStock.get(j).getUnit()) - Float.parseFloat(q23) ;
                    addStock(cmp,modelListAddStock.get(j).getMaterial(),modelListAddStock.get(j).getQuantity(),a);
                }
            }

            if (!m24.isEmpty()){
                if (modelListAddStock.get(j).getMaterial().equals(m24)){
                    float a =  Float.parseFloat(modelListAddStock.get(j).getUnit()) - Float.parseFloat(q24) ;
                    addStock(cmp,modelListAddStock.get(j).getMaterial(),modelListAddStock.get(j).getQuantity(),a);
                }
            }

            if (!m25.isEmpty()){
                if (modelListAddStock.get(j).getMaterial().equals(m25)){
                    float a =  Float.parseFloat(modelListAddStock.get(j).getUnit()) - Float.parseFloat(q25) ;
                    addStock(cmp,modelListAddStock.get(j).getMaterial(),modelListAddStock.get(j).getQuantity(),a);
                }
            }

            if (!m26.isEmpty()){
                if (modelListAddStock.get(j).getMaterial().equals(m26)){
                    float a =  Float.parseFloat(modelListAddStock.get(j).getUnit()) - Float.parseFloat(q26) ;
                    addStock(cmp,modelListAddStock.get(j).getMaterial(),modelListAddStock.get(j).getQuantity(),a);
                }
            }

            if (!m27.isEmpty()){
                if (modelListAddStock.get(j).getMaterial().equals(m27)){
                    float a =  Float.parseFloat(modelListAddStock.get(j).getUnit()) - Float.parseFloat(q27) ;
                    addStock(cmp,modelListAddStock.get(j).getMaterial(),modelListAddStock.get(j).getQuantity(),a);
                }
            }

            if (!m28.isEmpty()){
                if (modelListAddStock.get(j).getMaterial().equals(m28)){
                    float a =  Float.parseFloat(modelListAddStock.get(j).getUnit()) - Float.parseFloat(q28) ;
                    addStock(cmp,modelListAddStock.get(j).getMaterial(),modelListAddStock.get(j).getQuantity(),a);
                }
            }

            if (!m29.isEmpty()){
                if (modelListAddStock.get(j).getMaterial().equals(m29)){
                    float a =  Float.parseFloat(modelListAddStock.get(j).getUnit()) - Float.parseFloat(q29) ;
                    addStock(cmp,modelListAddStock.get(j).getMaterial(),modelListAddStock.get(j).getQuantity(),a);
                }
            }

            if (!m30.isEmpty()){
                if (modelListAddStock.get(j).getMaterial().equals(m30)){
                    float a =  Float.parseFloat(modelListAddStock.get(j).getUnit()) - Float.parseFloat(q30) ;
                    addStock(cmp,modelListAddStock.get(j).getMaterial(),modelListAddStock.get(j).getQuantity(),a);
                }
            }


        }

    }

    private void addStock(String cmp, String material, String unit, float quantityF) {

        String quantity = String.valueOf(quantityF);

        Map<String, Object> doc = new HashMap<>();
        doc.put("Id",material);
        doc.put("Material",material);
        doc.put("Unit",unit);
        doc.put("Quantity",quantity);

        fStore.collection(cmp+" AddStock").document(material).set(doc).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                //this will be called when data is added Successfully
                Toast.makeText(getApplicationContext(), "Delete", Toast.LENGTH_SHORT).show();


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //this will be called when data is added Failed
                Toast.makeText(getApplicationContext(), "Failed to add data "+e.getMessage(), Toast.LENGTH_SHORT).show();

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
}