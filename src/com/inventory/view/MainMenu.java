package com.inventory.view;

import com.inventory.model.UserRole;
import com.inventory.model.Users;

import java.util.Scanner;

public class MainMenu {

    private final Scanner scan = new Scanner(System.in);
    private final UserRegistrationView registrationView = new UserRegistrationView();
    private final UserLoginView loginView = new UserLoginView();

    public void show() {
        while (true) {
            System.out.println("\n Main Menu");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scan.nextInt();
            scan.nextLine();

            switch (choice) {
                case 1:
                    showRegistrationMenu();
                    break;
                case 2:
                    showLoginMenu();
                    break;
                case 3:
                    System.out.println(" Exiting. Thank you!");
                    return;
                default:
                    System.out.println(" Invalid choice. Try again.");
            }
        }
    }

    private void showRegistrationMenu() {
        while (true) {
            System.out.println("\n Register As:");
            System.out.println("1. Supplier");
            System.out.println("2. Inventory Manager");
            System.out.println("3. Production Manager");
            System.out.println("4. Back");
            System.out.print("Enter your choice: ");

            int regChoice = scan.nextInt();
            scan.nextLine();

            switch (regChoice) {
                case 1:
                    registrationView.registerUserByRole(UserRole.SUPPLIER);
                    break;
                case 2:
                    registrationView.registerUserByRole(UserRole.INVENTORY_MANAGER);
                    break;
                case 3:
                    registrationView.registerUserByRole(UserRole.PRODUCTION_MANAGER);
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
            System.out.println("\n Login As:");
            System.out.println("1. Supplier");
            System.out.println("2. Inventory Manager");
            System.out.println("3. Production Manager");
            System.out.println("4. Back");
            System.out.print("Enter your choice: ");

            int loginChoice = scan.nextInt();
            scan.nextLine();

            switch (loginChoice) {
                case 1: {
                    Users supplier = loginView.loginByRole(UserRole.SUPPLIER);
                    if (supplier != null) {
                        new SupplierDashboardView().show(supplier);
                    }
                    break;
                }
                case 2: {
                    Users manager = loginView.loginByRole(UserRole.INVENTORY_MANAGER);
                    if (manager != null) {
                        new InventoryManagerDashboard().show(manager);
                    }
                    break;
                }
                case 3: {
                    Users pm = loginView.loginByRole(UserRole.PRODUCTION_MANAGER);
                    if (pm != null) {
                        new ProductionManagerDashboardView().show(pm);
                    }
                    break;
                }
                case 4:
                    return;
                default:
                    System.out.println(" Invalid choice. Try again.");
            }
        }
    }
}
