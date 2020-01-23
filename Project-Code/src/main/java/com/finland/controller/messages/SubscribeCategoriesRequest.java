package com.finland.controller.messages;

public class SubscribeCategoriesRequest {

    private String email;
    private String availableCategory;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvailableCategory() {
        return availableCategory;
    }

    public void setAvailableCategory(String availableCategory) {
        this.availableCategory = availableCategory;
    }
}
