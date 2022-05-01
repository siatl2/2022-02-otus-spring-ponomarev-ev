package ru.otus.homework06.service;

import ru.otus.homework06.model.Book;

import java.util.List;

public interface BookOutput {
    void outputBook(Book book);
    void outputBooks(List<Book> books);
}
