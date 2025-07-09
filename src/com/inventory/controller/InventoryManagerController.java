package com.inventory.controller;


import com.inventory.model.InventoryItem;
import com.inventory.model.ProductionRequest;
import com.inventory.service.InventoryService;
import com.inventory.service.impl.InventoryServiceImpl;
import com.inventory.view.InventoryManagerMenu;

import java.util.List;
import java.util.Scanner;

public class InventoryManagerController {

    InventoryManagerMenu menu = new InventoryManagerMenu();
    PurchaseController purchase = new PurchaseController();
    InventoryService inventoryService = new InventoryServiceImpl();
    ProductionRequestController requestController = new ProductionRequestController();

    public void handleMenu() {
        while (true) {
            int choice = menu.showMenu();

            switch (choice) {
                case 1:
                    purchase.handlePurchase();
                    break;
                case 2:
                    showAllInventory();
                    break;
                case 3:
                    showLowInventory();
                    break;
                case 4:
                    handleFabricRequests();
                    break;
                case 5:
                    System.out.println("Logged out.");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void showAllInventory() {
        List<InventoryItem> inventory = inventoryService.getAllInventory();

        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }

        System.out.println("\nCurrent Inventory:");
        for (InventoryItem item : inventory) {
            System.out.printf("Fabric ID: %d | Name: %s | Type: %s | Quantity: %d rolls\n",
                    item.getFabric().getFabricId(),
                    item.getFabric().getName(),
                    item.getFabric().getType(),
                    item.getQuantity()
            );
        }
    }

    private void showLowInventory() {
        List<InventoryItem> lowStock = inventoryService.getLowInventoryItems();


        if (lowStock.isEmpty()) {
            System.out.println("No low inventory items (All stocks are sufficient).");
            return;
        }

        System.out.println("\nLow Inventory Report (Quantity <= 5 rolls):");
        for (InventoryItem item : lowStock) {
            System.out.printf("Fabric ID: %d | Name: %s | Type: %s | Quantity: %d rolls\n",
                    item.getFabric().getFabricId(),
                    item.getFabric().getName(),
                    item.getFabric().getType(),
                    item.getQuantity()
            );
        }
    }

    private void handleFabricRequests() {
        List<ProductionRequest> requests = requestController.getPendingRequests();

        if (requests.isEmpty()) {
            System.out.println(" No pending requests.");
            return;
        }

        System.out.println("\nPending Fabric Requests:");
        for (ProductionRequest req : requests) {
            System.out.printf("Request ID: %d | Fabric: %s | Requested: %d | In Stock: %d | Date: %s\n",
                    req.getRequestId(),
                    req.getInventoryItem().getFabric().getName(),
                    req.getQuantity(),
                    req.getInventoryItem().getQuantity(),
                    req.getRequestDate()
            );
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Request ID to approve/reject (0 to cancel): ");
        int id = Integer.parseInt(sc.nextLine());
        if (id == 0) return;

        System.out.print("Approve this request? (yes/no): ");
        String decision = sc.nextLine().trim().toLowerCase();

        requestController.processRequest(id, decision);
    }
}
