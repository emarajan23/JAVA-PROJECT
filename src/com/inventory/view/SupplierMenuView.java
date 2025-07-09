package com.inventory.view;



import java.util.Scanner;

public class SupplierMenuView {
    private final Scanner scan=new Scanner(System.in);


    public void showTitle(){
        System.out.println("\n Supplier Menu");
    }

    public int getUserChoice() {
        System.out.println("1. Add Fabric");
        System.out.println("2. View My Fabric");
        System.out.println("3. Logout");
        System.out.print("Enter choice: ");
        return scan.nextInt();
    }

    public void showLogoutMessage() {
        System.out.println("Logged out.");
    }

    public void showInvalidOption() {
        System.out.println("Invalid option. Try again.");
    }

}
