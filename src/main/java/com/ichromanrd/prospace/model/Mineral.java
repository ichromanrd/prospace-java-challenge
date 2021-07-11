package com.ichromanrd.prospace.model;

public class Mineral {
    private String name;
    private double creditsPerUnit;

    public static Mineral create(String name, double creditsPerUnit) {
        Mineral mineral = new Mineral();
        mineral.setName(name);
        mineral.setCreditsPerUnit(creditsPerUnit);
        return mineral;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCreditsPerUnit() {
        return creditsPerUnit;
    }

    public void setCreditsPerUnit(double creditsPerUnit) {
        this.creditsPerUnit = creditsPerUnit;
    }
}
