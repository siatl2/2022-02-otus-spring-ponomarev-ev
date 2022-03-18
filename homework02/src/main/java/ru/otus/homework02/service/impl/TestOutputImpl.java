package ru.otus.homework02.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.homework02.domain.Student;
import ru.otus.homework02.domain.TestResult;
import ru.otus.homework02.service.TestOutput;
import ru.otus.homework02.service.IOService;

@Service
public class TestOutputImpl implements TestOutput {
    private final IOService ioService;

    @Autowired
    public TestOutputImpl(final IOService ioService) {
        this.ioService = ioService;
    }

    @Override
    public void showResults(TestResult testResult) {
        int scoreResult = testResult.getUserScore();
        int scoreMinimum = testResult.getMinimumScore();
        Student student = testResult.getStudent();

        ioService.outputEmptyLine();
        ioService.outputString("===========================");
        ioService.outputEmptyLine();
        ioService.outputString("Students " + student.getFamily() + " " + student.getName());
        ioService.outputEmptyLine();
        ioService.outputString("===========================");
        ioService.outputEmptyLine();
        ioService.outputString("Get score: " + scoreResult);
        ioService.outputEmptyLine();
        ioService.outputString("Minimum score: " + scoreMinimum);
        ioService.outputEmptyLine();
        ioService.outputString("===========================");
        ioService.outputEmptyLine();
        ioService.outputString("Result: ");
        if (scoreResult >= scoreMinimum) {
            ioService.outputString("passed");
        } else {
            ioService.outputString("failed");
        }
    }
}
