package ru.otus.homework16.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.homework16.model.Genre;
import ru.otus.homework16.repository.GenreRepository;
import ru.otus.homework16.service.GenreCrud;

import java.util.List;

@Service
public class GenreCrudImpl implements GenreCrud {
    private final GenreRepository genreRepository;

    @Autowired
    public GenreCrudImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public List<Genre> readAllGenres() {
        return genreRepository.findAll();
    }
}

