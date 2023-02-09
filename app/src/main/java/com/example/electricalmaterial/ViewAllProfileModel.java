package com.example.electricalmaterial;

public class ViewAllProfileModel {
    String id,companyEmail,companyName,currentAddress,permanentAddress,profile,mobileNumber,fullName,email,education;

    public ViewAllProfileModel(String id,String companyEmail, String companyName, String currentAddress, String permanentAddress, String profile, String mobileNumber, String fullName, String email, String education) {
        this.id = id;
        this.companyEmail = companyEmail;
        this.companyName = companyName;
        this.currentAddress = currentAddress;
        this.permanentAddress = permanentAddress;
        this.profile = profile;
        this.mobileNumber = mobileNumber;
        this.fullName = fullName;
        this.email = email;
        this.education = education;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public String getProfile() {
        return profile;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getEducation() {
        return education;
    }
}
