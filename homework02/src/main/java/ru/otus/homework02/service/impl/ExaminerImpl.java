package ru.otus.homework02.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.homework02.domain.Student;
import ru.otus.homework02.service.*;

@Service
public class ExaminerImpl implements Examiner {
    private final ExamAsker examAsker;
    private final ExamAskerOutput examAskerOutput;
    private final TestResult testResult;

    @Autowired
    public ExaminerImpl(ExamAsker examAsker
                            , ExamAskerOutput examAskerOutput
                            , TestResult testResult) {
        this.examAsker = examAsker;
        this.examAskerOutput = examAskerOutput;
        this.testResult = testResult;
    }

    @Override
    public Student askFamilyAndName() {
        String family = examAsker.askFamily();
        String name = examAsker.askName();
        return new Student(family, name);
    }

    @Override
    public void askQuestions() {
        int count = examAsker.getCountQuestions();
        for (int i = 0; i < count; i++) {
            examAskerOutput.showQuestionWithVariabkeAnswers(i);
            String answer = examAsker.askAnswer(i);
            testResult.add(i, answer);
        }
    }
}
