package ru.otus.homework09.service;

import ru.otus.homework09.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookCrud {
    List<Book> readAllBooks();

    Optional<Book> retrieveBook(long id);

    void deleteBook(long id);

    void saveBook(Book book);
}

