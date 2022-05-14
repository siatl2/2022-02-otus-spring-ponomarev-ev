package ru.otus.homework08.service;

import ru.otus.homework08.model.Genre;

import java.util.List;

public interface GenreOutput {
    void outputGenre(Genre genre);
    void outputGenres(List<Genre> genres);
}

