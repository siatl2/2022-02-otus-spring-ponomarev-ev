package ru.otus.homework11.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
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
    public Mono<Comment> createComment(long bookId, String name) {
        return Mono.just(new Comment())
                .zipWith(generator.getNextCounter(SEQ_COMMENT), (comment, counter) -> {
                    comment.setId(counter.getSequenceNumber());
                    comment.setName(name);
                    return comment;
                }).zipWith(bookRepository.findById(bookId), (comment, book) -> {
                    comment.setBook(book);
                    return comment;
                })
                .flatMap(commentRepository::save);
    }

    @Override
    public Flux<Comment> readAllCommentsByBookId(long bookId) {
        return commentRepository.findAllByBookId(bookId);
    }
}

