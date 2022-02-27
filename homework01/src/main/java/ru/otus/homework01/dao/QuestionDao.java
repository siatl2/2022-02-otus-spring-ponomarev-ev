package ru.otus.homework01.dao;

import ru.otus.homework01.domain.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> getQuestions();
}
