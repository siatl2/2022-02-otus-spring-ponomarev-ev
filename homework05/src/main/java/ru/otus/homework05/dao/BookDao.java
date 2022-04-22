package ru.otus.homework05.dao;

import ru.otus.homework05.domain.Book;

import java.util.List;

public interface BookDao {
    void insert(Book book);
    List<Book> getAll();
    Book getById(long id);
    boolean existById(long id);
    void update(Book book);
    void deleteById(long id);
}
