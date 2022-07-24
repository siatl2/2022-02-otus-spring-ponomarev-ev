package ru.otus.homework18.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.homework18.exception.UnavailableException;
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
    @HystrixCommand(commandKey = "getInfo", fallbackMethod = "errorCreateGenre")
    public Mono<Genre> createGenre(String name) {
        return Mono.just(new Genre())
                .zipWith(generator.getNextCounter(SEQ_GENRE), (genre, counter) -> {
                    genre.setId(counter.getSequenceNumber());
                    genre.setName(name);
                    return genre;
                })
                .flatMap(genreRepository::save);
    }

    public Mono<Genre> errorCreateGenre(String name) {
        return Mono.error(new UnavailableException());
    }

    @Override
    @HystrixCommand(commandKey = "getInfo", fallbackMethod = "errorReadAllGenres")
    public Flux<Genre> readAllGenres() {
        return genreRepository.findAll();
    }

    public Flux<Genre> errorReadAllGenres() {
        return Flux.error(new UnavailableException());
    }

    @Override
    @HystrixCommand(commandKey = "getInfo", fallbackMethod = "errorFindByName")
    public Flux<Genre> findByName(String name) {
        return genreRepository.findByName(name);
    }

    public Flux<Genre> errorFindByName(String name) {
        return Flux.error(new UnavailableException());
    }
}

