package ru.otus.homework11.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;
import ru.otus.homework11.model.Comment;

public interface CommentRepository extends ReactiveMongoRepository<Comment, Long> {
    @Query("{'book.$id' : :#{#bookId}}")
    Flux<Comment> findAllByBookId(@Param("bookId") Long id);
}

