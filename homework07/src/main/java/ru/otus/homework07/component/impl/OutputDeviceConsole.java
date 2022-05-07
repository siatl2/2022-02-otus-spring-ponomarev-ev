package ru.otus.homework07.component.impl;

import org.springframework.stereotype.Component;
import ru.otus.homework07.component.OutputDevice;

import java.io.PrintStream;

@Component
public class OutputDeviceConsole implements OutputDevice {
    @Override
    public PrintStream getPrintStream() {
        return System.out;
    }
}

