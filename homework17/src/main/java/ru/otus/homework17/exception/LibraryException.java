package ru.otus.homework17.exception;

public class LibraryException extends RuntimeException {
    public LibraryException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public LibraryException(String message) {
        super(message);
    }

    public LibraryException() {
        super();
    }
}

