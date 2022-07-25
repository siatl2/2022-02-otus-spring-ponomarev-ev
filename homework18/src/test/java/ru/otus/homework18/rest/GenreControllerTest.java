package ru.otus.homework18.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.homework18.model.Genre;
import ru.otus.homework18.rest.dto.GenreDto;
import ru.otus.homework18.service.GenreCrud;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GenreController.class)
class GenreControllerTest {
    @Autowired
    MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private GenreCrud genreCrud;

    @Test
    void readAllGenres() throws Exception {
        List<Genre> genres = List.of(
                new Genre(1L, "Genre-1"),
                new Genre(2L, "Genre-2")
        );

        List<GenreDto> genresDto = List.of(
                GenreDto.toDto(new Genre(1L, "Genre-1")),
                GenreDto.toDto(new Genre(2L, "Genre-2"))
        );

        String expectedContent = mapper.writeValueAsString(genresDto);

        when(genreCrud.readAllGenres()).thenReturn(genres);

        mvc.perform(get("/genres"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(expectedContent));

        verify(genreCrud, times(1)).readAllGenres();
    }
}