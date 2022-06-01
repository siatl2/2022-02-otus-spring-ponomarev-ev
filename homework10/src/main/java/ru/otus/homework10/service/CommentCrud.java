package ru.otus.homework10.service;

import ru.otus.homework10.model.Comment;

import java.util.List;

public interface CommentCrud {
    List<Comment> readAllCommentsByBookId(long bookId);
}

