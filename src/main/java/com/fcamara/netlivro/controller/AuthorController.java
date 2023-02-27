package com.fcamara.netlivro.controller;

import com.fcamara.netlivro.model.Author;
import com.fcamara.netlivro.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(name = "/v1/api/author")
@RequiredArgsConstructor
public class AuthorController {
  private final AuthorService authorService;

  @PostMapping("/save-author")
  private ResponseEntity<Author> createAuthor(@RequestBody String author) {
    Author response = authorService.createAuthor(author);
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/delete-author")
  private ResponseEntity<Void> deleteAuthor(@PathVariable UUID authorId) {
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
    List<Author> allAuthors = authorService.findAllAndActiveFalse();
    return ResponseEntity.ok(allAuthors);
  }
}
