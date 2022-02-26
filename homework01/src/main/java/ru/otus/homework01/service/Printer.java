package ru.otus.homework01.service;

import ru.otus.homework01.domain.Answer;
import ru.otus.homework01.domain.Question;

import java.util.List;

public class Printer {
    private Parser parser;
    public Printer(Parser parser){
        this.parser = parser;
    }

    public void print(){
        List<Question> listQuestion = parser.getQuestions();

        System.out.println("Print questions:");
        System.out.println("=================");

        for (Question question: listQuestion) {
            System.out.println("Question: " + question.getName());
            System.out.println("variable of answers:");
            if (question.getListAnswer().size() == 0){
                System.out.println("Free answer");
            } else{
                for (Answer answer: question.getListAnswer()){
                    System.out.println("Answer:" + answer.getName());
                }
            }
        }
    }
}
