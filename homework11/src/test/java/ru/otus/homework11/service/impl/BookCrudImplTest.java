package ru.otus.homework11.service.impl;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.homework11.model.Book;
import ru.otus.homework11.repository.AuthorRepository;
import ru.otus.homework11.repository.BookRepository;
import ru.otus.homework11.repository.CommentRepository;
import ru.otus.homework11.repository.GenreRepository;
import ru.otus.homework11.service.BookCrud;
import ru.otus.homework11.service.SequenceGenerator;

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
    @MockBean
    private CommentRepository commentRepository;
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
    void deleteBook() {
        when(bookRepository.deleteById(idCaptor.capture())).thenReturn(Mono.empty());

        bookRepository.deleteById(SOME_ID);

        assertAll(
                () -> verify(bookRepository, times(1)).deleteById(anyLong())
                , () -> assertEquals(SOME_ID, idCaptor.getValue())
        );
    }
}