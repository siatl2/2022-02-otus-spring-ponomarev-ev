package ru.otus.homework06.repository;

import ru.otus.homework06.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Book save(Book book);
    List<Book> findAll();
    Optional<Book> findById(long id);
    boolean existById(long id);
    void deleteById(long id);
}
