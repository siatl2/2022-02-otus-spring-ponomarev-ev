package ru.otus.homework13.service;

import ru.otus.homework13.model.Author;

import java.util.List;

public interface AuthorCrud {
    List<Author> readAllAuthors();
}

