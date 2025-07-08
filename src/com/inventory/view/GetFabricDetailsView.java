package com.inventory.view;

import java.util.Scanner;

public class GetFabricDetailsView {

    private final Scanner scan = new Scanner(System.in);

    public String getName() {
        System.out.print("Enter Fabric Name: ");
        return scan.nextLine();
    }

    public String getType() {
        System.out.print("Enter Fabric Type (e.g., Cotton, Silk): ");
        return scan.nextLine();
    }

    public String getColor() {
        System.out.print("Enter Fabric Color: ");
        return scan.nextLine();
    }

    public int getGsm() {
        System.out.print("Enter GSM (Weight): ");
        int gsm = scan.nextInt();
        scan.nextLine();
        return gsm;
    }

    public double getPrice() {
        System.out.print("Enter Price (roll): ");
        double price = scan.nextDouble();
        scan.nextLine();
        return price;
    }
    public boolean askToContinue() {
        System.out.print("Do you want to add another fabric? (yes/no): ");
        String input = scan.nextLine();
        return input.equalsIgnoreCase("yes");
    }
}
