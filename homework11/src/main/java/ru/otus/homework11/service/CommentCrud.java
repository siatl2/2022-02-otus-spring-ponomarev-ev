package ru.otus.homework11.service;

import reactor.core.publisher.Flux;
import ru.otus.homework11.model.Comment;


public interface CommentCrud {
    void createComment(long bookId, String name);

    Flux<Comment> readAllCommentsByBookId(long bookId);
}

