package ru.otus.homework08.event;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.stereotype.Component;
import ru.otus.homework08.model.Book;
import ru.otus.homework08.model.Comment;
import ru.otus.homework08.model.Genre;
import ru.otus.homework08.repository.BookRepository;
import ru.otus.homework08.repository.CommentRepository;

import java.util.List;

@Component
public class GenreEvent extends AbstractMongoEventListener<Genre> {
    private final BookRepository bookRepository;

    @Autowired
    public GenreEvent(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }
    @Override
    public void onBeforeDelete(BeforeDeleteEvent<Genre> event) {
        super.onBeforeDelete(event);
        Document document = event.getDocument();
        Long genreId = document.getLong("_id");
        if (genreId > 0){
            List<Book> books = bookRepository.findByGenreId(genreId);
            bookRepository.deleteAll(books);
        }
    }
}
