package ru.otus.homework06.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.homework06.exception.LibraryException;
import ru.otus.homework06.model.Genre;
import ru.otus.homework06.repository.GenreRepository;
import ru.otus.homework06.service.GenreCrud;
import ru.otus.homework06.service.GenreOutput;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class GenreCrudImpl implements GenreCrud {
    private final GenreOutput genreOutput;
    private final GenreRepository genreRepository;

    @Autowired
    public GenreCrudImpl(GenreOutput genreOutput, GenreRepository genreRepository) {
        this.genreOutput = genreOutput;
        this.genreRepository = genreRepository;
    }

    @Transactional
    @Override
    public void createGenre(String name) {
        Genre genre = new Genre(0, name);
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

    @Transactional
    @Override
    public void updateGenre(long id, String name) {
        if (!genreRepository.existById(id)){
            throw new LibraryException("Don't exist genre");
        }
        Genre genre = new Genre(id, name);
        genreRepository.save(genre);
    }

    @Transactional
    @Override
    public void deleteGenre(long id) {
        genreRepository.deleteById(id);
    }
}
