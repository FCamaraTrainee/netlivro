package com.fcamara.netlivro.repository;

import com.fcamara.netlivro.model.Author;
import com.fcamara.netlivro.model.Book;
import com.fcamara.netlivro.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {
    Optional<Book> findByIdAndActiveFalse(UUID uuid);
    List<Book> findByAuthor(Author author);
    List<Book> findByCategoriesContaining(Category category);
    @Query("SELECT Book FROM Book WHERE Book.year >= :startYear AND Book.year <= :endYear")
    List<Book> findAllByYearIsBetween(Integer startYear, Integer endYear);
}
