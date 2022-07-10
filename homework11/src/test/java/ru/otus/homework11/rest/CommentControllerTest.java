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
import ru.otus.homework11.model.Author;
import ru.otus.homework11.model.Book;
import ru.otus.homework11.model.Comment;
import ru.otus.homework11.model.Genre;
import ru.otus.homework11.rest.dto.CommentDto;
import ru.otus.homework11.service.CommentCrud;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ComponentScan(basePackageClasses = {ru.otus.homework11.config.Config.class})
@WebFluxTest(CommentController.class)
class CommentControllerTest {
    @Autowired
    WebTestClient webTestClient;
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

        Flux<Comment> comments = Flux.just(
                new Comment(1L, "Comment-1", book),
                new Comment(2L, "Comment-2", book)
        );

        List<CommentDto> commentsDto = List.of(
                CommentDto.toDto(new Comment(1L, "Comment-1", book)),
                CommentDto.toDto(new Comment(2L, "Comment-2", book))
        );

        when(commentCrud.readAllCommentsByBookId(idCaptor.capture())).thenReturn(comments);

        webTestClient.get()
                .uri("/comments/" + bookId)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();


        assertAll(
                () -> verify(commentCrud, times(1)).readAllCommentsByBookId(anyLong()),
                () -> assertEquals(bookId, idCaptor.getValue())
        );
    }
}