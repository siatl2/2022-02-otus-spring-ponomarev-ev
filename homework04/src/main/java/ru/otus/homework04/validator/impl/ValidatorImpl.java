package ru.otus.homework04.validator.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.homework04.exception.LanguageNotSupportException;
import ru.otus.homework04.validator.Validator;

import java.util.Arrays;
import java.util.List;

@Component
public class ValidatorImpl implements Validator {
    private final String availableLanguages;

    public ValidatorImpl(@Value("${questions.available_languages}") final String availableLanguages) {
        this.availableLanguages = availableLanguages;
    }

    @Override
    public void validateLanguage(String language) {
        String[] arrayLanguages = availableLanguages.split(",");
        List<String> listLanguages = Arrays.asList(arrayLanguages);
        boolean selectAvailableLanguage = listLanguages.contains(language);
        if (!selectAvailableLanguage){
            throw new LanguageNotSupportException("Language " + language + "not supported", new RuntimeException());
        }
    }
}
