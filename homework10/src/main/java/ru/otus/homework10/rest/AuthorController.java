package ru.otus.homework10.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.homework10.rest.dto.AuthorDto;
import ru.otus.homework10.service.AuthorCrud;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("authors")
public class AuthorController {
    private final AuthorCrud authorCrud;

    @Autowired
    public AuthorController(AuthorCrud authorCrud) {
        this.authorCrud = authorCrud;
    }

    @GetMapping
    public List<AuthorDto> readAllAuthors() {
        return authorCrud.readAllAuthors().stream()
                .map(AuthorDto::toDto)
                .collect(Collectors.toList());
    }
}
