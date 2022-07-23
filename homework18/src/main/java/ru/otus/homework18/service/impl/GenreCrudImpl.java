package ru.otus.homework18.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.homework18.model.Genre;
import ru.otus.homework18.repository.GenreRepository;
import ru.otus.homework18.service.GenreCrud;
import ru.otus.homework18.service.SequenceGenerator;

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
    public Mono<Genre> createGenre(String name) {
        return Mono.just(new Genre())
                .zipWith(generator.getNextCounter(SEQ_GENRE), (genre, counter) -> {
                    genre.setId(counter.getSequenceNumber());
                    genre.setName(name);
                    return genre;
                })
                .flatMap(genreRepository::save);
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

