package ru.otus.homework03.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.homework03.service.QuestionFile;
import ru.otus.homework03.validator.Validator;

@Component
public class QuestionFileImpl implements QuestionFile {
    private final String templateFileQuestions;
    private final String language;

    @Autowired
    public QuestionFileImpl(@Value("${questions.template_file_name}") final String templateFileQuestions
            , @Value("${questions.language}") final String language
            , final Validator validator) {
        validator.validateLanguage(language);
        this.templateFileQuestions = templateFileQuestions;
        this.language = language;
    }

    @Override
    public String getFileQuestions() {
        String fileQuestions = templateFileQuestions.replace("[language]", language);
        return fileQuestions;
    }
}
