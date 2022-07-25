package ru.otus.homework18.service;

import ru.otus.homework18.model.Comment;

import java.util.List;

public interface CommentCrud {
    List<Comment> readAllCommentsByBookId(long bookId);
}

