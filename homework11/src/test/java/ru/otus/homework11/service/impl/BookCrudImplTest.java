package ru.otus.homework11.service.impl;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.homework11.exception.NotFoundException;
import ru.otus.homework11.model.Author;
import ru.otus.homework11.model.Book;
import ru.otus.homework11.model.Genre;
import ru.otus.homework11.repository.AuthorRepository;
import ru.otus.homework11.repository.BookRepository;
import ru.otus.homework11.repository.GenreRepository;
import ru.otus.homework11.service.BookCrud;
import ru.otus.homework11.service.SequenceGenerator;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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
    @MockBean
    private SequenceGenerator sequenceGenerator;
    @Captor
    private ArgumentCaptor<Long> idCaptor;
    @Captor
    private ArgumentCaptor<Book> bookCaptor;

    @Test
    void readAllBooks() {
        Flux<Book> books = null;
        when(bookRepository.findAll()).thenReturn(books);

        bookCrud.readAllBooks();

        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void retrieveBook() {
        Mono<Book> book = Mono.justOrEmpty(Optional.of(new Book()));
        when(bookRepository.findById(idCaptor.capture())).thenReturn(book);

        bookCrud.retrieveBook(SOME_ID);

        assertAll(
                () -> verify(bookRepository, times(1)).findById(anyLong())
                , () -> assertEquals(SOME_ID, idCaptor.getValue())
        );
    }

    @Test
    void saveBook() {
        long expectedBookId = 10L;
        String expectedBookName = "Some name of book";
        long expectedAuthorId = 1L;
        long expectedGenreId = 2L;

        Author author = new Author(expectedAuthorId, "Some author");
        Genre genre = new Genre(expectedGenreId, "Some genre");

        Book passedOnMethodBook = new Book(expectedBookId,
                expectedBookName,
                author,
                genre
        );

        when(authorRepository.existsById(anyLong())).thenReturn(Mono.just(Boolean.TRUE));
        when(genreRepository.existsById(anyLong())).thenReturn(Mono.just(Boolean.TRUE));
        when(authorRepository.findById(anyLong())).thenReturn(Mono.justOrEmpty(author));
        when(genreRepository.findById(anyLong())).thenReturn(Mono.justOrEmpty(genre));
        when(bookRepository.save(bookCaptor.capture())).thenReturn(Mono.just(new Book()));
        when(sequenceGenerator.getSequenceNumber(anyString())).thenReturn(expectedBookId);

        bookCrud.saveBook(passedOnMethodBook);

        assertAll(
                () -> verify(bookRepository, times(1)).save(any())
                , () -> assertEquals(expectedBookId, bookCaptor.getValue().getId())
                , () -> assertEquals(expectedBookName, bookCaptor.getValue().getName())
                , () -> assertEquals(expectedAuthorId, bookCaptor.getValue().getAuthor().getId())
                , () -> assertEquals(expectedGenreId, bookCaptor.getValue().getGenre().getId())
        );
    }

    @Test
    void saveBook_exceptionAuthorNotExist() {
        long bookId = 10L;
        String bookName = "Some name of book";
        long authorId = 1L;
        long genreId = 2L;

        Author author = new Author(authorId, "Some author");
        Genre genre = new Genre(genreId, "Some genre");

        Book passedOnMethodBook = new Book(bookId,
                bookName,
                author,
                genre
        );

        when(authorRepository.existsById(anyLong())).thenReturn(Mono.just(Boolean.FALSE));
        when(genreRepository.existsById(anyLong())).thenReturn(Mono.just(Boolean.TRUE));

        assertThrows(NotFoundException.class, () -> bookCrud.saveBook(passedOnMethodBook));
    }

    @Test
    void saveBook_exceptionGenreNotExist() {
        long bookId = 10L;
        String bookName = "Some name of book";
        long authorId = 1L;
        long genreId = 2L;

        Author author = new Author(authorId, "Some author");
        Genre genre = new Genre(genreId, "Some genre");

        Book passedOnMethodBook = new Book(bookId,
                bookName,
                author,
                genre
        );

        when(authorRepository.existsById(anyLong())).thenReturn(Mono.just(Boolean.TRUE));
        when(genreRepository.existsById(anyLong())).thenReturn(Mono.just(Boolean.FALSE));

        assertThrows(NotFoundException.class, () -> bookCrud.saveBook(passedOnMethodBook));
    }

    @Test
    void deleteBook() {
        when(bookRepository.deleteById(idCaptor.capture())).thenReturn(Mono.empty());

        bookRepository.deleteById(SOME_ID);

        assertAll(
                () -> verify(bookRepository, times(1)).deleteById(anyLong())
                , () -> assertEquals(SOME_ID, idCaptor.getValue())
        );
    }
}