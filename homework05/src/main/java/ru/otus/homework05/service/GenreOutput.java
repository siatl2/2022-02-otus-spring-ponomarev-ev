package ru.otus.homework05.service;

import ru.otus.homework05.domain.Genre;

import java.util.List;

public interface GenreOutput {
    void outputGenre(Genre genre);
    void outputGenres(List<Genre> genres);
}
