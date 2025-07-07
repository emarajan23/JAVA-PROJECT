package com.inventory.controller;

import com.inventory.dao.FabricDao;
import com.inventory.dao.InventoryDao;
import com.inventory.dao.PurchaseDao;
import com.inventory.dao.impl.FabricDaoImpl;
import com.inventory.dao.impl.InventoryDaoImpl;
import com.inventory.dao.impl.PurchaseDaoImpl;
import com.inventory.model.FabricEntity;
import com.inventory.model.Purchase;
import com.inventory.view.PurchaseView;

import java.util.List;

public class PurchaseController {

    private final PurchaseView view =new PurchaseView();
    private final FabricDao fabricDao=new FabricDaoImpl();
    private final PurchaseDao purchaseDao = new PurchaseDaoImpl();
    private final InventoryDao inventoryDao=new InventoryDaoImpl();


    public void handlePurchase(){
        List<FabricEntity> fabrics= fabricDao.getAllFabrics();


        if (fabrics.isEmpty()) {
            view.showError("No fabrics available to purchase.");
            return;
        }

        showAllFabrics(fabrics);


        FabricEntity selectedFabric = null;
        while (selectedFabric == null) {
            int fabricId = view.askFabricId();


            for (FabricEntity fabric : fabrics) {
                if (fabric.getFabricId() == fabricId) {
                    selectedFabric = fabric;
                    break;
                }
            }

            if (selectedFabric == null) {
                view.showError("Invalid Fabric ID. Please try again.");
            }
        }


        int quantity = 0;
        while (quantity <= 0) {
            quantity = view.askQuantity();
            if (quantity <= 0) {
                view.showError("Quantity must be greater than 0.");
            }
        }


        double amount = selectedFabric.getPrice() * quantity;


        Purchase purchase = new Purchase(quantity, amount, selectedFabric);


        purchaseDao.savePurchase(purchase);
        inventoryDao.addOrUpdateInventory(selectedFabric.getFabricId(), quantity);
    }

    private void showAllFabrics(List<FabricEntity> fabrics) {
        System.out.println("\n All Available Fabrics:");
        for (FabricEntity fabric : fabrics) {
            System.out.printf("ID: %d | Name: %s | Type: %s | Color: %s | GSM: %d | Price: ₹%.2f | Supplier: %s\n",
                    fabric.getFabricId(),
                    fabric.getName(),
                    fabric.getType(),
                    fabric.getColor(),
                    fabric.getGsm(),
                    fabric.getPrice(),
                    fabric.getSupplier().getName()
            );
        }
    }
}
