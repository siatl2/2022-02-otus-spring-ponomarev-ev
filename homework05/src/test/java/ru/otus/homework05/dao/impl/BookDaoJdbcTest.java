package ru.otus.homework05.dao.impl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import ru.otus.homework05.dao.AuthorDao;
import ru.otus.homework05.dao.BookDao;
import ru.otus.homework05.dao.GenreDao;
import ru.otus.homework05.domain.Author;
import ru.otus.homework05.domain.Book;
import ru.otus.homework05.domain.Genre;
import ru.otus.homework05.exception.LibraryException;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@JdbcTest
@Import({BookDaoJdbc.class})
class BookDaoJdbcTest {
    private static final long EXISTING_SIZE_LIST = 2L;
    private static final long SOME_EXISTING_ID = 1L;
    private static final String SOME_EXISTING_NAME = "Martin Eden";
    private static final long SOME_EXISTING_AUTHOR_ID = 1L;
    private static final String SOME_EXISTING_AUTHOR_NAME = "JACK LONDON";
    private static final long SOME_EXISTING_GENRE_ID = 1L;
    private static final String SOME_EXISTING_GENRE_NAME = "Adventure literature";

    private final BookDao bookDao;

    @MockBean
    private AuthorDao authorDao;

    @MockBean
    private GenreDao genreDao;

    @Autowired
    public BookDaoJdbcTest(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Test
    void insertByName() {
        when(authorDao.existById(anyLong())).thenReturn(true);
        when(genreDao.existById(anyLong())).thenReturn(true);

        String name = "New Original book";
        Author author = new Author(SOME_EXISTING_AUTHOR_ID, SOME_EXISTING_AUTHOR_NAME);
        Genre genre = new Genre(SOME_EXISTING_GENRE_ID, SOME_EXISTING_GENRE_NAME);
        Book expectedValue = new Book(name,author, genre);
        bookDao.insert(expectedValue);
        long id = expectedValue.getId();

        when(authorDao.getById(anyLong())).thenReturn(author);
        when(genreDao.getById(anyLong())).thenReturn(genre);

        Book actualValue = bookDao.getById(id);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void insertByName_exceptionAuthorNotExist() {
        when(authorDao.existById(anyLong())).thenReturn(false);
        when(genreDao.existById(anyLong())).thenReturn(true);

        String name = "New Original book";
        Author author = new Author(SOME_EXISTING_AUTHOR_ID, SOME_EXISTING_AUTHOR_NAME);
        Genre genre = new Genre(SOME_EXISTING_GENRE_ID, SOME_EXISTING_GENRE_NAME);
        Book book = new Book(name, author, genre);

        assertThrows(LibraryException.class
                , () -> bookDao.insert(book));
    }

    @Test
    void insertByName_exceptionGenreNotExist() {
        when(authorDao.existById(anyLong())).thenReturn(true);
        when(genreDao.existById(anyLong())).thenReturn(false);

        String name = "New Original book";
        Author author = new Author(SOME_EXISTING_AUTHOR_ID, SOME_EXISTING_AUTHOR_NAME);
        Genre genre = new Genre(SOME_EXISTING_GENRE_ID, SOME_EXISTING_GENRE_NAME);
        Book book = new Book(name, author, genre);

        assertThrows(LibraryException.class
                , () -> bookDao.insert(book));
    }

    @Test
    void getAll() {
        long expectedValue = EXISTING_SIZE_LIST;
        List<Book> books = bookDao.getAll();
        long actualValue = books.size();
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void getById() {
        Author author = new Author(SOME_EXISTING_AUTHOR_ID, SOME_EXISTING_AUTHOR_NAME);
        Genre genre = new Genre(SOME_EXISTING_GENRE_ID, SOME_EXISTING_GENRE_NAME);
        Book expectedValue = new Book(SOME_EXISTING_ID, SOME_EXISTING_NAME, author, genre);

        when(authorDao.getById(anyLong())).thenReturn(author);
        when(genreDao.getById(anyLong())).thenReturn(genre);

        Book actualValue = bookDao.getById(SOME_EXISTING_ID);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void existById() {
        boolean expectedValue = true;
        boolean actualValue = bookDao.existById(SOME_EXISTING_ID);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void update() {
        Author author = new Author(SOME_EXISTING_AUTHOR_ID, SOME_EXISTING_AUTHOR_NAME);
        Genre genre = new Genre(SOME_EXISTING_GENRE_ID, SOME_EXISTING_GENRE_NAME);

        String newName = SOME_EXISTING_NAME + " NEW";
        Book expectedValue = new Book(SOME_EXISTING_ID, newName, author, genre);
        bookDao.update(expectedValue);

        when(authorDao.getById(anyLong())).thenReturn(author);
        when(genreDao.getById(anyLong())).thenReturn(genre);

        Book actualValue = bookDao.getById(SOME_EXISTING_ID);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void delete() {
        bookDao.deleteById(SOME_EXISTING_ID);

        boolean expectedValue = false;
        boolean actualValue = bookDao.existById(SOME_EXISTING_ID);
        assertEquals(expectedValue, actualValue);
    }
}
