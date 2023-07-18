package com.example.electricalmaterial;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ViewBalanceData extends AppCompatActivity {

    //authorization
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String companyEmail;
    String userId;
    String cmp;


    TextView date,teamName,line,tender,driverName,vehicalName,consumerName,siteName,materialReceiverName,center,village;
    String id,dateS,teamNameS,lineS,tenderS,driverNameS,vehicalNameS,consumerNameS,siteNameS,materialReceiverNameS,centerS,villageS;


    //Total Material Taken
    List<AddTotalMaterialTakenModel> modelListTMT = new ArrayList<>();
    RecyclerView recyclerViewTMT;
    RecyclerView.LayoutManager layoutManagerTMT;
    ViewBalanceTotalMaterialTakenAdapter adapterStockTMT;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_balance_data);

        //Auth
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = mAuth.getCurrentUser().getUid();


        date = findViewById(R.id.dateTV);
        teamName = findViewById(R.id.teamNameTV);
        line = findViewById(R.id.lineTV);
        tender = findViewById(R.id.tenderTV);
        driverName = findViewById(R.id.driverNameTV);
        vehicalName = findViewById(R.id.vehicalNameTV);
        consumerName = findViewById(R.id.consumerNameTV);
        siteName = findViewById(R.id.siteTV);
        materialReceiverName = findViewById(R.id.materialReceiverNameTV);
        center = findViewById(R.id.centerTV);
        village = findViewById(R.id.villageTV);


        //Stock Material
        recyclerViewTMT = findViewById(R.id.recyclerATMT);
        recyclerViewTMT.setHasFixedSize(true);
        layoutManagerTMT = new LinearLayoutManager(ViewBalanceData.this);
        recyclerViewTMT.setLayoutManager(layoutManagerTMT);
        modelListTMT.clear();

        ActionBar bar = getSupportActionBar();
        assert bar != null;
        bar.hide();

        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            id = bundle.getString("Id");
            dateS = bundle.getString("Date");
            teamNameS = bundle.getString("TeamName");
            lineS = bundle.getString("Line");
            tenderS = bundle.getString("Tender");
            driverNameS = bundle.getString("Driver Name");
            vehicalNameS = bundle.getString("Vehical Name");
            consumerNameS = bundle.getString("Consumer Name");
            siteNameS = bundle.getString("Site");
            materialReceiverNameS = bundle.getString("Material Receiver Name");
            centerS = bundle.getString("Center");
            villageS = bundle.getString("Village");

        }

        date.setText(dateS);
        teamName.setText(teamNameS);
        line.setText(lineS);
        tender.setText(tenderS);
        driverName.setText(driverNameS);
        vehicalName.setText(vehicalNameS);
        consumerName.setText(consumerNameS);
        siteName.setText(siteNameS);
        materialReceiverName.setText(materialReceiverNameS);
        center.setText(centerS);
        village.setText(villageS);



        DocumentReference documentReference = fStore.collection("Users")
                .document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {


                companyEmail = value.getString("companyEmail");
                cmp = companyEmail;
                companyEmail = companyEmail.replace("@", "");
                companyEmail = companyEmail.replace(".", "");

                showTotalMaterialTaken(cmp);
            }

        });


    }

    private void showTotalMaterialTaken(String cmp) {
        fStore.collection(cmp+" BalanceMaterialOnSite")
                .document(id).collection("BalanceMaterialOnSite")
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
                adapterStockTMT = new ViewBalanceTotalMaterialTakenAdapter(ViewBalanceData.this,modelListTMT);
                recyclerViewTMT.setAdapter(adapterStockTMT);
            }}).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ViewBalanceData.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}