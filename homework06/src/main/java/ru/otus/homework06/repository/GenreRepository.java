package ru.otus.homework06.repository;

import ru.otus.homework06.model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {
    Genre save(Genre genre);
    List<Genre> findAll();
    Optional<Genre> findById(long id);
    boolean existById(long id);
    void deleteById(long id);
}
