package com.fcamara.netlivro.service;

import com.fcamara.netlivro.model.Author;

import java.util.List;
import java.util.UUID;

public interface AuthorService {
  Author findAuthorById(UUID id);
  Author saveAuthor(Author author);
  void deleteAuthor(UUID id);
  List<Author> findAllAndActiveTrue();
}
