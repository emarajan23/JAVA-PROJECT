package com.inventory.view;

import com.inventory.dao.FabricDao;
import com.inventory.dao.InventoryDao;
import com.inventory.dao.impl.FabricDaoImpl;
import com.inventory.dao.impl.InventoryDaoImpl;
import com.inventory.model.Fabric;
import com.inventory.model.Users;

import java.util.List;
import java.util.Scanner;

public class InventoryManagerDashboard {

    private final FabricDao fabricDao = new FabricDaoImpl();
    private final InventoryDao inventoryDao = new InventoryDaoImpl();
    private final Scanner sc = new Scanner(System.in);

    public void show(Users manager) {
        while (true) {
            System.out.println("\n📦 Inventory Manager Dashboard");
            System.out.println("1. View All Fabrics");
            System.out.println("2. Purchase Fabric from Supplier");
            System.out.println("3. View Inventory");
            System.out.println("4. Logout");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    viewAllFabrics();
                    break;
                case 2:
                    purchaseFabric();
                    break;
                case 3:
                    viewInventory();
                    break;
                case 4:
                    System.out.println("🔓 Logged out.");
                    return;
                default:
                    System.out.println("❌ Invalid choice. Try again.");
            }
        }
    }

    private void viewAllFabrics() {
        List<Fabric> fabrics = fabricDao.getAllFabrics();
        System.out.println("\n📋 All Available Fabrics:");
        for (Fabric fabric : fabrics) {
            System.out.printf("ID: %d | Name: %s | Type: %s | Color: %s | GSM: %d | Rolls: %d | Price/Roll: %.2f%n",
                    fabric.getFabricId(), fabric.getName(), fabric.getType(), fabric.getColor(),
                    fabric.getGsm(), fabric.getRolls(), fabric.getPricePerRoll());
        }
    }

    private void purchaseFabric() {
        viewAllFabrics();
        System.out.print("Enter Fabric ID to Purchase: ");
        int fabricId = sc.nextInt();
        System.out.print("Enter Quantity to Purchase (rolls): ");
        int quantity = sc.nextInt();

        inventoryDao.addOrUpdateInventory(fabricId, quantity);
        System.out.println("🛒 Fabric purchased and added to inventory.");
    }

    private void viewInventory() {
        System.out.println("\n📊 Current Inventory:");
        inventoryDao.getAllInventory().forEach(item -> {
            System.out.printf("InventoryID: %d | Fabric: %s | Type: %s | Quantity: %d%n",
                    item.getInventoryId(), item.getFabricName(), item.getFabricType(), item.getQuantity());
        });
    }
}
