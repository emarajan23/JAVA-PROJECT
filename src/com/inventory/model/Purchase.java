package com.inventory.model;

import java.time.LocalDateTime;

public class Purchase {

    private int quantity;
    private double amount;
    private LocalDateTime purchaseDate;

    private FabricEntity fabric;

    public Purchase(int quantity, double amount, FabricEntity fabric) {
        this.quantity = quantity;
        this.amount = amount;
        this.fabric = fabric;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public FabricEntity getFabric() {
        return fabric;
    }
}
