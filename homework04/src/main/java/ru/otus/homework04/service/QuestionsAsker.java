package ru.otus.homework04.service;

import ru.otus.homework04.domain.AnswerUserOnQuestion;
import ru.otus.homework04.domain.Question;

import java.util.List;

public interface QuestionsAsker {
    List<AnswerUserOnQuestion> askQuestions(List<Question> questionList);
}
