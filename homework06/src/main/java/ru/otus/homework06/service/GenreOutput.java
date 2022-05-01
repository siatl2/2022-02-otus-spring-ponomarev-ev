package ru.otus.homework06.service;

import ru.otus.homework06.model.Genre;

import java.util.List;

public interface GenreOutput {
    void outputGenre(Genre genre);
    void outputGenres(List<Genre> genres);
}
