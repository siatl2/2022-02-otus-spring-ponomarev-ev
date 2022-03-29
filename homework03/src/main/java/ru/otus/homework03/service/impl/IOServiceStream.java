package ru.otus.homework03.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.homework03.service.IOService;
import ru.otus.homework03.service.InputDevice;
import ru.otus.homework03.service.OutputDevice;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

@Service
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
