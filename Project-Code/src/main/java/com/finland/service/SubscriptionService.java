package com.finland.service;

import com.finland.controller.messages.CommonResponse;
import com.finland.controller.messages.ShareCategoriesRequest;
import com.finland.controller.messages.SubscribeCategoriesRequest;
import com.finland.controller.messages.SubscribeCategoriesResponse;
import com.finland.model.Category;
import com.finland.model.Subscription;
import com.finland.model.User;

public interface SubscriptionService {

    SubscribeCategoriesResponse subscribeCategories(SubscribeCategoriesRequest subscribeCategoriesRequest);

    CommonResponse shareCategories(ShareCategoriesRequest shareCategoriesRequest);

    void updateSubscription(Subscription subscription);

    Subscription checkCategory(Category categoryId, User userId , String mainSub);
}
