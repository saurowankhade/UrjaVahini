package com.example.electricalmaterial;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
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
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class StockDataLibary extends AppCompatActivity {

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

    //authorization
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String companyEmail;
    String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_data_libary);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        ActionBar bar = getSupportActionBar();
        assert bar != null;
        bar.setTitle("STOCK DATA LIBRARY");


        //Auth
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = mAuth.getCurrentUser().getUid();


        //Team Name
        team = findViewById(R.id.supplierLL);
        teamNameLL = findViewById(R.id.addSupplierLL);
        teamName = findViewById(R.id.supplierName);
        addTeamName = findViewById(R.id.addSupplier);
        teamNameETAdd = findViewById(R.id.supplierETAdd);
        addTeamNameBtn = findViewById(R.id.supplierBtn);
        viewTeamName = findViewById(R.id.viewSupplier);
        deleteTeamName = findViewById(R.id.deleteSupplier);

        DocumentReference documentReference = fStore.collection("Users")
                .document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                companyEmail = value.getString("companyEmail");
                String cmp = companyEmail;
                companyEmail = companyEmail.replace("@", "");
                companyEmail = companyEmail.replace(".", "");


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
                databaseReference = FirebaseDatabase.getInstance().getReference(companyEmail + " supplierName");
                spinnerDataList = new ArrayList<>();
                adapter = new ArrayAdapter<String>(StockDataLibary.this, R.layout.support_simple_spinner_dropdown_item, spinnerDataList);
                teamName.setAdapter(adapter);
                retrieveData();
                addTeamNameBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textData = teamNameETAdd.getText().toString().trim();
                        if (textData.isEmpty()) {
                            teamNameETAdd.setError("Required");
                            teamNameETAdd.requestFocus();
                        } else {
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

//                return false;
            }
        });


    }



    public void r(String cmp){
        fStore.collection(cmp+" Stock").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (DocumentSnapshot i : task.getResult()){
                    spinnerDataList.add(i.get("").toString());
                }
                adapter.notifyDataSetChanged();
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
}