package com.example.electricalmaterial;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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
import java.util.List;

public class AddStock extends AppCompatActivity {

    //Authorization
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String companyEmail;
    String userId;

    ProgressDialog pd;

    //Stock Material
    List<AddStockModel> modelList = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    AddStockAdapter adapter;

    SwipeRefreshLayout refresh;

    FloatingActionButton addBtn;

    AutoCompleteTextView material;
    ArrayAdapter<String> adapterMaterial;
    ArrayList<String> spinnerDataListMaterial;
    DatabaseReference databaseReferenceMaterial;
    ValueEventListener listenerMaterial;





    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stock);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        //Auth
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = mAuth.getCurrentUser().getUid();

        refresh = findViewById(R.id.refresh);

        pd = ProgressDialog.show(this,"Loading...","Please Wait",false,false);

        //Stock Material
        recyclerView = findViewById(R.id.recyclerViewAS);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        modelList.clear();

        ActionBar bar = getSupportActionBar();
        assert bar != null;
        bar.setTitle("CURRENT STOCK");


        addBtn = findViewById(R.id.addBtn);




        material = findViewById(R.id.material);










        //Stock Material
        DocumentReference documentReference = fStore.collection("Users")
                .document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                companyEmail = value.getString("companyEmail");
                String cmp = companyEmail;
                companyEmail = companyEmail.replace("@","");
                companyEmail = companyEmail.replace(".","");

                String profile = value.getString("profile");


                addBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (profile.equals("ADMIN")){
                            AlertDialog.Builder builder = new AlertDialog.Builder(AddStock.this);
                            String[] options = {"Export to Excel", "Add"};
                            builder.setTitle("Select any one ");
                            builder.setItems(options, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {


                                    if (which == 0) {
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                                                checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                                            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                                        } else {
                                            //your cod
                                            createXlFile();
                                        }

                                    } else {
                                        startActivity(new Intent(getApplicationContext(), Stock.class));
                                        finish();
                                    }
                                }
                            }).create().show();
                        }

                        else {
                            startActivity(new Intent(getApplicationContext(),Stock.class));
                            finish();
                        }


                    }
                });

                //Material
                databaseReferenceMaterial = FirebaseDatabase.getInstance().getReference(companyEmail+" Material");
                spinnerDataListMaterial = new ArrayList<>();
                adapterMaterial = new ArrayAdapter<String>(AddStock.this,R.layout.support_simple_spinner_dropdown_item,spinnerDataListMaterial);
                material.setAdapter(adapterMaterial);
                retrieveDataMaterial();
                material.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String n = parent.getItemAtPosition(position).toString().trim();
                        showMaterial(cmp,n);
                    }
                });

                showData(cmp);
                refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        showData(cmp);

                    }
                });



//



            }
        });

    }

    private void showMaterial(String cmp, String n) {
        pd.setTitle("Searching...");
        pd.show();
        fStore.collection(cmp + " AddStock").whereEqualTo("Material", n)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
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
                        adapter = new AddStockAdapter(AddStock.this,modelList);
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            createXlFile();
        } else {
            Toast.makeText(getApplicationContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
        }
    }
    private void createXlFile() {

        Workbook wb = new HSSFWorkbook();
        Cell cell = null;
        Sheet sheet;
        sheet = wb.createSheet("Added Stock Material");

        excel1(cell, sheet);

        String folderName = "Electrical Contractors Material WorkBook";
        String fileName = "Added Stock Material" + System.currentTimeMillis() + ".xls";
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
    private void excel1(Cell cell, Sheet sheet) {

        Row row = sheet.createRow(0);

        cell = row.createCell(0);
        cell.setCellValue("Material");

        cell = row.createCell(1);
        cell.setCellValue("Unit");

        cell = row.createCell(2);
        cell.setCellValue("Quantity");

        sheet.setColumnWidth(0, (30 * 200));
        sheet.setColumnWidth(1, (30 * 200));
        sheet.setColumnWidth(2, (30 * 200));
        for (int i = 0; i < modelList.size(); i++) {

            Row row1 = sheet.createRow(i + 1);
            cell = row1.createCell(0);
            cell.setCellValue(modelList.get(i).getMaterial());

            cell = row1.createCell(1);
            cell.setCellValue((modelList.get(i).getQuantity()));

            cell = row1.createCell(2);
            cell.setCellValue((modelList.get(i).getUnit()));

            sheet.setColumnWidth(0, (20 * 200));
            sheet.setColumnWidth(1, (30 * 200));
            sheet.setColumnWidth(2, (30 * 200));
        }
    }
    private void showData(String cmp) {
        fStore.collection(cmp+" AddStock").orderBy("Quantity", Query.Direction.ASCENDING).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        modelList.clear();
                        pd.dismiss();
                        refresh.setRefreshing(false);
                        for (DocumentSnapshot doc :queryDocumentSnapshots){
                            AddStockModel model = new AddStockModel(
                                    doc.getString("Id"),
                                    doc.getString("Material"),
                                    doc.getString("Unit"),
                                    doc.getString("Quantity")
                            );
                            modelList.add(model);
                        }
                        adapter = new AddStockAdapter(AddStock.this,modelList);
                        recyclerView.setAdapter(adapter);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                refresh.setRefreshing(false);
                Toast.makeText(AddStock.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
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

}