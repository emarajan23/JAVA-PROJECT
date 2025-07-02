package com.inventory.view;

import com.inventory.model.Users;

import java.util.Scanner;

public class SupplierDashboardView {
    public void show(Users supplier) {
        Scanner sc = new Scanner(System.in);
        AddFabricView addFabricView = new AddFabricView();

        while (true) {
            System.out.println("\n📦 Supplier Dashboard");
            System.out.println("1. Add Fabric");
            System.out.println("2. Logout");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addFabricView.addFabric(supplier);
                    break;
                case 2:
                    System.out.println("🔒 Logged out.");
                    return;
                default:
                    System.out.println("❌ Invalid option. Try again.");
            }
        }
    }
}
