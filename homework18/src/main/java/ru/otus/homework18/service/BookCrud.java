package ru.otus.homework18.service;

import ru.otus.homework18.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookCrud {
    List<Book> readAllBooks();

    Optional<Book> retrieveBook(long id);

    void deleteBook(long id);

    Book saveBook(Book book);

    boolean existsById(long id);
}

