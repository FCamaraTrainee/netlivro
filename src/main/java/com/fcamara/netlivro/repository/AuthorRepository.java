package com.fcamara.netlivro.repository;

import com.fcamara.netlivro.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthorRepository extends JpaRepository<Author, UUID> {
  Optional<Author> findByIdAndActiveFalse(UUID uuid);
  List<Author> findAllByActiveIsFalse();
}
