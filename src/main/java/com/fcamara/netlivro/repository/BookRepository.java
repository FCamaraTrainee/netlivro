package com.fcamara.netlivro.repository;

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
    List<Book> findByAuthorId(UUID authorId);
    List<Book> findByCategoriesNameContaining(String category);
    @Query("SELECT b FROM Book b WHERE b.year >= :startYear AND b.year <= :endYear")
    List<Book> findAllByYearIsBetween(Integer startYear, Integer endYear);
}
