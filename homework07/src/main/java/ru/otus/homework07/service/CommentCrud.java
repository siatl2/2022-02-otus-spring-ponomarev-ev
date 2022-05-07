package ru.otus.homework07.service;

public interface CommentCrud {
    void createComment(long bookId, String name);
    void readAllCommentsByBookId(long bookId);
    void retrieveComment(long id);
    void updateComment(long id, long bookId, String name);
    void deleteComment(long id);
}

