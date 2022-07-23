package ru.otus.homework11.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.homework11.model.Author;

public interface AuthorCrud {
    Mono<Author> createAuthor(String name);

    Flux<Author> readAllAuthors();

    Flux<Author> findByName(String name);
}

