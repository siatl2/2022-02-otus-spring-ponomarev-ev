package ru.otus.homework18.rest;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.otus.homework18.rest.dto.CommentDto;
import ru.otus.homework18.service.CommentCrud;

@RestController
@RequestMapping("comments")
public class CommentController {
    private static final long ID_WHEN_UNAVAILABLE = -1L;
    private static final String MESSAGE_WHEN_UNAVAILABLE = "System is down";
    private final CommentCrud commentCrud;

    @Autowired
    public CommentController(CommentCrud commentCrud) {
        this.commentCrud = commentCrud;
    }

    @GetMapping("{bookId}")
    @HystrixCommand(commandKey = "getInfo", fallbackMethod = "errorReadAllComments")
    public Flux<CommentDto> readAllComments(@PathVariable long bookId) {
        return commentCrud.readAllCommentsByBookId(bookId)
                .map(CommentDto::toDto);
    }

    public Flux<CommentDto> errorReadAllComments(@PathVariable long bookId) {
        return Flux.just(
                new CommentDto(ID_WHEN_UNAVAILABLE, MESSAGE_WHEN_UNAVAILABLE, null)
        );
    }
}
