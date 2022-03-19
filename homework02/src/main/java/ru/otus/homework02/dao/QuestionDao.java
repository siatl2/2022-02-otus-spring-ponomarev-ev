package ru.otus.homework02.dao;

import ru.otus.homework02.domain.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> getQuestions();
}
