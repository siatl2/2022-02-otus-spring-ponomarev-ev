package ru.otus.homework07.service;

public interface AuthorCrud {
    void createAuthor(String name);
    void readAllAuthors();
    void retrieveAuthor(long id);
    void updateAuthor(long id, String name);
    void deleteAuthor(long id);
}

