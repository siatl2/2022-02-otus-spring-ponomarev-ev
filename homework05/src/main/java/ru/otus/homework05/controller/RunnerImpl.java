package ru.otus.homework05.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.stereotype.Controller;
import ru.otus.homework05.dao.AuthorDao;
import ru.otus.homework05.dao.BookDao;
import ru.otus.homework05.dao.GenreDao;
import ru.otus.homework05.domain.Author;
import ru.otus.homework05.domain.Book;
import ru.otus.homework05.domain.Genre;
import ru.otus.homework05.service.AuthorOutput;
import ru.otus.homework05.service.BookOutput;
import ru.otus.homework05.service.GenreOutput;

import java.util.List;

@Controller
@ShellComponent
public class RunnerImpl {
    private final AuthorOutput authorOutput;
    private final AuthorDao authorDao;

    private final GenreOutput genreOutput;
    private final GenreDao genreDao;

    private final BookOutput bookOutput;
    private final BookDao bookDao;

    @Autowired
    public RunnerImpl(
            AuthorOutput authorOutput
            , AuthorDao authorDao
            , GenreOutput genreOutput
            , GenreDao genreDao
            , BookOutput bookOutput
            , BookDao bookDao
            ) {
        this.authorOutput = authorOutput;
        this.authorDao = authorDao;
        this.genreOutput = genreOutput;
        this.genreDao = genreDao;
        this.bookOutput = bookOutput;
        this.bookDao = bookDao;
    }

    @ShellMethod("Create new book")
    public void createBook(
            @ShellOption String name
            , @ShellOption long authorId
            , @ShellOption long genreId
            ) {
        Author author = authorDao.getById(authorId);
        Genre genre = genreDao.getById(genreId);
        Book book = new Book(name, author, genre);
        bookDao.insert(book);
    }

    @ShellMethod("Read all books")
    public void readAllBooks() {
        List<Book> books = bookDao.getAll();
        bookOutput.outputBooks(books);
    }

    @ShellMethod("Retrieve a book")
    public void retrieveBook(@ShellOption long id) {
        Book book = bookDao.getById(id);
        bookOutput.outputBook(book);
    }

    @ShellMethod("Update book")
    public void updateBook(
            @ShellOption long id
            , @ShellOption String name
            , @ShellOption long authorId
            , @ShellOption long genreId
            ) {
        Author author = authorDao.getById(authorId);
        Genre genre = genreDao.getById(genreId);

        Book book = new Book(id, name, author, genre);
        bookDao.update(book);
    }

    @ShellMethod("Delete book")
    public void deleteBook(@ShellOption long id) {
        bookDao.deleteById(id);
    }

    @ShellMethod("Create new author")
    public void createAuthor(@ShellOption String name) {
        Author author = new Author(name);
        authorDao.insert(author);
    }

    @ShellMethod("Read all authors")
    public void readAllAuthors() {
        List<Author> authors = authorDao.getAll();
        authorOutput.outputAuthors(authors);
    }

    @ShellMethod("Retrieve a author")
    public void retrieveAuthor(@ShellOption long id) {
        Author author = authorDao.getById(id);
        authorOutput.outputAuthor(author);
    }

    @ShellMethod("Update author")
    public void updateAuthor(@ShellOption long id, @ShellOption String name) {
        Author author = new Author(id, name);
        authorDao.update(author);
    }

    @ShellMethod("Delete author")
    public void deleteAuthor(@ShellOption long id) {
        authorDao.deleteById(id);
    }

    @ShellMethod("Create new genre")
    public void createGenre(@ShellOption String name) {
        Genre genre = new Genre(name);
        genreDao.insert(genre);
    }

    @ShellMethod("Read all genries")
    public void readAllGenres() {
        List<Genre> genres = genreDao.getAll();
        genreOutput.outputGenres(genres);
    }

    @ShellMethod("Retrieve a genre")
    public void retrieveGenre(@ShellOption long id) {
        Genre genre = genreDao.getById(id);
        genreOutput.outputGenre(genre);
    }

    @ShellMethod("Update genre")
    public void updateGenre(@ShellOption long id, @ShellOption String name) {
        Genre genre = new Genre(id, name);
        genreDao.update(genre);
    }

    @ShellMethod("Delete genre")
    public void deleteGenre(@ShellOption long id) {
        genreDao.deleteById(id);
    }
}
