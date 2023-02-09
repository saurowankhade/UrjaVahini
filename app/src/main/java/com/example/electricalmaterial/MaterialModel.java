package com.example.electricalmaterial;

public class MaterialModel {
    String id;
    String material;

    public MaterialModel(String id, String material) {
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
