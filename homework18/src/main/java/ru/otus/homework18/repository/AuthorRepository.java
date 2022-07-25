package ru.otus.homework18.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework18.model.Author;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findAll();
}

