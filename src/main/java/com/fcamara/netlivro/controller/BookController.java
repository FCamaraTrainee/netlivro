package com.fcamara.netlivro.controller;

import com.fcamara.netlivro.exception.IllegalAuthorException;
import com.fcamara.netlivro.exception.IllegalBookException;
import com.fcamara.netlivro.model.Book;
import com.fcamara.netlivro.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping("/save-book")
    public ResponseEntity<Book> createBook(@RequestBody Book book){

        if (book.getName() == null) throw new IllegalBookException("Name cannot be null");

        Book response = bookService.createBook(book);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<Book>>getBookByAuthorId(@PathVariable("authorId") UUID authorId){

        List<Book> bookList = bookService.findByAuthorId(authorId);
        return ResponseEntity.ok(bookList);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Book>>getBookByCategoryId(@PathVariable("category") String category){

        List<Book> bookList = bookService.findByCategoriesName(category);
        return ResponseEntity.ok(bookList);
    }

    @GetMapping("/year-range")
    public ResponseEntity<List<Book>>getBookByYearRange(@RequestParam("min") int min, @RequestParam("max") int max){

        List<Book> bookList = bookService.findByYearRange(min, max);
        return ResponseEntity.ok(bookList);
    }

    @PutMapping("/edit-book")
    private ResponseEntity<Book> editBook(@RequestBody Book book) {

        if (book.getId() == null) throw new IllegalAuthorException("Id must not be null");

        Book response = bookService.createBook(book);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete-book/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") UUID id){

        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<Book>>findAll(){

        return ResponseEntity.ok(bookService.findAll());
    }



}
