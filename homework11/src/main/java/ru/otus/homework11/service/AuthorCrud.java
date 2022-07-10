package ru.otus.homework11.service;

import reactor.core.publisher.Flux;
import ru.otus.homework11.model.Author;

public interface AuthorCrud {
    void createAuthor(String name);

    Flux<Author> readAllAuthors();

    Flux<Author> findByName(String name);
}

