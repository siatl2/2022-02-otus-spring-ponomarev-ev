package ru.otus.homework05.dao;

import ru.otus.homework05.domain.Author;
import ru.otus.homework05.domain.Book;
import ru.otus.homework05.domain.Genre;

import java.util.List;

public interface BookDao {
    long insertByNameAuthorIdGenreId(String name, long authorId, long genreId);
    List<Book> getAll();
    Book getById(long id);
    boolean existById(long id);
    void update(Book newBook);
    void delete(Book book);
}
