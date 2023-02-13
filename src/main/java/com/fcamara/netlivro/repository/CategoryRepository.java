package com.fcamara.netlivro.repository;

import com.fcamara.netlivro.model.Author;
import com.fcamara.netlivro.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
