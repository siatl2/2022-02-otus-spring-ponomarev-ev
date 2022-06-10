package ru.otus.homework12.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.homework12.model.Author;
import ru.otus.homework12.repository.AuthorRepository;
import ru.otus.homework12.service.AuthorCrud;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest(classes = {AuthorCrudImpl.class})
class AuthorCrudImplTest {
    @Autowired
    private AuthorCrud authorCrud;
    @MockBean
    private AuthorRepository authorRepository;

    @Test
    void readAllAuthors() {
        List<Author> authors = new ArrayList<>();
        when(authorRepository.findAll()).thenReturn(authors);

        authorCrud.readAllAuthors();

        verify(authorRepository, times(1)).findAll();
    }
}
