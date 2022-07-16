package ru.otus.homework17.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework17.domain.BookMongo;

public interface BookMongoRepository extends MongoRepository<BookMongo, Long> {
}
