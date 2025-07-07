package com.inventory.view;


import java.util.Scanner;

public class InventoryManagerMenu {

    private final Scanner scan = new Scanner(System.in);

    public int showMenu() {
        System.out.println("\nInventory Manager Dashboard");
        System.out.println("1. Purchase Fabric from Supplier");
        System.out.println("2. View Inventory");
        System.out.println("3. View Low Inventory Report");
        System.out.println("4. Handle Fabric Requests");
        System.out.println("5. Logout");
        System.out.print("Enter your choice: ");
        return scan.nextInt();


    }
}