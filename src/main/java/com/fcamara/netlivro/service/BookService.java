package com.fcamara.netlivro.service;

import com.fcamara.netlivro.model.Book;

import java.util.List;
import java.util.UUID;

public interface BookService {
    Book findBookById(UUID id);
    List<Book> findByAuthorId (UUID authorId);
    List<Book> findByCategoriesName(String category);
    List<Book> findByYearRange (Integer startYear, Integer endYear);
    Book createBook(Book book);
    void deleteBook(UUID id);
    List<Book> findAll();
}