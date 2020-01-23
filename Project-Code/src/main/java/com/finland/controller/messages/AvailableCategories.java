package com.finland.controller.messages;

public class AvailableCategories {

    private String name;
    private int availableContent;
    private double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAvailableContent() {
        return availableContent;
    }

    public void setAvailableContent(int availableContent) {
        this.availableContent = availableContent;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
