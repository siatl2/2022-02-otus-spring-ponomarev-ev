package ru.otus.homework12.service;

import ru.otus.homework12.model.Author;

import java.util.List;

public interface AuthorCrud {
    List<Author> readAllAuthors();
}

