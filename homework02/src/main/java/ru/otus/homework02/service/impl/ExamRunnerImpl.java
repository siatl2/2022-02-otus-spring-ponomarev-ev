package ru.otus.homework02.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.homework02.domain.Student;
import ru.otus.homework02.service.ExamRunner;
import ru.otus.homework02.service.Examiner;
import ru.otus.homework02.service.ExaminerOutput;

@Service
public class ExamRunnerImpl implements ExamRunner {
    private final Examiner examiner;
    private final ExaminerOutput examinerOutput;

    @Autowired
    public ExamRunnerImpl(Examiner examiner
                            , ExaminerOutput examinerOutput) {
        this.examiner = examiner;
        this.examinerOutput = examinerOutput;
    }

    @Override
    public void run() {
        Student student = examiner.askFamilyAndName();
        examiner.askQuestions();
        examinerOutput.showResults(student);
    }
}
