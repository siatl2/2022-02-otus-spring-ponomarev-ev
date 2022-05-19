package ru.otus.homework08.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework08.exception.LibraryException;
import ru.otus.homework08.model.Author;
import ru.otus.homework08.repository.AuthorRepository;
import ru.otus.homework08.service.AuthorCrud;
import ru.otus.homework08.service.AuthorOutput;
import ru.otus.homework08.service.SequenceGenerator;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorCrudImpl implements AuthorCrud {
    private static final String SEQ_AUTHOR = "SEQ_AUTHOR";
    private final AuthorOutput authorOutput;
    private final AuthorRepository authorRepository;
    private final SequenceGenerator generator;

    @Autowired
    public AuthorCrudImpl(AuthorOutput authorOutput
            , AuthorRepository authorRepository
            , SequenceGenerator generator) {
        this.authorOutput = authorOutput;
        this.authorRepository = authorRepository;
        this.generator = generator;
    }

    @Override
    @Transactional
    public void createAuthor(String name) {
        long id = generator.getSequenceNumber(SEQ_AUTHOR);
        Author author = new Author(id, name);
        authorRepository.save(author);
    }

    @Override
    public void readAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        authorOutput.outputAuthors(authors);
    }

    @Override
    public void retrieveAuthor(long id) {
        Optional<Author> author = authorRepository.findById(id);
        if (!author.isPresent()) {
            throw new LibraryException("Don't exist author");
        }
        authorOutput.outputAuthor(author.get());
    }

    @Override
    @Transactional
    public void updateAuthor(long id, String name) {
        if (!authorRepository.existsById(id)){
            throw new LibraryException("Don't exist author");
        }
        Author author = new Author(id, name);
        authorRepository.save(author);
    }

    @Override
    @Transactional
    public void deleteAuthor(long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public List<Author> findByName(String name) {
        return authorRepository.findByName(name);
    }
}

