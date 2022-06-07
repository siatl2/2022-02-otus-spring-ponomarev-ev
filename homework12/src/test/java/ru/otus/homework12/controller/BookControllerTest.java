package ru.otus.homework12.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.ConcurrentModel;
import ru.otus.homework12.model.Author;
import ru.otus.homework12.model.Book;
import ru.otus.homework12.model.Genre;
import ru.otus.homework12.service.AuthorCrud;
import ru.otus.homework12.service.BookCrud;
import ru.otus.homework12.service.GenreCrud;
import ru.otus.homework12.service.impl.ReaderCurrentImpl;
import ru.otus.homework12.service.impl.ReaderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BookController.class)
class BookControllerTest {
    private static final long SOME_ID = 1L;
    @Autowired
    private MockMvc mvc;
    @MockBean
    private AuthorCrud authorCrud;
    @MockBean
    private BookCrud bookCrud;
    @MockBean
    private GenreCrud genreCrud;
    @MockBean
    private ReaderService readerService;
    @MockBean
    private ReaderCurrentImpl readerCurrent;

    @Captor
    private ArgumentCaptor<Long> idCaptor;
    @Captor
    private ArgumentCaptor<Book> bookCaptor;


    @Test
    @WithMockUser(
            username = "DOE",
            authorities = {"ROLE_USER"}
    )
    void saveBook() throws Exception {
        doNothing().when(bookCrud).saveBook(bookCaptor.capture());

        Book book = new Book();

        mvc.perform(post("/books/save")
                        .param("book", String.valueOf(book))
                        .param("model", String.valueOf(new ConcurrentModel())))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"));

        assertAll(
                () -> verify(bookCrud, times(1)).saveBook(any())
                , () -> assertEquals(book, bookCaptor.getValue())
        );
    }

    @WithMockUser(
            username = "DOE",
            authorities = {"ROLE_USER"}
    )
    @Test
    void addBook() throws Exception {
        List<Author> authors = new ArrayList<>();
        List<Genre> genres = new ArrayList<>();
        when(authorCrud.readAllAuthors()).thenReturn(authors);
        when(genreCrud.readAllGenres()).thenReturn(genres);

        mvc.perform(get("/books/add")
                        .param("model", String.valueOf(new ConcurrentModel())))
                .andExpect(status().isOk())
                .andExpect(view().name("edit"));

        assertAll(
                () -> verify(authorCrud, times(1)).readAllAuthors()
                , () -> verify(genreCrud, times(1)).readAllGenres()
        );
    }

    @WithMockUser(
            username = "DOE",
            authorities = {"ROLE_USER"}
    )
    @Test
    void readAllBooks() throws Exception {
        List<Book> books = new ArrayList<>();
        when(bookCrud.readAllBooks()).thenReturn(books);

        mvc.perform(get("/books")
                        .param("model", String.valueOf(new ConcurrentModel())))
                .andExpect(status().isOk())
                .andExpect(view().name("list"));

        verify(bookCrud, times(1)).readAllBooks();
    }

    @WithMockUser(
            username = "DOE",
            authorities = {"ROLE_USER"}
    )
    @Test
    void retrieveBook() throws Exception {
        Book book = new Book();
        List<Author> authors = new ArrayList<>();
        List<Genre> genres = new ArrayList<>();
        when(bookCrud.retrieveBook(idCaptor.capture())).thenReturn(Optional.of(book));
        when(authorCrud.readAllAuthors()).thenReturn(authors);
        when(genreCrud.readAllGenres()).thenReturn(genres);

        mvc.perform(get("/books/get")
                        .param("id", String.valueOf(SOME_ID))
                        .param("model", String.valueOf(new ConcurrentModel())))
                .andExpect(status().isOk())
                .andExpect(view().name("edit"));

        assertAll(
                () -> verify(bookCrud, times(1)).retrieveBook(anyLong())
                , () -> assertEquals(SOME_ID, idCaptor.getValue())
                , () -> verify(authorCrud, times(1)).readAllAuthors()
                , () -> verify(genreCrud, times(1)).readAllGenres()
        );
    }

    @WithMockUser(
            username = "DOE",
            authorities = {"ROLE_USER"}
    )
    @Test
    void retrieveBookExceptionNotFound() throws Exception {
        List<Author> authors = new ArrayList<>();
        List<Genre> genres = new ArrayList<>();
        when(bookCrud.retrieveBook(anyLong())).thenReturn(Optional.empty());
        when(authorCrud.readAllAuthors()).thenReturn(authors);
        when(genreCrud.readAllGenres()).thenReturn(genres);

        mvc.perform(get("/books/get")
                        .param("id", String.valueOf(SOME_ID))
                        .param("model", String.valueOf(new ConcurrentModel())))
                .andExpect(status().isNotFound());
    }

    @WithMockUser(
            username = "DOE",
            authorities = {"ROLE_USER"}
    )
    @Test
    void deleteBook() throws Exception {
        doNothing().when(bookCrud).deleteBook(idCaptor.capture());

        mvc.perform(get("/books/delete")
                        .param("id", String.valueOf(SOME_ID))
                        .param("model", String.valueOf(new ConcurrentModel())))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"));

        assertAll(
                () -> verify(bookCrud, times(1)).deleteBook(anyLong())
                , () -> assertEquals(SOME_ID, idCaptor.getValue())
        );
    }
}