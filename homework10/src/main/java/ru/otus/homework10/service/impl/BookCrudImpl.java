package ru.otus.homework10.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.homework10.exception.NotFoundException;
import ru.otus.homework10.model.Author;
import ru.otus.homework10.model.Book;
import ru.otus.homework10.model.Genre;
import ru.otus.homework10.repository.AuthorRepository;
import ru.otus.homework10.repository.BookRepository;
import ru.otus.homework10.repository.GenreRepository;
import ru.otus.homework10.service.BookCrud;

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

    @Transactional
    @Override
    public void deleteBook(long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public boolean existsById(long id) {
        return bookRepository.existsById(id);
    }
}

