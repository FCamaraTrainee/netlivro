package com.fcamara.netlivro.service;

import com.fcamara.netlivro.model.Author;
import com.fcamara.netlivro.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class AuthorServiceImplementation implements AuthorService {
  private final AuthorRepository authorRepository;

  public AuthorServiceImplementation(AuthorRepository authorRepository) {
    this.authorRepository = authorRepository;
  }

  @Override
  public Author findAuthorById(UUID id) {
    Optional<Author> author = authorRepository.findByIdAndActiveTrue(id);
    if(!author.isPresent()) {
      throw new IllegalArgumentException("Author not found");
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
    Author author = findAuthorById(id);
    author.setActive(false);
    authorRepository.save(author);
  }

  @Override
  public List<Author> findAllAndActiveTrue() {
    List<Author> authors = authorRepository.findAllByActiveIsTrue();
    authors.sort(Comparator.comparing(Author::getName));
    return authors;
  }
}
