package ru.otus.homework06.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.homework06.exception.LibraryException;
import ru.otus.homework06.model.Book;
import ru.otus.homework06.model.Comment;
import ru.otus.homework06.repository.BookRepository;
import ru.otus.homework06.repository.CommentRepository;
import ru.otus.homework06.service.CommentCrud;
import ru.otus.homework06.service.CommentOutput;

import javax.management.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CommentCrudImpl implements CommentCrud {
    private final BookRepository bookRepository;
    private final CommentOutput commentOutput;
    private final CommentRepository commentRepository;

    @Autowired
    public CommentCrudImpl(BookRepository bookRepository, CommentOutput commentOutput, CommentRepository commentRepository) {
        this.bookRepository = bookRepository;
        this.commentOutput = commentOutput;
        this.commentRepository = commentRepository;
    }

    @Transactional
    @Override
    public void createComment(long bookId, String name) {
        Optional<Book> book = bookRepository.findById(bookId);
        if (!book.isPresent()){
            throw new LibraryException("Don't exist book");
        }
        Comment comment = new Comment(0, name, book.get());
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

    @Transactional
    @Override
    public void updateComment(long id, long bookId, String name) {
        Optional<Book> book = bookRepository.findById(bookId);
        if (!book.isPresent()){
            throw new LibraryException("Don't exist book");
        }

        if (!commentRepository.existById(id)){
            throw new LibraryException("Don't exist comment");
        }

        Comment comment = new Comment(id, name, book.get());
        commentRepository.save(comment);
    }

    @Transactional
    @Override
    public void deleteComment(long id) {
        commentRepository.deleteById(id);
    }
}
