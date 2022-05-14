package ru.otus.homework08.service;

import ru.otus.homework08.model.Author;

import java.util.List;

public interface AuthorCrud {
    void createAuthor(String name);
    void readAllAuthors();
    void retrieveAuthor(long id);
    void updateAuthor(long id, String name);
    void deleteAuthor(long id);
    List<Author> findByName(String name);
}

