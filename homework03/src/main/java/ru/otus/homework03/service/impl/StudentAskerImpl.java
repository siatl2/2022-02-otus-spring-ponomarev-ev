package ru.otus.homework03.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.homework03.domain.Student;
import ru.otus.homework03.exception.LanguageNotSupportException;
import ru.otus.homework03.service.IOService;
import ru.otus.homework03.service.StudentAsker;

import java.util.Locale;

@Service
public class StudentAskerImpl implements StudentAsker {
    private final IOService ioService;
    private final MessageSource messageSource;
    private final Locale language;

    @Autowired
    public StudentAskerImpl(final IOService ioService
                            , final MessageSource messageSource
                            , @Value("${questions.language}") final String language) {
        this.ioService = ioService;
        this.messageSource = messageSource;
        if (language.equalsIgnoreCase("en")||language.equalsIgnoreCase("ru")){
            this.language = Locale.forLanguageTag(language);
        } else {
            throw new LanguageNotSupportException("Language " + language + "not supported", new RuntimeException());
        }
    }

    @Override
    public Student askStudentInfo() {
        String enterYourFamily= messageSource.getMessage("enter_your_family", null, language);
        String family = ioService.inputStringWithPrompt(enterYourFamily + ": ");
        String enterYourName = messageSource.getMessage("enter_your_name", null, language);
        String name = ioService.inputStringWithPrompt(enterYourName + ": ");

        return new Student(family, name);
    }
}
