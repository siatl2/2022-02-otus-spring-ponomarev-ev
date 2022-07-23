package ru.otus.homework18.rest;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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
    @HystrixCommand(commandKey = "getInfo", fallbackMethod = "errorReadAllAuthors")
    public Flux<AuthorDto> readAllAuthors() {
        return authorCrud.readAllAuthors()
                .map(AuthorDto::toDto);
    }

    public Flux<AuthorDto> errorReadAllAuthors() {
        return Flux.just(
                new AuthorDto(ID_WHEN_UNAVAILABLE, MESSAGE_WHEN_UNAVAILABLE)
        );
    }

}
