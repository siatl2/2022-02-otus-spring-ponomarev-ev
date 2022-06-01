package ru.otus.homework10.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.homework10.model.Book;
import ru.otus.homework10.model.Comment;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private long id;
    private String name;
    private Book book;

    public static Comment toDomainObject(CommentDto dto) {
        return new Comment(
                dto.getId(),
                dto.getName(),
                dto.getBook()
        );
    }

    public static CommentDto toDto(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getName(),
                comment.getBook()
        );
    }
}
