package ru.otus.homework18.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.homework18.model.Book;
import ru.otus.homework18.rest.dto.BookDto;
import ru.otus.homework18.service.BookCrud;

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
    public Mono<ResponseEntity<BookDto>> retrieveBook(@PathVariable long id) {
        return bookCrud
                .retrieveBook(id)
                .map(BookDto::toDto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<BookDto> addBook(@RequestBody BookDto bookDto) {
        Book book = BookDto.toDomainObject(bookDto);
        return bookCrud.saveBook(book).map(BookDto::toDto);
    }

    @PutMapping
    public Mono<ResponseEntity<BookDto>> updateBook(@RequestBody BookDto bookDto) {
        Book book = BookDto.toDomainObject(bookDto);

        return bookCrud
                .saveBook(book)
                .map(BookDto::toDto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public Mono<ResponseEntity<Void>> deleteBook(@PathVariable long id) {
        return bookCrud.retrieveBook(id)
                .flatMap(s ->
                        bookCrud.deleteBook(id)
                                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                )
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
