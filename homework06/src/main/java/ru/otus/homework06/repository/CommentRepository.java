package ru.otus.homework06.repository;

import ru.otus.homework06.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    Comment save(Comment comment);
    List<Comment> findAllByBookId(Long id);
    Optional<Comment> findById(long id);
    boolean existById(long id);
    void deleteById(long id);
}
