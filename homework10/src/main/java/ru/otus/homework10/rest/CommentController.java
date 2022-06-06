package ru.otus.homework10.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.homework10.rest.dto.CommentDto;
import ru.otus.homework10.service.CommentCrud;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("comments")
public class CommentController {
    private final CommentCrud commentCrud;

    @Autowired
    public CommentController(CommentCrud commentCrud) {
        this.commentCrud = commentCrud;
    }

    @GetMapping("{bookId}")
    public List<CommentDto> readAllComments(@PathVariable long bookId) {
        return commentCrud.readAllCommentsByBookId(bookId).stream()
                .map(CommentDto::toDto)
                .collect(Collectors.toList());
    }
}
