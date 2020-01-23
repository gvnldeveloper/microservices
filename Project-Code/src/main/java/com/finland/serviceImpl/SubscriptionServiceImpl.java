package com.finland.serviceImpl;

import com.finland.controller.messages.CommonResponse;
import com.finland.controller.messages.ShareCategoriesRequest;
import com.finland.controller.messages.SubscribeCategoriesRequest;
import com.finland.controller.messages.SubscribeCategoriesResponse;
import com.finland.dao.SubscriptionDao;
import com.finland.model.Category;
import com.finland.model.Subscription;
import com.finland.model.User;
import com.finland.service.SubscriptionService;
import com.finland.service.mapper.ShareCategoriesRequestMapper;
import com.finland.service.mapper.SubscribeCategoriesRequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.finland.controller.messages.CommonMessages.*;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private SubscriptionDao subscriptionDao;

    @Autowired
    private SubscribeCategoriesRequestMapper requestMapper;

    @Autowired
    private ShareCategoriesRequestMapper shareCategoriesRequestMapper;

    @Override
    public SubscribeCategoriesResponse subscribeCategories(SubscribeCategoriesRequest subscribeCategoriesRequest) {
        //Log request at  trace level
        //Auth token via WS call

        Subscription subscription = requestMapper.map(subscribeCategoriesRequest);
        //BL
        if (subscription != null) {
            subscriptionDao.subscribeCategories(subscription);
            return new SubscribeCategoriesResponse(SUCCESS, SUCCESS_MESSAGE);
        }
        return new SubscribeCategoriesResponse(DUPLICATE, DUPLICATE_MESSAGE);

    }

    @Override
    public CommonResponse shareCategories(ShareCategoriesRequest shareCategoriesRequest) {
        Subscription subscription = shareCategoriesRequestMapper.map(shareCategoriesRequest);

        //Log response
        if (subscription != null) {
            if (subscription.isSubscribed()) {
                subscriptionDao.subscribeCategories(subscription);
                return new CommonResponse(SUCCESS, SUCCESS_MESSAGE);
            } else {
                subscriptionDao.updateSubscription(subscription);
                return new CommonResponse(SUCCESS, SUCCESS_MESSAGE);
            }

        } else {
            return new CommonResponse(DUPLICATE, DUPLICATE_MESSAGE);
        }

    }

    @Override
    public void updateSubscription(Subscription subscription) {
        //Log trace
        subscriptionDao.updateSubscription(subscription);
    }

    @Override
    public Subscription checkCategory(Category categoryId, User userId, String mainSub) {
        //Log trace
        return subscriptionDao.checkCategory(categoryId, userId, mainSub);
    }
}
