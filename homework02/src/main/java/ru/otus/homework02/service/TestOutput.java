package ru.otus.homework02.service;

import ru.otus.homework02.domain.Student;
import ru.otus.homework02.domain.TestResult;

public interface TestOutput {
    void showResults(TestResult testResult);
}
