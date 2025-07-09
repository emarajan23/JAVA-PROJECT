package com.inventory.service;

import com.inventory.model.InventoryItem;

import java.util.List;

public interface InventoryService {
    List<InventoryItem> getAllInventory();
    List<InventoryItem> getLowInventoryItems();
}
