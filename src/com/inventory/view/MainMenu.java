package com.inventory.view;

import com.inventory.controller.InventoryManagerController;
import com.inventory.controller.SupplierMenuController;
import com.inventory.controller.UserLoginController;
import com.inventory.controller.UserRegistrationController;
import com.inventory.model.UserRole;
import com.inventory.model.UserEntity;

import java.util.Scanner;

public class MainMenu {

    private final Scanner scan = new Scanner(System.in);
    private final UserRegistrationController registrationController = new UserRegistrationController();
    private final UserLoginController loginController = new UserLoginController();

    public void show() {
        while (true) {
            System.out.println("\n=== com.inventory.Main Menu ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = getInputAsInt();

            switch (choice) {
                case 1:
                    showRegistrationMenu();
                    break;
                case 2:
                    showLoginMenu();
                    break;
                case 3:
                    System.out.println("Exiting. Thank you!");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void showRegistrationMenu() {
        while (true) {
            System.out.println("\n--- Register As ---");
            System.out.println("1. Supplier");
            System.out.println("2. Inventory Manager");
            System.out.println("3. Production Manager");
            System.out.println("4. Back");
            System.out.print("Enter your choice: ");

            int regChoice = getInputAsInt();

            switch (regChoice) {
                case 1:
                    registrationController.handleRegistration(UserRole.SUPPLIER);
                    break;
                case 2:
                    registrationController.handleRegistration(UserRole.INVENTORY_MANAGER);
                    break;
                case 3:
                    registrationController.handleRegistration(UserRole.PRODUCTION_MANAGER);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void showLoginMenu() {
        while (true) {
            System.out.println("\n--- Login As ---");
            System.out.println("1. Supplier");
            System.out.println("2. Inventory Manager");
            System.out.println("3. Production Manager");
            System.out.println("4. Back");
            System.out.print("Enter your choice: ");

            int loginChoice = getInputAsInt();

            switch (loginChoice) {
                case 1:
                    UserEntity supplier = loginController.handleLogin(UserRole.SUPPLIER);

                    if (supplier != null) {
                        new SupplierMenuController().showMenu(supplier);
                    }
                    break;
                case 2:
                    UserEntity manager = loginController.handleLogin(UserRole.INVENTORY_MANAGER);
                    if (manager != null) {
                        new InventoryManagerController().handleMenu();
                    }
                    break;
                case 3:
                    UserEntity pm = loginController.handleLogin(UserRole.PRODUCTION_MANAGER);
                    if (pm != null) {
                        new ProductionManagerDashboardView().show(pm);
                    }
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private int getInputAsInt() {
        try {
            return Integer.parseInt(scan.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
