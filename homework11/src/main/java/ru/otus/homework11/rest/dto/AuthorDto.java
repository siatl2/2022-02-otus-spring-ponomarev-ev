package ru.otus.homework11.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.homework11.model.Author;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {
    private long id;
    private String name;

    public static Author toDomainObject(AuthorDto dto) {
        return new Author(
                dto.getId(),
                dto.getName()
        );
    }

    public static AuthorDto toDto(Author author) {
        return new AuthorDto(
                author.getId(),
                author.getName()
        );
    }
}