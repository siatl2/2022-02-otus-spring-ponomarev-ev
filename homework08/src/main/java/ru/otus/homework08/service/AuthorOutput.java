package ru.otus.homework08.service;

import ru.otus.homework08.model.Author;

import java.util.List;

public interface AuthorOutput {
    void outputAuthor(Author author);
    void outputAuthors(List<Author> authors);
}

