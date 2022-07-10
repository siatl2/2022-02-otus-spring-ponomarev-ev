package ru.otus.homework11.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import ru.otus.homework11.model.Genre;
import ru.otus.homework11.rest.dto.GenreDto;
import ru.otus.homework11.service.GenreCrud;

import java.util.List;

import static org.mockito.Mockito.*;

@ComponentScan(basePackageClasses = {ru.otus.homework11.config.Config.class})
@WebFluxTest(GenreController.class)
class GenreControllerTest {
    @Autowired
    WebTestClient webTestClient;

    @MockBean
    private GenreCrud genreCrud;

    @Test
    void readAllGenres() throws Exception {
        Flux<Genre> genres = Flux.just(
                new Genre(1L, "Genre-1"),
                new Genre(2L, "Genre-2")
        );

        List<GenreDto> genresDto = List.of(
                GenreDto.toDto(new Genre(1L, "Genre-1")),
                GenreDto.toDto(new Genre(2L, "Genre-2"))
        );

        when(genreCrud.readAllGenres()).thenReturn(genres);

        webTestClient.get()
                .uri("/genres")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();

        verify(genreCrud, times(1)).readAllGenres();
    }
}