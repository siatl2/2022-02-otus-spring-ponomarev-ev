package ru.otus.homework10.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.homework10.model.Genre;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenreDto {
    private long id;
    private String name;

    public static Genre toDomainObject(GenreDto dto) {
        return new Genre(
                dto.getId(),
                dto.getName()
        );
    }

    public static GenreDto toDto(Genre genre) {
        return new GenreDto(
                genre.getId(),
                genre.getName()
        );
    }
}
