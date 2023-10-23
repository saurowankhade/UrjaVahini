package com.example.electricalmaterial.add_material;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.electricalmaterial.R;
import com.example.electricalmaterial.getinfromation.GetUserInfromation;
import com.example.electricalmaterial.settingsprinner.CustomSpinner;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class SiteInformation extends Fragment {
    private final GetUserInfromation userInfromation = new GetUserInfromation(); // getting user data

    private final String setError = "Required!";

    protected CustomSpinner csConsumerName, csState, csDistrict, csTaluka, csCenter, csVillage;

    private AutoCompleteTextView actvConsumerName;
    private AutoCompleteTextView actvState;
    private AutoCompleteTextView actvDistrict;
    private AutoCompleteTextView actvTaluka;
    private AutoCompleteTextView actvCenter;
    private AutoCompleteTextView actvVillage;
    private TextInputEditText tietSiteName;
    private int count = 0;

    private ArrayAdapter<String> adapterUpdate;
    private ArrayList<String> spinnerDataListUpdate;
    private DatabaseReference databaseReferenceUpdate;
    private ValueEventListener listenerUpdate;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_site_information, container, false);
        // finding ids
        actvConsumerName = rootView.findViewById(R.id.consumerName);
        actvState = rootView.findViewById(R.id.state);
        actvDistrict = rootView.findViewById(R.id.district);
        actvTaluka = rootView.findViewById(R.id.taluka);
        actvCenter = rootView.findViewById(R.id.center);
        actvVillage = rootView.findViewById(R.id.village);
        tietSiteName = rootView.findViewById(R.id.siteName);

        // while updating the data
        if ( ((AddMaterial) requireActivity()).getsConsumerName() !=null ){
            // setting the data for update
            actvConsumerName.setText(((AddMaterial) requireActivity()).getsConsumerName());
            actvState.setText(((AddMaterial) requireActivity()).getsState());
            actvDistrict.setText(((AddMaterial) requireActivity()).getsDistrict());
            actvTaluka.setText(((AddMaterial) requireActivity()).getsTaluka());
            actvCenter.setText(((AddMaterial) requireActivity()).getsCenter());
            actvVillage.setText(((AddMaterial) requireActivity()).getsVillage());
            tietSiteName.setText(((AddMaterial) requireActivity()).getsSiteName());
        }

        // declaring auto complete text view
        actvConsumerName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count==0){
                    showSprineer(csConsumerName,"consumerName",actvConsumerName); // consumerName
                    retrivingDataForAutoCompleteTextView(userInfromation.getCompanyEmailWithOutExtension());
                    showSprineer(csState,"State",actvState); // state
                    showSprineer(csDistrict,"District",actvDistrict); // district
                    showSprineer(csTaluka,"Taluka",actvTaluka); // taluka
                    showSprineer(csCenter,"Center",actvCenter); // center
                    showSprineer(csVillage,"Village",actvVillage); // village
                } else {
                    showSprineer(csConsumerName,"consumerName",actvConsumerName);
                }
                count++;
            }
        });




        // when click on back
        rootView.findViewById(R.id.pre).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ((AddMaterial) requireActivity()).goToBacisInformation();
            }
        });

        // when click on next
        rootView.findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String consumerName = actvConsumerName.getText().toString().trim();
                String state = actvState.getText().toString().trim();
                String district = actvDistrict.getText().toString().trim();
                String taluka = actvTaluka.getText().toString().trim();
                String center = actvCenter.getText().toString().trim();
                String village = actvVillage.getText().toString().trim();
                String siteName = tietSiteName.getText().toString().trim();
                if (consumerName.isEmpty()){
                    actvConsumerName.setError(setError);
                } else if (state.isEmpty()){
                    actvState.setError(setError);
                } else if (district.isEmpty()){
                    actvDistrict.setError(setError);
                } else if (taluka.isEmpty()){
                    actvTaluka.setError(setError);
                } else if (center.isEmpty()){
                    actvCenter.setError(setError);
                } else if (village.isEmpty()) {
                    actvVillage.setError(setError);
                } else if (siteName.isEmpty()) {
                    tietSiteName.setError(setError);
                } else {
                    if (!AddMaterial.isUpdate){
                        addCosumerNameInFirebase(consumerName);
                    }

                    ((AddMaterial) requireActivity()).goToMaterialInformation(consumerName,state,district,taluka,center,village,siteName);
                }
            }
        });

        return rootView;
    }

    private void showSprineer(CustomSpinner customSpinner, String type, AutoCompleteTextView autoCompleteTextView) {
        customSpinner = new CustomSpinner(requireActivity(), userInfromation.getCompanyEmailWithOutExtension(), type);
        autoCompleteTextView.setAdapter(customSpinner.getAdapter());
        customSpinner.retrieveData();
    }



    // adding consumerName
    private void addCosumerNameInFirebase(String text) {
        databaseReferenceUpdate.child(text).setValue(text)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        spinnerDataListUpdate.clear();
                         retrieveDataUpdate();
                        adapterUpdate.notifyDataSetChanged();
                        Toast.makeText(requireActivity(), "inserted", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(requireActivity(), "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void retrieveDataUpdate(){
        listenerUpdate = databaseReferenceUpdate.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot item : snapshot.getChildren()){
                    spinnerDataListUpdate.add(item.getValue().toString());
                }
                adapterUpdate.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void retrivingDataForAutoCompleteTextView(String cmp) {
        databaseReferenceUpdate = FirebaseDatabase.getInstance().getReference(cmp + " consumerName");
        spinnerDataListUpdate = new ArrayList<>();
        adapterUpdate = new ArrayAdapter<String>(requireActivity(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, spinnerDataListUpdate);
        actvConsumerName.setAdapter(adapterUpdate);
        retrieveDataUpdate();
    }

}