package ru.otus.homework14.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework14.domain.BookMongo;

public interface BookMongoRepository extends MongoRepository<BookMongo, Long> {
}
