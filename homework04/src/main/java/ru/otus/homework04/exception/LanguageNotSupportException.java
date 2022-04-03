package ru.otus.homework04.exception;

public class LanguageNotSupportException extends RuntimeException  {
    public LanguageNotSupportException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
