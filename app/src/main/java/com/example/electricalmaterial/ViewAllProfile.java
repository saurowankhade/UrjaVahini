package com.example.electricalmaterial;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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

public class ViewAllProfile extends AppCompatActivity  {

    public static final int REQUEST_CALL = 1;
    List<ViewAllProfileModel> modelList = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    ViewAllProfileAdapter adapter;
    SwipeRefreshLayout refreshLayout;

    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String companyEmail;
    String userId;
    String cmp;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)   {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_profile);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        modelList.clear();
        refreshLayout = findViewById(R.id.refresh);


        fStore = FirebaseFirestore.getInstance();

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = mAuth.getCurrentUser().getUid();




        ActionBar bar = getSupportActionBar();
        assert bar != null;
        bar.setTitle("View All Profile");


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

                refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        showData(cmp);
                        refreshLayout.setRefreshing(false);
                    }
                });


//                return false;
            }

        });
    }

    private void showData(String cmp)   {
        fStore.collection("Users")
                .whereEqualTo("companyEmail",cmp)
                .orderBy("fullName", Query.Direction.ASCENDING).get().addOnSuccessListener(this, new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                modelList.clear();
                refreshLayout.setRefreshing(false);
                for (DocumentSnapshot doc : queryDocumentSnapshots){
                    ViewAllProfileModel model = new ViewAllProfileModel(
                            doc.getString("Id"),
                            doc.getString("companyEmail"),
                            doc.getString("companyName"),
                            doc.getString("currentAddress"),
                            doc.getString("permanentAddress"),
                            doc.getString("profile"),
                            doc.getString("mobileNumber"),
                            doc.getString("fullName"),
                            doc.getString("email"),
                            doc.getString("education")
                    );
                    modelList.add(model);
                }
                adapter = new ViewAllProfileAdapter(ViewAllProfile.this, modelList);
                recyclerView.setAdapter(adapter);
            }
        }).addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ViewAllProfile.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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
                cmp = companyEmail;
                companyEmail = companyEmail.replace("@", "");
                companyEmail = companyEmail.replace(".", "");

                delete(cmp,position);


//                return false;
            }

        });

    }

    private void delete(String cmp, int position) {

        fStore.collection("Users").document(modelList.get(position).getId())
                .delete()
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(ViewAllProfile.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                        showData(cmp);

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

}