package ru.otus.homework18.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.homework18.model.Genre;

public interface GenreCrud {
    Mono<Genre> createGenre(String name);

    Flux<Genre> readAllGenres();

    Flux<Genre> findByName(String name);
}

