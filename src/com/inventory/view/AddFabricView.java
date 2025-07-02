package com.inventory.view;

import com.inventory.model.Fabric;
import com.inventory.model.Users;
import com.inventory.service.FabricService;
import com.inventory.service.impl.FabricServiceImpl;

import java.util.Scanner;

public class AddFabricView {
    private FabricService fabricService = new FabricServiceImpl();

    public void addFabric(Users supplier) {
        Scanner sc = new Scanner(System.in);
        Fabric fabric = new Fabric();

        System.out.println("\n🧵 Enter Fabric Details");

        System.out.print("Fabric Name     : ");
        fabric.setName(sc.nextLine());

        System.out.print("Fabric Type     : ");
        fabric.setType(sc.nextLine());

        System.out.print("Color           : ");
        fabric.setColor(sc.nextLine());

        System.out.print("GSM             : ");
        fabric.setGsm(sc.nextInt());

        System.out.print("Rolls           : ");
        fabric.setRolls(sc.nextInt());

        System.out.print("Price per Roll  : ");
        fabric.setPricePerRoll(sc.nextDouble());
        sc.nextLine(); // Consume newline

        fabric.setSupplier(supplier);

        // Save to DB
        fabricService.addFabric(fabric);
    }
}
