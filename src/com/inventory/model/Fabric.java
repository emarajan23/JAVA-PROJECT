package com.inventory.model;

public class Fabric {
    private int fabricId;
    private String name;
    private String type;
    private String color;
    private int gsm;
    private int rolls;
    private double pricePerRoll;

    private Users supplier;



    public int getFabricId() {
        return fabricId;
    }

    public void setFabricId(int fabricId) {
        this.fabricId = fabricId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getGsm() {
        return gsm;
    }

    public void setGsm(int gsm) {
        this.gsm = gsm;
    }

    public int getRolls() {
        return rolls;
    }

    public void setRolls(int rolls) {
        this.rolls = rolls;
    }

    public double getPricePerRoll() {
        return pricePerRoll;
    }

    public void setPricePerRoll(double pricePerRoll) {
        this.pricePerRoll = pricePerRoll;
    }

    public Users getSupplier() {
        return supplier;
    }

    public void setSupplier(Users supplier) {
        this.supplier = supplier;
    }
}
