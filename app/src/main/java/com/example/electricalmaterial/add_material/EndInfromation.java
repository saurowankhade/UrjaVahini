package com.example.electricalmaterial.add_material;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.electricalmaterial.R;
import com.example.electricalmaterial.getinfromation.GetUserInfromation;
import com.example.electricalmaterial.settingsprinner.CustomSpinner;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class EndInfromation extends Fragment {

    private FirebaseFirestore fStore;
    private final GetUserInfromation userInfromation = new GetUserInfromation(); // getting user data

    private final String setError = "Required!";

    protected CustomSpinner csMaterialReciverName;

    private TextInputEditText tietNote;
    private AutoCompleteTextView actvMaterialReceiverName;
    private TextInputEditText tietReceiverPassword;
    private MaterialButton mbPre;
    private MaterialButton mbNext;
    private ProgressBar pbProgressBar;

    private int count = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_end_infromation, container, false);

        // finding ids
        tietNote =rootView.findViewById(R.id.note);
        actvMaterialReceiverName =rootView.findViewById(R.id.receiver);
        tietReceiverPassword =rootView.findViewById(R.id.pass);
        mbPre =rootView.findViewById(R.id.pre);
        mbNext =rootView.findViewById(R.id.add);
        pbProgressBar =rootView.findViewById(R.id.progressBar);

        fStore = FirebaseFirestore.getInstance();


        // while updating the data
        if ( ((AddMaterial) requireActivity()).getsNote() !=null ){
            // setting the data for update
            tietNote.setText(((AddMaterial) requireActivity()).getsNote());
            actvMaterialReceiverName.setText(((AddMaterial) requireActivity()).getsMaterialReceiverName());
        }


        //declaring auto complete text view
        actvMaterialReceiverName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count==0){
                    showSprineer(csMaterialReciverName,"Receiver",actvMaterialReceiverName); // MaterialReceiverName
                }
                count++;
            }
        });


        // when click to back

        mbPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AddMaterial) requireActivity()).goToMaterialInformation();
            }
        });

        mbNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String note = tietNote.getText().toString().trim();
                String materialReceiverName = actvMaterialReceiverName.getText().toString().trim();
                String password = tietReceiverPassword.getText().toString().trim();

                if (note.isEmpty()){
                    tietNote.setError(setError);
                } else if (materialReceiverName.isEmpty()) {
                    actvMaterialReceiverName.setText(setError);
                } else if (password.isEmpty()) {
                    tietReceiverPassword.setError(setError);
                } else {
                    verifyPassReceiver(userInfromation.getCompanyEmail(),materialReceiverName,password,note);
                }

            }
        });

        return rootView;
    }

    private void verifyPassReceiver(String companyEmail, String materialReceiverName, String password,String note) {
        DocumentReference documentReference = fStore.collection(companyEmail+" ProfilePass")
                .document(materialReceiverName.toLowerCase());
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                if (password.equals(value.getString("Password"))){
                    ((AddMaterial) requireActivity()).goToDoneAnimation(note,materialReceiverName,userInfromation.getUserName());
                } else {
                    tietReceiverPassword.setError("Wrong password!");
                }



            }
        });
    }

    private void showSprineer(CustomSpinner customSpinner, String type, AutoCompleteTextView autoCompleteTextView) {
        customSpinner = new CustomSpinner(requireActivity(), userInfromation.getCompanyEmailWithOutExtension(), type);
        autoCompleteTextView.setAdapter(customSpinner.getAdapter());
        customSpinner.retrieveData();
    }

}