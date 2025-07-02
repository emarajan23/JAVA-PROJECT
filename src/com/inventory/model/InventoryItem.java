package com.inventory.model;

public class InventoryItem {
    private int inventoryId;
    private int fabricId;
    private int quantity;
    private String fabricName;
    private String fabricType;

    // Getters and Setters

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public int getFabricId() {
        return fabricId;
    }

    public void setFabricId(int fabricId) {
        this.fabricId = fabricId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getFabricName() {
        return fabricName;
    }

    public void setFabricName(String fabricName) {
        this.fabricName = fabricName;
    }

    public String getFabricType() {
        return fabricType;
    }

    public void setFabricType(String fabricType) {
        this.fabricType = fabricType;
    }
}
