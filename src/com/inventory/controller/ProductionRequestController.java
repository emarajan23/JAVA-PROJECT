
package com.inventory.controller;

import com.inventory.dao.InventoryDao;
import com.inventory.dao.RequestDao;
import com.inventory.dao.impl.InventoryDaoImpl;
import com.inventory.dao.impl.RequestDaoImpl;
import com.inventory.model.ProductionRequest;

import java.util.List;

public class ProductionRequestController {

    private final RequestDao requestDao = new RequestDaoImpl();
    private final InventoryDao inventoryDao = new InventoryDaoImpl();

    public void sendRequest(int inventoryId, int quantity) {
        if (quantity <= 0) {
            System.out.println("Quantity must be greater than 0.");
            return;
        }

        boolean success = requestDao.createRequest(inventoryId, quantity);
        if (success) {
            System.out.println("Fabric request submitted successfully.");
        } else {
            System.out.println("Failed to submit request. Inventory ID might be invalid.");
        }
    }

    public List<ProductionRequest> getPendingRequests() {
        return requestDao.getPendingRequests();
    }

    public void processRequest(int requestId, String decision) {
        ProductionRequest req = requestDao.getRequestById(requestId);

        if (req == null) {
            System.out.println("Invalid Request ID.");
            return;
        }

        if (decision.equalsIgnoreCase("yes")) {
            if (req.getQuantity() > req.getInventoryItem().getQuantity()) {
                System.out.println("Not enough stock. Request rejected.");
                requestDao.updateRequestStatus(requestId, "REJECTED");
            } else {
                inventoryDao.reduceInventoryQuantity(req.getInventoryItem().getInventoryId(), req.getQuantity());
                requestDao.updateRequestStatus(requestId, "APPROVED");
                System.out.println(" Request approved. Stock updated.");
            }
        } else {
            requestDao.updateRequestStatus(requestId, "REJECTED");
            System.out.println("Request rejected.");
        }
    }
}
