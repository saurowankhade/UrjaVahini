package com.example.electricalmaterial;

public class BalanceMaterialModel {
    String id;
    String date;
    String teamName;
    String line;
    String tender;
    String driverName;
    String vehicalName;
    String consumerName;
    String site;

    String materialReceiverName;
    String center;
    String village;


    public BalanceMaterialModel(String id, String date, String team_name, String line, String tender, String driver_name, String vehical_name, String consumer_name, String site_name, String material_receiver_name, String center, String village) {

        this.id = id;
        this.date = date;
        this.teamName = team_name;
        this.line = line;
        this.tender = tender;
        this.driverName = driver_name;
        this.vehicalName = vehical_name;
        this.consumerName = consumer_name;
        this.site = site_name;

        this.materialReceiverName = material_receiver_name;
        this.center = center;
        this.village = village;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getSite() {
        return site;
    }


    public String getMaterialReceiverName() {
        return materialReceiverName;
    }

    public String getCenter() {
        return center;
    }

    public String getVillage() {
        return village;
    }
}
