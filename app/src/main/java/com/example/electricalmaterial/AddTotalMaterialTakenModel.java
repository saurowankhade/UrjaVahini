package com.example.electricalmaterial;

public class AddTotalMaterialTakenModel {
   private String id;
   private String material;
   private String unit;
   private String quantity;

    public AddTotalMaterialTakenModel(String id, String material, String unit, String quantity) {
        this.id = id;
        this.material = material;
        this.unit = unit;
        this.quantity = quantity;
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

    public String getUnit() {
        return unit;
    }

    public String getQuantity() {
        return quantity;
    }
}
