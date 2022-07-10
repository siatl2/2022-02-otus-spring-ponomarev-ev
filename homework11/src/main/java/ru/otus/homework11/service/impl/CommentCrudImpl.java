package ru.otus.homework11.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import ru.otus.homework11.exception.NotFoundException;
import ru.otus.homework11.model.Book;
import ru.otus.homework11.model.Comment;
import ru.otus.homework11.repository.BookRepository;
import ru.otus.homework11.repository.CommentRepository;
import ru.otus.homework11.service.CommentCrud;
import ru.otus.homework11.service.SequenceGenerator;


@Service
public class CommentCrudImpl implements CommentCrud {
    private static final String SEQ_COMMENT = "SEQ_COMMENT";
    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;
    private final SequenceGenerator generator;

    @Autowired
    public CommentCrudImpl(BookRepository bookRepository
            , CommentRepository commentRepository
            , SequenceGenerator generator) {
        this.bookRepository = bookRepository;
        this.commentRepository = commentRepository;
        this.generator = generator;
    }

    @Override
    public void createComment(long bookId, String name) {
        Book book = bookRepository.findById(bookId).block();
        if (book == null) {
            throw new NotFoundException("Don't exist book");
        }
        long id = generator.getSequenceNumber(SEQ_COMMENT);
        Comment comment = new Comment(id, name, book);
        commentRepository.save(comment).subscribe();
    }

    @Override
    public Flux<Comment> readAllCommentsByBookId(long bookId) {
        return commentRepository.findAllByBookId(bookId);
    }
}

