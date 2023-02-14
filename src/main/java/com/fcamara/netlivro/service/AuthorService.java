package com.fcamara.netlivro.service;

import com.fcamara.netlivro.model.Author;

import java.util.List;
import java.util.UUID;

public interface AuthorService {
  Author findAuthorById(UUID id);
  Author createAuthor(String name);
  void deleteAuthor(UUID id);
  List<Author> findAll();
}
