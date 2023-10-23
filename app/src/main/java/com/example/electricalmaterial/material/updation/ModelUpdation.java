package com.example.electricalmaterial.material.updation;

public class ModelUpdation {
    private String id;
    private String date;
    private String teamName;
    private String line;
    private String tender;
    private String driverName;
    private String vehicalName;
    private String consumerName;
    private String state;
    private String district;
    private String taluka;
    private String centerName;
    private String village;
    private String site;
    private String materialReceiverName;
    private String materialGiverName;
    private String note;

    public ModelUpdation(String id, String date, String teamName, String line, String tender, String driverName, String vehicalName, String consumerName, String state, String district, String taluka, String centerName, String village, String site, String materialReceiverName, String materialGiverName,String note) {
        this.id = id;
        this.date = date;
        this.teamName = teamName;
        this.line = line;
        this.tender = tender;
        this.driverName = driverName;
        this.vehicalName = vehicalName;
        this.consumerName = consumerName;
        this.state = state;
        this.district = district;
        this.taluka = taluka;
        this.centerName = centerName;
        this.village = village;
        this.site = site;
        this.materialReceiverName = materialReceiverName;
        this.materialGiverName = materialGiverName;
        this.note = note;
    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getLine() {
        return line;
    }

    public String getTender() {
        return tender;
    }

    public String getDriverName() {
        return driverName;
    }

    public String getVehicalName() {
        return vehicalName;
    }

    public String getConsumerName() {
        return consumerName;
    }

    public String getState() {
        return state;
    }

    public String getDistrict() {
        return district;
    }

    public String getTaluka() {
        return taluka;
    }

    public String getCenterName() {
        return centerName;
    }

    public String getVillage() {
        return village;
    }

    public String getSite() {
        return site;
    }

    public String getMaterialReceiverName() {
        return materialReceiverName;
    }

    public String getMaterialGiverName() {
        return materialGiverName;
    }

    public String getNote() {
        return note;
    }
}
