package ru.otus.homework04.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.homework04.domain.Student;
import ru.otus.homework04.domain.TestResult;
import ru.otus.homework04.component.MessageOutput;
import ru.otus.homework04.service.TestOutput;
import ru.otus.homework04.component.IOService;

@Service
public class TestOutputImpl implements TestOutput {
    private final IOService ioService;
    private final MessageOutput messageOutput;

    @Autowired
    public TestOutputImpl(final IOService ioService
                            , final MessageOutput messageOutput) {
        this.ioService = ioService;
        this.messageOutput = messageOutput;
    }

    @Override
    public void showResults(TestResult testResult) {
        int scoreResult = testResult.getUserScore();
        int scoreMinimum = testResult.getMinimumScore();
        Student student = testResult.getStudent();

        ioService.outputEmptyLine();
        ioService.outputString("===========================");
        ioService.outputEmptyLine();
        String students = messageOutput.getMessage("student");
        ioService.outputString(students + " " + student.getFamily() + " " + student.getName());
        ioService.outputEmptyLine();
        ioService.outputString("===========================");
        ioService.outputEmptyLine();
        String getScore = messageOutput.getMessage("get_score");
        ioService.outputString(getScore + ": " + scoreResult);
        ioService.outputEmptyLine();
        String minimumScore = messageOutput.getMessage("minimum_score");
        ioService.outputString(minimumScore + ": " + scoreMinimum);
        ioService.outputEmptyLine();
        ioService.outputString("===========================");
        ioService.outputEmptyLine();
        String result = messageOutput.getMessage("result");
        ioService.outputString(result + ": ");
        if (scoreResult >= scoreMinimum) {
            String passed = messageOutput.getMessage("passed");
            ioService.outputString(passed);
        } else {
            String failed = messageOutput.getMessage("failed");
            ioService.outputString(failed);
        }
        ioService.outputEmptyLine();
    }
}
