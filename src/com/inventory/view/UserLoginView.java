package com.inventory.view;

import java.util.Scanner;

public class UserLoginView {

    private final Scanner scanner = new Scanner(System.in);

    public String getUsernameInput() {
        System.out.print("Enter Username: ");
        return scanner.nextLine();
    }

    public String getPasswordInput() {
        System.out.print("Enter Password: ");
        return scanner.nextLine();
    }

    public void showLoginTitle(String role) {
        System.out.println("\n" + role + " Login");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showSuccess(String name) {
        System.out.println("Login successful. Welcome " + name);
    }

    public void showAccessDenied(String role) {
        System.out.println("Access denied. You are not a " + role);
    }
}
