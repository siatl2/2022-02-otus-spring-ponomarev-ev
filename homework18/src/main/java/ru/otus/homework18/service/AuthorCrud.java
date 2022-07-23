package ru.otus.homework18.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.homework18.model.Author;

public interface AuthorCrud {
    Mono<Author> createAuthor(String name);

    Flux<Author> readAllAuthors();

    Flux<Author> findByName(String name);
}

