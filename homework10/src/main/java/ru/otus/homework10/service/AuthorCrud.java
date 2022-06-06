package ru.otus.homework10.service;

import ru.otus.homework10.model.Author;

import java.util.List;

public interface AuthorCrud {
    List<Author> readAllAuthors();
}

