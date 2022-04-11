package ru.otus.homework04.domain;

import java.util.List;

public class QuestionWithVariableAnswers extends Question {
    private final List<Answer> answers;

    public QuestionWithVariableAnswers(String name, List<Answer> answers) {
        super(name);
        this.answers = answers;
    }

    @Override
    public List<Answer> getAnswers() {
        return answers;
    }

    @Override
    public String getCorrectAnswer() {
        int returnValue = 1;
        for (Answer answer : answers){
            if (answer.isCorrect()){
                break;
            }
            returnValue++;
        }
        return String.valueOf(returnValue);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuestionWithVariableAnswers)) return false;

        QuestionWithVariableAnswers that = (QuestionWithVariableAnswers) o;

        return (name.equals(that.name)) &&
                (answers.equals(answers));
    }

    @Override
    public int hashCode() {
        int result = answers != null ? answers.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
