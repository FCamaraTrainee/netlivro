package com.fcamara.netlivro.service;

import com.fcamara.netlivro.model.Book;
import com.fcamara.netlivro.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
@Service
public class BookServiceImplementation implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImplementation(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book findBookById(UUID id) {
        return bookRepository.findByIdAndActiveFalse(id).orElseThrow(() -> new RuntimeException("Book not found!"));

    }

    @Override
    public List<Book> findByAuthorId(UUID authorId) {
        List<Book> book = bookRepository.findByAuthorId(authorId);
        return filterBookList(book);
    }

    @Override
    public List<Book> findByYearRange(Integer startYear, Integer endYear) {
        List<Book> book = bookRepository.findAllByYearIsBetween(startYear, endYear);
        return filterBookList(book);
    }

    @Override
    public List<Book> findByCategoriesName(String category) {
        List<Book> book = bookRepository.findByCategoriesNameContaining(category);
        return filterBookList(book);
    }

    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(UUID id) {
        Book book = findBookById(id);
        book.setActive(false);
        bookRepository.save(book);
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = bookRepository.findAll();
        books.sort(Comparator.comparing(Book::getName));
        return filterBookList(books);
    }

    private List<Book> filterBookList(List<Book> bookList) {
        return bookList.stream().filter(book -> book.getAuthor().getActive()).collect(Collectors.toList());
    }

}
