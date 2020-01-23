package com.finland.service.mapper;

import com.finland.controller.messages.AvailableCategories;
import com.finland.controller.messages.CategoryResponse;
import com.finland.model.Category;
import com.finland.model.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryResponseMapper {

    @Autowired
    private SubscribedCategoriesMapper subscribedCategoriesMapper;

    public CategoryResponse map(List<Subscription> subscription, List<Category> categories) {
        if (subscription == null) {
            return null;
        }
        CategoryResponse categoryResponse = new CategoryResponse();
        List<AvailableCategories> availableCategoriesList = new ArrayList<>();
        AvailableCategories availableCategories = null;
        //
        for (Category category : categories) {
            availableCategories = new AvailableCategories();
            availableCategories.setName(category.getName());
            availableCategories.setAvailableContent(category.getContent().size());
            availableCategories.setPrice(category.getContent().stream().filter(o -> o.getPrice() != 0.0).mapToDouble(o -> o.getPrice()).sum());
            availableCategoriesList.add(availableCategories);
        }
        categoryResponse.setAvailableCategories(availableCategoriesList);


        categoryResponse.setSubscribedCategories(subscribedCategoriesMapper.map(subscription));
        return categoryResponse;
    }
}
