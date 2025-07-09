package com.inventory.controller;

import com.inventory.model.FabricEntity;
import com.inventory.model.UserEntity;
import com.inventory.service.FabricService;
import com.inventory.service.impl.FabricServiceImpl;
import com.inventory.view.SupplierMenuView;

import java.util.List;

public class SupplierMenuController {

    SupplierMenuView view =new SupplierMenuView();
    FabricController fabricController=new FabricController();
    FabricService fabricService = new FabricServiceImpl();

    public void showMenu(UserEntity supplier) {
        while (true) {
            view.showTitle();
            int choice = view.getUserChoice();

            switch (choice) {
                case 1:
                    fabricController.handleFabric(supplier);
                    break;
                case 2:
                    showMyFabrics(supplier);
                    break;
                case 3:
                    view.showLogoutMessage();
                    return;
                default:
                    view.showInvalidOption();
            }
        }
    }

    private void showMyFabrics(UserEntity supplier) {
        List<FabricEntity> fabrics = fabricService.getFabricsBySupplierId(supplier.getUserId());

        if (fabrics.isEmpty()) {
            System.out.println("No fabrics found for your account.");
        } else {
            System.out.println("Your Added Fabrics:");
            for (FabricEntity fabric : fabrics) {
                System.out.println("Fabric ID: " + fabric.getFabricId() +
                        " | Name: " + fabric.getName() +
                        " | Type: " + fabric.getType() +
                        " | Color: " + fabric.getColor() +
                        " | GSM: " + fabric.getGsm() +
                        " | Price: ₹" + fabric.getPrice());
            }
        }
    }


}
