package ru.otus.homework14.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework14.domain.GenreMongo;

public interface GenreMongoRepository extends MongoRepository<GenreMongo, Long> {
}
