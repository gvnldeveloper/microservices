package com.finland.controller.messages;

public class SubscribedCategories {

    private String name;
    private int remainingContent;
    private double price;
    private String startDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRemainingContent() {
        return remainingContent;
    }

    public void setRemainingContent(int remainingContent) {
        this.remainingContent = remainingContent;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}
