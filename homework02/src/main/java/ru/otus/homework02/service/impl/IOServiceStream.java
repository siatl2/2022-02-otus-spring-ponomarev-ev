package ru.otus.homework02.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.homework02.service.IOService;
import ru.otus.homework02.service.InputDevice;
import ru.otus.homework02.service.OutputDevice;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

@Service
public class IOServiceStream implements IOService {
    private final PrintStream printStream;
    private final InputStream inputStream;

    Scanner reader;

    @Autowired
    public IOServiceStream(OutputDevice outputDevice, InputDevice inputDevice) {
        this.printStream = outputDevice.getPrintStream();
        this.inputStream = inputDevice.getInputStream();

        reader = new Scanner(inputStream);
    }

    @Override
    public void outputString(String output) {
        printStream.print(output);
    }

    @Override
    public void newLine() {
        printStream.println();
    }

    @Override
    public String inputStringWithPrompt(String prompt) {
        printStream.print(prompt);
        String enterValue = reader.next();
        return enterValue;
    }
}
