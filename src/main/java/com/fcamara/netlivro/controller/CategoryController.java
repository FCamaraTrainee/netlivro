package com.fcamara.netlivro.controller;

import com.fcamara.netlivro.model.Category;
import com.fcamara.netlivro.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/find/{categoryId}")
    public ResponseEntity<Category> findCategoryById(@PathVariable UUID categoryId) {
        Category category = categoryService.findCategoryById(categoryId);
        return ResponseEntity.ok(category);
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<Category>> findAllCategory() {
        List<Category> allCategories = categoryService.findAll();

        return ResponseEntity.ok(allCategories);
    }

    /**
     * Post request to create new category
     *
     * @param category
     * @return ResponseEntity<Category> with the new category
     */
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category result = categoryService.createCategory(category);

        return ResponseEntity.ok(result);
    }

    @PostMapping("/save-all")
    public ResponseEntity<List<Category>> createCategoryList(@RequestBody List<Category> categories) {
        List<Category> result = categoryService.saveAll(categories);

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID categoryId) {
        categoryService.deleteCategory(categoryId);

        return ResponseEntity.noContent().build();
    }
}
