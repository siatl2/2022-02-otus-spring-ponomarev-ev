package ru.otus.homework06.repository;

import ru.otus.homework06.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {
    Author save(Author author);
    List<Author> findAll();
    Optional<Author> findById(long id);
    boolean existById(long id);
    void deleteById(long id);
}
