package ru.otus.homework02;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import ru.otus.homework02.domain.Student;
import ru.otus.homework02.service.Examiner;

import java.util.Map;

@PropertySource("classpath:application.properties")
@ComponentScan
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

        Examiner examiner = context.getBean(Examiner.class);
        Student student = examiner.askFamilyAndName();
        Map<Integer, String> studentAnswers = examiner.askQuestions();
        int scoreStudent = examiner.calculateResults(studentAnswers);
        examiner.showResults(student, scoreStudent);
    }
}
