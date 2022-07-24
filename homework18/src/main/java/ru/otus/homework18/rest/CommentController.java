package ru.otus.homework18.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.otus.homework18.rest.dto.CommentDto;
import ru.otus.homework18.service.CommentCrud;

@RestController
@RequestMapping("comments")
public class CommentController {
    private final CommentCrud commentCrud;

    @Autowired
    public CommentController(CommentCrud commentCrud) {
        this.commentCrud = commentCrud;
    }

    @GetMapping("{bookId}")
    public Flux<CommentDto> readAllComments(@PathVariable long bookId) {
        return commentCrud.readAllCommentsByBookId(bookId)
                .map(CommentDto::toDto);
    }
}
