package ru.otus.homework02.service;

import java.io.PrintStream;

public interface ExamAsker {
    String askFamily();
    String askName();
    int getCountQuestions();
    void showQuestionWithVariabkeAnswers(int number);
    String askAnswer(int number);
}
