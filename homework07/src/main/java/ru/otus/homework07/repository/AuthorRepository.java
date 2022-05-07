package ru.otus.homework07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework07.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findAll();
}

