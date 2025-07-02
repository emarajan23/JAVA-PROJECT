package com.inventory.dao;

import com.inventory.model.Fabric;
import java.util.List;

public interface FabricDao {
    void saveFabric(Fabric fabric);
    List<Fabric> getAllFabrics();
}
