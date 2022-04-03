package ru.otus.homework04.exception;

public class InvalidFileFormatException extends RuntimeException {
    public InvalidFileFormatException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
