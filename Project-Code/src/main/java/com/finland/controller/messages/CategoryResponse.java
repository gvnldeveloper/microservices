package com.finland.controller.messages;

import java.util.List;

public class CategoryResponse {

    private List<AvailableCategories> availableCategories;
    private List<SubscribedCategories> subscribedCategories;

    public List<AvailableCategories> getAvailableCategories() {
        return availableCategories;
    }

    public void setAvailableCategories(List<AvailableCategories> availableCategories) {
        this.availableCategories = availableCategories;
    }

    public List<SubscribedCategories> getSubscribedCategories() {
        return subscribedCategories;
    }

    public void setSubscribedCategories(List<SubscribedCategories> subscribedCategories) {
        this.subscribedCategories = subscribedCategories;
    }
}
