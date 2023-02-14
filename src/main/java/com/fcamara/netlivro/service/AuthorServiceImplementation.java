package com.fcamara.netlivro.service;

import com.fcamara.netlivro.model.Author;
import com.fcamara.netlivro.repository.AuthorRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AuthorServiceImplementation implements AuthorService{
  private final AuthorRepository authorRepository;

  public AuthorServiceImplementation(AuthorRepository authorRepository) {
    this.authorRepository = authorRepository;
  }

  @Override
  public Author findAuthorById(UUID id) {
    Optional<Author> author = authorRepository.findByIdAndActiveFalse(id);
    if(!author.isPresent()) {
      throw new RuntimeException("Author not found");
    }
    return author.get();
  }

  @Override
  public Author createAuthor(String name) {
    Author author = new Author();
    author.setName(name);
    return authorRepository.save(author);
  }

  @Override
  public void deleteAuthor(UUID id) {
    Optional<Author> author = authorRepository.findById(id);
    if(!author.isPresent()) {
      throw new RuntimeException("Author not found");
    }
    author.ifPresent((author1) -> {
      author1.setActive(false);
      authorRepository.save(author1);
    });
  }

  @Override
  public List<Author> findAll() {
    List<Author> authors = authorRepository.findAll();
    authors.sort(Comparator.comparing(Author::getName));
    return authors;
  }
}