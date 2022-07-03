package ru.otus.homework14.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework14.domain.AuthorMongo;

public interface AuthorMongoRepository extends MongoRepository<AuthorMongo, Long> {
}
