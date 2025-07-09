package com.inventory.controller;

import com.inventory.model.ProductionRequest;
import com.inventory.service.ProductionRequestService;
import com.inventory.service.impl.ProductionRequestServiceImpl;

import java.util.List;

public class ProductionRequestController {

    private final ProductionRequestService requestService = new ProductionRequestServiceImpl();

    public void sendRequest(int inventoryId, int quantity) {
        if (requestService.sendRequest(inventoryId, quantity)) {
            System.out.println("Fabric request submitted successfully.");
        } else {
            System.out.println("Failed to submit request. Check quantity or inventory ID.");
        }
    }

    public List<ProductionRequest> getPendingRequests() {
        return requestService.getPendingRequests();
    }

    public void processRequest(int requestId, String decision) {
        ProductionRequest req = requestService.getRequestById(requestId);

        if (req == null) {
            System.out.println("Invalid Request ID.");
            return;
        }

        requestService.processRequest(requestId, decision);

        if ("yes".equalsIgnoreCase(decision)) {
            if (req.getQuantity() > req.getInventoryItem().getQuantity()) {
                System.out.println("Not enough stock. Request rejected.");
            } else {
                System.out.println("Request approved. Stock updated.");
            }
        } else {
            System.out.println("Request rejected.");
        }
    }
}
