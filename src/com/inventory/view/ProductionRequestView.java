package com.inventory.view;

import com.inventory.controller.ProductionRequestController;
import com.inventory.model.ProductionRequest;

import java.util.List;
import java.util.Scanner;

public class ProductionRequestView {

    private final ProductionRequestController controller = new ProductionRequestController();

    public void handleRequests() {
        List<ProductionRequest> requests = controller.getPendingRequests();

        if (requests.isEmpty()) {
            System.out.println("📭 No pending requests.");
            return;
        }

        for (ProductionRequest req : requests) {
            System.out.printf("ID: %d | Fabric: %s | Qty: %d | Stock: %d | Date: %s\n",
                    req.getRequestId(),
                    req.getInventoryItem().getFabric().getName(),
                    req.getQuantity(),
                    req.getInventoryItem().getQuantity(),
                    req.getRequestDate()
            );
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Request ID to process (0 to skip): ");
        int reqId = sc.nextInt();
        if (reqId == 0) return;

        System.out.print("Approve? (yes/no): ");
        String decision = sc.next();
        controller.processRequest(reqId, decision.toLowerCase());
    }
}
