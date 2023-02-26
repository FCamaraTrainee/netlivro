package com.fcamara.netlivro.repository;

import com.fcamara.netlivro.model.Author;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql("/data.sql")
class AuthorRepositoryTest {

    @Autowired
    AuthorRepository authorRepository;

    @Test
    void ShouldGetAllActiveAuthors() {
        List<Author> authorList = authorRepository.findAllByActiveIsTrue();
        assertEquals(7, authorList.size());
    }

    @Test
    void ShoulGetAnAuthorWithActiveTrue() {
        UUID id = UUID.fromString("03f8763b-2298-405f-8f91-ee78751e1120");
        Optional<Author> author = authorRepository.findByIdAndActiveTrue(id);

        assertTrue(author.isPresent());
        assertTrue(author.get().getActive());
    }
    @Test
    void ShoulNotGetAnAuthorWithActiveFalse() {
        UUID id = UUID.fromString("1d6486cd-4953-4da7-8ef5-ac2f4010e872");
        Optional<Author> author = authorRepository.findByIdAndActiveTrue(id);

        assertFalse(author.isPresent());
    }
}