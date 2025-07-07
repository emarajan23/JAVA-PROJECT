package com.inventory.view;

import java.util.Scanner;

public class PurchaseView {

    private final Scanner scan=new Scanner(System.in);

    public int askFabricId() {
        System.out.print("\nEnter Fabric ID to purchase: ");
        return scan.nextInt();
    }

    public int askQuantity() {
        System.out.print("Enter Quantity to Purchase (rolls): ");
        return scan.nextInt();
    }

    public void showSuccessMessage() {
        System.out.println("Fabric purchased successfully.");
    }

    public void showError(String message) {
        System.out.println(message);
    }
}
