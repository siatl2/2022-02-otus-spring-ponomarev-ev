package ru.otus.homework10.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.homework10.model.Author;
import ru.otus.homework10.model.Book;
import ru.otus.homework10.model.Genre;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private long id;
    private String name;
    private Author author;
    private Genre genre;

    public static Book toDomainObject(BookDto dto) {
        return new Book(
                dto.getId(),
                dto.getName(),
                dto.getAuthor(),
                dto.getGenre()
        );
    }

    public static BookDto toDto(Book book) {
        return new BookDto(
                book.getId(),
                book.getName(),
                book.getAuthor(),
                book.getGenre()
        );
    }
}
