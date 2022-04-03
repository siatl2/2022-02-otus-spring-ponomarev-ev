package ru.otus.homework04.component.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.otus.homework04.component.LocaleProvider;
import ru.otus.homework04.component.MessageOutput;

import java.util.Locale;

@Component
public class MessageOutputImpl implements MessageOutput {
    private final MessageSource messageSource;
    private final Locale language;

    @Autowired
    public MessageOutputImpl(final MessageSource messageSource
                            , final LocaleProvider localeProvider) {
        this.messageSource = messageSource;
        this.language = localeProvider.getLocale();
    }

    @Override
    public String getMessage(String parameter) {
        return messageSource.getMessage(parameter, null, language);
    }
}
