package ru.otus.homework11.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.homework11.model.Author;
import ru.otus.homework11.repository.AuthorRepository;
import ru.otus.homework11.service.AuthorCrud;
import ru.otus.homework11.service.SequenceGenerator;

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
    public Mono<Author> createAuthor(String name) {
        return Mono.just(new Author())
                .zipWith(generator.getNextCounter(SEQ_AUTHOR), (author, counter) -> {
                    author.setId(counter.getSequenceNumber());
                    author.setName(name);
                    return author;
                })
                .flatMap(authorRepository::save);
    }

    @Override
    public Flux<Author> readAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Flux<Author> findByName(String name) {
        return authorRepository.findByName(name);
    }
}

