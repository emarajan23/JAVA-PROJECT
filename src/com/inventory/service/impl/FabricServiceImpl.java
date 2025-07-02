package com.inventory.service.impl;

import com.inventory.dao.FabricDao;
import com.inventory.dao.impl.FabricDaoImpl;
import com.inventory.model.Fabric;
import com.inventory.service.FabricService;

import java.util.List;

public class FabricServiceImpl implements FabricService {
    private FabricDao fabricDao = new FabricDaoImpl();

    @Override
    public void addFabric(Fabric fabric) {
        fabricDao.saveFabric(fabric);
    }

    @Override
    public List<Fabric> getAllFabrics() {
        return fabricDao.getAllFabrics();
    }
}
