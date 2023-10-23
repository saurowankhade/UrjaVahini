package com.example.electricalmaterial;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.electricalmaterial.add_material.CustomListAdapter;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class ImportDataFromExcel extends AppCompatActivity {


    private AutoCompleteTextView actvMaterial;
    private AutoCompleteTextView actvUnit;
    private TextInputEditText tietQuantity;
    private Button bAdd;
    private ListView lvListView;
    private ArrayList<AddTotalMaterialTakenModel> alData = new ArrayList<>();
    private CustomListAdapter adapter; // Custom adapter for your data

    private int positionOfList = -1;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import_data_from_excel);
        actvMaterial = findViewById(R.id.materialWork1);
        actvUnit = findViewById(R.id.unit1);
        tietQuantity = findViewById(R.id.quantity1);
        bAdd = findViewById(R.id.add);
        lvListView = findViewById(R.id.listView);

        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String material = actvMaterial.getText().toString().trim();
                String quantity = tietQuantity.getText().toString().trim();
                String unit = actvUnit.getText().toString().trim();

                if (material.isEmpty()||quantity.isEmpty()||unit.isEmpty()){
                    Toast.makeText(ImportDataFromExcel.this, "Add all details", Toast.LENGTH_SHORT).show();
                } else{
                    if (positionOfList < 0){
                        alData.add(new AddTotalMaterialTakenModel(material,material,unit,quantity));
                    } else {
                        alData.set(positionOfList,new AddTotalMaterialTakenModel(material,material,unit,quantity));
                        positionOfList = -1;
                    }
                    adapter.notifyDataSetChanged();

                }

            }
        });

        adapter = new CustomListAdapter(this, alData);
        lvListView.setAdapter(adapter);

        // Handle item clicks in the ListView
        lvListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item from the adapter
                AddTotalMaterialTakenModel selectedItem = adapter.getItem(position);

                // Update the fields with the data from the selected item

                positionOfList = position;
                actvMaterial.setText(selectedItem.getMaterial());
                tietQuantity.setText(selectedItem.getQuantity());
                actvUnit.setText(selectedItem.getUnit());
            }
        });

        lvListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showDialogBox(position);
                return true;
            }
        });


    }

    private void showDialogBox(int position) {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);
        builder.setIcon(R.drawable.delete_icon)
                .setTitle("Do you want to delete ?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (position >= 0 && position < alData.size()) {
                            alData.remove(position);
                            adapter.notifyDataSetChanged(); // Notify the adapter of data change
                        }
                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).create().show();
    }
}