package ru.otus.homework06.repository.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import ru.otus.homework06.model.Author;
import ru.otus.homework06.service.impl.AuthorOutputImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(AuthorRepositoryJpa.class)
class AuthorRepositoryJpaTest {
    private static final long EXISTING_SIZE_LIST = 1L;
    private static final long EXISTING_ID = 1L;
    private static final String EXISTING_VALUE = "JACK LONDON";
    private final AuthorRepositoryJpa jpa;
    private final TestEntityManager em;

    @Autowired
    public AuthorRepositoryJpaTest(AuthorRepositoryJpa jpa
                                    , TestEntityManager em) {
        this.jpa = jpa;
        this.em = em;
    }
    @Test
    void save_newValue() {
        String expectedValue = "Vasya-authur";
        Author author = new Author(0, expectedValue);
        author = jpa.save(author);
        em.flush();
        long id = author.getId();

        Optional<Author> actualAuthor = jpa.findById(id);
        String actualValue = actualAuthor.get().getName();
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void save_exisitingValue() {
        String newName = "JACK LONDON JR";
        Author expectedValue = new Author(EXISTING_ID, newName);
        expectedValue = jpa.save(expectedValue);
        Author actualValue = jpa.findById(EXISTING_ID).get();
        assertEquals(expectedValue, actualValue);
    }
    @Test
    void findAll() {
        long expectedValue = EXISTING_SIZE_LIST;
        List<Author> authors = jpa.findAll();
        long actualValue = authors.size();
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void findById() {
        Author expectedValue = new Author(EXISTING_ID, EXISTING_VALUE);
        Author actualValue = jpa.findById(EXISTING_ID).get();
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void existById() {
        boolean expectedValue = true;
        boolean actualValue = jpa.existById(EXISTING_ID);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void deleteById() {
        String name = "Vasya-authur";
        Author author = new Author(0, name);
        author = jpa.save(author);
        em.flush();
        long id = author.getId();

        jpa.deleteById(id);

        boolean expectedValue = false;
        boolean actualValue = jpa.existById(id);
        assertEquals(expectedValue, actualValue);
    }
}