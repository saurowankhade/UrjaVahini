package com.example.electricalmaterial;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
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
    String cmp;
    Pattern pattern = Pattern.compile("[^a-z A-Z0-9]");
    final String WARRING_MESSAGE = "Spacial Symbol does allow";

    //authorization
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String companyEmail;
    String userId;

    Timer time;
    ProgressDialog pd;

    TextView tv;




    AlertDialog.Builder builder;


    TextView kgToNo;

    TextView materialPair;


//    Data form added material for changes material and additional
    List<AdminModel> modelList = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    AddedDataChangesCustomAdapter adminCustomAdapter;


    // Update Data

    Dialog dialog;

    TextInputEditText updatedText;
    MaterialButton addButton,updateButton,deleteButton;
    AutoCompleteTextView whatDoUpdate,whichTextDoYouReplace;

    ArrayAdapter <String> arrayAdapterForWhatDoUpdate;
    String [] whatDoUpdateItem = {"Team Name","Line","Tender","Driver Name","Vehical","Material Receiver Name","Material Name","State","District","Taluka"
    ,"Center","Village","Unit","Work Done"};

    ArrayAdapter<String> adapterUpdate;
    ArrayList<String> spinnerDataListUpdate;
    DatabaseReference databaseReferenceUpdate;
    ValueEventListener listenerUpdate;

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
                 cmp = companyEmail;
                companyEmail = companyEmail.replace("@","");
                companyEmail = companyEmail.replace(".","");

                showData(cmp);

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
//                        updateActualUsedMaterialNameOneField(materialData,cmp,material,id);
//                        updateBalanceUsedMaterialNameOneField(materialData,cmp,material,id);
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.update_add_details, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.update) {
            showDilog();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void showDilog() {
        dialog = new Dialog(AddDetails.this);
        dialog.setContentView(R.layout.update_data_libary);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.back_background);
        dialog.show();

        whatDoUpdate = dialog.findViewById(R.id.whatDoUpdate);
        whichTextDoYouReplace = dialog.findViewById(R.id.selectWhatUpadte);
        updatedText = dialog.findViewById(R.id.updatedText);
        updateButton = dialog.findViewById(R.id.updateButton);
        addButton = dialog.findViewById(R.id.addButton);
        deleteButton = dialog.findViewById(R.id.deleteButton);
        arrayAdapterForWhatDoUpdate = new ArrayAdapter<String>(AddDetails.this,R.layout.support_simple_spinner_dropdown_item, whatDoUpdateItem);
        whatDoUpdate.setAdapter(arrayAdapterForWhatDoUpdate);
        whatDoUpdate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String n = parent.getItemAtPosition(position).toString().trim();

                switch (n){
                    case "Team Name":
                        retrivingDataForAutoCompleteTextView("teamName");
                        break;
                    case "Line":
                    case "Center":
                    case "Village":
                    case "Driver Name":
                    case "Vehical Name":
                    case "Tender":
                    case "State":
                    case "District":
                    case "Taluka":
                    case "Unit":
                        retrivingDataForAutoCompleteTextView(n);
                        break;
                    case "Material Receiver Name":
                        retrivingDataForAutoCompleteTextView("Receiver");
                        break;
                    case "Material Name":
                        retrivingDataForAutoCompleteTextView("Material");
                        break;
                    case "Work Done":
                        retrivingDataForAutoCompleteTextView("WorkDone");
                        break;
                }

            }
        });
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = updatedText.getText().toString().trim();
                String whichData = whichTextDoYouReplace.getText().toString().trim();
                String taskToUpdate = whatDoUpdate.getText().toString().trim();

                Matcher matcher = pattern.matcher(text);
                boolean isStringContainsSpecialCharacter = matcher.find();

                if (text.isEmpty() || whichData.isEmpty() || taskToUpdate.isEmpty()){
                    Toast.makeText(AddDetails.this, "Fill all the information!!", Toast.LENGTH_SHORT).show();
                } else if (isStringContainsSpecialCharacter) {
                    updatedText.setError(WARRING_MESSAGE);
                } else {
                    add(text);
                    delete(whichData);
                    updateDate(text,whichData,taskToUpdate,cmp);


                }

            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = updatedText.getText().toString().trim();
                String whichData = whichTextDoYouReplace.getText().toString().trim();
                String taskToUpdate = whatDoUpdate.getText().toString().trim();

                Matcher matcher = pattern.matcher(text);
                boolean isStringContainsSpecialCharacter = matcher.find();

                if (text.isEmpty() || whichData.isEmpty() || taskToUpdate.isEmpty()){
                    Toast.makeText(AddDetails.this, "Fill all the information!!", Toast.LENGTH_SHORT).show();
                }
                else if (isStringContainsSpecialCharacter) {
                    updatedText.setError(WARRING_MESSAGE);
                }
                else {
                    add(text);
                    if (taskToUpdate.equalsIgnoreCase("Material")){
                        uploadMaterial(text,cmp);
                    }
                    else if (taskToUpdate.equalsIgnoreCase("Work Done")){
                        uploadWorkDone(text,cmp);
                    }

                }

            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String whichData = whichTextDoYouReplace.getText().toString().trim();
                String taskToUpdate = whatDoUpdate.getText().toString().trim();

                startActivity(new Intent(getApplicationContext(),AddDetails.class));
                finish();

                if (whichData.isEmpty() || taskToUpdate.isEmpty()){
                    Toast.makeText(AddDetails.this, "Fill all the information!!", Toast.LENGTH_SHORT).show();
                } else {
                    delete(whichData);
                }

            }
        });
    }
    private void updateDate(String text, String whichData, String taskToUpdate, String cmp) {
        if (taskToUpdate.equals("Team Name")){
            for (int i=0;i< modelList.size();i++){
                if (modelList.get(i).getTeamName().equals(whichData)){
                    updateMaterialNameOneField(text,cmp,taskToUpdate,modelList.get(i).getId());
                }
            }
        }
        else if (taskToUpdate.equals("Line")){
            for (int i=0;i< modelList.size();i++){
                if (modelList.get(i).getLine().equals(whichData)){
                    updateMaterialNameOneField(text,cmp,taskToUpdate,modelList.get(i).getId());
                }
            }
        }
        else if (taskToUpdate.equals("Center")){
            for (int i=0;i< modelList.size();i++){
                if (modelList.get(i).getCenter().equals(whichData)){
                    updateMaterialNameOneField(text,cmp,taskToUpdate,modelList.get(i).getId());
                }
            }
        }
        else if (taskToUpdate.equals("Village")){
            for (int i=0;i< modelList.size();i++){
                if (modelList.get(i).getVillage().equals(whichData)){
                    updateMaterialNameOneField(text,cmp,taskToUpdate,modelList.get(i).getId());
                }
            }
        }
        else if (taskToUpdate.equals("Driver Name")){
            for (int i=0;i< modelList.size();i++){
                if (modelList.get(i).getDriverName().equals(whichData)){
                    updateMaterialNameOneField(text,cmp,taskToUpdate,modelList.get(i).getId());
                }
            }
        }
        else if (taskToUpdate.equals("Vehical Name")){
            for (int i=0;i< modelList.size();i++){
                if (modelList.get(i).getVehicalName().equals(whichData)){
                    updateMaterialNameOneField(text,cmp,taskToUpdate,modelList.get(i).getId());
                }
            }
        }
        else if (taskToUpdate.equals("Tender")){
            for (int i=0;i< modelList.size();i++){
                if (modelList.get(i).getTender().equals(whichData)){
                    updateMaterialNameOneField(text,cmp,taskToUpdate,modelList.get(i).getId());
                }
            }
        }
        else if (taskToUpdate.equals("Material Receiver Name")){
            for (int i=0;i< modelList.size();i++){
                if (modelList.get(i).getMaterialReceiverName().equals(whichData)){
                    updateMaterialNameOneField(text,cmp,taskToUpdate,modelList.get(i).getId());
                }
            }
        }
        else if (taskToUpdate.equals("Material")){
            updateMaterialNameData(text,whichData,cmp);
        }
        else if (taskToUpdate.equals("Unit")){
            updateUnitData(text,whichData,cmp);
        }

    }
    private void delete(String text) {
        spinnerDataListUpdate.clear();
        databaseReferenceUpdate.child(text).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(AddDetails.this, "Deleted", Toast.LENGTH_SHORT).show();
            }
        });
        adapterUpdate.notifyDataSetChanged();
    }
    private void add(String text) {
        databaseReferenceUpdate.child(text).setValue(text)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        spinnerDataListUpdate.clear();
                        retrieveDataUpdate();
                        adapterUpdate.notifyDataSetChanged();
                        Toast.makeText(getApplicationContext(), "inserted", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void retrivingDataForAutoCompleteTextView(String s) {
        databaseReferenceUpdate = FirebaseDatabase.getInstance().getReference(companyEmail + " "+s);
        spinnerDataListUpdate = new ArrayList<>();
        adapterUpdate = new ArrayAdapter<String>(AddDetails.this, R.layout.support_simple_spinner_dropdown_item, spinnerDataListUpdate);
        whichTextDoYouReplace.setAdapter(adapterUpdate);
        retrieveDataUpdate();
    }
    public void retrieveDataUpdate(){
        pd.show();
        listenerUpdate = databaseReferenceUpdate.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot item : snapshot.getChildren()){
                    spinnerDataListUpdate.add(item.getValue().toString());
                }
                adapterUpdate.notifyDataSetChanged();
                pd.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void updateUnitData(String materialData, String updateMaterialName, String cmp) {
        for (int i=0;i<modelList.size();i++){
            if (modelList.get(i).getUnit1().equalsIgnoreCase(updateMaterialName)){
                updateMaterialNameOneField(materialData,cmp,"Unit 1",modelList.get(i).getId());
            }
            else  if (modelList.get(i).getUnit2().equalsIgnoreCase(updateMaterialName)){
                updateMaterialNameOneField(materialData,cmp,"Unit 2",modelList.get(i).getId());
            }
            else  if (modelList.get(i).getUnit3().equalsIgnoreCase(updateMaterialName)){
                updateMaterialNameOneField(materialData,cmp,"Unit 3",modelList.get(i).getId());
            }
            else  if (modelList.get(i).getUnit4().equalsIgnoreCase(updateMaterialName)){
                updateMaterialNameOneField(materialData,cmp,"Unit 4",modelList.get(i).getId());
            }
            else  if (modelList.get(i).getUnit5().equalsIgnoreCase(updateMaterialName)){
                updateMaterialNameOneField(materialData,cmp,"Unit 5",modelList.get(i).getId());
            }
            else  if (modelList.get(i).getUnit6().equalsIgnoreCase(updateMaterialName)){
                updateMaterialNameOneField(materialData,cmp,"Unit 6",modelList.get(i).getId());
            }
            else  if (modelList.get(i).getUnit7().equalsIgnoreCase(updateMaterialName)){
                updateMaterialNameOneField(materialData,cmp,"Unit 7",modelList.get(i).getId());
            }
            else  if (modelList.get(i).getUnit8().equalsIgnoreCase(updateMaterialName)){
                updateMaterialNameOneField(materialData,cmp,"Unit 8",modelList.get(i).getId());
            }
            else  if (modelList.get(i).getUnit9().equalsIgnoreCase(updateMaterialName)){
                updateMaterialNameOneField(materialData,cmp,"Unit 9",modelList.get(i).getId());
            }
            else  if (modelList.get(i).getUnit10().equalsIgnoreCase(updateMaterialName)){
                updateMaterialNameOneField(materialData,cmp,"Unit 10",modelList.get(i).getId());
            }
            else if (modelList.get(i).getUnit11().equalsIgnoreCase(updateMaterialName)){
                updateMaterialNameOneField(materialData,cmp,"Unit 11",modelList.get(i).getId());

            }

            else if (modelList.get(i).getUnit12().equalsIgnoreCase(updateMaterialName)){
                updateMaterialNameOneField(materialData,cmp,"Unit 12",modelList.get(i).getId());

            }

            else if (modelList.get(i).getUnit13().equalsIgnoreCase(updateMaterialName)){
                updateMaterialNameOneField(materialData,cmp,"Unit 13",modelList.get(i).getId());

            }

            else if (modelList.get(i).getUnit14().equalsIgnoreCase(updateMaterialName)){
                updateMaterialNameOneField(materialData,cmp,"Unit 14",modelList.get(i).getId());

            }

            else if (modelList.get(i).getUnit15().equalsIgnoreCase(updateMaterialName)){
                updateMaterialNameOneField(materialData,cmp,"Unit 15",modelList.get(i).getId());

            }

            else if (modelList.get(i).getUnit16().equalsIgnoreCase(updateMaterialName)){
                updateMaterialNameOneField(materialData,cmp,"Unit 16",modelList.get(i).getId());

            }

            else if (modelList.get(i).getUnit17().equalsIgnoreCase(updateMaterialName)){
                updateMaterialNameOneField(materialData,cmp,"Unit 17",modelList.get(i).getId());

            }

            else if (modelList.get(i).getUnit18().equalsIgnoreCase(updateMaterialName)){
                updateMaterialNameOneField(materialData,cmp,"Unit 18",modelList.get(i).getId());

            }

            else if (modelList.get(i).getUnit19().equalsIgnoreCase(updateMaterialName)){
                updateMaterialNameOneField(materialData,cmp,"Unit 19",modelList.get(i).getId());

            }

            else if (modelList.get(i).getUnit20().equalsIgnoreCase(updateMaterialName)){
                updateMaterialNameOneField(materialData,cmp,"Unit 20",modelList.get(i).getId());

            }

            else if (modelList.get(i).getUnit21().equalsIgnoreCase(updateMaterialName)){
                updateMaterialNameOneField(materialData,cmp,"Unit 21",modelList.get(i).getId());

            }

            else if (modelList.get(i).getUnit22().equalsIgnoreCase(updateMaterialName)){
                updateMaterialNameOneField(materialData,cmp,"Unit 22",modelList.get(i).getId());

            }

            else if (modelList.get(i).getUnit23().equalsIgnoreCase(updateMaterialName)){
                updateMaterialNameOneField(materialData,cmp,"Unit 23",modelList.get(i).getId());

            }

            else if (modelList.get(i).getUnit24().equalsIgnoreCase(updateMaterialName)){
                updateMaterialNameOneField(materialData,cmp,"Unit 24",modelList.get(i).getId());

            }

            else if (modelList.get(i).getUnit25().equalsIgnoreCase(updateMaterialName)){
                updateMaterialNameOneField(materialData,cmp,"Unit 25",modelList.get(i).getId());

            }

            else if (modelList.get(i).getUnit26().equalsIgnoreCase(updateMaterialName)){
                updateMaterialNameOneField(materialData,cmp,"Unit 26",modelList.get(i).getId());

            }

            else if (modelList.get(i).getUnit27().equalsIgnoreCase(updateMaterialName)){
                updateMaterialNameOneField(materialData,cmp,"Unit 27",modelList.get(i).getId());

            }

            else if (modelList.get(i).getUnit28().equalsIgnoreCase(updateMaterialName)){
                updateMaterialNameOneField(materialData,cmp,"Unit 28",modelList.get(i).getId());

            }
            else if (modelList.get(i).getUnit29().equalsIgnoreCase(updateMaterialName)){
                updateMaterialNameOneField(materialData,cmp,"Unit 29",modelList.get(i).getId());

            }
            else if (modelList.get(i).getUnit30().equalsIgnoreCase(updateMaterialName)){
                updateMaterialNameOneField(materialData,cmp,"Unit 30",modelList.get(i).getId());

            }


        }
    }

}