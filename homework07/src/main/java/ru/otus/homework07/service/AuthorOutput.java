package ru.otus.homework07.service;

import ru.otus.homework07.model.Author;

import java.util.List;

public interface AuthorOutput {
    void outputAuthor(Author author);
    void outputAuthors(List<Author> authors);
}

