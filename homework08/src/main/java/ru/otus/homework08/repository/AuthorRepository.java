package ru.otus.homework08.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework08.model.Author;

import java.util.List;

public interface AuthorRepository extends MongoRepository<Author, Long> {
    List<Author> findAll();

    List<Author> findByName(String name);
}

