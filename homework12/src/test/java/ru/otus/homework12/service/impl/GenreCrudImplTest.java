package ru.otus.homework12.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.homework12.model.Genre;
import ru.otus.homework12.repository.GenreRepository;
import ru.otus.homework12.service.GenreCrud;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest(classes = {GenreCrudImpl.class})
class GenreCrudImplTest {
    @Autowired
    private GenreCrud genreCrud;
    @MockBean
    private GenreRepository genreRepository;

    @Test
    void readAllGenres() {
        List<Genre> genres = new ArrayList<>();
        when(genreRepository.findAll()).thenReturn(genres);

        genreCrud.readAllGenres();

        verify(genreRepository, times(1)).findAll();
    }
}