package ru.otus.homework08.service.impl;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.homework08.exception.LibraryException;
import ru.otus.homework08.model.Author;
import ru.otus.homework08.repository.AuthorRepository;
import ru.otus.homework08.service.AuthorCrud;
import ru.otus.homework08.service.AuthorOutput;
import ru.otus.homework08.service.SequenceGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {AuthorCrudImpl.class})
class AuthorCrudImplTest {
    @Autowired
    private AuthorCrud authorCrud;
    @MockBean
    private AuthorOutput authorOutput;
    @MockBean
    private AuthorRepository authorRepository;
    @MockBean
    private SequenceGenerator sequenceGenerator;
    @Captor
    private ArgumentCaptor<List<Author>> authorsCaptor;
    @Captor
    private ArgumentCaptor<Author> authorCaptor;

    @Test
    void createAuthor() {
        when(authorRepository.save(authorCaptor.capture())).thenReturn(null);

        authorCrud.createAuthor("Some name");

        assertAll(
                () -> verify(authorRepository, times(1)).save(any()),
                () -> assertEquals(0L, authorCaptor.getValue().getId()));
    }

    @Test
    void readAllAuthors() {
        List<Author> authors = new ArrayList<>();
        when(authorRepository.findAll()).thenReturn(authors);
        when(sequenceGenerator.getSequenceNumber(anyString())).thenReturn(1L);
        doNothing().when(authorOutput).outputAuthors(authorsCaptor.capture());

        authorCrud.readAllAuthors();

        assertAll(
                () -> verify(authorRepository, times(1)).findAll(),
                () -> verify(authorOutput, times(1)).outputAuthors(any()),
                () -> assertEquals(authors, authorsCaptor.getValue())
        );
    }

    @Test
    void retrieveAuthor() {
        Author author = new Author(0L, "some name");
        when(authorRepository.findById(anyLong())).thenReturn(Optional.of(author));
        doNothing().when(authorOutput).outputAuthor(authorCaptor.capture());

        authorCrud.retrieveAuthor(1L);

        assertAll(
                () -> verify(authorRepository, times(1)).findById(anyLong()),
                () -> verify(authorOutput, times(1)).outputAuthor(any()),
                () -> assertEquals(author, authorCaptor.getValue())
        );
    }

    @Test
    void retrieveAuthorException() {
        when(authorRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(LibraryException.class, () -> authorCrud.retrieveAuthor(1L));
    }

    @Test
    void updateAuthor() {
        when(authorRepository.existsById(anyLong())).thenReturn(true);
        when(authorRepository.save(authorCaptor.capture())).thenReturn(null);

        authorCrud.updateAuthor(1L, "Some author");

        assertAll(
                () -> verify(authorRepository, times(1)).save(any()),
                () -> assertNotEquals(0L, authorCaptor.getValue().getId())
        );
    }

    @Test
    void updateAuthorException() {
        when(authorRepository.existsById(anyLong())).thenReturn(false);
        assertThrows(LibraryException.class, () -> authorCrud.updateAuthor(1L, "Some author"));
    }

    @Test
    void deleteAuthor() {
        doNothing().when(authorRepository).deleteById(anyLong());

        authorCrud.deleteAuthor(1L);

        verify(authorRepository, times(1)).deleteById(anyLong());
    }
}
