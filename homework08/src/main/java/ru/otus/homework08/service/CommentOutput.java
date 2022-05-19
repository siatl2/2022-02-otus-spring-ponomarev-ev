package ru.otus.homework08.service;

import ru.otus.homework08.model.Comment;

import java.util.List;

public interface CommentOutput {
    void outputComment(Comment comment);
    void outputComments(List<Comment> comments);
}

