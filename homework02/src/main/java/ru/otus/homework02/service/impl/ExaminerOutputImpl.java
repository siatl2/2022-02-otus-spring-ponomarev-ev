package ru.otus.homework02.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.homework02.domain.Student;
import ru.otus.homework02.service.ExaminerOutput;
import ru.otus.homework02.service.IOService;
import ru.otus.homework02.service.TestResult;

@Service
public class ExaminerOutputImpl implements ExaminerOutput {
    private final TestResult testResult;
    private final IOService ioService;

    @Autowired
    public ExaminerOutputImpl(TestResult testResult, IOService ioService) {
        this.testResult = testResult;
        this.ioService = ioService;
    }

    @Override
    public void showResults(Student student) {
        int scoreResult = testResult.calculateResult();
        int scoreMinimum = testResult.getMinimumScore();

        ioService.newLine();
        ioService.outputString("===========================");
        ioService.newLine();
        ioService.outputString("Students " + student.getFamily() + " " + student.getName());
        ioService.newLine();
        ioService.outputString("===========================");
        ioService.newLine();
        ioService.outputString("Get score: " + scoreResult);
        ioService.newLine();
        ioService.outputString("Minimum score: " + scoreMinimum);
        ioService.newLine();
        ioService.outputString("===========================");
        ioService.newLine();
        ioService.outputString("Result: ");
        if (scoreResult >= scoreMinimum) {
            ioService.outputString("passed");
        } else {
            ioService.outputString("failed");
        }
    }
}
