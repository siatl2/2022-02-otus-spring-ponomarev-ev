package ru.otus.homework18.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.homework18.model.Book;

public interface BookRepository extends ReactiveMongoRepository<Book, Long> {
    @Override
    Flux<Book> findAll();

    @Override
    Mono<Book> findById(Long aLong);

    @Query("{'author.$id' : :#{#authorId}}")
    Flux<Book> findByAuthorId(@Param("authorId") Long authorId);

    @Query("{'genre.$id' : :#{#genreId}}")
    Flux<Book> findByGenreId(@Param("genreId") Long genreId);

    Flux<Book> findByName(String name);
}

