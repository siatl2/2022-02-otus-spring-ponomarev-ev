package ru.otus.homework12.service;

import ru.otus.homework12.model.Comment;

import java.util.List;

public interface CommentCrud {
    List<Comment> readAllCommentsByBookId(long bookId);
}

