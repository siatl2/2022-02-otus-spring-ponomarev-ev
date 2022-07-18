package ru.otus.homework16.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.homework16.exception.NotFoundException;
import ru.otus.homework16.model.Book;
import ru.otus.homework16.model.Comment;
import ru.otus.homework16.service.BookCrud;
import ru.otus.homework16.service.CommentCrud;

import java.util.List;

@Controller
public class CommentController {
    private final BookCrud bookCrud;
    private final CommentCrud commentCrud;

    @Autowired
    public CommentController(BookCrud bookCrud, CommentCrud commentCrud) {
        this.bookCrud = bookCrud;
        this.commentCrud = commentCrud;
    }

    @GetMapping("/comments")
    public String readAllComments(@RequestParam long bookId
            , Model model) {
        Book book = bookCrud.retrieveBook(bookId).orElseThrow(NotFoundException::new);
        List<Comment> comments = commentCrud.readAllCommentsByBookId(bookId);
        model.addAttribute("bookName", book.getName());
        model.addAttribute("comments", comments);
        return "comments";
    }
}
