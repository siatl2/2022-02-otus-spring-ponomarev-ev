package ru.otus.homework03.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.otus.homework03.service.MessageOutput;
import ru.otus.homework03.validator.Validator;

import java.util.Locale;

@Component
public class MessageOutputImpl implements MessageOutput {
    private final MessageSource messageSource;
    private final Locale language;

    @Autowired
    public MessageOutputImpl(final MessageSource messageSource
                            , @Value("${questions.language}") final String language
                            , final Validator validator) {
        validator.validateLanguage(language);
        this.messageSource = messageSource;
        this.language = Locale.forLanguageTag(language);
    }

    @Override
    public String getMessage(String parameter) {
        return messageSource.getMessage(parameter, null, language);
    }
}
