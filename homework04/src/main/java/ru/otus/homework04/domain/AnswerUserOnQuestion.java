package ru.otus.homework04.domain;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AnswerUserOnQuestion)) return false;

        AnswerUserOnQuestion that = (AnswerUserOnQuestion) o;

        if (!question.equals(that.question)) return false;
        return answerUser.equals(that.answerUser);
    }

    @Override
    public int hashCode() {
        int result = question.hashCode();
        result = 31 * result + answerUser.hashCode();
        return result;
    }
}
