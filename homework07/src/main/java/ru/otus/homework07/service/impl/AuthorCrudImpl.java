package ru.otus.homework07.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.homework07.exception.LibraryException;
import ru.otus.homework07.model.Author;
import ru.otus.homework07.repository.AuthorRepository;
import ru.otus.homework07.service.AuthorCrud;
import ru.otus.homework07.service.AuthorOutput;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorCrudImpl implements AuthorCrud {
    private final AuthorOutput authorOutput;
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorCrudImpl(AuthorOutput authorOutput, AuthorRepository authorRepository) {
        this.authorOutput = authorOutput;
        this.authorRepository = authorRepository;
    }

    @Transactional
    @Override
    public void createAuthor(String name) {
        Author author = new Author(0, name);
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

    @Transactional
    @Override
    public void updateAuthor(long id, String name) {
        if (!authorRepository.existsById(id)){
            throw new LibraryException("Don't exist author");
        }
        Author author = new Author(id, name);
        authorRepository.save(author);
    }

    @Transactional
    @Override
    public void deleteAuthor(long id) {
        authorRepository.deleteById(id);
    }
}

