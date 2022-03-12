package ru.otus.homework02.service;

import ru.otus.homework02.domain.Student;

public interface Examiner {
    Student askFamilyAndName();
    void askQuestions();
}
