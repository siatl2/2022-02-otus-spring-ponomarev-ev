package ru.otus.homework18.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import ru.otus.homework18.model.Author;
import ru.otus.homework18.service.AuthorCrud;

import static org.mockito.Mockito.*;

@ComponentScan(basePackageClasses = {ru.otus.homework18.config.Config.class})
@WebFluxTest(AuthorController.class)
class AuthorControllerTest {
    @MockBean
    private AuthorCrud authorCrud;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void readAllAuthors() throws Exception {
        Flux<Author> authors = Flux.just(
                new Author(1L, "Author-1"),
                new Author(2L, "Author-2")
        );

        when(authorCrud.readAllAuthors()).thenReturn(authors);

        webTestClient.get()
                .uri("/authors")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();

        verify(authorCrud, times(1)).readAllAuthors();
    }
}