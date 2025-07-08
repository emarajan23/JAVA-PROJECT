package com.inventory.dao;

import com.inventory.model.Fabric;
import com.inventory.model.FabricEntity;

import java.util.List;

public interface FabricDao {
    void saveFabric(Fabric fabric);
    List<FabricEntity> getAllFabrics();
    boolean fabricExists(int supplierId, String name, String type, String color);
}
