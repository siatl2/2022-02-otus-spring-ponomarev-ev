package ru.otus.homework06.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.stereotype.Controller;
import ru.otus.homework06.service.*;

@Controller
@ShellComponent
public class RunnerImpl {
    private final AuthorCrud authorCrud;
    private final BookCrud bookCrud;
    private final CommentCrud commentCrud;
    private final GenreCrud genreCrud;

    @Autowired
    public RunnerImpl(
            AuthorCrud authorCrud
            , BookCrud bookCrud
            , CommentCrud commentCrud
            , GenreCrud genreCrud
            ) {
        this.authorCrud = authorCrud;
        this.bookCrud = bookCrud;
        this.commentCrud = commentCrud;
        this.genreCrud = genreCrud;
    }

    @ShellMethod("Create book")
    public void createBook(
            @ShellOption String name
            , @ShellOption long authorId
            , @ShellOption long genreId
            ) {
        bookCrud.createBook(name, authorId, genreId);
    }

    @ShellMethod("Real All Books")
    public void readAllBooks() {
        bookCrud.readAllBooks();
    }

    @ShellMethod("Retrieve Book")
    public void retrieveBook(@ShellOption long id) {
        bookCrud.retrieveBook(id);
    }

    @ShellMethod("Update Book")
    public void updateBook(
            @ShellOption long id
            , @ShellOption String name
            , @ShellOption long authorId
            , @ShellOption long genreId
            ) {
        bookCrud.updateBook(id, name, authorId, genreId);
    }

    @ShellMethod("Delete Book")
    public void deleteBook(@ShellOption long id) {
        bookCrud.deleteBook(id);
    }

    @ShellMethod("Create Author")
    public void createAuthor(@ShellOption String name) {
        authorCrud.createAuthor(name);
    }

    @ShellMethod("Read All Authors")
    public void readAllAuthors() {
        authorCrud.readAllAuthors();
    }

    @ShellMethod("Retrieve Author")
    public void retrieveAuthor(@ShellOption long id) {
        authorCrud.retrieveAuthor(id);
    }

    @ShellMethod("Update Author")
    public void updateAuthor(@ShellOption long id, @ShellOption String name) {
        authorCrud.updateAuthor(id, name);
    }

    @ShellMethod("Delete Author")
    public void deleteAuthor(@ShellOption long id) {
        authorCrud.deleteAuthor(id);
    }

    @ShellMethod("Create Comment")
    public void createComment(@ShellOption long bookId, @ShellOption String name) {
        commentCrud.createComment(bookId, name);
    }

    @ShellMethod("Read All Comments")
    public void readAllComments(@ShellOption long bookId) {
        commentCrud.readAllCommentsByBookId(bookId);
    }

    @ShellMethod("Retrieve Comment")
    public void retrieveComment(@ShellOption long id) {
        commentCrud.retrieveComment(id);
    }

    @ShellMethod("Update Comment")
    public void updateComment(@ShellOption long id, @ShellOption long bookId, @ShellOption String name) {
        commentCrud.updateComment(id, bookId, name);
    }

    @ShellMethod("Delete Comment")
    public void deleteComment(@ShellOption long id) {
        commentCrud.deleteComment(id);
    }

    @ShellMethod("Create Genre")
    public void createGenre(@ShellOption String name) {
        genreCrud.createGenre(name);
    }

    @ShellMethod("Read All Genres")
    public void readAllGenres() {
        genreCrud.readAllGenres();
    }

    @ShellMethod("Retrieve Genre")
    public void retrieveGenre(@ShellOption long id) {
        genreCrud.retrieveGenre(id);
    }

    @ShellMethod("Update Genre")
    public void updateGenre(@ShellOption long id, @ShellOption String name) {
        genreCrud.updateGenre(id, name);
    }

    @ShellMethod("Delete Genre")
    public void deleteGenre(@ShellOption long id) {
        genreCrud.deleteGenre(id);
    }

}
