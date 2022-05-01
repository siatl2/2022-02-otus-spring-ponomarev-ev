package ru.otus.homework07.service;

public interface GenreCrud {
    void createGenre(String name);
    void readAllGenres();
    void retrieveGenre(long id);
    void updateGenre(long id, String name);
    void deleteGenre(long id);
}
