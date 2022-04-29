package ru.otus.homework06.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.stereotype.Controller;
import ru.otus.homework06.exception.LibraryException;
import ru.otus.homework06.model.Comment;
import ru.otus.homework06.repository.AuthorRepository;
import ru.otus.homework06.repository.BookRepository;
import ru.otus.homework06.repository.CommentRepository;
import ru.otus.homework06.repository.GenreRepository;
import ru.otus.homework06.model.Author;
import ru.otus.homework06.model.Book;
import ru.otus.homework06.model.Genre;
import ru.otus.homework06.service.AuthorOutput;
import ru.otus.homework06.service.BookOutput;
import ru.otus.homework06.service.CommentOutput;
import ru.otus.homework06.service.GenreOutput;

import java.util.List;
import java.util.Optional;

@Controller
@ShellComponent
public class RunnerImpl {
    private final AuthorOutput authorOutput;
    private final AuthorRepository authorRepository;

    private final GenreOutput genreOutput;
    private final GenreRepository genreRepository;

    private final BookOutput bookOutput;
    private final BookRepository bookRepository;
    private final CommentOutput commentOutput;
    private final CommentRepository commentRepository;

    @Autowired
    public RunnerImpl(
            AuthorOutput authorOutput
            , AuthorRepository authorRepository
            , GenreOutput genreOutput
            , GenreRepository genreRepository
            , BookOutput bookOutput
            , BookRepository bookRepository
            , CommentOutput commentOutput
            , CommentRepository commentRepository
            ) {
        this.authorOutput = authorOutput;
        this.authorRepository = authorRepository;
        this.genreOutput = genreOutput;
        this.genreRepository = genreRepository;
        this.bookOutput = bookOutput;
        this.bookRepository = bookRepository;
        this.commentOutput = commentOutput;
        this.commentRepository = commentRepository;
    }

    @ShellMethod("params: --name --authorId --genreId")
    public void createBook(
            @ShellOption String name
            , @ShellOption long authorId
            , @ShellOption long genreId
            ) {
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

    @ShellMethod("params: no")
    public void readAllBooks() {
        List<Book> books = bookRepository.findAll();
        bookOutput.outputBooks(books);
    }

    @ShellMethod("params: --id")
    public void retrieveBook(@ShellOption long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (!book.isPresent()){
            throw new LibraryException("Book id not exist");
        }
        bookOutput.outputBook(book.get());
    }

    @ShellMethod("params: --id --name --authorId --genreId")
    public void updateBook(
            @ShellOption long id
            , @ShellOption String name
            , @ShellOption long authorId
            , @ShellOption long genreId
            ) {
        Optional<Author> author = authorRepository.findById(authorId);
        if (!author.isPresent()){
            throw new LibraryException("Author id not exist");
        }
        Optional<Genre> genre = genreRepository.findById(genreId);
        if (!author.isPresent()){
            throw new LibraryException("Genre id not exist");
        }
        if (!bookRepository.existById(id)){
            throw new LibraryException("Don't exist book");
        }
        Book book = new Book(id, name, author.get(), genre.get());
        bookRepository.save(book);
    }

    @ShellMethod("params: --id")
    public void deleteBook(@ShellOption long id) {
        bookRepository.deleteById(id);
    }

    @ShellMethod("params: --name")
    public void createAuthor(@ShellOption String name) {
        Author author = new Author(0, name);
        authorRepository.save(author);
    }

    @ShellMethod("params: no")
    public void readAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        authorOutput.outputAuthors(authors);
    }

    @ShellMethod("params: --id")
    public void retrieveAuthor(@ShellOption long id) {
        Optional<Author> author = authorRepository.findById(id);
        if (!author.isPresent()) {
           throw new LibraryException("Don't exist author");
        }
        authorOutput.outputAuthor(author.get());
    }

    @ShellMethod("params: --id --name")
    public void updateAuthor(@ShellOption long id, @ShellOption String name) {
        if (!authorRepository.existById(id)){
            throw new LibraryException("Don't exist author");
        }
        Author author = new Author(id, name);
        authorRepository.save(author);
    }

    @ShellMethod("params: --id")
    public void deleteAuthor(@ShellOption long id) {
        authorRepository.deleteById(id);
    }

    @ShellMethod("params: --book-id --name")
    public void createComment(@ShellOption long bookId, @ShellOption String name) {
        Optional<Book> book = bookRepository.findById(bookId);
        if (!book.isPresent()){
            throw new LibraryException("Don't exist book");
        }
        Comment comment = new Comment(0, name, book.get());
        commentRepository.save(comment);
    }

    @ShellMethod("params: no")
    public void readAllComments() {
        List<Comment> commnents = commentRepository.findAll();
        commentOutput.outputComments(commnents);
    }

    @ShellMethod("params: --id")
    public void retrieveComment(@ShellOption long id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isPresent()) {
            throw new LibraryException("Don't exist comment");
        }
        commentOutput.outputComment(comment.get());
    }

    @ShellMethod("params: --id --bookId --name")
    public void updateComment(@ShellOption long id, @ShellOption long bookId, @ShellOption String name) {
        Optional<Book> book = bookRepository.findById(bookId);
        if (!book.isPresent()){
            throw new LibraryException("Don't exist book");
        }

        if (!commentRepository.existById(id)){
            throw new LibraryException("Don't exist comment");
        }

        Comment comment = new Comment(id, name, book.get());
        commentRepository.save(comment);
    }

    @ShellMethod("params: --id")
    public void deleteComment(@ShellOption long id) {
        commentRepository.deleteById(id);
    }

    @ShellMethod("params: --name")
    public void createGenre(@ShellOption String name) {
        Genre genre = new Genre(0, name);
        genreRepository.save(genre);
    }

    @ShellMethod("params: no")
    public void readAllGenres() {
        List<Genre> genres = genreRepository.findAll();
        genreOutput.outputGenres(genres);
    }

    @ShellMethod("params: --id")
    public void retrieveGenre(@ShellOption long id) {
        Optional<Genre> genre = genreRepository.findById(id);
        if (genre.isPresent()) {
            genreOutput.outputGenre(genre.get());
        }
    }

    @ShellMethod("params: --id --name")
    public void updateGenre(@ShellOption long id, @ShellOption String name) {
        if (!genreRepository.existById(id)){
            throw new LibraryException("Don't exist genre");
        }
        Genre genre = new Genre(id, name);
        genreRepository.save(genre);
    }

    @ShellMethod("params: --id")
    public void deleteGenre(@ShellOption long id) {
        genreRepository.deleteById(id);
    }

}
