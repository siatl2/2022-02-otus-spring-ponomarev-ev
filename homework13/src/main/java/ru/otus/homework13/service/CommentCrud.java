package ru.otus.homework13.service;

import ru.otus.homework13.model.Comment;

import java.util.List;

public interface CommentCrud {
    List<Comment> readAllCommentsByBookId(long bookId);
}

