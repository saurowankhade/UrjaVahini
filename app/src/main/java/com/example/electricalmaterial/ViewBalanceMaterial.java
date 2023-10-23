package com.example.electricalmaterial;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
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

public class ViewBalanceMaterial extends AppCompatActivity {
    List<BalanceMaterialModel> modelList = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String companyEmail;
    String userId;

    ProgressDialog pd,pd1;

    androidx.appcompat.widget.SearchView searchView;

    BalanceMaterialCustomAdapter adapter;
    String cmp;

    Button excelSheet;

    SwipeRefreshLayout refreshLayout;



    //Material
    List<MaterialModel> list = new ArrayList<>();
    RecyclerView recycler;
    RecyclerView.LayoutManager layout;
    MaterialCustomAdapter materialCustomAdapter;


    //Total Material Taken
    List<AddTotalMaterialTakenModel> modelListTMT = new ArrayList<>();
    RecyclerView recyclerViewTMT;
    RecyclerView.LayoutManager layoutManagerTMT;
    ViewBalanceTotalMaterialTakenAdapter adapterStockTMT;

    String [] arr;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_balance_material);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        fStore = FirebaseFirestore.getInstance();
        pd = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = mAuth.getCurrentUser().getUid();

        pd1 = ProgressDialog.show(this,"Loading...","Please Wait",false,false);



        ActionBar bar = getSupportActionBar();
        assert bar != null;
        bar.setTitle("ACTUAL MATERIAL");

        refreshLayout = findViewById(R.id.refresh);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        modelList.clear();



        //Stock Material
        recyclerViewTMT = findViewById(R.id.recyclerMaterial);
        recyclerViewTMT.setHasFixedSize(true);
        layoutManagerTMT = new LinearLayoutManager(this);
        recyclerViewTMT.setLayoutManager(layoutManagerTMT);
        modelListTMT.clear();





        recycler = findViewById(R.id.recycler);
        recycler.setHasFixedSize(true);
        layout = new LinearLayoutManager(this);
        recycler.setLayoutManager(layout);
        list.clear();
        showMaterial();






        searchView = findViewById(R.id.searchView);

        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                ArrayList<BalanceMaterialModel> models = new ArrayList<>();
                for (BalanceMaterialModel model : modelList){
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

                BalanceMaterialCustomAdapter adminCustomAdapter = new BalanceMaterialCustomAdapter(ViewBalanceMaterial.this,models);
                recyclerView.setAdapter(adminCustomAdapter);

                return true;
            }
        });


       // excelSheet

        excelSheet = findViewById(R.id.excelSheet);




        DocumentReference documentReference = fStore.collection("Users")
                .document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                companyEmail = value.getString("companyEmail");
                cmp = companyEmail;
                companyEmail = companyEmail.replace("@", "");
                companyEmail = companyEmail.replace(".", "");


                refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        showData(cmp);
                        refreshLayout.setRefreshing(false);
                    }
                });

                excelSheet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                                checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                        }
                        else {
                            createXlFile();
                            showTotalMaterialTaken(cmp);




                        }
                    }
                });


                showData(cmp);


//                return false;
            }
        });

    }

    private void showData(String cmp) {
        fStore.collection(cmp+" BalanceMaterial").orderBy("Date", Query.Direction.DESCENDING).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        modelList.clear();
                        pd1.dismiss();
                        refreshLayout.setRefreshing(false);
                        for (DocumentSnapshot doc : queryDocumentSnapshots){
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
                        adapter = new BalanceMaterialCustomAdapter(ViewBalanceMaterial.this,modelList);
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

    private void showTotalMaterialTaken(String cmp) {

        for (int i=0;i<modelList.size();i++){

            fStore.collection(cmp+" BalanceMaterialOnSite")
                    .document(modelList.get(i).getId()).collection("BalanceMaterialOnSite")
                    .orderBy("Material", Query.Direction.ASCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            modelListTMT.clear();
                            for (DocumentSnapshot doc : task.getResult()){
                                AddTotalMaterialTakenModel model = new AddTotalMaterialTakenModel(
                                        doc.getString("Id"),
                                        doc.getString("Material"),
                                        doc.getString("Unit"),
                                        doc.getString("Quantity")
                                );
                                modelListTMT.add(model);
                            }
                            adapterStockTMT = new ViewBalanceTotalMaterialTakenAdapter(ViewBalanceMaterial.this,modelListTMT);
                            recyclerViewTMT.setAdapter(adapterStockTMT);
                        }})
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ViewBalanceMaterial.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }


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
                                materialCustomAdapter = new MaterialCustomAdapter(ViewBalanceMaterial.this, list);
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
//                return false;
            }

        });
    }

    public void deleteData(int position) {
        pd1.setTitle("Deleting Data...");
        pd1.show();

        DocumentReference documentReference = fStore.collection("Users")
                .document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                companyEmail = value.getString("companyEmail");
                String cmp = companyEmail;
                String a = modelList.get(position).getId();

                fStore.collection(cmp + " AddActualData").document(modelList.get(position).getId())
                        .delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                pd1.dismiss();
                                Toast.makeText(ViewBalanceMaterial.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                                showData(cmp);

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                pd1.dismiss();
                                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

//                return false;
            }
        });

    }

    private void createXlFile() {


        Workbook wb = new HSSFWorkbook();
        Cell cell = null;
        Sheet sheet;
        sheet = wb.createSheet("Balance Material List");

        excel();
        excel1(cell, sheet);
//
        excel2(wb, cell, sheet);

        String folderName = "UrjaVahini";
        String fileName = "Balance Material List" + System.currentTimeMillis() + ".xls";
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

        arr = new String[list.size()];

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
            int n = j<256 ? j : 0;
            cell = row.createCell(n);
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


            for (int j = 0; j < arr.length; j++) {

                int k = 11 + j;

                for (int a = 0; a < modelListTMT.size(); a++) {
                    if (arr[j].equals(modelListTMT.get(a).getMaterial())) {
                        cell = row1.createCell(k);
                        cell.setCellValue((modelListTMT.get(a).getQuantity()));
                    }
                }

                for (int b = 0; b < arr.length + 10; b++) {
                    sheet.setColumnWidth(i, (20 * 200));
                }

            }
        }

    }



}