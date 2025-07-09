package com.inventory.service;

import com.inventory.model.Fabric;
import com.inventory.model.FabricEntity;

import java.util.List;

public interface FabricService {
    void addFabric(Fabric fabric);
    List<FabricEntity> getFabricsBySupplierId(int supplierId);
}
