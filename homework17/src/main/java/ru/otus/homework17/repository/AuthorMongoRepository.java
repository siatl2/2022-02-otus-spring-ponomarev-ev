package ru.otus.homework17.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework17.domain.AuthorMongo;

public interface AuthorMongoRepository extends MongoRepository<AuthorMongo, Long> {
}
