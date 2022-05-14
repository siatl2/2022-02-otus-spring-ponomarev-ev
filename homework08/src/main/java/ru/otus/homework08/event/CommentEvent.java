package ru.otus.homework08.event;

import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import ru.otus.homework08.model.Comment;
import ru.otus.homework08.repository.BookRepository;

public class CommentEvent extends AbstractMongoEventListener<Comment> {
    private final BookRepository bookRepository;

    public CommentEvent(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Comment> event) {
        super.onBeforeConvert(event);
        Comment comment = event.getSource();
        if (comment.getBook() != null){
            bookRepository.save(comment.getBook());
        }
    }
}
