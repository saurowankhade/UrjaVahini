package com.example.electricalmaterial;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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

public class ViewTotalMaterialTaken extends AppCompatActivity {

    private static final int REQUEST_WRITE_EXTERNAL_STORAGE = 1;
    List<BalanceMaterialModel> modelList = new ArrayList<>();


    ArrayList<AddTotalMaterialTakenModel>[] materialModelList;


    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String companyEmail;
    String userId;

    ProgressDialog pd, pd1;

    androidx.appcompat.widget.SearchView searchView;


    TotalMaterialAdapter adapter;
    String cmp;

    Button excelSheet, checkData;

    SwipeRefreshLayout refreshLayout;


    List<String> materialList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_total_material_taken);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        fStore = FirebaseFirestore.getInstance();
        pd = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = mAuth.getCurrentUser().getUid();

        pd1 = ProgressDialog.show(this, "Loading...", "Please Wait", false, false);

        ActionBar bar = getSupportActionBar();
        assert bar != null;
        bar.setTitle("TOTAL MATERIAL TAKEN");


        refreshLayout = findViewById(R.id.refresh);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        modelList.clear();

        searchView = findViewById(R.id.searchView);

        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                ArrayList<BalanceMaterialModel> models = new ArrayList<>();
                for (BalanceMaterialModel model : modelList) {
                    if (model.getDate().toLowerCase().contains(s.toLowerCase())) {
                        models.add(model);
                    } else if (model.getTeamName().toLowerCase().contains(s.toLowerCase())) {
                        models.add(model);
                    } else if (model.getTender().toLowerCase().contains(s.toLowerCase())) {
                        models.add(model);
                    } else if (model.getConsumerName().toLowerCase().contains(s.toLowerCase())) {
                        models.add(model);
                    } else if (model.getCenter().toLowerCase().contains(s.toLowerCase())) {
                        models.add(model);
                    } else if (model.getSite().toLowerCase().contains(s.toLowerCase())) {
                        models.add(model);
                    }
                }

                TotalMaterialAdapter adminCustomAdapter = new TotalMaterialAdapter(ViewTotalMaterialTaken.this, models);
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

                refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        showData(cmp);
                        refreshLayout.setRefreshing(false);
                    }
                });

                showData(cmp);


                excelSheet = findViewById(R.id.excel);
                checkData = findViewById(R.id.CheckData);

                checkData.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(ViewTotalMaterialTaken.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_EXTERNAL_STORAGE);
                        } else {
                            createXlFile();
                        }
                    }
                });

                excelSheet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        materialModelList = new ArrayList[modelList.size()];
                        for (int i = 0; i < modelList.size(); i++) {
                            showMaterial(cmp, modelList.get(i).getConsumerName(), modelList.get(i).getTeamName(), i);

                        }
                    }
                });

            }
        });


    }

    private void showData(String cmp) {
        fStore.collection(cmp + " TotalMaterialTaken").orderBy("Date", Query.Direction.DESCENDING).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        modelList.clear();
                        pd1.dismiss();
                        refreshLayout.setRefreshing(false);
                        for (DocumentSnapshot doc : queryDocumentSnapshots) {
                            BalanceMaterialModel model = new BalanceMaterialModel(
                                    doc.getString("id"),
                                    doc.getString("Date"),
                                    doc.getString("Team Name"),
                                    doc.getString("Line"),
                                    doc.getString("Tender"),
                                    doc.getString("Driver Name"),
                                    doc.getString("Vehical Name"),
                                    doc.getString("Consumer Name"),
                                    doc.getString("Site Name"),
                                    doc.getString("Material Receiver Name"),
                                    doc.getString("Center"),
                                    doc.getString("Village")
                            );
                            modelList.add(model);
                        }
                        adapter = new TotalMaterialAdapter(ViewTotalMaterialTaken.this, modelList);
                        recyclerView.setAdapter(adapter);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(getApplicationContext(), "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void showMaterial(String cmp, String consumerName, String teamName, int position) {
        pd1.show();
        pd1.setTitle("Position : " + position);
        materialModelList[position] = new ArrayList<>();
        fStore.collection(cmp + " TotalMaterialTaken")
                .document(consumerName + " " + teamName)

                .collection("MaterialDetails")
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

                            pd1.dismiss();
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ViewTotalMaterialTaken.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
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
                    String data = snapshot.getValue(String.class); // Replace 'String' with the appropriate data type
                    materialList.add(data); // Add the data to the ArrayList
                    // Toast.makeText(ViewTotalMaterialTaken.this, "Data : " + data, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(ViewTotalMaterialTaken.this, "Error : " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

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
        cell.setCellValue("Site Name");

        cell = row.createCell(8);
        cell.setCellValue("Material Receiver Name");

        cell = row.createCell(9);
        cell.setCellValue("Center");

        cell = row.createCell(10);
        cell.setCellValue("Village");

        for (int i = 0; i < materialList.size(); i++) {
            cell = row.createCell(11 + i);
            cell.setCellValue(materialList.get(i));
            sheet.setColumnWidth(i + 10, (30 * 200));
        }


    }

    private void settingMainData(Workbook wb, Cell cell, Sheet sheet) {
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


            for (int ml = 0; ml < materialList.size(); ml++) {
                for (int subMML = 0; subMML < materialModelList[i].size(); subMML++) {
                    if (materialList.get(ml).trim().equalsIgnoreCase(materialModelList[i].get(subMML).getMaterial().trim())) {
                        int count = ml + 11;
                        cell = row1.createCell(count);
                        cell.setCellValue(materialModelList[i].get(subMML).getQuantity().trim());
                    }
                }
            }

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_WRITE_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                createXlFile();
            } else {
                Toast.makeText(this, "Give the permission", Toast.LENGTH_SHORT).show();
            }
        }
    }

}