package com.inventory.dao;

import com.inventory.model.ProductionRequest;
import java.util.List;

public interface RequestDao {
    boolean createRequest(int inventoryId, int quantity);
    List<ProductionRequest> getPendingRequests();
    boolean updateRequestStatus(int requestId, String status);
    ProductionRequest getRequestById(int requestId);
}
