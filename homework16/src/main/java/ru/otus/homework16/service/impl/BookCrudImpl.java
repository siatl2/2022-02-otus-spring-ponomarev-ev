package ru.otus.homework16.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.homework16.model.Book;
import ru.otus.homework16.repository.AuthorRepository;
import ru.otus.homework16.repository.BookRepository;
import ru.otus.homework16.repository.GenreRepository;
import ru.otus.homework16.service.BookCrud;

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
    public List<Book> readAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> retrieveBook(long id) {
        return bookRepository.findById(id);
    }

    @Transactional
    @Override
    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    @Transactional
    @Override
    public void deleteBook(long id) {
        bookRepository.deleteById(id);
    }
}

