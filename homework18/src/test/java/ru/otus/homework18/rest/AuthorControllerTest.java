package ru.otus.homework18.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.homework18.model.Author;
import ru.otus.homework18.rest.dto.AuthorDto;
import ru.otus.homework18.service.AuthorCrud;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthorController.class)
class AuthorControllerTest {
    @Autowired
    MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private AuthorCrud authorCrud;

    @Test
    void readAllAuthors() throws Exception {
        List<Author> authors = List.of(
                new Author(1L, "Author-1"),
                new Author(2L, "Author-2")
        );

        List<AuthorDto> authorsDto = List.of(
                AuthorDto.toDto(new Author(1L, "Author-1")),
                AuthorDto.toDto(new Author(2L, "Author-2"))
        );

        String expectedContent = mapper.writeValueAsString(authorsDto);

        when(authorCrud.readAllAuthors()).thenReturn(authors);

        mvc.perform(get("/authors"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(expectedContent));

        verify(authorCrud, times(1)).readAllAuthors();
    }
}