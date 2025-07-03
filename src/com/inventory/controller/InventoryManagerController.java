package com.inventory.controller;

import com.inventory.view.InventoryManagerMenu;

public class InventoryManagerController {

    InventoryManagerMenu menu=new InventoryManagerMenu();

    public void handleMenu(){
        int choice= menu.showMenu();
    }
}
