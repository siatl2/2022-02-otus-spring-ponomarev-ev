package ru.otus.homework13.service;

import org.springframework.security.access.prepost.PreAuthorize;
import ru.otus.homework13.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookCrud {
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    List<Book> readAllBooks();

    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    Optional<Book> retrieveBook(long id);

    @PreAuthorize("hasAuthority('ADMIN')")
    void deleteBook(long id);

    @PreAuthorize("hasAuthority('ADMIN')")
    void saveBook(Book book);
}

