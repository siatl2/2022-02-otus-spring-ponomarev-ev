package ru.otus.homework11.event;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import ru.otus.homework11.model.Book;
import ru.otus.homework11.model.Comment;
import ru.otus.homework11.repository.AuthorRepository;
import ru.otus.homework11.repository.CommentRepository;
import ru.otus.homework11.repository.GenreRepository;

@Component
public class BookEvent extends AbstractMongoEventListener<Book> {
    private final AuthorRepository authorRepository;
    private final CommentRepository commentRepository;
    private final GenreRepository genreRepository;

    @Autowired
    public BookEvent(AuthorRepository authorRepository
            , CommentRepository commentRepository
            , GenreRepository genreRepository) {
        this.authorRepository = authorRepository;
        this.commentRepository = commentRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<Book> event) {
        super.onBeforeDelete(event);
        Document document = event.getDocument();
        Long bookId = document.getLong("_id");
        if (bookId > 0) {
            Flux<Comment> comments = commentRepository.findAllByBookId(bookId);
            commentRepository.deleteAll(comments);
        }
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Book> event) {
        super.onBeforeConvert(event);
        Book book = event.getSource();
        if (book.getAuthor() != null) {
            authorRepository.save(book.getAuthor());
        }

        if (book.getGenre() != null) {
            genreRepository.save(book.getGenre());
        }
    }
}
