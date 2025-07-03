package com.inventory.view;

import java.util.Scanner;

public class UserRegistrationView {

    private final Scanner scanner = new Scanner(System.in);

    public String getNameInput() {
        System.out.print("Enter Name: ");
        return scanner.nextLine();
    }

    public String getEmailInput() {
        System.out.print("Enter Email: ");
        return scanner.nextLine();
    }

    public String getContactInput() {
        System.out.print("Enter Contact Number: ");
        return scanner.nextLine();
    }

    public String getUsernameInput() {
        System.out.print("Choose Username: ");
        return scanner.nextLine();
    }

    public String getPasswordInput() {
        System.out.print("Choose Password: ");
        return scanner.nextLine();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
