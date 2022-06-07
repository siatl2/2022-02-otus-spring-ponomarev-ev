package ru.otus.homework12.service.impl;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.homework12.model.Book;
import ru.otus.homework12.repository.AuthorRepository;
import ru.otus.homework12.repository.BookRepository;
import ru.otus.homework12.repository.GenreRepository;
import ru.otus.homework12.service.BookCrud;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {BookCrudImpl.class})
class BookCrudImplTest {
    private static final long SOME_ID = 1L;
    @Autowired
    private BookCrud bookCrud;
    @MockBean
    private AuthorRepository authorRepository;
    @MockBean
    private BookRepository bookRepository;
    @MockBean
    private GenreRepository genreRepository;
    @Captor
    private ArgumentCaptor<Long> idCaptor;
    @Captor
    private ArgumentCaptor<Book> bookCaptor;

    @Test
    void readAllBooks() {
        List<Book> books = new ArrayList<>();
        when(bookRepository.findAll()).thenReturn(books);

        bookCrud.readAllBooks();

        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void retrieveBook() {
        Optional<Book> book = Optional.of(new Book());
        when(bookRepository.findById(idCaptor.capture())).thenReturn(book);

        bookCrud.retrieveBook(SOME_ID);

        assertAll(
                () -> verify(bookRepository, times(1)).findById(anyLong())
                , () -> assertEquals(SOME_ID, idCaptor.getValue())
        );
    }

    @Test
    void saveBook() {
        Book book = new Book();
        when(bookRepository.save(bookCaptor.capture())).thenReturn(book);

        bookCrud.saveBook(book);

        assertAll(
                () -> verify(bookRepository, times(1)).save(any())
                , () -> assertEquals(book, bookCaptor.getValue())
        );
    }

    @Test
    void deleteBook() {
        doNothing().when(bookRepository).deleteById(idCaptor.capture());

        bookRepository.deleteById(SOME_ID);

        assertAll(
                () -> verify(bookRepository, times(1)).deleteById(anyLong())
                , () -> assertEquals(SOME_ID, idCaptor.getValue())
        );
    }
}