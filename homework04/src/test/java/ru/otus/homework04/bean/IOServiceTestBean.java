package ru.otus.homework04.bean;

import ru.otus.homework04.component.IOService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class IOServiceTestBean implements IOService {
    private static final ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();

    private static ByteArrayInputStream arrayInputStream;
    private static Scanner reader;
    @Override
    public void outputString(String output) {
        try {
            arrayOutputStream.write(output.getBytes());
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void outputEmptyLine() {
        try {
            arrayOutputStream.write("\n".getBytes());
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String inputStringWithPrompt(String prompt) {
        String enterValue = reader.nextLine();
        try {
            arrayOutputStream.write((prompt + enterValue + "\n").getBytes());
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        return enterValue;
    }

    public String getOutoutText(){
        return arrayOutputStream.toString();
    }

    public void clearOutput(){
        arrayOutputStream.reset();
    }

    public void setInputText(String input){
        arrayInputStream = new ByteArrayInputStream(input.getBytes());
        reader = new Scanner(arrayInputStream);
    }
    public void clearInput(){
        arrayInputStream.reset();
    }
}
