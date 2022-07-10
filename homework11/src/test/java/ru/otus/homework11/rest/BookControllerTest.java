package ru.otus.homework11.rest;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.homework11.model.Author;
import ru.otus.homework11.model.Book;
import ru.otus.homework11.model.Genre;
import ru.otus.homework11.rest.dto.BookDto;
import ru.otus.homework11.service.BookCrud;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ComponentScan(basePackageClasses = {ru.otus.homework11.config.Config.class})
@WebFluxTest(BookController.class)
class BookControllerTest {
    @Autowired
    WebTestClient webTestClient;
    @MockBean
    private BookCrud bookCrud;
    @Captor
    private ArgumentCaptor<Long> idCaptor;
    @Captor
    private ArgumentCaptor<Book> bookCaptor;

    @Test
    void readAllBooks() throws Exception {
        Book book = getTestedBook();
        Flux<Book> books = Flux.just(book);
        List<BookDto> booksDto = List.of(BookDto.toDto(book));

        when(bookCrud.readAllBooks()).thenReturn(books);

        webTestClient.get()
                .uri("/books")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();

        verify(bookCrud, times(1)).readAllBooks();
    }

    @Test
    void retrieveBook() throws Exception {
        Book book = getTestedBook();

        when(bookCrud.retrieveBook(idCaptor.capture())).thenReturn(Mono.justOrEmpty(Optional.of(book)));

        webTestClient.get()
                .uri("/books/" + book.getId())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();

        assertAll(
                () -> verify(bookCrud, times(1)).retrieveBook(anyLong()),
                () -> assertEquals(book.getId(), idCaptor.getValue())
        );
    }

    @Test
    void retrieveBookExceptionNotFound() throws Exception {
        when(bookCrud.retrieveBook(anyLong())).thenReturn(Mono.justOrEmpty(Optional.empty()));

        webTestClient.get()
                .uri("/books/1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void addBook() throws Exception {
        Book book = getTestedBook();
        book.setId(0L);

        when(bookCrud.saveBook(bookCaptor.capture())).thenReturn(Mono.just(book));

        webTestClient.post()
                .uri("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(book), Book.class)
                .exchange()
                .expectStatus().isOk();

        assertAll(
                () -> verify(bookCrud, times(1)).saveBook(any())
                , () -> assertEquals(book, bookCaptor.getValue())
        );
    }

    @Test
    void updateBook() throws Exception {
        Book book = getTestedBook();

        when(bookCrud.existsById(anyLong())).thenReturn(true);
        when(bookCrud.saveBook(bookCaptor.capture())).thenReturn(Mono.just(book));

        webTestClient.put()
                .uri("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(book), Book.class)
                .exchange()
                .expectStatus().isOk();

        assertAll(
                () -> verify(bookCrud, times(1)).saveBook(any())
                , () -> assertEquals(book, bookCaptor.getValue())
        );
    }

    @Test
    void updateBookExceptionNotFound() throws Exception {
        Book book = getTestedBook();

        when(bookCrud.existsById(anyLong())).thenReturn(false);

        webTestClient.put()
                .uri("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(book), Book.class)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void deleteBook() throws Exception {
        long bookId = 10L;
        when(bookCrud.existsById(anyLong())).thenReturn(true);
        doNothing().when(bookCrud).deleteBook(idCaptor.capture());

        webTestClient.delete()
                .uri("/books/" + bookId)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();

        assertAll(
                () -> verify(bookCrud, times(1)).deleteBook(anyLong()),
                () -> assertEquals(bookId, idCaptor.getValue())
        );
    }

    @Test
    void deleteBookExceptionNotFound() throws Exception {
        long bookId = 10L;
        when(bookCrud.existsById(anyLong())).thenReturn(false);

        webTestClient.delete()
                .uri("/books/" + bookId)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound();
    }

    private Book getTestedBook() {
        long bookId = 0L;
        String bookName = "Some name of book";
        long authorId = 1L;
        long genreId = 2L;

        Author author = new Author(authorId, "Some author");
        Genre genre = new Genre(genreId, "Some genre");

        return new Book(
                bookId,
                bookName,
                author,
                genre
        );
    }
}