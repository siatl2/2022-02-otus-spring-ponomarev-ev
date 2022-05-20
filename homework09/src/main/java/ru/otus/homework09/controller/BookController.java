package ru.otus.homework09.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.homework09.exception.NotFoundException;
import ru.otus.homework09.model.Author;
import ru.otus.homework09.model.Book;
import ru.otus.homework09.model.Genre;
import ru.otus.homework09.service.AuthorCrud;
import ru.otus.homework09.service.BookCrud;
import ru.otus.homework09.service.GenreCrud;

import java.util.List;

@Controller
public class BookController {
    private final AuthorCrud authorCrud;
    private final BookCrud bookCrud;
    private final GenreCrud genreCrud;

    @Autowired
    public BookController(
            AuthorCrud authorCrud
            , BookCrud bookCrud
            , GenreCrud genreCrud
    ) {
        this.authorCrud = authorCrud;
        this.bookCrud = bookCrud;
        this.genreCrud = genreCrud;
    }

    @PostMapping("/books/save")
    public String saveBook(
            Book book,
            Model model
    ) {
        bookCrud.saveBook(book);
        return "redirect:/books";
    }

    @GetMapping("/books/add")
    public String addBook(Model model) {
        List<Author> authors = authorCrud.readAllAuthors();
        List<Genre> genres = genreCrud.readAllGenres();
        model.addAttribute("mode", "add");
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authors);
        model.addAttribute("genres", genres);
        return "edit";
    }

    @GetMapping("/books")
    public String readAllBooks(Model model) {
        List<Book> books = bookCrud.readAllBooks();
        model.addAttribute("books", books);
        return "list";
    }

    @GetMapping("/books/get")
    public String retrieveBook(@RequestParam("id") long id
            , Model model) {
        Book book = bookCrud.retrieveBook(id).orElseThrow(NotFoundException::new);
        List<Author> authors = authorCrud.readAllAuthors();
        List<Genre> genres = genreCrud.readAllGenres();
        model.addAttribute("mode", "edit");
        model.addAttribute("book", book);
        model.addAttribute("authors", authors);
        model.addAttribute("genres", genres);
        return "edit";
    }

    @GetMapping("/books/delete")
    public String deleteBook(@RequestParam("id") long id,
                             Model model) {
        bookCrud.deleteBook(id);
        return "redirect:/books";
    }
}
