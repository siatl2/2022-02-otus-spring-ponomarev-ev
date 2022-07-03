package ru.otus.homework13.service;

import ru.otus.homework13.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookCrud {
    List<Book> readAllBooks();

    Optional<Book> retrieveBook(long id);

    void deleteBook(long id);

    void saveBook(Book book);
}

