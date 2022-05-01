package ru.otus.homework06.service;

public interface BookCrud {
    void createBook(String name, long authorId, long genreId);
    void readAllBooks();
    void retrieveBook(long id);
    void updateBook(long id, String name, long authorId, long genreId);
    void deleteBook(long id);
}
