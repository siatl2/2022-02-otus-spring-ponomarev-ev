package ru.otus.homework11.service.impl;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Flux;
import ru.otus.homework11.model.Comment;
import ru.otus.homework11.repository.BookRepository;
import ru.otus.homework11.repository.CommentRepository;
import ru.otus.homework11.service.CommentCrud;
import ru.otus.homework11.service.SequenceGenerator;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {CommentCrudImpl.class})
class CommentCrudImplTest {
    private static final long SOME_ID = 1L;
    @Autowired
    private CommentCrud commentCrud;
    @MockBean
    private CommentRepository commentRepository;
    @MockBean
    private BookRepository bookRepository;
    @MockBean
    private SequenceGenerator sequenceGenerator;
    @Captor
    private ArgumentCaptor<Long> idCaptor;

    @Test
    void readAllCommentsByBookId() {
        Comment comment = new Comment();
        when(commentRepository.findAllByBookId(idCaptor.capture())).thenReturn(Flux.just(comment));

        commentCrud.readAllCommentsByBookId(SOME_ID);

        assertAll(
                () -> verify(commentRepository, times(1)).findAllByBookId(anyLong())
                , () -> assertEquals(SOME_ID, idCaptor.getValue())
        );
    }
}