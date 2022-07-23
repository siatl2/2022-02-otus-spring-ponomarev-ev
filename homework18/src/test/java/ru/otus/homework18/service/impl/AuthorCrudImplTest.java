package ru.otus.homework18.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Flux;
import ru.otus.homework18.model.Author;
import ru.otus.homework18.repository.AuthorRepository;
import ru.otus.homework18.service.AuthorCrud;
import ru.otus.homework18.service.SequenceGenerator;

import static org.mockito.Mockito.*;

@SpringBootTest(classes = {AuthorCrudImpl.class})
class AuthorCrudImplTest {
    @Autowired
    private AuthorCrud authorCrud;
    @MockBean
    private AuthorRepository authorRepository;
    @MockBean
    private SequenceGenerator sequenceGenerator;

    @Test
    void readAllAuthors() {
        Flux<Author> authors = null;
        when(authorRepository.findAll()).thenReturn(authors);

        authorCrud.readAllAuthors();

        verify(authorRepository, times(1)).findAll();
    }
}
