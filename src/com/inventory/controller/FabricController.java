package com.inventory.controller;

import com.inventory.model.Fabric;
import com.inventory.model.Users;
import com.inventory.service.FabricService;
import com.inventory.service.impl.FabricServiceImpl;
import com.inventory.view.GetFabricDetailsView;

public class FabricController {

    GetFabricDetailsView view=new GetFabricDetailsView();
    private final FabricService fabricService = new FabricServiceImpl();

    public void handleFabric(Users supplier){
        String name = view.getName().toLowerCase();
        String type = view.getType().toLowerCase();
        String color = view.getColor().toLowerCase();
        int gsm = view.getGsm();
        double price = view.getPrice();

        Fabric fabric=new Fabric (name,type,color,gsm,price,supplier);
        fabricService.addFabric(fabric);
    }
}
