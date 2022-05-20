package ru.otus.homework09.service;

import ru.otus.homework09.model.Comment;

import java.util.List;

public interface CommentCrud {
    List<Comment> readAllCommentsByBookId(long bookId);
}

