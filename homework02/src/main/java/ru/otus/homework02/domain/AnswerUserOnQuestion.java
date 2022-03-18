package ru.otus.homework02.domain;

public class AnswerUserOnQuestion {
    private final Question question;
    private final String answerUser;

    public AnswerUserOnQuestion(final Question question, final String answerUser) {
        this.question = question;
        this.answerUser = answerUser;
    }

    public Question getQuestion() {
        return question;
    }

    public String getAnswerUser() {
        return answerUser;
    }
}
