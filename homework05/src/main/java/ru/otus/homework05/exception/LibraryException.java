package ru.otus.homework05.exception;

public class LibraryException extends RuntimeException {
    public LibraryException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
