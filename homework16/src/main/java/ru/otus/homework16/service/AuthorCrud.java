package ru.otus.homework16.service;

import ru.otus.homework16.model.Author;

import java.util.List;

public interface AuthorCrud {
    List<Author> readAllAuthors();
}
