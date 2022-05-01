package ru.otus.homework07.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.homework07.component.impl.IOServiceStream;
import ru.otus.homework07.model.Author;
import ru.otus.homework07.model.Book;
import ru.otus.homework07.model.Genre;
import ru.otus.homework07.service.BookOutput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;

@SpringBootTest(classes = {BookOutputImpl.class})
class BookOutputImplTest {
    private static final String EXPECTED_BOOK = "Book with id=1, name=Boring book";
    private static final List<String> EXPECTED_BOOKS = Arrays.asList(
            "List books:"
            , "  id  | Name                         | Author_id | Genre_id |"
            , " -----|------------------------------|-----------|----------|"
            , "     1|Boring book                   |          1|         1|"
            , "     2|Other Boring book             |          1|         1|"
            , " ------------------------------------------------------------"
    );

    @Autowired
    private BookOutput bookOutput;

    @MockBean
    private IOServiceStream ioServiceStream;

    @Captor
    private ArgumentCaptor<String> interceptOutput;

    @BeforeEach
    void BeforeEachTest(){
        doNothing().when(ioServiceStream).outputString(interceptOutput.capture());
    }

    @Test
    void outputBook() {
        long authorId = 1L;
        String authorName = "vasya-author";
        Author author = new Author(authorId, authorName);

        long genreId = 1L;
        String genreName = "genre";
        Genre genre = new Genre(genreId, genreName);

        long id = 1L;
        String name = "Boring book";

        Book book = new Book(id, name, author, genre);

        bookOutput.outputBook(book);

        String expectedValue = EXPECTED_BOOK;
        String actualValue = interceptOutput.getValue();
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void outputBooks() {
        long authorId = 1L;
        String authorName = "vasya-author";
        Author author = new Author(authorId, authorName);

        long genreId = 1L;
        String genreName = "genre";
        Genre genre = new Genre(genreId, genreName);

        long bookIdOne = 1L;
        String bookNameOne = "Boring book";
        Book bookOne = new Book(bookIdOne, bookNameOne, author, genre);

        long bookIdTwo = 2L;
        String bookNameTwo = "Other Boring book";
        Book bookTwo = new Book(bookIdTwo, bookNameTwo, author, genre);

        List<Book> books = new ArrayList<>();
        books.add(bookOne);
        books.add(bookTwo);

        bookOutput.outputBooks(books);

        List<String> expectedValue = EXPECTED_BOOKS;
        List<String> actualValue = interceptOutput.getAllValues();
        assertEquals(expectedValue, actualValue);
    }
}