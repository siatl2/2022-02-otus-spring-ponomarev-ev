package ru.otus.homework06.repository.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.homework06.model.Author;
import ru.otus.homework06.model.Book;
import ru.otus.homework06.model.Comment;
import ru.otus.homework06.model.Genre;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(CommentRepositoryJpa.class)
class CommentRepositoryJpaTest {
    private static final long EXISTING_SIZE_LIST = 1L;
    private static final long EXISTING_ID = 1L;
    private static final String EXISTING_VALUE = "COMMENT-1";

    private static final long SOME_EXISTING_BOOK_ID = 2L;
    private static final String SOME_EXISTING_BOOK_NAME = "The Little Lady";
    private static final long SOME_EXISTING_AUTHOR_ID = 1L;
    private static final String SOME_EXISTING_AUTHOR_NAME = "JACK LONDON";
    private static final long SOME_EXISTING_GENRE_ID = 1L;
    private static final String SOME_EXISTING_GENRE_NAME = "Adventure literature";
    @Autowired
    private CommentRepositoryJpa jpa;
    @Autowired
    private TestEntityManager em;

    @Test
    void saveNewValue() {
        String expectedValue = "new Comment";
        Author author = new Author(SOME_EXISTING_AUTHOR_ID, SOME_EXISTING_AUTHOR_NAME);
        Genre genre = new Genre(SOME_EXISTING_GENRE_ID, SOME_EXISTING_GENRE_NAME);
        Book book = new Book(SOME_EXISTING_BOOK_ID,SOME_EXISTING_BOOK_NAME,author, genre);

        Comment comment = new Comment(0L, expectedValue, book);
        comment = jpa.save(comment);
        em.flush();
        long id = comment.getId();

        Optional<Comment> actualComment = jpa.findById(id);
        String actualValue = actualComment.get().getName();
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void saveExistingValue() {
        String expectedValue = EXISTING_VALUE + "_NEW";
        Author author = new Author(SOME_EXISTING_AUTHOR_ID, SOME_EXISTING_AUTHOR_NAME);
        Genre genre = new Genre(SOME_EXISTING_GENRE_ID, SOME_EXISTING_GENRE_NAME);
        Book book = new Book(SOME_EXISTING_BOOK_ID,SOME_EXISTING_BOOK_NAME,author, genre);
        Comment comment = new Comment(EXISTING_ID, expectedValue, book);
        comment = jpa.save(comment);
        em.flush();
        long id = comment.getId();

        Optional<Comment> actualComment = jpa.findById(id);
        String actualValue = actualComment.get().getName();
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void findAll() {
        long expectedValue = EXISTING_SIZE_LIST;
        List<Comment> comments = jpa.findAllByBookId(SOME_EXISTING_BOOK_ID);
        long actualValue = comments.size();
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void findById() {
        Author author = new Author(SOME_EXISTING_AUTHOR_ID, SOME_EXISTING_AUTHOR_NAME);
        Genre genre = new Genre(SOME_EXISTING_GENRE_ID, SOME_EXISTING_GENRE_NAME);
        Book book = new Book(SOME_EXISTING_BOOK_ID,SOME_EXISTING_BOOK_NAME,author, genre);
        Comment expectedValue = new Comment(EXISTING_ID, EXISTING_VALUE, book);
        Comment actualValue = jpa.findById(EXISTING_ID).get();
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
        String name = EXISTING_VALUE + "_NEW";
        Author author = new Author(SOME_EXISTING_AUTHOR_ID, SOME_EXISTING_AUTHOR_NAME);
        Genre genre = new Genre(SOME_EXISTING_GENRE_ID, SOME_EXISTING_GENRE_NAME);
        Book book = new Book(SOME_EXISTING_BOOK_ID,SOME_EXISTING_BOOK_NAME,author, genre);
        Comment comment = new Comment(EXISTING_ID, EXISTING_VALUE, book);
        comment = jpa.save(comment);
        em.flush();
        long id = comment.getId();

        jpa.deleteById(id);

        boolean expectedValue = false;
        boolean actualValue = jpa.existById(id);
        assertEquals(expectedValue, actualValue);
    }
}