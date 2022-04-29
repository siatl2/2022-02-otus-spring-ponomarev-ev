package ru.otus.homework06.service;

import ru.otus.homework06.model.Comment;

import java.util.List;

public interface CommentOutput {
    void outputComment(Comment comment);
    void outputComments(List<Comment> comments);
}
