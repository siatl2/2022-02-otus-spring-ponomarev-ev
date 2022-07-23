package ru.otus.homework18.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.homework18.model.Comment;

public interface CommentCrud {
    Mono<Comment> createComment(long bookId, String name);

    Flux<Comment> readAllCommentsByBookId(long bookId);
}

