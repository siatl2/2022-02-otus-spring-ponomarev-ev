package ru.otus.homework13.service;

import org.springframework.security.access.prepost.PreAuthorize;
import ru.otus.homework13.model.Author;

import java.util.List;

public interface AuthorCrud {
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    List<Author> readAllAuthors();
}

