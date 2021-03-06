package ru.otus.homework08.mongock;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import io.changock.migration.api.annotations.NonLockGuarded;
import io.changock.migration.api.annotations.NonLockGuardedType;
import ru.otus.homework08.model.Counter;
import ru.otus.homework08.repository.CounterRepository;
import ru.otus.homework08.service.AuthorCrud;
import ru.otus.homework08.service.BookCrud;
import ru.otus.homework08.service.CommentCrud;
import ru.otus.homework08.service.GenreCrud;

import java.util.ArrayList;
import java.util.List;

@ChangeLog
public class DatabaseChangelog {
    private static final long INIT_SEQ_VALUE = 500L;
    private static final String SEQ_AUTHOR = "SEQ_AUTHOR";
    private static final String SEQ_BOOK = "SEQ_BOOK";
    private static final String SEQ_COMMENT = "SEQ_COMMENT";
    private static final String SEQ_GENRE = "SEQ_GENRE";

    @ChangeSet(order = "001", id = "dropDb", author = "siatl2", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }


    @ChangeSet(order = "002", id = "insertCounters", author = "siatl2")
    public void insertCounters(CounterRepository repository) {
        Counter seqAuthor = new Counter(SEQ_AUTHOR, INIT_SEQ_VALUE);
        Counter seqBook = new Counter(SEQ_BOOK, INIT_SEQ_VALUE);
        Counter seqComment = new Counter(SEQ_COMMENT, INIT_SEQ_VALUE);
        Counter seqGenre = new Counter(SEQ_GENRE, INIT_SEQ_VALUE);

        List<Counter> seqList = new ArrayList<>();
        seqList.add(seqAuthor);
        seqList.add(seqBook);
        seqList.add(seqComment);
        seqList.add(seqGenre);

        repository.saveAll(seqList);
    }

    @ChangeSet(order = "003", id = "insertAuthors", author = "siatl2")
    public void insertAuthors(@NonLockGuarded(NonLockGuardedType.NONE) AuthorCrud crud) {
        crud.createAuthor("JACK LONDON");
    }

    @ChangeSet(order = "004", id = "insertGenres", author = "siatl2")
    public void insertGenres(@NonLockGuarded(NonLockGuardedType.NONE) GenreCrud crud) {
        crud.createGenre("Adventure literature");
    }

    @ChangeSet(order = "005", id = "insertBooks", author = "siatl2")
    public void insertBooks(@NonLockGuarded(NonLockGuardedType.NONE) BookCrud bookCrud,
                            @NonLockGuarded(NonLockGuardedType.NONE) AuthorCrud authorCrud,
                            @NonLockGuarded(NonLockGuardedType.NONE) GenreCrud genreCrud) {
        long authorId = authorCrud.findByName("JACK LONDON").get(0).getId();
        long genreId = genreCrud.findByName("Adventure literature").get(0).getId();

        bookCrud.createBook("Martin Eden", authorId, genreId);
        bookCrud.createBook("The Little Lady", authorId, genreId);
    }

    @ChangeSet(order = "006", id = "insertComments", author = "siatl2")
    public void insertComments(@NonLockGuarded(NonLockGuardedType.NONE) BookCrud bookCrud,
                               @NonLockGuarded(NonLockGuardedType.NONE) CommentCrud commentCrud) {
        long bookId = bookCrud.findByName("Martin Eden").get(0).getId();

        commentCrud.createComment(bookId, "COMMENT-1");
        commentCrud.createComment(bookId, "COMMENT-2");
        commentCrud.createComment(bookId, "COMMENT-3");
        commentCrud.createComment(bookId, "COMMENT-4");
    }
}
