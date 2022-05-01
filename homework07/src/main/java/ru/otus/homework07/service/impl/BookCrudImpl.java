package ru.otus.homework07.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.homework07.exception.LibraryException;
import ru.otus.homework07.model.Author;
import ru.otus.homework07.model.Book;
import ru.otus.homework07.model.Genre;
import ru.otus.homework07.repository.AuthorRepository;
import ru.otus.homework07.repository.BookRepository;
import ru.otus.homework07.repository.GenreRepository;
import ru.otus.homework07.service.BookCrud;
import ru.otus.homework07.service.BookOutput;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BookCrudImpl implements BookCrud {
    private final AuthorRepository authorRepository;
    private final BookOutput bookOutput;
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;

    @Autowired
    public BookCrudImpl(AuthorRepository authorRepository, BookOutput bookOutput, BookRepository bookRepository, GenreRepository genreRepository) {
        this.authorRepository = authorRepository;
        this.bookOutput = bookOutput;
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
    }

    @Transactional
    @Override
    public void createBook(String name, long authorId, long genreId) {
        Optional<Author> author = authorRepository.findById(authorId);
        if (!author.isPresent()){
            throw new LibraryException("Author id not exist");
        }
        Optional<Genre> genre = genreRepository.findById(genreId);
        if (!author.isPresent()){
            throw new LibraryException("Genre id not exist");
        }
        Book book = new Book(0, name, author.get(), genre.get());
        bookRepository.save(book);
    }

    @Override
    public void readAllBooks() {
        List<Book> books = bookRepository.findAll();
        bookOutput.outputBooks(books);
    }

    @Override
    public void retrieveBook(long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (!book.isPresent()){
            throw new LibraryException("Book id not exist");
        }
        bookOutput.outputBook(book.get());
    }

    @Transactional
    @Override
    public void updateBook(long id, String name, long authorId, long genreId) {
        Optional<Author> author = authorRepository.findById(authorId);
        if (!author.isPresent()){
            throw new LibraryException("Author id not exist");
        }
        Optional<Genre> genre = genreRepository.findById(genreId);
        if (!author.isPresent()){
            throw new LibraryException("Genre id not exist");
        }
        if (!bookRepository.existsById(id)){
            throw new LibraryException("Don't exist book");
        }
        Book book = new Book(id, name, author.get(), genre.get());
        bookRepository.save(book);
    }

    @Transactional
    @Override
    public void deleteBook(long id) {
        bookRepository.deleteById(id);
    }
}

