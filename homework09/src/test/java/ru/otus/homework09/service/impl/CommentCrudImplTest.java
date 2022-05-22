package ru.otus.homework09.service.impl;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.homework09.model.Comment;
import ru.otus.homework09.repository.CommentRepository;
import ru.otus.homework09.service.CommentCrud;

import java.util.ArrayList;
import java.util.List;

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
    @Captor
    private ArgumentCaptor<Long> idCaptor;

    @Test
    void readAllCommentsByBookId() {
        List<Comment> comments = new ArrayList<>();
        when(commentRepository.findAllByBookId(idCaptor.capture())).thenReturn(comments);

        commentCrud.readAllCommentsByBookId(SOME_ID);

        assertAll(
                () -> verify(commentRepository, times(1)).findAllByBookId(anyLong())
                , () -> assertEquals(SOME_ID, idCaptor.getValue())
        );
    }
}