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
import ru.otus.homework13.model.Book;
import ru.otus.homework13.model.Comment;
import ru.otus.homework13.service.BookCrud;
import ru.otus.homework13.service.CommentCrud;
import ru.otus.homework13.service.impl.ReaderCurrentImpl;
import ru.otus.homework13.service.impl.ReaderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CommentController.class)
@Import({BookSecurityConfig.class, ReaderService.class})
class CommentControllerTest {
    private static final long SOME_ID = 1L;
    @Autowired
    private MockMvc mvc;
    @MockBean
    private BookCrud bookCrud;
    @MockBean
    private CommentCrud commentCrud;
    @MockBean
    private ReaderService readerService;
    @MockBean
    private ReaderCurrentImpl readerCurrent;
    @Captor
    private ArgumentCaptor<Long> idCaptor;

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void readAllComments() throws Exception {
        List<Comment> comments = new ArrayList<>();
        when(bookCrud.retrieveBook(anyLong())).thenReturn(Optional.of(new Book()));
        when(commentCrud.readAllCommentsByBookId(idCaptor.capture())).thenReturn(comments);

        mvc.perform(get("/comments")
                        .param("bookId", String.valueOf(SOME_ID))
                        .param("model", String.valueOf(new ConcurrentModel())))
                .andExpect(status().isOk())
                .andExpect(view().name("comments"));

        assertAll(
                () -> verify(bookCrud, times(1)).retrieveBook(anyLong())
                , () -> verify(commentCrud, times(1)).readAllCommentsByBookId(anyLong())
                , () -> assertEquals(SOME_ID, Optional.ofNullable(idCaptor.getValue()).get())
        );
    }

    @Test
    @WithAnonymousUser
    void readAllCommentsWhenAnonymous() throws Exception {
        mvc.perform(get("/comments")
                        .param("bookId", String.valueOf(SOME_ID))
                        .param("model", String.valueOf(new ConcurrentModel())))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void readAllCommentsExceptionNotFound() throws Exception {
        List<Comment> comments = new ArrayList<>();
        when(bookCrud.retrieveBook(anyLong())).thenReturn(Optional.empty());

        mvc.perform(get("/comments")
                        .param("bookId", String.valueOf(SOME_ID))
                        .param("model", String.valueOf(new ConcurrentModel())))
                .andExpect(status().isNotFound());
    }
}