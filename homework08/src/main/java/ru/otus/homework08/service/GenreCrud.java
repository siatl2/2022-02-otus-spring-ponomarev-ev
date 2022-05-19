package ru.otus.homework08.service;

import ru.otus.homework08.model.Genre;

import java.util.List;

public interface GenreCrud {
    void createGenre(String name);
    void readAllGenres();
    void retrieveGenre(long id);
    void updateGenre(long id, String name);
    void deleteGenre(long id);
    List<Genre> findByName(String name);
}

