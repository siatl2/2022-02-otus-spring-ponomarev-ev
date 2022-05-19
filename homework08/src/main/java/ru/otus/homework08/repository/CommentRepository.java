package ru.otus.homework08.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.homework08.model.Comment;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, Long> {
    @Query("{'book.$id' : :#{#bookId}}")
    List<Comment> findAllByBookId(@Param("bookId") Long id);
}

