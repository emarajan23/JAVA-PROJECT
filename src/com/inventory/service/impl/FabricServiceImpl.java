package com.inventory.service.impl;

import com.inventory.dao.FabricDao;
import com.inventory.dao.impl.FabricDaoImpl;
import com.inventory.exception.DuplicateFabricException;
import com.inventory.model.Fabric;
import com.inventory.service.FabricService;

import java.util.List;

public class FabricServiceImpl implements FabricService {
    private final FabricDao fabricDao = new FabricDaoImpl();

    @Override
    public void addFabric(Fabric fabric) {
        if (fabricDao.fabricExists(fabric.getSupplier().getUserId(), fabric.getName(), fabric.getType(), fabric.getColor())) {
            throw new DuplicateFabricException("Fabric already exists for this supplier.");
        }
        fabricDao.saveFabric(fabric);
    }

//    @Override
//    public List<Fabric> getAllFabrics() {
//        return fabricDao.getAllFabrics();
//    }
}
