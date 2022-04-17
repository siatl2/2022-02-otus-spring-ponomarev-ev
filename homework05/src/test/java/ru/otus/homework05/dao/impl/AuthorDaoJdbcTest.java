package ru.otus.homework05.dao.impl;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.homework05.dao.AuthorDao;
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
    void insert() {
        String expectedValue = "Vasya-authur";
        Author author = new Author(expectedValue);
        authorDao.insert(author);
        long id = author.getId();

        System.out.println("2.id=" + id);
        author = authorDao.getById(id);
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
        Author author = new Author(name);
        authorDao.insert(author);
        long id = author.getId();

        authorDao.deleteById(id);
        
        boolean expectedValue = false;
        boolean actualValue = authorDao.existById(id);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void delete_exeptionDeleteAuthor() {
        assertThrows(LibraryException.class, () -> authorDao.deleteById(EXISTING_ID));
    }
}