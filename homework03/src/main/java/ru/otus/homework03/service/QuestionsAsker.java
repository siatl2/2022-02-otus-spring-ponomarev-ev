package ru.otus.homework03.service;

import ru.otus.homework03.domain.AnswerUserOnQuestion;
import ru.otus.homework03.domain.Question;

import java.util.List;

public interface QuestionsAsker {
    List<AnswerUserOnQuestion> askQuestions(List<Question> questionList);
}
