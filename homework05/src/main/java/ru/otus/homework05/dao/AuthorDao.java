package ru.otus.homework05.dao;

import ru.otus.homework05.domain.Author;

import java.util.List;

public interface AuthorDao {
    long insertByName(String name);
    List<Author> getAll();
    Author getById(long id);
    boolean existById(long id);
    void update(Author newAuthor);
    void delete(Author author);
}
