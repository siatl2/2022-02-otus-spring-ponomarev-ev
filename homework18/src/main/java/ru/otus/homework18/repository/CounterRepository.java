package ru.otus.homework18.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.homework18.model.Counter;

public interface CounterRepository extends ReactiveMongoRepository<Counter, String> {
}
