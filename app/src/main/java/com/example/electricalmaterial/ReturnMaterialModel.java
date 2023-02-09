package com.example.electricalmaterial;

public class ReturnMaterialModel {
    String id;
    String material;

    public ReturnMaterialModel(String id, String material) {
        this.id = id;
        this.material = material;
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
}
