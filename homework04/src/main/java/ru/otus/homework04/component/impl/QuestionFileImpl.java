package ru.otus.homework04.component.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.homework04.component.LocaleProvider;
import ru.otus.homework04.component.QuestionFile;

@Component
public class QuestionFileImpl implements QuestionFile {
    private final String templateFileQuestions;
    private final String language;

    @Autowired
    public QuestionFileImpl(@Value("${questions.template_file_name}") final String templateFileQuestions
            , final LocaleProvider localeProvider) {
        this.templateFileQuestions = templateFileQuestions;
        this.language = localeProvider.getLocale().getLanguage();
    }

    @Override
    public String getFileQuestions() {
        String fileQuestions = templateFileQuestions.replace("[language]", language);
        return fileQuestions;
    }
}
