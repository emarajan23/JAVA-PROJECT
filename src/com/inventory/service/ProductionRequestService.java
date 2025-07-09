package com.inventory.service;

import com.inventory.model.ProductionRequest;

import java.util.List;

public interface ProductionRequestService {
    boolean sendRequest(int inventoryId, int quantity);
    List<ProductionRequest> getPendingRequests();
    ProductionRequest getRequestById(int requestId);
    void processRequest(int requestId, String decision);
}
