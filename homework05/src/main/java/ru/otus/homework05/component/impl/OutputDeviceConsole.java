package ru.otus.homework05.component.impl;

import org.springframework.stereotype.Component;
import ru.otus.homework05.component.OutputDevice;

import java.io.PrintStream;

@Component
public class OutputDeviceConsole implements OutputDevice {
    @Override
    public PrintStream getPrintStream() {
        return System.out;
    }
}
