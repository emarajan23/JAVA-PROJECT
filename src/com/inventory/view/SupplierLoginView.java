package com.inventory.view;


import java.util.Scanner;
import com.inventory.exception.InvalidInputException;
import com.inventory.exception.LoginFailedException;
import com.inventory.model.UserRole;
import com.inventory.model.Users;
import com.inventory.service.UserService;
import com.inventory.service.impl.UserServiceImpl;
import com.inventory.validation.InputValidator;

public class SupplierLoginView {

    private final UserService userService = new UserServiceImpl();

    public void login() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Supplier Login");

        String username;
        while (true) {
            System.out.print("Enter Username: ");
            username = scan.nextLine();
            try {
                InputValidator.validateUsername(username);
                break;
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }

        String password;
        while (true) {
            System.out.print("Enter Password: ");
            password = scan.nextLine();
            try {
                InputValidator.validatePassword(password);
                break;
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }

        try {
            Users user = userService.login(username, password);

            if (user.getRole() != UserRole.SUPPLIER) {
                System.out.println("Access denied. You are not a supplier.");
                return;
            }

            System.out.println("Login successful. Welcome " + user.getName());
            handleSupplierActions(user);



        } catch (LoginFailedException | InvalidInputException e) {
            System.out.println( e.getMessage());
        }
    }

    private void handleSupplierActions(Users supplier) {
        Scanner sc = new Scanner(System.in);
        AddFabricView addFabricView = new AddFabricView();

        while (true) {
            System.out.println("\n Supplier Dashboard");
            System.out.println("1. Add Fabric");
            System.out.println("2. Logout");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addFabricView.addFabric(supplier);
                    break;
                case 2:
                    System.out.println("Logged out.");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
