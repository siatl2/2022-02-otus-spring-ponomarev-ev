package ru.otus.homework16.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework16.model.Author;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findAll();
}

