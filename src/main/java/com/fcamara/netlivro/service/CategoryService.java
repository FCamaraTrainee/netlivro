package com.fcamara.netlivro.service;

import com.fcamara.netlivro.model.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    Category findCategoryById(UUID id);
    Category createCategory(Category category);
    void deleteCategory(UUID id);
    List<Category> findAll();
    List<Category> saveAll(List<Category> categories);
}
