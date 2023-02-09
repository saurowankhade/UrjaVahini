package com.example.electricalmaterial;

public class KgToNoModel {
    String id,material,number,unit;

    public KgToNoModel(String id, String material, String number,String unit) {
        this.id = id;
        this.material = material;
        this.number = number;
        this.unit = unit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaterial() {
        return material;
    }

    public String getNumber() {
        return number;
    }

    public String getUnit() {
        return unit;
    }
}
