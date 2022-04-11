package ru.otus.homework04.component.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.homework04.component.LocaleProvider;
import ru.otus.homework04.validator.Validator;

import java.util.Locale;

@Component
public class LocaleProviderImpl implements LocaleProvider {
    private final Locale language;

    @Autowired
    public LocaleProviderImpl(@Value("${questions.language}") final String language
            , final Validator validator) {
        validator.validateLanguage(language);
        this.language = Locale.forLanguageTag(language);
    }

    @Override
    public Locale getLocale() {
        return language;
    }
}
