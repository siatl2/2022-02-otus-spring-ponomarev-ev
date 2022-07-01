package ru.otus.homework13.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.ConcurrentModel;
import ru.otus.homework13.config.BookSecurityConfig;
import ru.otus.homework13.model.Author;
import ru.otus.homework13.model.Book;
import ru.otus.homework13.model.Genre;
import ru.otus.homework13.service.AuthorCrud;
import ru.otus.homework13.service.BookCrud;
import ru.otus.homework13.service.CommentCrud;
import ru.otus.homework13.service.GenreCrud;
import ru.otus.homework13.service.impl.ReaderCurrentImpl;
import ru.otus.homework13.service.impl.ReaderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BookController.class)
@Import({BookSecurityConfig.class, ReaderService.class})
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
    @MockBean
    private CommentCrud commentCrud;
    @Captor
    private ArgumentCaptor<Long> idCaptor;
    @Captor
    private ArgumentCaptor<Book> bookCaptor;

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void saveBook() throws Exception {
        doNothing().when(bookCrud).saveBook(bookCaptor.capture());

        Book book = new Book();

        mvc.perform(post("/books/save")
                        .with(csrf())
                        .param("book", String.valueOf(book))
                        .param("model", String.valueOf(new ConcurrentModel())))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"));

        assertAll(
                () -> verify(bookCrud, times(1)).saveBook(any())
                , () -> assertEquals(book, bookCaptor.getValue())
        );
    }

    @Test
    @WithMockUser(roles = {"ZZ"})
    void saveBookIncorrectRoles() throws Exception {
        Book book = new Book();
        mvc.perform(post("/books/save")
                        .with(csrf())
                        .param("book", String.valueOf(book))
                        .param("model", String.valueOf(new ConcurrentModel())))
                .andExpect(status().isForbidden());
    }


    @Test
    @WithAnonymousUser
    void saveBookWhenAnonimus() throws Exception {
        Book book = new Book();
        mvc.perform(post("/books/save")
                        .with(csrf())
                        .param("book", String.valueOf(book))
                        .param("model", String.valueOf(new ConcurrentModel())))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));
    }

    @WithMockUser(roles = {"ADMIN"})
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

    @Test
    @WithAnonymousUser
    void addBookWhenAnonimus() throws Exception {
        mvc.perform(get("/books/add")
                        .param("model", String.valueOf(new ConcurrentModel())))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));
    }

    @WithMockUser(roles = {"ADMIN"})
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

    @Test
    @WithAnonymousUser
    void readAllBooksWhenAnonimous() throws Exception {
        mvc.perform(get("/books")
                        .param("model", String.valueOf(new ConcurrentModel())))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    void retrieveBookWhenAdmin() throws Exception {
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

    @Test
    @WithAnonymousUser
    void retrieveBookWhenAnonymous() throws Exception {
        mvc.perform(get("/books/get")
                        .param("id", String.valueOf(SOME_ID))
                        .param("model", String.valueOf(new ConcurrentModel())))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));
    }
}
