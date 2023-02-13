package com.fcamara.netlivro.repository;

import com.fcamara.netlivro.model.Author;
import com.fcamara.netlivro.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuthorRepository extends JpaRepository<Author, UUID> {
}
