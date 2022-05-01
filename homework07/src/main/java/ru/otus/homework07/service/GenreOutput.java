package ru.otus.homework07.service;

import ru.otus.homework07.model.Genre;

import java.util.List;

public interface GenreOutput {
    void outputGenre(Genre genre);
    void outputGenres(List<Genre> genres);
}

