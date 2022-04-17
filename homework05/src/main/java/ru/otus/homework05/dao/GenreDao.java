package ru.otus.homework05.dao;

import ru.otus.homework05.domain.Genre;

import java.util.List;

public interface GenreDao {
    void insert(Genre genre);
    List<Genre> getAll();
    Genre getById(long id);
    boolean existById(long id);
    void update(Genre genre);
    void deleteById(long id);
}
