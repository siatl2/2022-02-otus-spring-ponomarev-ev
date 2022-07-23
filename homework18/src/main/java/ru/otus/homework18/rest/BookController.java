package ru.otus.homework18.rest;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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
    private static final long ID_WHEN_UNAVAILABLE = -1L;
    private static final String MESSAGE_WHEN_UNAVAILABLE = "System is down";
    private final BookCrud bookCrud;

    @Autowired
    public BookController(BookCrud bookCrud) {
        this.bookCrud = bookCrud;
    }

    @GetMapping
    @HystrixCommand(commandKey = "getInfo", fallbackMethod = "errorReadAllBooks")
    public Flux<BookDto> readAllBooks() {
        return bookCrud.readAllBooks()
                .map(BookDto::toDto);
    }

    public Flux<BookDto> errorReadAllBooks() {
        return Flux.just(
                new BookDto(ID_WHEN_UNAVAILABLE, MESSAGE_WHEN_UNAVAILABLE, null, null)
        );
    }

    @GetMapping("{id}")
    @HystrixCommand(commandKey = "getInfo", fallbackMethod = "errorRetrieveBook")
    public Mono<ResponseEntity<BookDto>> retrieveBook(@PathVariable long id) {
        return bookCrud
                .retrieveBook(id)
                .map(BookDto::toDto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    public Mono<ResponseEntity<BookDto>> ErrorRetrieveBook(@PathVariable long id) {
        return Mono.just(
                new ResponseEntity<>(
                        new BookDto(ID_WHEN_UNAVAILABLE, MESSAGE_WHEN_UNAVAILABLE, null, null), HttpStatus.OK
                )
        );
    }

    @PostMapping
    @HystrixCommand(commandKey = "getInfo", fallbackMethod = "errorAddBook")
    public Mono<BookDto> addBook(@RequestBody BookDto bookDto) {
        Book book = BookDto.toDomainObject(bookDto);
        return bookCrud.saveBook(book).map(BookDto::toDto);
    }

    public Mono<BookDto> errorAddBook(@RequestBody BookDto bookDto) {
        return Mono.just(
                new BookDto(ID_WHEN_UNAVAILABLE, MESSAGE_WHEN_UNAVAILABLE, null, null)
        );
    }

    @PutMapping
    @HystrixCommand(commandKey = "getInfo", fallbackMethod = "errorUpdateBook")
    public Mono<ResponseEntity<BookDto>> updateBook(@RequestBody BookDto bookDto) {
        Book book = BookDto.toDomainObject(bookDto);

        return bookCrud
                .saveBook(book)
                .map(BookDto::toDto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    public Mono<ResponseEntity<BookDto>> errorUpdateBook(@RequestBody BookDto bookDto) {
        return Mono.just(
                new ResponseEntity<>(
                        new BookDto(ID_WHEN_UNAVAILABLE, MESSAGE_WHEN_UNAVAILABLE, null, null), HttpStatus.OK
                )
        );
    }


    @DeleteMapping("{id}")
    @HystrixCommand(commandKey = "getInfo", fallbackMethod = "errorDeleteBook")
    public Mono<ResponseEntity<Void>> deleteBook(@PathVariable long id) {
        return bookCrud.retrieveBook(id)
                .flatMap(s ->
                        bookCrud.deleteBook(id)
                                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                )
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public Mono<ResponseEntity<Void>> errorDeleteBook(@PathVariable long id) {
        return Mono.just(
                new ResponseEntity<>(
                        null, HttpStatus.OK
                )
        );
    }
}
