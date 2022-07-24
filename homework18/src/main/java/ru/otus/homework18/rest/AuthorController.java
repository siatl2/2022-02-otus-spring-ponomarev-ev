package ru.otus.homework18.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.otus.homework18.rest.dto.AuthorDto;
import ru.otus.homework18.service.AuthorCrud;

@RestController
@RequestMapping("authors")
public class AuthorController {
    private static final long ID_WHEN_UNAVAILABLE = -1L;
    private static final String MESSAGE_WHEN_UNAVAILABLE = "System is down";
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
