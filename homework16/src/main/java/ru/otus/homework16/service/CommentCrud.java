package ru.otus.homework16.service;

import ru.otus.homework16.model.Comment;

import java.util.List;

public interface CommentCrud {
    List<Comment> readAllCommentsByBookId(long bookId);
}
