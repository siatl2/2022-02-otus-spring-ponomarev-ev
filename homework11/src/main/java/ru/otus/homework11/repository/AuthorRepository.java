package ru.otus.homework11.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import ru.otus.homework11.model.Author;

public interface AuthorRepository extends ReactiveMongoRepository<Author, Long> {
    Flux<Author> findAll();

    Flux<Author> findByName(String name);
}

