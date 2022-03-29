package ru.otus.homework03.exception;

public class InvalidFileFormatException extends RuntimeException {
    public InvalidFileFormatException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
