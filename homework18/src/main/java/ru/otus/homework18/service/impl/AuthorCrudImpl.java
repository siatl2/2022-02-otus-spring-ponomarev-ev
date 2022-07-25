package ru.otus.homework18.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.homework18.exception.UnavailableException;
import ru.otus.homework18.model.Author;
import ru.otus.homework18.repository.AuthorRepository;
import ru.otus.homework18.service.AuthorCrud;

import java.util.List;

@Service
public class AuthorCrudImpl implements AuthorCrud {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorCrudImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    @HystrixCommand(commandKey = "getInfo", fallbackMethod = "errorReadAllAuthors")
    public List<Author> readAllAuthors() {
        return authorRepository.findAll();
    }

    public List<Author> errorReadAllAuthors() {
        throw new UnavailableException();
    }
}

