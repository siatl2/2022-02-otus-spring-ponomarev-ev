package ru.otus.homework07.service;

import ru.otus.homework07.model.Comment;

import java.util.List;

public interface CommentOutput {
    void outputComment(Comment comment);
    void outputComments(List<Comment> comments);
}

