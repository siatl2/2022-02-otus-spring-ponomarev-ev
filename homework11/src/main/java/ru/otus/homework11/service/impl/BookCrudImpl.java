package ru.otus.homework11.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.homework11.exception.NotFoundException;
import ru.otus.homework11.model.Author;
import ru.otus.homework11.model.Book;
import ru.otus.homework11.model.Genre;
import ru.otus.homework11.repository.AuthorRepository;
import ru.otus.homework11.repository.BookRepository;
import ru.otus.homework11.repository.GenreRepository;
import ru.otus.homework11.service.BookCrud;
import ru.otus.homework11.service.SequenceGenerator;

@Service
public class BookCrudImpl implements BookCrud {
    private static final String SEQ_BOOK = "SEQ_BOOK";
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final SequenceGenerator generator;

    @Autowired
    public BookCrudImpl(AuthorRepository authorRepository
            , BookRepository bookRepository
            , GenreRepository genreRepository
            , SequenceGenerator generator) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        this.generator = generator;
    }

    @Override
    public void createBook(String name, long authorId, long genreId) {
        Author author = authorRepository.findById(authorId).block();
        if (author == null) {
            throw new NotFoundException("Author id not exist");
        }
        Genre genre = genreRepository.findById(genreId).block();
        if (genre == null) {
            throw new NotFoundException("Genre id not exist");
        }
        long id = generator.getSequenceNumber(SEQ_BOOK);
        Book book = new Book(id, name, author, genre);
        bookRepository.save(book).subscribe();
    }

    @Override
    public Flux<Book> readAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Mono<Book> retrieveBook(long id) {
        return bookRepository.findById(id);
    }

    @Transactional
    @Override
    public Mono<Book> saveBook(Book book) {
        long authorId = book.getAuthor().getId();
        long genreId = book.getGenre().getId();

        if ((!authorRepository.existsById(authorId).block()) ||
                (!genreRepository.existsById(genreId).block())) {
            throw new NotFoundException();
        }
        Author author = authorRepository.findById(authorId).block();
        Genre genre = genreRepository.findById(genreId).block();
        Book returnBook = new Book(book.getId() > 0 ? book.getId() : generator.getSequenceNumber(SEQ_BOOK),
                book.getName(),
                author,
                genre);

        bookRepository.save(returnBook).subscribe();
        return Mono.just(returnBook);
    }

    @Transactional
    @Override
    public void deleteBook(long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public boolean existsById(long id) {
        return bookRepository.existsById(id).block();
    }

    @Override
    public Flux<Book> findByName(String name) {
        return bookRepository.findByName(name);
    }
}

