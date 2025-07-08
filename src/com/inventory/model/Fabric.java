package com.inventory.model;

public class Fabric {

    private String name;
    private String type;
    private String color;
    private int gsm;
    private double price;

    private UserEntity supplier;


    public Fabric(String name, String type, String color, int gsm, double price, UserEntity supplier) {
        this.name = name;
        this.type = type;
        this.color = color;
        this.gsm = gsm;
        this.price = price;
        this.supplier = supplier;
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

    public UserEntity getSupplier() {
        return supplier;
    }


    @Override
    public String toString() {
        return "Fabric{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", color='" + color + '\'' +
                ", gsm=" + gsm +
                ", price=" + price +
                ", supplier=" + (supplier != null ? supplier.getName() : "null") +
                '}';
    }

}
