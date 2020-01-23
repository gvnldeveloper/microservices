package com.finland.dao;

import com.finland.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryDao {

    List<Category> getCategories();

    Optional<Category> getCategory(int id);

    List<Category> getAvailableCategory(List<Category> categoryList);

    Category getCategoryByName(String categoryName);
}
