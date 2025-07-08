package com.inventory.view;

import com.inventory.controller.ProductionRequestController;
import com.inventory.dao.InventoryDao;
import com.inventory.dao.impl.InventoryDaoImpl;
import com.inventory.model.InventoryItem;
import com.inventory.model.UserEntity;

import java.util.List;
import java.util.Scanner;

public class ProductionManagerDashboardView {

    private final InventoryDao inventoryDao = new InventoryDaoImpl();
    private final ProductionRequestController requestController = new ProductionRequestController();

    public void show(UserEntity manager) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n🏭 Production Manager Dashboard");
            System.out.println("1. View Inventory");
            System.out.println("2. Request Fabric");
            System.out.println("3. Logout");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    viewInventory();
                    break;
                case 2:
                    requestFabric();
                    break;
                case 3:
                    System.out.println("🔓 Logged out.");
                    return;
                default:
                    System.out.println("❌ Invalid option. Try again.");
            }
        }
    }

    private void viewInventory() {
        List<InventoryItem> inventoryList = inventoryDao.getAllInventory();

        if (inventoryList.isEmpty()) {
            System.out.println("📦 No inventory available.");
            return;
        }

        System.out.println("\n📦 Available Inventory:");
        for (InventoryItem item : inventoryList) {
            System.out.printf("Inventory ID: %d | Name: %s | Type: %s | Rolls: %d\n",
                    item.getInventoryId(),
                    item.getFabric().getName(),
                    item.getFabric().getType(),
                    item.getQuantity()
            );
        }
    }

    private void requestFabric() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Inventory ID to request: ");
        int inventoryId = sc.nextInt();

        System.out.print("Enter Quantity (rolls): ");
        int quantity = sc.nextInt();

        requestController.sendRequest(inventoryId, quantity);
    }
}
