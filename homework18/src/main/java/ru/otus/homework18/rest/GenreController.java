package ru.otus.homework18.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.otus.homework18.rest.dto.GenreDto;
import ru.otus.homework18.service.GenreCrud;

@RestController
@RequestMapping("genres")
public class GenreController {
    private final GenreCrud genreCrud;

    @Autowired
    public GenreController(GenreCrud genreCrud) {
        this.genreCrud = genreCrud;
    }

    @GetMapping
    public Flux<GenreDto> readAllGenres() {
        return genreCrud.readAllGenres()
                .map(GenreDto::toDto);
    }
}
