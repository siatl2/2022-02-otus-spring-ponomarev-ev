package ru.otus.homework11.event;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import ru.otus.homework11.model.Book;
import ru.otus.homework11.model.Genre;
import ru.otus.homework11.repository.BookRepository;

@Component
public class GenreEvent extends AbstractMongoEventListener<Genre> {
    private final BookRepository bookRepository;

    @Autowired
    public GenreEvent(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<Genre> event) {
        super.onBeforeDelete(event);
        Document document = event.getDocument();
        Long genreId = document.getLong("_id");
        if (genreId > 0) {
            Flux<Book> books = bookRepository.findByGenreId(genreId);
            bookRepository.deleteAll(books);
        }
    }
}
