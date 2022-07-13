package ru.otus.homework11.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.homework11.exception.NotFoundException;
import ru.otus.homework11.model.Book;
import ru.otus.homework11.rest.dto.BookDto;
import ru.otus.homework11.service.BookCrud;

@RestController
@RequestMapping("books")
public class BookController {
    private final BookCrud bookCrud;

    @Autowired
    public BookController(BookCrud bookCrud) {
        this.bookCrud = bookCrud;
    }

    @GetMapping
    public Flux<BookDto> readAllBooks() {
        return bookCrud.readAllBooks()
                .map(BookDto::toDto);
    }

    @GetMapping("{id}")
    public Mono<BookDto> retrieveBook(@PathVariable long id) {
        Book book = bookCrud.retrieveBook(id).block();

        if (book == null) {
            throw new NotFoundException();
        }

        return Mono.just(BookDto.toDto(book));
    }

    @PostMapping
    public Mono<BookDto> addBook(@RequestBody BookDto bookDto) {
        Book book = BookDto.toDomainObject(bookDto);
        return bookCrud.saveBook(book).map(x -> BookDto.toDto(x));
    }

    @PutMapping
    public Mono<BookDto> updateBook(@RequestBody BookDto bookDto) {
        Book book = BookDto.toDomainObject(bookDto);
        if (!bookCrud.existsById(book.getId())) {
            throw new NotFoundException();
        }

        return bookCrud.saveBook(book).map(x -> BookDto.toDto(x));
    }

    @DeleteMapping("{id}")
    public Flux<Void> deleteBook(@PathVariable long id) {
        if (!bookCrud.existsById(id)) {
            throw new NotFoundException();
        }
        return bookCrud.deleteBook(id);
    }
}
