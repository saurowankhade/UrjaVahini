package com.example.electricalmaterial.settingsprinner;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CustomSpinner {
    //private final GetUserInfromation getUserInfromation = new GetUserInfromation();

    private Context context;

    private String companyEmail;
    private String type;



    //team name
    private ArrayAdapter<String> adapter;
    private ArrayList<String> spinnerDataList;
    static DatabaseReference databaseReference;
    private ValueEventListener listener;



    public CustomSpinner(Context context,String companyEmail,String type) {
        this.context = context;
        this.companyEmail = companyEmail;
        this.type = type;
        declaringValues();
    }


    private void declaringValues(){
        databaseReference = FirebaseDatabase.getInstance().getReference(companyEmail + " "+type);
        spinnerDataList = new ArrayList<>();
        adapter = new ArrayAdapter<String>(context,  androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, spinnerDataList);
    }

    public ArrayAdapter<String> getAdapter() {
        return adapter;
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
