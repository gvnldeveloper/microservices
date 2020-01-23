package com.finland.service;

import com.finland.controller.messages.CategoryResponse;
import com.finland.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> getCategories();

    Optional<Category> getCategoryById(int categoryId);

    CategoryResponse getCategory(int userId);

    Category getCategoryByName(String categoryName);

    CategoryResponse getCategoryData(String email);

}
