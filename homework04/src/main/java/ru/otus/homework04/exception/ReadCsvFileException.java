package ru.otus.homework04.exception;

public class ReadCsvFileException extends RuntimeException {
    public ReadCsvFileException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
