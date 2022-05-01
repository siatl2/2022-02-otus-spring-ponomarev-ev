package ru.otus.homework06.component.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.homework06.component.IOService;
import ru.otus.homework06.component.OutputDevice;

import java.io.PrintStream;

@Component
public class IOServiceStream implements IOService {
    private final PrintStream printStream;

    @Autowired
    public IOServiceStream(final OutputDevice outputDevice) {
        this.printStream = outputDevice.getPrintStream();
    }

    @Override
    public void outputString(final String output) {
        printStream.println(output);
    }
}
