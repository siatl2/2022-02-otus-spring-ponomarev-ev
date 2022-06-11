package ru.otus.homework13.service;

import org.springframework.security.access.prepost.PreAuthorize;
import ru.otus.homework13.model.Genre;

import java.util.List;

public interface GenreCrud {
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    List<Genre> readAllGenres();
}

