package com.inventory.model;

import java.time.LocalDateTime;

public class ProductionRequest {
    private int requestId;
    private InventoryItem inventoryItem;
    private int quantity;
    private RequestStatus status;
    private LocalDateTime requestDate;

    public ProductionRequest(int requestId, InventoryItem inventoryItem, int quantity, RequestStatus status, LocalDateTime requestDate) {
        this.requestId = requestId;
        this.inventoryItem = inventoryItem;
        this.quantity = quantity;
        this.status = status;
        this.requestDate = requestDate;
    }

    public ProductionRequest(InventoryItem inventoryItem, int quantity) {
        this.inventoryItem = inventoryItem;
        this.quantity = quantity;
        this.status = RequestStatus.PENDING;
        this.requestDate = LocalDateTime.now();
    }

    public int getRequestId() {
        return requestId;
    }

    public InventoryItem getInventoryItem() {
        return inventoryItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }
}
