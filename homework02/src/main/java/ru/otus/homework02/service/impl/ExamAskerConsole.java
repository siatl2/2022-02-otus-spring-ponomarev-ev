package ru.otus.homework02.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.homework02.dao.QuestionDao;
import ru.otus.homework02.domain.Answer;
import ru.otus.homework02.domain.Question;
import ru.otus.homework02.service.InputDevice;
import ru.otus.homework02.service.OutputDevice;
import ru.otus.homework02.service.ExamAsker;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

@Service
public class ExamAskerConsole implements ExamAsker {
    private final QuestionDao questionDao;

    private final PrintStream printStream;
    private final InputStream inputStream;

    Scanner reader;

    public ExamAskerConsole(QuestionDao questionDao, OutputDevice outputDevice, InputDevice inputDevice) {
        this.questionDao = questionDao;

        printStream = outputDevice.getPrintStream();
        inputStream = inputDevice.getInputStream();

        reader = new Scanner(inputStream);
    }

    @Override
    public String askFamily() {
        printStream.print("Enter your family: ");
        return reader.next();
    }

    @Override
    public String askName() {
        printStream.print("Enter your name: ");
        return reader.next();
    }

    @Override
    public int getCountQuestions() {
        return questionDao.getQuestions().size();
    }

    @Override
    public void showQuestionWithVariabkeAnswers(int number) {
        Question question = questionDao.getQuestions().get(number);
        printStream.println("=========================================");
        printStream.println("Question number[" + (number + 1) + "]=" + question.getName());
        List<Answer> listAnswer = question.getListAnswer();
        if (listAnswer.size() == 0){
            printStream.print("Enter free answer=");
        } else{
            printStream.println("Variable answers:");
            for (int i = 0; i < listAnswer.size(); i++){
                printStream.println((i + 1) +" - " + listAnswer.get(i).getName());
            }
            printStream.print("Enter number answer=");
        }
    }

    @Override
    public String askAnswer(int number) {
        return reader.next();
    }
}
