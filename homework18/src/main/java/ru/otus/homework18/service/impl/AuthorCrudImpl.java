package ru.otus.homework18.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.homework18.exception.UnavailableException;
import ru.otus.homework18.model.Author;
import ru.otus.homework18.repository.AuthorRepository;
import ru.otus.homework18.service.AuthorCrud;
import ru.otus.homework18.service.SequenceGenerator;

@Service
public class AuthorCrudImpl implements AuthorCrud {
    private static final String SEQ_AUTHOR = "SEQ_AUTHOR";
    private final AuthorRepository authorRepository;
    private final SequenceGenerator generator;

    @Autowired
    public AuthorCrudImpl(AuthorRepository authorRepository
            , SequenceGenerator generator) {
        this.authorRepository = authorRepository;
        this.generator = generator;
    }

    @Override
    @Transactional
    @HystrixCommand(commandKey = "getInfo", fallbackMethod = "errorCreateAuthor")
    public Mono<Author> createAuthor(String name) {
        return Mono.just(new Author())
                .zipWith(generator.getNextCounter(SEQ_AUTHOR), (author, counter) -> {
                    author.setId(counter.getSequenceNumber());
                    author.setName(name);
                    return author;
                })
                .flatMap(authorRepository::save);
    }

    public Mono<Author> errorCreateAuthor(String name) {
        return Mono.error(new UnavailableException());
    }

    @Override
    @HystrixCommand(commandKey = "getInfo", fallbackMethod = "errorReadAllAuthors")
    public Flux<Author> readAllAuthors() {
        return authorRepository.findAll();
    }

    public Flux<Author> errorReadAllAuthors() {
        return Flux.error(new UnavailableException());
    }

    @Override
    @HystrixCommand(commandKey = "getInfo", fallbackMethod = "errorFindByName")
    public Flux<Author> findByName(String name) {
        return authorRepository.findByName(name);
    }

    public Flux<Author> errorFindByName(String name) {
        return Flux.error(new UnavailableException());
    }
}

