package ru.otus.homework18.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.homework18.exception.NotFoundException;
import ru.otus.homework18.exception.UnavailableException;
import ru.otus.homework18.model.Author;
import ru.otus.homework18.model.Book;
import ru.otus.homework18.model.Genre;
import ru.otus.homework18.repository.AuthorRepository;
import ru.otus.homework18.repository.BookRepository;
import ru.otus.homework18.repository.GenreRepository;
import ru.otus.homework18.service.BookCrud;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BookCrudImpl implements BookCrud {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;

    @Autowired
    public BookCrudImpl(AuthorRepository authorRepository, BookRepository bookRepository, GenreRepository genreRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    @HystrixCommand(commandKey = "getInfo", fallbackMethod = "errorReadAllBooks")
    public List<Book> readAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> errorReadAllBooks() {
        throw new UnavailableException();
    }

    @Override
    @HystrixCommand(commandKey = "getInfo", fallbackMethod = "errorRetrieveBook")
    public Optional<Book> retrieveBook(long id) {
        return bookRepository.findById(id);
    }

    public Optional<Book> errorRetrieveBook(long id) {
        throw new UnavailableException();
    }

    @Transactional
    @Override
    @HystrixCommand(commandKey = "getInfo", fallbackMethod = "errorSaveBook")
    public Book saveBook(Book book) {
        long authorId = book.getAuthor().getId();
        long genreId = book.getGenre().getId();

        if ((!authorRepository.existsById(authorId)) ||
                (!genreRepository.existsById(genreId))) {
            throw new NotFoundException();
        }
        Author author = authorRepository.findById(authorId).get();
        Genre genre = genreRepository.findById(genreId).get();
        Book returnBook = new Book(book.getId(), book.getName(), author, genre);

        return bookRepository.save(returnBook);
    }

    public Book errorSaveBook(Book book) {
        throw new UnavailableException();
    }

    @Transactional
    @Override
    @HystrixCommand(commandKey = "getInfo", fallbackMethod = "errorDeleteBook")
    public void deleteBook(long id) {
        bookRepository.deleteById(id);
    }

    public void errorDeleteBook(long id) {
        throw new UnavailableException();
    }

    @Override
    @HystrixCommand(commandKey = "getInfo", fallbackMethod = "errorExistsById")
    public boolean existsById(long id) {
        return bookRepository.existsById(id);
    }

    public boolean errorExistsById(long id) {
        throw new UnavailableException();
    }
}

