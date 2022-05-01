package ru.otus.homework07.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.homework07.component.IOService;
import ru.otus.homework07.model.Comment;
import ru.otus.homework07.service.CommentOutput;

import java.util.List;

@Service
public class CommentOutputImpl implements CommentOutput {
    private final IOService ioService;

    @Autowired
    public CommentOutputImpl(
            IOService ioService
    ) {
        this.ioService = ioService;
    }

    @Override
    public void outputComment(Comment comment) {
        String outComment = String.format("Comment with id=%d, name=%s", comment.getId(), comment.getName());
        ioService.outputString(outComment);
    }

    @Override
    public void outputComments(List<Comment> comments) {
        ioService.outputString("List comments:");
        ioService.outputString(" id  | Name");
        ioService.outputString("-----|------");

        for(Comment comment : comments){
            String row = String.format("%5s|%s", comment.getId(), comment.getName());
            ioService.outputString(row);
        }
        ioService.outputString("------------");
    }
}
