package ru.otus.homework17.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework17.domain.GenreMongo;

public interface GenreMongoRepository extends MongoRepository<GenreMongo, Long> {
}
