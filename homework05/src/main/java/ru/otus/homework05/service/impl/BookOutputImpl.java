package ru.otus.homework05.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.homework05.component.IOService;
import ru.otus.homework05.domain.Book;
import ru.otus.homework05.service.BookOutput;

import java.util.List;

@Service
public class BookOutputImpl implements BookOutput {
    private final IOService ioService;

    @Autowired
    public BookOutputImpl(
            IOService ioService
    ) {
        this.ioService = ioService;
    }

    @Override
    public void outputBook(Book book) {
        String outBook = String.format("Book with id=%d, name=%s", book.getId(), book.getName());
        ioService.outputString(outBook);
    }

    @Override
    public void outputBooks(List<Book> books) {
        ioService.outputString("List books:");
        ioService.outputString("  id  | Name                         | Author_id | Genre_id |");
        ioService.outputString(" -----|------------------------------|-----------|----------|");

        for(Book book : books){
            String row = String.format(" %5s|%-30s|%11s|%10s|"
                    , book.getId()
                    , book.getName()
                    , book.getAuthor().getId()
                    , book.getGenre().getId()
            );
            ioService.outputString(row);
        }
        ioService.outputString(" ------------------------------------------------------------");
    }
}
