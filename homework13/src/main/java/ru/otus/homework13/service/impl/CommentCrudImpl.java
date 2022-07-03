package ru.otus.homework13.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.homework13.model.Comment;
import ru.otus.homework13.repository.CommentRepository;
import ru.otus.homework13.service.CommentCrud;

import java.util.List;

@Service
public class CommentCrudImpl implements CommentCrud {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentCrudImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> readAllCommentsByBookId(long bookId) {
        return commentRepository.findAllByBookId(bookId);
    }
}

