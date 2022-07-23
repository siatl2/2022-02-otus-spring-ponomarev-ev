package ru.otus.homework18.rest;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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
    private static final long ID_WHEN_UNAVAILABLE = -1L;
    private static final String MESSAGE_WHEN_UNAVAILABLE = "System is down";
    private final GenreCrud genreCrud;

    @Autowired
    public GenreController(GenreCrud genreCrud) {
        this.genreCrud = genreCrud;
    }

    @GetMapping
    @HystrixCommand(commandKey = "getInfo", fallbackMethod = "errorReadAllGenres")
    public Flux<GenreDto> readAllGenres() {
        return genreCrud.readAllGenres()
                .map(GenreDto::toDto);
    }

    public Flux<GenreDto> errorReadAllGenres() {
        return Flux.just(
                new GenreDto(ID_WHEN_UNAVAILABLE, MESSAGE_WHEN_UNAVAILABLE)
        );
    }
}
