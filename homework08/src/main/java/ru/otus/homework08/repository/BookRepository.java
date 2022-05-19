package ru.otus.homework08.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.homework08.model.Book;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, Long> {

    List<Book> findAll();

    @Query("{'author.$id' : :#{#authorId}}")
    List<Book> findByAuthorId(@Param("authorId") Long authorId);

    @Query("{'genre.$id' : :#{#genreId}}")
    List<Book> findByGenreId(@Param("genreId") Long genreId);

    List<Book> findByName(String name);
}

