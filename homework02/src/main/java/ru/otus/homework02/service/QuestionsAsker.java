package ru.otus.homework02.service;

import ru.otus.homework02.domain.AnswerUserOnQuestion;
import ru.otus.homework02.domain.Question;

import java.util.List;

public interface QuestionsAsker {
    List<AnswerUserOnQuestion> askQuestions(List<Question> questionList);
}
