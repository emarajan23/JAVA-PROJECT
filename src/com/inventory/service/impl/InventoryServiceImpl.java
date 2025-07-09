package com.inventory.service.impl;

import com.inventory.dao.InventoryDao;
import com.inventory.dao.impl.InventoryDaoImpl;
import com.inventory.model.InventoryItem;
import com.inventory.service.InventoryService;

import java.util.List;

public class InventoryServiceImpl implements InventoryService {

    private final InventoryDao inventoryDao = new InventoryDaoImpl();

    @Override
    public List<InventoryItem> getAllInventory() {
        return inventoryDao.getAllInventory();
    }

    @Override
    public List<InventoryItem> getLowInventoryItems() {
        return inventoryDao.getLowInventoryItems();
    }
}
