package com.fcamara.netlivro.service;

import com.fcamara.netlivro.model.Category;
import com.fcamara.netlivro.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class CategoryServiceImplementation implements CategoryService {

    private final CategoryRepository repository;


    public CategoryServiceImplementation(CategoryRepository repository) {
        this.repository = repository;
    }


    @Override
    public Category findCategoryById(UUID id) {
        Optional<Category> optionalCategory = repository.findById(id);

        if (!optionalCategory.isPresent()) {

            throw new RuntimeException("Cannot find category");

        } else {
            return optionalCategory.get();
        }
    }

    @Override
    public Category createCategory(Category category) {
        return repository.save(category);
    }

    @Override
    public void deleteCategory(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public List<Category> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Category> saveAll(List<Category> categories) {
        return repository.saveAll(categories);
    }
}
