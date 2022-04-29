package ru.otus.homework06.repository.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.homework06.model.Author;
import ru.otus.homework06.model.Genre;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(GenreRepositoryJpa.class)
class GenreRepositoryJpaTest {
    private static final long EXISTING_SIZE_LIST = 1L;
    private static final long EXISTING_ID = 1L;
    private static final String EXISTING_VALUE = "Adventure literature";
    private final GenreRepositoryJpa jpa;
    private final TestEntityManager em;

    @Autowired
    public GenreRepositoryJpaTest(GenreRepositoryJpa jpa
            , TestEntityManager em) {
        this.jpa = jpa;
        this.em = em;
    }

    @Test
    void save_newValue() {
        String expectedValue = "New genre";
        Genre genre = new Genre(0, expectedValue);
        genre = jpa.save(genre);
        em.flush();
        long id = genre.getId();

        Optional<Genre> actualGenre = jpa.findById(id);
        String actualValue = actualGenre.get().getName();
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void save_exisitingValue() {
        String newName = EXISTING_VALUE + "_NEW";
        Genre expectedValue = new Genre(EXISTING_ID, newName);
        expectedValue = jpa.save(expectedValue);
        Genre actualValue = jpa.findById(EXISTING_ID).get();
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void findAll() {
        long expectedValue = EXISTING_SIZE_LIST;
        List<Genre> genres = jpa.findAll();
        long actualValue = genres.size();
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void findById() {
        Genre expectedValue = new Genre(EXISTING_ID, EXISTING_VALUE);
        Genre actualValue = jpa.findById(EXISTING_ID).get();
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
        String name = "New genre";
        Genre genre = new Genre(0L, name);
        genre = jpa.save(genre);
        em.flush();
        long id = genre.getId();

        jpa.deleteById(id);

        boolean expectedValue = false;
        boolean actualValue = jpa.existById(id);
        assertEquals(expectedValue, actualValue);
    }
}