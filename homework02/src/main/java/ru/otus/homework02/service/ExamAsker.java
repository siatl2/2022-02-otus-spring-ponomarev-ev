package ru.otus.homework02.service;

public interface ExamAsker {
    String askFamily();
    String askName();
    int getCountQuestions();
    String askAnswer(int number);
}
