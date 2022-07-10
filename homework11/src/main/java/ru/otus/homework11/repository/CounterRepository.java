package ru.otus.homework11.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.homework11.model.Counter;

public interface CounterRepository extends ReactiveMongoRepository<Counter, String> {
}
