package com.inventory.view;


import com.inventory.model.UserRole;

import java.util.Scanner;

public class MainMenu {

    UserRegistrationView view=new UserRegistrationView();
    private final SupplierLoginView loginView = new SupplierLoginView();

    private Scanner scan = new Scanner(System.in);

    public void show() {
        while (true) {
            System.out.println("\nSelect your role to login:");
            System.out.println("1. Admin");
            System.out.println("2. Inventory Manager");
            System.out.println("3. Production Manager");
            System.out.println("4. Supplier");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scan.nextInt();
            scan.nextLine();

            switch (choice) {
                case 1:
                    // AdminView.login();
                    break;
                case 2:
                    // InventoryManagerView.login();
                    break;
                case 3:
                    // ProductionManagerView.login();
                    break;
                case 4:
                    handleSupplierMenu();
                    break;
                case 5:
                    System.out.println("Exiting. Thank you!");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void handleSupplierMenu() {
        while (true) {
            System.out.println("\n--- Supplier Menu ---");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Back");
            System.out.print("Enter your choice: ");
            int supplierChoice = scan.nextInt();
            scan.nextLine();

            switch (supplierChoice) {
                case 1:
                    view.registerUserByRole(UserRole.SUPPLIER);
                    break;
                case 2:
                    loginView.login();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

}
