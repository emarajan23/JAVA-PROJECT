package com.inventory.controller;

import com.inventory.exception.DuplicateFabricException;
import com.inventory.model.Fabric;
import com.inventory.model.UserEntity;
import com.inventory.service.FabricService;
import com.inventory.service.impl.FabricServiceImpl;
import com.inventory.view.GetFabricDetailsView;

public class FabricController {

    GetFabricDetailsView view=new GetFabricDetailsView();
    private final FabricService fabricService = new FabricServiceImpl();

    public void handleFabric(UserEntity supplier) {
        while (true) {
            String name = view.getName().toLowerCase();
            String type = view.getType().toLowerCase();
            String color = view.getColor().toLowerCase();
            int gsm = view.getGsm();
            double price = view.getPrice();

            Fabric fabric = new Fabric(name, type, color, gsm, price, supplier);

            try {
                fabricService.addFabric(fabric);
            } catch (DuplicateFabricException e) {
                System.out.println(e.getMessage());
            }

            if (!view.askToContinue()){
                break;
            };
        }
    }
}
