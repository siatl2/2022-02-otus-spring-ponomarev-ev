package ru.otus.homework18.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.homework18.exception.UnavailableException;
import ru.otus.homework18.model.Book;
import ru.otus.homework18.repository.AuthorRepository;
import ru.otus.homework18.repository.BookRepository;
import ru.otus.homework18.repository.CommentRepository;
import ru.otus.homework18.repository.GenreRepository;
import ru.otus.homework18.service.BookCrud;
import ru.otus.homework18.service.SequenceGenerator;

@Service
public class BookCrudImpl implements BookCrud {
    private static final String SEQ_BOOK = "SEQ_BOOK";
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final CommentRepository commentRepository;
    private final SequenceGenerator generator;

    @Autowired
    public BookCrudImpl(AuthorRepository authorRepository
            , BookRepository bookRepository
            , GenreRepository genreRepository
            , CommentRepository commentRepository
            , SequenceGenerator generator) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        this.commentRepository = commentRepository;
        this.generator = generator;
    }

    @Override
    @HystrixCommand(commandKey = "getInfo", fallbackMethod = "errorCreateBook")
    public Mono<Book> createBook(String name, long authorId, long genreId) {
        return Mono.just(new Book())
                .zipWith(generator.getNextCounter(SEQ_BOOK), (book, counter) -> {
                    book.setId(counter.getSequenceNumber());
                    book.setName(name);
                    return book;
                }).zipWith(authorRepository.findById(authorId), (book, author) -> {
                    book.setAuthor(author);
                    return book;
                }).zipWith(genreRepository.findById(genreId), (book, genre) -> {
                    book.setGenre(genre);
                    return book;
                })
                .flatMap(bookRepository::save);
    }

    public Mono<Book> errorCreateBook(String name, long authorId, long genreId) {
        return Mono.error(new UnavailableException());
    }

    @Override
    @HystrixCommand(commandKey = "getInfo", fallbackMethod = "errorReadAllBooks")
    public Flux<Book> readAllBooks() {
        return bookRepository.findAll();
    }

    public Flux<Book> errorReadAllBooks() {
        return Flux.error(new UnavailableException());
    }

    @Override
    @HystrixCommand(commandKey = "getInfo", fallbackMethod = "errorRetrieveBook")
    public Mono<Book> retrieveBook(long id) {
        return bookRepository.findById(id);
    }

    public Mono<Book> errorRetrieveBook(long id) {
        return Mono.error(new UnavailableException());
    }

    @Transactional
    @Override
    @HystrixCommand(commandKey = "getInfo", fallbackMethod = "errorSaveBook")
    public Mono<Book> saveBook(Book book) {
        return Mono.just(book)
                .zipWith(generator.getNextCounter(SEQ_BOOK), (bookProcess, counter) -> {
                    if (book.getId() == 0) {
                        book.setId(counter.getSequenceNumber());
                    }
                    return book;
                })
                .flatMap(bookRepository::save);
    }

    public Mono<Book> errorSaveBook(Book book) {
        return Mono.error(new UnavailableException());
    }

    @Transactional
    @Override
    @HystrixCommand(commandKey = "getInfo", fallbackMethod = "errorDeleteBook")
    public Flux<Void> deleteBook(long id) {
        return bookRepository.deleteById(id).concatWith(
                commentRepository.deleteAll(commentRepository.findAllByBookId(id)));
    }

    public Flux<Void> errorDeleteBook(long id) {
        return Flux.error(new UnavailableException());
    }

    @Override
    @HystrixCommand(commandKey = "getInfo", fallbackMethod = "errorFindByName")
    public Flux<Book> findByName(String name) {
        return bookRepository.findByName(name);
    }

    public Flux<Book> errorFindByName(String name) {
        return Flux.error(new UnavailableException());
    }
}

