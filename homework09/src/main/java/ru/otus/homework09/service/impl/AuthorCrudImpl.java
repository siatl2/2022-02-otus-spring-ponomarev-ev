package ru.otus.homework09.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.homework09.model.Author;
import ru.otus.homework09.repository.AuthorRepository;
import ru.otus.homework09.service.AuthorCrud;

import java.util.List;

@Service
public class AuthorCrudImpl implements AuthorCrud {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorCrudImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> readAllAuthors() {
        return authorRepository.findAll();
    }
}

