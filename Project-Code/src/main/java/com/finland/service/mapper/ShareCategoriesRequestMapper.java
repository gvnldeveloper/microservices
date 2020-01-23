package com.finland.service.mapper;

import com.finland.controller.messages.ShareCategoriesRequest;
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
public class ShareCategoriesRequestMapper {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;
    @Autowired
    private SubscriptionService subscriptionService;

    public Subscription map(ShareCategoriesRequest shareCategoriesRequest) {

        Category category = categoryService.getCategoryByName(shareCategoriesRequest.getSubscribedCategory());
        User user = userService.findUser(shareCategoriesRequest.getEmail(), null);
        User customer = userService.findUser(shareCategoriesRequest.getCustomer(), null);
        if (!(category != null && user != null && customer != null)) {
            throw new RuntimeException("Invalid request parameters");
        }
        Subscription existingSubscription = subscriptionService.checkCategory(category, user, "existingSubscription");
        if (existingSubscription != null) {
            Subscription subscriptionCheck = subscriptionService.checkCategory(category, customer, null);
            if (subscriptionCheck == null) {
                Subscription subscription = new Subscription();
                subscription.setSubscribed(true);
                subscription.setLocalDate(LocalDate.now());
                subscription.setCategory(category);
                subscription.setUser(customer);
                return subscription;
            } else if (!subscriptionCheck.isSubscribed()) {
                subscriptionService.updateSubscription(subscriptionCheck);
                return subscriptionCheck;
            }
        }
        return null;
    }
}
