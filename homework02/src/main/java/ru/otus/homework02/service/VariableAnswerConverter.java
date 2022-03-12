package ru.otus.homework02.service;

import ru.otus.homework02.domain.Question;

public interface VariableAnswerConverter {
    String convertVariableAnswerToString(Question question);
}
