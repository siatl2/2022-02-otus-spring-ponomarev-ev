package ru.otus.homework04.dao;

import ru.otus.homework04.domain.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> getQuestions();
}
