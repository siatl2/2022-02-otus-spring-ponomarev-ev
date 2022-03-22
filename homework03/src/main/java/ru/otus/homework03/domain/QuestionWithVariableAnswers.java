package ru.otus.homework03.domain;

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
}
