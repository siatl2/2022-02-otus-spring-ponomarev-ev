package ru.otus.homework05.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.homework05.component.IOService;
import ru.otus.homework05.domain.Author;
import ru.otus.homework05.service.AuthorOutput;

import java.util.List;

@Service
public class AuthorOutputImpl implements AuthorOutput {
    private final IOService ioService;

    @Autowired
    public AuthorOutputImpl(
            IOService ioService
            ) {
        this.ioService = ioService;
    }

    @Override
    public void outputAuthor(Author author) {
        String outAuthor = String.format("Author with id=%d, name=%s", author.getId(), author.getName());
        ioService.outputString(outAuthor);
    }

    @Override
    public void outputAuthors(List<Author> authors) {
        ioService.outputString("List authors:");
        ioService.outputString(" id  | Name");
        ioService.outputString("-----|------");

        for(Author author : authors){
            String row = String.format("%5s|%s", author.getId(), author.getName());
            ioService.outputString(row);
        }
        ioService.outputString("------------");
    }
}
