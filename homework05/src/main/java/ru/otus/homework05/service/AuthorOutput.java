package ru.otus.homework05.service;

import ru.otus.homework05.domain.Author;

import java.util.List;

public interface AuthorOutput {
    void outputAuthor(Author author);
    void outputAuthors(List<Author> authors);
}
