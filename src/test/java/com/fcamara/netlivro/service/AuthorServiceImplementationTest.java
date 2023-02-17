package com.fcamara.netlivro.service;

import com.fcamara.netlivro.model.Author;
import com.fcamara.netlivro.repository.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class AuthorServiceImplementationTest {

    @Mock
    AuthorRepository authorRepository;
    @InjectMocks
    AuthorServiceImplementation authorService;


    @Test
    void ShouldThrowWhenGetEmptyAuthor() {
        Mockito.when(authorRepository.findByIdAndActiveTrue(Mockito.any()))
                .thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class,() -> authorService.findAuthorById(Mockito.any()));
    }

    @Test
    void ShouldSortList() {
        List<Author> authorList = Arrays.asList(
                new Author("zNome 1"),
                new Author("Nome 1")
        );
        Mockito.when(authorRepository.findAllByActiveIsTrue())
                .thenReturn(authorList);

    }

}