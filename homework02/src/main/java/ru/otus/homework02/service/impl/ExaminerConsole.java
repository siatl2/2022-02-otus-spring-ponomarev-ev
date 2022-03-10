package ru.otus.homework02.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.homework02.domain.Student;
import ru.otus.homework02.service.ExamAsker;
import ru.otus.homework02.service.Examiner;
import ru.otus.homework02.service.OutputDevice;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

@Service
public class ExaminerConsole implements Examiner {
    private final ExamAsker examAsker;
    private final PrintStream printStream;

    @Value("${questions.score_minimum}")
    private int scoreRecomended;

    @Value("${questions.right_answers}")
    private String rigthAnswers;

    @Autowired
    public ExaminerConsole(ExamAsker examAsker, OutputDevice outputDevice) {
        this.examAsker = examAsker;
        printStream = outputDevice.getPrintStream();
    }

    @Override
    public Student askFamilyAndName() {
        String family = examAsker.askFamily();
        String name = examAsker.askName();
        return new Student(family, name);
    }

    @Override
    public Map<Integer, String> askQuestions() {
        Map<Integer, String> studentsAnswers = new HashMap<>();
        int count = examAsker.getCountQuestions();
        for (int i = 0; i < count; i++) {
            examAsker.showQuestionWithVariabkeAnswers(i);
            String answer = examAsker.askAnswer(i);
            studentsAnswers.put(i, answer);
        }
        return studentsAnswers;
    }

    @Override
    public int calculateResults(Map<Integer, String> userAnswers) {
        int score = 0;
        String[] rightAnswers = this.rigthAnswers.split(",");

        for (Map.Entry<Integer, String> userAnswer : userAnswers.entrySet()){
            String userAnswerText = userAnswer.getValue();
            String rightAnswerText = rightAnswers[userAnswer.getKey()];

            if (userAnswerText.equalsIgnoreCase(rightAnswerText)){
                score++;
            }
        }

        return score;
    }

    @Override
    public void showResults(Student student, int scoreResult) {
        printStream.println();
        printStream.println("===========================");
        printStream.println("Students " + student.getFamily() + " " + student.getName());
        printStream.println("===========================");
        printStream.println("Get score: " + scoreResult);
        printStream.println("Minimum score: " + scoreRecomended);
        printStream.println("===========================");
        printStream.print("Result: ");
        if (scoreResult >= scoreRecomended){
            printStream.println("passed");
        } else {
            printStream.print("failed");
        }
    }
}
