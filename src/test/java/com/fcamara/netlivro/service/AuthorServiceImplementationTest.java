package com.fcamara.netlivro.service;

import com.fcamara.netlivro.model.Author;
import com.fcamara.netlivro.repository.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

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
    void ShouldCallMethodToLisAllAuthors() {
        authorService.findAllAndActiveTrue();
        Mockito.verify(authorRepository, Mockito.times(1)).findAllByActiveIsTrue();
    }

    @Test
    void ShouldCallMethodWithGivenArguments() {
        String authorName = "Nome";

        authorService.createAuthor(authorName);
        ArgumentCaptor<Author> argument = ArgumentCaptor.forClass(Author.class);

        Mockito.verify(authorRepository).save(argument.capture());

        assertEquals(authorName, argument.getValue().getName());
    }
}