package com.inventory.dao;

import com.inventory.model.InventoryItem;

import java.util.List;

public interface InventoryDao {

    void addOrUpdateInventory(int fabricId, int quantity);

    List<InventoryItem> getAllInventory();

    List<InventoryItem> getLowInventoryItems();

    void reduceInventoryQuantity(int inventoryId, int quantity);

}
