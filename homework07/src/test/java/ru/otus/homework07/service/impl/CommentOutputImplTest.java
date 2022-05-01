package ru.otus.homework07.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.homework07.component.impl.IOServiceStream;
import ru.otus.homework07.model.Author;
import ru.otus.homework07.model.Book;
import ru.otus.homework07.model.Comment;
import ru.otus.homework07.model.Genre;
import ru.otus.homework07.service.CommentOutput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;

@SpringBootTest(classes = {CommentOutputImpl.class})
class CommentOutputImplTest {
    private static final String EXPECTED_AUTHOR = "Comment with id=1, name=new Comment";
    private static final List<String> EXPECTED_AUTHORS = Arrays.asList(
            "List comments:"
            , " id  | Name"
            , "-----|------"
            , "    1|new Comment-1"
            , "    2|new Comment-2"
            , "------------"
    );
    @Autowired
    private CommentOutput commentOutput;

    @MockBean
    private IOServiceStream ioServiceStream;

    @Captor
    private ArgumentCaptor<String> interceptOutput;

    @BeforeEach
    void BeforeEachTest(){
        doNothing().when(ioServiceStream).outputString(interceptOutput.capture());
    }
    @Test
    void outputComment() {
        long authorId = 1L;
        String authorName = "Some Author";
        Author author = new Author(authorId, authorName);

        long genreId = 1L;
        String genreName = "Some Genre";
        Genre genre = new Genre(genreId, genreName);

        long bookId = 1L;
        String bookName = "Some book";
        Book book = new Book(bookId, bookName, author, genre);

        long id = 1L;
        String name = "new Comment";
        Comment comment = new Comment(id, name, book);

        commentOutput.outputComment(comment);

        String expectedValue = EXPECTED_AUTHOR;
        String actualValue = interceptOutput.getValue();
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void outputComments() {
        long authorId = 1L;
        String authorName = "Some Author";
        Author author = new Author(authorId, authorName);

        long genreId = 1L;
        String genreName = "Some Genre";
        Genre genre = new Genre(genreId, genreName);

        long bookId = 1L;
        String bookName = "Some book";
        Book book = new Book(bookId, bookName, author, genre);

        long id1 = 1L;
        String name1 = "new Comment-1";
        Comment comment1 = new Comment(id1, name1, book);

        long id2 = 2L;
        String name2= "new Comment-2";
        Comment comment2 = new Comment(id2, name2, book);

        List<Comment> comments = new ArrayList<>();
        comments.add(comment1);
        comments.add(comment2);

        commentOutput.outputComments(comments);

        List<String> expectedValue = EXPECTED_AUTHORS;
        List<String> actualValue = interceptOutput.getAllValues();
        assertEquals(expectedValue, actualValue);
    }
}