package ru.otus.homework05.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.homework05.component.IOService;
import ru.otus.homework05.domain.Genre;
import ru.otus.homework05.service.GenreOutput;

import java.util.List;

@Service
public class GenreOutputImpl implements GenreOutput {
    private final IOService ioService;

    @Autowired
    public GenreOutputImpl(
            IOService ioService
    ) {
        this.ioService = ioService;
    }

    @Override
    public void outputGenre(Genre genre) {
        String outGenre = String.format("Genre with id=%d, name=%s", genre.getId(), genre.getName());
        ioService.outputString(outGenre);
    }

    @Override
    public void outputGenres(List<Genre> genres) {
        ioService.outputString("List genres:");
        ioService.outputString(" id  | Name");
        ioService.outputString("-----|------");

        for(Genre genre : genres){
            String row = String.format("%5s|%s", genre.getId(), genre.getName());
            ioService.outputString(row);
        }
        ioService.outputString("------------");
    }
}
