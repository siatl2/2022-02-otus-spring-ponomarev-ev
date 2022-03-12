package ru.otus.homework02.service;

public interface TestResult {
    void add(int numberQuestion, String answerStudent);
    int calculateResult();
    int getMinimumScore();
}
