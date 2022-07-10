package ru.otus.homework11.event;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import ru.otus.homework11.model.Author;
import ru.otus.homework11.model.Book;
import ru.otus.homework11.repository.BookRepository;

@Component
public class AuthorEvent extends AbstractMongoEventListener<Author> {
    private final BookRepository bookRepository;

    @Autowired
    public AuthorEvent(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<Author> event) {
        super.onBeforeDelete(event);
        Document document = event.getDocument();
        Long authorId = document.getLong("_id");
        if (authorId > 0) {
            Flux<Book> books = bookRepository.findByAuthorId(authorId);
            bookRepository.deleteAll(books);
        }
    }
}
