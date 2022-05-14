package ru.otus.homework08.service;

import ru.otus.homework08.model.Book;

import java.util.List;

public interface BookOutput {
    void outputBook(Book book);
    void outputBooks(List<Book> books);
}

