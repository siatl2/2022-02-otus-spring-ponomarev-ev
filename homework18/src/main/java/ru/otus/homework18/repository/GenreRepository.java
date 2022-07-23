package ru.otus.homework18.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import ru.otus.homework18.model.Genre;

public interface GenreRepository extends ReactiveMongoRepository<Genre, Long> {
    Flux<Genre> findAll();

    Flux<Genre> findByName(String name);
}

