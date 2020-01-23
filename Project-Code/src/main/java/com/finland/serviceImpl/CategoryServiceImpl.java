package com.finland.serviceImpl;

import com.finland.controller.messages.CategoryResponse;
import com.finland.dao.CategoryDao;
import com.finland.dao.SubscriptionDao;
import com.finland.dao.UserDao;
import com.finland.model.Category;
import com.finland.model.Subscription;
import com.finland.model.User;
import com.finland.service.CategoryService;
import com.finland.service.mapper.CategoryResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private SubscriptionDao subscriptionDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private CategoryResponseMapper categoryResponseMapper;

    @Override
    public List<Category> getCategories() {
        return categoryDao.getCategories();
    }

    @Override
    public Optional<Category> getCategoryById(int categoryId) {
        //Log request at  trace level
        //Auth token via WS call
        return categoryDao.getCategory(categoryId);
    }

    @Override
    public CategoryResponse getCategory(int userId) {
        //Log request at  trace level
        //Auth token via WS call

        //BL
        List<Subscription> subscriptions = subscriptionDao.getUserSubscription(userId);
        List<Category> categoryList = getAvailableCategory(subscriptions);
        List<Category> availableCategories = categoryDao.getAvailableCategory(categoryList);
        //Log response

        return categoryResponseMapper.map(subscriptions, availableCategories);
    }

    @Override
    public CategoryResponse getCategoryData(String email) {
        //Log request at  trace level
        //Auth token via WS call
        if (email == null) {
            throw new RuntimeException("Invalid request parameter");
        }

        User user = userDao.findUser(email, null);
        return getCategory(user.getId());
    }

    @Override
    public Category getCategoryByName(String categoryName) {
        //Log request at  trace level
        //Auth token via WS call
        return categoryDao.getCategoryByName(categoryName);
    }

    private List<Category> getAvailableCategory(List<Subscription> subscriptions) {
        List<Category> categoryList = new ArrayList<>();
        for (Subscription subscription : subscriptions) {
            categoryList.add(subscription.getCategory());
        }
        return categoryList;
    }

}
