package com.fcamara.netlivro.service;

import com.fcamara.netlivro.model.Author;
import com.fcamara.netlivro.model.Book;
import com.fcamara.netlivro.model.Category;

import java.util.List;
import java.util.UUID;

public interface BookService {
    Book findBookById(UUID id);
    List<Book> findByAuthor(Author author);
    List<Book> findByCategory (Category category);
    List<Book> findByYearRange (Integer startYear, Integer endYear);
    Book createBook(Book book);
    void deleteBook(UUID id);
    List<Book> findAll();
}