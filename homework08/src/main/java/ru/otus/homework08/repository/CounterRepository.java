package ru.otus.homework08.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework08.model.Counter;

public interface CounterRepository extends MongoRepository<Counter, String> {
}
