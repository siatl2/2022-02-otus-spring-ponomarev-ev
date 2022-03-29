package ru.otus.homework03.dao;

import ru.otus.homework03.domain.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> getQuestions();
}
