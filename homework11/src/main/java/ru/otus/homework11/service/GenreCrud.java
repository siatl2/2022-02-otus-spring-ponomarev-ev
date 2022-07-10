package ru.otus.homework11.service;

import reactor.core.publisher.Flux;
import ru.otus.homework11.model.Genre;

public interface GenreCrud {
    void createGenre(String name);

    Flux<Genre> readAllGenres();

    Flux<Genre> findByName(String name);
}

