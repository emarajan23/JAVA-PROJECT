package com.inventory.model;

public class InventoryItem {

    private int inventoryId;
    private FabricEntity fabric;
    private int quantity;


    public InventoryItem(int inventoryId, FabricEntity fabric, int quantity) {
        this.inventoryId = inventoryId;
        this.fabric = fabric;
        this.quantity = quantity;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public FabricEntity getFabric() {
        return fabric;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return String.format(
                "Inventory ID: %d | Fabric ID: %d | Name: %s | Type: %s | Color: %s | GSM: %d | Rolls: %d | Supplier: %s",
                inventoryId,
                fabric.getFabricId(),
                fabric.getName(),
                fabric.getType(),
                fabric.getColor(),
                fabric.getGsm(),
                quantity,
                fabric.getSupplier() != null ? fabric.getSupplier().getName() : "N/A"
        );
    }
}
