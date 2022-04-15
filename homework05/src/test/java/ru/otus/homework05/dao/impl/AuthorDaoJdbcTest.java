package ru.otus.homework05.dao.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import ru.otus.homework05.dao.AuthorDao;
import ru.otus.homework05.dao.BookDao;
import ru.otus.homework05.domain.Author;
import ru.otus.homework05.exception.LibraryException;

import java.util.List;

@JdbcTest
@Import(AuthorDaoJdbc.class)
class AuthorDaoJdbcTest {
    private static final long EXISTING_SIZE_LIST = 1;
    private static final long EXISTING_ID = 1;
    private static final String EXISTING_VALUE = "JACK LONDON";
    private final AuthorDao authorDao;

    @Autowired
    public AuthorDaoJdbcTest(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Test
    void insertByName() {
        String expectedValue = "Vasya-authur";
        long id = authorDao.insertByName(expectedValue);
        Author author = authorDao.getById(id);
        String actualValue = author.getName();
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void getAll() {
        long expectedValue = EXISTING_SIZE_LIST;
        List<Author> authors = authorDao.getAll();
        long actualValue = authors.size();
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void getById() {
        Author expectedValue = new Author(EXISTING_ID, EXISTING_VALUE);
        Author actualValue = authorDao.getById(EXISTING_ID);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void existById() {
        boolean expectedValue = true;
        boolean actualValue = authorDao.existById(EXISTING_ID);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void update() {
        String newName = "JACK LONDON JR";
        Author expectedValue = new Author(EXISTING_ID, newName);
        authorDao.update(expectedValue);
        Author actualValue = authorDao.getById(EXISTING_ID);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void delete() {
        String name = "Vasya-authur";
        long id = authorDao.insertByName(name);
        Author author = new Author(id, name);

        authorDao.delete(author);
        
        boolean expectedValue = false;
        boolean actualValue = authorDao.existById(id);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void delete_exeptionDeleteAuthor() {
        Author author = new Author(EXISTING_ID, EXISTING_VALUE);

        assertThrows(LibraryException.class, () -> authorDao.delete(author));
    }
}