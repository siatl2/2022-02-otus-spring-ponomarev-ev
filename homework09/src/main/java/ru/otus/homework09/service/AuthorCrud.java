package ru.otus.homework09.service;

import ru.otus.homework09.model.Author;

import java.util.List;

public interface AuthorCrud {
    List<Author> readAllAuthors();
}

