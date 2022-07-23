package ru.otus.homework18.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Flux;
import ru.otus.homework18.model.Genre;
import ru.otus.homework18.repository.GenreRepository;
import ru.otus.homework18.service.GenreCrud;
import ru.otus.homework18.service.SequenceGenerator;

import static org.mockito.Mockito.*;

@SpringBootTest(classes = {GenreCrudImpl.class})
class GenreCrudImplTest {
    @Autowired
    private GenreCrud genreCrud;
    @MockBean
    private GenreRepository genreRepository;
    @MockBean
    private SequenceGenerator sequenceGenerator;

    @Test
    void readAllGenres() {
        Genre genre = new Genre();
        when(genreRepository.findAll()).thenReturn(Flux.just(genre));

        genreCrud.readAllGenres();

        verify(genreRepository, times(1)).findAll();
    }
}