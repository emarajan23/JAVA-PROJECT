package com.inventory.service.impl;

import com.inventory.dao.InventoryDao;
import com.inventory.dao.RequestDao;
import com.inventory.dao.impl.InventoryDaoImpl;
import com.inventory.dao.impl.RequestDaoImpl;
import com.inventory.model.ProductionRequest;
import com.inventory.service.ProductionRequestService;

import java.util.List;

public class ProductionRequestServiceImpl implements ProductionRequestService {

    private final RequestDao requestDao = new RequestDaoImpl();
    private final InventoryDao inventoryDao = new InventoryDaoImpl();

    @Override
    public boolean sendRequest(int inventoryId, int quantity) {
        if (quantity <= 0) {
            return false;
        }
        return requestDao.createRequest(inventoryId, quantity);
    }

    @Override
    public List<ProductionRequest> getPendingRequests() {
        return requestDao.getPendingRequests();
    }

    @Override
    public ProductionRequest getRequestById(int requestId) {
        return requestDao.getRequestById(requestId);
    }

    @Override
    public void processRequest(int requestId, String decision) {
        ProductionRequest req = requestDao.getRequestById(requestId);
        if (req == null) return;

        if ("yes".equalsIgnoreCase(decision)) {
            if (req.getQuantity() > req.getInventoryItem().getQuantity()) {
                requestDao.updateRequestStatus(requestId, "REJECTED");
            } else {
                inventoryDao.reduceInventoryQuantity(req.getInventoryItem().getInventoryId(), req.getQuantity());
                requestDao.updateRequestStatus(requestId, "APPROVED");
            }
        } else {
            requestDao.updateRequestStatus(requestId, "REJECTED");
        }
    }
}
