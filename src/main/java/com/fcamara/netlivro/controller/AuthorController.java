package com.fcamara.netlivro.controller;

import com.fcamara.netlivro.exception.IllegalAuthorException;
import com.fcamara.netlivro.model.Author;
import com.fcamara.netlivro.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/author")
@RequiredArgsConstructor
public class AuthorController {
  private final AuthorService authorService;

  @PostMapping("/save-author")
  public ResponseEntity<Author> createAuthor(@RequestBody Author author) {

    if (author.getName() == null) throw new IllegalAuthorException("Name must not be null");

    Author response = authorService.saveAuthor(author);
    return ResponseEntity.ok(response);
  }

  @PutMapping("/edit-author")
  public ResponseEntity<Author> editAuthor(@RequestBody Author author) {

    if (author.getId() == null) throw new IllegalAuthorException("Id must not be null");

    Author response = authorService.saveAuthor(author);
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/delete-author/{authorId}")
  public ResponseEntity<Void> deleteAuthor(@PathVariable UUID authorId) {
    authorService.deleteAuthor(authorId);
    return ResponseEntity.noContent().build();
  }


  @GetMapping("/{authorId}")
  public ResponseEntity<Author> findAuthorById(@PathVariable UUID authorId) {
    Author author = authorService.findAuthorById(authorId);
    return ResponseEntity.ok(author);
  }

  @GetMapping("/find-all")
  public ResponseEntity<List<Author>> findAllAuthor() {
    List<Author> allAuthors = authorService.findAllAndActiveTrue();
    return ResponseEntity.ok(allAuthors);
  }
}
