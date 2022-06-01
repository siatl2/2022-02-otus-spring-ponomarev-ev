package ru.otus.homework10.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.homework10.model.Author;
import ru.otus.homework10.model.Book;
import ru.otus.homework10.model.Comment;
import ru.otus.homework10.model.Genre;
import ru.otus.homework10.rest.dto.CommentDto;
import ru.otus.homework10.service.CommentCrud;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommentController.class)
class CommentControllerTest {
    @Autowired
    MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private CommentCrud commentCrud;

    @Captor
    private ArgumentCaptor<Long> idCaptor;

    @Test
    void readAllComments() throws Exception {
        long bookId = 10L;
        String bookName = "Some name of book";
        long authorId = 1L;
        long genreId = 2L;

        Author author = new Author(authorId, "Some author");
        Genre genre = new Genre(genreId, "Some genre");

        Book book = new Book(bookId,
                bookName,
                author,
                genre
        );

        List<Comment> comments = List.of(
                new Comment(1L, "Comment-1", book),
                new Comment(2L, "Comment-2", book)
        );

        List<CommentDto> commentsDto = List.of(
                CommentDto.toDto(new Comment(1L, "Comment-1", book)),
                CommentDto.toDto(new Comment(2L, "Comment-2", book))
        );

        String expectedContent = mapper.writeValueAsString(commentsDto);

        when(commentCrud.readAllCommentsByBookId(idCaptor.capture())).thenReturn(comments);

        mvc.perform(get("/comments/" + bookId))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(expectedContent));

        assertAll(
                () -> verify(commentCrud, times(1)).readAllCommentsByBookId(anyLong()),
                () -> assertEquals(bookId, idCaptor.getValue())
        );
    }
}