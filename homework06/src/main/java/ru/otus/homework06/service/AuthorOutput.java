package ru.otus.homework06.service;

import ru.otus.homework06.model.Author;

import java.util.List;

public interface AuthorOutput {
    void outputAuthor(Author author);
    void outputAuthors(List<Author> authors);
}
