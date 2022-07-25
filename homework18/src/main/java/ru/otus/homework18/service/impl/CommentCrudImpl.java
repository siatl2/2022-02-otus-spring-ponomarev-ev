package ru.otus.homework18.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.homework18.exception.UnavailableException;
import ru.otus.homework18.model.Comment;
import ru.otus.homework18.repository.CommentRepository;
import ru.otus.homework18.service.CommentCrud;

import java.util.List;

@Service
public class CommentCrudImpl implements CommentCrud {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentCrudImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    //@HystrixCommand(commandKey = "getInfo", fallbackMethod = "errorReadAllCommentsByBookId")
    public List<Comment> readAllCommentsByBookId(long bookId) {
        return commentRepository.findAllByBookId(bookId);
    }

    public List<Comment> errorReadAllCommentsByBookId(long bookId) {
        throw new UnavailableException();
    }
}

