package ru.otus.homework08.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.homework08.exception.LibraryException;
import ru.otus.homework08.model.Genre;
import ru.otus.homework08.repository.GenreRepository;
import ru.otus.homework08.service.GenreCrud;
import ru.otus.homework08.service.GenreOutput;
import ru.otus.homework08.service.SequenceGenerator;

import java.util.List;
import java.util.Optional;

@Service
public class GenreCrudImpl implements GenreCrud {
    private static final String SEQ_GENRE = "SEQ_GENRE";
    private final GenreOutput genreOutput;
    private final GenreRepository genreRepository;
    private final SequenceGenerator generator;

    @Autowired
    public GenreCrudImpl(GenreOutput genreOutput,
                         GenreRepository genreRepository,
                         SequenceGenerator generator) {
        this.genreOutput = genreOutput;
        this.genreRepository = genreRepository;
        this.generator = generator;
    }

    @Override
    public void createGenre(String name) {
        long id = generator.getSequenceNumber(SEQ_GENRE);
        Genre genre = new Genre(id, name);
        genreRepository.save(genre);
    }

    @Override
    public void readAllGenres() {
        List<Genre> genres = genreRepository.findAll();
        genreOutput.outputGenres(genres);
    }

    @Override
    public void retrieveGenre(long id) {
        Optional<Genre> genre = genreRepository.findById(id);
        if (genre.isPresent()) {
            genreOutput.outputGenre(genre.get());
        }
    }

    @Override
    public void updateGenre(long id, String name) {
        if (!genreRepository.existsById(id)) {
            throw new LibraryException("Don't exist genre");
        }
        Genre genre = new Genre(id, name);
        genreRepository.save(genre);
    }

    @Override
    public void deleteGenre(long id) {
        genreRepository.deleteById(id);
    }

    @Override
    public List<Genre> findByName(String name) {
        return genreRepository.findByName(name);
    }
}

