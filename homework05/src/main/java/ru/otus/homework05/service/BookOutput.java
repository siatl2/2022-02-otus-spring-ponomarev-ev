package ru.otus.homework05.service;

import ru.otus.homework05.domain.Book;

import java.util.List;

public interface BookOutput {
    void outputBook(Book book);
    void outputBooks(List<Book> books);
}
