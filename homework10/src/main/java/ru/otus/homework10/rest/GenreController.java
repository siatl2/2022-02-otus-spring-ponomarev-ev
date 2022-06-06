package ru.otus.homework10.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.homework10.rest.dto.GenreDto;
import ru.otus.homework10.service.GenreCrud;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("genres")
public class GenreController {
    private final GenreCrud genreCrud;

    @Autowired
    public GenreController(GenreCrud genreCrud) {
        this.genreCrud = genreCrud;
    }

    @GetMapping
    public List<GenreDto> readAllGenres() {
        return genreCrud.readAllGenres().stream()
                .map(GenreDto::toDto)
                .collect(Collectors.toList());
    }
}
