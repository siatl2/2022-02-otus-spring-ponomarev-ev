package ru.otus.homework18.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.homework18.model.Book;

public interface BookCrud {
    Mono<Book> createBook(String name, long authorId, long genreId);

    Flux<Book> readAllBooks();

    Mono<Book> retrieveBook(long id);

    Flux<Void> deleteBook(long id);

    Mono<Book> saveBook(Book book);

    Flux<Book> findByName(String name);
}

