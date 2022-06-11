package ru.otus.homework13.service;

import org.springframework.security.access.prepost.PreAuthorize;
import ru.otus.homework13.model.Comment;

import java.util.List;

public interface CommentCrud {
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    List<Comment> readAllCommentsByBookId(long bookId);
}

