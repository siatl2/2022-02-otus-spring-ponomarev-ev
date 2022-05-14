package ru.otus.homework08.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.homework08.exception.LibraryException;
import ru.otus.homework08.model.Book;
import ru.otus.homework08.model.Comment;
import ru.otus.homework08.repository.BookRepository;
import ru.otus.homework08.repository.CommentRepository;
import ru.otus.homework08.service.CommentCrud;
import ru.otus.homework08.service.CommentOutput;
import ru.otus.homework08.service.SequenceGenerator;

import java.util.List;
import java.util.Optional;

@Service
public class CommentCrudImpl implements CommentCrud {
    private static final String SEQ_COMMENT = "SEQ_COMMENT";
    private final BookRepository bookRepository;
    private final CommentOutput commentOutput;
    private final CommentRepository commentRepository;
    private final SequenceGenerator generator;

    @Autowired
    public CommentCrudImpl(BookRepository bookRepository,
                           CommentOutput commentOutput,
                           CommentRepository commentRepository,
                           SequenceGenerator generator) {
        this.bookRepository = bookRepository;
        this.commentOutput = commentOutput;
        this.commentRepository = commentRepository;
        this.generator = generator;
    }

    @Override
    public void createComment(long bookId, String name) {
        Optional<Book> book = bookRepository.findById(bookId);
        if (!book.isPresent()){
            throw new LibraryException("Don't exist book");
        }
        long id = generator.getSequenceNumber(SEQ_COMMENT);
        Comment comment = new Comment(id, name, book.get());
        commentRepository.save(comment);
    }

    @Override
    public void readAllCommentsByBookId(long bookId) {
        List<Comment> commnents = commentRepository.findAllByBookId(bookId);
        commentOutput.outputComments(commnents);
    }

    @Override
    public void retrieveComment(long id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isPresent()) {
            throw new LibraryException("Don't exist comment");
        }
        commentOutput.outputComment(comment.get());
    }

    @Override
    public void updateComment(long id, long bookId, String name) {
        Optional<Book> book = bookRepository.findById(bookId);
        if (!book.isPresent()){
            throw new LibraryException("Don't exist book");
        }

        if (!commentRepository.existsById(id)){
            throw new LibraryException("Don't exist comment");
        }

        Comment comment = new Comment(id, name, book.get());
        commentRepository.save(comment);
    }

    @Override
    public void deleteComment(long id) {
        commentRepository.deleteById(id);
    }
}

