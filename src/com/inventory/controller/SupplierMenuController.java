package com.inventory.controller;

import com.inventory.model.Users;
import com.inventory.view.SupplierMenuView;

public class SupplierMenuController {

    SupplierMenuView view =new SupplierMenuView();
    FabricController fabricController=new FabricController();

    public void showMenu(Users supplier) {
        while (true) {
            view.showTitle();
            int choice = view.getUserChoice();

            switch (choice) {
                case 1:
                    fabricController.handleFabric(supplier);
                    break;
                case 2:
                    view.showLogoutMessage();
                    return;
                default:
                    view.showInvalidOption();
            }
        }
    }
}
