package ru.otus.homework16.controller;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.ConcurrentModel;
import ru.otus.homework16.model.Book;
import ru.otus.homework16.model.Comment;
import ru.otus.homework16.service.BookCrud;
import ru.otus.homework16.service.CommentCrud;

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

@WebMvcTest(CommentController.class)
class CommentControllerTest {
    private static final long SOME_ID = 1L;
    @Autowired
    MockMvc mvc;
    @MockBean
    private BookCrud bookCrud;
    @MockBean
    private CommentCrud commentCrud;
    @Captor
    private ArgumentCaptor<Long> idCaptor;

    @Test
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
                , () -> assertEquals(SOME_ID, idCaptor.getValue())
        );
    }

    @Test
    void readAllCommentsExceptionNotFound() throws Exception {
        List<Comment> comments = new ArrayList<>();
        when(bookCrud.retrieveBook(anyLong())).thenReturn(Optional.empty());
        when(commentCrud.readAllCommentsByBookId(idCaptor.capture())).thenReturn(comments);

        mvc.perform(get("/comments")
                        .param("bookId", String.valueOf(SOME_ID))
                        .param("model", String.valueOf(new ConcurrentModel())))
                .andExpect(status().isNotFound());
    }
}