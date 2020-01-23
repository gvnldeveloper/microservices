package com.finland.service.mapper;

import com.finland.controller.messages.SubscribedCategories;
import com.finland.model.Category;
import com.finland.model.Subscription;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class SubscribedCategoriesMapper {

    public List<SubscribedCategories> map(List<Subscription> subscriptions) {
        DateTimeFormatter formatter = null;
        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        List<SubscribedCategories> subscribedCategoriesList = new ArrayList<>();

        for (Subscription subscription : subscriptions) {
            Category category = subscription.getCategory();
            SubscribedCategories subscribedCategories = new SubscribedCategories();
            subscribedCategories.setName(category.getName());
            subscribedCategories.setPrice(category.getContent().stream().filter(o -> o.getPrice() != 0.0).mapToDouble(o -> o.getPrice()).sum());
            subscribedCategories.setRemainingContent(category.getContent().size());
            subscribedCategories.setStartDate((subscription.getLocalDate().format(formatter)));
            subscribedCategoriesList.add(subscribedCategories);
        }
        return subscribedCategoriesList;
    }

}
