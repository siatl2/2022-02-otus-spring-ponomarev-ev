package ru.otus.homework18.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.homework18.exception.UnavailableException;
import ru.otus.homework18.model.Genre;
import ru.otus.homework18.repository.GenreRepository;
import ru.otus.homework18.service.GenreCrud;

import java.util.List;

@Service
public class GenreCrudImpl implements GenreCrud {
    private final GenreRepository genreRepository;

    @Autowired
    public GenreCrudImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    @HystrixCommand(commandKey = "getInfo", fallbackMethod = "errorReadAllGenres")
    public List<Genre> readAllGenres() {
        return genreRepository.findAll();
    }

    public List<Genre> errorReadAllGenres() {
        throw new UnavailableException();
    }
}

