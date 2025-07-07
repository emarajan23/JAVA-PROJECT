package com.inventory.model;

public class FabricEntity {
    private int fabricId;
    private String name;
    private String type;
    private String color;
    private int gsm;
    private double price;

    private Users supplier;

    public FabricEntity(int fabricId, String name, String type, String color, int gsm, double price, Users supplier) {
        this.fabricId = fabricId;
        this.name = name;
        this.type = type;
        this.color = color;
        this.gsm = gsm;
        this.price = price;
        this.supplier = supplier;
    }

    public FabricEntity(int fabricId, String name, String type, String color, int gsm, double price) {
        this.fabricId = fabricId;
        this.name = name;
        this.type = type;
        this.color = color;
        this.gsm = gsm;
        this.price = price;
        this.supplier = null;
    }

    public int getFabricId() {
        return fabricId;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getColor() {
        return color;
    }

    public int getGsm() {
        return gsm;
    }

    public double getPrice() {
        return price;
    }

    public Users getSupplier() {
        return supplier;
    }

    @Override
    public String toString() {
        return "FabricEntity{" +
                "fabricId=" + fabricId +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", color='" + color + '\'' +
                ", gsm=" + gsm +
                ", price=" + price +
                ", supplier=" + (supplier != null ? supplier.getName() : "null") +
                '}';
    }
}
