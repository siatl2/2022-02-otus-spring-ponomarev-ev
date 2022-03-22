package ru.otus.homework03.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.homework03.domain.Student;
import ru.otus.homework03.domain.TestResult;
import ru.otus.homework03.exception.LanguageNotSupportException;
import ru.otus.homework03.service.TestOutput;
import ru.otus.homework03.service.IOService;

import java.util.Locale;

@Service
public class TestOutputImpl implements TestOutput {
    private final IOService ioService;
    private final MessageSource messageSource;
    private final Locale language;

    @Autowired
    public TestOutputImpl(final IOService ioService
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
    public void showResults(TestResult testResult) {
        int scoreResult = testResult.getUserScore();
        int scoreMinimum = testResult.getMinimumScore();
        Student student = testResult.getStudent();

        ioService.outputEmptyLine();
        ioService.outputString("===========================");
        ioService.outputEmptyLine();
        String students = messageSource.getMessage("student", null, language);
        ioService.outputString(students + " " + student.getFamily() + " " + student.getName());
        ioService.outputEmptyLine();
        ioService.outputString("===========================");
        ioService.outputEmptyLine();
        String getScore = messageSource.getMessage("get_score", null, language);
        ioService.outputString(getScore + ": " + scoreResult);
        ioService.outputEmptyLine();
        String minimumScore = messageSource.getMessage("minimum_score", null, language);
        ioService.outputString(minimumScore + ": " + scoreMinimum);
        ioService.outputEmptyLine();
        ioService.outputString("===========================");
        ioService.outputEmptyLine();
        String result = messageSource.getMessage("result", null, language);
        ioService.outputString(result + ": ");
        if (scoreResult >= scoreMinimum) {
            String passed = messageSource.getMessage("passed", null, language);
            ioService.outputString(passed);
        } else {
            String failed = messageSource.getMessage("failed", null, language);
            ioService.outputString(failed);
        }
        ioService.outputEmptyLine();
    }
}
