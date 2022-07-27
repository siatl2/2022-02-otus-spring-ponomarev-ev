package ru.otus.homework18.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.homework18.model.Author;
import ru.otus.homework18.model.Book;
import ru.otus.homework18.model.Genre;
import ru.otus.homework18.rest.dto.BookDto;
import ru.otus.homework18.service.BookCrud;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class BookControllerTest {
    @Autowired
    MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private BookCrud bookCrud;
    @Captor
    private ArgumentCaptor<Long> idCaptor;
    @Captor
    private ArgumentCaptor<Book> bookCaptor;

    @Test
    void readAllBooks() throws Exception {
        Book book = getTestedBook();
        List<Book> books = List.of(book);
        List<BookDto> booksDto = List.of(BookDto.toDto(book));
        String expectedContent = mapper.writeValueAsString(booksDto);

        when(bookCrud.readAllBooks()).thenReturn(books);

        mvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(expectedContent));

        verify(bookCrud, times(1)).readAllBooks();
    }

    @Test
    void retrieveBook() throws Exception {
        Book book = getTestedBook();
        String expectedContent = mapper.writeValueAsString(BookDto.toDto(book));

        when(bookCrud.retrieveBook(idCaptor.capture())).thenReturn(Optional.of(book));

        mvc.perform(get("/books/" + book.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(expectedContent));

        assertAll(
                () -> verify(bookCrud, times(1)).retrieveBook(anyLong()),
                () -> assertEquals(book.getId(), idCaptor.getValue())
        );
    }

    @Test
    void retrieveBookExceptionNotFound() throws Exception {
        when(bookCrud.retrieveBook(anyLong())).thenReturn(Optional.empty());

        mvc.perform(get("/books/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void addBook() throws Exception {
        Book book = getTestedBook();
        book.setId(0L);
        String expectedContent = mapper.writeValueAsString(BookDto.toDto(book));

        when(bookCrud.saveBook(bookCaptor.capture())).thenReturn(book);

        mvc.perform(post("/books")
                        .contentType("application/json")
                        .content(expectedContent))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(expectedContent));

        assertAll(
                () -> verify(bookCrud, times(1)).saveBook(any())
                , () -> assertEquals(book, bookCaptor.getValue())
        );
    }

    @Test
    void updateBook() throws Exception {
        Book book = getTestedBook();
        String expectedContent = mapper.writeValueAsString(BookDto.toDto(book));

        when(bookCrud.existsById(anyLong())).thenReturn(true);
        when(bookCrud.saveBook(bookCaptor.capture())).thenReturn(book);

        mvc.perform(put("/books")
                        .contentType("application/json")
                        .content(expectedContent))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(expectedContent));

        assertAll(
                () -> verify(bookCrud, times(1)).saveBook(any())
                , () -> assertEquals(book, bookCaptor.getValue())
        );
    }

    @Test
    void updateBookExceptionNotFound() throws Exception {
        Book book = getTestedBook();
        String expectedContent = mapper.writeValueAsString(BookDto.toDto(book));

        when(bookCrud.existsById(anyLong())).thenReturn(false);

        mvc.perform(put("/books/")
                        .contentType("application/json")
                        .content(expectedContent))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteBook() throws Exception {
        long bookId = 10L;
        when(bookCrud.existsById(anyLong())).thenReturn(true);
        doNothing().when(bookCrud).deleteBook(idCaptor.capture());

        mvc.perform(delete("/books/" + bookId))
                .andExpect(status().isOk());

        assertAll(
                () -> verify(bookCrud, times(1)).deleteBook(anyLong()),
                () -> assertEquals(bookId, idCaptor.getValue())
        );
    }

    @Test
    void deleteBookExceptionNotFound() throws Exception {
        long bookId = 10L;
        when(bookCrud.existsById(anyLong())).thenReturn(false);

        mvc.perform(delete("/books/" + bookId))
                .andExpect(status().isNotFound());
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