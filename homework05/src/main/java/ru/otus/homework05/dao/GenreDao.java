package ru.otus.homework05.dao;

import ru.otus.homework05.domain.Author;
import ru.otus.homework05.domain.Genre;

import java.util.List;

public interface GenreDao {
    long insertByName(String name);
    List<Genre> getAll();
    Genre getById(long id);
    boolean existById(long id);
    void update(Genre newGenre);
    void delete(Genre genre);
}
