package com.finland.dao;

import com.finland.model.Category;
import com.finland.model.Subscription;
import com.finland.model.User;

import java.util.List;

public interface SubscriptionDao {

    Subscription subscribeCategories(Subscription subscription);

    void updateSubscription(Subscription subscription);

    List<Subscription> getUserSubscription(int userId);

    Subscription checkCategory(Category categoryId, User userId , String mainSub);

    void subscriptionJob();
}
