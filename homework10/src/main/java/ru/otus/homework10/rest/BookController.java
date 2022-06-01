package ru.otus.homework10.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.otus.homework10.exception.NotFoundException;
import ru.otus.homework10.model.Book;
import ru.otus.homework10.rest.dto.BookDto;
import ru.otus.homework10.service.BookCrud;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("books")
public class BookController {
    private final BookCrud bookCrud;

    @Autowired
    public BookController(BookCrud bookCrud) {
        this.bookCrud = bookCrud;
    }

    @GetMapping
    public List<BookDto> readAllBooks() {
        return bookCrud.readAllBooks().stream()
                .map(BookDto::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public BookDto retrieveBook(@PathVariable long id) {
        return BookDto.toDto(bookCrud.retrieveBook(id).orElseThrow(NotFoundException::new));
    }

    @PostMapping
    public BookDto addBook(@RequestBody BookDto bookDto) {
        Book book = BookDto.toDomainObject(bookDto);
        return BookDto.toDto(bookCrud.saveBook(book));
    }

    @PutMapping
    public BookDto updateBook(@RequestBody BookDto bookDto) {
        Book book = BookDto.toDomainObject(bookDto);
        if (!bookCrud.existsById(book.getId())) {
            throw new NotFoundException();
        }
        return BookDto.toDto(bookCrud.saveBook(book));
    }

    @DeleteMapping("{id}")
    public void deleteBook(@PathVariable long id) {
        if (!bookCrud.existsById(id)) {
            throw new NotFoundException();
        }
        bookCrud.deleteBook(id);
    }
}
