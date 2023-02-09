package com.example.electricalmaterial;

public class AddStockModel {
    String id,material,quantity;
    String unit;

    public AddStockModel(String id, String material, String quantity,String unit) {
        this.id = id;
        this.material = material;
        this.quantity = quantity;
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

    public String getQuantity() {
        return quantity;
    }

    public String getUnit() {
        return unit;
    }

}
