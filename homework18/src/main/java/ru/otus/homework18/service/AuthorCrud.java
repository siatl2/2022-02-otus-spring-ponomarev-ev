package ru.otus.homework18.service;

import ru.otus.homework18.model.Author;

import java.util.List;

public interface AuthorCrud {
    List<Author> readAllAuthors();
}

