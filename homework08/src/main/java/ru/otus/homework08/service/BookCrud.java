package ru.otus.homework08.service;

import ru.otus.homework08.model.Book;

import java.util.List;

public interface BookCrud {
    void createBook(String name, long authorId, long genreId);
    void readAllBooks();
    void retrieveBook(long id);
    void updateBook(long id, String name, long authorId, long genreId);
    void deleteBook(long id);
    List<Book> findByName(String name);
}

