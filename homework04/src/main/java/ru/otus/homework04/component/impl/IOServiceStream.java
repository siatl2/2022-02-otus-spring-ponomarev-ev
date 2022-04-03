package ru.otus.homework04.component.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.homework04.component.IOService;
import ru.otus.homework04.component.InputDevice;
import ru.otus.homework04.component.OutputDevice;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

@Component
public class IOServiceStream implements IOService {
    private final PrintStream printStream;
    private final Scanner reader;

    @Autowired
    public IOServiceStream(final OutputDevice outputDevice, final InputDevice inputDevice) {
        this.printStream = outputDevice.getPrintStream();
        InputStream inputStream = inputDevice.getInputStream();
        reader = new Scanner(inputStream);
    }

    @Override
    public void outputString(final String output) {
        printStream.print(output);
    }

    @Override
    public void outputEmptyLine() {
        printStream.println();
    }

    @Override
    public String inputStringWithPrompt(final String prompt) {
        printStream.print(prompt);
        return reader.next();
    }
}
