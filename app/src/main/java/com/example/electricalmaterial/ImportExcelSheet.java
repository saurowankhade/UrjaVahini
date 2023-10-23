package com.example.electricalmaterial;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ImportExcelSheet extends AppCompatActivity {

    private static final int REQUEST_CODE_PICK_EXCEL = 101;
    private static final int REQUEST_CODE_PERMISSION = 102;

    private File selectedExcelFile;
    private ArrayList<String> excelData = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import_excel_sheet);

         Button selectExcelButton = findViewById(R.id.select_excel_button);
        Button readExcelButton = findViewById(R.id.read_excel_button);

        selectExcelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAndRequestPermission();
            }
        });

        readExcelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedExcelFile != null) {
                    readExcelFile(selectedExcelFile);
                } else {
                    Toast.makeText(ImportExcelSheet.this, "Please select an Excel file first.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void checkAndRequestPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_PERMISSION);
        } else {
            pickExcelFile();
        }
    }

    private void pickExcelFile() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//        intent.setType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        intent.setType("application/vnd.ms-excel");
        startActivityForResult(intent, REQUEST_CODE_PICK_EXCEL);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_PICK_EXCEL && resultCode == RESULT_OK && data != null) {
            Uri excelUri = data.getData();
            if (excelUri != null) {
                selectedExcelFile = copyExcelFileToInternalStorage(excelUri);
                if (selectedExcelFile != null) {
                    Toast.makeText(this, "Excel file selected and copied to internal storage.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Failed to copy Excel file to internal storage.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private File copyExcelFileToInternalStorage(Uri excelUri) {
        try {
            String excelFileName = "user_excel.xlsx"; // You can give any desired name
            File internalStorageDir = getFilesDir();
            File destinationFile = new File(internalStorageDir, excelFileName);

            FileInputStream inputStream = (FileInputStream) getContentResolver().openInputStream(excelUri);
            FileOutputStream outputStream = new FileOutputStream(destinationFile);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            outputStream.close();
            inputStream.close();

            return destinationFile;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void readExcelFile(File excelFile) {
        try {
            Workbook workbook = Workbook.getWorkbook(excelFile);
            Sheet sheet = workbook.getSheet(0); // Assuming the first sheet in the Excel file

            int numRows = sheet.getRows();
            int numCols = sheet.getColumns();

            excelData.clear(); // Clear the previous data

            for (int i = 0; i < numRows; i++) {
                StringBuilder rowData = new StringBuilder();
                for (int j = 0; j < numCols; j++) {
                    Cell cell = sheet.getCell(j, i);
                    rowData.append(cell.getContents());
                }

                excelData.add(rowData.toString());
            }

            workbook.close();

            // Now, excelData ArrayList contains the Excel data
            // You can do whatever you want with this data, e.g., display it, process it, etc.
            // For now, let's display it as a Toast message
            for (int i = 1; i < excelData.size(); i++) {
                Toast.makeText(this, "Consumer : "+i+" is : "+excelData.get(i), Toast.LENGTH_SHORT).show();
            }
        } catch (IOException | BiffException e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to read Excel file.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                pickExcelFile();
            } else {
                Toast.makeText(this, "Permission denied. You need to grant permission to read external storage.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}