package ru.otus.homework05.dao;

import ru.otus.homework05.domain.Author;

import java.util.List;

public interface AuthorDao {
    void insert(Author author);
    List<Author> getAll();
    Author getById(long id);
    boolean existById(long id);
    void update(Author author);
    void deleteById(long id);
}
