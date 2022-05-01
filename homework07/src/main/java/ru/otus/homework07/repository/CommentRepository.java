package ru.otus.homework07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework07.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByBookId(Long id);
}
