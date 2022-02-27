package ru.otus.homework01.service;

import ru.otus.homework01.dao.QuestionDao;
import ru.otus.homework01.domain.Answer;
import ru.otus.homework01.domain.Question;

import java.io.PrintStream;
import java.util.List;

public class Printer {
    private final QuestionDao questionDao;
    private final PrintStream printStream;

    public Printer(QuestionDao questionDao, PrintStream printStream){
        this.questionDao = questionDao;
        this.printStream = printStream;
    }

    private void initPrintStream(){
        if (printStream != null){
            System.setOut(printStream);
        }
    }

    private void restorePrintStream(){
        if (printStream != null){
            System.setOut(System.out);
        }
    }

    public void print(){
        List<Question> listQuestion = questionDao.getQuestions();

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
