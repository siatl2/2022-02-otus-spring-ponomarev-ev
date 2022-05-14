package ru.otus.homework08.event;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.stereotype.Component;
import ru.otus.homework08.model.Author;
import ru.otus.homework08.model.Book;
import ru.otus.homework08.model.Comment;
import ru.otus.homework08.repository.BookRepository;
import ru.otus.homework08.repository.CommentRepository;

import java.util.List;

@Component
public class AuthorEvent extends AbstractMongoEventListener<Author> {
    private final BookRepository bookRepository;

    @Autowired
    public AuthorEvent(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<Author> event) {
        super.onBeforeDelete(event);
        Document document = event.getDocument();
        Long authorId = document.getLong("_id");
        if (authorId > 0){
            List<Book> books = bookRepository.findByAuthorId(authorId);
            bookRepository.deleteAll(books);
        }
    }
}
