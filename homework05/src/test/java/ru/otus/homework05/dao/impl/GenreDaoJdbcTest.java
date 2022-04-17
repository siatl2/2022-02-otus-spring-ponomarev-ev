package ru.otus.homework05.dao.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import ru.otus.homework05.dao.BookDao;
import ru.otus.homework05.dao.GenreDao;
import ru.otus.homework05.domain.Genre;
import ru.otus.homework05.exception.LibraryException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@JdbcTest
@Import(GenreDaoJdbc.class)
public class GenreDaoJdbcTest {
    private static final long EXISTING_SIZE_LIST = 1;
    private static final long EXISTING_ID = 1;
    private static final String EXISTING_VALUE = "Adventure literature";
    private final GenreDao genreDao;

    @Autowired
    public GenreDaoJdbcTest(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @MockBean
    private BookDao bookDao;

    @Test
    void insertByName() {
        String expectedValue = "new Genre";
        Genre genre = new Genre(expectedValue);
        genreDao.insert(genre);
        long id = genre.getId();

        genre = genreDao.getById(id);
        String actualValue = genre.getName();
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void getAll() {
        long expectedValue = EXISTING_SIZE_LIST;
        List<Genre> genres = genreDao.getAll();
        long actualValue = genres.size();
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void getById() {
        Genre expectedValue = new Genre(EXISTING_ID, EXISTING_VALUE);
        Genre actualValue = genreDao.getById(EXISTING_ID);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void existById() {
        boolean expectedValue = true;
        boolean actualValue = genreDao.existById(EXISTING_ID);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void update() {
        String newName = "Romantic literature";
        Genre expectedValue = new Genre(EXISTING_ID, newName);
        genreDao.update(expectedValue);
        Genre actualValue = genreDao.getById(EXISTING_ID);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void delete() {
        String name = "Something literature";
        Genre genre = new Genre(name);
        genreDao.insert(genre);
        long id= genre.getId();

        genreDao.deleteById(id);

        boolean expectedValue = false;
        boolean actualValue = genreDao.existById(id);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void delete_exeptionDeleteGenry() {
        assertThrows(LibraryException.class, () -> genreDao.deleteById(EXISTING_ID));
    }    
    
}
