package ru.otus.homework13.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.homework13.model.Book;
import ru.otus.homework13.repository.BookRepository;
import ru.otus.homework13.service.BookCrud;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BookCrudImpl implements BookCrud {
    private final BookRepository bookRepository;

    @Autowired
    public BookCrudImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
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

