package ru.otus.homework06.repository.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import ru.otus.homework06.model.Author;
import ru.otus.homework06.model.Book;
import ru.otus.homework06.model.Genre;
import ru.otus.homework06.repository.AuthorRepository;
import ru.otus.homework06.repository.BookRepository;
import ru.otus.homework06.repository.GenreRepository;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@DataJpaTest
@Import({BookRepositoryJpa.class})
class BookRepositoryJpaTest {
    private static final long EXISTING_SIZE_LIST = 2L;
    private static final long SOME_EXISTING_ID = 1L;
    private static final String SOME_EXISTING_NAME = "Martin Eden";
    private static final long SOME_EXISTING_AUTHOR_ID = 1L;
    private static final String SOME_EXISTING_AUTHOR_NAME = "JACK LONDON";
    private static final long SOME_EXISTING_GENRE_ID = 1L;
    private static final String SOME_EXISTING_GENRE_NAME = "Adventure literature";

    @Autowired
    private TestEntityManager em;
    @Autowired
    private BookRepository bookRepository;

    @Test
    void saveNewValue() {
        String name = "New Original book";
        Author author = new Author(SOME_EXISTING_AUTHOR_ID, SOME_EXISTING_AUTHOR_NAME);
        Genre genre = new Genre(SOME_EXISTING_GENRE_ID, SOME_EXISTING_GENRE_NAME);
        Book expectedValue = new Book(0,name,author, genre);
        expectedValue = bookRepository.save(expectedValue);
        em.flush();
        long id = expectedValue.getId();

        Book actualValue = bookRepository.findById(id).get();
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void saveExistingValue() {
        Author author = new Author(SOME_EXISTING_AUTHOR_ID, SOME_EXISTING_AUTHOR_NAME);
        Genre genre = new Genre(SOME_EXISTING_GENRE_ID, SOME_EXISTING_GENRE_NAME);

        String newName = SOME_EXISTING_NAME + " NEW";
        Book expectedValue = new Book(SOME_EXISTING_ID, newName, author, genre);
        expectedValue = bookRepository.save(expectedValue);
        em.flush();
        long id = expectedValue.getId();

        Book actualValue = bookRepository.findById(SOME_EXISTING_ID).get();
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void findAll() {
        long expectedValue = EXISTING_SIZE_LIST;
        List<Book> books = bookRepository.findAll();
        long actualValue = books.size();
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void findById() {
        Author author = new Author(SOME_EXISTING_AUTHOR_ID, SOME_EXISTING_AUTHOR_NAME);
        Genre genre = new Genre(SOME_EXISTING_GENRE_ID, SOME_EXISTING_GENRE_NAME);
        Book expectedValue = new Book(SOME_EXISTING_ID, SOME_EXISTING_NAME, author, genre);

        Book actualValue = bookRepository.findById(SOME_EXISTING_ID).get();
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void existById() {
        boolean expectedValue = true;
        boolean actualValue = bookRepository.existById(SOME_EXISTING_ID);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void deleteById() {
        bookRepository.deleteById(SOME_EXISTING_ID);

        boolean expectedValue = false;
        boolean actualValue = bookRepository.existById(SOME_EXISTING_ID);
        assertEquals(expectedValue, actualValue);
    }
}