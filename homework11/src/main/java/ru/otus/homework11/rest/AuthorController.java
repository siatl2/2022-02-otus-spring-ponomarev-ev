package ru.otus.homework11.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.otus.homework11.rest.dto.AuthorDto;
import ru.otus.homework11.service.AuthorCrud;

@RestController
@RequestMapping("authors")
public class AuthorController {
    private final AuthorCrud authorCrud;

    @Autowired
    public AuthorController(AuthorCrud authorCrud) {
        this.authorCrud = authorCrud;
    }

    @GetMapping
    public Flux<AuthorDto> readAllAuthors() {
        return authorCrud.readAllAuthors()
                .map(AuthorDto::toDto);
    }
}
