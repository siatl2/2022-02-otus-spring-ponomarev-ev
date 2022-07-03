package ru.otus.homework13.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.homework13.exception.NotFoundException;
import ru.otus.homework13.model.Book;
import ru.otus.homework13.model.Comment;
import ru.otus.homework13.service.BookCrud;
import ru.otus.homework13.service.CommentCrud;
import ru.otus.homework13.service.impl.ReaderCurrentImpl;

import java.util.List;

@Controller
public class CommentController {
    private final BookCrud bookCrud;
    private final CommentCrud commentCrud;
    private final ReaderCurrentImpl readerCurrent;

    @Autowired
    public CommentController(BookCrud bookCrud, CommentCrud commentCrud, ReaderCurrentImpl readerCurrent) {
        this.bookCrud = bookCrud;
        this.commentCrud = commentCrud;
        this.readerCurrent = readerCurrent;
    }

    @GetMapping("/comments")
    public String readAllComments(@RequestParam long bookId
            , Model model) {
        Book book = bookCrud.retrieveBook(bookId).orElseThrow(NotFoundException::new);
        List<Comment> comments = commentCrud.readAllCommentsByBookId(bookId);
        model.addAttribute("bookName", book.getName());
        model.addAttribute("comments", comments);
        model.addAttribute("fullname", readerCurrent.getCurrentReader());
        return "comments";
    }
}
