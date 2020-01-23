package com.finland.service.mapper;

import com.finland.controller.messages.SubscribeCategoriesRequest;
import com.finland.model.Category;
import com.finland.model.Subscription;
import com.finland.model.User;
import com.finland.service.CategoryService;
import com.finland.service.SubscriptionService;
import com.finland.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class SubscribeCategoriesRequestMapper {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;
    @Autowired
    private SubscriptionService subscriptionService;

    public Subscription map(SubscribeCategoriesRequest subscribeCategoriesRequest) {

        Category category = categoryService.getCategoryByName(subscribeCategoriesRequest.getAvailableCategory());
        User user = userService.findUser(subscribeCategoriesRequest.getEmail(), null);
        if (category == null || user == null) {
            throw new RuntimeException("Invalid request parameters");
        }

        Subscription sub = subscriptionService.checkCategory(category, user, null);
        if (sub == null) {
            Subscription subscription = new Subscription();
            subscription.setSubscribed(true);
            subscription.setLocalDate(LocalDate.now());
            subscription.setCategory(category);
            subscription.setUser(user);
            return subscription;
        }

        return null;
    }
}
