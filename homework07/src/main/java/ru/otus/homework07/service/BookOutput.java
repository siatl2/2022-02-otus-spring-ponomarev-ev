package ru.otus.homework07.service;

import ru.otus.homework07.model.Book;

import java.util.List;

public interface BookOutput {
    void outputBook(Book book);
    void outputBooks(List<Book> books);
}
