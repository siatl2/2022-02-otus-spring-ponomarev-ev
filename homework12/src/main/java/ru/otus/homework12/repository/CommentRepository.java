package ru.otus.homework12.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework12.model.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByBookId(Long id);
}

