package com.example.electricalmaterial.getinfromation;

import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class GetUserInfromation {


    //authorization
    private FirebaseAuth mAuth;
    private FirebaseFirestore fStore;
    private String companyEmailWithOutExtension;
    private String userId;
    private String companyEmail;

    private String companyName;
    private String userName;
    private String userAddress;
    private String userEducation;
    private String userPhoneNo;
    private String userRole;

    public GetUserInfromation() {
       setUserId();
       setData();
    }


    private void setUserId(){
        //Auth
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = mAuth.getCurrentUser().getUid();
    }

    private void setData(){
        DocumentReference documentReference = fStore.collection("Users")
                .document(userId);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                companyEmailWithOutExtension = value.getString("companyEmail");
                companyEmail = companyEmailWithOutExtension;
                companyEmailWithOutExtension = companyEmailWithOutExtension.replace("@", "");
                companyEmailWithOutExtension = companyEmailWithOutExtension.replace(".", "");

                companyName = value.getString("companyName");
                userName = value.getString("fullName");
                userAddress = value.getString("currentAddress");
                userEducation = value.getString("education");
                userPhoneNo = value.getString("mobileNumber");
                userRole = value.getString("profile");

//                return false;
            }
        });
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public String getCompanyEmailWithOutExtension() {
        return companyEmailWithOutExtension;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public String getUserEducation() {
        return userEducation;
    }

    public String getUserPhoneNo() {
        return userPhoneNo;
    }

    public String getUserRole() {
        return userRole;
    }
}
