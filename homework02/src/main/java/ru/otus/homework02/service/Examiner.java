package ru.otus.homework02.service;

import ru.otus.homework02.domain.Question;
import ru.otus.homework02.domain.Student;

import java.util.Map;

public interface Examiner {
    Student askFamilyAndName();
    Map<Integer, String> askQuestions();
    int calculateResults(Map<Integer, String> userAnswers);
    void showResults(Student student, int scoreResult);
}
