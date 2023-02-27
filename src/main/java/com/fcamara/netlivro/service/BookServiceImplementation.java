package com.fcamara.netlivro.service;

import com.fcamara.netlivro.model.Author;
import com.fcamara.netlivro.model.Book;
import com.fcamara.netlivro.model.Category;
import com.fcamara.netlivro.repository.BookRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    public List<Book> findByAuthor(Author author) {
        List<Book> book = bookRepository.findByAuthor(author);
        return filterBookList(book);
    }

    @Override
    public List<Book> findByYearRange(Integer startYear, Integer endYear) {
        List<Book> book = bookRepository.findAllByYearIsBetween(startYear, endYear);
        return filterBookList(book);
    }

    @Override
    public List<Book> findByCategory(Category category) {
        List<Book> book = bookRepository.findByCategoriesContaining(category);
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
