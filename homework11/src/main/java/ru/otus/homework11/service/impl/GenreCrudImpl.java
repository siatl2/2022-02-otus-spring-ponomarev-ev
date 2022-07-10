package ru.otus.homework11.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import ru.otus.homework11.model.Genre;
import ru.otus.homework11.repository.GenreRepository;
import ru.otus.homework11.service.GenreCrud;
import ru.otus.homework11.service.SequenceGenerator;

@Service
public class GenreCrudImpl implements GenreCrud {
    private static final String SEQ_GENRE = "SEQ_GENRE";
    private final GenreRepository genreRepository;
    private final SequenceGenerator generator;

    @Autowired
    public GenreCrudImpl(GenreRepository genreRepository,
                         SequenceGenerator generator) {
        this.genreRepository = genreRepository;
        this.generator = generator;
    }

    @Override
    public void createGenre(String name) {
        long id = generator.getSequenceNumber(SEQ_GENRE);
        Genre genre = new Genre(id, name);
        genreRepository.save(genre).subscribe();
    }

    @Override
    public Flux<Genre> readAllGenres() {
        return genreRepository.findAll();
    }

    @Override
    public Flux<Genre> findByName(String name) {
        return genreRepository.findByName(name);
    }
}

